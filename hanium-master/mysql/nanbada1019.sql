-- MariaDB dump 10.17  Distrib 10.4.8-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: nanbada
-- ------------------------------------------------------
-- Server version	10.4.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `administrator_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `alert_altert_id` int(11) NOT NULL,
  PRIMARY KEY (`administrator_id`),
  KEY `alert_alert_id` (`alert_altert_id`),
  CONSTRAINT `alert_alert_id` FOREIGN KEY (`alert_altert_id`) REFERENCES `alert` (`alert_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert`
--

DROP TABLE IF EXISTS `alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert` (
  `alert_id` int(11) NOT NULL,
  `administrator` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `alert` varchar(45) NOT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert`
--

LOCK TABLES `alert` WRITE;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_loc`
--

DROP TABLE IF EXISTS `customer_loc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_loc` (
  `name` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_loc`
--

LOCK TABLES `customer_loc` WRITE;
/*!40000 ALTER TABLE `customer_loc` DISABLE KEYS */;
INSERT INTO `customer_loc` VALUES ('a','1','2019-10-19 13:12:31'),('aa','1','2019-10-19 18:30:18'),('aa','1','2019-10-19 18:43:35'),('aa','1','2019-10-19 18:49:16'),('aa','1','2019-10-19 18:50:09'),('aa','1','2019-10-19 18:50:26'),('aa','1','2019-10-19 18:51:47'),('aa','1','2019-10-19 21:31:21'),('aa','3','2019-10-19 21:31:42'),('8d8a5579c0214279','5','2019-10-19 21:43:29'),('8d8a5579c0214279','5','2019-10-19 21:43:32'),('8d8a5579c0214279','5','2019-10-19 21:43:35'),('8d8a5579c0214279','5','2019-10-19 21:43:38');
/*!40000 ALTER TABLE `customer_loc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `idcustomers_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `location_IoT` int(11) NOT NULL,
  PRIMARY KEY (`idcustomers_id`),
  KEY `location_iot` (`location_IoT`),
  CONSTRAINT `location_iot` FOREIGN KEY (`location_IoT`) REFERENCES `location_iot` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'aa','3','aa@',2),(2,'8d8a5579c0214279','5','aa@@',1);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_iot`
--

DROP TABLE IF EXISTS `location_iot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_iot` (
  `idx` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `area` varchar(45) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_iot`
--

LOCK TABLES `location_iot` WRITE;
/*!40000 ALTER TABLE `location_iot` DISABLE KEYS */;
INSERT INTO `location_iot` VALUES (1,'Nanbada1','1'),(2,'Nanbada2','2'),(3,'Nanbada3','3'),(4,'Nanbada4','4'),(5,'KT_GiGA_2G_78b6','5');
/*!40000 ALTER TABLE `location_iot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-19 21:45:53
