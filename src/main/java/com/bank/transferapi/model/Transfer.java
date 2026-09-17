package com.bank.transferapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transferencias")
public class Transfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cuenta_origen", length = 20, nullable = false)
    private String cuentaOrigen;
    
    @Column(name = "cuenta_destino", length = 20, nullable = false)
    private String cuentaDestino;
    
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferEstado estado;
    
    @Column(name = "clave_idempotencia", length = 64, unique = true)
    private String claveIdempotencia;
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Instant fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;
    
    @Column(length = 255)
    private String descripcion;
    
    public enum TransferEstado {
        PENDIENTE, COMPLETADA, FALLIDA, CANCELADA
    }
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = Instant.now();
        fechaActualizacion = Instant.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = Instant.now();
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
    
    public TransferEstado getEstado() {
        return estado;
    }
    
    public void setEstado(TransferEstado estado) {
        this.estado = estado;
    }
    
    public String getClaveIdempotencia() {
        return claveIdempotencia;
    }
    
    public void setClaveIdempotencia(String claveIdempotencia) {
        this.claveIdempotencia = claveIdempotencia;
    }
    
    public Instant getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}