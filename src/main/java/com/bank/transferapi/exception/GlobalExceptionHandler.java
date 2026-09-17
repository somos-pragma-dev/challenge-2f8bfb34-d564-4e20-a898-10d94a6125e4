package com.bank.transferapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> manejarTransferException(
            TransferException ex, WebRequest request) {
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .codigo(ex.getCodigoError())
                .mensaje(ex.getMensaje())
                .detalle(ex.getDetalleTecnico())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        HttpStatus estado = determinarEstadoHttp(ex.getCodigoError());
        return new ResponseEntity<>(error, estado);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacionException(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> errores = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                            ? error.getDefaultMessage() 
                            : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        String mensaje = "Error de validación en los campos: " + String.join(", ", errores.keySet());
        String detalle = errores.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("; "));
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .codigo("VALIDATION_ERROR")
                .mensaje(mensaje)
                .detalle(detalle)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> manejarTipoMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        String mensaje = String.format("Parámetro '%s' con valor '%s' no es del tipo esperado '%s'",
                ex.getName(),
                ex.getValue() != null ? ex.getValue().toString() : "null",
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido");
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .codigo("TYPE_MISMATCH")
                .mensaje("Tipo de parámetro inválido")
                .detalle(mensaje)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> manejarArgumentoIlegal(
            IllegalArgumentException ex, WebRequest request) {
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .codigo("ARGUMENTO_INVALIDO")
                .mensaje(ex.getMessage() != null ? ex.getMessage() : "Argumento inválido")
                .detalle("Verifique los parámetros proporcionados")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarExcepcionGeneral(
            Exception ex, WebRequest request) {
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(Instant.now())
                .codigo("ERROR_INTERNO")
                .mensaje("Error interno del servidor")
                .detalle(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus determinarEstadoHttp(String codigoError) {
        return switch (codigoError) {
            case "CUENTA_NO_ENCONTRADA",
                 "SALDO_INSUFICIENTE",
                 "LIMITE_EXCEDIDO",
                 "VALIDATION_ERROR",
                 "ARGUMENTO_INVALIDO" -> HttpStatus.BAD_REQUEST;
            case "IDEMPOTENCIA_DUPLICADA" -> HttpStatus.CONFLICT;
            case "ERROR_INTERNO" -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    public static class ErrorResponse {
        private Instant timestamp;
        private String codigo;
        private String mensaje;
        private String detalle;
        private String path;
        private Map<String, Object> metadata;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getDetalle() {
            return detalle;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Map<String, Object> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }

        public static class ErrorResponseBuilder {
            private Instant timestamp;
            private String codigo;
            private String mensaje;
            private String detalle;
            private String path;

            public ErrorResponseBuilder timestamp(Instant timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder codigo(String codigo) {
                this.codigo = codigo;
                return this;
            }

            public ErrorResponseBuilder mensaje(String mensaje) {
                this.mensaje = mensaje;
                return this;
            }

            public ErrorResponseBuilder detalle(String detalle) {
                this.detalle = detalle;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.setTimestamp(this.timestamp);
                response.setCodigo(this.codigo);
                response.setMensaje(this.mensaje);
                response.setDetalle(this.detalle);
                response.setPath(this.path);
                return response;
            }
        }
    }
}