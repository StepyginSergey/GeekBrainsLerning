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
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_put` int(11) NOT NULL,
  `user_id_to` int(11) NOT NULL,
  `content_id` int(11) NOT NULL,
  `like_received` tinyint(1) DEFAULT '0',
  `recall` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `likes_content_id` (`content_id`),
  KEY `likes_user_id_put` (`user_id_put`),
  KEY `likes_user_id_to` (`user_id_to`),
  CONSTRAINT `likes_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `likes_user_id_put` FOREIGN KEY (`user_id_put`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `likes_user_id_to` FOREIGN KEY (`user_id_to`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,1,2,7,1,0),(2,1,2,8,1,0),(3,1,2,9,1,0),(4,1,2,10,1,0),(5,1,2,11,1,0),(6,1,2,12,1,0),(7,2,1,1,1,0),(8,2,1,2,1,0),(9,2,1,3,1,0),(10,2,1,4,1,0),(11,2,1,5,1,0),(12,2,1,6,1,0),(13,3,1,1,1,0),(14,3,2,7,1,0),(15,3,2,9,1,0),(16,3,2,10,1,0),(17,3,2,11,1,0),(18,3,2,12,1,1),(19,4,1,1,1,0),(20,4,2,8,1,0),(21,4,2,9,1,0),(22,4,2,10,1,0),(23,4,2,11,1,0),(24,4,2,12,1,0);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
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
