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
-- Table structure for table `refueling`
--

DROP TABLE IF EXISTS `refueling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refueling` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `OwnerID` varchar(45) DEFAULT NULL,
  `carnumber` varchar(45) DEFAULT NULL,
  `gasstation` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `gastype` varchar(45) DEFAULT NULL,
  `rate` varchar(45) DEFAULT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `pump` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refueling`
--

LOCK TABLES `refueling` WRITE;
/*!40000 ALTER TABLE `refueling` DISABLE KEYS */;
INSERT INTO `refueling` VALUES (1,NULL,'9458865','Yellow Station','Nazareth','Diesel fuel','1.6761599999999999','100','167.61599999999999','2020-6-15','3'),(2,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','100','518.4','2020-6-15','1'),(3,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','300','1555.2','2020-6-15','1'),(4,'318615499','987654321','Yellow Station','Haifa','Gasoline 95','4.6656','200','933.1200000000001','2020-6-15','2'),(5,'208864504','7575754','Paz Station','Der-Alasad','Scooters fuel','2.592','300.5','778.8960000000001','2020-6-15','2'),(6,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','250','1296.0','2020-6-15','1'),(7,'318615499','9458865','Yellow Station','Nazareth','Diesel fuel','1.6761599999999999','500','838.0799999999999','2020-6-16','3'),(8,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','600','3110.4','2020-6-16','1'),(9,'318615499','987654321','Yellow Station','Haifa','Gasoline 95','4.6656','100','466.56000000000006','2020-6-16','2'),(10,'318615499','9458865','Yellow Station','Haifa','Diesel fuel','1.6761599999999999','200','335.23199999999997','2020-6-16','3'),(11,'318615499','9458865','Yellow Station','Nazareth','Diesel fuel','1.6761599999999999','200','335.23199999999997','2020-6-16','3'),(12,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','500','2592.0','2020-6-16','1'),(13,'318615499','987654321','Yellow Station','Haifa','Gasoline 95','4.6656','450','2099.52','2020-6-16','2'),(14,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','500','2332.8','2020-6-16','2'),(15,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','500','2332.8','2020-6-16','2'),(16,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','200','1036.8','2020-6-16','1'),(17,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','50','259.2','2020-6-16','1'),(18,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','2500','12960.0','2020-6-16','1'),(19,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','100','518.4','2020-6-16','1'),(20,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','300','1399.68','2020-6-16','2'),(21,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','200','933.1200000000001','2020-6-16','2'),(22,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','900','4665.6','2020-6-16','1'),(23,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','200','1036.8','2020-6-16','1'),(24,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','300','1555.2','2020-6-16','1'),(25,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','200','1036.8','2020-6-16','1'),(26,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','300','1399.68','2020-6-16','2'),(27,'318615499','9458865','Yellow Station','Haifa','Diesel fuel','1.6761599999999999','4199.5','7039.03392','2020-6-16','3'),(28,'318615499','9458865','Yellow Station','Haifa','Diesel fuel','1.6761599999999999','100','167.61599999999999','2020-6-16','3'),(29,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','300','1555.2','2020-6-16','1'),(30,'318615499','9458865','Yellow Station','Nazareth','Diesel fuel','1.6761599999999999','100','167.61599999999999','2020-6-16','3'),(31,'318615499','987654321','Yellow Station','Haifa','Gasoline 95','4.6656','500','2332.8','2020-6-16','2'),(32,'318615499','987654321','Yellow Station','Haifa','Gasoline 95','4.6656','100','466.56000000000006','2020-6-16','2'),(33,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','300','1399.68','2020-6-16','2'),(34,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','200','933.1200000000001','2020-6-16','2'),(35,'318615499','9458865','Yellow Station','Nazareth','Diesel fuel','1.6761599999999999','4500','7542.719999999999','2020-6-16','3'),(36,'318615499','9458865','Yellow Station','Nazareth','Diesel fuel','1.6761599999999999','250','419.03999999999996','2020-6-16','3'),(37,'208864504','7575754','Paz Station','Der-Alasad','Scooters fuel','2.592','1200','3110.4','2020-6-16','2'),(38,'208864504','7575754','Paz Station','Der-Alasad','Scooters fuel','2.592','200','518.4','2020-6-16','2'),(39,'208864504','7575754','Paz Station','Der-Alasad','Scooters fuel','2.592','100','259.2','2020-6-16','2'),(40,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','700','3265.92','2020-6-16','2'),(41,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','200','933.1200000000001','2020-6-16','2'),(42,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','4600','23846.4','2020-6-16','1'),(43,'318615499','123456789','Yellow Station','Nazareth','Gasoline 95','5.184','100','518.4','2020-6-16','1'),(44,'208864504','01011475','Paz Station','Der-Alasad','Diesel fuel','1.728','3100','5356.8','2020-6-16','1'),(45,'208864504','01011475','Paz Station','Der-Alasad','Diesel fuel','1.728','100','172.8','2020-6-16','1'),(46,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','500','2592.0','2020-6-16','1'),(47,'318615499','123456789','Yellow Station','Haifa','Gasoline 95','5.184','200','1036.8','2020-6-16','1'),(48,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','4500','20995.2','2020-6-17','2'),(49,'318615499','987654321','Yellow Station','Nazareth','Gasoline 95','4.6656','100','466.56000000000006','2020-6-17','2');
/*!40000 ALTER TABLE `refueling` ENABLE KEYS */;
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
