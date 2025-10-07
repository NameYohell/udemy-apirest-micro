# Dockerfile para API Gateway
FROM eclipse-temurin:21-jdk

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR del microservicio
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del servicio
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]