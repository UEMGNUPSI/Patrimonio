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
-- Table structure for table `subtipo`
--

DROP TABLE IF EXISTS `subtipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subtipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `descricao` (`descricao`),
  KEY `id_tipo` (`id_tipo`),
  CONSTRAINT `subtipo_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtipo`
--

LOCK TABLES `subtipo` WRITE;
/*!40000 ALTER TABLE `subtipo` DISABLE KEYS */;
INSERT INTO `subtipo` VALUES (1,'Carteira',1),(2,'Mesa',1),(3,'Longarina',1),(4,'Cadeira',1),(5,'Gaveteiro',1),(6,'Quadro',1),(7,'Tripé',1),(8,'Pedestal',1),(9,'Poltrona',1),(10,'Cortina',1),(11,'Caminho para estabilizador',1),(12,'Sofá',1),(13,'Urna',1),(14,'Porta revista',1),(15,'Armário',1),(16,'Baia divisória',1),(17,'Lixeira',1),(18,'Prateleira',1),(19,'Rack',1),(20,'Geladeira',1),(21,'Painel',1),(22,'Suporte fundo foto',1),(23,'Varal',1),(24,'Balcão',1),(25,'Bancada',1),(26,'Ventilador',2),(27,'Telefone',2),(28,'Caixa de som',2),(29,'Ar condicionado',2),(30,'Filtro de linha',2),(31,'TV',2),(32,'Chuveiro',2),(33,'Receptor',2),(34,'Microsystem',2),(35,'Videocassete',2),(36,'DVD',2),(37,'Gravador de áudio',2),(38,'Gravador de vídeo',2),(39,'Câmera',2),(40,'Radiofusão',2),(41,'Compact disc',2),(42,'Soft box',2),(43,'Microfone',2),(44,'Gravador de dvd',2),(45,'Switcher',2),(46,'Iluminação',2),(47,'Operador de série',2),(48,'Mesa de som',2),(49,'Home theather',2),(50,'Tocha de iluminação',2),(51,'Fogão ',2),(52,'Frigobar',2),(53,'Estabilizador',3),(54,'Projetor',3),(55,'Modem',3),(56,'Placas',3),(57,'Computador',3),(58,'Impressora',3),(59,'Monitor',3),(60,'Notebook',3),(61,'Teclado',3),(62,'Mouse',3),(63,'CPU',3),(64,'Scanner',3),(65,'HD externo',3),(66,'Balança',4),(67,'Aparelho',4),(68,'Manta aquecedora',4),(69,'Agitador',4),(70,'Peças',4),(71,'Calorímetro',4),(72,'Ponto de fusão',4),(73,'Incubadora',4),(74,'Restrômetro',4),(75,'Condotivetro',4),(76,'Peneira ganulométrica',4),(77,'Carrinho',4),(78,'PHmetro',4),(79,'Barrilete',4),(80,'Autoclave',4),(81,'Estufa',4),(82,'Mufla',4),(83,'Chapa aquecedora',4),(84,'Jartest',4),(85,'Aquecedor',4),(86,'Acedematro',4),(87,'Suporte para tubos',4),(88,'Mixer',4),(89,'Dessecador',4),(90,'Fotometria ',4),(91,'Dexibelimetro',4),(92,'Oxímetro',4),(93,'Sonda',4),(94,'Multímetro',4),(95,'Luxímetro',4),(96,'Bloco digestor',4),(97,'Banho maria',4),(98,'Contador de colonias',4),(99,'Camara de germinação',4),(100,'Capela',4),(101,'Centrifuga',4),(102,'Destilador espextrofotometro',4),(103,'Microscópio',4),(104,'Estereoscópio',4),(105,'Lupa',4),(106,'Fonte',4),(107,'Dinamômetro',4),(108,'Celm',4),(109,'Moinho',4),(110,'Tacho',4),(111,'Escorredor',4),(112,'Deionizador',4),(113,'Grelme',4),(114,'Coletor',4),(115,'Eletroforese',4),(116,'Bomba',4),(117,'Medidor de intensidade',4),(118,'kit portabilidade',4),(119,'Medidor de condutividade',4),(120,'Contador',4),(121,'Conjunto hidrotatico',4),(122,'Maleta',4),(123,'Transformador',4),(124,'Caneca',4),(125,'Bateria',4),(126,'Dilatador ',4),(127,'Penetrômetro',4),(128,'Trilho de ar',4),(129,'Extrator de lipídio',4),(130,'Alambique',4),(131,'Capela de exaustão',4),(132,'Seladora',4),(133,'Vortex',4),(134,'Criscópio',4),(135,'Banqueta',1),(136,'Caixa Expositora',1),(137,'Central PABX',2),(138,'Estação Telemarketing',3),(139,'Bebedouro',1),(140,'Cadeira de Rodas',1),(141,'Lousa Digital',2),(142,'Microondas',3),(143,'Liquidificador',3),(144,'Escada',1);
/*!40000 ALTER TABLE `subtipo` ENABLE KEYS */;
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
