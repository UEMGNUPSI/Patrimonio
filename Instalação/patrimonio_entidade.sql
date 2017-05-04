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
-- Table structure for table `entidade`
--

DROP TABLE IF EXISTS `entidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cnpj` varchar(45) NOT NULL,
  `contato` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidade`
--

LOCK TABLES `entidade` WRITE;
/*!40000 ALTER TABLE `entidade` DISABLE KEYS */;
INSERT INTO `entidade` VALUES (1,'UEMG','sem CNPJ','sem contato'),(2,'FESF','sem CNPJ','sem contato'),(3,'Prefeitura','sem CNPJ','sem contato'),(4,'PROP','sem CNPJ','sem contato'),(5,'FRA','sem CNPJ','sem contato'),(6,'CMF','sem CNPJ','sem contato'),(7,'PMF','sem CNPJ','sem contato'),(8,'Poder Judiciário','sem CNPJ','sem contato'),(9,'FUNDAMEC','sem CNPJ','sem contato'),(10,'Camera Mun. de Frutal','sem CNPJ','sem contato'),(11,'FEST','sem CNPJ','sem contato'),(12,'UFMG','sem CNPJ','sem contato'),(13,'USP','sem CNPJ','sem contato'),(14,'Recursos Terceiros','sem CNPJ','sem contato'),(15,'MGS','sem CNPJ','sem contato'),(16,'Secre. Est. Des. Regional','sem CNPJ','sem contato'),(17,'Prof. Eduardo','sem CNPJ','sem contato'),(18,'A conferir','sem CNPJ','sem contato');
/*!40000 ALTER TABLE `entidade` ENABLE KEYS */;
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
