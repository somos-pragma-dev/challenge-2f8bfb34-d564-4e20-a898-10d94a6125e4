package com.bank.transferapi.config;

import com.bank.transferapi.model.Transfer;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transfer API - Banco Digital")
                        .version("1.0.0")
                        .description("""
                                API REST para gestión de transferencias de fondos entre cuentas bancarias.
                                
                                Esta API permite realizar transferencias monetarias de forma segura con soporte
                                para idempotencia, validación de saldo y límites por transacción.
                                
                                ## Características Principales
                                - Transferencias entre cuentas del mismo banco
                                - Validación de saldo antes de ejecutar
                                - Control de idempotencia mediante clave única
                                - Límites de monto por transacción
                                - Historial de transferencias por cuenta
                                
                                ## Autenticación
                                Esta API utiliza autenticación Bearer. Incluya el token en el header:
                                `Authorization: Bearer <token>`
                                
                                ## Códigos de Estado
                                - 200: Operación exitosa
                                - 201: Transferencia creada exitosamente
                                - 400: Error de validación o datos inválidos
                                - 404: Recurso no encontrado
                                - 409: Conflicto (transferencia duplicada)
                                - 500: Error interno del servidor
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@banco.com")
                                .url("https://www.banco.com/soporte"))
                        .license(new License()
                                .name("Licencia Proprietaria")
                                .url("https://www.banco.com/legal")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("""
                                        Token de autenticación JWT requerido para todas las operaciones.
                                        Obtain the token from the authentication endpoint.
                                        """)));
    }
}