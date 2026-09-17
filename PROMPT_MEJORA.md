# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/bank/transferapi/TransferApiApplication.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/TransferApiApplication.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/dto/TransferRequest.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/service/TransferService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.cuentaOrigen`: Se invoca `cuentaOrigen` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.cuentaDestino`: Se invoca `cuentaDestino` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.monto`: Se invoca `monto` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferException.getMessage`: Se invoca `getMessage` sobre `TransferException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferException.getHttpStatus`: Se invoca `getHttpStatus` sobre `TransferException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.claveIdempotencia`: Se invoca `claveIdempotencia` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/service/TransferService.java` — `TransferRepository.save`: Se invoca `save` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/service/TransferService.java` — `TransferRepository.findById`: Se invoca `findById` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/service/TransferService.java` — `TransferRepository.findAll`: Se invoca `findAll` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.getDefaultMessage`: Se invoca `getDefaultMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setTimestamp`: Se invoca `setTimestamp` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setCodigo`: Se invoca `setCodigo` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setMensaje`: Se invoca `setMensaje` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setDetalle`: Se invoca `setDetalle` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setPath`: Se invoca `setPath` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transferapi/service/TransferServiceTest.java` — `TransferRepository.save`: Se invoca `save` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/bank/transferapi/service/TransferServiceTest.java` — `TransferRepository.findById`: Se invoca `findById` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
API REST con H2 y Swagger

### Reto
- Tema: Java Spring Boot REST API
- Seniority: junior-l2
- Tipo: mixed
- Título: Desarrollo de API REST en dominio de banca
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Estructura básica de la API — objetivo: Definir y estructurar la API para manejar solicitudes de transferencia de fondos. — entregable (NO resolver): Especificación de la API y endpoints básicos implementados.
- Fase 2: Implementación de la persistencia — objetivo: Implementar la persistencia de las transferencias en la base de datos H2. — entregable (NO resolver): Base de datos H2 configurada y métodos de persistencia implementados.
- Fase 3: Documentación y validación — objetivo: Documentar la API usando Swagger y asegurar la validación de las solicitudes. — entregable (NO resolver): API documentada con Swagger y validación de solicitudes implementada.
- Fase 4: Optimización y decisiones de diseño — objetivo: Optimizar la API para manejar el throughput requerido y tomar decisiones de diseño informadas. — entregable (NO resolver): API optimizada y decisiones de diseño documentadas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>transfer-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>Transfer API</name>
    <description>API REST para transferencias de fondos entre cuentas bancarias</description>
    
    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <springdoc.version>2.5.0</springdoc.version>
        <lombok.version>1.18.30</lombok.version>
        <h2.version>2.2.224</h2.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>${h2.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc.version}</version>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/transferapi/TransferApiApplication.java ===
package com.bank.transferapi;

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

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/v1
    encoding:
      charset: UTF-8
      enabled: true
      force: true
  tomcat:
    threads:
      max: 200
      min-spare: 10
    connection-timeout: 20000
    max-connections: 8192
    accept-count: 100

spring:
  application:
    name: transfer-api
  profiles:
    active: dev
  datasource:
    url: jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=PostgreSQL
    username: sa
    password: 
    driver-class-name: org.h2.Driver
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      idle-timeout: 300000
      connection-timeout: 20000
      max-lifetime: 1200000
      pool-name: BankHikariPool
      auto-commit: true
      connection-test-query: SELECT 1
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
        jdbc:
          batch_size: 50
          fetch_size: 100
        order_inserts: true
        order_updates: true
        generate_statistics: false
    open-in-view: false
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true
  jackson:
    serialization:
      write-dates-as-timestamps: false
      indent-output: false
    deserialization:
      fail-on-unknown-properties: false
      fail-on-empty-beans: false
    default-property-inclusion: non_null
    time-zone: UTC
    date-format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'

springdoc:
  api-docs:
    path: /v3/api-docs
    enabled: true
  swagger-ui:
    path: /swagger-ui.html
    enabled: true
    operationsSorter: method
    tagsSorter: alpha
    tryItOutEnabled: true
    filter: 
    enabled: true
  show-spring-cloud-functions: false
  model-and-view-allowed: 

logging:
  level:
    root: INFO
    com.bank.transferapi: DEBUG
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.jdbc.core: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/transfer-api.log
    max-size: 10MB
    max-history: 30

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true


// === ARCHIVO: src/main/java/com/bank/transferapi/model/Transfer.java ===
package com.bank.transferapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuenta_origen", nullable = false, length = 20)
    private String cuentaOrigen;

    @Column(name = "cuenta_destino", nullable = false, length = 20)
    private String cuentaDestino;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransferEstado estado;

    @Column(name = "clave_idempotencia", unique = true, length = 64)
    private String claveIdempotencia;

    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = Instant.now();
        fechaActualizacion = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = Instant.now();
    }

    public enum TransferEstado {
        PENDIENTE("Pendiente de procesamiento"),
        PROCESANDO("En proceso de ejecución"),
        COMPLETADA("Transferencia exitosa"),
        FALLIDA("Transferencia fallida"),
        CANCELADA("Transferencia cancelada");

        private final String descripcion;

        TransferEstado(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/transferapi/dto/TransferRequest.java ===
package com.bank.transferapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Schema(description = "Solicitud de transferencia de fondos entre cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequest {

    @Schema(description = "Número de cuenta de origen (20 caracteres máximo)", 
            example = "12345678901234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La cuenta de origen es obligatoria")
    @Size(min = 10, max = 20, message = "La cuenta de origen debe tener entre 10 y 20 caracteres")
    private String cuentaOrigen;

    @Schema(description = "Número de cuenta de destino (20 caracteres máximo)", 
            example = "09876543210987654321", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La cuenta de destino es obligatoria")
    @Size(min = 10, max = 20, message = "La cuenta de destino debe tener entre 10 y 20 caracteres")
    private String cuentaDestino;

    @Schema(description = "Monto a transferir (mayor a cero, hasta 19 dígitos y 4 decimales)", 
            example = "1500.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @DecimalMax(value = "9999999999999999999.9999", message = "El monto excede el límite permitido")
    private BigDecimal monto;

    @Schema(description = "Descripción opcional de la transferencia", 
            example = "Pago de servicios", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String descripcion;

    @Schema(description = "Clave de idempotencia para evitar duplicados (64 caracteres máximo)", 
            example = "uuid-unico-por-transaccion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 64, message = "La clave de idempotencia no puede exceder 64 caracteres")
    private String claveIdempotencia;
}

// === ARCHIVO: src/main/java/com/bank/transferapi/repository/TransferRepository.java ===
package com.bank.transferapi.repository;

import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query("SELECT t FROM Transfer t WHERE t.cuentaOrigen = :cuenta OR t.cuentaDestino = :cuenta ORDER BY t.fechaCreacion DESC")
    List<Transfer> findAllByCuentaInvolucrada(@Param("cuenta") String cuenta);

    @Query("SELECT t FROM Transfer t WHERE t.fechaCreacion BETWEEN :inicio AND :fin ORDER BY t.fechaCreacion DESC")
    List<Transfer> findByFechaCreacionBetween(@Param("inicio") Instant inicio, @Param("fin") Instant fin);

    @Query("SELECT t FROM Transfer t WHERE t.estado = :estado AND t.fechaCreacion >= :desde ORDER BY t.fechaCreacion DESC")
    List<Transfer> findByEstadoAndFechaCreacionAfter(
            @Param("estado") TransferEstado estado, 
            @Param("desde") Instant desde);

    @Query("SELECT COUNT(t) FROM Transfer t WHERE t.estado = :estado")
    long countByEstado(@Param("estado") TransferEstado estado);

    @Query("SELECT t FROM Transfer t WHERE t.cuentaOrigen = :cuentaOrigen AND t.estado = :estado ORDER BY t.fechaCreacion DESC")
    List<Transfer> findByCuentaOrigenAndEstado(
            @Param("cuentaOrigen") String cuentaOrigen, 
            @Param("estado") TransferEstado estado);

    @Query(value = "SELECT * FROM transferencias WHERE cuenta_origen = :cuenta OR cuenta_destino = :cuenta ORDER BY fecha_creacion DESC LIMIT :limit", nativeQuery = true)
    List<Transfer> findRecentByCuenta(@Param("cuenta") String cuenta, @Param("limit") int limit);

    @Modifying
    @Query("UPDATE Transfer t SET t.estado = :nuevoEstado WHERE t.id = :id")
    int actualizarEstado(@Param("id") Long id, @Param("nuevoEstado") TransferEstado nuevoEstado);

    @Query("SELECT SUM(t.monto) FROM Transfer t WHERE t.cuentaOrigen = :cuenta AND t.estado = 'COMPLETADA' AND t.fechaCreacion >= :desde")
    java.math.BigDecimal sumMontoEnviadoDesde(@Param("cuenta") String cuenta, @Param("desde") Instant desde);

    @Query("SELECT SUM(t.monto) FROM Transfer t WHERE t.cuentaDestino = :cuenta AND t.estado = 'COMPLETADA' AND t.fechaCreacion >= :desde")
    java.math.BigDecimal sumMontoRecibidoDesde(@Param("cuenta") String cuenta, @Param("desde") Instant desde);
}

// === ARCHIVO: src/main/java/com/bank/transferapi/dto/TransferResponse.java ===
package com.bank.transferapi.dto;

import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.Instant;

@Schema(description = "Respuesta de transferencia con detalles de confirmación y estado")
public record TransferResponse(
    @Schema(description = "Identificador único de la transferencia", example = "1")
    Long id,
    
    @Schema(description = "Número de cuenta de origen", example = "1234567890")
    String cuentaOrigen,
    
    @Schema(description = "Número de cuenta de destino", example = "0987654321")
    String cuentaDestino,
    
    @Schema(description = "Monto transferido", example = "1000.00")
    BigDecimal monto,
    
    @Schema(description = "Estado actual de la transferencia", example = "COMPLETADA")
    TransferEstado estado,
    
    @Schema(description = "Clave de idempotencia utilizada", example = "uuid-key-123")
    String claveIdempotencia,
    
    @Schema(description = "Fecha y hora de creación de la transferencia")
    Instant fechaCreacion,
    
    @Schema(description = "Fecha y hora de última actualización")
    Instant fechaActualizacion,
    
    @Schema(description = "Descripción opcional de la transferencia", example = "Pago de servicios")
    String descripcion,
    
    @Schema(description = "Mensaje de estado para el cliente", example = "Transferencia procesada exitosamente")
    String mensaje,
    
    @Schema(description = "Código de resultado de la operación", example = "SUCCESS")
    String codigoResultado
) {
    public static TransferResponse fromTransfer(Transfer transfer) {
        return new TransferResponse(
            transfer.getId(),
            transfer.getCuentaOrigen(),
            transfer.getCuentaDestino(),
            transfer.getMonto(),
            transfer.getEstado(),
            transfer.getClaveIdempotencia(),
            transfer.getFechaCreacion(),
            transfer.getFechaActualizacion(),
            transfer.getDescripcion(),
            generarMensaje(transfer.getEstado()),
            generarCodigo(transfer.getEstado())
        );
    }
    
    public static TransferResponse success(Transfer transfer) {
        return fromTransfer(transfer);
    }
    
    public static TransferResponse pending(Transfer transfer) {
        return new TransferResponse(
            transfer.getId(),
            transfer.getCuentaOrigen(),
            transfer.getCuentaDestino(),
            transfer.getMonto(),
            transfer.getEstado(),
            transfer.getClaveIdempotencia(),
            transfer.getFechaCreacion(),
            transfer.getFechaActualizacion(),
            transfer.getDescripcion(),
            "Transferencia en proceso de validación",
            "PENDING"
        );
    }
    
    public static TransferResponse error(String mensaje, String codigo) {
        return new TransferResponse(
            null, null, null, null, null, null, 
            Instant.now(), Instant.now(), null,
            mensaje, codigo
        );
    }
    
    private static String generarMensaje(TransferEstado estado) {
        return switch (estado) {
            case COMPLETADA -> "Transferencia procesada exitosamente";
            case PENDIENTE -> "Transferencia en proceso de validación";
            case FALLIDA -> "La transferencia no pudo ser procesada";
            case CANCELADA -> "Transferencia cancelada por el usuario";
            case RECHAZADA -> "Transferencia rechazada por validación";
        };
    }
    
    private static String generarCodigo(TransferEstado estado) {
        return switch (estado) {
            case COMPLETADA -> "SUCCESS";
            case PENDIENTE -> "PENDING";
            case FALLIDA -> "ERROR";
            case CANCELADA -> "CANCELLED";
            case RECHAZADA -> "REJECTED";
        };
    }
}

// === ARCHIVO: src/main/java/com/bank/transferapi/controller/TransferController.java ===
package com.bank.transferapi.controller;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.dto.TransferResponse;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transferencias")
@Tag(name = "Transferencias", description = "API para gestión de transferencias de fondos entre cuentas bancarias")
public class TransferController {
    
    private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
    private static final String IDEMPOTENCY_HEADER = "X-Idempotency-Key";
    
    private final TransferService transferService;
    
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transferencia", 
               description = "Crea una transferencia de fondos entre dos cuentas. " +
                             "Soporta idempotencia mediante el header X-Idempotency-Key.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transferencia creada exitosamente",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Transferencia duplicada (idempotencia)"),
        @ApiResponse(responseCode = "422", description = "Error de validación de negocio"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<TransferResponse> crearTransferencia(
            @Valid @RequestBody TransferRequest request,
            @RequestHeader(value = IDEMPOTENCY_HEADER, required = false) String idempotencyKey) {
        
        String claveIdempotencia = resolverClaveIdempotencia(idempotencyKey, request);
        logger.info("Iniciando transferencia: origen={}, destino={}, monto={}, idempotencyKey={}",
                    request.cuentaOrigen(), request.cuentaDestino(), request.monto(), claveIdempotencia);
        
        try {
            Transfer transferencia = transferService.crearTransferencia(request, claveIdempotencia);
            TransferResponse response = TransferResponse.fromTransfer(transferencia);
            logger.info("Transferencia creada exitosamente con ID: {}", transferencia.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (TransferException e) {
            logger.error("Error de negocio al crear transferencia: {}", e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error inesperado al crear transferencia: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error al procesar la transferencia", e);
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener transferencia por ID", 
               description = "Recupera los detalles de una transferencia específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transferencia encontrada",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "404", description = "Transferencia no encontrada")
    })
    public ResponseEntity<TransferResponse> obtenerTransferencia(
            @Parameter(description = "ID de la transferencia") @PathVariable Long id) {
        logger.debug("Consultando transferencia con ID: {}", id);
        
        Transfer transferencia = transferService.obtenerTransferenciaPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Transferencia no encontrada con ID: " + id));
        
        return ResponseEntity.ok(TransferResponse.fromTransfer(transferencia));
    }
    
    @GetMapping
    @Operation(summary = "Listar transferencias", 
               description = "Lista todas las transferencias con filtros opcionales")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transferencias")
    })
    public ResponseEntity<List<TransferResponse>> listarTransferencias(
            @Parameter(description = "Filtrar por cuenta de origen") 
            @RequestParam(required = false) String cuentaOrigen,
            
            @Parameter(description = "Filtrar por cuenta de destino") 
            @RequestParam(required = false) String cuentaDestino,
            
            @Parameter(description = "Filtrar por estado") 
            @RequestParam(required = false) TransferEstado estado) {
        
        logger.debug("Listando transferencias: origen={}, destino={}, estado={}", 
                     cuentaOrigen, cuentaDestino, estado);
        
        List<Transfer> transferencias = transferService.listarTransferencias(
            cuentaOrigen, cuentaDestino, estado);
        
        List<TransferResponse> responses = transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .toList();
        
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/cuenta/{numeroCuenta}")
    @Operation(summary = "Obtener transferencias por cuenta", 
               description = "Recupera todas las transferencias asociadas a una cuenta (como origen o destino)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transferencias de la cuenta")
    })
    public ResponseEntity<List<TransferResponse>> obtenerTransferenciasPorCuenta(
            @Parameter(description = "Número de cuenta") @PathVariable String numeroCuenta) {
        
        logger.debug("Consultando transferencias para cuenta: {}", numeroCuenta);
        List<Transfer> transferencias = transferService.obtenerTransferenciasPorCuenta(numeroCuenta);
        
        List<TransferResponse> responses = transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .toList();
        
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar una transferencia", 
               description = "Cancela una transferencia existente (solo si está en estado PENDIENTE)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transferencia cancelada",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "400", description = "No se puede cancelar la transferencia"),
        @ApiResponse(responseCode = "404", description = "Transferencia no encontrada")
    })
    public ResponseEntity<TransferResponse> cancelarTransferencia(
            @Parameter(description = "ID de la transferencia a cancelar") @PathVariable Long id) {
        
        logger.info("Cancelando transferencia ID: {}", id);
        
        try {
            Transfer transferencia = transferService.cancelarTransferencia(id);
            return ResponseEntity.ok(TransferResponse.fromTransfer(transferencia));
        } catch (TransferException e) {
            logger.error("Error al cancelar transferencia: {}", e.getMessage());
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage(), e);
        }
    }
    
    @GetMapping("/{id}/estado")
    @Operation(summary = "Consultar estado de transferencia", 
               description = "Consulta el estado actual de una transferencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado de la transferencia"),
        @ApiResponse(responseCode = "404", description = "Transferencia no encontrada")
    })
    public ResponseEntity<TransferResponse> consultarEstado(
            @Parameter(description = "ID de la transferencia") @PathVariable Long id) {
        
        logger.debug("Consultando estado de transferencia ID: {}", id);
        
        Transfer transferencia = transferService.obtenerTransferenciaPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Transferencia no encontrada con ID: " + id));
        
        return ResponseEntity.ok(TransferResponse.fromTransfer(transferencia));
    }
    
    @GetMapping("/ idempotencia/{clave}")
    @Operation(summary = "Verificar clave de idempotencia", 
               description = "Verifica si existe una transferencia con la clave de idempotencia proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resultado de la verificación",
                     content = @Content(schema = @Schema(implementation = TransferResponse.class))),
        @ApiResponse(responseCode = "404", description = "No existe transferencia con esa clave")
    })
    public ResponseEntity<TransferResponse> verificarIdempotencia(
            @Parameter(description = "Clave de idempotencia") @PathVariable String clave) {
        
        logger.debug("Verificando idempotencia para clave: {}", clave);
        
        return transferService.obtenerPorClaveIdempotencia(clave)
            .map(t -> ResponseEntity.ok(TransferResponse.fromTransfer(t)))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "No existe transferencia con clave de idempotencia: " + clave));
    }
    
    private String resolverClaveIdempotencia(String headerKey, TransferRequest request) {
        if (headerKey != null && !headerKey.isBlank()) {
            return headerKey;
        }
        if (request.claveIdempotencia() != null && !request.claveIdempotencia().isBlank()) {
            return request.claveIdempotencia();
        }
        String generatedKey = UUID.randomUUID().toString();
        logger.debug("Generated idempotency key: {}", generatedKey);
        return generatedKey;
    }
}

// === ARCHIVO: src/main/java/com/bank/transferapi/service/TransferService.java ===
package com.bank.transferapi.service;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private static final int MAX_CUENTA_LENGTH = 20;
    private static final int MAX_DESCRIPCION_LENGTH = 255;
    private static final int MAX_IDEMPOTENCY_KEY_LENGTH = 64;
    private static final BigDecimal MONTO_MINIMO = BigDecimal.valueOf(0.01);
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("9999999999999999999");
    
    private final TransferRepository transferRepository;
    
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Transactional
    public Transfer crearTransferencia(TransferRequest request, String claveIdempotencia) {
        logger.info("Iniciando proceso de creación de transferencia");
        
        validarIdempotencia(claveIdempotencia);
        validarRequest(request);
        validarCuentas(request.cuentaOrigen(), request.cuentaDestino());
        validarMonto(request.monto());
        
        Transfer transferencia = new Transfer();
        transferencia.setCuentaOrigen(request.cuentaOrigen());
        transferencia.setCuentaDestino(request.cuentaDestino());
        transferencia.setMonto(request.monto());
        transferencia.setDescripcion(request.descripcion());
        transferencia.setClaveIdempotencia(claveIdempotencia);
        transferencia.setEstado(TransferEstado.PENDIENTE);
        transferencia.onCreate();
        
        Transfer transferenciaGuardada = transferRepository.save(transferencia);
        logger.info("Transferencia guardada con estado PENDIENTE, ID: {}", transferenciaGuardada.getId());
        
        boolean completada = procesarTransferencia(transferenciaGuardada);
        
        if (completada) {
            transferenciaGuardada.setEstado(TransferEstado.COMPLETADA);
        } else {
            transferenciaGuardada.setEstado(TransferEstado.FALLIDA);
        }
        transferenciaGuardada.onUpdate();
        
        Transfer resultado = transferRepository.save(transferenciaGuardada);
        logger.info("Transferencia procesada con estado final: {}", resultado.getEstado());
        
        return resultado;
    }
    
    private boolean procesarTransferencia(Transfer transferencia) {
        logger.debug("Procesando transferencia ID: {}, monto: {}", 
                     transferencia.getId(), transferencia.getMonto());
        
        boolean origenValido = validarSaldoCuenta(transferencia.getCuentaOrigen(), transferencia.getMonto());
        if (!origenValido) {
            logger.warn("Saldo insuficiente en cuenta origen: {}", transferencia.getCuentaOrigen());
            return false;
        }
        
        boolean destinoValido = validarCuentaActiva(transferencia.getCuentaDestino());
        if (!destinoValido) {
            logger.warn("Cuenta destino inactiva: {}", transferencia.getCuentaDestino());
            return false;
        }
        
        logger.info("Validaciones de negocio pasadas para transferencia {}", transferencia.getId());
        return true;
    }
    
    private boolean validarSaldoCuenta(String cuenta, BigDecimal monto) {
        logger.debug("Validando saldo para cuenta: {}, monto requerido: {}", cuenta, monto);
        BigDecimal saldoSimulado = new BigDecimal("50000.00");
        return saldoSimulado.compareTo(monto) >= 0;
    }
    
    private boolean validarCuentaActiva(String cuenta) {
        logger.debug("Validando estado de cuenta: {}", cuenta);
        return cuenta != null && !cuenta.isBlank() && cuenta.length() >= 10;
    }
    
    @Transactional(readOnly = true)
    public Optional<Transfer> obtenerTransferenciaPorId(Long id) {
        logger.debug("Buscando transferencia por ID: {}", id);
        return transferRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Transfer> obtenerPorClaveIdempotencia(String claveIdempotencia) {
        logger.debug("Buscando transferencia por clave de idempotencia: {}", claveIdempotencia);
        return transferRepository.findByClaveIdempotencia(claveIdempotencia);
    }
    
    @Transactional(readOnly = true)
    public List<Transfer> listarTransferencias(String cuentaOrigen, String cuentaDestino, TransferEstado estado) {
        logger.debug("Listando transferencias con filtros: origen={}, destino={}, estado={}", 
                     cuentaOrigen, cuentaDestino, estado);
        
        if (cuentaOrigen != null && !cuentaOrigen.isBlank()) {
            return transferRepository.findByCuentaOrigen(cuentaOrigen);
        }
        if (cuentaDestino != null && !cuentaDestino.isBlank()) {
            return transferRepository.findByCuentaDestino(cuentaDestino);
        }
        if (estado != null) {
            return transferRepository.findByEstado(estado);
        }
        
        return transferRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Transfer> obtenerTransferenciasPorCuenta(String cuenta) {
        logger.debug("Obteniendo transferencias para cuenta: {}", cuenta);
        return transferRepository.findAllByCuentaInvolucrada(cuenta);
    }
    
    @Transactional
    public Transfer cancelarTransferencia(Long id) {
        logger.info("Intentando cancelar transferencia ID: {}", id);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException(
                "Transferencia no encontrada con ID: " + id,
                HttpStatus.NOT_FOUND));
        
        if (transferencia.getEstado() != TransferEstado.PENDIENTE) {
            logger.error("No se puede cancelar transferencia en estado: {}", transferencia.getEstado());
            throw new TransferException(
                "Solo se pueden cancelar transferencias en estado PENDIENTE. Estado actual: " + 
                transferencia.getEstado(),
                HttpStatus.BAD_REQUEST);
        }
        
        transferencia.setEstado(TransferEstado.CANCELADA);
        transferencia.onUpdate();
        
        Transfer cancelada = transferRepository.save(transferencia);
        logger.info("Transferencia {} cancelada exitosamente", id);
        
        return cancelada;
    }
    
    @Transactional(readOnly = true)
    public long countByEstado(TransferEstado estado) {
        return transferRepository.countByEstado(estado);
    }
    
    @Transactional(readOnly = true)
    public BigDecimal sumMontoEnviadoDesde(String cuenta, Instant desde) {
        BigDecimal total = transferRepository.sumMontoEnviadoDesde(cuenta, desde);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    @Transactional(readOnly = true)
    public BigDecimal sumMontoRecibidoDesde(String cuenta, Instant desde) {
        BigDecimal total = transferRepository.sumMontoRecibidoDesde(cuenta, desde);
        return total != null ? total : BigDecimal.ZERO;
    }
    
    private void validarIdempotencia(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            logger.debug("No se proporcionó clave de idempotencia, se generará una");
            return;
        }
        
        if (claveIdempotencia.length() > MAX_IDEMPOTENCY_KEY_LENGTH) {
            throw new TransferException(
                "La clave de idempotencia no puede exceder " + MAX_IDEMPOTENCY_KEY_LENGTH + " caracteres",
                HttpStatus.BAD_REQUEST);
        }
        
        if (transferRepository.existsByClaveIdempotencia(claveIdempotencia)) {
            logger.warn("Clave de idempotencia duplicada: {}", claveIdempotencia);
            throw new TransferException(
                "Ya existe una transferencia con esta clave de idempotencia",
                HttpStatus.CONFLICT);
        }
    }
    
    private void validarRequest(TransferRequest request) {
        if (request == null) {
            throw new TransferException("La solicitud de transferencia no puede ser nula",
                HttpStatus.BAD_REQUEST);
        }
        
        if (request.cuentaOrigen() == null || request.cuentaOrigen().isBlank()) {
            throw new TransferException("La cuenta de origen es obligatoria",
                HttpStatus.BAD_REQUEST);
        }
        
        if (request.cuentaDestino() == null || request.cuentaDestino().isBlank()) {
            throw new TransferException("La cuenta de destino es obligatoria",
                HttpStatus.BAD_REQUEST);
        }
        
        if (request.monto() == null) {
            throw new TransferException("El monto es obligatorio",
                HttpStatus.BAD_REQUEST);
        }
    }
    
    private void validarCuentas(String cuentaOrigen, String cuentaDestino) {
        if (cuentaOrigen.length() > MAX_CUENTA_LENGTH) {
            throw new TransferException(
                "La cuenta de origen no puede exceder " + MAX_CUENTA_LENGTH + " caracteres",
                HttpStatus.BAD_REQUEST);
        }
        
        if (cuentaDestino.length() > MAX_CUENTA_LENGTH) {
            throw new TransferException(
                "La cuenta de destino no puede exceder " + MAX_CUENTA_LENGTH + " caracteres",
                HttpStatus.BAD_REQUEST);
        }
        
        if (cuentaOrigen.equals(cuentaDestino)) {
            throw new TransferException(
                "La cuenta de origen y destino no pueden ser iguales",
                HttpStatus.BAD_REQUEST);
        }
    }
    
    private void validarMonto(BigDecimal monto) {
        if (monto.compareTo(MONTO_MINIMO) <= 0) {
            throw new TransferException(
                "El monto debe ser mayor a " + MONTO_MINIMO,
                HttpStatus.BAD_REQUEST);
        }
        
        if (monto.compareTo(MONTO_MAXIMO) > 0) {
            throw new TransferException(
                "El monto no puede exceder " + MONTO_MAXIMO,
                HttpStatus.BAD_REQUEST);
        }
        
        int escala = monto.scale();
        if (escala > 4) {
            throw new TransferException(
                "El monto no puede tener más de 4 decimales",
                HttpStatus.BAD_REQUEST);
        }
    }
}

// === ARCHIVO: src/main/java/com/bank/transferapi/exception/TransferException.java ===
package com.bank.transferapi.exception;

public class TransferException extends RuntimeException {
    private final String codigoError;
    private final String detalleTecnico;

    public TransferException(String mensaje) {
        super(mensaje);
        this.codigoError = "TRANSFER_ERROR";
        this.detalleTecnico = null;
    }

    public TransferException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.detalleTecnico = null;
    }

    public TransferException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "TRANSFER_ERROR";
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

    public String getDetalleTecnico() {
        return detalleTecnico;
    }

    public static TransferException cuentaNoEncontrada(String numeroCuenta) {
        return new TransferException(
            "Cuenta no encontrada: " + numeroCuenta,
            "CUENTA_NO_ENCONTRADA",
            "La cuenta " + numeroCuenta + " no existe en el sistema",
            null
        );
    }

    public static TransferException saldoInsuficiente(String numeroCuenta, String montoSolicitado, String saldoActual) {
        return new TransferException(
            "Saldo insuficiente en cuenta: " + numeroCuenta,
            "SALDO_INSUFICIENTE",
            "Monto solicitado: " + montoSolicitado + ", saldo disponible: " + saldoActual,
            null
        );
    }

    public static TransferException transferenciaDuplicada(String claveIdempotencia) {
        return new TransferException(
            "Transferencia duplicada detectada",
            "IDEMPOTENCIA_DUPLICADA",
            "Ya existe una transferencia con la clave: " + claveIdempotencia,
            null
        );
    }

    public static TransferException limiteExcedido(String monto, String limite) {
        return new TransferException(
            "Monto excede el límite permitido",
            "LIMITE_EXCEDIDO",
            "Monto: " + monto + ", límite: " + limite,
            null
        );
    }

    public static TransferException errorInterno(String operacion, Throwable causa) {
        return new TransferException(
            "Error interno al procesar la transferencia",
            "ERROR_INTERNO",
            "Operación: " + operacion,
            causa
        );
    }
}

// === ARCHIVO: src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/config/OpenApiConfig.java ===
package com.bank.transferapi.config;

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

// === ARCHIVO: src/test/java/com/bank/transferapi/controller/TransferControllerTest.java ===
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

// === ARCHIVO: src/test/java/com/bank/transferapi/service/TransferServiceTest.java ===
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

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>transfer-api</artifactId>
    <version>1.0.0</version>
    <name>Transfer API</name>
    <description>API REST para gestión de transferencias bancarias</description>
    
    <properties>
        <java.version>21</java.version>
        <springdoc.version>2.5.0</springdoc.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.3.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>3.3.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <version>3.3.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>3.3.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/transferapi/TransferApiApplication.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/config/OpenApiConfig.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/dto/TransferRequest.java ===
package com.bank.transferapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Schema(description = "Solicitud de transferencia de fondos entre cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferRequest {

    @Schema(description = "Número de cuenta de origen (20 caracteres máximo)", 
            example = "12345678901234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La cuenta de origen es obligatoria")
    @Size(min = 10, max = 20, message = "La cuenta de origen debe tener entre 10 y 20 caracteres")
    private String cuentaOrigen;

    @Schema(description = "Número de cuenta de destino (20 caracteres máximo)", 
            example = "09876543210987654321", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La cuenta de destino es obligatoria")
    @Size(min = 10, max = 20, message = "La cuenta de destino debe tener entre 10 y 20 caracteres")
    private String cuentaDestino;

    @Schema(description = "Monto a transferir (mayor a cero, hasta 19 dígitos y 4 decimales)", 
            example = "1500.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @DecimalMax(value = "9999999999999999999.9999", message = "El monto excede el límite permitido")
    private BigDecimal monto;

    @Schema(description = "Descripción opcional de la transferencia", 
            example = "Pago de servicios", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String descripcion;

    @Schema(description = "Clave de idempotencia para evitar duplicados (64 caracteres máximo)", 
            example = "uuid-unico-por-transaccion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 64, message = "La clave de idempotencia no puede exceder 64 caracteres")
    private String claveIdempotencia;
}

// === ARCHIVO: src/main/java/com/bank/transferapi/model/Transfer.java ===
package com.bank.transferapi.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Transfer {
    private Long id;
    private String cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
    private TransferEstado estado;
    private String claveIdempotencia;
    private Instant fechaCreacion;
    private Instant fechaActualizacion;
    private String descripcion;
    
    public Transfer() {
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
    
    protected void onCreate() {
        this.fechaCreacion = Instant.now();
        this.fechaActualizacion = Instant.now();
    }
    
    protected void onUpdate() {
        this.fechaActualizacion = Instant.now();
    }
    
    public enum TransferEstado {
        PENDIENTE,
        COMPLETADA,
        FALLIDA,
        CANCELADA,
        RECHAZADA
    }
}

// === ARCHIVO: src/main/java/com/bank/transferapi/dto/TransferRequest.java ===
package com.bank.transferapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Schema(description = "Solicitud para crear una transferencia entre cuentas")
public record TransferRequest(
    @Schema(description = "Número de cuenta de origen (20 caracteres máximo)", example = "1234567890")
    @NotBlank(message = "La cuenta de origen es obligatoria")
    @Size(max = 20, message = "La cuenta de origen no puede exceder 20 caracteres")
    String cuentaOrigen,
    
    @Schema(description = "Número de cuenta de destino (20 caracteres máximo)", example = "0987654321")
    @NotBlank(message = "La cuenta de destino es obligatoria")
    @Size(max = 20, message = "La cuenta de destino no puede exceder 20 caracteres")
    String cuentaDestino,
    
    @Schema(description = "Monto a transferir (mayor a cero, hasta 19 dígitos y 4 decimales)", example = "1000.00")
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    BigDecimal monto,
    
    @Schema(description = "Descripción opcional de la transferencia", example = "Pago de servicios")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,
    
    @Schema(description = "Clave de idempotencia para evitar duplicados (64 caracteres máximo)", example = "uuid-key-123")
    @Size(max = 64, message = "La clave de idempotencia no puede exceder 64 caracteres")
    String claveIdempotencia
) {}

// === ARCHIVO: src/main/java/com/bank/transferapi/repository/TransferRepository.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/exception/TransferException.java ===
package com.bank.transferapi.exception;

import org.springframework.http.HttpStatus;

public class TransferException extends RuntimeException {
    private final String codigoError;
    private final String detalleTecnico;
    private final HttpStatus httpStatus;
    
    public TransferException(String mensaje) {
        super(mensaje);
        this.codigoError = "GENERIC_ERROR";
        this.detalleTecnico = null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    
    public TransferException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.detalleTecnico = null;
        this.httpStatus = mapearCodigoError(codigoError);
    }
    
    public TransferException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "GENERIC_ERROR";
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    
    public TransferException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
        this.httpStatus = mapearCodigoError(codigoError);
    }
    
    public TransferException(String mensaje, String codigoError, String detalleTecnico, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = detalleTecnico;
        this.httpStatus = mapearCodigoError(codigoError);
    }
    
    public String getCodigoError() {
        return codigoError;
    }
    
    public String getDetalleTecnico() {
        return detalleTecnico;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    
    private static HttpStatus mapearCodigoError(String codigoError) {
        if (codigoError == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return switch (codigoError) {
            case "CUENTA_NO_ENCONTRADA" -> HttpStatus.NOT_FOUND;
            case "SALDO_INSUFICIENTE" -> HttpStatus.UNPROCESSABLE_ENTITY;
            case "IDEMPOTENCY_CONFLICT" -> HttpStatus.CONFLICT;
            case "LIMITE_EXCEDIDO" -> HttpStatus.UNPROCESSABLE_ENTITY;
            case "VALIDATION_ERROR" -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
    
    public static TransferException cuentaNoEncontrada(String numeroCuenta) {
        return new TransferException(
            "Cuenta no encontrada: " + numeroCuenta,
            "CUENTA_NO_ENCONTRADA"
        );
    }
    
    public static TransferException saldoInsuficiente(String numeroCuenta, String montoSolicitado, String saldoActual) {
        String mensaje = String.format(
            "Saldo insuficiente en cuenta %s. Solicitado: %s, Actual: %s",
            numeroCuenta, montoSolicitado, saldoActual
        );
        return new TransferException(mensaje, "SALDO_INSUFICIENTE");
    }
    
    public static TransferException transferenciaDuplicada(String claveIdempotencia) {
        return new TransferException(
            "Ya existe una transferencia con la clave de idempotencia: " + claveIdempotencia,
            "IDEMPOTENCY_CONFLICT"
        );
    }
    
    public static TransferException limiteExcedido(String monto, String limite) {
        String mensaje = String.format(
            "El monto %s excede el límite permitido de %s",
            monto, limite
        );
        return new TransferException(mensaje, "LIMITE_EXCEDIDO");
    }
    
    public static TransferException errorInterno(String operacion, Throwable causa) {
        return new TransferException(
            "Error interno al " + operacion + ": " + causa.getMessage(),
            "INTERNAL_ERROR",
            causa.getMessage(),
            causa
        );
    }
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>transfer-api</artifactId>
    <version>1.0.0</version>
    <name>transfer-api</name>
    <description>API de Transferencias Bancarias</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.bank</groupId>
    <artifactId>transfer-api</artifactId>
    <version>1.0.0</version>
    <name>transfer-api</name>
    <description>API de Transferencias Bancarias</description>
    
    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/bank/transferapi/dto/TransferRequest.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/dto/TransferResponse.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/model/Transfer.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/exception/TransferException.java ===
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

// === ARCHIVO: src/main/java/com/bank/transferapi/service/TransferService.java ===
package com.bank.transferapi.service;

import com.bank.transferapi.dto.TransferRequest;
import com.bank.transferapi.dto.TransferResponse;
import com.bank.transferapi.exception.TransferException;
import com.bank.transferapi.model.Transfer;
import com.bank.transferapi.model.Transfer.TransferEstado;
import com.bank.transferapi.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferService {
    
    private static final Logger logger = LoggerFactory.getLogger(TransferService.class);
    private static final int MAX_CUENTA_LENGTH = 20;
    private static final int MAX_DESCRIPCION_LENGTH = 255;
    private static final int MAX_IDEMPOTENCY_KEY_LENGTH = 64;
    private static final BigDecimal MONTO_MINIMO = BigDecimal.valueOf(0.01);
    private static final BigDecimal MONTO_MAXIMO = new BigDecimal("9999999999999.9999");
    
    private final TransferRepository transferRepository;
    
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }
    
    @Transactional
    public TransferResponse crearTransferencia(TransferRequest request, String claveIdempotencia) {
        logger.info("Iniciando transferencia: cuentaOrigen={}, cuentaDestino={}, monto={}",
            request.getCuentaOrigen(), request.getCuentaDestino(), request.getMonto());
        
        validarIdempotencia(claveIdempotencia);
        validarRequest(request);
        validarCuentas(request.getCuentaOrigen(), request.getCuentaDestino());
        validarMonto(request.getMonto());
        
        Transfer transferencia = new Transfer();
        transferencia.setCuentaOrigen(request.getCuentaOrigen());
        transferencia.setCuentaDestino(request.getCuentaDestino());
        transferencia.setMonto(request.getMonto());
        transferencia.setDescripcion(request.getDescripcion());
        transferencia.setClaveIdempotencia(claveIdempotencia);
        transferencia.setEstado(TransferEstado.PENDIENTE);
        
        Transfer transferGuardada = transferRepository.save(transferencia);
        logger.info("Transferencia creada exitosamente con ID: {}", transferGuardada.getId());
        
        boolean procesada = procesarTransferencia(transferGuardada);
        if (!procesada) {
            transferGuardada.setEstado(TransferEstado.FALLIDA);
            transferGuardada = transferRepository.save(transferGuardada);
        }
        
        return TransferResponse.fromTransfer(transferGuardada);
    }
    
    private boolean procesarTransferencia(Transfer transferencia) {
        logger.info("Procesando transferencia ID: {}", transferencia.getId());
        
        if (!validarSaldoCuenta(transferencia.getCuentaOrigen(), transferencia.getMonto())) {
            logger.warn("Saldo insuficiente para transferencia ID: {}", transferencia.getId());
            return false;
        }
        
        if (!validarCuentaActiva(transferencia.getCuentaOrigen()) || 
            !validarCuentaActiva(transferencia.getCuentaDestino())) {
            logger.warn("Una o ambas cuentas no están activas para transferencia ID: {}", 
                transferencia.getId());
            return false;
        }
        
        transferencia.setEstado(TransferEstado.COMPLETADA);
        transferRepository.save(transferencia);
        logger.info("Transferencia ID: {} completada exitosamente", transferencia.getId());
        return true;
    }
    
    private boolean validarSaldoCuenta(String cuenta, BigDecimal monto) {
        return true;
    }
    
    private boolean validarCuentaActiva(String cuenta) {
        return true;
    }
    
    @Transactional(readOnly = true)
    public TransferResponse obtenerTransferencia(Long id) {
        logger.info("Obteniendo transferencia con ID: {}", id);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException("Transferencia no encontrada", "NOT_FOUND"));
        
        return TransferResponse.fromTransfer(transferencia);
    }
    
    @Transactional(readOnly = true)
    public Optional<Transfer> obtenerTransferenciaPorId(Long id) {
        return transferRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Transfer> obtenerPorClaveIdempotencia(String claveIdempotencia) {
        return transferRepository.findByClaveIdempotencia(claveIdempotencia);
    }
    
    @Transactional(readOnly = true)
    public List<TransferResponse> listarTransferencias(String cuentaOrigen, String cuentaDestino, TransferEstado estado) {
        logger.info("Listando transferencias - cuentaOrigen: {}, cuentaDestino: {}, estado: {}",
            cuentaOrigen, cuentaDestino, estado);
        
        List<Transfer> transferencias;
        
        if (cuentaOrigen != null && !cuentaOrigen.isEmpty()) {
            transferencias = transferRepository.findByCuentaOrigen(cuentaOrigen);
        } else if (cuentaDestino != null && !cuentaDestino.isEmpty()) {
            transferencias = transferRepository.findByCuentaDestino(cuentaDestino);
        } else if (estado != null) {
            transferencias = transferRepository.findByEstado(estado);
        } else {
            transferencias = transferRepository.findAll();
        }
        
        return transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TransferResponse> listarTransferenciasPorCuenta(String cuenta) {
        logger.info("Listando transferencias para cuenta: {}", cuenta);
        
        List<Transfer> transferencias = transferRepository.findAllByCuentaInvolucrada(cuenta);
        
        return transferencias.stream()
            .map(TransferResponse::fromTransfer)
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<Transfer> obtenerTransferenciasPorCuenta(String cuenta) {
        return transferRepository.findAllByCuentaInvolucrada(cuenta);
    }
    
    @Transactional
    public TransferResponse actualizarEstado(Long id, TransferEstado nuevoEstado) {
        logger.info("Actualizando estado de transferencia ID: {} a {}", id, nuevoEstado);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException("Transferencia no encontrada", "NOT_FOUND"));
        
        transferencia.setEstado(nuevoEstado);
        Transfer actualizada = transferRepository.save(transferencia);
        
        logger.info("Transferencia ID: {} actualizada a estado: {}", id, nuevoEstado);
        return TransferResponse.fromTransfer(actualizada);
    }
    
    @Transactional
    public Transfer cancelarTransferencia(Long id) {
        logger.info("Cancelando transferencia ID: {}", id);
        
        Transfer transferencia = transferRepository.findById(id)
            .orElseThrow(() -> new TransferException("Transferencia no encontrada", "NOT_FOUND"));
        
        if (transferencia.getEstado() == TransferEstado.COMPLETADA) {
            throw new TransferException("No se puede cancelar una transferencia completada", 
                "CANCELACION_INVALIDA");
        }
        
        transferencia.setEstado(TransferEstado.CANCELADA);
        return transferRepository.save(transferencia);
    }
    
    @Transactional(readOnly = true)
    public long countByEstado(TransferEstado estado) {
        return transferRepository.countByEstado(estado);
    }
    
    @Transactional(readOnly = true)
    public BigDecimal sumMontoEnviadoDesde(String cuenta, Instant desde) {
        return transferRepository.sumMontoEnviadoDesde(cuenta, desde != null ? desde : Instant.EPOCH);
    }
    
    @Transactional(readOnly = true)
    public BigDecimal sumMontoRecibidoDesde(String cuenta, Instant desde) {
        return transferRepository.sumMontoRecibidoDesde(cuenta, desde != null ? desde : Instant.EPOCH);
    }
    
    private void validarIdempotencia(String claveIdempotencia) {
        if (claveIdempotencia != null && !claveIdempotencia.isEmpty()) {
            if (claveIdempotencia.length() > MAX_IDEMPOTENCY_KEY_LENGTH) {
                throw new TransferException(
                    "La clave de idempotencia excede el límite de " + MAX_IDEMPOTENCY_KEY_LENGTH + " caracteres",
                    "LIMITE_EXCEDIDO");
            }
            if (transferRepository.existsByClaveIdempotencia(claveIdempotencia)) {
                throw TransferException.transferenciaDuplicada(claveIdempotencia);
            }
        }
    }
    
    private void validarRequest(TransferRequest request) {
        if (request == null) {
            throw new TransferException("La solicitud no puede ser nula", "VALIDATION_ERROR");
        }
    }
    
    private void validarCuentas(String cuentaOrigen, String cuentaDestino) {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new TransferException("La cuenta de origen es obligatoria", "VALIDATION_ERROR");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new TransferException("La cuenta de destino es obligatoria", "VALIDATION_ERROR");
        }
        if (cuentaOrigen.length() > MAX_CUENTA_LENGTH) {
            throw new TransferException(
                "La cuenta de origen excede el límite de " + MAX_CUENTA_LENGTH + " caracteres",
                "LIMITE_EXCEDIDO");
        }
        if (cuentaDestino.length() > MAX_CUENTA_LENGTH) {
            throw new TransferException(
                "La cuenta de destino excede el límite de " + MAX_CUENTA_LENGTH + " caracteres",
                "LIMITE_EXCEDIDO");
        }
        if (cuentaOrigen.equals(cuentaDestino)) {
            throw new TransferException(
                "La cuenta de origen y destino no pueden ser la misma",
                "VALIDATION_ERROR");
        }
    }
    
    private void validarMonto(BigDecimal monto) {
        if (monto == null) {
            throw new TransferException("El monto es obligatorio", "VALIDATION_ERROR");
        }
        if (monto.compareTo(MONTO_MINIMO) <= 0) {
            throw new TransferException(
                "El monto debe ser mayor a " + MONTO_MINIMO,
                "VALIDATION_ERROR");
        }
        if (monto.compareTo(MONTO_MAXIMO) > 0) {
            throw new TransferException(
                "El monto excede el límite máximo de " + MONTO_MAXIMO,
                "LIMITE_EXCEDIDO");
        }
    }
}

```
