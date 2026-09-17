# Desarrollo de API REST en dominio de banca

En un entorno de banca digital, necesitas desarrollar una API REST que maneje las solicitudes de transferencia de fondos entre cuentas. La API debe interactuar con una base de datos H2 para persistir los datos y usar Swagger para documentar las operaciones disponibles. Los actores involucrados son el 'originador de transferencias', el 'core bancario' y el'sistema de auditoría'. La API debe manejar un throughput de 500 transacciones por segundo y garantizar la idempotencia de las solicitudes mediante una clave única por operación. Deberás tomar decisiones sobre cómo estructurar la API, manejar errores y asegurar la integridad de los datos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Java Spring Boot REST API |
| **Nivel** | junior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Estructura básica de la API

**Objetivo:** Definir y estructurar la API para manejar solicitudes de transferencia de fondos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los endpoints necesarios para las operaciones de transferencia.
- Establecer la estructura de los recursos y las relaciones entre ellos.
- Garantizar que la API acepte y valide los datos de entrada correctamente.

**Entregable:** Especificación de la API y endpoints básicos implementados.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las operaciones CRUD necesarias para las transferencias.
- Piensa en cómo representar las relaciones entre cuentas y transferencias.

</details>

### Fase 2: Implementación de la persistencia

**Objetivo:** Implementar la persistencia de las transferencias en la base de datos H2.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configurar la conexión a la base de datos H2.
- Definir las entidades y relaciones necesarias para las transferencias.
- Implementar los métodos para persistir y recuperar las transferencias.

**Entregable:** Base de datos H2 configurada y métodos de persistencia implementados.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo modelar las entidades para asegurar la integridad referencial.
- Piensa en cómo manejar las relaciones entre cuentas y transferencias en la base de datos.

</details>

### Fase 3: Documentación y validación

**Objetivo:** Documentar la API usando Swagger y asegurar la validación de las solicitudes.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar Swagger para documentar la API.
- Implementar la validación de las solicitudes de transferencia.
- Asegurar que la API maneje correctamente los errores y devuelva respuestas adecuadas.

**Entregable:** API documentada con Swagger y validación de solicitudes implementada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo documentar los endpoints y sus operaciones usando Swagger.
- Piensa en cómo manejar los errores y asegurar que la API devuelva respuestas adecuadas.

</details>

### Fase 4: Optimización y decisiones de diseño

**Objetivo:** Optimizar la API para manejar el throughput requerido y tomar decisiones de diseño informadas.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Optimizar la API para manejar 500 transacciones por segundo.
- Tomar decisiones de diseño informadas para asegurar la idempotencia de las solicitudes.
- Evaluar y mitigar posibles puntos de falla en la API.

**Entregable:** API optimizada y decisiones de diseño documentadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo optimizar la API para manejar el throughput requerido.
- Piensa en cómo asegurar la idempotencia de las solicitudes mediante una clave única por operación.
- Evalúa y mitiga posibles puntos de falla en la API.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una API REST y cómo se estructura?
- **paraQueSirve**: ¿Para qué sirve documentar una API con Swagger?
- **comoSeUsa**: ¿Cómo se usa H2 para persistir datos en una aplicación Java?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar una API REST y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica asegurar la idempotencia de las solicitudes en una API REST?

## Criterios de Evaluacion

- Especificación de la API y endpoints básicos implementados.
- Base de datos H2 configurada y métodos de persistencia implementados.
- API documentada con Swagger y validación de solicitudes implementada.
- API optimizada para manejar 500 transacciones por segundo y decisiones de diseño documentadas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
