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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` text,
  `Firstname` text,
  `Lastname` text,
  `Email` text,
  `Username` text,
  `Userpassword` text,
  `UserRank` text,
  `ClientType` text,
  `Status` int DEFAULT NULL,
  `Image` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('208453811','Mohamed','Abo Hamad','mohamed.abha96@gmail.com','abohamad','123','Marketing Manager','',0,_binary '1231231'),('319008066','Wajeh','Otman','wajeh.otman01@gmail.com','wajeh','123','Station Manager','',0,_binary '21312312'),('312342041','Bshara','Bsharat','Bshara.b94@gmail.com','bshara','123','Marketing Representative','',0,_binary '123'),('318615499','Ahmad','Agbria','A.igbarya97@gmail.com','ahmad','123','Client','Private Client',0,_binary '0'),('207075912','Mahmoud','Odeh','Odeh.mahmood3@gmail.com','mahmoud','123','Station Manager','Private Client',0,_binary '0'),('208864504','Mohamed','Swaed','Mohamed-19@outlook.co.il','mohamed','123','Client','Private Client',0,_binary '0'),('205204206','Momen','Abo Hamad','momen@gmail.com','momen','123','Network Manager','',0,_binary '0'),('200500600','amer','amaar','asda@gmail.com','amer','123','Representative Transport','Private Client',0,_binary '0'),('1','123','123','123','123','123','Station Manager','Private Client',0,_binary '0'),('208209200','Salem','Sa','salem@gmail.com','salem','123','Supplier','Private Client',0,_binary '0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17 11:53:48
