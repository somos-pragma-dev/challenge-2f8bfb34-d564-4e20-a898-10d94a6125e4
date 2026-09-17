package com.bank.transferapi;

import com.bank.transferapi.model.Transfer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EnableAsync
@EnableScheduling
public class TransferApiApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(TransferApiApplication.class);
    
    private final String applicationName;
    private final String version;
    private final Instant startupTime;
    
    public TransferApiApplication() {
        this.applicationName = "Transfer API";
        this.version = "1.0.0";
        this.startupTime = Instant.now();
        validateApplicationState();
    }
    
    private void validateApplicationState() {
        if (applicationName == null || applicationName.isBlank()) {
            throw new IllegalStateException("El nombre de la aplicación no puede estar vacío");
        }
        if (version == null || version.isBlank()) {
            throw new IllegalStateException("La versión de la aplicación no puede estar vacía");
        }
        logger.info("Inicializando {} versión {}", applicationName, version);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TransferApiApplication.class, args);
    }
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transfer API - Banco Digital")
                        .description("""
                                API REST para gestión de transferencias de fondos entre cuentas.
                                
                                Esta API permite procesar transferencias bancarias con soporte para:
                                - Validación de solicitudes
                                - Persistencia en base de datos H2
                                - Documentación automática con Swagger/OpenAPI
                                - Manejo de idempotencia mediante claves únicas
                                - Throughput de hasta 500 transacciones por segundo
                                """)
                        .version(version)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("dev@bank.com")
                                .url("https://bank.com"))
                        .license(new License()
                                .name("Proprietario - Banco Digital")
                                .url("https://bank.com/legal")));
    }
    
    public String getApplicationInfo() {
        long uptimeSeconds = Instant.now().getEpochSecond() - startupTime.getEpochSecond();
        return String.format("%s v%s iniciada hace %d segundos", 
                applicationName, version, uptimeSeconds);
    }
    
    public void logStartupDetails() {
        logger.info("========================================");
        logger.info(" {} iniciada", getApplicationInfo());
        logger.info(" Timestamp de inicio: {}", startupTime);
        logger.info(" Entorno: {}", System.getProperty("spring.profiles.active", "default"));
        logger.info(" Java Version: {}", System.getProperty("java.version"));
        logger.info("========================================");
    }
}