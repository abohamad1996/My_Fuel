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
-- Table structure for table `ratesrequest`
--

DROP TABLE IF EXISTS `ratesrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratesrequest` (
  `FuelType` varchar(45) NOT NULL,
  `price` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratesrequest`
--

LOCK TABLES `ratesrequest` WRITE;
/*!40000 ALTER TABLE `ratesrequest` DISABLE KEYS */;
INSERT INTO `ratesrequest` VALUES ('Gasoline 95','7',1),('Diesel fuel','8',1),('Gasoline 95','1',1),('Diesel fuel','2',1),('Scooters fuel','3',1),('Home Heating fuel','4',1),('Scooters fuel','20',1),('Home Heating fuel','18',1),('Gasoline 95','18',1),('Gasoline 95','10',1),('Diesel fuel','15',1),('Scooters fuel','20',1),('Home Heating fuel','25',1),('Gasoline 95','40',1),('Gasoline 95','5.5',1),('Gasoline 95','2',1),('Diesel fuel','22',1),('Diesel fuel','2',1),('Scooters fuel','3',1),('Scooters fuel','23',1),('Home Heating fuel','10',1),('Diesel fuel','5',1),('Diesel fuel','7',1),('Gasoline 95','1',1),('Diesel fuel','3',1),('Gasoline 95','33',1),('Scooters fuel','13',1),('Home Heating fuel','2',1),('Scooters fuel','2',1),('Diesel fuel','4',1),('Diesel fuel','2',1),('Gasoline 95','5.4',1),('Diesel fuel','2',1),('Gasoline 95','1',1),('Scooters fuel','22',1),('Diesel fuel','5',1),('Gasoline 95','9',1),('Scooters fuel','22',1),('Diesel fuel','12',1),('Diesel fuel','15',1),('Scooters fuel','33',1),('Home Heating fuel','11',1),('Home Heating fuel','23',1),('Diesel fuel','3',1),('Gasoline 95','5.5',1),('Diesel fuel','6.2',1),('Scooters fuel','3.5',1),('Home Heating fuel','4.5',1),('Home Heating fuel','10',1),('Gasoline 95','5',1),('Diesel fuel','6.3',1),('Gasoline 95','6.3',1),('Gasoline 95','2.5',1),('Diesel fuel','4',1),('Gasoline 95','5.6',1),('Diesel fuel','7.2',1),('Scooters fuel','1.5',1),('Gasoline 95','5',1),('Diesel fuel','6',1),('Scooters fuel','7',1),('Home Heating fuel','9',1),('Gasoline 95','4',1),('Diesel fuel','6',1),('Scooters fuel','5',1),('Home Heating fuel','8',1),('Gasoline 95','5',1),('Diesel fuel','6',1),('Diesel fuel','6',1),('Scooters fuel','4',1),('Home Heating fuel','4',1),('Gasoline 95','1',1),('Diesel fuel','2',1),('Scooters fuel','3',1),('Home Heating fuel','4',1),('Gasoline 95','4',1),('Gasoline 95','5.4',1),('Gasoline 95','5',1);
/*!40000 ALTER TABLE `ratesrequest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 11:53:47
