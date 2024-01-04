-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: koreapizza
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_num` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `user_passwd` text NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_regdate` date NOT NULL,
  `user_enddate` date NOT NULL,
  `user_st` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'koreait1111','Asas0923!!','koreait1111@naver.com','이길동','2023-12-18','2023-12-18','00'),(3,'koreait11111','Koreait1111!!','koreait11111@naver.com','삼길동','2023-12-21','2023-12-21','00'),(4,'koreait2222','Koreait1111!!','koreait2222@naver.com','오길동','2023-12-21','2023-12-21','00'),(5,'seungmin8787','Koreait11!!','seungmin8787@naver.com','김승민','2023-12-27','2023-12-27','00'),(6,'hong4456','Hong1234!!','hong4456@gmail.com','박주원','2023-12-27','2023-12-27','00'),(7,'siu8989','Siu1234!!','siu8989@daum.net','김시우','2023-12-27','2023-12-27','00'),(8,'gun0011','Gun0011@@','gun2020@naver.com','신건우','2023-12-27','2023-12-27','00'),(9,'seyeon1234','Seyeon6611!!','seyeon1234@gmail.com','박서연','2023-12-27','2023-12-27','00'),(10,'jihu2222','Jihu6677!!','jihu2222@naver.com','김지후','2023-12-27','2023-12-27','00'),(11,'who1234','Who1234!!','who1234@naver.com','박건후','2023-12-27','2023-12-27','00'),(12,'admin1234','Admin1234!!','admin1234@naver.com','관리자','2024-01-04','2024-01-04','50');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-04 16:27:04
