-- Creación de la Base de Datos
CREATE DATABASE IF NOT EXISTS clicexpress_db;
USE clicexpress_db;

-- 1. Tabla de Proveedores
CREATE TABLE proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL,
    ruc VARCHAR(11) UNIQUE NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(20)
);

-- 2. Tabla de Categorías
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- 3. Tabla de Productos
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_proveedor INT,
    id_categoria INT,
    descripcion TEXT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    imagen_url VARCHAR(255),
    stock INT DEFAULT 0,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

-- 4. Tabla de Clientes
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_razon_social VARCHAR(150) NOT NULL,
    documento_identidad VARCHAR(20) UNIQUE NOT NULL, -- DNI o RUC
    tipo_cliente ENUM('Persona', 'Empresa') NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    direccion TEXT
);

-- 5. Tabla de Órdenes (Cabecera)
CREATE TABLE ordenes (
    id_orden INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado_orden ENUM('Pendiente Pago', 'Pagado', 'En Preparación', 'Enviado', 'Entregado', 'Finalizado') DEFAULT 'Pendiente Pago',
    total_pagar DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

-- 6. Detalle de la Orden
CREATE TABLE orden_detalles (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_orden INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_orden) REFERENCES ordenes(id_orden),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

-- 7. Tabla de Pagos
CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_orden INT,
    metodo_pago ENUM('Tarjeta de Crédito', 'Depósito Bancario') NOT NULL,
    fecha_pago DATETIME,
    monto_pagado DECIMAL(10,2),
    estado_pago ENUM('Verificado', 'Pendiente') DEFAULT 'Pendiente',
    FOREIGN KEY (id_orden) REFERENCES ordenes(id_orden)
);

-- 8. Tabla de Envíos (Seguimiento)
CREATE TABLE envios (
    id_envio INT AUTO_INCREMENT PRIMARY KEY,
    id_orden INT,
    empresa_reparto VARCHAR(100),
    codigo_seguimiento VARCHAR(50),
    fecha_envio DATETIME,
    fecha_entrega_real DATETIME,
    FOREIGN KEY (id_orden) REFERENCES ordenes(id_orden)
);