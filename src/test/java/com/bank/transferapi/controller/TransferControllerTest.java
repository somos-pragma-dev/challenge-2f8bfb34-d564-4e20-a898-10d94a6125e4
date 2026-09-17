package com.bank.transferapi.controller;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.dto.TransferResponse;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransferController.class)
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransferService transferService;

    private TransferRequest transferenciaValida;
    private TransferResponse transferenciaResponse;
    private Transfer transferencia;

    @BeforeEach
    void setUp() {
        transferenciaValida = new TransferRequest();
        transferenciaValida.setCuentaOrigen("12345678901234567890");
        transferenciaValida.setCuentaDestino("09876543210987654321");
        transferenciaValida.setMonto(new BigDecimal("1000.00"));
        transferenciaValida.setDescripcion("Transferencia de prueba");
        transferenciaValida.setClaveIdempotencia("idem-key-12345");

        transferenciaResponse = new TransferResponse();
        transferenciaResponse.setId(1L);
        transferenciaResponse.setCuentaOrigen("12345678901234567890");
        transferenciaResponse.setCuentaDestino("09876543210987654321");
        transferenciaResponse.setMonto(new BigDecimal("1000.00"));
        transferenciaResponse.setEstado(TransferEstado.PENDIENTE.name());
        transferenciaResponse.setDescripcion("Transferencia de prueba");
        transferenciaResponse.setFechaCreacion(Instant.now().toString());

        transferencia = new Transfer();
        transferencia.setId(1L);
        transferencia.setCuentaOrigen("12345678901234567890");
        transferencia.setCuentaDestino("09876543210987654321");
        transferencia.setMonto(new BigDecimal("1000.00"));
        transferencia.setEstado(TransferEstado.PENDIENTE);
        transferencia.setDescripcion("Transferencia de prueba");
        transferencia.setClaveIdempotencia("idem-key-12345");
    }

    @Test
    @DisplayName("POST /api/transferencias debe retornar 201 cuando la transferencia es exitosa")
    void crearTransferencia_DeberiaRetornar201_WhenTransferenciaExitosa() throws Exception {
        when(transferService.crearTransferencia(any(TransferRequest.class), eq("idem-key-12345")))
                .thenReturn(transferenciaResponse);

        mockMvc.perform(post("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Idempotency-Key", "idem-key-12345")
                        .content(objectMapper.writeValueAsString(transferenciaValida)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cuentaOrigen").value("12345678901234567890"))
                .andExpect(jsonPath("$.cuentaDestino").value("09876543210987654321"))
                .andExpect(jsonPath("$.monto").value(1000.00))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        Mockito.verify(transferService).crearTransferencia(
                Mockito.argThat(request -> 
                        request.getCuentaOrigen().equals("12345678901234567890") &&
                        request.getMonto().compareTo(new BigDecimal("1000.00")) == 0),
                eq("idem-key-12345"));
    }

    @Test
    @DisplayName("POST /api/transferencias debe retornar 400 cuando la cuenta origen es inválida")
    void crearTransferencia_DeberiaRetornar400_WhenCuentaOrigenInvalida() throws Exception {
        TransferRequest requestInvalida = new TransferRequest();
        requestInvalida.setCuentaOrigen("");
        requestInvalida.setCuentaDestino("09876543210987654321");
        requestInvalida.setMonto(new BigDecimal("1000.00"));
        requestInvalida.setClaveIdempotencia("idem-key-123");

        when(transferService.crearTransferencia(any(TransferRequest.class), any()))
                .thenThrow(new TransferException("La cuenta de origen es inválida"));

        mockMvc.perform(post("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Idempotency-Key", "idem-key-123")
                        .content(objectMapper.writeValueAsString(requestInvalida)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    @DisplayName("POST /api/transferencias debe retornar 409 cuando la clave de idempotencia ya existe")
    void crearTransferencia_DeberiaRetornar409_WhenClaveIdempotenciaDuplicada() throws Exception {
        when(transferService.crearTransferencia(any(TransferRequest.class), eq("duplicate-key")))
                .thenThrow(new TransferException("Ya existe una transferencia con esta clave de idempotencia", "IDEMPOTENCY_CONFLICT"));

        mockMvc.perform(post("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Idempotency-Key", "duplicate-key")
                        .content(objectMapper.writeValueAsString(transferenciaValida)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.codigo").value("IDEMPOTENCY_CONFLICT"));
    }

    @Test
    @DisplayName("GET /api/transferencias/{id} debe retornar 200 con los datos de la transferencia")
    void obtenerTransferencia_DeberiaRetornar200_WhenExiste() throws Exception {
        when(transferService.obtenerTransferencia(1L)).thenReturn(transferenciaResponse);

        mockMvc.perform(get("/api/transferencias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cuentaOrigen").value("12345678901234567890"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    @DisplayName("GET /api/transferencias/{id} debe retornar 404 cuando no existe")
    void obtenerTransferencia_DeberiaRetornar404_WhenNoExiste() throws Exception {
        when(transferService.obtenerTransferencia(999L))
                .thenThrow(new TransferException("Transferencia no encontrada", "NOT_FOUND"));

        mockMvc.perform(get("/api/transferencias/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value("NOT_FOUND"));
    }

    @Test
    @DisplayName("GET /api/transferencias debe retornar 200 con lista de transferencias por cuenta")
    void listarTransferencias_DeberiaRetornar200_WhenConsultaExitosa() throws Exception {
        when(transferService.listarTransferenciasPorCuenta("12345678901234567890"))
                .thenReturn(List.of(transferenciaResponse));

        mockMvc.perform(get("/api/transferencias")
                        .param("cuenta", "12345678901234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].cuentaOrigen").value("12345678901234567890"));
    }

    @Test
    @DisplayName("POST /api/transferencias debe retornar 400 cuando el monto es menor o igual a cero")
    void crearTransferencia_DeberiaRetornar400_WhenMontoInvalido() throws Exception {
        TransferRequest requestMontoInvalido = new TransferRequest();
        requestMontoInvalido.setCuentaOrigen("12345678901234567890");
        requestMontoInvalido.setCuentaDestino("09876543210987654321");
        requestMontoInvalido.setMonto(BigDecimal.ZERO);
        requestMontoInvalido.setClaveIdempotencia("idem-key-monto");

        mockMvc.perform(post("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Idempotency-Key", "idem-key-monto")
                        .content(objectMapper.writeValueAsString(requestMontoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/transferencias debe retornar 400 cuando falta el header de idempotencia")
    void crearTransferencia_DeberiaRetornar400_WhenFaltaHeaderIdempotencia() throws Exception {
        mockMvc.perform(post("/api/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transferenciaValida)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").exists());
    }
}