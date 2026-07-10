/*
SQLyog  v12.2.6 (64 bit)
MySQL - 8.0.23 : Database - health_db
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`health` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `health`;

/*Table structure for table `t_checkgroup` */

DROP TABLE IF EXISTS `t_checkgroup`;

CREATE TABLE `t_checkgroup` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(32) DEFAULT NULL,
  `name` VARCHAR(32) DEFAULT NULL,
  `helpCode` VARCHAR(32) DEFAULT NULL,
  `sex` CHAR(1) DEFAULT NULL,
  `remark` VARCHAR(128) DEFAULT NULL,
  `attention` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `t_checkgroup_checkitem` */

DROP TABLE IF EXISTS `t_checkgroup_checkitem`;

CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` INT NOT NULL DEFAULT '0',
  `checkitem_id` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`checkgroup_id`,`checkitem_id`),
  KEY `item_id` (`checkitem_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `item_id` FOREIGN KEY (`checkitem_id`) REFERENCES `t_checkitem` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `t_checkitem` */

DROP TABLE IF EXISTS `t_checkitem`;

CREATE TABLE `t_checkitem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(16) DEFAULT NULL,
  `name` VARCHAR(32) DEFAULT NULL,
  `sex` CHAR(1) DEFAULT NULL,
  `age` VARCHAR(32) DEFAULT NULL,
  `price` FLOAT DEFAULT NULL,
  `type` CHAR(1) DEFAULT NULL COMMENT '查检项类型,分为检查和检验两种',
  `attention` VARCHAR(128) DEFAULT NULL,
  `remark` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fileNumber` VARCHAR(32) DEFAULT NULL,
  `name` VARCHAR(32) DEFAULT NULL,
  `sex` VARCHAR(8) DEFAULT NULL,
  `idCard` VARCHAR(18) DEFAULT NULL,
  `phoneNumber` VARCHAR(11) DEFAULT NULL,
  `regTime` DATE DEFAULT NULL,
  `password` VARCHAR(32) DEFAULT NULL,
  `email` VARCHAR(32) DEFAULT NULL,
  `birthday` DATE DEFAULT NULL,
  `remark` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('1', null, '刘一', '1', '123456789000999999', '18511279942', '2021-09-08', null, null, null, null);
INSERT INTO `t_member` VALUES ('2', null, '陈二', '1', '132333333333333', '13412345678', '2021-09-11', null, null, null, null);
INSERT INTO `t_member` VALUES ('3', null, '张三', null, null, '18511279943', '2019-03-13', null, null, null, null);
INSERT INTO `t_member` VALUES ('4', null, '李四', null, null, null, '2021-09-06', null, null, null, null);
INSERT INTO `t_member` VALUES ('5', null, '王五', null, null, null, '2021-09-04', null, null, null, null);
INSERT INTO `t_member` VALUES ('6', null, '赵六', null, null, null, '2021-09-06', null, null, null, null);
INSERT INTO `t_member` VALUES ('7', null, '孙七', null, null, null, '2021-09-10', null, null, null, null);
INSERT INTO `t_member` VALUES ('8', null, '周八', null, null, null, '2021-09-01', null, null, null, null);
INSERT INTO `t_member` VALUES ('9', null, '吴九', null, null, null, '2021-09-02', null, null, null, null);
INSERT INTO `t_member` VALUES ('10', null, '郑十', null, null, null, '2021-09-01', null, null, null, null);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) DEFAULT NULL,
  `linkUrl` VARCHAR(128) DEFAULT NULL,
  `path` VARCHAR(128) DEFAULT NULL,
  `priority` INT DEFAULT NULL,
  `icon` VARCHAR(64) DEFAULT NULL,
  `description` VARCHAR(128) DEFAULT NULL,
  `parentMenuId` INT DEFAULT NULL,
  `level` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`parentMenuId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

INSERT  INTO `t_menu`(`id`,`name`,`linkUrl`,`path`,`priority`,`icon`,`description`,`parentMenuId`,`level`) VALUES 

(1,'会员管理',NULL,'2',1,'fa-user-md',NULL,NULL,1),

(2,'会员档案','member.html','/2-1',1,NULL,NULL,1,2),

(3,'体检上传',NULL,'/2-2',2,NULL,NULL,1,2),

(4,'会员统计',NULL,'/2-3',3,NULL,NULL,1,2),

(5,'预约管理',NULL,'3',2,'fa-tty',NULL,NULL,1),

(6,'预约列表','ordersettinglist.html','/3-1',1,NULL,NULL,5,2),

(7,'预约设置','ordersetting.html','/3-2',2,NULL,NULL,5,2),

(8,'套餐管理','setmeal.html','/3-3',3,NULL,NULL,5,2),

(9,'检查组管理','checkgroup.html','/3-4',4,NULL,NULL,5,2),

(10,'检查项管理','checkitem.html','/3-5',5,NULL,NULL,5,2),

(11,'健康评估',NULL,'4',3,'fa-stethoscope',NULL,NULL,1),

(12,'中医体质辨识',NULL,'/4-1',1,NULL,NULL,11,2),

(13,'统计分析',NULL,'5',4,'fa-heartbeat',NULL,NULL,1),

(14,'会员数量','report_member.html','/5-1',1,NULL,NULL,13,2),

(15,'系统设置',NULL,'6',5,'fa-users',NULL,NULL,1),

(16,'菜单管理','menu.html','/6-1',1,NULL,NULL,15,2),

(17,'权限管理','permission.html','/6-2',2,NULL,NULL,15,2),

(18,'角色管理','role.html','/6-3',3,NULL,NULL,15,2),

(19,'用户管理','user.html','/6-4',4,NULL,NULL,15,2),

(20,'套餐占比','report_setmeal.html','/5-2',2,NULL,NULL,13,2),

(21,'运营数据','report_business.html','/5-3',3,NULL,NULL,13,2);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT DEFAULT NULL COMMENT '员会id',
  `orderDate` DATE DEFAULT NULL COMMENT '约预日期',
  `orderType` VARCHAR(8) DEFAULT NULL COMMENT '约预类型 电话预约/客户端预约',
  `orderStatus` VARCHAR(8) DEFAULT NULL COMMENT '预约状态（是否到诊）',
  `setmeal_id` INT DEFAULT NULL COMMENT '餐套id',
  PRIMARY KEY (`id`),
  KEY `key_member_id` (`member_id`),
  KEY `key_setmeal_id` (`setmeal_id`),
  CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`),
  CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



/*Table structure for table `t_ordersetting` */

DROP TABLE IF EXISTS `t_ordersetting`;

CREATE TABLE `t_ordersetting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orderDate` DATE DEFAULT NULL COMMENT '约预日期',
  `number` INT DEFAULT NULL COMMENT '可预约人数',
  `reservations` INT DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) DEFAULT NULL,
  `keyword` VARCHAR(64) DEFAULT NULL,
  `description` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

INSERT  INTO `t_permission`(`id`,`name`,`keyword`,`description`) VALUES 

(1,'新增检查项','CHECKITEM_ADD',NULL),

(2,'删除检查项','CHECKITEM_DELETE',NULL),

(3,'编辑检查项','CHECKITEM_EDIT',NULL),

(4,'查询检查项','CHECKITEM_QUERY',NULL),

(5,'新增检查组','CHECKGROUP_ADD',NULL),

(6,'删除检查组','CHECKGROUP_DELETE',NULL),

(7,'编辑检查组','CHECKGROUP_EDIT',NULL),

(8,'查询检查组','CHECKGROUP_QUERY',NULL),

(9,'新增套餐','SETMEAL_ADD',NULL),

(10,'删除套餐','SETMEAL_DELETE',NULL),

(11,'编辑套餐','SETMEAL_EDIT',NULL),

(12,'查询套餐','SETMEAL_QUERY',NULL),

(13,'预约设置','ORDERSETTING',NULL),

(14,'查看统计报表','REPORT_VIEW',NULL),

(15,'新增菜单','MENU_ADD',NULL),

(16,'删除菜单','MENU_DELETE',NULL),

(17,'编辑菜单','MENU_EDIT',NULL),

(18,'查询菜单','MENU_QUERY',NULL),

(19,'新增角色','ROLE_ADD',NULL),

(20,'删除角色','ROLE_DELETE',NULL),

(21,'编辑角色','ROLE_EDIT',NULL),

(22,'查询角色','ROLE_QUERY',NULL),

(23,'新增用户','USER_ADD',NULL),

(24,'删除用户','USER_DELETE',NULL),

(25,'编辑用户','USER_EDIT',NULL),

(26,'查询用户','USER_QUERY',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) DEFAULT NULL,
  `keyword` VARCHAR(64) DEFAULT NULL,
  `description` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

INSERT  INTO `t_role`(`id`,`name`,`keyword`,`description`) VALUES 

(1,'系统管理员','ROLE_ADMIN',NULL),

(2,'健康管理师','ROLE_HEALTH_MANAGER',NULL);

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `role_id` INT NOT NULL,
  `menu_id` INT NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_Reference_10` (`menu_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_menu` */

INSERT  INTO `t_role_menu`(`role_id`,`menu_id`) VALUES 

(1,1),

(2,1),

(1,2),

(2,2),

(1,3),

(2,3),

(1,4),

(2,4),

(1,5),

(1,6),

(1,7),

(1,8),

(1,9),

(1,10),

(1,11),

(1,12),

(1,13),

(1,14),

(1,15),

(1,16),

(1,17),

(1,18),

(1,19),

(1,20),

(1,21);

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` INT NOT NULL,
  `permission_id` INT NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_Reference_12` (`permission_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

INSERT  INTO `t_role_permission`(`role_id`,`permission_id`) VALUES 

(1,1),

(2,1),

(1,2),

(1,3),

(2,3),

(1,4),

(2,4),

(1,5),

(2,5),

(1,6),

(2,6),

(1,7),

(2,7),

(1,8),

(2,8),

(1,9),

(2,9),

(1,10),

(2,10),

(1,11),

(2,11),

(1,12),

(2,12),

(1,13),

(2,13),

(1,14),

(2,14),

(1,15),

(1,16),

(1,17),

(1,18),

(1,19),

(1,20),

(1,21),

(1,22),

(1,23),

(1,24),

(1,25),

(1,26);

/*Table structure for table `t_setmeal` */

DROP TABLE IF EXISTS `t_setmeal`;

CREATE TABLE `t_setmeal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) DEFAULT NULL,
  `code` VARCHAR(8) DEFAULT NULL,
  `helpCode` VARCHAR(16) DEFAULT NULL,
  `sex` CHAR(1) DEFAULT NULL,
  `age` VARCHAR(32) DEFAULT NULL,
  `price` FLOAT DEFAULT NULL,
  `remark` VARCHAR(128) DEFAULT NULL,
  `attention` VARCHAR(128) DEFAULT NULL,
  `img` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `t_setmeal_checkgroup` */

DROP TABLE IF EXISTS `t_setmeal_checkgroup`;

CREATE TABLE `t_setmeal_checkgroup` (
  `setmeal_id` INT NOT NULL DEFAULT '0',
  `checkgroup_id` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`setmeal_id`,`checkgroup_id`),
  KEY `checkgroup_key` (`checkgroup_id`),
  CONSTRAINT `checkgroup_key` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `birthday` DATE DEFAULT NULL,
  `gender` VARCHAR(1) DEFAULT NULL,
  `username` VARCHAR(32) DEFAULT NULL,
  `password` VARCHAR(256) DEFAULT NULL,
  `remark` VARCHAR(32) DEFAULT NULL,
  `station` VARCHAR(1) DEFAULT NULL,
  `telephone` VARCHAR(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

INSERT  INTO `t_user`(`id`,`birthday`,`gender`,`username`,`password`,`remark`,`station`,`telephone`) VALUES 

(1,NULL,NULL,'admin','$2a$10$LPbhiutR34wKvjv3Qb8zBu7piw5hG3.IlQMAI3e/D1Y0DJ/mMSkYa',NULL,NULL,NULL),

(2,NULL,NULL,'xiaoming','$2a$10$3xW2nBjwBM3rx1LoYprVsemNri5bvxeOd/QfmO7UDFQhW2HRHLi.C',NULL,NULL,NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_8` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

INSERT  INTO `t_user_role`(`user_id`,`role_id`) VALUES 

(1,1),

(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
