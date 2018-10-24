-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,ANSI_QUOTES,MYSQL323' */;


--
-- Create schema contactmgr
--

CREATE DATABASE IF NOT EXISTS contactmgr;
USE contactmgr;

DROP TABLE IF EXISTS "contact";
CREATE TABLE "contact" (
  "oid" int(10) unsigned NOT NULL,
  "name" varchar(128) NOT NULL,
  "gender" enum('Male','Female') default NULL,
  "company" varchar(512) default NULL,
  "note" text,
  "im" varchar(128) default NULL,
  "blog" varchar(512) default NULL,
  "webpage" varchar(512) default NULL,
  "birthday" datetime default NULL,
  PRIMARY KEY  ("oid"),
  UNIQUE KEY "name_unique" ("name")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "contact" DISABLE KEYS */;
/*!40000 ALTER TABLE "contact" ENABLE KEYS */;


DROP TABLE IF EXISTS "contact_address";
CREATE TABLE "contact_address" (
  "contact" int(10) unsigned NOT NULL,
  "address_type" varchar(45) NOT NULL,
  "country" varchar(64) default NULL,
  "state" varchar(32) default NULL,
  "city" varchar(128) default NULL,
  "detail" text,
  "postal_code" varchar(32) default NULL,
  PRIMARY KEY  ("contact","address_type"),
  CONSTRAINT "FK_contact_address_1" FOREIGN KEY ("contact") REFERENCES "contact" ("oid")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "contact_address" DISABLE KEYS */;
/*!40000 ALTER TABLE "contact_address" ENABLE KEYS */;


DROP TABLE IF EXISTS "contact_category";
CREATE TABLE "contact_category" (
  "contact" int(10) unsigned NOT NULL,
  "category" varchar(64) NOT NULL,
  PRIMARY KEY  ("contact","category"),
  CONSTRAINT "FK_contact_category_1" FOREIGN KEY ("contact") REFERENCES "contact" ("oid")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "contact_category" DISABLE KEYS */;
/*!40000 ALTER TABLE "contact_category" ENABLE KEYS */;


DROP TABLE IF EXISTS "contact_email";
CREATE TABLE "contact_email" (
  "contact" int(10) unsigned NOT NULL,
  "email_type" varchar(32) NOT NULL,
  "email_content" varchar(128) default NULL,
  PRIMARY KEY  ("contact","email_type"),
  CONSTRAINT "FK_contact_email_1" FOREIGN KEY ("contact") REFERENCES "contact" ("oid")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "contact_email" DISABLE KEYS */;
/*!40000 ALTER TABLE "contact_email" ENABLE KEYS */;


DROP TABLE IF EXISTS "contact_phone";
CREATE TABLE "contact_phone" (
  "contact" int(10) unsigned NOT NULL,
  "phone_type" varchar(64) NOT NULL,
  "phone_content" varchar(128) default NULL,
  PRIMARY KEY  ("contact","phone_type"),
  CONSTRAINT "FK_contact_phone_1" FOREIGN KEY ("contact") REFERENCES "contact" ("oid")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "contact_phone" DISABLE KEYS */;
/*!40000 ALTER TABLE "contact_phone" ENABLE KEYS */;


DROP TABLE IF EXISTS "sequence";
CREATE TABLE "sequence" (
  "name" varchar(32) NOT NULL,
  "count" int(10) unsigned NOT NULL,
  PRIMARY KEY  ("name")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "sequence" DISABLE KEYS */;
INSERT INTO "sequence" ("name","count") VALUES 
 ('category',0),
 ('contact',0);
/*!40000 ALTER TABLE "sequence" ENABLE KEYS */;


DROP TABLE IF EXISTS "support_category";
CREATE TABLE "support_category" (
  "value" varchar(128) NOT NULL,
  "id" int(10) unsigned NOT NULL,
  PRIMARY KEY  ("id")
) TYPE=InnoDB;

/*!40000 ALTER TABLE "support_category" DISABLE KEYS */;
/*!40000 ALTER TABLE "support_category" ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
