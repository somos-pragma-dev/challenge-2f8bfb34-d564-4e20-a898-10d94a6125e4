# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de API REST en dominio de banca**.

| | |
|---|---|
| Tema | Java Spring Boot REST API |
| Nivel | junior-l2 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java 21 / Spring Boot 3.3.0 |
| Patron arquitectonico | capas estándar (Controller-Service-Repository) |
| Tiempo estimado | 8 horas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Estructura básica de la API**: Especificación de la API y endpoints básicos implementados.
- **Fase 2 — Implementación de la persistencia**: Base de datos H2 configurada y métodos de persistencia implementados.
- **Fase 3 — Documentación y validación**: API documentada con Swagger y validación de solicitudes implementada.
- **Fase 4 — Optimización y decisiones de diseño**: API optimizada y decisiones de diseño documentadas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### 2. Referencias colgando (24)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/bank/transferapi/TransferApiApplication.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/TransferApiApplication.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/dto/TransferRequest.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/service/TransferService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.cuentaOrigen`
      Se invoca `cuentaOrigen` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.cuentaDestino`
      Se invoca `cuentaDestino` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.monto`
      Se invoca `monto` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferException.getMessage`
      Se invoca `getMessage` sobre `TransferException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferException.getHttpStatus`
      Se invoca `getHttpStatus` sobre `TransferException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/controller/TransferController.java` — `TransferRequest.claveIdempotencia`
      Se invoca `claveIdempotencia` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/service/TransferService.java` — `TransferRepository.save`
      Se invoca `save` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/service/TransferService.java` — `TransferRepository.findById`
      Se invoca `findById` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/service/TransferService.java` — `TransferRepository.findAll`
      Se invoca `findAll` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.getDefaultMessage`
      Se invoca `getDefaultMessage` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setTimestamp`
      Se invoca `setTimestamp` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setCodigo`
      Se invoca `setCodigo` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setMensaje`
      Se invoca `setMensaje` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setDetalle`
      Se invoca `setDetalle` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java` — `ErrorResponse.setPath`
      Se invoca `setPath` sobre `ErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transferapi/service/TransferServiceTest.java` — `TransferRepository.save`
      Se invoca `save` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/bank/transferapi/service/TransferServiceTest.java` — `TransferRepository.findById`
      Se invoca `findById` sobre `TransferRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (14)

- `pom.xml`
- `src/main/java/com/bank/transferapi/TransferApiApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/bank/transferapi/model/Transfer.java`
- `src/main/java/com/bank/transferapi/dto/TransferRequest.java`
- `src/main/java/com/bank/transferapi/repository/TransferRepository.java`
- `src/main/java/com/bank/transferapi/dto/TransferResponse.java`
- `src/main/java/com/bank/transferapi/controller/TransferController.java`
- `src/main/java/com/bank/transferapi/service/TransferService.java`
- `src/main/java/com/bank/transferapi/exception/TransferException.java`
- `src/main/java/com/bank/transferapi/exception/GlobalExceptionHandler.java`
- `src/main/java/com/bank/transferapi/config/OpenApiConfig.java`
- `src/test/java/com/bank/transferapi/controller/TransferControllerTest.java`
- `src/test/java/com/bank/transferapi/service/TransferServiceTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/bank/transferapi`
- `src/main/java/com/bank/transferapi/controller`
- `src/main/java/com/bank/transferapi/service`
- `src/main/java/com/bank/transferapi/repository`
- `src/main/java/com/bank/transferapi/dto`
- `src/main/java/com/bank/transferapi/model`
- `src/main/java/com/bank/transferapi/exception`
- `src/main/java/com/bank/transferapi/config`
- `src/main/resources`
- `src/test/java/com/bank/transferapi`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar (Controller-Service-Repository)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: API REST con H2 y Swagger

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
