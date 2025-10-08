# 🗂️ Configuración GitIgnore - Proyecto Microservicios

## 📋 Resumen de Exclusiones

Este `.gitignore` ha sido optimizado específicamente para el proyecto de microservicios con Spring Boot, excluyendo archivos innecesarios para la ejecución y prueba del proyecto.

## 🚫 Archivos y Directorios Excluidos

### 📦 Artefactos de Construcción
- `**/target/` - Directorios de compilación de Maven
- `*.jar`, `*.war` - Archivos ejecutables generados
- `*.class` - Archivos de bytecode compilado
- `**/build/` - Directorios de construcción de Gradle

### 💻 Configuraciones de IDEs
- `.idea/` - Configuraciones de IntelliJ IDEA
- `.vscode/` - Configuraciones de Visual Studio Code
- `.settings/` - Configuraciones de Eclipse
- `*.iml`, `*.ipr` - Archivos de proyecto de IntelliJ

### 🔧 Configuraciones Sensibles
- `**/application-local.properties` - Configuraciones de desarrollo local
- `**/application-dev.properties` - Configuraciones de desarrollo
- `.env` - Variables de entorno
- `**/*-credentials.properties` - Archivos con credenciales

### 📊 Logs y Temporales
- `*.log` - Archivos de log
- `logs/` - Directorios de logs
- `*.tmp`, `*.temp` - Archivos temporales
- `*.pid` - Archivos de ID de proceso

### 🐳 Docker
- `**/docker-data/` - Volúmenes de datos de Docker
- `docker-compose.override.yml` - Overrides locales de Docker Compose

### 🗃️ Base de Datos
- `*.h2.db` - Archivos de base de datos H2
- `**/mysql-data/` - Datos de MySQL

### 🛡️ Certificados y Claves
- `*.p12`, `*.jks` - Almacenes de claves
- `*.pem`, `*.key` - Certificados y claves privadas

## ✅ Archivos Importantes Incluidos

### 📝 Configuración del Proyecto
- `pom.xml` - Configuración de Maven
- `src/` - Código fuente
- `Dockerfile` - Definiciones de contenedores
- `docker-compose.yml` - Orquestación de servicios

### 📚 Documentación
- `README.md` - Documentación principal
- `*.md` - Archivos de documentación
- `init-db.sql` - Script de inicialización de BD

### 🏗️ Scripts de Construcción
- `build-all.sh` / `build-all.bat` - Scripts de construcción
- `.mvn/wrapper/maven-wrapper.properties` - Configuración de Maven Wrapper

## 🧹 Limpieza de Archivos Existentes

Si ya tienes archivos que deberían ser ignorados en tu repositorio, ejecuta:

```bash
# Remover archivos del índice de Git (pero mantenerlos localmente)
git rm --cached -r */target/
git rm --cached *.log
git rm --cached -r .idea/
git rm --cached -r .vscode/

# Commit los cambios
git add .gitignore
git commit -m "feat: optimize .gitignore for microservices project"
```

## 🔄 Mantener el GitIgnore Actualizado

### Agregar nuevas exclusiones:
```bash
# Para un archivo específico
echo "archivo-a-ignorar.txt" >> .gitignore

# Para un patrón
echo "*.extension" >> .gitignore
```

### Verificar qué archivos serán ignorados:
```bash
# Ver archivos que serán ignorados
git status --ignored

# Ver si un archivo específico será ignorado
git check-ignore archivo.txt
```

## 🎯 Beneficios de esta Configuración

### ✅ **Ventajas:**
- **Repository limpio** - Solo código fuente y configuraciones esenciales
- **Builds más rápidos** - No sincronizar archivos compilados
- **Seguridad mejorada** - Excluye credenciales y configuraciones sensibles
- **Colaboración eficiente** - Evita conflictos por archivos generados
- **Menor uso de almacenamiento** - Repository más pequeño

### 🎲 **Casos de Uso:**
- **Desarrollo local** - Cada desarrollador puede tener configuraciones personales
- **CI/CD** - Pipeline se construye desde código fuente limpio
- **Deploy** - Solo artefactos necesarios en producción
- **Troubleshooting** - Fácil identificar qué archivos son realmente importantes

## 🚨 Consideraciones Importantes

### ⚠️ **No Ignores Accidentalmente:**
- Archivos de configuración necesarios para el proyecto
- Scripts de inicialización de base de datos
- Documentación del proyecto
- Archivos de ejemplo o templates

### 🔍 **Revisa Regularmente:**
- Nuevos tipos de archivos generados
- Cambios en herramientas de desarrollo
- Actualizaciones de frameworks que generen nuevos artefactos

---

**Última actualización:** Octubre 2025  
**Versión del proyecto:** Spring Boot 3.2.12, Java 21