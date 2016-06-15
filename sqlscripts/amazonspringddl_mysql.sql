--
-- Table structure for table 'product'
--

DROP TABLE IF EXISTS product;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 'product' (
  'productid' bigint(20) NOT NULL AUTO_INCREMENT,
  'name' varchar(255) DEFAULT NULL,
  'description' varchar(255) DEFAULT NULL,
  'price' varchar(255) DEFAULT NULL,
  'userid' bigint(20) DEFAULT NULL,
  PRIMARY KEY ('productid')
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table 'user_roles'
--

DROP TABLE IF EXISTS 'user_roles';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 'user_roles' (
  'userid' int(11) NOT NULL,
  'username' varchar(45) NOT NULL,
  'role' varchar(45) NOT NULL,
  'rating' decimal(11,1) DEFAULT '0.0',
  PRIMARY KEY ('userid'),
  UNIQUE KEY 'uni_username_role' ('role','username'),
  KEY 'FK734299492AF6EA0A' ('userid'),
  CONSTRAINT 'FK734299492AF6EA0A' FOREIGN KEY ('userid') REFERENCES 'users' ('userid')
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table 'users'
--

DROP TABLE IF EXISTS 'users';
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 'users' (
  'userid' int(11) NOT NULL AUTO_INCREMENT,
  'username' varchar(45) NOT NULL,
  'password' varchar(450) NOT NULL,
  'enabled' tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY ('userid'),
  UNIQUE KEY 'userid_UNIQUE' ('userid')
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-14  9:28:46
