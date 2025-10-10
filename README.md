# 🏗️ Microservicios con Spring Boot y Docker

Este proyecto implementa una arquitectura completa de microservicios usando **Spring Boot 3.2.12**, **Java 21**, **Eureka Server** para service discovery, y **Docker** para containerización.

## 📁 Estructura del Proyecto

```
udemy-apirest-ms/
├── eureka-server/          # Servidor de registro de servicios (Puerto 8761)
├── api-gateway/            # Gateway de entrada y enrutamiento (Puerto 8080)
├── micro-usuarios/         # Microservicio de gestión de usuarios (Puerto 8081)
├── micro-direcciones/      # Microservicio de gestión de direcciones (Puerto 8082)
├── docker-compose.yml      # Orquestación de contenedores
├── init-db.sql            # Script de inicialización de BD
├── build-all.bat          # Script de construcción para Windows
├── build-all.sh           # Script de construcción para Linux/Mac
├── Microservicios-*.json  # Colección de Postman para pruebas
└── README.md              # Este archivo
```

## 🛠️ Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.2.12** - Framework principal
- **Spring Cloud 2023.0.4** - Suite de microservicios
- **Eureka Server** - Service Discovery
- **Spring Cloud Gateway** - API Gateway y Load Balancer
- **MySQL 8** - Base de datos relacional
- **Docker & Docker Compose** - Containerización y orquestación
- **Maven 3.9.11** - Gestión de dependencias y construcción
- **Spring Boot Actuator** - Monitoreo y métricas

## 🚀 Guía Completa de Instalación

### 📋 Prerrequisitos

Antes de instalar, asegúrate de tener instalado:

- **Docker Desktop** (versión 20.10+) y **Docker Compose** 
- **Java 21** (solo para desarrollo local - opcional)
- **Git** para clonar el repositorio
- **Postman** (opcional, para pruebas con la colección incluida)

### 🔧 Instalación Paso a Paso

#### **Paso 1: Clonar el Repositorio**
```bash
git clone https://github.com/NameYohell/udemy-apirest-micro.git
cd udemy-apirest-micro
```

#### **Paso 2: Verificar Docker**
```bash
# Verificar que Docker esté funcionando
docker --version
docker-compose --version

# Verificar que Docker Desktop esté ejecutándose
docker ps
```

#### **Paso 3: Construcción Automatizada**

**Opción A - Scripts Automatizados (Recomendado):**

**Windows:**
```batch
.\build-all.bat
```

**Linux/Mac:**
```bash
chmod +x build-all.sh
./build-all.sh
```

**Opción B - Construcción Manual:**

```bash
# Eureka Server
cd eureka-server && ./mvnw clean package -DskipTests && cd ..

# API Gateway
cd api-gateway && ./mvnw clean package -DskipTests && cd ..

# Microservicio de Usuarios
cd micro-usuarios && ./mvnw clean package -DskipTests && cd ..

# Microservicio de Direcciones
cd micro-direcciones && ./mvnw clean package -DskipTests && cd ..
```

**Windows (PowerShell):**
```powershell
# Construcción manual en Windows
cd eureka-server ; .\mvnw.cmd clean package -DskipTests ; cd ..
cd api-gateway ; .\mvnw.cmd clean package -DskipTests ; cd ..
cd micro-usuarios ; .\mvnw.cmd clean package -DskipTests ; cd ..
cd micro-direcciones ; .\mvnw.cmd clean package -DskipTests ; cd ..
```

#### **Paso 4: Desplegar con Docker Compose**

```bash
# Levantar todos los servicios (primera vez)
docker-compose up --build

# Para ejecutar en segundo plano
docker-compose up --build -d

# Ver logs en tiempo real
docker-compose logs -f

# Ver logs de un servicio específico
docker-compose logs -f micro-usuarios
```

#### **Paso 5: Verificación del Despliegue**

**Esperar a que todos los servicios estén saludables (aprox. 2-3 minutos):**

```bash
# Verificar estado de contenedores
docker-compose ps

# Verificar logs de salud
docker-compose logs mysql
docker-compose logs eureka-server
```

**Servicios disponibles:**
- **Eureka Server**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **Micro Usuarios**: http://localhost:8081 (acceso directo)
- **Micro Direcciones**: http://localhost:8082 (acceso directo)
- **MySQL**: localhost:3308 (puerto externo)

#### **Paso 6: Verificación de Conectividad**

```bash
# Verificar salud de servicios
curl http://localhost:8761/actuator/health  # Eureka
curl http://localhost:8080/actuator/health  # Gateway  
curl http://localhost:8081/actuator/health  # Usuarios
curl http://localhost:8082/actuator/health  # Direcciones

# Verificar registro en Eureka
curl http://localhost:8761/eureka/apps
```

### 🛑 Comandos de Gestión

```bash
# Parar servicios
docker-compose down

# Parar y eliminar volúmenes (limpieza completa)
docker-compose down -v

# Restart completo
docker-compose restart

# Rebuild completo (si hay cambios en código)
docker-compose down -v && docker-compose up --build

# Ver uso de recursos
docker stats
```

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

El proyecto usa **MySQL 8** con las siguientes credenciales:

### 📊 Credenciales de Conexión
- **Host**: `mysql` (desde contenedores) / `localhost` (externo)
- **Puerto**: `3308` (externo) / `3306` (interno)
- **Base de datos**: `eureka_db`
- **Usuario**: `yohel` o `root`
- **Contraseña**: `mipasswordsegura`
- **Root password**: `mipasswordsegura`

### 🔌 Conexión Externa
```bash
# Conectar desde terminal
mysql -h localhost -P 3308 -u root -p
# Password: mipasswordsegura

# O con usuario yohel
mysql -h localhost -P 3308 -u yohel -p
# Password: mipasswordsegura
```

### 📋 Inicialización Automática
La base de datos se inicializa automáticamente con el archivo `init-db.sql` que contiene:
- Creación de tablas para usuarios y direcciones
- Datos de prueba iniciales
- Configuración de relaciones FK

## 🧪 Pruebas con Postman

### � Colección Incluida
El proyecto incluye una colección completa de Postman:
- **Archivo**: `Microservicios-API-Collection.postman_collection.json`
- **Environment**: `Microservicios-Environment.postman_environment.json`

### 🔄 Importar en Postman
1. Abrir Postman
2. Import → Upload Files
3. Seleccionar ambos archivos JSON
4. Configurar environment en Postman

### ✅ Pruebas Rápidas
```bash
# Crear usuario
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Test User","email":"test@email.com","telefono":"+56912345678"}'

# Listar usuarios
curl http://localhost:8080/usuarios

# Crear dirección
curl -X POST http://localhost:8080/direcciones \
  -H "Content-Type: application/json" \
  -d '{"calle":"Test Street 123","ciudad":"Santiago","estado":"RM","codigoPostal":"8320000","pais":"Chile","usuarioId":1}'
```

## 🐛 Solución de Problemas

### 🔍 Diagnóstico Rápido
```bash
# Verificar estado de todos los servicios
docker-compose ps

# Verificar salud de servicios
docker-compose exec api-gateway wget -qO- http://localhost:8080/actuator/health
```

### ❌ Problemas Comunes

#### **1. Servicios no se registran en Eureka**
```bash
# Verificar que Eureka esté funcionando
docker-compose logs eureka-server

# Verificar registro de servicios
curl http://localhost:8761/eureka/apps
```

#### **2. Error de conexión a MySQL**
```bash
# Verificar inicialización de MySQL (puede tomar 30-60 segundos)
docker-compose logs mysql

# Verificar conexión desde contenedor
docker-compose exec mysql mysql -u root -pmipasswordsegura -e "SHOW DATABASES;"
```

#### **3. Puertos ya en uso**
```bash
# Windows - Verificar puertos ocupados
netstat -an | findstr :8080
netstat -an | findstr :3308

# Linux/Mac
netstat -tulpn | grep :8080
lsof -i :3308

# Cambiar puertos en docker-compose.yml si es necesario
```

#### **4. Problemas de memoria**
```bash
# Verificar uso de recursos
docker stats

# Aumentar memoria para Docker Desktop (mínimo 4GB recomendado)
```

#### **5. Rebuild completo**
```bash
# Limpieza completa y reconstrucción
docker-compose down -v
docker system prune -f
docker volume prune -f
docker-compose up --build
```

### 🔄 Secuencia de Inicio Correcta
1. **MySQL** (30-60 segundos para inicializar)
2. **Eureka Server** (15-30 segundos)
3. **API Gateway** (registrarse en Eureka)
4. **Microservicios** (registrarse en Eureka)

## 🏗️ Arquitectura del Sistema

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Cliente/      │───▶│   API Gateway   │───▶│  Eureka Server  │
│   Postman       │    │   Puerto 8080   │    │   Puerto 8761   │
│                 │    │   (Load Balance)│    │ (Service Discov)│
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │                       │
                                │                       ▼
                                │           ┌─────────────────────┐
                                │           │   Service Registry  │
                                │           │   (Health Checks)   │
                                │           └─────────────────────┐
                                │                                │
                ┌───────────────┼──────────────┬─────────────────┘
                ▼                              ▼
    ┌─────────────────────┐        ┌─────────────────────┐
    │  Micro-Usuarios     │        │ Micro-Direcciones   │
    │   Puerto 8081       │        │   Puerto 8082       │
    │ • CRUD Usuarios     │        │ • CRUD Direcciones  │
    │ • Validaciones      │        │ • Relación con      │
    │ • APIs REST         │        │   usuarios          │
    └─────────────────────┘        └─────────────────────┘
                │                                │
                └─────────────┬──────────────────┘
                              ▼
                    ┌─────────────────────┐
                    │      MySQL 8        │
                    │   Puerto 3308       │
                    │ Base: eureka_db     │
                    │ • Tablas: usuarios  │
                    │ • Tablas: direccions│
                    │ • Datos iniciales   │
                    └─────────────────────┘
```

## 👨‍💻 Desarrollo Local

### 🔧 Configuración para Desarrollo

#### **Ejecutar sin Docker (para desarrollo)**
```bash
# 1. Configurar MySQL local
mysql -u root -p
CREATE DATABASE eureka_db;

# 2. Ejecutar servicios en orden:
cd eureka-server && ./mvnw spring-boot:run
cd api-gateway && ./mvnw spring-boot:run  
cd micro-usuarios && ./mvnw spring-boot:run
cd micro-direcciones && ./mvnw spring-boot:run
```

#### **Hot Reload habilitado**
- ✅ **Spring Boot DevTools** incluido en todos los proyectos
- ✅ Cambios automáticos sin restart completo
- ✅ LiveReload para desarrollo frontend

#### **Perfiles de configuración**
- `default`: Desarrollo local
- `docker`: Contenedores (URLs internas)

### 🧪 Testing y Desarrollo
```bash
# Ejecutar tests
./mvnw test                    # Un proyecto específico
./build-all.sh -test           # Todos los proyectos (si modificas script)

# Desarrollo con watch mode
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

## 🚀 Producción

### 📦 Build para Producción
```bash
# Build optimizado (sin DevTools)
./mvnw clean package -Pprod -DskipTests

# Crear imagen Docker optimizada
docker-compose -f docker-compose.prod.yml up --build
```

### 🔒 Consideraciones de Seguridad
- [ ] Cambiar credenciales por defecto de MySQL
- [ ] Configurar HTTPS en API Gateway  
- [ ] Implementar autenticación JWT
- [ ] Usar secrets de Docker/Kubernetes
- [ ] Configurar rate limiting

### 📈 Monitoreo
- **Actuator Health**: `/actuator/health`
- **Metrics**: `/actuator/metrics`
- **Info**: `/actuator/info`
- **Eureka Dashboard**: `http://localhost:8761`

---

## 🎯 Próximos Pasos

### 🔄 Mejoras Sugeridas
- [ ] Agregar autenticación y autorización
- [ ] Implementar circuit breaker (Hystrix/Resilience4j)  
- [ ] Configurar logging centralizado (ELK Stack)
- [ ] Agregar cache distribuido (Redis)
- [ ] Configurar métricas avanzadas (Prometheus + Grafana)
- [ ] Implementar CI/CD pipeline
- [ ] Agregar tests de integración

### 📚 Recursos Adicionales
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Eureka Service Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)
- [Docker Compose](https://docs.docker.com/compose/)

---

**¡Listo! Tu arquitectura de microservicios está funcionando con Docker! 🚀**

**Desarrollado por**: NameYohell  
**Proyecto**: Microservicios Spring Boot + Docker  
**Versión**: 1.0.0