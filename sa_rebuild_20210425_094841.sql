-- MySQL dump 10.13  Distrib 5.6.49, for Linux (x86_64)
--
-- Host: localhost    Database: sa_rebuild
-- ------------------------------------------------------
-- Server version	5.6.49-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `check_info`
--

DROP TABLE IF EXISTS `check_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_info` (
  `check_id` varchar(30) NOT NULL COMMENT '编号',
  `goods_name` varchar(255) NOT NULL COMMENT '物品名称',
  `goods_price` float NOT NULL COMMENT '物品价格',
  `goods_content` text NOT NULL COMMENT '物品描述',
  `goods_type` set('bargain','secondHand') NOT NULL COMMENT '类型（多选）：bargin=可报价，secondHand=二手',
  `goods_cover_image` varchar(100) NOT NULL COMMENT '封面',
  `goods_images` varchar(800) NOT NULL COMMENT '图片集',
  `goods_longitude` float NOT NULL COMMENT '经度',
  `goods_latitude` float NOT NULL COMMENT '纬度',
  `goods_ownerId` int(11) NOT NULL COMMENT '卖家ID',
  `goods_watch_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `goods_favor_count` int(11) NOT NULL DEFAULT '0' COMMENT '收藏量',
  `goods_chat_count` int(11) DEFAULT '0' COMMENT '交流量',
  `createtime` int(11) NOT NULL COMMENT '添加时间',
  `updatetime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_info`
--

LOCK TABLES `check_info` WRITE;
/*!40000 ALTER TABLE `check_info` DISABLE KEYS */;
INSERT INTO `check_info` VALUES ('i8gsfrYE9SZl7INB','很快就会考虑',666,'等到法国红酒加热2该合同胡椒粉的合法的结果大使馆开个\n\n共产党但是\n\n\n\n\n感谢这个吧风华绝代时时彩突然问我方法\n\n体无完肤','bargain','uploads/img/goods/20210220/ZF1Qi6r1KAn380pNHALi4X7b09.jpeg','uploads/img/goods/20210220/1TSvA6U1KzUT3X804760hD2Dm9.png',-122.084,37.4219,9,0,0,0,1613804710,1613804710),('J6rDBC8UfKomt9Rl','很快就会考虑',666,'等到法国红酒加热2该合同胡椒粉的合法的结果大使馆开个\n\n共产党但是\n\n\n\n\n感谢这个吧风华绝代时时彩突然问我方法\n\n体无完肤','bargain','uploads/img/goods/20210220/Y1BW7n6Gu1me3I78xxfa047x46.jpeg','uploads/img/goods/20210220/5E1Iu64l14a388i02r4ky74K26.png',-122.084,37.4219,9,0,0,0,1613804747,1613804747),('MafZcBVxI9eEhSRb','第一个测试物品',233,'第一个测试物品的详细描述\n\n\n哈哈哈哈\n\n\n\n可以了吗可以了吗\n\n\n让我康康','secondHand','tmp3167gWxI1V38f39lr839p745d0.jpg','tmp71yR7H26RL41e3ctS689q39Z40.jpg,tmpA1jVnJO6138kiW53q92390E4D0.jpg',-122.084,37.4219,9,0,0,0,1613893942,1613893942);
/*!40000 ALTER TABLE `check_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_result`
--

DROP TABLE IF EXISTS `check_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_result` (
  `check_id` varchar(30) NOT NULL COMMENT '编号',
  `check_type` set('checked','passed') NOT NULL COMMENT '类型checked=已审核，passed=已通过',
  `reason` varchar(30) NOT NULL COMMENT '理由',
  `create_time` int(11) NOT NULL COMMENT '创建时间',
  KEY `check_id` (`check_id`),
  CONSTRAINT `check_result_ibfk_1` FOREIGN KEY (`check_id`) REFERENCES `check_info` (`check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_result`
--

LOCK TABLES `check_result` WRITE;
/*!40000 ALTER TABLE `check_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods_info`
--

DROP TABLE IF EXISTS `goods_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods_info` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `goods_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物品名称',
  `goods_price` float NOT NULL COMMENT '物品价格',
  `goods_content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物品描述',
  `goods_type` set('bargain','secondHand') COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型(多选):bargain=可报价,secondHand=二手',
  `goods_cover_image` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '封面',
  `goods_images` varchar(800) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片集',
  `goods_longitude` double NOT NULL COMMENT '经度',
  `goods_latitude` double NOT NULL COMMENT '纬度',
  `goods_ownerId` int(11) NOT NULL COMMENT '卖家ID',
  `goods_watch_count` int(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `goods_favor_count` int(11) NOT NULL DEFAULT '0' COMMENT '收藏量',
  `goods_chat_count` int(11) NOT NULL DEFAULT '0' COMMENT '交流量',
  `createtime` int(11) NOT NULL COMMENT '添加时间',
  `updatetime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`goods_id`),
  KEY `goods_upload_user_id` (`goods_ownerId`),
  CONSTRAINT `goods_info_ibfk_1` FOREIGN KEY (`goods_ownerId`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='物品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods_info`
--

LOCK TABLES `goods_info` WRITE;
/*!40000 ALTER TABLE `goods_info` DISABLE KEYS */;
INSERT INTO `goods_info` VALUES (69,'青年大学习代刷',1,'代刷青年大学习，哈哈哈哈','bargain','uploads/img/goods/20210319/b1Wzv61b3uE6u14M3y4F5712Z7','uploads/img/goods/20210412/pM1M6ArwT18Y2r4pnK0E4J82s3,uploads/img/goods/20210412/LGS16va8i18w24DxHV0Y25o6U3,uploads/img/goods/20210412/Nk1BA6Nv182pM40qE8Ji6IH0b5,uploads/img/goods/20210412/1xB5A69q18GPp26041gep1nj07,uploads/img/goods/20210412/y161R18xo6PqVW2V41O1vxx0u7',120.3606,30.317797,18,28,2,0,1616135719,1616135719),(70,'龙狙',99999,'龙狙，价格美丽','bargain,secondHand','uploads/img/goods/20210319/K811eM6HUFy961W69p13A60I00','uploads/img/goods/20210319/1dj6C21J6j1l1QJ3w6a0UMi400',120.360628,30.317682,18,18,2,0,1616136001,1616136001),(71,'久经品质，略磨享受',6666,'你不发非凡哥','bargain,secondHand','uploads/img/goods/20210319/yt1618ozg6Ii1B54p1K02xR8k3','uploads/img/goods/20210319/q161advA61T4alN3A0A28HTF73,uploads/img/goods/20210319/16brksILAn41mz61Pfy40e2803',120.36069986979167,30.3178125,21,16,1,0,1616140285,1616140285),(73,'绿箭',648,'居家旅行砍瓜切菜好伴侣','bargain,secondHand','uploads/img/goods/20210319/16s0V1q16Df1r4c4iZ2iCNb451','uploads/img/goods/20210319/1ohE361D69A1fLDR4k2Kv24551',120.36059461805556,30.31754692925347,22,22,8,0,1616142452,1616142452),(74,'妙啊',0.99,'麻了','','uploads/img/goods/20210319/t1k6SA1O3R61n446qk350F4Vs5','uploads/img/goods/20210319/1hZcPHXrD616X1eH44U38z07B5',120.360542,30.317593,23,22,3,0,1616144306,1616144306),(75,'魈',648,'帅','','uploads/img/goods/20210319/11Q6bXZvN1yzR6jYI14gw46375','uploads/img/goods/20210319/1YXjx6D2161E448z38e91u17L5',120.360539,30.31759,24,19,2,0,1616144376,1616144376),(79,'多图阿斯顿as',230,'阿斯打死打伤的\n\nsad','','uploads/img/goods/20210320/M11OEz61l2Dw6u92241kKa1ib8','uploads/img/goods/20210320/md1TI61Z6Fg2s2641UA1IF9wp8,uploads/img/goods/20210320/0156x1W60g2r2b4u157N1FA8n2,uploads/img/goods/20210320/amt1bpJ6916l24aQQ2y4L5LM82',-122.083977,37.421948,20,5,0,0,1616224120,1616224120),(80,'?宁生日快落',4799,'继续记得记得就行觉得不对不对吧','','uploads/img/goods/20210320/1w6odOORO895162L2h537F4oK4','uploads/img/goods/20210320/W1z61wyeze3gnN8a62P25g34Z4,uploads/img/goods/20210320/xW9176yGtRK1DI6y246d2534i4,uploads/img/goods/20210320/c156COl1vst2kc5J62I2KL5344',120.36236895096818,30.318141927801996,20,22,3,0,1616225346,1616225346),(81,'帅',10000,'帅气','bargain,secondHand','uploads/img/goods/20210321/K5g916H162U5wKAHkpo7PV11q0','uploads/img/goods/20210321/13sQ6k4dJvl1N6Smqu2W57X110,uploads/img/goods/20210321/M1wq6136OHOA257rjd3U1oth10,uploads/img/goods/20210321/1s6Lb1642554VrA0v71RXkYB10',120.360348,30.317516,29,21,2,0,1616257112,1616257112),(83,'毛概作业小虎还乡',7698,'好的好想好想会说话','secondHand','uploads/img/goods/20210325/DAM16yB1L86A64gn679DQVMlC0','uploads/img/goods/20210325/eG8O1XRJx61T16g6m46n82eFi8',120.360356,30.317535,29,13,2,0,1616646792,1616646792),(89,'以为是美女，其实是帅哥',2888880,'。。。。。。','','uploads/img/goods/20210402/rGQ811N6KlO61Dx7GDTW343980','uploads/img/goods/20210402/16qVER1oLt7rUED3z4859l3980',120.36243001302083,30.320237630208332,32,10,-1,0,1617343983,1617343983),(97,'C#核心技术指南，全新未拆塑封，不继续学C#了所以出',100,'当初淘宝买来花了129.5，买了就一直放着了，那个时候学C#，网上都说这上面linq讲的好，就买了，结果不搞了。老厚一本了，看不进去拿来防身也是非常不错。这个价格，比淘宝便宜多了真的。','bargain','uploads/img/goods/20210416/P19i6d1e8ZFV8556UiwMWY3p52','uploads/img/goods/20210416/KRqVEOn161re1o85U563KE5v12,uploads/img/goods/20210416/rH1bgsjSF61zm8rt556TE3YM52,uploads/img/goods/20210416/uPT16418A5Vp563MMGh75qaP82,uploads/img/goods/20210416/Mp14Zy618FOm58546KP3J8om52,uploads/img/goods/20210416/oM1NR861i88F5DE566M0mEWTB3,uploads/img/goods/20210416/1XV6vsO312B8u55n5d66P950M3',120.36302463107639,30.320009223090278,31,5,1,0,1618556353,1618556353),(98,'在线教育部门负责人参加的这次比赛活动结束后对你进行批评孩子',99.5,'这些天能收到短信没有了、这么晚的地方还在你那里没有人在这里我会永远支持所有人员和美关系中成长起来吧！你是个男人吧！你在一起了么！你们是怎么回事！你是否真的会遇到','bargain,secondHand','uploads/img/goods/20210416/vr1Y6D72h91856Y5i5U5rvxR53','uploads/img/goods/20210416/q1FVT6U15MMtoNsD8565vb5J53,uploads/img/goods/20210416/y1um61Q318G5G65r5QKow5jBP3,uploads/img/goods/20210416/Bd1xd61myM8n1TV5d65lY565B3,uploads/img/goods/20210416/Dv1618j5hsg6Nc55UyX7ir5K83,uploads/img/goods/20210416/bPN1y6N1l14856IpUB0j55C5L3,uploads/img/goods/20210416/1J61z858EQ8c6Sk5RH0Lzs5853,uploads/img/goods/20210416/v1ih2W6F13w853dKS8e6T55R53',120.22828531901041,30.22443305121528,31,1,0,0,1618565555,1618565555);
/*!40000 ALTER TABLE `goods_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iwant_info`
--

DROP TABLE IF EXISTS `iwant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iwant_info` (
  `iwant_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '求购ID',
  `iwant_content` varchar(200) NOT NULL COMMENT '求购内容',
  `iwant_tag` int(11) NOT NULL COMMENT '求购标签',
  `iwant_images` varchar(200) DEFAULT NULL COMMENT '求购图片',
  `iwant_color` int(11) NOT NULL COMMENT '卡片颜色',
  `iwant_longitude` double NOT NULL COMMENT '求购经度',
  `iwant_latitude` double NOT NULL COMMENT '求购纬度',
  `iwant_ownerId` int(11) NOT NULL COMMENT '求购用户ID',
  `createtime` int(11) NOT NULL COMMENT '创建时间',
  `updatetime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`iwant_id`),
  KEY `inquiry_tag` (`iwant_tag`),
  CONSTRAINT `iwant_info_ibfk_1` FOREIGN KEY (`iwant_id`) REFERENCES `iwant_tags` (`tags_id`),
  CONSTRAINT `iwant_info_ibfk_2` FOREIGN KEY (`iwant_tag`) REFERENCES `iwant_tags` (`tags_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='求购表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iwant_info`
--

LOCK TABLES `iwant_info` WRITE;
/*!40000 ALTER TABLE `iwant_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `iwant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iwant_tags`
--

DROP TABLE IF EXISTS `iwant_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iwant_tags` (
  `tags_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tags_content` varchar(10) NOT NULL COMMENT '标签详情',
  `createtime` int(11) NOT NULL COMMENT '创建时间',
  `updatetime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`tags_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iwant_tags`
--

LOCK TABLES `iwant_tags` WRITE;
/*!40000 ALTER TABLE `iwant_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `iwant_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_info`
--

DROP TABLE IF EXISTS `mail_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_info` (
  `mail_info` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `descreption` varchar(100) NOT NULL,
  `create_time` int(11) NOT NULL,
  PRIMARY KEY (`mail_info`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_info`
--

LOCK TABLES `mail_info` WRITE;
/*!40000 ALTER TABLE `mail_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offline0`
--

DROP TABLE IF EXISTS `offline0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offline0` (
  `offline_id` int(11) NOT NULL AUTO_INCREMENT,
  `finger_print` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sender_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `receiver_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `message_type` tinyint(4) DEFAULT '0' COMMENT '0代表普通文本，可带emoji表情\r\n1代表图片消息',
  `received` tinyint(4) DEFAULT '0' COMMENT '标记离线消息是否已被ack\r\nack的消息将在每日某个特定时间统一删除',
  `send_time` bigint(20) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`offline_id`),
  UNIQUE KEY `offline0_finger_print_uindex` (`finger_print`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline0`
--

LOCK TABLES `offline0` WRITE;
/*!40000 ALTER TABLE `offline0` DISABLE KEYS */;
INSERT INTO `offline0` VALUES (58,'f4a72d5a-778f-4663-8242-876b1491a444','16','7',1,0,1616129207375,'uploads/img/im/20210319/Cxq0e1O616qj12Nx0A92DKhg06'),(59,'1d62b2f7-d649-4164-9117-f8f1b4c4579a','16','7',1,0,1616129208191,'uploads/img/im/20210319/WeU16Y1q06n3N12Y992t2Az0D6'),(60,'BA6ECF8F-4D60-4C76-9C48-FE371D4AD66F','21','18',0,0,1616140079286,'你好'),(61,'6CBEE9E7-A37F-4D28-A5CD-526AE246A5B8','21','18',0,0,1616140085690,'看看匕'),(62,'7196cac8-d878-4adc-8203-435f16bd02d3','20','18',0,0,1616224760770,'有体育台'),(63,'78653395-44a8-4a9e-bd71-d700c611ed5f','20','18',0,0,1616224784411,'户籍科户籍科'),(64,'ADFBBC38-4738-41CF-9615-DFD7ADE5817E','20','18',0,0,1616225568984,' 你在那想你想你'),(65,'4CEB01BC-71E7-46D4-803C-A9CB67C733B8','19','18',1,0,1616320721532,'uploads/img/im/20210321/PG1y60a361l6Wbez32M0H714d8'),(66,'5CA8ABBD-7BFD-48C6-B14E-3C57F8C1C76A','19','18',0,0,1616321779288,'。zz'),(67,'de200a8f-1221-44c1-996b-9d63a76419d8','20','18',0,0,1616322583273,'阿斯打死打伤的'),(68,'F62EE5DB-4EDA-4985-84C6-905342A56B6E','19','18',0,0,1616323988237,' '),(69,'BD4C15CD-756F-46AB-8244-FD62DAE6B9DD','19','18',0,0,1616324077227,'zzz'),(70,'12A2AE64-D6BB-42BD-B99A-EAC74E049555','20','18',0,0,1616328047945,'一股股'),(87,'16F360FD-B016-453F-8AFA-0AC012A8C3CF','20','18',0,0,1616337131910,'就是觉得精神'),(88,'7C9A6425-A835-448E-A59D-86021DA56135','20','18',0,0,1616338606166,'会大喊大叫'),(89,'D9CFD740-B1CE-46C8-A8CD-480EA23CDA93','20','18',0,0,1616338612465,'后端觉得你难受'),(90,'493CD431-6E46-4256-B12B-0B420BB6C4EA','20','18',0,0,1616338614130,'好的计算机界'),(91,'BB5E3847-6530-4FB6-A1A9-0320F09B9D27','20','18',0,0,1616342065464,'尽职尽责就'),(92,'0AA4A2B3-51C8-4B08-932C-273F179B97CA','20','18',0,0,1616389699519,'更待何时实际上'),(93,'C77EA872-1880-4ABA-AAEA-7E41A7F8DB2A','20','18',0,0,1616389703977,'啊啊啊啊啊'),(94,'4B2FBA04-B383-4405-82E2-48F0D7EAFB79','20','18',0,0,1616389976287,'还是说啥呢'),(95,'35F06E49-8CB7-45BF-8DB4-064565A8E9A0','20','18',0,0,1616390168172,'遇到好多好多话'),(96,'FF321475-D44C-4F18-931D-3930F75373B2','20','18',0,0,1616391315824,'都记得记得就'),(97,'0D515D5F-04D8-4519-8DA9-9CAA4CE16230','20','18',0,0,1616391336371,'发v被拒绝'),(98,'27280F2C-2E92-49F1-94B3-8B626C1A501B','20','18',0,0,1616392130857,'不不不'),(99,'1CE1C5A4-E93A-4344-BEEB-C4B11AB96E87','20','18',0,0,1616401242927,'呵呵蝴蝶结多久'),(100,'9C2C8252-E54A-4242-B564-370030FBC891','20','18',0,0,1616405600599,'收到了收到了'),(101,'00dbc86c-855f-4102-835e-95a5b8989cf9','20','18',0,0,1616409868477,'二人'),(102,'b7afcc3c-da5b-49a3-b430-37b4b75f2446','20','18',0,0,1616409891656,'添加剂和'),(103,'51E6FB97-CFA0-4915-8980-BDD94A82040B','19','18',0,0,1616413662400,'韩国国会和规范'),(104,'51AA032B-3E67-4BF2-84AF-C3DB9AEC2117','19','18',0,0,1616413664374,'回归发广告'),(105,'4e38d88e-2425-4327-a523-0b6fe2940431','20','18',0,0,1616415879263,'啊是大师的'),(106,'3F03170C-917D-454C-9F36-2256537A36D6','19','18',0,0,1616426078267,'贵不贵'),(107,'38EDBC35-298E-4ADB-8910-F79BC6FB8016','19','18',0,0,1616428272226,'好好聚聚'),(108,'EE551A6D-2577-454B-999E-4D5497B2E66D','19','18',0,0,1616428430604,'不会让别人'),(109,'75A06FF0-CAF3-460D-AB95-DD1ADD36738D','19','18',0,0,1616428462450,'这些是'),(110,'F692F636-0D4A-4975-B89D-2E882CDAAEAA','19','18',0,0,1616428600117,'这'),(111,'E276D2CE-7443-4C01-9DF5-C12F81D383FA','19','18',0,0,1616428646915,'好后悔过'),(112,'B2F67221-21DA-471F-8564-634C07037FA7','19','18',0,0,1616428724106,'在于自己'),(113,'6870B578-D4E6-4BBA-80BB-7A8411901243','19','18',0,0,1616429105436,'ccc'),(114,'292D214C-B149-4BF4-A74F-9B71859A50FF','19','18',0,0,1616495327962,'哈哈哈哈'),(115,'861A3227-05AF-4587-A70F-816DA4A2411D','19','18',0,0,1616495329971,'不哈哈哈哈'),(116,'68572D9A-31B6-477A-B6DA-7A39E8F2FED1','19','18',0,0,1616495332082,'哈哈哈哈哈哈'),(117,'9CA74775-22B3-4D10-8B8F-327180F1E0CE','19','18',0,0,1616495377457,'忽然好的好的'),(118,'19197001-CDCB-47D5-8956-17098AF26088','19','18',1,0,1616495395866,'uploads/img/im/20210323/16aI514N622469Jxpgt539LQ42'),(119,'D1C31AD6-A16C-4424-9579-3B80E61211C2','19','18',0,0,1616495437430,'好吧、'),(120,'26EEB5E6-DE43-4E8D-9D5B-48469FC39D35','19','18',0,0,1616495439489,'具有'),(121,'4E95A413-EEB0-4AC1-91C6-2B9647BC85F5','19','18',0,0,1616495447949,'呃呃呃'),(122,'63848FD4-1DC5-4EBC-A541-FEB25CACE9D1','19','18',0,0,1616495463201,'发你方便的好的好的好粉嫩粉嫩才能从聚聚聚聚'),(123,'08CCFC29-11FF-401D-A679-6BD5AA8418CD','20','18',0,0,1616514377844,'??'),(124,'A838901F-C6C5-4C1F-BDE1-1CDEBF443B31','20','18',0,0,1616514388458,'??'),(125,'80EE4233-500A-4317-9232-34695DFB0C23','20','18',1,0,1616514415899,'uploads/img/im/20210323/1lSUm6QQ166P5miB9K1441yIo0'),(126,'816F5338-2B79-46B8-897E-AC456CEEE6B6','20','18',1,0,1616514415968,'uploads/img/im/20210323/ts16w2n1Y076514C4Qi4hSjT10'),(127,'1F9D8C0F-E1F3-4B69-87F6-18A0C369BD3C','20','18',1,0,1616514415981,'uploads/img/im/20210323/1K6wt1zLJlLT6Ht5l1s64241v0'),(128,'942E3CBF-B39B-4826-8956-6112B571FEC0','19','18',0,0,1616598409496,'就是男神'),(129,'7D32757D-0407-4FD6-899E-CD00980E7E89','19','18',0,0,1616644061788,'have the a people l the only way i could get to be a game i was a great game i is a great way game to get to play '),(130,'09B62C71-3FF5-42E6-8517-4470F0D6C2BD','19','18',0,0,1616644073248,'好吧！你就这样对我说过我会一直支持下去我也可以的……这么好我都不知道?‍♂️……在'),(131,'0B135BD6-6E05-42A2-943D-C69DB2E86730','20','18',0,0,1616648095848,'还是啥'),(146,'913c97d1-2016-4981-a21f-5bc01f4fec49','33','18',0,0,1616670918257,'hello'),(158,'40080c4c-356b-4af3-b0b5-31560b77b339','27','13',0,0,1616882695754,'tji'),(163,'13DEBCF8-3E4E-48D4-B84A-EFFD1FEC5A27','31','18',0,0,1617421166124,'aaa'),(164,'028FE687-0EFA-4E41-B2D6-CA517921139A','31','18',0,0,1617421167974,'发'),(175,'7785A6A3-60E2-41EE-855C-0BCEF281D471','20','18',0,0,1617864677749,'读书');
/*!40000 ALTER TABLE `offline0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offline1`
--

DROP TABLE IF EXISTS `offline1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offline1` (
  `offline_id` int(11) NOT NULL AUTO_INCREMENT,
  `finger_print` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sender_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `receiver_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `message_type` tinyint(4) DEFAULT '0' COMMENT '0代表普通文本，可带emoji表情\r\n1代表图片消息',
  `received` tinyint(4) DEFAULT '0' COMMENT '标记离线消息是否已被ack\r\nack的消息将在每日某个特定时间统一删除',
  `send_time` bigint(20) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`offline_id`),
  UNIQUE KEY `offline1_finger_print_uindex` (`finger_print`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline1`
--

LOCK TABLES `offline1` WRITE;
/*!40000 ALTER TABLE `offline1` DISABLE KEYS */;
INSERT INTO `offline1` VALUES (32,'763876A9-3911-4C10-8392-49E9B52421BF','9','8',0,0,1615257561642,'把喜不喜欢'),(35,'5E87F2B6-C1F5-404A-BD43-9FC4CB743563','19','23',1,0,1616169234708,'uploads/img/im/20210319/Ma1610bz6OMM16Zp292x3gNDv0'),(36,'206FC17B-A0F4-417E-A99A-1D07ECF22305','19','23',1,0,1616170242672,'uploads/img/im/20210320/d8ra1M6K8dSPGw51m6e1702M38'),(37,'A3ADC887-B5F1-4918-ACDF-5CDE030BAF33','19','23',1,0,1616170243318,'uploads/img/im/20210320/16gh1r6LL2cCbR1700s2kVoI38'),(38,'78C0A115-70C6-42DD-B42D-DC8461E30A67','19','23',1,0,1616170243514,'uploads/img/im/20210320/16PQf1O6s6yh17qv0Ih62o7u38'),(48,'6C21B58F-D930-4E32-A55B-789D2D17C8E3','20','28',0,0,1616305823972,'尺寸规格分'),(56,'8C5F993A-0E0D-45F0-8D05-9CE3F71BB96E','19','23',0,0,1616321867473,'shift'),(57,'032ED10D-90EE-4121-9790-BFB828363F77','19','23',0,0,1616321897523,'？？'),(76,'9DA3BC32-8AB9-4893-A9BB-82207D7D89F7','20','28',0,0,1616334363215,'法国哈哈哈'),(79,'D3794AA6-2B37-40B8-82AA-4658126E7117','20','28',0,0,1616337158100,'到底多少'),(83,'a15a5ee0-edc3-4bf4-bda2-0de4308aced2','18','23',0,0,1616337203707,'还是正准备'),(86,'6f9c8d96-88ec-4dd1-8d97-edfea5e5de73','18','23',0,0,1616337232999,'vhvh_'),(88,'9ba56263-0637-49a0-8a3a-34403608677a','18','23',0,0,1616337247454,'愁更愁v句i'),(91,'8bfe1d3d-2c89-48d2-ab67-b7b1062bef25','18','23',0,0,1616337274327,'发个干活'),(92,'eaafc5f5-0ddc-4a20-b452-952fdc5af308','18','23',0,0,1616337279556,'方法干活'),(94,'28828f6b-4b21-48cf-8f4a-7d0a811e5e37','18','23',0,0,1616337283966,'回家姐姐'),(107,'A4866F64-E8A7-4565-940B-23E5A6D36A6C','20','28',0,0,1616340704945,'很多年'),(223,'6FD1E800-F44A-4688-AA50-A1A7ACB2AD8F','31','32',1,0,1617376972788,'uploads/img/im/20210402/16L1sZ734t8TW7q6Vtr9l6r275'),(224,'D2795F2E-F4BF-4F28-A1EF-42065E05E865','31','32',1,0,1617376972835,'uploads/img/im/20210402/yRG416173786CGeIMu9VKIC6t5'),(225,'BFFCC9EF-0927-4A66-B6A1-C1330337AC0D','31','32',1,0,1617376972874,'uploads/img/im/20210402/1PQO3617X37qpAIOXUOc696CJ5'),(226,'F13DB12A-FB39-4AB8-BA8B-AE765EBB2CE7','31','32',1,0,1617376972893,'uploads/img/im/20210402/aaI2161fF7c37Dp69U6n34wWu5'),(227,'5A6393FF-88AD-404D-A3CE-189FE6375D2D','31','32',1,0,1617420385907,'uploads/img/im/20210403/nG1bF6174sM2A5yQ0E96j3Zl84');
/*!40000 ALTER TABLE `offline1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offline2`
--

DROP TABLE IF EXISTS `offline2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offline2` (
  `offline_id` int(11) NOT NULL AUTO_INCREMENT,
  `finger_print` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sender_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `receiver_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `message_type` tinyint(4) DEFAULT '0' COMMENT '0代表普通文本，可带emoji表情\r\n1代表图片消息',
  `received` tinyint(4) DEFAULT '0' COMMENT '标记离线消息是否已被ack\r\nack的消息将在每日某个特定时间统一删除',
  `send_time` bigint(20) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`offline_id`),
  UNIQUE KEY `offline2_finger_print_uindex` (`finger_print`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline2`
--

LOCK TABLES `offline2` WRITE;
/*!40000 ALTER TABLE `offline2` DISABLE KEYS */;
INSERT INTO `offline2` VALUES (1,'3CBB43C1-F841-484D-901A-0C244AE88E6A','19','29',0,0,1616325607790,'.....'),(2,'6339f903-ab1d-443f-942e-ae12cee7393e','18','24',0,0,1616337304656,'挂号费哈戳戳'),(5,'649c8a3e-f502-4932-802e-ac7168f28079','20','29',0,0,1616663832547,'asdasd '),(6,'444d5862-a0f9-48ac-8289-b16d4df5acd8','20','29',0,0,1616663964782,'1'),(7,'2d3e9c94-647d-4b99-8b1a-cfcf57d78a1c','20','29',0,0,1616663966165,'1'),(8,'ff6369e1-7c03-42a7-94d6-afbfda11033e','20','29',0,0,1616663966799,'1'),(9,'25715626-be07-445c-a46c-545a77fe81d1','20','29',0,0,1616663967983,'1'),(10,'a2ba1a0e-1ef5-4638-9741-08aa20cdc4cc','20','29',0,0,1616663969016,'1'),(11,'ca57d41b-19a7-4594-b13a-a28b1fb8543e','20','29',0,0,1616663970421,'1'),(12,'77c30a44-4955-4458-825e-a88fa24f4cd2','20','29',0,0,1616663971015,'2'),(13,'3613a5a2-1b84-4c80-9e0e-303a8df4e280','20','29',0,0,1616663972019,'3'),(14,'1f32ba09-622c-4945-a945-34d1a52aa31c','20','29',0,0,1616663973182,'4'),(15,'d762d752-f89c-4bca-8696-eb59be42ba2e','20','29',0,0,1616663974196,'5'),(16,'df93d61d-a561-4c42-95c9-e3a5f8e51377','20','29',0,0,1616663983438,'1'),(17,'79ff6d84-d45a-4922-9ad9-d5a9b90c9407','20','29',0,0,1616665645398,'2'),(18,'67e8dd3d-2a2b-4003-9721-2381a1360e14','18','29',0,0,1616669020483,'经常继续继续'),(19,'5c661ad0-8283-4513-bed9-0c5800b92685','18','29',0,0,1616669042789,'不行不行不'),(20,'359ae771-1e44-4eed-959a-3c7a182e69b4','18','29',0,0,1616669115149,'好多海鲜');
/*!40000 ALTER TABLE `offline2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offline3`
--

DROP TABLE IF EXISTS `offline3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offline3` (
  `offline_id` int(11) NOT NULL AUTO_INCREMENT,
  `finger_print` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sender_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `receiver_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `message_type` tinyint(4) DEFAULT '0' COMMENT '0代表普通文本，可带emoji表情\r\n1代表图片消息',
  `received` tinyint(4) DEFAULT '0' COMMENT '标记离线消息是否已被ack\r\nack的消息将在每日某个特定时间统一删除',
  `send_time` bigint(20) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`offline_id`),
  UNIQUE KEY `offline3_finger_print_uindex` (`finger_print`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline3`
--

LOCK TABLES `offline3` WRITE;
/*!40000 ALTER TABLE `offline3` DISABLE KEYS */;
INSERT INTO `offline3` VALUES (134,'ff9d1882-ccae-404a-8e18-1d76937254ee','33','20',0,0,1616670964250,'?'),(135,'7c2e073c-71ab-4dc4-b88b-9b0857fa740c','18','20',1,0,1616688825597,'uploads/img/im/20210326/7Ah1uP6W2166O8A8L51ql8WV23'),(136,'b0379075-4e3f-43c8-b9dd-261bf2cade8d','18','20',0,0,1616754190173,'喜欢吃v就吵架'),(137,'7eea35e1-feb3-490f-9b16-89401e2a586d','18','20',0,0,1616754191495,'大姐大姐'),(138,'3f48f479-bc9e-4112-a6fc-798b208aebfc','18','20',0,0,1616754193254,'计算机视觉'),(139,'e1dbafe4-1c38-4da9-afc5-19f2eef7956a','18','20',0,0,1616754194793,'觉得就到家'),(140,'27bd3af8-b32d-4959-8cc2-c4dee448bf46','18','20',0,0,1616754196283,'回电话大姐大姐'),(141,'c0544fe1-d19a-43b9-b072-9f156fb742a2','18','20',0,0,1616754197672,'到几点结束'),(142,'79e36bfe-7732-42d5-ad62-a2b7069dbd23','18','20',0,0,1616754199244,'都觉得就到家'),(143,'983490f7-5675-408b-bda3-4f8f0041dbaf','18','20',0,0,1616754200742,'都觉得就到家');
/*!40000 ALTER TABLE `offline3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offline4`
--

DROP TABLE IF EXISTS `offline4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offline4` (
  `offline_id` int(11) NOT NULL AUTO_INCREMENT,
  `finger_print` varchar(50) CHARACTER SET latin1 NOT NULL,
  `sender_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `receiver_id` varchar(11) CHARACTER SET latin1 NOT NULL,
  `message_type` tinyint(4) DEFAULT '0' COMMENT '0代表普通文本，可带emoji表情\r\n1代表图片消息',
  `received` tinyint(4) DEFAULT '0' COMMENT '标记离线消息是否已被ack\r\nack的消息将在每日某个特定时间统一删除',
  `send_time` bigint(20) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`offline_id`),
  UNIQUE KEY `offline4_finger_print_uindex` (`finger_print`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline4`
--

LOCK TABLES `offline4` WRITE;
/*!40000 ALTER TABLE `offline4` DISABLE KEYS */;
INSERT INTO `offline4` VALUES (1,'A9742A84-2F60-4AF1-A8F7-03F334C49510','19','26',0,0,1616565516546,'啊啊啊啊啊'),(2,'4542EAC9-4E34-4354-9312-FEA5A497D419','19','26',0,0,1616578977853,'在一起就会幸福快乐'),(3,'13876DEA-E775-4805-A616-D49E96ED4477','19','26',0,0,1616579043419,'我想知道什么的好东西没有好结果、这些东西是你不'),(4,'046D8134-A66F-4629-A616-163531BCF5A1','19','26',0,0,1616581317233,'ccccc'),(5,'485114F4-DE4F-4A4D-A380-02136EE68B8B','19','26',0,0,1616583149298,'不会让'),(6,'88916BE2-CFCE-4DDB-92C0-4116E405B1EF','19','26',0,0,1616583201468,'居然回我了'),(7,'325C1881-3C81-4991-A694-A0CF542B44B6','19','26',0,0,1616583207339,'我在测试'),(8,'9721243E-FDF8-475A-897C-91142A645751','19','26',0,0,1616583212748,'我乱发的'),(9,'8EE280E0-6B43-48D1-8E32-AC508E17D323','19','26',0,0,1616583216988,'你可以不用回'),(10,'C09CF37C-BC74-456C-95FE-E1A2DC21F426','19','26',0,0,1616583282801,'我再发几条'),(11,'E70EF33A-0CB5-4CD6-B15C-1DB453170DD1','19','26',0,0,1616583387154,'奇怪');
/*!40000 ALTER TABLE `offline4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `offline_from_all0`
--

DROP TABLE IF EXISTS `offline_from_all0`;
/*!50001 DROP VIEW IF EXISTS `offline_from_all0`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_from_all0` AS SELECT 
 1 AS `offline_id`,
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `finger_print`,
 1 AS `message_type`,
 1 AS `message`,
 1 AS `send_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_from_all1`
--

DROP TABLE IF EXISTS `offline_from_all1`;
/*!50001 DROP VIEW IF EXISTS `offline_from_all1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_from_all1` AS SELECT 
 1 AS `offline_id`,
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `finger_print`,
 1 AS `message_type`,
 1 AS `message`,
 1 AS `send_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_from_all2`
--

DROP TABLE IF EXISTS `offline_from_all2`;
/*!50001 DROP VIEW IF EXISTS `offline_from_all2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_from_all2` AS SELECT 
 1 AS `offline_id`,
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `finger_print`,
 1 AS `message_type`,
 1 AS `message`,
 1 AS `send_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_from_all3`
--

DROP TABLE IF EXISTS `offline_from_all3`;
/*!50001 DROP VIEW IF EXISTS `offline_from_all3`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_from_all3` AS SELECT 
 1 AS `offline_id`,
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `finger_print`,
 1 AS `message_type`,
 1 AS `message`,
 1 AS `send_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_from_all4`
--

DROP TABLE IF EXISTS `offline_from_all4`;
/*!50001 DROP VIEW IF EXISTS `offline_from_all4`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_from_all4` AS SELECT 
 1 AS `offline_id`,
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `finger_print`,
 1 AS `message_type`,
 1 AS `message`,
 1 AS `send_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `offline_nums`
--

DROP TABLE IF EXISTS `offline_nums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offline_nums` (
  `client_a_id` varchar(11) CHARACTER SET latin1 NOT NULL COMMENT '两者中compareTo方法比较的id小者',
  `client_b_id` varchar(11) CHARACTER SET latin1 NOT NULL COMMENT '两者中id大者，即后注册者\r\n',
  `offline_num_to_a` smallint(6) DEFAULT '0' COMMENT '对于a来说未查看的消息数',
  `offline_num_to_b` smallint(6) DEFAULT '0' COMMENT '对于b来说未查看的消息数',
  `fingerprint` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '用于在请求snapshot时合并查找最后一条消息的内容',
  `latest_sender` tinyint(1) DEFAULT NULL COMMENT '最新消息的发送者\r\n0 == > a\r\n1 == > b',
  PRIMARY KEY (`client_a_id`,`client_b_id`),
  UNIQUE KEY `unq_client_a_b` (`client_a_id`,`client_b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline_nums`
--

LOCK TABLES `offline_nums` WRITE;
/*!40000 ALTER TABLE `offline_nums` DISABLE KEYS */;
INSERT INTO `offline_nums` VALUES ('13','27',1,0,'40080c4c-356b-4af3-b0b5-31560b77b339',1),('18','19',0,0,'09B62C71-3FF5-42E6-8517-4470F0D6C2BD',1),('18','20',0,0,'7785A6A3-60E2-41EE-855C-0BCEF281D471',1),('18','21',0,0,'6CBEE9E7-A37F-4D28-A5CD-526AE246A5B8',1),('18','23',0,0,'28828f6b-4b21-48cf-8f4a-7d0a811e5e37',0),('18','24',0,1,'6339f903-ab1d-443f-942e-ae12cee7393e',0),('18','29',0,0,'359ae771-1e44-4eed-959a-3c7a182e69b4',0),('18','31',0,0,'ec3072ea-42db-4a5a-bd7c-e309e8a24615',0),('18','33',0,0,'913c97d1-2016-4981-a21f-5bc01f4fec49',1),('19','20',0,0,'235E3D10-723C-41A6-9535-458AF382CCD3',1),('19','23',0,0,'032ED10D-90EE-4121-9790-BFB828363F77',0),('19','26',0,0,'E70EF33A-0CB5-4CD6-B15C-1DB453170DD1',0),('19','29',0,0,'3CBB43C1-F841-484D-901A-0C244AE88E6A',0),('20','28',0,3,'A4866F64-E8A7-4565-940B-23E5A6D36A6C',0),('20','29',0,0,'79ff6d84-d45a-4922-9ad9-d5a9b90c9407',0),('20','31',0,0,'5F44AB96-3EEC-4A0C-992A-3638A40B0B02',1),('20','33',0,0,'ff9d1882-ccae-404a-8e18-1d76937254ee',1),('23','33',0,0,'90505743-062d-4afc-be43-d0fd8336ee63',1),('31','32',0,5,'5A6393FF-88AD-404D-A3CE-189FE6375D2D',0),('7','16',2,0,'1d62b2f7-d649-4164-9117-f8f1b4c4579a',1),('7','8',0,0,'89E19408-641A-498B-A74D-B30D2B10AE00',0),('7','9',0,0,'C04A9A88-679C-4106-9FE5-6110DD37B33D',0),('8','9',0,0,'763876A9-3911-4C10-8392-49E9B52421BF',1),('9','10',0,0,'f46c5424-c95b-4ed5-b04a-a90cd917fcb3',1);
/*!40000 ALTER TABLE `offline_nums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `offline_nums_detail_a0`
--

DROP TABLE IF EXISTS `offline_nums_detail_a0`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a0`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_a0` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_a1`
--

DROP TABLE IF EXISTS `offline_nums_detail_a1`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_a1` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_a2`
--

DROP TABLE IF EXISTS `offline_nums_detail_a2`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_a2` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_a3`
--

DROP TABLE IF EXISTS `offline_nums_detail_a3`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a3`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_a3` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_a4`
--

DROP TABLE IF EXISTS `offline_nums_detail_a4`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a4`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_a4` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_b0`
--

DROP TABLE IF EXISTS `offline_nums_detail_b0`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b0`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_b0` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_b1`
--

DROP TABLE IF EXISTS `offline_nums_detail_b1`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_b1` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_b2`
--

DROP TABLE IF EXISTS `offline_nums_detail_b2`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_b2` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_b3`
--

DROP TABLE IF EXISTS `offline_nums_detail_b3`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b3`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_b3` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `offline_nums_detail_b4`
--

DROP TABLE IF EXISTS `offline_nums_detail_b4`;
/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b4`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `offline_nums_detail_b4` AS SELECT 
 1 AS `sender_id`,
 1 AS `receiver_id`,
 1 AS `offline_num`,
 1 AS `finger_print`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user_access_tokens`
--

DROP TABLE IF EXISTS `user_access_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_access_tokens` (
  `access_token` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户token',
  `refresh_token` varchar(40) NOT NULL COMMENT '刷新令牌',
  `user_id` int(10) NOT NULL COMMENT '用户ID',
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `user_access_tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_access_tokens`
--

LOCK TABLES `user_access_tokens` WRITE;
/*!40000 ALTER TABLE `user_access_tokens` DISABLE KEYS */;
INSERT INTO `user_access_tokens` VALUES ('a5f4ac9f2d1a418936fee551b8bdae54b321e980','2936676cd2c0b1abb92a35d4f3dce01001ce7fa1',18),('1ad9ecfbaa68bdd2690a4356b6e0ab9cea8e915b','0e191af05744bf6fc4768396e849aac619a3cb94',20),('ba41b3e9db197fd4d8de625391336524698a2164','929512bb22fc0e818dbedf29aa48925fc34bce22',21),('6c3ecd8ca7757b6d48048ed62b7fe60f0ccbc14e','fe80e1d78ee43da18f379b1eec9c1fdda14ce6bf',22),('d94013a4ddd89fa77f8c7782207aa6e62059f035','6bd85f5a5951501fcee9479a87d2ac5fb711826e',23),('24974f50fd388a62592021440f7cbbce77810fa1','0a4d74c76cbf5b6e360afd5f1126f57973bc7303',26),('4b8ca8ae2cf374f78fb89b1ea5ef71639a5a156a','6286c183ac6319aaf5ef979bdf0bda4d61e9734c',29),('01319e7c2888be398332e88e9011a3b8ae7c61af','da650611e0553287271cfc4193fd4437603fb84c',31);
/*!40000 ALTER TABLE `user_access_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_browse_history`
--

DROP TABLE IF EXISTS `user_browse_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_browse_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `goodsId` int(11) DEFAULT NULL COMMENT '物品编号',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加的时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`)
) ENGINE=MRG_MyISAM DEFAULT CHARSET=utf8mb4 UNION=(`user_browse_history0`,`user_browse_history1`,`user_browse_history2`,`user_browse_history3`,`user_browse_history4`);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_browse_history0`
--

DROP TABLE IF EXISTS `user_browse_history0`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_browse_history0` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `goodsId` int(11) DEFAULT NULL COMMENT '物品编号',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加的时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_browse_history0`
--

LOCK TABLES `user_browse_history0` WRITE;
/*!40000 ALTER TABLE `user_browse_history0` DISABLE KEYS */;
INSERT INTO `user_browse_history0` VALUES (1,10,59,'2021-03-07 14:39:01'),(2,10,58,'2021-03-07 15:28:03'),(3,20,76,'2021-03-20 13:05:16'),(4,20,79,'2021-03-20 15:08:50'),(5,20,80,'2021-03-20 15:29:21');
/*!40000 ALTER TABLE `user_browse_history0` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_browse_history1`
--

DROP TABLE IF EXISTS `user_browse_history1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_browse_history1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `goodsId` int(11) DEFAULT NULL COMMENT '物品编号',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加的时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_browse_history1`
--

LOCK TABLES `user_browse_history1` WRITE;
/*!40000 ALTER TABLE `user_browse_history1` DISABLE KEYS */;
INSERT INTO `user_browse_history1` VALUES (1,21,71,'2021-03-19 15:51:54'),(2,31,84,'2021-03-25 15:31:10'),(3,31,85,'2021-03-25 18:33:54'),(4,31,86,'2021-03-28 12:20:09'),(5,31,87,'2021-03-28 19:02:07'),(6,31,88,'2021-03-31 11:46:57'),(7,31,91,'2021-04-08 16:28:47'),(8,31,90,'2021-04-08 16:54:18'),(9,31,93,'2021-04-08 16:54:46'),(10,31,94,'2021-04-10 17:01:57'),(11,31,95,'2021-04-11 11:55:14'),(12,31,96,'2021-04-13 12:23:30'),(13,31,97,'2021-04-16 15:00:40'),(14,31,98,'2021-04-16 17:32:54');
/*!40000 ALTER TABLE `user_browse_history1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_browse_history2`
--

DROP TABLE IF EXISTS `user_browse_history2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_browse_history2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `goodsId` int(11) DEFAULT NULL COMMENT '物品编号',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加的时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_browse_history2`
--

LOCK TABLES `user_browse_history2` WRITE;
/*!40000 ALTER TABLE `user_browse_history2` DISABLE KEYS */;
INSERT INTO `user_browse_history2` VALUES (3,7,10,'2021-02-20 12:50:21'),(4,7,16,'2021-02-22 23:48:29'),(5,7,22,'2021-02-23 17:50:23'),(6,7,32,'2021-02-27 01:09:10'),(7,7,31,'2021-02-27 11:30:29'),(8,7,36,'2021-02-27 11:40:06'),(9,7,35,'2021-02-27 13:12:49'),(10,7,40,'2021-02-28 11:44:00'),(11,7,41,'2021-02-28 20:11:22'),(12,7,42,'2021-02-28 23:07:25'),(13,7,43,'2021-03-01 11:37:38'),(14,7,44,'2021-03-04 16:16:58'),(15,7,45,'2021-03-04 17:04:21'),(16,7,47,'2021-03-04 21:49:13'),(17,7,49,'2021-03-05 09:53:43'),(18,12,52,'2021-03-07 00:11:43'),(19,7,54,'2021-03-07 00:37:47'),(20,7,55,'2021-03-07 00:43:10'),(21,7,56,'2021-03-07 00:48:50'),(22,7,53,'2021-03-07 12:06:17'),(23,7,61,'2021-03-07 15:31:23'),(24,7,66,'2021-03-07 16:53:29'),(25,22,73,'2021-03-19 16:27:46'),(26,32,89,'2021-04-02 14:13:20');
/*!40000 ALTER TABLE `user_browse_history2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_browse_history3`
--

DROP TABLE IF EXISTS `user_browse_history3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_browse_history3` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `goodsId` int(11) DEFAULT NULL COMMENT '物品编号',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加的时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_browse_history3`
--

LOCK TABLES `user_browse_history3` WRITE;
/*!40000 ALTER TABLE `user_browse_history3` DISABLE KEYS */;
INSERT INTO `user_browse_history3` VALUES (1,3,8,'2021-02-20 12:36:08'),(2,8,18,'2021-02-23 15:24:49'),(3,8,38,'2021-02-28 00:20:12'),(4,8,51,'2021-03-06 00:03:22'),(5,8,67,'2021-03-07 15:48:36'),(6,13,68,'2021-03-07 20:46:45'),(7,18,69,'2021-03-19 14:35:27'),(8,18,70,'2021-03-19 14:40:07'),(9,23,74,'2021-03-19 16:58:38'),(10,18,77,'2021-03-20 14:55:01'),(11,18,78,'2021-03-20 15:03:03');
/*!40000 ALTER TABLE `user_browse_history3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_browse_history4`
--

DROP TABLE IF EXISTS `user_browse_history4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_browse_history4` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `goodsId` int(11) DEFAULT NULL COMMENT '物品编号',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加的时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_browse_history4`
--

LOCK TABLES `user_browse_history4` WRITE;
/*!40000 ALTER TABLE `user_browse_history4` DISABLE KEYS */;
INSERT INTO `user_browse_history4` VALUES (2,9,17,'2021-02-23 00:17:04'),(3,9,24,'2021-02-25 13:41:49'),(4,9,25,'2021-02-25 21:16:01'),(5,9,26,'2021-02-25 21:16:11'),(6,9,27,'2021-02-25 22:55:43'),(7,9,28,'2021-02-25 23:01:46'),(8,9,29,'2021-02-25 23:15:32'),(9,9,30,'2021-02-25 23:21:40'),(10,9,37,'2021-02-27 22:02:26'),(11,9,46,'2021-03-04 21:31:58'),(12,9,48,'2021-03-05 09:09:10'),(13,9,50,'2021-03-05 12:38:59'),(14,19,72,'2021-03-19 16:23:23'),(15,24,75,'2021-03-19 17:39:19'),(16,29,81,'2021-03-21 00:18:39'),(17,19,82,'2021-03-24 20:41:51'),(18,29,83,'2021-03-25 12:33:24');
/*!40000 ALTER TABLE `user_browse_history4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `user_avatar` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'uploads/img/user/default/default.jpg' COMMENT '用户头像',
  `alipay_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付宝ID',
  `device_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'DeviceID',
  `registration_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'RegistrationID',
  `user_gender` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户性别',
  `user_goodsOnSaleCount` int(11) NOT NULL DEFAULT '0' COMMENT '在售物品数量',
  `user_contactCount` int(11) NOT NULL DEFAULT '0' COMMENT '交流数量',
  `last_login_time` datetime NOT NULL COMMENT '上次活跃时间',
  `last_login_address` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '上次活跃IP',
  `createtime` int(11) NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (18,'weirdo','uploads/img/user/20210325/16zo1Dwhk9XF6q4R647xk6U1R6','2088032602057834','','65kycrkspee1a80','m',2,0,'2021-04-24 18:04:35','223.104.246.142',1616131170),(20,'kennen','uploads/img/user/20210320/316y1tpd43ZSoS6v2QiHj15997','2088032859860768','958abc21f8507d0a85e48e36ff59554d3fc22ebd65e31491216556d0241b6d89','','m',2,0,'2021-04-18 22:04:30','223.104.246.149',1616138757),(21,'普通人阿鸡','uploads/img/user/20210319/Tf1mrcvz6n16S141LlY0h0w003','2088722590875793','e1f20f5146a329b5d4f5d6b74af3a6d0c2a01dad4038bba0294910f950f91a7e','','m',1,0,'2021-04-08 13:04:05','36.23.60.171',1616139984),(22,'user_22','uploads/img/user/20210319/16g617Nx61J8fv426Rjvdz3Xg3','2088132864295710','6b192ed4a6bcd8885600d4f5c48191bfffe22ba0384e3fff6e13067df587cf59','','m',1,0,'2021-04-05 16:04:32','36.23.92.111',1616142185),(23,'沃斯尼蝶','uploads/img/user/20210406/8L166UN1770tX57bue2CLW6yW5','2088232315698133','','65kyvf6la5mes5c','m',1,0,'2021-04-04 23:04:37','223.104.244.75',1616144123),(24,'魔人','uploads/img/user/20210319/U1612E6e81fd44wt5divIcct10','2088132510488389','','65kyu4i4tghtbeo','m',1,0,'2021-03-21 10:03:00','36.23.19.16',1616144374),(25,'king','uploads/img/user/20210319/uLy161o641X2HpbX44f4gN3981','2088032132913118','','65kyu4ic03tkbgg','m',0,0,'2021-03-19 16:03:55','112.17.240.92',1616144395),(26,'user_26','uploads/img/user/default/default.jpg','2088132884673510','','65kyvp0lhr0ighs','m',0,0,'2021-04-04 13:04:16','36.18.183.167',1616154674),(27,'user_27','uploads/img/user/default/default.jpg','test1','','65kyt3xbg12jda8','m',0,0,'2021-03-28 06:03:50','36.23.83.231',1616218787),(28,'123','uploads/img/user/20210320/w16KUaM16jS2X482V9dwtwj5z6','2088132006200282','','65kysy6wjy9wav4','m',0,0,'2021-03-21 13:03:01','223.104.160.103',1616242933),(29,'lzcsb','uploads/img/user/20210321/VF71x8q616TO2L657m2Bc21OA2','2088022231609520','','65kyunhjzw24q9s','m',2,0,'2021-04-12 20:04:39','223.104.246.31',1616256730),(31,'luckyXzz！','uploads/img/user/20210408/rTo1f61tGHvl97Gz86U81v8Zb8','2088422649192711','42560498aa65734cd186751135b1012c401a2b2d72b209aa8cbb61f7aaf72c73','','m',2,0,'2021-04-25 09:04:19','36.17.46.40',1616657231),(32,'Zzx笨蛋','uploads/img/user/20210325/VK1RYIw26165hf6f8G716WX2s2','2088122831332092','b6d02fccad9616ec761d8a7ff0d2cd68438d76ee02f78b3229f6b9be9e92c099','','fm',1,0,'2021-04-02 19:04:56','223.104.160.1',1616657350),(33,'我是你爸','uploads/img/user/default/default.jpg','2088132608417925','','65kysv6v3i6a70g','m',0,0,'2021-03-26 14:03:15','112.10.21.168',1616670840),(34,'user_34@✔郑','uploads/img/user/default/default.jpg','2088022080469562','','65kyuuaxhssq3gg','m',0,0,'2021-03-26 22:03:31','115.200.120.66',1616768936),(35,'user_35','uploads/img/user/default/default.jpg','2088232362282444','','65kyuubeth9ro5c','m',0,0,'2021-03-27 21:03:51','115.200.120.66',1616769309),(36,'zsxsb','uploads/img/user/20210328/1JJ6X1G69XjT1h04yaQXdl5t44','2088132507588960','','65kyi5j7uhv1ts0','m',0,0,'2021-03-28 12:03:28','112.17.247.254',1616904416);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_push_info`
--

DROP TABLE IF EXISTS `user_push_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_push_info` (
  `user_id` int(11) DEFAULT NULL,
  `badge` int(11) DEFAULT '0',
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_push_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_push_info`
--

LOCK TABLES `user_push_info` WRITE;
/*!40000 ALTER TABLE `user_push_info` DISABLE KEYS */;
INSERT INTO `user_push_info` VALUES (18,0),(20,21),(21,1),(32,20),(31,0);
/*!40000 ALTER TABLE `user_push_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `offline_from_all0`
--

/*!50001 DROP VIEW IF EXISTS `offline_from_all0`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_from_all0` AS select `offline0`.`offline_id` AS `offline_id`,`offline0`.`sender_id` AS `sender_id`,`offline0`.`receiver_id` AS `receiver_id`,`offline0`.`finger_print` AS `finger_print`,`offline0`.`message_type` AS `message_type`,`offline0`.`message` AS `message`,`offline0`.`send_time` AS `send_time` from `offline0` where (`offline0`.`sender_id`,`offline0`.`receiver_id`) in (select `offline_nums`.`client_a_id`,`offline_nums`.`client_b_id` from `offline_nums` where (`offline_nums`.`offline_num_to_b` > 0) union all select `offline_nums`.`client_b_id`,`offline_nums`.`client_a_id` from `offline_nums` where (`offline_nums`.`offline_num_to_a` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_from_all1`
--

/*!50001 DROP VIEW IF EXISTS `offline_from_all1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_from_all1` AS select `offline1`.`offline_id` AS `offline_id`,`offline1`.`sender_id` AS `sender_id`,`offline1`.`receiver_id` AS `receiver_id`,`offline1`.`finger_print` AS `finger_print`,`offline1`.`message_type` AS `message_type`,`offline1`.`message` AS `message`,`offline1`.`send_time` AS `send_time` from `offline1` where (`offline1`.`sender_id`,`offline1`.`receiver_id`) in (select `offline_nums`.`client_a_id`,`offline_nums`.`client_b_id` from `offline_nums` where (`offline_nums`.`offline_num_to_b` > 0) union all select `offline_nums`.`client_b_id`,`offline_nums`.`client_a_id` from `offline_nums` where (`offline_nums`.`offline_num_to_a` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_from_all2`
--

/*!50001 DROP VIEW IF EXISTS `offline_from_all2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_from_all2` AS select `offline2`.`offline_id` AS `offline_id`,`offline2`.`sender_id` AS `sender_id`,`offline2`.`receiver_id` AS `receiver_id`,`offline2`.`finger_print` AS `finger_print`,`offline2`.`message_type` AS `message_type`,`offline2`.`message` AS `message`,`offline2`.`send_time` AS `send_time` from `offline2` where (`offline2`.`sender_id`,`offline2`.`receiver_id`) in (select `offline_nums`.`client_a_id`,`offline_nums`.`client_b_id` from `offline_nums` where (`offline_nums`.`offline_num_to_b` > 0) union all select `offline_nums`.`client_b_id`,`offline_nums`.`client_a_id` from `offline_nums` where (`offline_nums`.`offline_num_to_a` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_from_all3`
--

/*!50001 DROP VIEW IF EXISTS `offline_from_all3`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_from_all3` AS select `offline3`.`offline_id` AS `offline_id`,`offline3`.`sender_id` AS `sender_id`,`offline3`.`receiver_id` AS `receiver_id`,`offline3`.`finger_print` AS `finger_print`,`offline3`.`message_type` AS `message_type`,`offline3`.`message` AS `message`,`offline3`.`send_time` AS `send_time` from `offline3` where (`offline3`.`sender_id`,`offline3`.`receiver_id`) in (select `offline_nums`.`client_a_id` AS `sender_id`,`offline_nums`.`client_b_id` AS `receiver_id` from `offline_nums` where (`offline_nums`.`offline_num_to_b` > 0) union all select `offline_nums`.`client_b_id` AS `sender_id`,`offline_nums`.`client_a_id` AS `receiver_id` from `offline_nums` where (`offline_nums`.`offline_num_to_a` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_from_all4`
--

/*!50001 DROP VIEW IF EXISTS `offline_from_all4`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_from_all4` AS select `offline4`.`offline_id` AS `offline_id`,`offline4`.`sender_id` AS `sender_id`,`offline4`.`receiver_id` AS `receiver_id`,`offline4`.`finger_print` AS `finger_print`,`offline4`.`message_type` AS `message_type`,`offline4`.`message` AS `message`,`offline4`.`send_time` AS `send_time` from `offline4` where (`offline4`.`sender_id`,`offline4`.`receiver_id`) in (select `offline_nums`.`client_a_id`,`offline_nums`.`client_b_id` from `offline_nums` where (`offline_nums`.`offline_num_to_b` > 0) union all select `offline_nums`.`client_b_id`,`offline_nums`.`client_a_id` from `offline_nums` where (`offline_nums`.`offline_num_to_a` > 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_a0`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a0`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_a0` AS select `offline_nums`.`client_b_id` AS `sender_id`,`offline_nums`.`client_a_id` AS `receiver_id`,`offline_nums`.`offline_num_to_a` AS `offline_num`,`offline0`.`finger_print` AS `finger_print` from (`offline_nums` join `offline0`) where ((`offline_nums`.`client_b_id` = `offline0`.`sender_id`) and (`offline_nums`.`client_a_id` = `offline0`.`receiver_id`) and (`offline_nums`.`offline_num_to_a` > 0) and (`offline_nums`.`fingerprint` = `offline0`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_a1`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_a1` AS select `offline_nums`.`client_b_id` AS `sender_id`,`offline_nums`.`client_a_id` AS `receiver_id`,`offline_nums`.`offline_num_to_a` AS `offline_num`,`offline1`.`finger_print` AS `finger_print` from (`offline_nums` join `offline1`) where ((`offline_nums`.`client_b_id` = `offline1`.`sender_id`) and (`offline_nums`.`client_a_id` = `offline1`.`receiver_id`) and (`offline_nums`.`offline_num_to_a` > 0) and (`offline_nums`.`fingerprint` = `offline1`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_a2`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_a2` AS select `offline_nums`.`client_b_id` AS `sender_id`,`offline_nums`.`client_a_id` AS `receiver_id`,`offline_nums`.`offline_num_to_a` AS `offline_num`,`offline2`.`finger_print` AS `finger_print` from (`offline_nums` join `offline2`) where ((`offline_nums`.`client_b_id` = `offline2`.`sender_id`) and (`offline_nums`.`client_a_id` = `offline2`.`receiver_id`) and (`offline_nums`.`offline_num_to_a` > 0) and (`offline_nums`.`fingerprint` = `offline2`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_a3`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a3`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_a3` AS select `offline_nums`.`client_b_id` AS `sender_id`,`offline_nums`.`client_a_id` AS `receiver_id`,`offline_nums`.`offline_num_to_a` AS `offline_num`,`offline3`.`finger_print` AS `finger_print` from (`offline_nums` join `offline3`) where ((`offline_nums`.`client_b_id` = `offline3`.`sender_id`) and (`offline_nums`.`client_a_id` = `offline3`.`receiver_id`) and (`offline_nums`.`offline_num_to_a` > 0) and (`offline_nums`.`fingerprint` = `offline3`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_a4`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_a4`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_a4` AS select `offline_nums`.`client_b_id` AS `sender_id`,`offline_nums`.`client_a_id` AS `receiver_id`,`offline_nums`.`offline_num_to_a` AS `offline_num`,`offline4`.`finger_print` AS `finger_print` from (`offline_nums` join `offline4`) where ((`offline_nums`.`client_b_id` = `offline4`.`sender_id`) and (`offline_nums`.`client_a_id` = `offline4`.`receiver_id`) and (`offline_nums`.`offline_num_to_a` > 0) and (`offline_nums`.`fingerprint` = `offline4`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_b0`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b0`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_b0` AS select `offline_nums`.`client_a_id` AS `sender_id`,`offline_nums`.`client_b_id` AS `receiver_id`,`offline_nums`.`offline_num_to_b` AS `offline_num`,`offline0`.`finger_print` AS `finger_print` from (`offline_nums` join `offline0`) where ((`offline_nums`.`client_b_id` = `offline0`.`receiver_id`) and (`offline_nums`.`client_a_id` = `offline0`.`sender_id`) and (`offline_nums`.`offline_num_to_b` > 0) and (`offline_nums`.`fingerprint` = `offline0`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_b1`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_b1` AS select `offline_nums`.`client_a_id` AS `sender_id`,`offline_nums`.`client_b_id` AS `receiver_id`,`offline_nums`.`offline_num_to_b` AS `offline_num`,`offline1`.`finger_print` AS `finger_print` from (`offline_nums` join `offline1`) where ((`offline_nums`.`client_b_id` = `offline1`.`receiver_id`) and (`offline_nums`.`client_a_id` = `offline1`.`sender_id`) and (`offline_nums`.`offline_num_to_b` > 0) and (`offline_nums`.`fingerprint` = `offline1`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_b2`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_b2` AS select `offline_nums`.`client_a_id` AS `sender_id`,`offline_nums`.`client_b_id` AS `receiver_id`,`offline_nums`.`offline_num_to_b` AS `offline_num`,`offline2`.`finger_print` AS `finger_print` from (`offline_nums` join `offline2`) where ((`offline_nums`.`client_b_id` = `offline2`.`receiver_id`) and (`offline_nums`.`client_a_id` = `offline2`.`sender_id`) and (`offline_nums`.`offline_num_to_b` > 0) and (`offline_nums`.`fingerprint` = `offline2`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_b3`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b3`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_b3` AS select `offline_nums`.`client_a_id` AS `sender_id`,`offline_nums`.`client_b_id` AS `receiver_id`,`offline_nums`.`offline_num_to_b` AS `offline_num`,`offline3`.`finger_print` AS `finger_print` from (`offline_nums` join `offline3`) where ((`offline_nums`.`client_b_id` = `offline3`.`receiver_id`) and (`offline_nums`.`client_a_id` = `offline3`.`sender_id`) and (`offline_nums`.`offline_num_to_b` > 0) and (`offline_nums`.`fingerprint` = `offline3`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `offline_nums_detail_b4`
--

/*!50001 DROP VIEW IF EXISTS `offline_nums_detail_b4`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`sa_rebuild`@`81.69.14.64` SQL SECURITY DEFINER */
/*!50001 VIEW `offline_nums_detail_b4` AS select `offline_nums`.`client_a_id` AS `sender_id`,`offline_nums`.`client_b_id` AS `receiver_id`,`offline_nums`.`offline_num_to_b` AS `offline_num`,`offline4`.`finger_print` AS `finger_print` from (`offline_nums` join `offline4`) where ((`offline_nums`.`client_b_id` = `offline4`.`receiver_id`) and (`offline_nums`.`client_a_id` = `offline4`.`sender_id`) and (`offline_nums`.`offline_num_to_b` > 0) and (`offline_nums`.`fingerprint` = `offline4`.`finger_print`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25  9:48:42
