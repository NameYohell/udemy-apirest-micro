# 📚 Guía de Aprendizaje - Microservicios con Spring Boot

## 🎯 Objetivos de Aprendizaje

Al completar esta guía, serás capaz de:
- Comprender la arquitectura de microservicios y sus beneficios
- Implementar un ecosistema completo de microservicios con Spring Boot
- Configurar service discovery con Eureka
- Implementar un API Gateway para enrutamiento
- Containerizar aplicaciones con Docker
- Resolver problemas comunes en microservicios

---

## 🏗️ Análisis de Tecnologías Utilizadas

### 1. **Java 21** ☕
**¿Por qué se usó?**
- Versión LTS más reciente con mejoras en rendimiento
- Nuevas características como pattern matching y records
- Mejor gestión de memoria y garbage collection

**Conceptos clave:**
- JVM (Java Virtual Machine)
- Compilación bytecode
- Gestión automática de memoria

### 2. **Spring Boot 3.2.12** 🌱
**¿Por qué se usó?**
- Framework que simplifica la configuración de aplicaciones Java
- Auto-configuración reduce código boilerplate
- Ecosistema maduro con gran comunidad
- Integración nativa con microservicios

**Beneficios:**
- Configuración por convención
- Servidor embebido (Tomcat)
- Actuator para monitoreo
- Facilita testing

### 3. **Spring Cloud 2023.0.4** ☁️
**¿Por qué se usó?**
- Suite especializada para microservicios
- Patrones de resiliencia incorporados
- Integración con Netflix OSS
- Service discovery nativo

**Componentes utilizados:**
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign (implícito)

### 4. **Netflix Eureka** 🔍
**¿Por qué se usó?**
- Service Discovery y Service Registry
- Auto-registro de servicios
- Health checking
- Load balancing del lado cliente

**Conceptos:**
- Service Registry: Directorio de servicios disponibles
- Service Discovery: Mecanismo para encontrar servicios
- Heartbeat: Señal de vida de los servicios

### 5. **Spring Cloud Gateway** 🚪
**¿Por qué se usó?**
- Punto de entrada único (Single Point of Entry)
- Enrutamiento inteligente
- Filtros para cross-cutting concerns
- Mejor rendimiento que Zuul (asíncrono)

**Funcionalidades:**
- Rate limiting
- Circuit breaker
- Load balancing
- Authentication/Authorization

### 6. **Spring Data JPA** 💾
**¿Por qué se usó?**
- Abstracción sobre JPA/Hibernate
- Reduce código CRUD repetitivo
- Query methods automáticos
- Transacciones declarativas

**Conceptos:**
- ORM (Object-Relational Mapping)
- Entidades JPA
- Repositorios
- Lazy/Eager loading

### 7. **MySQL 8** 🗄️
**¿Por qué se usó?**
- Base de datos relacional robusta
- Buen rendimiento
- JSON nativo
- Amplia adopción en la industria

**Consideraciones:**
- ACID compliance
- Índices para performance
- Replicación master-slave

### 8. **Docker & Docker Compose** 🐳
**¿Por qué se usó?**
- Containerización para portabilidad
- Aislamiento de dependencias
- Orquestación de múltiples servicios
- Facilita deployment

**Beneficios:**
- "Works on my machine" → "Works everywhere"
- Escalabilidad horizontal
- DevOps practices

---

## ⚠️ Problemas Comunes y Soluciones

### 1. **Problema: Servicios no se registran en Eureka**
**Síntomas:**
- Dashboard de Eureka vacío
- Servicios no se encuentran entre sí

**Soluciones:**
```properties
# Verificar configuración en application.properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
```

**Causa raíz:** Configuración incorrecta de URLs o anotaciones faltantes

### 2. **Problema: Gateway no enruta correctamente**
**Síntomas:**
- 404 Not Found en rutas esperadas
- Requests no llegan a microservicios

**Soluciones:**
```yaml
# Verificar rutas en application.yml
spring:
  cloud:
    gateway:
      routes:
        - id: micro-usuarios-service
          uri: lb://MICRO-USUARIOS  # lb = load balancer
          predicates:
            - Path=/usuarios/**
```

### 3. **Problema: Conexión a base de datos falla**
**Síntomas:**
- SQLException al iniciar
- Servicios no pueden conectarse a MySQL

**Soluciones:**
- Verificar que MySQL esté ejecutándose
- Comprobar credenciales en application.properties
- Verificar que la base de datos existe

### 4. **Problema: Puerto ya en uso**
**Síntomas:**
- `Port 8080 is already in use`

**Soluciones:**
```bash
# Linux/Mac
lsof -ti:8080 | xargs kill -9

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### 5. **Problema: Dependencias circulares**
**Síntomas:**
- BeanCurrentlyInCreationException

**Soluciones:**
- Usar `@Lazy` annotation
- Refactorizar diseño para eliminar dependencias circulares
- Usar eventos de Spring para desacoplar

---

## 🚀 Instalación en Nuevo Ambiente

### Prerrequisitos
- [ ] Java 21 JDK instalado
- [ ] Docker Desktop instalado
- [ ] Git instalado
- [ ] IDE (IntelliJ IDEA, VS Code, Eclipse)

### Paso 1: Clonar el Repositorio
```bash
git clone https://github.com/NameYohell/udemy-apirest-micro.git
cd udemy-apirest-micro
```

### Paso 2: Verificar Instalaciones
```bash
# Verificar Java
java -version
# Debe mostrar: openjdk version "21.x.x"

# Verificar Docker
docker --version
docker-compose --version
```

### Paso 3: Construcción de Aplicaciones

**En Linux/Mac:**
```bash
chmod +x build-all.sh
./build-all.sh
```

**En Windows:**
```cmd
build-all.bat
```

**Construcción manual:**
```bash
# Para cada microservicio
cd eureka-server
./mvnw clean package -DskipTests
cd ../api-gateway
./mvnw clean package -DskipTests
cd ../micro-usuarios
./mvnw clean package -DskipTests
cd ../micro-direcciones
./mvnw clean package -DskipTests
cd ..
```

### Paso 4: Levantar Servicios
```bash
# Construir imágenes y levantar contenedores
docker-compose up --build

# En background (detached mode)
docker-compose up --build -d

# Ver logs en tiempo real
docker-compose logs -f
```

### Paso 5: Verificación
- **Eureka Dashboard:** http://localhost:8761
- **API Gateway:** http://localhost:8080
- **Micro Usuarios:** http://localhost:8081
- **Micro Direcciones:** http://localhost:8082

### Paso 6: Pruebas de API

**Crear usuario:**
```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@example.com",
    "telefono": "123-456-7890"
  }'
```

**Obtener usuarios:**
```bash
curl http://localhost:8080/usuarios
```

### Solución de Problemas Comunes en Instalación

1. **Error de permisos en Linux/Mac:**
   ```bash
   chmod +x mvnw
   chmod +x build-all.sh
   ```

2. **Puerto ocupado:**
   ```bash
   # Cambiar puertos en docker-compose.yml
   ports:
     - "8080:8080"  # cambiar primer número
   ```

3. **Memoria insuficiente:**
   ```bash
   # Aumentar memoria para Docker Desktop
   # Settings → Resources → Memory → 4GB+
   ```

---

## 🧠 Preguntas de Autoevaluación

### Nivel Básico 🟢

1. **¿Qué es un microservicio?**
   - [ ] Una aplicación pequeña
   - [ ] Un servicio independiente que hace una cosa bien
   - [ ] Un método dentro de una clase
   - [ ] Una base de datos

2. **¿En qué puerto corre por defecto Eureka Server?**
   - [ ] 8080
   - [ ] 8761
   - [ ] 3000
   - [ ] 5000

3. **¿Qué significa "lb://" en las rutas del Gateway?**
   - [ ] Load Balancer
   - [ ] Local Backend
   - [ ] Library
   - [ ] Logic Bean

4. **¿Cuál es la anotación principal para habilitar Eureka Server?**
   - [ ] @EnableEureka
   - [ ] @EurekaServer
   - [ ] @EnableEurekaServer
   - [ ] @ServiceRegistry

5. **¿Qué archivo de configuración usa Spring Boot por defecto?**
   - [ ] config.properties
   - [ ] application.properties
   - [ ] spring.properties
   - [ ] settings.properties

### Nivel Intermedio 🟡

6. **¿Qué patron implementa el API Gateway?**
   - [ ] Singleton
   - [ ] Observer
   - [ ] Backend for Frontend
   - [ ] Factory

7. **¿Qué significa @EnableDiscoveryClient?**
   - [ ] Habilita el servidor de descubrimiento
   - [ ] Permite que el servicio se registre en Eureka
   - [ ] Crea un cliente HTTP
   - [ ] Habilita la base de datos

8. **¿Cuál es la diferencia entre @RestController y @Controller?**
   - Tu respuesta: ________________

9. **¿Por qué usar Docker Compose en lugar de Docker run?**
   - Tu respuesta: ________________

10. **Explica el flujo de una petición desde el cliente hasta la base de datos:**
    - Tu respuesta: ________________

### Nivel Avanzado 🔴

11. **¿Cómo implementarías Circuit Breaker en este proyecto?**
    - Tu respuesta: ________________

12. **¿Qué estrategias de deployment usarías para microservicios?**
    - Tu respuesta: ________________

13. **¿Cómo manejarías transacciones distribuidas?**
    - Tu respuesta: ________________

14. **¿Qué métricas monitorizarías en producción?**
    - Tu respuesta: ________________

15. **Diseña una estrategia de logging centralizado:**
    - Tu respuesta: ________________

---

## 🔤 Sopa de Letras - Conceptos Clave

```
E U R E K A S E R V E R M I C R O S
S P R I N G B O O T G A T E W A Y T
T C O N T A I N E R D O A P I R E S
A L O A D B A L A N C E R E K C T U
C O N T R O L L E R S T N A C H A L
K U D O C K E R C O M P O S E I T F
T D I S C O V E R Y C L I E N T E U
R G A T E W A Y F I L T E R S E G L
A A J P A R E P O S I T O R Y C Y M
C T H E A L T H C H E C K D R T O Y
E E M Y S Q L C O N N E C T O R U S
R W B E A N C O N F I G U R A T I Q
F A C T U A T O R E N D P O I N T L
I Y R E S T T E M P L A T E J S O N
L F E I G N C L I E N T M O D E L S
T S P R I N G C L O U D N E T F L I
E M B E D D E D S E R V E R T E S T
R E S I L I E N C E P A T T E R N S
```

**Palabras a encontrar:**
- EUREKA
- SPRING
- MICROSERVICES
- GATEWAY
- DOCKER
- CONTAINER
- DISCOVERY
- LOADBALANCER
- CONTROLLER
- REPOSITORY
- ACTUATOR
- RESILIENCE
- EMBEDDED
- PATTERNS
- NETFLIX
- HEALTHCHECK
- RESTTEMPLATE
- CONFIGURATION

---

## 🎮 Actividades Prácticas

### Ejercicio 1: Modificar Configuración
1. Cambia el puerto del API Gateway a 9090
2. Agrega una nueva ruta en el Gateway para health checks
3. Verifica que los cambios funcionen

### Ejercicio 2: Nuevo Endpoint
1. Crea un endpoint en micro-usuarios para buscar por email
2. Agrega validación de email
3. Prueba con Postman o curl

### Ejercicio 3: Manejo de Errores
1. Implementa GlobalExceptionHandler
2. Crea respuestas de error consistentes
3. Prueba con datos inválidos

### Ejercicio 4: Logging
1. Configura niveles de logging diferentes por microservicio
2. Agrega logs estructurados con información de contexto
3. Verifica logs en Docker

---

## 📋 Checklist de Dominio

### Conceptos Fundamentales
- [ ] Entiendo qué son los microservicios
- [ ] Conozco las ventajas y desventajas
- [ ] Comprendo Service Discovery
- [ ] Entiendo el patrón API Gateway

### Tecnologías
- [ ] Puedo crear un proyecto Spring Boot
- [ ] Configuro Eureka Server y Client
- [ ] Implemento rutas en Spring Cloud Gateway
- [ ] Uso JPA para persistencia
- [ ] Containerizo con Docker

### Operación
- [ ] Puedo ejecutar el stack completo
- [ ] Diagnostico problemas de conectividad
- [ ] Monitor servicios con Actuator
- [ ] Manejo logs de múltiples servicios

### Desarrollo
- [ ] Creo nuevos endpoints REST
- [ ] Implemento validaciones
- [ ] Manejo errores apropiadamente
- [ ] Escribo tests unitarios

---

## 🎯 Próximos Pasos de Aprendizaje

1. **Monitoreo y Observabilidad**
   - Spring Boot Admin
   - Micrometer + Prometheus
   - Zipkin para tracing distribuido

2. **Seguridad**
   - Spring Security
   - OAuth2 / JWT
   - API Keys

3. **Resiliencia**
   - Circuit Breaker (Resilience4j)
   - Retry patterns
   - Bulkhead pattern

4. **Deployment**
   - Kubernetes
   - CI/CD pipelines
   - Blue-Green deployment

5. **Arquitectura Avanzada**
   - Event-driven architecture
   - CQRS pattern
   - Saga pattern para transacciones distribuidas

---

## 📚 Recursos Adicionales

### Documentación Oficial
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Cloud Reference](https://docs.spring.io/spring-cloud/docs/current/reference/html/)
- [Docker Documentation](https://docs.docker.com/)

### Libros Recomendados
- "Microservices Patterns" - Chris Richardson
- "Building Microservices" - Sam Newman
- "Spring Boot in Action" - Craig Walls

### Cursos Online
- Spring Academy
- Baeldung Tutorials
- Pluralsight Spring paths

---

**¡Felicitaciones por completar esta guía! 🎉**

*Recuerda: La práctica hace al maestro. Experimenta, rompe cosas, y aprende de los errores.*