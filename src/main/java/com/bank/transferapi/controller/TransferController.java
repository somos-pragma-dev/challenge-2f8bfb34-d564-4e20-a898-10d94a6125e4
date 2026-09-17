package com.bank.transferapi.controller;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.dto.TransferResponse;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transferencias")
@Tag(name = "Transferencias", description = "API para gestión de transferencias de fondos entre cuentas bancarias")
public class TransferController {
    
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
    private static final String IDEMPOTENCY_HEADER = "X-Idempotency-Key";
    
    private final TransferService transferService;
    
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transferencia", 
               description = "Crea una transferencia de fondos entre dos cuentas. " +
                             "Soporta idempotencia mediante el header X-Idempotency-Key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transferencia creada exitosamente",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Transferencia duplicada (idempotencia)"),
        @ApiResponse(responseCode = "422", description = "Error de validación de negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TransferResponse> crearTransferencia(
            @Valid @RequestBody TransferRequest request,
            @RequestHeader(value = IDEMPOTENCY_HEADER, required = false) String idempotencyKey) {
        
        String claveIdempotencia = resolverClaveIdempotencia(idempotencyKey, request);
        logger.info("Iniciando transferencia: origen={}, destino={}, monto={}, idempotencyKey={}",
                    request.cuentaOrigen(), request.cuentaDestino(), request.monto(), claveIdempotencia);
        
        try {
            Transfer transferencia = transferService.crearTransferencia(request, claveIdempotencia);
            TransferResponse response = TransferResponse.fromTransfer(transferencia);
            logger.info("Transferencia creada exitosamente con ID: {}", transferencia.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (TransferException e) {
            logger.error("Error de negocio al crear transferencia: {}", e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error inesperado al crear transferencia: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error al procesar la transferencia", e);
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener transferencia por ID", 
               description = "Recupera los detalles de una transferencia específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transferencia encontrada",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "404", description = "Transferencia no encontrada")
    })
    public ResponseEntity<TransferResponse> obtenerTransferencia(
            @Parameter(description = "ID de la transferencia") @PathVariable Long id) {
        logger.debug("Consultando transferencia con ID: {}", id);
        
        Transfer transferencia = transferService.obtenerTransferenciaPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Transferencia no encontrada con ID: " + id));
        
        return ResponseEntity.ok(TransferResponse.fromTransfer(transferencia));
    }
    
    @GetMapping
    @Operation(summary = "Listar transferencias", 
               description = "Lista todas las transferencias con filtros opcionales")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transferencias")
    })
    public ResponseEntity<List<TransferResponse>> listarTransferencias(
            @Parameter(description = "Filtrar por cuenta de origen") 
            @RequestParam(required = false) String cuentaOrigen,
            
            @Parameter(description = "Filtrar por cuenta de destino") 
            @RequestParam(required = false) String cuentaDestino,
            
            @Parameter(description = "Filtrar por estado") 
            @RequestParam(required = false) TransferEstado estado) {
        
        logger.debug("Listando transferencias: origen={}, destino={}, estado={}", 
                     cuentaOrigen, cuentaDestino, estado);
        
        List<Transfer> transferencias = transferService.listarTransferencias(
            cuentaOrigen, cuentaDestino, estado);
        
        List<TransferResponse> responses = transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .toList();
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/cuenta/{numeroCuenta}")
    @Operation(summary = "Obtener transferencias por cuenta", 
               description = "Recupera todas las transferencias asociadas a una cuenta (como origen o destino)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transferencias de la cuenta")
    })
    public ResponseEntity<List<TransferResponse>> obtenerTransferenciasPorCuenta(
            @Parameter(description = "Número de cuenta") @PathVariable String numeroCuenta) {
        
        logger.debug("Consultando transferencias para cuenta: {}", numeroCuenta);
        List<Transfer> transferencias = transferService.obtenerTransferenciasPorCuenta(numeroCuenta);
        
        List<TransferResponse> responses = transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .toList();
        
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar una transferencia", 
               description = "Cancela una transferencia existente (solo si está en estado PENDIENTE)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transferencia cancelada",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "400", description = "No se puede cancelar la transferencia"),
        @ApiResponse(responseCode = "404", description = "Transferencia no encontrada")
    })
    public ResponseEntity<TransferResponse> cancelarTransferencia(
            @Parameter(description = "ID de la transferencia a cancelar") @PathVariable Long id) {
        
        logger.info("Cancelando transferencia ID: {}", id);
        
        try {
            Transfer transferencia = transferService.cancelarTransferencia(id);
            return ResponseEntity.ok(TransferResponse.fromTransfer(transferencia));
        } catch (TransferException e) {
            logger.error("Error al cancelar transferencia: {}", e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage(), e);
        }
    }
    
    @GetMapping("/{id}/estado")
    @Operation(summary = "Consultar estado de transferencia", 
               description = "Consulta el estado actual de una transferencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado de la transferencia"),
        @ApiResponse(responseCode = "404", description = "Transferencia no encontrada")
    })
    public ResponseEntity<TransferResponse> consultarEstado(
            @Parameter(description = "ID de la transferencia") @PathVariable Long id) {
        
        logger.debug("Consultando estado de transferencia ID: {}", id);
        
        Transfer transferencia = transferService.obtenerTransferenciaPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Transferencia no encontrada con ID: " + id));
        
        return ResponseEntity.ok(TransferResponse.fromTransfer(transferencia));
    }
    
    @GetMapping("/ idempotencia/{clave}")
    @Operation(summary = "Verificar clave de idempotencia", 
               description = "Verifica si existe una transferencia con la clave de idempotencia proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resultado de la verificación",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "404", description = "No existe transferencia con esa clave")
    })
    public ResponseEntity<TransferResponse> verificarIdempotencia(
            @Parameter(description = "Clave de idempotencia") @PathVariable String clave) {
        
        logger.debug("Verificando idempotencia para clave: {}", clave);
        
        return transferService.obtenerPorClaveIdempotencia(clave)
            .map(t -> ResponseEntity.ok(TransferResponse.fromTransfer(t)))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "No existe transferencia con clave de idempotencia: " + clave));
    }
    
    private String resolverClaveIdempotencia(String headerKey, TransferRequest request) {
        if (headerKey != null && !headerKey.isBlank()) {
            return headerKey;
        }
        if (request.claveIdempotencia() != null && !request.claveIdempotencia().isBlank()) {
            return request.claveIdempotencia();
        }
        String generatedKey = UUID.randomUUID().toString();
        logger.debug("Generated idempotency key: {}", generatedKey);
        return generatedKey;
    }
}