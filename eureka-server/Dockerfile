# Dockerfile para Eureka Server
FROM eclipse-temurin:21-jdk

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR del microservicio
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del servicio
EXPOSE 8761

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]