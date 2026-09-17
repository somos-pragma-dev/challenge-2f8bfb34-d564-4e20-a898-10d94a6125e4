package com.bank.transferapi.service;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.dto.TransferResponse;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferService transferService;

    private TransferRequest transferenciaValida;
    private Transfer transferenciaPersistida;
    private final String claveIdempotencia = "clave-idempotencia-unica";

    @BeforeEach
    void setUp() {
        transferenciaValida = new TransferRequest();
        transferenciaValida.setCuentaOrigen("12345678901234567890");
        transferenciaValida.setCuentaDestino("09876543210987654321");
        transferenciaValida.setMonto(new BigDecimal("5000.00"));
        transferenciaValida.setDescripcion("Pago de servicios");
        transferenciaValida.setClaveIdempotencia(claveIdempotencia);

        transferenciaPersistida = new Transfer();
        transferenciaPersistida.setId(1L);
        transferenciaPersistida.setCuentaOrigen("12345678901234567890");
        transferenciaPersistida.setCuentaDestino("09876543210987654321");
        transferenciaPersistida.setMonto(new BigDecimal("5000.00"));
        transferenciaPersistida.setEstado(TransferEstado.PENDIENTE);
        transferenciaPersistida.setDescripcion("Pago de servicios");
        transferenciaPersistida.setClaveIdempotencia(claveIdempotencia);
    }

    @Test
    @DisplayName("crearTransferencia debe persistir la transferencia cuando la clave de idempotencia es nueva")
    void crearTransferencia_DeberiaPersistir_WhenClaveIdempotenciaNueva() {
        when(transferRepository.existsByClaveIdempotencia(claveIdempotencia)).thenReturn(false);
        when(transferRepository.save(any(Transfer.class))).thenReturn(transferenciaPersistida);

        TransferResponse resultado = transferService.crearTransferencia(transferenciaValida, claveIdempotencia);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("12345678901234567890", resultado.getCuentaOrigen());
        assertEquals("PENDIENTE", resultado.getEstado());
        
        ArgumentCaptor<Transfer> transferCaptor = ArgumentCaptor.forClass(Transfer.class);
        verify(transferRepository).save(transferCaptor.capture());
        
        Transfer transferenciaGuardada = transferCaptor.getValue();
        assertEquals("Pago de servicios", transferenciaGuardada.getDescripcion());
        assertEquals(TransferEstado.PENDIENTE, transferenciaGuardada.getEstado());
    }

    @Test
    @DisplayName("crearTransferencia debe lanzar excepción cuando la clave de idempotencia ya existe")
    void crearTransferencia_DeberiaLanzarExcepcion_WhenClaveIdempotenciaDuplicada() {
        when(transferRepository.existsByClaveIdempotencia(claveIdempotencia)).thenReturn(true);

        TransferException excepcion = assertThrows(TransferException.class, 
                () -> transferService.crearTransferencia(transferenciaValida, claveIdempotencia));

        assertEquals("IDEMPOTENCY_CONFLICT", excepcion.getCodigo());
        verify(transferRepository, never()).save(any(Transfer.class));
    }

    @Test
    @DisplayName("crearTransferencia debe validar que el monto sea mayor a cero")
    void crearTransferencia_DeberiaValidarMontoMayorACero() {
        transferenciaValida.setMonto(BigDecimal.ZERO);

        when(transferRepository.existsByClaveIdempotencia(any())).thenReturn(false);

        assertThrows(TransferException.class, 
                () -> transferService.crearTransferencia(transferenciaValida, claveIdempotencia));
    }

    @Test
    @DisplayName("crearTransferencia debe validar que la cuenta origen no esté vacía")
    void crearTransferencia_DeberiaValidarCuentaOrigenNoVacia() {
        transferenciaValida.setCuentaOrigen("");

        when(transferRepository.existsByClaveIdempotencia(any())).thenReturn(false);

        TransferException excepcion = assertThrows(TransferException.class,
                () -> transferService.crearTransferencia(transferenciaValida, claveIdempotencia));

        assertTrue(excepcion.getMensaje().contains("cuenta de origen"));
    }

    @Test
    @DisplayName("crearTransferencia debe validar que la cuenta destino no esté vacía")
    void crearTransferencia_DeberiaValidarCuentaDestinoNoVacia() {
        transferenciaValida.setCuentaDestino("");

        when(transferRepository.existsByClaveIdempotencia(any())).thenReturn(false);

        TransferException excepcion = assertThrows(TransferException.class,
                () -> transferService.crearTransferencia(transferenciaValida, claveIdempotencia));

        assertTrue(excepcion.getMensaje().contains("cuenta de destino"));
    }

    @Test
    @DisplayName("crearTransferencia debe validar que las cuentas sean diferentes")
    void crearTransferencia_DeberiaValidarCuentasDiferentes() {
        transferenciaValida.setCuentaDestino("12345678901234567890");

        when(transferRepository.existsByClaveIdempotencia(any())).thenReturn(false);

        TransferException excepcion = assertThrows(TransferException.class,
                () -> transferService.crearTransferencia(transferenciaValida, claveIdempotencia));

        assertTrue(excepcion.getMensaje().toLowerCase().contains("misma cuenta"));
    }

    @Test
    @DisplayName("obtenerTransferencia debe retornar la transferencia cuando existe")
    void obtenerTransferencia_DeberiaRetornar_WhenExiste() {
        when(transferRepository.findById(1L)).thenReturn(Optional.of(transferenciaPersistida));

        TransferResponse resultado = transferService.obtenerTransferencia(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(TransferEstado.PENDIENTE.name(), resultado.getEstado());
    }

    @Test
    @DisplayName("obtenerTransferencia debe lanzar excepción cuando no existe")
    void obtenerTransferencia_DeberiaLanzarExcepcion_WhenNoExiste() {
        when(transferRepository.findById(999L)).thenReturn(Optional.empty());

        TransferException excepcion = assertThrows(TransferException.class,
                () -> transferService.obtenerTransferencia(999L));

        assertEquals("NOT_FOUND", excepcion.getCodigo());
    }

    @Test
    @DisplayName("listarTransferenciasPorCuenta debe retornar lista de transferencias de la cuenta")
    void listarTransferenciasPorCuenta_DeberiaRetornarLista() {
        when(transferRepository.findAllByCuentaInvolucrada("12345678901234567890"))
                .thenReturn(List.of(transferenciaPersistida));

        List<TransferResponse> resultados = transferService.listarTransferenciasPorCuenta("12345678901234567890");

        assertEquals(1, resultados.size());
        assertEquals("12345678901234567890", resultados.get(0).getCuentaOrigen());
    }

    @Test
    @DisplayName("listarTransferenciasPorCuenta debe retornar lista vacía cuando no hay transferencias")
    void listarTransferenciasPorCuenta_DeberiaRetornarListaVacia() {
        when(transferRepository.findAllByCuentaInvolucrada("00000000000000000000"))
                .thenReturn(List.of());

        List<TransferResponse> resultados = transferService.listarTransferenciasPorCuenta("00000000000000000000");

        assertTrue(resultados.isEmpty());
    }

    @Test
    @DisplayName("actualizarEstado debe cambiar el estado de la transferencia")
    void actualizarEstado_DeberiaCambiarEstado() {
        when(transferRepository.findById(1L)).thenReturn(Optional.of(transferenciaPersistida));
        when(transferRepository.save(any(Transfer.class))).thenReturn(transferenciaPersistida);

        transferenciaPersistida.setEstado(TransferEstado.COMPLETADA);

        TransferResponse resultado = transferService.actualizarEstado(1L, TransferEstado.COMPLETADA);

        assertNotNull(resultado);
        verify(transferRepository).save(any(Transfer.class));
    }

    @Test
    @DisplayName("actualizarEstado debe lanzar excepción cuando la transferencia no existe")
    void actualizarEstado_DeberiaLanzarExcepcion_WhenNoExiste() {
        when(transferRepository.findById(999L)).thenReturn(Optional.empty());

        TransferException excepcion = assertThrows(TransferException.class,
                () -> transferService.actualizarEstado(999L, TransferEstado.COMPLETADA));

        assertEquals("NOT_FOUND", excepcion.getCodigo());
    }
}