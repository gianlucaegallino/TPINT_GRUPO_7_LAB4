CREATE DATABASE  IF NOT EXISTS `bdbancoliberacion` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bdbancoliberacion`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bdbancoliberacion
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `cuil` varchar(20) NOT NULL,
  `sexo_id` bigint(20) DEFAULT NULL,
  `nacionalidad_id` bigint(20) DEFAULT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion_id` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(255) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `estado` tinyint(1) DEFAULT '1',
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `cuil` (`cuil`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`),
  UNIQUE KEY `dni` (`dni`),
  KEY `sexo_id` (`sexo_id`),
  KEY `nacionalidad_id` (`nacionalidad_id`),
  KEY `direccion_id` (`direccion_id`),
  KEY `fk_cliente_usuario` (`id_usuario`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`sexo_id`) REFERENCES `sexo` (`id`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`nacionalidad_id`) REFERENCES `nacionalidad` (`id`),
  CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`ID_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Lucas','Perez','12345678','20-12345678-9',1,1,'1990-01-01','1','Lucas.perez@example.com','123456789',1,2),(2,'Norma','Normal','11122244','20-12121212-5',2,2,'1992-02-02','Sandoval 395','salasala@sala.com','111122222',1,3),(3,'hola','1113345','13123312','20-1113345-0',1,1,'2001-11-11','1','holaolita@gmail.com','01147232391',1,10),(6,'hola','olita','11231321','20-11231321-0',2,2,'2024-11-11','la ferrere','pepito.juan@example.com','01147232391',1,15),(8,'Ivan','Ramirez','1112223345','19-1112223345-9',1,2,'2004-12-30','holaa1','ivancito@example.com','113465231',0,4),(9,'dadsda','adsadadasd','123444','2313133123',1,1,'2024-11-05','direccion1','adsda33sdasdas','12312312',0,14),(10,'juan','papa','46279374','20462793741',1,1,'2004-12-30','sdsda1','juanpaa@gmail.com','1149470329',0,17),(11,'gian','prueba','11233246','20-112332469',2,1,'2005-12-03','callecita123','gianlu@gmail.com','1232313311',1,NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas` (
  `cliente_id` bigint(20) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tipo_cuenta_id` bigint(20) DEFAULT NULL,
  `numero_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `cbu` varchar(22) NOT NULL,
  `saldo` decimal(15,2) NOT NULL DEFAULT '10000.00',
  `estado` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`numero_cuenta`),
  UNIQUE KEY `numero_cuenta` (`numero_cuenta`),
  UNIQUE KEY `cbu` (`cbu`),
  KEY `tipo_cuenta_id` (`tipo_cuenta_id`),
  KEY `fk_cliente_id` (`cliente_id`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`tipo_cuenta_id`) REFERENCES `tipo_cuenta` (`id`),
  CONSTRAINT `fk_cliente_id` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (2,'2024-11-17 03:00:00',1,1,'1111111111111111111111',2933365.75,1),(2,'2024-11-13 03:00:00',1,4,'2222222222222222222222',5004679.50,1),(9,'2024-11-11 03:00:00',1,5,'13123111111111',30000.00,1),(9,'2024-11-23 03:00:00',2,6,'3211323131',40000.00,1),(9,'2004-12-30 03:00:00',1,8,'123123132123',35000.00,1),(2,'2024-11-11 03:00:00',1,9,'3333333333333333333333',9998906.75,1),(1,'2024-12-03 03:00:00',1,10,'2113331233111',8940.26,1);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuotas`
--

DROP TABLE IF EXISTS `cuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuotas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prestamo_id` bigint(20) NOT NULL,
  `numero_cuota` int(11) NOT NULL,
  `fecha_pago` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `monto` decimal(15,2) NOT NULL,
  `pagada` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `prestamo_id` (`prestamo_id`),
  CONSTRAINT `cuotas_ibfk_1` FOREIGN KEY (`prestamo_id`) REFERENCES `prestamos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `direccion` varchar(100) NOT NULL,
  `id_localidad` int(11) NOT NULL,
  PRIMARY KEY (`direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `provincia_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `provincia_id` (`provincia_id`),
  CONSTRAINT `localidad_ibfk_1` FOREIGN KEY (`provincia_id`) REFERENCES `provincia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,'Capital Federal',1),(2,'Cordoba Capital',2);
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimientos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuenta_id` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `detalle` varchar(255) NOT NULL,
  `importe` decimal(15,2) NOT NULL,
  `tipo_movimiento` enum('Alta de cuenta','Alta de prestamo','Pago de prestamo','Transferencia Positiva','Transferencia Negativa') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cuenta_id` (`cuenta_id`),
  CONSTRAINT `fk_movimientos_cuenta_id` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`numero_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (2,10,'2024-12-04 03:00:00','Alta de cuenta10',10000.00,'Alta de cuenta'),(3,10,'2024-12-05 03:00:00','Pago de 1 cuota(s) de deuda 9',1059.74,'Pago de prestamo'),(4,1,'2024-12-05 03:00:00','Envio a cuenta 2222222222222222222222',5000.00,'Transferencia Negativa'),(5,4,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',5000.00,'Transferencia Positiva'),(6,1,'2024-12-05 03:00:00','Pago de 1 cuota(s) de deuda 12',8864.00,'Pago de prestamo'),(7,4,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',500.50,'Transferencia Negativa'),(8,9,'2024-12-05 03:00:00','Recibo desde cuenta 2222222222222222222222',500.50,'Transferencia Positiva'),(9,9,'2024-12-05 03:00:00','Envio a cuenta 1111111111111111111111',2000.75,'Transferencia Negativa'),(10,1,'2024-12-05 03:00:00','Recibo desde cuenta 3333333333333333333333',2000.75,'Transferencia Positiva'),(11,4,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',20.00,'Transferencia Negativa'),(12,9,'2024-12-05 03:00:00','Recibo desde cuenta 2222222222222222222222',20.00,'Transferencia Positiva'),(13,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',30.00,'Transferencia Negativa'),(14,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',30.00,'Transferencia Positiva'),(15,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',40.00,'Transferencia Negativa'),(16,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',40.00,'Transferencia Positiva'),(17,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',50.00,'Transferencia Negativa'),(18,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',50.00,'Transferencia Positiva'),(19,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',60.00,'Transferencia Negativa'),(20,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',60.00,'Transferencia Positiva'),(21,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',70.00,'Transferencia Negativa'),(22,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',70.00,'Transferencia Positiva'),(23,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',80.00,'Transferencia Negativa'),(24,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',80.00,'Transferencia Positiva'),(25,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',56.50,'Transferencia Negativa'),(26,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',56.50,'Transferencia Positiva'),(27,1,'2024-12-05 03:00:00','Envio a cuenta 2222222222222222222222',200.00,'Transferencia Negativa'),(28,4,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',200.00,'Transferencia Positiva'),(29,1,'2024-12-05 03:00:00','Pago de 3 cuota(s) de deuda 12',26592.00,'Pago de prestamo'),(30,1,'2024-12-05 03:00:00','Envio a cuenta 3333333333333333333333',1000.50,'Transferencia Negativa'),(31,9,'2024-12-05 03:00:00','Recibo desde cuenta 1111111111111111111111',1000.50,'Transferencia Positiva'),(32,1,'2024-12-05 03:00:00','Pago de 3 cuota(s) de deuda 12',26592.00,'Pago de prestamo');
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nacionalidad`
--

DROP TABLE IF EXISTS `nacionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nacionalidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nacionalidad`
--

LOCK TABLES `nacionalidad` WRITE;
/*!40000 ALTER TABLE `nacionalidad` DISABLE KEYS */;
INSERT INTO `nacionalidad` VALUES (1,'Argentina'),(2,'Mexicana');
/*!40000 ALTER TABLE `nacionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint(20) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `importe_pedido` decimal(15,2) NOT NULL,
  `estado` enum('pendiente','aprobado','rechazado') NOT NULL DEFAULT 'pendiente',
  `interes_anual` decimal(5,2) NOT NULL,
  `importe_con_intereses` decimal(15,2) NOT NULL,
  `plazo_meses` int(11) NOT NULL,
  `monto_mensual` decimal(15,2) NOT NULL,
  `cbu_cuenta` varchar(22) NOT NULL,
  `pagos_restantes` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `cbufk_idx` (`cbu_cuenta`),
  CONSTRAINT `cbufk` FOREIGN KEY (`cbu_cuenta`) REFERENCES `cuentas` (`cbu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (9,1,'2024-12-05 03:00:00',999.00,'aprobado',72.96,1059.74,1,1059.74,'2113331233111','0'),(10,2,'2024-12-05 03:00:00',10000.00,'aprobado',72.96,17296.00,12,1441.33,'1111111111111111111111','12'),(11,2,'2024-12-05 03:00:00',30000.00,'pendiente',72.96,57360.00,15,3824.00,'1111111111111111111111','15'),(12,2,'2024-12-05 03:00:00',80000.00,'aprobado',72.96,177280.00,20,8864.00,'1111111111111111111111','13'),(13,2,'2024-12-05 03:00:00',50000.00,'rechazado',72.96,110800.00,20,5540.00,'1111111111111111111111','20');
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'Buenos Aires'),(2,'Cordoba');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sexo`
--

DROP TABLE IF EXISTS `sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sexo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sexo`
--

LOCK TABLES `sexo` WRITE;
/*!40000 ALTER TABLE `sexo` DISABLE KEYS */;
INSERT INTO `sexo` VALUES (1,'Masculino'),(2,'Femenino');
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cuenta`
--

DROP TABLE IF EXISTS `tipo_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cuenta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cuenta`
--

LOCK TABLES `tipo_cuenta` WRITE;
/*!40000 ALTER TABLE `tipo_cuenta` DISABLE KEYS */;
INSERT INTO `tipo_cuenta` VALUES (1,'Caja de Ahorro'),(2,'Cuenta Corriente');
/*!40000 ALTER TABLE `tipo_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` enum('cliente','administrador') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` VALUES (1,'cliente'),(2,'administrador');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `tipo_usuario` int(11) NOT NULL,
  PRIMARY KEY (`ID_Usuario`),
  UNIQUE KEY `username` (`username`),
  KEY `tipo_usuario` (`tipo_usuario`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`tipo_usuario`) REFERENCES `tipo_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','admin',2),(2,'user','user',1),(3,'normanormal','test',1),(4,'user3','user3',1),(10,'sussybaka','1234',1),(14,'parra','parra',1),(15,'juan','juan',1),(16,'pablo','123',1),(17,'pruebafinal','pepe',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-06 12:03:43
