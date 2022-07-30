-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `second_name` varchar(45) DEFAULT NULL,
  `am` int NOT NULL,
  `course` bigint DEFAULT NULL,
  `f_exam` int DEFAULT NULL,
  `project` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `final_grade` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1vhx5mxp4n3g8ap0otynlp711` (`course`),
  CONSTRAINT `FK1vhx5mxp4n3g8ap0otynlp711` FOREIGN KEY (`course`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'George','Bantouvakis',4119,1,6,7,'george@gmail.com',6.3),(2,'Lazaros','Kosmidis',4085,1,7,6,'lazaros@gmail.com',6.7),(3,'Dimitris','Antoniou',4027,1,8,5,'dimitris@gmail.com',7.1),(4,'George','Bantouvakis',4119,2,7,5,'george@gmail.com',6),(5,'Lazaros','Kosmidis',4085,2,5,8,'lazaros@gmail.com',6.5),(6,'Dimitris','Antoniou',4027,2,10,10,'dimitris@gmail.com',10),(7,'George','Bantouvakis',4119,3,6,6,'george@gmail.com',6),(8,'Lazaros','Kosmidis',4085,3,8,8,'lazaros@gmail.com',8),(9,'Dimitris','Antoniou',4027,3,8,10,'dimitris@gmail.com',9.6),(10,'George','Bantouvakis',4119,4,6,9,'george@gmail.com',7.5),(11,'Lazaros','Kosmidis',4085,4,8,8,'lazaros@gmail.com',8),(12,'Dimitris','Antoniou',4027,4,8,8,'dimitris@gmail.com',8),(13,'George','Bantouvakis',4119,5,9,10,'george@gmail.com',9.8),(14,'Lazaros','Kosmidis',4085,5,7,5,'lazaros@gmail.com',5.4),(15,'Dimitris','Antoniou',4027,5,8,10,'dimitris@gmail.com',9.6),(16,'George','Bantouvakis',4119,6,9,10,'george@gmail.com',9.7),(17,'Lazaros','Kosmidis',4085,6,9,10,'lazaros@gmail.com',9.7),(18,'DImitris','Antoniou',4027,6,10,7,'dimitris@gmail.com',7.9);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 16:02:19
