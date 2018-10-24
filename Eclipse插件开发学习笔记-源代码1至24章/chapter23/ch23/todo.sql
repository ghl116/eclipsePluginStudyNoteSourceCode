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
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema todo
--

CREATE DATABASE IF NOT EXISTS todo;
USE todo;

--
-- Definition of table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `name` varchar(32) NOT NULL,
  `count` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sequence`
--

/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`name`,`count`) VALUES 
 ('todo_item',0),
 ('todo_series_info',0);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;


--
-- Definition of table `todo_item`
--

DROP TABLE IF EXISTS `todo_item`;
CREATE TABLE `todo_item` (
  `id` int(10) unsigned NOT NULL,
  `type` varchar(32) NOT NULL,
  `start_date` datetime NOT NULL,
  `start_time` int(10) unsigned NOT NULL,
  `stop_time` int(10) unsigned NOT NULL,
  `subject` varchar(64) NOT NULL,
  `content` text,
  `series` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_todo_item_series` (`series`),
  CONSTRAINT `FK_todo_item_series` FOREIGN KEY (`series`) REFERENCES `todo_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `todo_item`
--

/*!40000 ALTER TABLE `todo_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `todo_item` ENABLE KEYS */;


--
-- Definition of table `todo_series`
--

DROP TABLE IF EXISTS `todo_series`;
CREATE TABLE `todo_series` (
  `id` int(10) unsigned NOT NULL,
  `series_type` varchar(32) NOT NULL,
  `stop_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  CONSTRAINT `FK_todo_series_item` FOREIGN KEY (`id`) REFERENCES `todo_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `todo_series`
--

/*!40000 ALTER TABLE `todo_series` DISABLE KEYS */;
/*!40000 ALTER TABLE `todo_series` ENABLE KEYS */;


--
-- Definition of table `todo_series_info`
--

DROP TABLE IF EXISTS `todo_series_info`;
CREATE TABLE `todo_series_info` (
  `id` int(10) unsigned NOT NULL,
  `baseDate` datetime default NULL,
  `next` int(10) unsigned default NULL,
  `span` int(10) unsigned default NULL,
  `day_type` int(10) unsigned default NULL,
  `week_data` varchar(7) default NULL,
  `month_day` int(10) unsigned default NULL,
  `type` varchar(32) NOT NULL,
  `y_month` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_todo_series_info_next` (`next`),
  CONSTRAINT `FK_todo_series_info_next` FOREIGN KEY (`next`) REFERENCES `todo_series_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `todo_series_info`
--

/*!40000 ALTER TABLE `todo_series_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `todo_series_info` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
