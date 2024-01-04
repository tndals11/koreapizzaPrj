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
-- Table structure for table `ask`
--

DROP TABLE IF EXISTS `ask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ask` (
  `id` int NOT NULL AUTO_INCREMENT,
  `option_code` char(2) NOT NULL,
  `user_num` int NOT NULL,
  `writer` varchar(10) DEFAULT NULL,
  `subject` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `regdate` date DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  `savedFileName` varchar(255) DEFAULT NULL,
  `savedFilePathName` varchar(255) DEFAULT NULL,
  `savedFileSize` bigint DEFAULT NULL,
  `folderName` varchar(10) DEFAULT NULL,
  `ext` varchar(20) DEFAULT NULL,
  `grp` int DEFAULT NULL,
  `seq` int DEFAULT NULL,
  `depth` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `option_code` (`option_code`),
  KEY `user_num` (`user_num`),
  CONSTRAINT `ask_ibfk_1` FOREIGN KEY (`option_code`) REFERENCES `options` (`option_code`),
  CONSTRAINT `ask_ibfk_2` FOREIGN KEY (`user_num`) REFERENCES `users` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ask`
--

LOCK TABLES `ask` WRITE;
/*!40000 ALTER TABLE `ask` DISABLE KEYS */;
INSERT INTO `ask` VALUES (5,'2',11,NULL,'주문관련해서 문의드립니다','주문이 누락된거 같아요 확인부탁드립니다.','2023-12-27',NULL,NULL,NULL,0,NULL,NULL,1,1,1),(6,'1',7,NULL,'회원관련 문의','회원탈퇴시 보존기간이 얼마인지 알고싶어서 문의드립니다.','2023-12-27',NULL,NULL,NULL,0,NULL,NULL,2,1,1),(8,'2',11,'[관리자]','','문의관려 답변입니다.','2023-12-27',NULL,NULL,NULL,0,NULL,NULL,1,2,2);
/*!40000 ALTER TABLE `ask` ENABLE KEYS */;
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
