package com.bank.transferapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Solicitud de transferencia bancaria")
public class TransferRequest {
    
    @Schema(description = "Número de cuenta de origen (20 caracteres máximo)", 
            example = "12345678901234567890")
    private String cuentaOrigen;
    
    @Schema(description = "Número de cuenta de destino (20 caracteres máximo)", 
            example = "09876543210987654321")
    private String cuentaDestino;
    
    @Schema(description = "Monto a transferir (mayor a cero, hasta 19 dígitos y 4 decimales)", 
            example = "1000.00")
    private BigDecimal monto;
    
    @Schema(description = "Descripción opcional de la transferencia")
    private String descripcion;
    
    @Schema(description = "Clave de idempotencia para evitar duplicados (64 caracteres máximo)", 
            example = "idem-key-12345")
    private String claveIdempotencia;
    
    public TransferRequest() {}
    
    public String getCuentaOrigen() {
        return cuentaOrigen;
    }
    
    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }
    
    public String getCuentaDestino() {
        return cuentaDestino;
    }
    
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getClaveIdempotencia() {
        return claveIdempotencia;
    }
    
    public void setClaveIdempotencia(String claveIdempotencia) {
        this.claveIdempotencia = claveIdempotencia;
    }
}