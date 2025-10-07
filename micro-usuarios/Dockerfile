# Dockerfile para Micro Usuarios
FROM eclipse-temurin:21-jdk

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR del microservicio
COPY target/micro-usuarios-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del servicio
EXPOSE 8081

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]