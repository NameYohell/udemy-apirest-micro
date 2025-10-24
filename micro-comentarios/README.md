# Microservicio de Comentarios

Este microservicio gestiona los comentarios de los usuarios en la plataforma. Permite a los usuarios crear, leer, actualizar y eliminar comentarios asociados a diferentes candidatos.

## Características

- **Puerto**: 8083
- **Base de datos**: MySQL (compartida con otros microservicios)
- **Registro**: Eureka Server
- **Framework**: Spring Boot 3.2.12
- **Java**: 21

## Modelo de Datos

### Entidad Comentario

La entidad `Comentario` contiene los siguientes atributos:

- **id** (Long): Identificador único del comentario (auto-generado)
- **usuarioId** (Long): ID del usuario que realizó el comentario (requerido)
- **candidato** (String): Nombre del candidato sobre el que se comenta (máximo 100 caracteres)
- **fechaComentario** (LocalDateTime): Fecha y hora de creación del comentario (auto-generada)
- **textoComentario** (String): Contenido del comentario (máximo 1000 caracteres)

### Validaciones

- El `usuarioId` es obligatorio
- El `candidato` es obligatorio y no puede tener más de 100 caracteres
- El `textoComentario` es obligatorio y no puede tener más de 1000 caracteres
- La `fechaComentario` se establece automáticamente al crear el comentario

## API Endpoints

### Comentarios CRUD

- `POST /api/comentarios` - Crear nuevo comentario
- `GET /api/comentarios` - Obtener todos los comentarios
- `GET /api/comentarios/{id}` - Obtener comentario por ID
- `PUT /api/comentarios/{id}` - Actualizar comentario
- `DELETE /api/comentarios/{id}` - Eliminar comentario

### Consultas Específicas

- `GET /api/comentarios/usuario/{usuarioId}` - Comentarios por usuario
- `GET /api/comentarios/candidato/{candidato}` - Comentarios por candidato
- `GET /api/comentarios/buscar?texto={texto}` - Buscar por contenido
- `GET /api/comentarios/fechas?fechaInicio={fecha}&fechaFin={fecha}` - Por rango de fechas
- `GET /api/comentarios/usuario/{usuarioId}/candidato/{candidato}` - Por usuario y candidato
- `GET /api/comentarios/usuario/{usuarioId}/count` - Contar comentarios por usuario
- `DELETE /api/comentarios/usuario/{usuarioId}` - Eliminar todos los comentarios de un usuario

### Formato de fechas

Para las consultas por fecha, usar formato ISO 8601:
`2024-10-24T10:30:00`

## Ejemplos de uso

### Crear un comentario

```bash
curl -X POST http://localhost:8083/api/comentarios \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "candidato": "Juan Pérez",
    "textoComentario": "Excelente propuesta de política educativa"
  }'
```

### Obtener comentarios por usuario

```bash
curl http://localhost:8083/api/comentarios/usuario/1
```

### Buscar comentarios por texto

```bash
curl http://localhost:8083/api/comentarios/buscar?texto=educativa
```

## Configuración

### Variables de entorno en Docker

- `SPRING_DATASOURCE_URL`: jdbc:mysql://mysql:3306/eureka_db
- `SPRING_DATASOURCE_USERNAME`: root
- `SPRING_DATASOURCE_PASSWORD`: mipasswordsegura
- `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE`: http://eureka-server:8761/eureka

### Desarrollo local

Para ejecutar en desarrollo, asegúrate de tener:

1. MySQL ejecutándose en puerto 3306
2. Base de datos `eureka_db` creada
3. Eureka Server ejecutándose en puerto 8761

```bash
cd micro-comentarios
./mvnw spring-boot:run
```

## Construcción

### Maven

```bash
./mvnw clean package -DskipTests
```

### Docker

```bash
docker build -t micro-comentarios .
```

## Integración

Este microservicio:

- Se registra automáticamente en Eureka Server
- Utiliza la misma base de datos MySQL que otros microservicios
- Puede ser accedido a través del API Gateway
- Mantiene logs detallados para monitoring y debugging

## Relaciones

- **Un usuario** puede tener **múltiples comentarios** (relación 1:N)
- Los comentarios están ordenados por fecha de creación (más recientes primero)
- No hay validación de existencia del usuario (microservicio independiente)