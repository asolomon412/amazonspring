-- Database: amazonspring

-- DROP DATABASE amazonspring;
-- This create statement is not needed for Heroku DB

CREATE DATABASE amazonspring
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
       
--
-- Table structure for table 'product'
--

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  productid serial NOT NULL,
  name varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  price varchar(255) DEFAULT NULL,
  userid bigint DEFAULT NULL,
  PRIMARY KEY (productid)
);


--
-- Table structure for table 'users'
--

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  userid serial PRIMARY KEY NOT NULL UNIQUE,
  username varchar(45) NOT NULL,
  password varchar(450) NOT NULL,
  enabled smallint NOT NULL DEFAULT 1
);

--
-- Table structure for table 'user_roles'
--

	DROP TABLE IF EXISTS user_roles;

	CREATE TABLE user_roles (
	  userid integer PRIMARY KEY NOT NULL REFERENCES users (userid),
	  username varchar(45) NOT NULL,
	  role varchar(45) NOT NULL,
	  rating decimal(11,1) DEFAULT '0.0'
	);

