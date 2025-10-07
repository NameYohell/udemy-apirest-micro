#!/bin/bash

# Script para construir todos los microservicios
echo "🏗️ Construyendo microservicios..."

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Función para construir un microservicio
build_service() {
    local service_name=$1
    echo -e "${YELLOW}📦 Construyendo $service_name...${NC}"
    
    cd $service_name
    if ./mvnw clean package -DskipTests; then
        echo -e "${GREEN}✅ $service_name construido exitosamente${NC}"
    else
        echo -e "${RED}❌ Error construyendo $service_name${NC}"
        exit 1
    fi
    cd ..
}

# Construir cada microservicio
build_service "eureka-server"
build_service "api-gateway"
build_service "micro-usuarios"
build_service "micro-direcciones"

echo -e "${GREEN}🎉 ¡Todos los microservicios construidos exitosamente!${NC}"
echo -e "${YELLOW}💡 Ahora puedes ejecutar: docker-compose up --build${NC}"