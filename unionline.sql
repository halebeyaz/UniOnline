-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: unionline
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
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `info` varchar(45) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `teacher_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_code`),
  CONSTRAINT `announcement_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=556 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (1,'Lesson Cancelled','The lesson at this week is cancelled.','CME2201','t002');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement_student`
--

DROP TABLE IF EXISTS `announcement_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement_student` (
  `ann_id` int NOT NULL,
  `student_id` varchar(50) NOT NULL,
  KEY `ann_id` (`ann_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `announcement_course_ibfk_1` FOREIGN KEY (`ann_id`) REFERENCES `announcement` (`id`),
  CONSTRAINT `announcement_course_ibfk_4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement_student`
--

LOCK TABLES `announcement_student` WRITE;
/*!40000 ALTER TABLE `announcement_student` DISABLE KEYS */;
INSERT INTO `announcement_student` VALUES (1,'s006');
/*!40000 ALTER TABLE `announcement_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `info` varchar(45) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `teacher_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_code`),
  CONSTRAINT `assignment_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'2*2','Enter the result','CME3209','t010');
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment_student`
--

DROP TABLE IF EXISTS `assignment_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_student` (
  `id` int NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `assn_status` varchar(50) DEFAULT NULL,
  `assn_text` varchar(50) DEFAULT NULL,
  KEY `id` (`id`),
  KEY `course_id` (`student_id`),
  CONSTRAINT `assignment_student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `assignment` (`id`),
  CONSTRAINT `assignment_student_ibfk_4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_number`),
  CONSTRAINT `chk_assnstatus` CHECK ((`assn_status` in (_utf8mb4'completed',_utf8mb4'uncompleted')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_student`
--

LOCK TABLES `assignment_student` WRITE;
/*!40000 ALTER TABLE `assignment_student` DISABLE KEYS */;
INSERT INTO `assignment_student` VALUES (1,'s006','uncompleted','');
/*!40000 ALTER TABLE `assignment_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `text` varchar(45) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,'s006','Ok');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_code` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  PRIMARY KEY (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('CME1212','Algorithms',150),('CME2201','Data Structures and Algorithms',110),('CME2210','OOP',120),('CME3201','DB',120),('CME3202','Programming Languages',100),('CME3204','Networks',130),('CME3206','Software Engineering',100),('CME3207','Signal&System',90),('CME3208','Embedded Systems',130),('CME3209','Calculus',150);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `senderID` varchar(45) NOT NULL,
  `receiverID` varchar(45) NOT NULL,
  `msgtext` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'s006','t002','Hi'),(2,'t002','s006','Hi');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `course_code` varchar(45) NOT NULL,
  `teacher_id` varchar(45) NOT NULL,
  `text` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `post_fkbj_1_idx` (`course_code`),
  KEY `post_fkjb_2_idx` (`teacher_id`),
  CONSTRAINT `post_fkbj_1` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`),
  CONSTRAINT `post_fkjb_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'CME2201','t002','Lesson Date : 3:00 pm every Wednesday !');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_number` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `personal_id` varchar(50) NOT NULL,
  PRIMARY KEY (`student_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('s001','Helen','Jerems','hjerems@berkeley.deu','+353 466 113 1148','776-33-6829'),('s002','Leupold','Kluger','lkluger1@com.com','+66 868 276 5927','117-55-2717'),('s003','Beckie','Pilfold','bpilfold2@reference.com','+55 209 888 3961','178-19-4023'),('s004','Minetta','Skea','mskea3@wired.com','+212 360 353 1138','365-12-7981'),('s005','Winfield','Flockhart','wflockhart4@cloudflare.com','+63 208 475 1061','422-18-3469'),('s006','Truda','Willers','twillers5@dell.com','+86 856 760 0468','614-51-6017'),('s007','Carolee','Chadburn','cchadburn6@unesco.org','+385 666 667 3902','387-77-9636'),('s008','Ralph','Yeldon','ryeldon7@independent.co.uk','+86 135 969 7468','609-04-1413'),('s009','Kiley','Aimable','kaimable8@sina.com.cn','+251 304 448 1250','474-09-3754'),('s010','Lulita','Tellenbach','ltellenbach9@google.nl','+55 102 457 4232','548-93-8536');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `student_id` varchar(50) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_number`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_code`),
  CONSTRAINT `chk_status` CHECK ((`status` in (_utf8mb4'required',_utf8mb4'elective')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES ('s006','CME2201','required'),('s006','CME1212','elective'),('s006','CME3209','required'),('s006','CME3209','required');
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacher_id` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `personal_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('t001','Helly','Juhr','hjuhruke@jalbum.net','+46 574 355 6757','707-07-8943'),('t002','Nowell','Botwood','nbotwood1@fc2.com','+66 729 462 4496','629-35-6172'),('t003','Alika','Steels','asteels2@4shared.com','+33 527 611 3677','234-43-8867'),('t004','Morgen','Archibald','marchibald3@vk.com','+48 155 621 3414','314-01-3696'),('t005','Korella','Thorbon','kthorbon4@cloudflare.com','+92 365 213 1252','570-38-3824'),('t006','Shane','Larimer','slarimer5@nbcnews.com','+86 998 867 9672','413-32-1828'),('t007','Caritta','Kuller','ckuller6@bizjournals.com','+86 661 354 8722','516-27-6636'),('t008','Sioux','Perillo','sperillo7@themeforest.net','+371 126 727 5712','755-27-7625'),('t009','Abbie','Shovelin','ashovelin8@wikia.com','+62 668 359 9513','822-41-6984'),('t010','Haydon','Cheyney','hcheyney9@t-online.de','+62 940 730 1871','568-78-3901');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_course`
--

DROP TABLE IF EXISTS `teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_course` (
  `teacher_id` varchar(50) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  KEY `teacher_id_idx` (`teacher_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_code`),
  CONSTRAINT `teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_course`
--

LOCK TABLES `teacher_course` WRITE;
/*!40000 ALTER TABLE `teacher_course` DISABLE KEYS */;
INSERT INTO `teacher_course` VALUES ('t001','CME1212'),('t002','CME2201'),('t003','CME2210'),('t004','CME3201'),('t005','CME3202'),('t006','CME3204'),('t007','CME3206'),('t008','CME3207'),('t009','CME3208'),('t010','CME3209');
/*!40000 ALTER TABLE `teacher_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 14:40:25
