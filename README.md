# 🏗️ Microservicios con Spring Boot y Docker

Este proyecto implementa una arquitectura de microservicios usando Spring Boot 3.1.5, Java 21, Eureka Server para service discovery, y Docker para containerización.

## 📁 Estructura del Proyecto

```
udemy-apirest-ms/
├── eureka-server/          # Servidor de registro de servicios
├── api-gateway/            # Gateway de entrada y enrutamiento
├── micro-usuarios/         # Microservicio de gestión de usuarios
├── micro-direcciones/      # Microservicio de gestión de direcciones
├── docker-compose.yml      # Orquestación de contenedores
└── README.md              # Este archivo
```

## 🛠️ Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.1.5** - Framework principal
- **Spring Cloud** - Suite de microservicios
- **Eureka Server** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **MySQL 8** - Base de datos
- **Docker & Docker Compose** - Containerización
- **Maven** - Gestión de dependencias

## 🚀 Instrucciones de Despliegue

### Prerrequisitos

- **Docker** y **Docker Compose** instalados
- **Java 21** (para desarrollo local)
- **Maven 3.6+** (para construcción local)

### 1️⃣ Construcción de los JARs

Ejecuta los siguientes comandos para generar los archivos JAR de cada microservicio:

```bash
# Eureka Server
cd eureka-server
./mvnw clean package -DskipTests
cd ..

# API Gateway
cd api-gateway  
./mvnw clean package -DskipTests
cd ..

# Microservicio de Usuarios
cd micro-usuarios
./mvnw clean package -DskipTests
cd ..

# Microservicio de Direcciones
cd micro-direcciones
./mvnw clean package -DskipTests
cd ..
```

**Windows (PowerShell):**
```powershell
# Para cada microservicio, ejecutar:
cd eureka-server ; .\mvnw.cmd clean package -DskipTests ; cd ..
cd api-gateway ; .\mvnw.cmd clean package -DskipTests ; cd ..
cd micro-usuarios ; .\mvnw.cmd clean package -DskipTests ; cd ..
cd micro-direcciones ; .\mvnw.cmd clean package -DskipTests ; cd ..
```

### 2️⃣ Levantamiento con Docker Compose

Una vez generados los JARs, ejecuta desde la raíz del proyecto:

```bash
# Construir y levantar todos los servicios
docker-compose up --build

# Para ejecutar en background
docker-compose up --build -d

# Para ver los logs
docker-compose logs -f

# Para parar los servicios
docker-compose down

# Para parar y eliminar volúmenes
docker-compose down -v
```

### 3️⃣ Verificación del Despliegue

Los servicios estarán disponibles en los siguientes puertos:

- **Eureka Server**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **Micro Usuarios**: http://localhost:8081 (directo)
- **Micro Direcciones**: http://localhost:8082 (directo)
- **MySQL**: localhost:3306

## 🌐 Endpoints Disponibles

### 📋 A través del API Gateway (Puerto 8080)

#### **Usuarios** - `/usuarios`
```http
GET    http://localhost:8080/usuarios                    # Listar todos los usuarios
GET    http://localhost:8080/usuarios/{id}               # Obtener usuario por ID
POST   http://localhost:8080/usuarios                    # Crear nuevo usuario
PUT    http://localhost:8080/usuarios/{id}               # Actualizar usuario
DELETE http://localhost:8080/usuarios/{id}               # Eliminar usuario
GET    http://localhost:8080/usuarios/buscar?nombre=X    # Buscar por nombre
GET    http://localhost:8080/usuarios/email/existe?email=X # Verificar email
```

#### **Direcciones** - `/direcciones`
```http
GET    http://localhost:8080/direcciones                      # Listar todas las direcciones
GET    http://localhost:8080/direcciones/{id}                 # Obtener dirección por ID
POST   http://localhost:8080/direcciones                      # Crear nueva dirección
PUT    http://localhost:8080/direcciones/{id}                 # Actualizar dirección
DELETE http://localhost:8080/direcciones/{id}                 # Eliminar dirección
GET    http://localhost:8080/direcciones/usuario/{usuarioId}  # Direcciones por usuario
GET    http://localhost:8080/direcciones/buscar/ciudad?ciudad=X # Buscar por ciudad
GET    http://localhost:8080/direcciones/buscar/codigo-postal?codigoPostal=X # Por código postal
```

### 📊 Endpoints de Monitoreo

- **Eureka Dashboard**: http://localhost:8761
- **Gateway Actuator**: http://localhost:8080/actuator/health
- **Usuarios Health**: http://localhost:8081/actuator/health
- **Direcciones Health**: http://localhost:8082/actuator/health

## 📄 Ejemplos de Payloads JSON

### Usuario
```json
{
  "nombre": "Juan Pérez",
  "email": "juan.perez@email.com", 
  "telefono": "+56912345678"
}
```

### Dirección
```json
{
  "calle": "Av. Principal 123",
  "ciudad": "Santiago",
  "estado": "Región Metropolitana",
  "codigoPostal": "8320000",
  "pais": "Chile",
  "usuarioId": 1
}
```

## 🗄️ Configuración de Base de Datos

El proyecto usa MySQL 8 con las siguientes credenciales por defecto:

- **Host**: mysql (contenedor) / localhost (externo)
- **Puerto**: 3306
- **Base de datos**: microservicios
- **Usuario**: microservices
- **Contraseña**: micro123
- **Root password**: root123

## 🐛 Solución de Problemas

### Servicios no se registran en Eureka
- Verifica que Eureka Server esté ejecutándose primero
- Revisa los logs: `docker-compose logs eureka-server`

### Error de conexión a MySQL
- Espera que MySQL termine de inicializar
- Verifica: `docker-compose logs mysql`

### Puerto ya en uso
```bash
# Verificar puertos ocupados
netstat -tulpn | grep :8080

# Cambiar puertos en docker-compose.yml si es necesario
```

### Rebuild completo
```bash
# Limpiar y reconstruir todo
docker-compose down -v
docker system prune -f
docker-compose up --build
```

## 🏗️ Arquitectura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │───▶│   API Gateway   │───▶│  Eureka Server  │
│   (Puerto ?)    │    │   (Puerto 8080) │    │   (Puerto 8761) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │
                                ▼
                    ┌─────────────────────┐
                    │     Service         │
                    │     Discovery       │
                    └─────────────────────┘
                                │
                ┌───────────────┼───────────────┐
                ▼                               ▼
    ┌─────────────────┐              ┌─────────────────┐
    │ Micro-Usuarios  │              │ Micro-Direccion │
    │  (Puerto 8081)  │              │  (Puerto 8082)  │
    └─────────────────┘              └─────────────────┘
                │                                │
                └─────────────┬──────────────────┘
                              ▼
                    ┌─────────────────┐
                    │     MySQL       │
                    │  (Puerto 3306)  │
                    └─────────────────┘
```

## 👨‍💻 Desarrollo

### Ejecutar localmente (sin Docker)
1. Inicia MySQL localmente
2. Ejecuta Eureka Server primero
3. Luego API Gateway
4. Finalmente los microservicios

### Hot Reload para desarrollo
Usa Spring Boot DevTools en cada proyecto para hot reload durante desarrollo.

---

**¡Listo! Tu arquitectura de microservicios está funcionando con Docker! 🚀**