package com.bank.transferapi.exception;

public class TransferException extends RuntimeException {
    
    private final String codigoError;
    private final String detalleTecnico;
    
    public TransferException(String mensaje) {
        super(mensaje);
        this.codigoError = "ERROR_INTERNO";
        this.detalleTecnico = null;
    }
    
    public TransferException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.detalleTecnico = null;
    }
    
    public TransferException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "ERROR_INTERNO";
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
    }
    
    public TransferException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
    }
    
    public TransferException(String mensaje, String codigoError, String detalleTecnico, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = detalleTecnico;
    }
    
    public String getCodigoError() {
        return codigoError;
    }
    
    public String getCodigo() {
        return codigoError;
    }
    
    public String getDetalleTecnico() {
        return detalleTecnico;
    }
    
    public String getMensaje() {
        return getMessage();
    }
    
    public static TransferException cuentaNoEncontrada(String numeroCuenta) {
        return new TransferException(
            "Cuenta no encontrada: " + numeroCuenta, 
            "CUENTA_NO_ENCONTRADA");
    }
    
    public static TransferException saldoInsuficiente(String numeroCuenta, String montoSolicitado, String saldoActual) {
        return new TransferException(
            String.format("Saldo insuficiente en cuenta %s: solicitado %s, disponible %s", 
                numeroCuenta, montoSolicitado, saldoActual),
            "SALDO_INSUFICIENTE");
    }
    
    public static TransferException transferenciaDuplicada(String claveIdempotencia) {
        return new TransferException(
            "Ya existe una transferencia con esta clave de idempotencia: " + claveIdempotencia,
            "IDEMPOTENCIA_DUPLICADA");
    }
    
    public static TransferException limiteExcedido(String monto, String limite) {
        return new TransferException(
            String.format("Monto %s excede el límite permitido de %s", monto, limite),
            "LIMITE_EXCEDIDO");
    }
    
    public static TransferException errorInterno(String operacion, Throwable causa) {
        return new TransferException(
            "Error interno en operación: " + operacion,
            "ERROR_INTERNO",
            causa != null ? causa.getMessage() : null,
            causa);
    }
}