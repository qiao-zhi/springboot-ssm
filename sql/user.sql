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
/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (1,'qlq1','1995-02-03 00:00:00','男','地址1');
insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (2,'qlq2','1995-02-03 00:00:00','男','地址2');
insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (3,'qlq3','1995-02-03 00:00:00','男','地址3');
insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (4,'qlq4','1995-02-03 00:00:00','男','地址4');
insert  into `user`(`id`,`username`,`birthday`,`sex`,`address`) values (5,'qlq5','1995-02-03 00:00:00','男','地址5');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
