-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-05-2024 a las 01:58:00
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
-- Base de datos: `far_far_west_inc_`
--
CREATE DATABASE IF NOT EXISTS `far_far_west_inc_` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `far_far_west_inc_`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `nº_reservas` int(11) NOT NULL,
  `id_pedido_fav` int(11) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `dni`, `nº_reservas`, `id_pedido_fav`) VALUES
(1, '22332233N', 0, 5),
(2, '33443344P', 12, 3),
(3, '44554455K', 5, 6),
(4, '12345677b', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `id_factura` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `id_pago` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `fecha_factura` date NOT NULL,
  `monto_total` int(11) NOT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `id_pedido` (`id_pedido`),
  KEY `id_sucursal` (`id_sucursal`),
  KEY `id_pago` (`id_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id_factura`, `id_pedido`, `id_pago`, `id_sucursal`, `fecha_factura`, `monto_total`) VALUES
(1, 1, 1, 1, '0000-00-00', 13),
(2, 2, 2, 2, '0000-00-00', 12),
(3, 3, 3, 2, '0000-00-00', 11),
(4, 4, 4, 1, '0000-00-00', 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE IF NOT EXISTS `ingrediente` (
  `id_ingrediente` int(11) NOT NULL AUTO_INCREMENT,
  `id_plato` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre_ingrediente` varchar(15) NOT NULL,
  `tipo_producto` enum('carnes','pescados','lacteos','verduras','otros') NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id_ingrediente`),
  KEY `id_plato` (`id_plato`,`id_sucursal`),
  KEY `id_sucursal` (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingrediente`
--

INSERT INTO `ingrediente` (`id_ingrediente`, `id_plato`, `id_sucursal`, `nombre_ingrediente`, `tipo_producto`, `stock`) VALUES
(1, 1, 1, 'piña', 'verduras', 200),
(2, 1, 2, 'piña', 'verduras', 100),
(3, 3, 1, 'queso', 'lacteos', 45),
(4, 3, 2, 'queso', 'lacteos', 0),
(5, 2, 1, 'filete', 'carnes', 12),
(6, 2, 2, 'filete', 'carnes', 30),
(7, 5, 2, 'anchoa', 'pescados', 60),
(8, 5, 1, 'anchoa', 'pescados', 56);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_pedido`
--

DROP TABLE IF EXISTS `linea_pedido`;
CREATE TABLE IF NOT EXISTS `linea_pedido` (
  `id_linea_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_plato` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `cantidad` int(2) UNSIGNED NOT NULL,
  `precio_total` float NOT NULL,
  PRIMARY KEY (`id_linea_pedido`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_plato` (`id_plato`),
  KEY `id_pedido` (`id_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `linea_pedido`
--

INSERT INTO `linea_pedido` (`id_linea_pedido`, `id_cliente`, `id_plato`, `id_pedido`, `cantidad`, `precio_total`) VALUES
(1, 1, 1, 1, 1, 1.5),
(2, 1, 2, 1, 1, 11.5),
(3, 3, 3, 2, 1, 3.25),
(4, 3, 4, 2, 1, 9.75),
(5, 2, 5, 3, 0, 8.5),
(6, 2, 6, 3, 1, 2.5),
(7, 1, 1, 4, 1, 1.5),
(8, 1, 2, 4, 1, 11.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE IF NOT EXISTS `pago` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `nº_tarjeta` int(20) NOT NULL,
  `fecha_pago` varchar(200) NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_pedido` (`id_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id_pago`, `id_pedido`, `nº_tarjeta`, `fecha_pago`) VALUES
(1, 1, 12345678, '0000-00-00'),
(2, 2, 87654321, '0000-00-00'),
(3, 3, 18273645, '0000-00-00'),
(4, 4, 98765432, '0000-00-00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `servidor` int(11) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `precio_pedido` float NOT NULL,
  `fecha` varchar(30) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `id_cliente` (`id_cliente`),
  KEY `servidor` (`servidor`),
  KEY `servidor_2` (`servidor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_cliente`, `servidor`, `direccion`, `precio_pedido`, `fecha`) VALUES
(1, 1, 1, 'C/ Germania, 2', 13, '15/05/2024'),
(2, 3, 2, 'C/ Jaen', 3.25, '16/05/2024'),
(3, 2, 7, 'C/ de Pepe', 11, '18/05/2024'),
(4, 1, 8, 'C/ Germania, 2', 13, '20/05/2024'),
(5, 1, 1, 'C/Agucate, 31', 56.7, '12/5/2024');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `DNI` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `edad` int(11) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(11) NOT NULL,
  PRIMARY KEY (`DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`DNI`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`) VALUES
('11221122G', 'Paco', 'Vela', 22, 'C/ Granada, 19', 684841940),
('12345677b', 'Fulanito', 'Ferrera', 22, 'C/Mendala', 954744444),
('12345677C', 'Tamara', 'Castillo', 50, 'C/ Los Jazmines, 74', 333449988),
('12345678A', 'Giovanni', 'Giorgio', 34, 'C/ Los Nardos 21', 475445566),
('12345679B', 'Armando ', 'Guerra', 44, 'C/ Las Rosas, 54', 475112233),
('12345689D', 'Helena', 'Troja', 21, 'C/ Las Jacarandas', 654224488),
('12356789L', 'Mari Trini', 'Sáchez', 61, 'C/Pintor López, 11', 789665432),
('22332233N', 'Luna', 'Ortiz', 25, 'C/ Germania, 2', 724592104),
('32445412M', 'José', 'Martínez', 33, 'C/Andalucía, 23', 789665432),
('33443344P', 'Pepe', 'Alonso', 31, 'C/ de Pepe, Nºde Pepe\'', 642142905),
('44554455K', '\'Manuel', 'Cruz', 18, 'C/ Jaen', 673903217),
('47556172S', 'Ana', 'Torres', 32, 'C/Pintor Ocaña, 123', 678991234),
('65223121B', 'Antonio', 'García', 54, 'C/Cruz Roja, 87', 64221132),
('75908712N', 'Alba', 'Estevez', 45, 'C/Luis Montoto, 2', 654123989);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plato`
--

DROP TABLE IF EXISTS `plato`;
CREATE TABLE IF NOT EXISTS `plato` (
  `id_plato` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_plato` varchar(20) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `precio_plato` double NOT NULL,
  `tipo_plato` enum('Bebidas','Postres','Plato Principal','Plato Secundario') NOT NULL,
  PRIMARY KEY (`id_plato`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plato`
--

INSERT INTO `plato` (`id_plato`, `nombre_plato`, `descripcion`, `precio_plato`, `tipo_plato`) VALUES
(1, 'Zumo de Piña', 'Refrescante zumo hecho con piña natural', 1.5, 'Bebidas'),
(2, 'Entrecot', 'Entrecot recién cazado del campo', 11.5, 'Plato Principal'),
(3, 'Tarta de Queso', 'Suave y deliciosa Tarta de Queso', 3.25, 'Postres'),
(4, 'Cochinillo Asado', 'Un plato tradicional de Segovia, con carne de cerd', 9.75, 'Plato Principal'),
(5, 'Ensalada de Anchoas', 'Acompañada de anchoas y frutos secos', 8.5, 'Plato Secundario'),
(6, 'Flan de Huevo', 'Un postre cremoso y clásico', 2.5, 'Postres');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id_reserva` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre_reserva` varchar(30) NOT NULL,
  `fecha_pago` date NOT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_sucursal` (`id_sucursal`),
  KEY `id_cliente_2` (`id_cliente`,`id_sucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

DROP TABLE IF EXISTS `sucursales`;
CREATE TABLE IF NOT EXISTS `sucursales` (
  `id_sucursal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_sucursal` varchar(20) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` int(9) NOT NULL,
  `email` varchar(100) NOT NULL,
  `horario` date NOT NULL,
  PRIMARY KEY (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`id_sucursal`, `nombre_sucursal`, `direccion`, `telefono`, `email`, `horario`) VALUES
(1, 'FarFarNorte', 'C/Flor, 41', 675443218, 'farfarNorte@gmail.com', '2024-03-12'),
(2, 'FarFarSur', 'C/Amapola, 5', 625663855, 'farFarWestSur@hotmail.com', '2024-05-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
CREATE TABLE IF NOT EXISTS `trabajador` (
  `id_trabajador` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `salario` double NOT NULL,
  `cargo` enum('Jefe','Chef','Camarero','Repartidor') NOT NULL,
  PRIMARY KEY (`id_trabajador`),
  KEY `dni` (`dni`,`id_sucursal`),
  KEY `id_sucursal` (`id_sucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`id_trabajador`, `dni`, `id_sucursal`, `salario`, `cargo`) VALUES
(1, '32445412M', 1, 987, 'Camarero'),
(2, '47556172S', 1, 1100, 'Repartidor'),
(3, '65223121B', 1, 1324, 'Chef'),
(4, '75908712N', 1, 1200, 'Jefe'),
(5, '12345678A', 2, 4000, 'Jefe'),
(6, '12345679B', 2, 1900, 'Chef'),
(7, '12345677C', 2, 1450, 'Camarero'),
(8, '12345689D', 2, 1200, 'Repartidor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(10) NOT NULL AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `contraseña` char(20) NOT NULL,
  `permisos` int(10) NOT NULL,
  `nombre_usuario` varchar(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `dni`, `contraseña`, `permisos`, `nombre_usuario`) VALUES
(4, '12345677b', 'contraseña', 1, 'Fulano'),
(5, '12345677C', 'contraseña', 2, 'Taca'),
(6, '12345678A', 'contraseña', 3, 'Giorgo'),
(7, '12345679B', 'contraseña', 2, 'Armandito'),
(8, '12345689D', 'contraseña', 2, 'La de Troja'),
(9, '22332233N', 'contraseña', 1, 'Luna'),
(10, '32445412M', 'contraseña', 2, 'Jose'),
(11, '33443344P', 'contraseña', 1, 'Pepe'),
(12, '44554455K', 'contraseña', 1, 'Manolo'),
(13, '47556172S', 'contraseña', 2, 'Ana'),
(14, '65223121B', 'contraseña', 2, 'Antonio'),
(15, '75908712N', 'contraseña', 3, 'Alba '),
(16, '12345677b', 'contraseña', 1, 'Fulanito');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `persona` (`DNI`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id_pago`),
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);

--
-- Filtros para la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD CONSTRAINT `ingrediente_ibfk_1` FOREIGN KEY (`id_plato`) REFERENCES `plato` (`id_plato`),
  ADD CONSTRAINT `ingrediente_ibfk_2` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursales` (`id_sucursal`);

--
-- Filtros para la tabla `linea_pedido`
--
ALTER TABLE `linea_pedido`
  ADD CONSTRAINT `lineaPedido_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lineaPedido_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lineaPedido_plato` FOREIGN KEY (`id_plato`) REFERENCES `plato` (`id_plato`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `pago_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sercidor_trabajador` FOREIGN KEY (`servidor`) REFERENCES `trabajador` (`id_trabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
