-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: my_fuel
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orderconfirmation`
--

DROP TABLE IF EXISTS `orderconfirmation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderconfirmation` (
  `OrderNumber` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `OrderStatus` varchar(45) DEFAULT NULL,
  `StationName` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `ManagerID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderconfirmation`
--

LOCK TABLES `orderconfirmation` WRITE;
/*!40000 ALTER TABLE `orderconfirmation` DISABLE KEYS */;
INSERT INTO `orderconfirmation` VALUES (3,'Diesel fuel','4900.0','Seen','Yellow Station','Nazareth','2020-6-16','319008066'),(4,'Gasoline 95','4600.0','Seen','Yellow Station','Haifa','2020-6-16','2'),(5,'Gasoline 95','4900.0','Seen','Yellow Station','Nazareth','2020-6-16','319008066'),(6,'Diesel fuel','4850.0','Seen','Yellow Station','Nazareth','2020-6-16','319008066'),(7,'Scooters fuel','4100.0','Seen','Paz Station','Der-Alasad','2020-6-16','207075912'),(8,'Gasoline 95','4900.0','Seen','Yellow Station','Nazareth','2020-6-16','319008066'),(9,'Gasoline 95','4800.0','Seen','Yellow Station','Nazareth','2020-6-16','319008066'),(10,'Diesel fuel','4700.0','Seen','Paz Station','Der-Alasad','2020-6-16','207075912'),(11,'Gasoline 95','4700.0','Seen','Yellow Station','Haifa','2020-6-16','2'),(12,'Gasoline 95','4800.0','Seen','Yellow Station','Nazareth','2020-6-17','319008066');
/*!40000 ALTER TABLE `orderconfirmation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 11:53:43
