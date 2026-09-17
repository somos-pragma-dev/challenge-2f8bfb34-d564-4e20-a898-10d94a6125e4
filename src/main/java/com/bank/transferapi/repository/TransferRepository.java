package com.bank.transferapi.repository;

import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    
    Optional<Transfer> findByClaveIdempotencia(String claveIdempotencia);
    
    boolean existsByClaveIdempotencia(String claveIdempotencia);
    
    List<Transfer> findByCuentaOrigen(String cuentaOrigen);
    
    List<Transfer> findByCuentaDestino(String cuentaDestino);
    
    List<Transfer> findByEstado(TransferEstado estado);
    
    @Query("SELECT t FROM Transfer t WHERE t.cuentaOrigen = :cuenta OR t.cuentaDestino = :cuenta")
    List<Transfer> findAllByCuentaInvolucrada(@Param("cuenta") String cuenta);
    
    List<Transfer> findByFechaCreacionBetween(@Param("inicio") Instant inicio, @Param("fin") Instant fin);
    
    List<Transfer> findByEstadoAndFechaCreacionAfter(@Param("desde") Instant desde);
    
    @Query("SELECT COUNT(t) FROM Transfer t WHERE t.estado = :estado")
    long countByEstado(@Param("estado") TransferEstado estado);
    
    List<Transfer> findByCuentaOrigenAndEstado(@Param("estado") TransferEstado estado);
    
    @Query("SELECT t FROM Transfer t WHERE t.cuentaOrigen = :cuenta OR t.cuentaDestino = :cuenta ORDER BY t.fechaCreacion DESC")
    List<Transfer> findRecentByCuenta(@Param("cuenta") String cuenta, @Param("limit") int limit);
    
    @Modifying
    @Query("UPDATE Transfer t SET t.estado = :nuevoEstado WHERE t.id = :id")
    int actualizarEstado(@Param("id") Long id, @Param("nuevoEstado") TransferEstado nuevoEstado);
    
    @Query("SELECT SUM(t.monto) FROM Transfer t WHERE t.cuentaOrigen = :cuenta AND t.estado = 'COMPLETADA' AND t.fechaCreacion >= :desde")
    BigDecimal sumMontoEnviadoDesde(@Param("cuenta") String cuenta, @Param("desde") Instant desde);
    
    @Query("SELECT SUM(t.monto) FROM Transfer t WHERE t.cuentaDestino = :cuenta AND t.estado = 'COMPLETADA' AND t.fechaCreacion >= :desde")
    BigDecimal sumMontoRecibidoDesde(@Param("cuenta") String cuenta, @Param("desde") Instant desde);
}