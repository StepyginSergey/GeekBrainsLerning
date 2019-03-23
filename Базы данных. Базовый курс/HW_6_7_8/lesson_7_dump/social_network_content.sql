-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: social_network
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `content_data` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `content_user_id` (`user_id`),
  KEY `content_type_id` (`content_type_id`),
  CONSTRAINT `content_type_id` FOREIGN KEY (`content_type_id`) REFERENCES `content_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `content_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (1,1,1,'page'),(2,1,2,'coment'),(3,1,3,'image1'),(4,1,3,'image2'),(5,1,4,'video1'),(6,1,4,'video2'),(7,2,1,'page'),(8,2,2,'coment'),(9,2,3,'image1'),(10,2,3,'image2'),(11,2,4,'video1'),(12,2,4,'video2'),(13,3,1,'page'),(14,3,2,'coment'),(15,3,3,'image1'),(16,3,3,'image2'),(17,3,4,'video1'),(18,3,4,'video2'),(19,4,1,'page'),(20,4,2,'coment'),(21,4,3,'image1'),(22,4,3,'image2'),(23,4,4,'video1'),(24,4,4,'video2'),(25,5,1,'page'),(26,5,2,'coment'),(27,5,3,'image1'),(28,5,3,'image2'),(29,5,4,'video1'),(30,5,4,'video2'),(31,6,1,'page'),(32,6,2,'coment'),(33,6,3,'image1'),(34,6,3,'image2'),(35,6,4,'video1'),(36,6,4,'video2');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-17 16:59:38
