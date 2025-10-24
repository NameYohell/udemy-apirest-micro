@echo off
REM Script para construir todos los microservicios en Windows

echo 🏗️ Construyendo microservicios...

REM Función para construir un microservicio
:build_service
set service_name=%1
echo 📦 Construyendo %service_name%...

cd %service_name%
call .\mvnw.cmd clean package -DskipTests
if errorlevel 1 (
    echo ❌ Error construyendo %service_name%
    exit /b 1
) else (
    echo ✅ %service_name% construido exitosamente
)
cd ..
goto :eof

REM Construir cada microservicio
call :build_service "eureka-server"
call :build_service "api-gateway"
call :build_service "micro-usuarios"
call :build_service "micro-direcciones"
call :build_service "micro-comentarios"

echo 🎉 ¡Todos los microservicios construidos exitosamente!
echo 💡 Ahora puedes ejecutar: docker-compose up --build
pause