-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.38-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema timelapse
--

CREATE DATABASE IF NOT EXISTS timelapse;
USE timelapse;

--
-- Definition of table `tld_categoria`
--

DROP TABLE IF EXISTS `tld_categoria`;
CREATE TABLE `tld_categoria` (
  `oid` varchar(32) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `usuarioOID` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `usuarioOID` (`usuarioOID`),
  CONSTRAINT `usuarioOID` FOREIGN KEY (`usuarioOID`) REFERENCES `tld_usuario` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tld_categoria`
--

/*!40000 ALTER TABLE `tld_categoria` DISABLE KEYS */;
INSERT INTO `tld_categoria` (`oid`,`nombre`,`descripcion`,`usuarioOID`) VALUES 
 ('4028804024d39a180124d39fa8050002','Cosas gatito','Es bastante importante','4028804124bef2cf0124bef49ef70003'),
 ('4028804024f875af0124f8a6ca720001','Proyecto','proyecto','W87760759'),
 ('4028804024f875af0124f9bbcdb20009','Primera Categoría','Primera categoría creada en la aplicación','4028804024f2604e0124f26cce480001'),
 ('402880412415d86e012415dc32c00004','Viajes','Viajes caseros','W87760759'),
 ('402880412527020801252709af1d0004','asdf','asdf','402880412522afac012522b2e5200001'),
 ('40288185228e753b01228e9d38970006','Proyecteo','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','4028818620ddb98b0120ddba12720001');
/*!40000 ALTER TABLE `tld_categoria` ENABLE KEYS */;


--
-- Definition of table `tld_dia_no_laborable`
--

DROP TABLE IF EXISTS `tld_dia_no_laborable`;
CREATE TABLE `tld_dia_no_laborable` (
  `oid` varchar(32) NOT NULL,
  `fecha` date NOT NULL,
  `tipoDiaOID` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `tipoDiaOID` (`tipoDiaOID`),
  CONSTRAINT `tipoDiaOID` FOREIGN KEY (`tipoDiaOID`) REFERENCES `tlp_tipo_dia_no_laborable` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tld_dia_no_laborable`
--

/*!40000 ALTER TABLE `tld_dia_no_laborable` DISABLE KEYS */;
/*!40000 ALTER TABLE `tld_dia_no_laborable` ENABLE KEYS */;


--
-- Definition of table `tld_grupo`
--

DROP TABLE IF EXISTS `tld_grupo`;
CREATE TABLE `tld_grupo` (
  `oid` varchar(32) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `usuarioCreadorOID` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_tld_grupo_tld_usuario` (`usuarioCreadorOID`),
  CONSTRAINT `fk_tld_grupo_tld_usuario` FOREIGN KEY (`usuarioCreadorOID`) REFERENCES `tld_usuario` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tld_grupo`
--

/*!40000 ALTER TABLE `tld_grupo` DISABLE KEYS */;
INSERT INTO `tld_grupo` (`oid`,`nombre`,`descripcion`,`usuarioCreadorOID`) VALUES 
 ('4028804124684e7f01246851195d0001','PFC','Proyecto de Fin de Carrera','W87760759'),
 ('40288041252702080125270973c70003','asdf','asdf','402880412522afac012522b2e5200001'),
 ('402881a222b396d60122b39cf5860002','yo yo mismo y roberto','...','4028818620ddb98b0120ddba12720001'),
 ('402881a224b5a0f90124b5ac55f00005','Familia','Grupo para tareas en familia','W87760759'),
 ('402881a224b5c24f0124b5c8552f0001','Grupo de Pruebas','Grupo de Pruebas','W87760759');
/*!40000 ALTER TABLE `tld_grupo` ENABLE KEYS */;


--
-- Definition of table `tld_periodicidad`
--

DROP TABLE IF EXISTS `tld_periodicidad`;
CREATE TABLE `tld_periodicidad` (
  `oid` varchar(32) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date DEFAULT NULL,
  `cada` int(11) NOT NULL,
  `omitirDiasNoLaborables` int(11) NOT NULL,
  `unidadDeRepeticionOID` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `unidadDeRepeticionOID` (`unidadDeRepeticionOID`),
  CONSTRAINT `unidadDeRepeticionOID` FOREIGN KEY (`unidadDeRepeticionOID`) REFERENCES `tlp_unidad_repeticion` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tld_periodicidad`
--

/*!40000 ALTER TABLE `tld_periodicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `tld_periodicidad` ENABLE KEYS */;


--
-- Definition of table `tld_tarea`
--

DROP TABLE IF EXISTS `tld_tarea`;
CREATE TABLE `tld_tarea` (
  `oid` varchar(32) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaRealizacion` date DEFAULT NULL,
  `horaRealizacion` time DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `horaInicio` time DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `localizacionAsociada` varchar(500) DEFAULT NULL,
  `autorrealizable` int(11) NOT NULL,
  `importancia` int(11) NOT NULL,
  `usuarioCreadorOID` varchar(32) DEFAULT NULL,
  `usuarioAsociadoOID` varchar(32) DEFAULT NULL,
  `grupoAsociadoOID` varchar(32) DEFAULT NULL,
  `categoriaAsociadaOID` varchar(32) DEFAULT NULL,
  `tareaPadreOID` varchar(32) DEFAULT NULL,
  `periodicidadOID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `grupoAsociadoOID` (`grupoAsociadoOID`),
  KEY `categoriaAsociadaOID` (`categoriaAsociadaOID`),
  KEY `usuarioCreadorOID` (`usuarioCreadorOID`),
  KEY `usuarioAsociadoOID` (`usuarioAsociadoOID`),
  KEY `tareaPadreOID` (`tareaPadreOID`),
  KEY `periodicidadOID` (`periodicidadOID`),
  CONSTRAINT `categoriaAsociadaOID` FOREIGN KEY (`categoriaAsociadaOID`) REFERENCES `tld_categoria` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `grupoAsociadoOID` FOREIGN KEY (`grupoAsociadoOID`) REFERENCES `tld_grupo` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `periodicidadOID` FOREIGN KEY (`periodicidadOID`) REFERENCES `tld_periodicidad` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tareaPadreOID` FOREIGN KEY (`tareaPadreOID`) REFERENCES `tld_tarea` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuarioAsociadoOID` FOREIGN KEY (`usuarioAsociadoOID`) REFERENCES `tld_usuario` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuarioCreadorOID` FOREIGN KEY (`usuarioCreadorOID`) REFERENCES `tld_usuario` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tld_tarea`
--

/*!40000 ALTER TABLE `tld_tarea` DISABLE KEYS */;
INSERT INTO `tld_tarea` (`oid`,`nombre`,`descripcion`,`fechaCreacion`,`fechaRealizacion`,`horaRealizacion`,`fechaInicio`,`horaInicio`,`fechaFin`,`horaFin`,`localizacionAsociada`,`autorrealizable`,`importancia`,`usuarioCreadorOID`,`usuarioAsociadoOID`,`grupoAsociadoOID`,`categoriaAsociadaOID`,`tareaPadreOID`,`periodicidadOID`) VALUES 
 ('4028804022fa5c3a0122fa64e53c0001','asdf','asdf','2009-08-08 16:22:53','2009-10-14','21:08:21','2009-08-09','14:00:00','2009-08-09','14:45:00','',0,1,'W87760759',NULL,NULL,NULL,NULL,NULL),
 ('40288040233e8b6301233e8c344d0001','Abuelo','Abuelo del todo','2009-08-21 22:00:58',NULL,NULL,'2009-09-30','09:00:00','5000-08-28',NULL,'',0,5,'W87760759',NULL,NULL,NULL,NULL,NULL),
 ('4028804024d39a180124d39f3db70001','Comida de Piticli','Comprar la comida al gato','2009-11-08 12:47:58',NULL,NULL,'2009-10-31',NULL,'2009-10-31',NULL,'Sevilla',1,10,'4028804124bef2cf0124bef49ef70003',NULL,NULL,'4028804024d39a180124d39fa8050002',NULL,NULL),
 ('4028804024d39a180124d3a85d290003','Copia Comida de Piticli','Comprar la comida al gato','2009-11-08 12:57:56',NULL,NULL,'2009-11-01',NULL,'2009-11-01',NULL,'Sevilla',1,10,'4028804124bef2cf0124bef49ef70003',NULL,NULL,NULL,NULL,NULL),
 ('4028804024dacab00124dacd09d10001','Copia Tarea 2 con daniel','Segunda tarea con daniel','2009-11-09 22:15:20','2009-11-09','22:15:31','5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'W87760759',NULL,'402881a224b5a0f90124b5ac55f00005',NULL,NULL,NULL),
 ('4028804024f875af0124f8aa68f00002','Primera Tarea','Hacer el manual de ayuda','2009-11-15 17:26:07',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,10,'4028804024f2604e0124f26cce480001',NULL,NULL,'4028804024f875af0124f9bbcdb20009',NULL,NULL),
 ('4028804024f875af0124f8ce8eb80005','Segunda Tarea','Segunda tarea creada en timeLapse','2009-11-15 18:05:36',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'4028804024f2604e0124f26cce480001',NULL,NULL,NULL,NULL,NULL),
 ('4028804024f875af0124f8ce96660006','Copia Segunda Tarea','Segunda tarea creada en timeLapse','2009-11-15 18:05:38',NULL,NULL,'2009-11-17',NULL,'5000-08-28',NULL,'',0,1,'4028804024f2604e0124f26cce480001',NULL,NULL,NULL,NULL,NULL),
 ('4028804024f875af0124f8cea8780007','Copia Primera Tarea','Hacer el manual de ayuda','2009-11-15 18:05:43',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,10,'4028804024f2604e0124f26cce480001',NULL,NULL,NULL,NULL,NULL),
 ('402880412415d86e012415dae42f0003','Irnos al puerto','En coche y conduciendo er dani','2009-10-02 17:25:17','2009-10-14','21:08:20','5000-08-28',NULL,'5000-08-28',NULL,'',0,6,'W87760759',NULL,NULL,'402880412415d86e012415dc32c00004',NULL,NULL),
 ('402880412464124e01246417900a0001','Tarea PFC 1','Creada por roberto','2009-10-17 22:01:56',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'En mi casa',1,1,'4028818620ddb98b0120ddba12720001',NULL,NULL,NULL,'402881a224b5fed60124b60042ce0001',NULL),
 ('402880412468410c01246846036b0001','Padre','Tarea Padre','2009-10-18 17:31:09',NULL,NULL,'2009-09-30','09:00:00','5000-08-28',NULL,'',0,1,'W87760759',NULL,NULL,NULL,'40288040233e8b6301233e8c344d0001',NULL),
 ('402880412468410c012468460fb20002','Hija','Abuelo del todo','2009-10-18 17:31:12',NULL,NULL,'2009-09-30','09:00:00','5000-08-28',NULL,'',0,1,'W87760759',NULL,NULL,NULL,'402880412468410c01246846036b0001',NULL),
 ('40288041248306420124831634960001','Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:28:40',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',1,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006','402881a224b5fed60124b60042ce0001',NULL),
 ('40288041248306420124831eae350002','Copia Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:37:56',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831eb4ba0003','Copia (1) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:37:58',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831eefc90004','Copia (2) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:13',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831efe6f0005','Copia (3) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:16',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f0d660006','Copia (4) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:20',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f16620007','Copia (5) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:23',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f1eda0008','Copia (6) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:25',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f2a1e0009','Copia (7) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:28',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f316f000a','Copia (8) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:29',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f39a4000b','Copia (9) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:32',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f41e8000c','Copia (10) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:34',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f4eb0000d','Copia (11) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:37',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f5a80000e','Copia (12) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:40',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f6457000f','Copia (13) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:43',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f6de10010','Copia (14) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:45',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f806d0011','Copia (15) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:50',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f8e2b0012','Copia (16) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:53',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831f972a0013','Copia (17) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:56',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831fa3020014','Copia (18) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:38:59',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124831ffc7f0015','Copia (19) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:21',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248320071f0016','Copia (20) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:24',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248320105e0017','Copia (21) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:27',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124832019870018','Copia (22) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:29',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248320236c0019','Copia (23) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:31',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483202edd001a','Copia (24) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:34',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483203aac001b','Copia (25) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:37',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248320486a001c','Copia (26) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:41',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483205269001d','Copia (27) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:43',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483205fd4001e','Copia (28) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:47',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483206a26001f','Copia (29) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:39:50',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483216a650020','Copia (30) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:40:55',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124832176e40021','Copia (31) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:40:58',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248321c3800022','Copia (32) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:41:18',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248321d2d70023','Copia (33) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:41:22',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248321deac0024','Copia (34) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:41:25',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124832264010025','Copia (35) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:41:59',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483226e200026','Copia (36) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:02',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483227a030027','Copia (37) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:05',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('40288041248306420124832285a30028','Copia (38) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:08',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322941b0029','Copia (39) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:11',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483229f2c002a','Copia (40) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:14',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322b798002b','Copia (41) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:20',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322c46d002c','Copia (42) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:24',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322cfff002d','Copia (43) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:27',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322dcf4002e','Copia (44) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:30',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322e8ff002f','Copia (45) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:33',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322f43a0030','Copia (46) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:36',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412483064201248322ffcb0031','Copia (47) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:39',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('4028804124830642012483230ad50032','Copia (48) Primera Tarea de Roberto','Cosas creadas para el proyecto de fin de carrera en el que actualmente estoy trabajando y terminando de hacer las cosas que me faltan. No me acuerdo cuantas líneas había que escribir aquí para llegar al limite por eso no paro de escribir, esto ya no tiene mucho sentido pero bueno yo sigo escribiendo todo lo rápido que puedo, sin pensar demasiado, perso seguir sigo....................................................................a...................................................a.............','2009-10-23 22:42:42',NULL,NULL,'2009-10-01','22:00:00','2009-10-25','23:00:00','En mi casa',0,4,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006',NULL,NULL),
 ('402880412485e92a012485fa104d0001','Nueva tarea para Roberto','Una descripción','2009-10-24 11:56:48',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'4028818620ddb98b0120ddba12720001',NULL,NULL,NULL,NULL,NULL),
 ('4028804124bafc800124baffdb6f0001','PFC copia 5','creado por roberto','2009-11-03 18:03:00',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'En mi casa',1,1,'4028818620ddb98b0120ddba12720001',NULL,NULL,NULL,NULL,NULL),
 ('4028804124bbb8050124bbc01eb90001','Copia PFC copia 3','por admin','2009-11-03 21:33:00',NULL,NULL,'2009-11-04',NULL,'2009-11-04',NULL,'En mi casa',0,1,'4028818620ddb98b0120ddba12720001',NULL,NULL,NULL,NULL,NULL),
 ('4028804124bbb8050124bbc8c4580003','Copia PFC 1!','PFC copia 1 creada por admin','2009-11-03 21:42:27',NULL,NULL,'2009-11-04',NULL,'2009-11-04',NULL,'En mi casa',0,1,'W87760759',NULL,'4028804124684e7f01246851195d0001','402880412415d86e012415dc32c00004','40288040233e8b6301233e8c344d0001',NULL),
 ('4028804124bef2cf0124bef3486f0001','Tarea con daniel','primera tarea','2009-11-04 12:27:45',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'W87760759',NULL,'402881a224b5a0f90124b5ac55f00005','402880412415d86e012415dc32c00004',NULL,NULL),
 ('4028804124bef2cf0124bef3a0cd0002','Tarea 2 con daniel','Segunda tarea con daniel','2009-11-04 12:28:07',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,10,'W87760759',NULL,'4028804124684e7f01246851195d0001',NULL,NULL,NULL),
 ('402880412526defb012526e18f2e0001','asdf','asdf','2009-11-24 16:48:54',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'W87760759',NULL,'4028804124684e7f01246851195d0001',NULL,NULL,NULL),
 ('402880412527020801252707e5ef0002','asdf','asdf','2009-11-24 17:30:46',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'4028818620ddb98b0120ddba12720001',NULL,'402881a222b396d60122b39cf5860002','40288185228e753b01228e9d38970006','402880412464124e01246417900a0001',NULL),
 ('40288041252702080125270a00e50005','primera','primera tarea','2009-11-24 17:33:04',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,1,'402880412522afac012522b2e5200001',NULL,NULL,NULL,NULL,NULL),
 ('40288041252702080125270a563e0006','hija','hija','2009-11-24 17:33:26',NULL,NULL,'2009-11-04','16:00:00','2009-11-18','16:00:00','',0,1,'402880412522afac012522b2e5200001','402880412522afac012522b2e5200001','40288041252702080125270973c70003','402880412527020801252709af1d0004','40288041252702080125270a00e50005',NULL),
 ('4028818524aabe430124aac0ff950001','PFC copia creada por admin','creada por admin','2009-10-31 14:20:25','2009-11-09','21:56:52','2009-11-04',NULL,'2009-11-04',NULL,'En mi casa',0,1,'W87760759',NULL,'4028804124684e7f01246851195d0001',NULL,'40288040233e8b6301233e8c344d0001',NULL),
 ('4028818524aabe430124aac114450002','PFC 1!','PFC copia 1 creada por admin','2009-10-31 14:20:30','2009-11-09','22:06:23','2009-11-04',NULL,'2009-11-04',NULL,'En mi casa',0,1,'W87760759',NULL,'4028804124684e7f01246851195d0001','402880412415d86e012415dc32c00004',NULL,NULL),
 ('4028818524aabe430124aac1373b0003','PFC copia 2 creada','por admin','2009-10-31 14:20:39',NULL,NULL,'2009-11-04',NULL,'2009-11-04',NULL,'En mi casa',0,1,'W87760759',NULL,'4028804124684e7f01246851195d0001','402880412415d86e012415dc32c00004','40288040233e8b6301233e8c344d0001',NULL),
 ('4028818524aabe430124aac140a60004','Tarea PFC','Finalizar el PFC','2009-10-31 14:20:42',NULL,NULL,'2009-11-04',NULL,'2009-11-24',NULL,'En mi casa',0,10,'W87760759','4028804024f2604e0124f26cce480001','4028804124684e7f01246851195d0001','402880412415d86e012415dc32c00004','40288040233e8b6301233e8c344d0001',NULL),
 ('402881a124b10afd0124b1104cad0001','PFC copia 4','creada por roberto','2009-11-01 19:44:45',NULL,NULL,'2009-11-04',NULL,'2009-11-04',NULL,'En mi casa',0,1,'4028818620ddb98b0120ddba12720001',NULL,NULL,NULL,NULL,NULL),
 ('402881a224b5fed60124b60042ce0001','Altíiisima','Tarea Con gran prioridad','2009-11-02 18:45:20',NULL,NULL,'5000-08-28',NULL,'5000-08-28',NULL,'',0,9,'4028818620ddb98b0120ddba12720001',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tld_tarea` ENABLE KEYS */;


--
-- Definition of table `tld_usuario`
--

DROP TABLE IF EXISTS `tld_usuario`;
CREATE TABLE `tld_usuario` (
  `oid` varchar(32) NOT NULL,
  `nombreUsuario` varchar(50) NOT NULL,
  `nombrePropio` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) DEFAULT NULL,
  `contrasenna` varchar(100) NOT NULL,
  `rolOID` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_tld_usuario_rol` (`rolOID`),
  CONSTRAINT `fk_tld_usuario_rol` FOREIGN KEY (`rolOID`) REFERENCES `tlp_rol` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tld_usuario`
--

/*!40000 ALTER TABLE `tld_usuario` DISABLE KEYS */;
INSERT INTO `tld_usuario` (`oid`,`nombreUsuario`,`nombrePropio`,`apellido1`,`apellido2`,`contrasenna`,`rolOID`) VALUES 
 ('4028804024dfb82f0124dfb9cac00001','nuria','Nuria','Pérez','Morillo','Á¿ÁˆÛ¥�&�dŠ æÊŒŽ','402881a31b93de7f011b939724661909'),
 ('4028804024f2604e0124f26cce480001','usuario de pruebas','Usuario','Pruebas','','î.ÃÌfB{´\"‰D•‚\"¨','402881a31b93de7f011b939724661909'),
 ('4028804124bef2cf0124bef49ef70003','daniel','Daniel','Vázquez','González','ªGø!\\o0 ÜÛ*6©ôŽ','402881a31b93de7f011b939724661909'),
 ('402880412522afac012522b2e5200001','nuevo','Nuevo Usuario','nuevo','nuevo','âl/íö³(4äÞ“ùÈ¶D','402881a31b93de7f011b939724661909'),
 ('4028818620ddb98b0120ddba12720001','roberto','roberto','roberto','roberto','Á¿ÁˆÛ¥�&�dŠ æÊŒŽ','402881a31b93de7f011b939724661909'),
 ('402881a224b4f9280124b4fa323f0001','roberto2','Roberto','Vázquez','González','Á¿ÁˆÛ¥�&�dŠ æÊŒŽ','402881a31b93de7f011b939724661909'),
 ('402881a224b5a0f90124b5ab4ed10002','otro','otro','otro','otro','kÍN+{×YsÕ\"ö=º¢','402881a31b93de7f011b939724661909'),
 ('W87760759','admin','Usuario Administrador','-',NULL,'!#/)zW¥§C‰JJ€Ã','402881a31b93de7f011b93e724660008');
/*!40000 ALTER TABLE `tld_usuario` ENABLE KEYS */;


--
-- Definition of table `tlp_accion`
--

DROP TABLE IF EXISTS `tlp_accion`;
CREATE TABLE `tlp_accion` (
  `OID` varchar(32) NOT NULL,
  `funcionalidadOID` varchar(32) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `path` varchar(100) NOT NULL,
  `orden` int(10) unsigned NOT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK_dAcciones_1` (`funcionalidadOID`),
  CONSTRAINT `FK_dAcciones_1` FOREIGN KEY (`funcionalidadOID`) REFERENCES `tlp_funcionalidad` (`OID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlp_accion`
--

/*!40000 ALTER TABLE `tlp_accion` DISABLE KEYS */;
INSERT INTO `tlp_accion` (`OID`,`funcionalidadOID`,`nombre`,`descripcion`,`path`,`orden`) VALUES 
 ('4028804022dbfa9f0122dc02d6790002','4028804022dbfa9f0122dc01f4010001','bbdd.acciones.adminTareas.nom','bbdd.acciones.adminTareas.desc','tareas.adminTareas',1),
 ('4028804024d4920a0124d4a922830001','4028804022dbfa9f0122dc01f4010001','bbdd.acciones.busquedaTareas.nom','bbdd.acciones.busquedaTareas.desc','tareas.buscarTareas',2),
 ('402880412485e92a01248619b7770008','402880412485e92a01248603d5d10003','bbdd.acciones.vinTareaTarea.nom','bbdd.acciones.vinTareaTarea.desc','tareas.vincularTareaTarea',1),
 ('402880412485e92a0124861aac240009','402880412485e92a01248603d5d10003','bbdd.acciones.desTareaTarea.nom','bbdd.acciones.desTareaTarea.desc','tareas.desvincularTareaTarea',2),
 ('402880412485e92a0124861e4a92000a','402880412485e92a0124860494ef0004','bbdd.acciones.vinTareaTiempo.nom','bbdd.acciones.vinTareaTiempo.desc','tiempo.vincularTareaTiempo',1),
 ('402880412485e92a0124861f9fa4000b','402880412485e92a0124860494ef0004','bbdd.acciones.desTareaTiempo.nom','bbdd.acciones.desTareaTiempo.desc','tiempo.desvincularTareaTiempo',2),
 ('402880412485e92a0124862151d7000c','402880412485e92a0124860512b50005','bbdd.acciones.vinTareaCategoria.nom','bbdd.acciones.vinTareaCategoria.desc','categorias.vincularTareaCategoria',1),
 ('402880412485e92a01248621b31d000d','402880412485e92a0124860512b50005','bbdd.acciones.desTareaCategoria.nom','bbdd.acciones.desTareaCategoria.desc','categorias.desvincularTareaCategoria',2),
 ('402880412485e92a01248629b6c1000e','402880412485e92a0124860942680006','bbdd.acciones.vinTareaGrupo.nom','bbdd.acciones.vinTareaGrupo.desc','grupos.vincularTareaGrupo',1),
 ('402880412485e92a0124862a2688000f','402880412485e92a0124860942680006','bbdd.acciones.desTareaGrupo.nom','bbdd.acciones.desTareaGrupo.desc','grupos.desvincularTareaGrupo',2),
 ('402880412485e92a0124862b083e0010','402880412485e92a01248609aaf40007','bbdd.acciones.vinUsuarioGrupo.nom','bbdd.acciones.vinUsuarioGrupo.desc','grupos.vincularUsuarioGrupo',1),
 ('402880412485e92a0124862b79cd0011','402880412485e92a01248609aaf40007','bbdd.acciones.desUsuarioGrupo.nom','bbdd.acciones.desUsuarioGrupo.desc','grupos.desvincularUsuarioGrupo',2),
 ('402880412492adb1012492bf2dc10002','402880412492adb1012492bcbf960001','bbdd.acciones.vinTareasUsuario.nom','bbdd.acciones.vinTareasUsuario.desc','grupos.vincularTareaUsuario',1),
 ('402880412492adb1012492bfc7bf0003','402880412492adb1012492bcbf960001','bbdd.acciones.desTareasUsuario.nom','bbdd.acciones.desTareasUsuario.desc','grupos.desvincularTareaUsuario',2),
 ('402880412492adb1012492c4b00a0005','402880412492adb1012492c2c8b10004','bbdd.acciones.visualizarCalen.nom','bbdd.acciones.visualizarCalen.desc','tiempo.visualizarCalendario',1),
 ('4028804124be942d0124be999da00002','4028804124be942d0124be96447a0001','bbdd.acciones.cambioPassword.nombre','bbdd.acciones.cambioPassword.descripcion','administracion.cambioPassword',1),
 ('40288185228e1f5901228e2478120003','40288185228e1f5901228e21b0f20001','bbdd.acciones.adminCategoria.nom','bbdd.acciones.adminCategoria.desc','categorias.adminCategorias',1),
 ('402881a31b93de7f011b93e6ec6c0005','402881a31b93de7f011b93e518660004','bbdd.acciones.adminObjetivo.nom','bbdd.acciones.adminObjetivo.desc','administracion.adminObjetivos',1),
 ('402881a31b93de7f011b93e6f26e0006','402881a31b93de7f011b93e518660004','bbdd.acciones.adminFuncion.nom','bbdd.acciones.adminFuncion.desc','administracion.adminFuncionalidades',2),
 ('402881a31b93de7f011b93e6fe950007','402881a31b93de7f011b93e518660004','bbdd.acciones.adminAcciones.nom','bbdd.acciones.adminAcciones.desc','administracion.adminAcciones',3),
 ('8ae482022130d2ac012130dd9d510006','8ae482022130d2ac012130dc41a50005','bbdd.acciones.vinRolAccion.nom','bbdd.acciones.vinRolAccion.desc','administracion.vincularRolAccion',1),
 ('ff80808121979a69012197a0a89e0002','8ae482022130d2ac012130dc41a50005','bbdd.acciones.desRolAccion.nom','bbdd.acciones.desRolAccion.desc','administracion.desvincularRolAccion',2),
 ('ff808081229397ee0122939c5c560002','ff808081229397ee0122939b54ed0001','bbdd.acciones.adminGrupos.nom','bbdd.acciones.adminGrupos.desc','grupos.adminGrupos',1);
/*!40000 ALTER TABLE `tlp_accion` ENABLE KEYS */;


--
-- Definition of table `tlp_funcionalidad`
--

DROP TABLE IF EXISTS `tlp_funcionalidad`;
CREATE TABLE `tlp_funcionalidad` (
  `OID` varchar(32) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `objetivoOID` varchar(32) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `orden` int(10) unsigned NOT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK_dfuncionalidad_1` (`objetivoOID`),
  CONSTRAINT `FK_dfuncionalidad_1` FOREIGN KEY (`objetivoOID`) REFERENCES `tlp_objetivo` (`OID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlp_funcionalidad`
--

/*!40000 ALTER TABLE `tlp_funcionalidad` DISABLE KEYS */;
INSERT INTO `tlp_funcionalidad` (`OID`,`nombre`,`objetivoOID`,`descripcion`,`orden`) VALUES 
 ('4028804022dbfa9f0122dc01f4010001','bbdd.funcionalidades.manTareas.nombre','ff8080811ec742ac011ec75d7bbb0002','bbdd.funcionalidades.manTareas.descripcion',1),
 ('402880412485e92a01248603d5d10003','bbdd.funcionalidades.asociaTareas.nombre','ff8080811ec742ac011ec75d7bbb0002','bbdd.funcionalidades.asociaTareas.descripcion',2),
 ('402880412485e92a0124860494ef0004','bbdd.funcionalidades.asociaTiempo.nombre','ff8080811ec742ac011ec75f35b40003','bbdd.funcionalidades.asociaTiempo.descripcion',2),
 ('402880412485e92a0124860512b50005','bbdd.funcionalidades.asociaCate.nombre','ff8080811ec742ac011ec76032080004','bbdd.funcionalidades.asociaCate.descripcion',2),
 ('402880412485e92a0124860942680006','bbdd.funcionalidades.asociaGruposTareas.nombr','ff8080811ec742ac011ec760af0d0005','bbdd.funcionalidades.asociaGruposTareas.descr',2),
 ('402880412485e92a01248609aaf40007','bbdd.funcionalidades.asociaGruposUsuarios.nom','ff8080811ec742ac011ec760af0d0005','bbdd.funcionalidades.asociaGruposUsuarios.des',3),
 ('402880412492adb1012492bcbf960001','bbdd.funcionalidades.asociaTareasUsuarios.nom','ff8080811ec742ac011ec760af0d0005','bbdd.funcionalidades.asociaTareasUsuarios.des',4),
 ('402880412492adb1012492c2c8b10004','bbdd.funcionalidades.gestCal.nombre','ff8080811ec742ac011ec75f35b40003','bbdd.funcionalidades.gestCal.descripcion',1),
 ('4028804124be942d0124be96447a0001','bbdd.funcionalidades.perfil.nombre','402881a31b93de7f011b93e1334f0003','bbdd.funcionalidades.perfil.descripcion',3),
 ('40288185228e1f5901228e21b0f20001','bbdd.funcionalidades.manCat.nombre','ff8080811ec742ac011ec76032080004','bbdd.funcionalidades.manCat.descripcion',1),
 ('402881a31b93de7f011b93e518660004','bbdd.funcionalidades.ofa.nombre','402881a31b93de7f011b93e1334f0003','bbdd.funcionalidades.ofa.descripcion',1),
 ('8ae482022130d2ac012130dc41a50005','bbdd.funcionalidades.usuarios.nombre','402881a31b93de7f011b93e1334f0003','bbdd.funcionalidades.usuarios.descripcion',2),
 ('ff808081229397ee0122939b54ed0001','bbdd.funcionalidades.manGrupos.nombre','ff8080811ec742ac011ec760af0d0005','bbdd.funcionalidades.manGrupos.descripcion',1);
/*!40000 ALTER TABLE `tlp_funcionalidad` ENABLE KEYS */;


--
-- Definition of table `tlp_objetivo`
--

DROP TABLE IF EXISTS `tlp_objetivo`;
CREATE TABLE `tlp_objetivo` (
  `OID` varchar(32) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `orden` int(10) unsigned NOT NULL,
  PRIMARY KEY (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlp_objetivo`
--

/*!40000 ALTER TABLE `tlp_objetivo` DISABLE KEYS */;
INSERT INTO `tlp_objetivo` (`OID`,`nombre`,`descripcion`,`orden`) VALUES 
 ('402881a31b93de7f011b93e1334f0003','bbdd.objetivos.administracion.nombre','bbdd.objetivos.administracion.descripcion',5),
 ('ff8080811ec742ac011ec75d7bbb0002','bbdd.objetivos.tareas.nombre','bbdd.objetivos.tareas.descripcion',1),
 ('ff8080811ec742ac011ec75f35b40003','bbdd.objetivos.tiempo.nombre','bbdd.objetivos.tiempo.descripcion',2),
 ('ff8080811ec742ac011ec76032080004','bbdd.objetivos.categorias.nombre','bbdd.objetivos.categorias.descripcion',3),
 ('ff8080811ec742ac011ec760af0d0005','bbdd.objetivos.grupos.nombre','bbdd.objetivos.grupos.descripcion',4);
/*!40000 ALTER TABLE `tlp_objetivo` ENABLE KEYS */;


--
-- Definition of table `tlp_rol`
--

DROP TABLE IF EXISTS `tlp_rol`;
CREATE TABLE `tlp_rol` (
  `oid` varchar(32) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `grado` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlp_rol`
--

/*!40000 ALTER TABLE `tlp_rol` DISABLE KEYS */;
INSERT INTO `tlp_rol` (`oid`,`nombre`,`descripcion`,`grado`) VALUES 
 ('402881a31b93de7f011b939724661909','bbdd.rol.usuario.nombre','bbdd.rol.usuario.descripcion',2),
 ('402881a31b93de7f011b93e724660008','bbdd.rol.administrador.nombre','bbdd.rol.administrador.descripcion',1);
/*!40000 ALTER TABLE `tlp_rol` ENABLE KEYS */;


--
-- Definition of table `tlp_tipo_dia_no_laborable`
--

DROP TABLE IF EXISTS `tlp_tipo_dia_no_laborable`;
CREATE TABLE `tlp_tipo_dia_no_laborable` (
  `oid` varchar(32) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlp_tipo_dia_no_laborable`
--

/*!40000 ALTER TABLE `tlp_tipo_dia_no_laborable` DISABLE KEYS */;
/*!40000 ALTER TABLE `tlp_tipo_dia_no_laborable` ENABLE KEYS */;


--
-- Definition of table `tlp_unidad_repeticion`
--

DROP TABLE IF EXISTS `tlp_unidad_repeticion`;
CREATE TABLE `tlp_unidad_repeticion` (
  `oid` varchar(32) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlp_unidad_repeticion`
--

/*!40000 ALTER TABLE `tlp_unidad_repeticion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tlp_unidad_repeticion` ENABLE KEYS */;


--
-- Definition of table `tlr_permiso`
--

DROP TABLE IF EXISTS `tlr_permiso`;
CREATE TABLE `tlr_permiso` (
  `oid` varchar(32) NOT NULL,
  `tld_accion_OID` varchar(32) NOT NULL,
  `tld_rol_oid` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_tld_accion_has_tld_rol_tld_accion` (`tld_accion_OID`),
  KEY `fk_tld_accion_has_tld_rol_tld_rol` (`tld_rol_oid`),
  CONSTRAINT `fk_tld_accion_has_tld_rol_tld_accion` FOREIGN KEY (`tld_accion_OID`) REFERENCES `tlp_accion` (`OID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tld_accion_has_tld_rol_tld_rol` FOREIGN KEY (`tld_rol_oid`) REFERENCES `tlp_rol` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlr_permiso`
--

/*!40000 ALTER TABLE `tlr_permiso` DISABLE KEYS */;
INSERT INTO `tlr_permiso` (`oid`,`tld_accion_OID`,`tld_rol_oid`) VALUES 
 ('4028804022dbfa9f0122dc031a370003','4028804022dbfa9f0122dc02d6790002','402881a31b93de7f011b93e724660008'),
 ('4028804022dc044c0122dc0531020001','4028804022dbfa9f0122dc02d6790002','402881a31b93de7f011b939724661909'),
 ('4028804024d4920a0124d4a953350002','4028804024d4920a0124d4a922830001','402881a31b93de7f011b93e724660008'),
 ('4028804024d4920a0124d4a971840003','4028804024d4920a0124d4a922830001','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862cf5a40012','402880412485e92a01248619b7770008','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50013','402880412485e92a0124862b083e0010','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50014','402880412485e92a0124861e4a92000a','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50015','402880412485e92a0124862151d7000c','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50016','402880412485e92a01248629b6c1000e','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50017','402880412485e92a0124862a2688000f','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50018','402880412485e92a01248621b31d000d','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a50019','402880412485e92a0124861f9fa4000b','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a6001a','402880412485e92a0124861aac240009','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862cf5a6001b','402880412485e92a0124862b79cd0011','402881a31b93de7f011b93e724660008'),
 ('402880412485e92a0124862ee21c001c','402880412485e92a01248619b7770008','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e001d','402880412485e92a0124861e4a92000a','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e001e','402880412485e92a0124862151d7000c','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e001f','402880412485e92a01248629b6c1000e','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e0020','402880412485e92a0124862b083e0010','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e0021','402880412485e92a0124862b79cd0011','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e0022','402880412485e92a0124862a2688000f','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e0023','402880412485e92a01248621b31d000d','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e0024','402880412485e92a0124861f9fa4000b','402881a31b93de7f011b939724661909'),
 ('402880412485e92a0124862ee21e0025','402880412485e92a0124861aac240009','402881a31b93de7f011b939724661909'),
 ('4028804124be942d0124be99e8660003','4028804124be942d0124be999da00002','402881a31b93de7f011b93e724660008'),
 ('4028804124becbfd0124becd639f0001','4028804124be942d0124be999da00002','402881a31b93de7f011b939724661909'),
 ('40288185228e1f5901228e253eb40005','40288185228e1f5901228e2478120003','402881a31b93de7f011b93e724660008'),
 ('4028818524abc3810124abda442f0002','402880412492adb1012492bf2dc10002','402881a31b93de7f011b93e724660008'),
 ('4028818524abc3810124abda44300003','402880412492adb1012492bfc7bf0003','402881a31b93de7f011b93e724660008'),
 ('402881a222b396d60122b39c88c50001','ff808081229397ee0122939c5c560002','402881a31b93de7f011b939724661909'),
 ('402881a224b5fed60124b601d4d10002','402880412492adb1012492bf2dc10002','402881a31b93de7f011b939724661909'),
 ('402881a224b5fed60124b601d4d20003','402880412492adb1012492bfc7bf0003','402881a31b93de7f011b939724661909'),
 ('8ae482022130d2ac012130d47acb0001','402881a31b93de7f011b93e6f26e0006','402881a31b93de7f011b93e724660008'),
 ('8ae482022130d2ac012130d4c9130002','402881a31b93de7f011b93e6fe950007','402881a31b93de7f011b93e724660008'),
 ('8ae482022130d2ac012130ddfdd60007','8ae482022130d2ac012130dd9d510006','402881a31b93de7f011b93e724660008'),
 ('8ae48c7322132b3f0122134106c90001','402881a31b93de7f011b93e6ec6c0005','402881a31b93de7f011b93e724660008'),
 ('ff8080812197a6bc012197abcf510002','ff80808121979a69012197a0a89e0002','402881a31b93de7f011b93e724660008'),
 ('ff808081228fe4ff01229015534f0007','40288185228e1f5901228e2478120003','402881a31b93de7f011b939724661909'),
 ('ff808081229397ee0122939c9e0e0003','ff808081229397ee0122939c5c560002','402881a31b93de7f011b93e724660008');
/*!40000 ALTER TABLE `tlr_permiso` ENABLE KEYS */;


--
-- Definition of table `tlr_usuario_grupo`
--

DROP TABLE IF EXISTS `tlr_usuario_grupo`;
CREATE TABLE `tlr_usuario_grupo` (
  `oid` varchar(32) NOT NULL,
  `usuarioOID` varchar(32) NOT NULL,
  `grupoOID` varchar(32) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `fk_dUsuario_has_dGrupo_dUsuario` (`usuarioOID`),
  KEY `fk_dUsuario_has_dGrupo_dGrupo` (`grupoOID`),
  CONSTRAINT `fk_dUsuario_has_dGrupo_dGrupo` FOREIGN KEY (`grupoOID`) REFERENCES `tld_grupo` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dUsuario_has_dGrupo_dUsuario` FOREIGN KEY (`usuarioOID`) REFERENCES `tld_usuario` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tlr_usuario_grupo`
--

/*!40000 ALTER TABLE `tlr_usuario_grupo` DISABLE KEYS */;
INSERT INTO `tlr_usuario_grupo` (`oid`,`usuarioOID`,`grupoOID`) VALUES 
 ('4028804024f875af0124f957b0ec0008','4028804024f2604e0124f26cce480001','4028804124684e7f01246851195d0001'),
 ('4028804124b965b70124b9668a800001','402881a224b5a0f90124b5ab4ed10002','4028804124684e7f01246851195d0001'),
 ('4028804124b965b70124b9668a860003','402881a224b4f9280124b4fa323f0001','4028804124684e7f01246851195d0001');
/*!40000 ALTER TABLE `tlr_usuario_grupo` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
