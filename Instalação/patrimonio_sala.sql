-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: patrimonio
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sala` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `id_piso` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_piso` (`id_piso`),
  CONSTRAINT `sala_ibfk_1` FOREIGN KEY (`id_piso`) REFERENCES `piso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,'Hall e Corredores',1),(2,'Anfiteatro',1),(3,'Secretaria',1),(4,'Sala 01',1),(5,'Sala 02',1),(6,'Sala 03 (Laboratório Info IV)',1),(7,'Sala 04',1),(8,'Sala 05',1),(9,'Sala 06 (Sala de Estudos)',1),(10,'Sala 07 (Diretório Central dos Estudantes)',1),(11,'Sala 08 (Laboratório Info III )',1),(12,'Sala 09 (Laboratório Info II )',1),(13,'Sala 10 (Laboratório Info  I )',1),(14,'Sala 11 (Laboratório de Geomática)',1),(15,'Sala 12(Biblioteca)',1),(16,'Copa',2),(17,'Diretoria',2),(18,'Coordenadorias',2),(19,'Sala de Professores',2),(20,'Sala 17 (Departamento de TI)',2),(21,'Psicologia',2),(22,'Pesquisa e Extensão',2),(23,'Administrativo - Sala de Reuniao',2),(24,'Administrativo - Recepção',2),(25,'Administrativo - Administrativo Sala 01',2),(26,'Administrativo - Administrativo Sala 02',2),(27,'Administrativo - Recepção Administrativo',2),(28,'Administrativo - Hall Secretaria',2),(29,'Administrativo - Recepção Patrimônio / Jurídico',2),(30,'Administrativo - Patrimônio',2),(31,'Administrativo - Estágio e Convênios',2),(32,'Sala 07 (Empresa JR.)',2),(33,'Sala 08',2),(34,'Sala 09',2),(35,'Sala 10',2),(36,'Sala 11',2),(37,'Sala 12',2),(38,'Sala 13',2),(39,'Sala 18 (Nupsi)',2),(40,'Sala 18 (Recepção Nupsi)',2),(41,'Atividades Complementares',3),(42,'Grupo de Pesquisas S.I.C',3),(43,'Laboratório de Práticas de Ensino (LAPEGEO)',3),(44,'Sala 20',3),(45,'Sala 21',3),(46,'Sala 22',3),(47,'Sala 23',3),(48,'Sala 24',3),(49,'Sala 25',3),(50,'Sala 26',3),(51,'Sala 27',3),(52,'Sala 28',3),(53,'Sala 29',3),(54,'Sala 30',3),(55,'CPD',4),(56,'Sala 02',4),(57,'Sala 03',4),(58,'Sala 04',4),(59,'Laboratório de Anatomia',4),(60,'Laboratório de Biologia',4),(61,'Laboratório de Microbiologia',4),(62,'Laboratório de Microscopia e Física',4),(63,'Eletrônica',4),(64,'Almoxerifado / Anatômia Lab.',4),(65,'Almoxerifado Lab.',4),(66,'Laboratório Fisico-Químico',4),(67,'Departamento de Ciências Humanas',5),(68,'Departamento de Ciências Exatas e da Terra',5),(69,'Departamento de Linguística, Letras, Comunicação e artes',5),(70,'Chefia de Departamento',5),(71,'Sala 01 (Laboratório de Aerofotogrametria e Sensoriamento Remoto)',5),(72,'Sala 02 (Laboratório de Estudos Geográficos)',5),(73,'Sala 07',5),(74,'Sala 08',5),(75,'Sala 09',5),(76,'Gabinete I - Thiago Torres',5),(77,'Gabinete I - Allynson Fujita',5),(78,'Gabinete I - Cristiane Azevedo',5),(79,'Gabinete I - Sala 05',5),(80,'Gabinete I - Sala 06',5),(81,'Gabinete I - Marli Graniel',5),(82,'Gabinete I - Sala 08',5),(83,'Gabinete I -Revista Gnose',5),(84,'Gabinete I - Corredor',5),(85,'Gabinete II - Sala 09',5),(86,'Gabinete II - Sala 10',5),(87,'Gabinete II - Sala 11',5),(88,'Gabinete II - Sala 12',5),(89,'Sala 14',5),(90,'Sala 15',5),(91,'Sala 16',5),(92,'CA',6),(93,'Estúdio',6),(94,'HIDROEX - Sala 1',7),(95,'HIDROEX - Sala 2',7),(96,'Núcleo Juridico - Hall',7),(97,'Núcleo Juridico - Sala de aula',7),(98,'Núcleo Juridico - Recepção',7),(99,'Núcleo Juridico - Sala PROCON',7),(100,'Núcleo Juridico - Cartório Modelo',7),(101,'Núcleo Juridico - Sala de Atendimento',7),(102,'Núcleo Juridico - Corredor',7),(103,'Núcleo Juridico - Sala de Alunos 2',7),(104,'Núcleo Juridico - Cozinha',7),(105,'Monitores EM Desuso',7),(106,' Coordenadoria de Administração',2),(107,' Assessoria de Comunicação',2),(108,' Coordenadoria  - MGS',2),(109,' Coordenadoria de Comunicação',2),(110,' Coordenadoria de Sucro e Alimentos',2),(111,' Coordenadoria de Sistemas de Informação',2),(112,' Coordenadoria de Direito',2);
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-06 16:27:21
