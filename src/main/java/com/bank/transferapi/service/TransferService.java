package com.bank.transferapi.service;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.dto.TransferResponse;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferService {
    
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private static final int MAX_CUENTA_LENGTH = 20;
    private static final int MAX_DESCRIPCION_LENGTH = 255;
    private static final int MAX_IDEMPOTENCY_KEY_LENGTH = 64;
    private static final BigDecimal MONTO_MINIMO = BigDecimal.valueOf(0.01);
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("9999999999999.9999");
    
    private final TransferRepository transferRepository;
    
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }
    
    @Transactional
    public TransferResponse crearTransferencia(TransferRequest request, String claveIdempotencia) {
        logger.info("Iniciando transferencia: cuentaOrigen={}, cuentaDestino={}, monto={}",
            request.getCuentaOrigen(), request.getCuentaDestino(), request.getMonto());
        
        validarIdempotencia(claveIdempotencia);
        validarRequest(request);
        validarCuentas(request.getCuentaOrigen(), request.getCuentaDestino());
        validarMonto(request.getMonto());
        
        Transfer transferencia = new Transfer();
        transferencia.setCuentaOrigen(request.getCuentaOrigen());
        transferencia.setCuentaDestino(request.getCuentaDestino());
        transferencia.setMonto(request.getMonto());
        transferencia.setDescripcion(request.getDescripcion());
        transferencia.setClaveIdempotencia(claveIdempotencia);
        transferencia.setEstado(TransferEstado.PENDIENTE);
        
        Transfer transferGuardada = transferRepository.save(transferencia);
        logger.info("Transferencia creada exitosamente con ID: {}", transferGuardada.getId());
        
        boolean procesada = procesarTransferencia(transferGuardada);
        if (!procesada) {
            transferGuardada.setEstado(TransferEstado.FALLIDA);
            transferGuardada = transferRepository.save(transferGuardada);
        }
        
        return TransferResponse.fromTransfer(transferGuardada);
    }
    
    private boolean procesarTransferencia(Transfer transferencia) {
        logger.info("Procesando transferencia ID: {}", transferencia.getId());
        
        if (!validarSaldoCuenta(transferencia.getCuentaOrigen(), transferencia.getMonto())) {
            logger.warn("Saldo insuficiente para transferencia ID: {}", transferencia.getId());
            return false;
        }
        
        if (!validarCuentaActiva(transferencia.getCuentaOrigen()) || 
            !validarCuentaActiva(transferencia.getCuentaDestino())) {
            logger.warn("Una o ambas cuentas no están activas para transferencia ID: {}", 
                transferencia.getId());
            return false;
        }
        
        transferencia.setEstado(TransferEstado.COMPLETADA);
        transferRepository.save(transferencia);
        logger.info("Transferencia ID: {} completada exitosamente", transferencia.getId());
        return true;
    }
    
    private boolean validarSaldoCuenta(String cuenta, BigDecimal monto) {
        return true;
    }
    
    private boolean validarCuentaActiva(String cuenta) {
        return true;
    }
    
    @Transactional(readOnly = true)
    public TransferResponse obtenerTransferencia(Long id) {
        logger.info("Obteniendo transferencia con ID: {}", id);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException("Transferencia no encontrada", "NOT_FOUND"));
        
        return TransferResponse.fromTransfer(transferencia);
    }
    
    @Transactional(readOnly = true)
    public Optional<Transfer> obtenerTransferenciaPorId(Long id) {
        return transferRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Transfer> obtenerPorClaveIdempotencia(String claveIdempotencia) {
        return transferRepository.findByClaveIdempotencia(claveIdempotencia);
    }
    
    @Transactional(readOnly = true)
    public List<TransferResponse> listarTransferencias(String cuentaOrigen, String cuentaDestino, TransferEstado estado) {
        logger.info("Listando transferencias - cuentaOrigen: {}, cuentaDestino: {}, estado: {}",
            cuentaOrigen, cuentaDestino, estado);
        
        List<Transfer> transferencias;
        
        if (cuentaOrigen != null && !cuentaOrigen.isEmpty()) {
            transferencias = transferRepository.findByCuentaOrigen(cuentaOrigen);
        } else if (cuentaDestino != null && !cuentaDestino.isEmpty()) {
            transferencias = transferRepository.findByCuentaDestino(cuentaDestino);
        } else if (estado != null) {
            transferencias = transferRepository.findByEstado(estado);
        } else {
            transferencias = transferRepository.findAll();
        }
        
        return transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TransferResponse> listarTransferenciasPorCuenta(String cuenta) {
        logger.info("Listando transferencias para cuenta: {}", cuenta);
        
        List<Transfer> transferencias = transferRepository.findAllByCuentaInvolucrada(cuenta);
        
        return transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<Transfer> obtenerTransferenciasPorCuenta(String cuenta) {
        return transferRepository.findAllByCuentaInvolucrada(cuenta);
    }
    
    @Transactional
    public TransferResponse actualizarEstado(Long id, TransferEstado nuevoEstado) {
        logger.info("Actualizando estado de transferencia ID: {} a {}", id, nuevoEstado);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException("Transferencia no encontrada", "NOT_FOUND"));
        
        transferencia.setEstado(nuevoEstado);
        Transfer actualizada = transferRepository.save(transferencia);
        
        logger.info("Transferencia ID: {} actualizada a estado: {}", id, nuevoEstado);
        return TransferResponse.fromTransfer(actualizada);
    }
    
    @Transactional
    public Transfer cancelarTransferencia(Long id) {
        logger.info("Cancelando transferencia ID: {}", id);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException("Transferencia no encontrada", "NOT_FOUND"));
        
        if (transferencia.getEstado() == TransferEstado.COMPLETADA) {
            throw new TransferException("No se puede cancelar una transferencia completada", 
                "CANCELACION_INVALIDA");
        }
        
        transferencia.setEstado(TransferEstado.CANCELADA);
        return transferRepository.save(transferencia);
    }
    
    @Transactional(readOnly = true)
    public long countByEstado(TransferEstado estado) {
        return transferRepository.countByEstado(estado);
    }
    
    @Transactional(readOnly = true)
    public BigDecimal sumMontoEnviadoDesde(String cuenta, Instant desde) {
        return transferRepository.sumMontoEnviadoDesde(cuenta, desde != null ? desde : Instant.EPOCH);
    }
    
    @Transactional(readOnly = true)
    public BigDecimal sumMontoRecibidoDesde(String cuenta, Instant desde) {
        return transferRepository.sumMontoRecibidoDesde(cuenta, desde != null ? desde : Instant.EPOCH);
    }
    
    private void validarIdempotencia(String claveIdempotencia) {
        if (claveIdempotencia != null && !claveIdempotencia.isEmpty()) {
            if (claveIdempotencia.length() > MAX_IDEMPOTENCY_KEY_LENGTH) {
                throw new TransferException(
                    "La clave de idempotencia excede el límite de " + MAX_IDEMPOTENCY_KEY_LENGTH + " caracteres",
                    "LIMITE_EXCEDIDO");
            }
            if (transferRepository.existsByClaveIdempotencia(claveIdempotencia)) {
                throw TransferException.transferenciaDuplicada(claveIdempotencia);
            }
        }
    }
    
    private void validarRequest(TransferRequest request) {
        if (request == null) {
            throw new TransferException("La solicitud no puede ser nula", "VALIDATION_ERROR");
        }
    }
    
    private void validarCuentas(String cuentaOrigen, String cuentaDestino) {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new TransferException("La cuenta de origen es obligatoria", "VALIDATION_ERROR");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new TransferException("La cuenta de destino es obligatoria", "VALIDATION_ERROR");
        }
        if (cuentaOrigen.length() > MAX_CUENTA_LENGTH) {
            throw new TransferException(
                "La cuenta de origen excede el límite de " + MAX_CUENTA_LENGTH + " caracteres",
                "LIMITE_EXCEDIDO");
        }
        if (cuentaDestino.length() > MAX_CUENTA_LENGTH) {
            throw new TransferException(
                "La cuenta de destino excede el límite de " + MAX_CUENTA_LENGTH + " caracteres",
                "LIMITE_EXCEDIDO");
        }
        if (cuentaOrigen.equals(cuentaDestino)) {
            throw new TransferException(
                "La cuenta de origen y destino no pueden ser la misma",
                "VALIDATION_ERROR");
        }
    }
    
    private void validarMonto(BigDecimal monto) {
        if (monto == null) {
            throw new TransferException("El monto es obligatorio", "VALIDATION_ERROR");
        }
        if (monto.compareTo(MONTO_MINIMO) <= 0) {
            throw new TransferException(
                "El monto debe ser mayor a " + MONTO_MINIMO,
                "VALIDATION_ERROR");
        }
        if (monto.compareTo(MONTO_MAXIMO) > 0) {
            throw new TransferException(
                "El monto excede el límite máximo de " + MONTO_MAXIMO,
                "LIMITE_EXCEDIDO");
        }
    }
}