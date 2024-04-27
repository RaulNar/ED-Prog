-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2024 a las 11:12:14
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `far far west inc.`
--
CREATE DATABASE IF NOT EXISTS `far far west inc.` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `far far west inc.`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--
-- Creación: 24-04-2024 a las 09:01:10
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nº_reservas` int(11) NOT NULL,
  `id_pedido_fav` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `cliente`:
--   `dni`
--       `persona` -> `DNI`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingrediente`
--
-- Creación: 27-04-2024 a las 09:09:09
--

DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE `ingrediente` (
  `id_ingrediente` int(11) NOT NULL,
  `id_plato` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `tipo_producto` enum('carnes','pescados','lacteos','verduras','otros') NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `ingrediente`:
--   `id_plato`
--       `plato` -> `id_plato`
--   `id_sucursal`
--       `sucursales` -> `id_sucursal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--
-- Creación: 23-04-2024 a las 22:32:10
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE `persona` (
  `DNI` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `edad` int(11) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `persona`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plato`
--
-- Creación: 24-04-2024 a las 12:47:41
--

DROP TABLE IF EXISTS `plato`;
CREATE TABLE `plato` (
  `id_plato` int(11) NOT NULL,
  `nombre_plato` int(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `precio_plato` double NOT NULL,
  `tipo_plato` enum('Bebidas','Postres','Plato Principal','Plato Secundario') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `plato`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--
-- Creación: 24-04-2024 a las 12:43:00
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre_completo` varchar(50) NOT NULL,
  `telefono` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `proveedor`:
--   `id_sucursal`
--       `sucursales` -> `id_sucursal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--
-- Creación: 24-04-2024 a las 12:13:02
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre_reserva` varchar(30) NOT NULL,
  `fecha_pago` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `reserva`:
--   `id_cliente`
--       `cliente` -> `id_cliente`
--   `id_sucursal`
--       `sucursales` -> `id_sucursal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--
-- Creación: 24-04-2024 a las 10:34:17
--

DROP TABLE IF EXISTS `sucursales`;
CREATE TABLE `sucursales` (
  `id_sucursal` int(11) NOT NULL,
  `nombre_sucursal` int(20) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(100) NOT NULL,
  `horario` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `sucursales`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--
-- Creación: 24-04-2024 a las 11:14:24
--

DROP TABLE IF EXISTS `trabajador`;
CREATE TABLE `trabajador` (
  `id_trabjador` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `salario` double NOT NULL,
  `cargo` enum('Jefe','Chef','Camarero','Repartidor') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `trabajador`:
--   `dni`
--       `persona` -> `DNI`
--   `id_sucursal`
--       `sucursales` -> `id_sucursal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--
-- Creación: 24-04-2024 a las 07:04:32
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_factura` int(10) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `contraseña` char(20) NOT NULL,
  `permisos` int(10) NOT NULL,
  `nombre_usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `usuario`:
--   `dni`
--       `persona` -> `DNI`
--

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `dni` (`dni`);

--
-- Indices de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD PRIMARY KEY (`id_ingrediente`),
  ADD KEY `id_plato` (`id_plato`,`id_sucursal`),
  ADD KEY `id_sucursal` (`id_sucursal`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`DNI`);

--
-- Indices de la tabla `plato`
--
ALTER TABLE `plato`
  ADD PRIMARY KEY (`id_plato`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`),
  ADD KEY `id_sucursal` (`id_sucursal`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_sucursal` (`id_sucursal`),
  ADD KEY `id_cliente_2` (`id_cliente`,`id_sucursal`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`id_sucursal`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id_trabjador`),
  ADD KEY `dni` (`dni`,`id_sucursal`),
  ADD KEY `id_sucursal` (`id_sucursal`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  MODIFY `id_ingrediente` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `plato`
--
ALTER TABLE `plato`
  MODIFY `id_plato` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id_trabjador` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_factura` int(10) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `persona` (`DNI`);

--
-- Filtros para la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD CONSTRAINT `ingrediente_ibfk_1` FOREIGN KEY (`id_plato`) REFERENCES `plato` (`id_plato`),
  ADD CONSTRAINT `ingrediente_ibfk_2` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);

--
-- Filtros para la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `persona` (`DNI`),
  ADD CONSTRAINT `trabajador_ibfk_2` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `persona` (`DNI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
