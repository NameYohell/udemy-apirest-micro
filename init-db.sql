-- Script de inicialización de base de datos para microservicios
-- Este archivo se ejecuta automáticamente cuando MySQL se inicia por primera vez

-- Crear la base de datos eureka_db si no existe
CREATE DATABASE IF NOT EXISTS eureka_db;

-- Usar la base de datos eureka_db
USE eureka_db;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE,
    telefono VARCHAR(20),
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
);

-- Tabla de direcciones
CREATE TABLE IF NOT EXISTS direcciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(255) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    estado VARCHAR(100),
    codigo_postal VARCHAR(20),
    pais VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_usuario_id (usuario_id),
    INDEX idx_ciudad (ciudad),
    INDEX idx_codigo_postal (codigo_postal),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Datos de ejemplo para usuarios
INSERT INTO usuarios (nombre, email, telefono) VALUES 
('Juan Pérez', 'juan.perez@email.com', '+56912345678'),
('María González', 'maria.gonzalez@email.com', '+56987654321'),
('Carlos López', 'carlos.lopez@email.com', '+56911111111'),
('Ana Martínez', 'ana.martinez@email.com', '+56922222222');

-- Datos de ejemplo para direcciones
INSERT INTO direcciones (calle, ciudad, estado, codigo_postal, pais, usuario_id) VALUES 
('Av. Providencia 1234', 'Santiago', 'Región Metropolitana', '7500000', 'Chile', 1),
('Calle Las Condes 5678', 'Santiago', 'Región Metropolitana', '7550000', 'Chile', 1),
('Av. Valparaíso 999', 'Viña del Mar', 'Región de Valparaíso', '2520000', 'Chile', 2),
('Calle Libertad 456', 'Concepción', 'Región del Biobío', '4030000', 'Chile', 3),
('Av. Alemania 789', 'Temuco', 'Región de La Araucanía', '4780000', 'Chile', 4);

-- Mostrar resumen de datos insertados
SELECT 'Usuarios creados:' as resultado, COUNT(*) as cantidad FROM usuarios
UNION ALL
SELECT 'Direcciones creadas:' as resultado, COUNT(*) as cantidad FROM direcciones;