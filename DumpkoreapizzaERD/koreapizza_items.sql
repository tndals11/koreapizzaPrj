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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `category_code` varchar(10) NOT NULL,
  `item_name` varchar(20) NOT NULL,
  `item_price` int NOT NULL,
  `item_intro` text NOT NULL,
  `visit` int DEFAULT NULL,
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
  PRIMARY KEY (`item_id`),
  KEY `category_code` (`category_code`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`category_code`) REFERENCES `category` (`category_code`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (18,'1','치즈 스테이크 피자',24900,'치즈큐브와 스테이크가 올라간 풍성한 맛의 피자',0,'2023-12-27','치즈스테이크피자.jpg','33085486-aec9-4a1e-9f03-6ee1322d4fb0.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/33085486-aec9-4a1e-9f03-6ee1322d4fb0.jpg',404567,'20231227','.jpg',0,1,1),(19,'1','직화 바베큐 피자',27900,'불맛 가득한 직화 스테이크의 풍미를 고스란히 담은 피자\r\n\r\n',0,'2023-12-27','직화바베큐피자.jpg','918b40e2-493a-4faf-bde6-0ba1d258c1e3.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/918b40e2-493a-4faf-bde6-0ba1d258c1e3.jpg',415177,'20231227','.jpg',0,1,1),(20,'1','바질 크림치즈 피자',26900,'상큼한 바질크림과 크림치즈의 조합이 환상적인 피자\r\n\r\n',0,'2023-12-29','바질크림치즈피자.jpg','0a13f82c-1b70-4cf2-9a3c-8f58014d30ba.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/0a13f82c-1b70-4cf2-9a3c-8f58014d30ba.jpg',400342,'20231227','.jpg',0,1,1),(21,'1','에그 고구마 피자',26900,'부드러운 에그고구마무스와 달달한 마요소스의 조합이 환상적인 피자\r\n\r\n',0,'2023-12-27','에그고구마피자.jpg','ed667ad0-4584-42d2-8f28-14d235f671ea.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/ed667ad0-4584-42d2-8f28-14d235f671ea.jpg',359702,'20231227','.jpg',0,1,1),(22,'1','코리아 스폐셜 피자',26900,'4가지의 맛을 한 판에 즐길 수 있는 가성비 갑인 피자\r\n\r\n',0,'2023-12-27','코리아스페셜피자.jpg','726faa01-1440-48d5-81ca-6fcd3abd4949.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/726faa01-1440-48d5-81ca-6fcd3abd4949.jpg',347604,'20231227','.jpg',0,1,1),(23,'1','멕시칸 쉬림프 피자',26900,'살사소스와 맥앤치즈 그리고 할라피뇨의 이국적인 조합이 매력적인 피자\r\n\r\n',0,'2023-12-27','멕시칸쉬림프피자.jpg','a16f0924-5b3f-4a7d-9717-23d68c345809.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/a16f0924-5b3f-4a7d-9717-23d68c345809.jpg',385004,'20231227','.jpg',0,1,1),(24,'2','페퍼로니 피자',19900,'진한 풍미의 페퍼로니를 부드러운 체다치즈가 감싸안은 전통 피자\r\n\r\n',0,'2023-12-27','페퍼로니피자.jpg','b2f5180d-928c-4101-a8a3-3b08a1862a7e.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/b2f5180d-928c-4101-a8a3-3b08a1862a7e.jpg',362124,'20231227','.jpg',0,1,1),(25,'2','콤비네이션 피자',19900,'신선하고 다양한 토핑조합이 매력적인 클래식 피자\r\n\r\n',0,'2023-12-27','콤비네이션피자.jpg','135fd652-0bb3-4e40-a0f4-858189336b71.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/135fd652-0bb3-4e40-a0f4-858189336b71.jpg',371029,'20231227','.jpg',0,1,1),(26,'2','하와이안 피자',19900,'상큼한 파인애플과 고소한 치즈의 조합이 환상적인 피자\r\n\r\n',0,'2023-12-27','하와이안피자.jpg','042254e8-d70a-43a0-95a7-2f37aa7f10c4.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/042254e8-d70a-43a0-95a7-2f37aa7f10c4.jpg',303147,'20231227','.jpg',0,1,1),(27,'2','치즈 피자',19900,'담백하고 고소한 치즈가 넘칠듯이 풍부하게 담긴 피자\r\n\r\n',0,'2023-12-27','치즈피자.jpg','ea3a41bf-9629-475e-bfb2-b675d9f4651b.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/ea3a41bf-9629-475e-bfb2-b675d9f4651b.jpg',381412,'20231227','.jpg',0,1,1),(28,'2','할라피뇨 불고기 피자',19900,'육즙 가득한 불고기와 매콤한 할라피뇨의 조합이 끝내주는 피자\r\n\r\n',0,'2023-12-27','할라피뇨불고기피자.jpg','b8511f15-16ec-4199-b9f7-edf4a04f04b5.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/b8511f15-16ec-4199-b9f7-edf4a04f04b5.jpg',393501,'20231227','.jpg',0,1,1),(29,'2','포테이토 피자',19900,'풍성한 감자토핑 위에 고소한 마요소스를 뿌린 담백하고 부드러운 피자\r\n\r\n',0,'2023-12-27','포테이토피자.jpg','73c4f4e0-eeb0-4fac-bc0a-1d4559e53f6b.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/73c4f4e0-eeb0-4fac-bc0a-1d4559e53f6b.jpg',374291,'20231227','.jpg',0,1,1),(30,'3','미트소스 스파게티',8900,'',0,'2023-12-27','미트소스스파게티.jpg','4d8c20b2-a662-4308-8b48-d0f120192a6e.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/4d8c20b2-a662-4308-8b48-d0f120192a6e.jpg',246727,'20231227','.jpg',0,1,1),(31,'3','베이컨 까르보나라',8900,'',0,'2023-12-27','베이컨까르보나라.jpg','534935dc-003d-43ed-b575-b6bc8bf5bbe5.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/534935dc-003d-43ed-b575-b6bc8bf5bbe5.jpg',257081,'20231227','.jpg',0,1,1),(32,'3','치즈소스 감자튀김',6900,'',0,'2023-12-27','치즈소스감자튀김.jpg','ce02cb8b-df1c-47e5-bd0d-2611c3c093fa.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/ce02cb8b-df1c-47e5-bd0d-2611c3c093fa.jpg',213096,'20231227','.jpg',0,1,1),(33,'3','고구마크림치즈스틱',6900,'',0,'2023-12-27','고구마크림치즈스틱.jpg','b482c97a-05ca-4a18-a446-b37d8a903a2a.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/b482c97a-05ca-4a18-a446-b37d8a903a2a.jpg',211129,'20231227','.jpg',0,1,1),(34,'4','코카콜라 500ml',2000,'',0,'2023-12-27','코카콜라.jpg','5adf9696-858d-4e10-9ed6-cdff2c94e0e8.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/5adf9696-858d-4e10-9ed6-cdff2c94e0e8.jpg',65501,'20231227','.jpg',0,1,1),(36,'4','스프라이트 500ml',2000,'',0,'2023-12-27','스프라이트.jpg','cb239616-7e71-4a5b-9fb1-49133b80c6f4.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/cb239616-7e71-4a5b-9fb1-49133b80c6f4.jpg',57662,'20231227','.jpg',0,1,1),(37,'4','갈릭디핑소스',500,'',0,'2023-12-27','갈릭디핑소스.jpg','c4554f95-b774-413c-9429-a8adca3a797f.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/c4554f95-b774-413c-9429-a8adca3a797f.jpg',89607,'20231227','.jpg',0,1,1),(38,'4','핫소스',200,'',0,'2023-12-27','핫소스.png','cd6d6d3e-3b5e-4058-95f8-70ea21f6e509.png','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/cd6d6d3e-3b5e-4058-95f8-70ea21f6e509.png',231852,'20231227','.png',0,1,1),(39,'4','오이 피클',800,'',0,'2023-12-27','오이피클.jpg','f9c8f894-d6a8-4378-822c-520d1341ebe6.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/f9c8f894-d6a8-4378-822c-520d1341ebe6.jpg',104168,'20231227','.jpg',0,1,1),(40,'4','할라피뇨 피클',800,'',0,'2023-12-27','할라피뇨피클.jpg','45ac5e22-a92f-426c-b195-565275a90c57.jpg','C:/Users/ITPS/Downloads/korpizzaPrj (2) (1)/src/main/resources/static/upload/20231227/45ac5e22-a92f-426c-b195-565275a90c57.jpg',94999,'20231227','.jpg',0,1,1);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
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
