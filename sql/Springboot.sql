/*
SQLyog Ultimate v8.32 
MySQL - 5.7.10-log : Database - test1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test1`;

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `tokenStr` varchar(40) DEFAULT NULL,
  `losetime` datetime DEFAULT NULL,
  `userfullname` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `token` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `userfullname` varchar(10) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `isdeleted` varchar(2) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`userfullname`,`createtime`,`isdeleted`,`sex`,`address`) values (1,'zhangsan','zhangsan','张三','2019-02-21',NULL,'男','地址1'),(2,'1','1','1','2019-02-21',NULL,'女','1'),(3,'2','2','2','2019-02-21',NULL,'女','2'),(4,'2','2','2','2019-02-21',NULL,'女','2'),(8,'2','2','2','2019-02-21',NULL,'女','2'),(13,'33','33','33update','2019-02-21',NULL,'男','33'),(17,'99','99','99','2019-02-21',NULL,'女','99'),(20,'777','777','777','2019-02-21',NULL,'男','77777777777');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
