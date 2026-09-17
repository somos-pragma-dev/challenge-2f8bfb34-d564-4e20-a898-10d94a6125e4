package com.bank.transferapi.dto;

import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import java.math.BigDecimal;
import java.time.Instant;

public class TransferResponse {
    
    private Long id;
    private String cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
    private String estado;
    private String descripcion;
    private String fechaCreacion;
    
    public TransferResponse() {}
    
    public static TransferResponse fromTransfer(Transfer transfer) {
        TransferResponse response = new TransferResponse();
        response.setId(transfer.getId());
        response.setCuentaOrigen(transfer.getCuentaOrigen());
        response.setCuentaDestino(transfer.getCuentaDestino());
        response.setMonto(transfer.getMonto());
        response.setEstado(transfer.getEstado().name());
        response.setDescripcion(transfer.getDescripcion());
        response.setFechaCreacion(transfer.getFechaCreacion() != null ? 
            transfer.getFechaCreacion().toString() : null);
        return response;
    }
    
    public static TransferResponse success(Transfer transfer) {
        return fromTransfer(transfer);
    }
    
    public static TransferResponse pending(Transfer transfer) {
        return fromTransfer(transfer);
    }
    
    public static TransferResponse error(String mensaje, String codigo) {
        TransferResponse response = new TransferResponse();
        response.setEstado("ERROR");
        return response;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}