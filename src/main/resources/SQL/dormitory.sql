/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : dormitory

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2022-10-25 20:41:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for absent
-- ----------------------------
DROP TABLE IF EXISTS `absent`;
CREATE TABLE `absent` (
  `id` int NOT NULL AUTO_INCREMENT,
  `building_id` int DEFAULT NULL,
  `dormitory_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `dormitory_admin_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `building_id` (`building_id`),
  KEY `dormitory_id` (`dormitory_id`),
  KEY `student_id` (`student_id`),
  KEY `dormitory_admin_id` (`dormitory_admin_id`),
  CONSTRAINT `absent_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`),
  CONSTRAINT `absent_ibfk_2` FOREIGN KEY (`dormitory_id`) REFERENCES `dormitory` (`id`),
  CONSTRAINT `absent_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `absent_ibfk_4` FOREIGN KEY (`dormitory_admin_id`) REFERENCES `dormitory_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of absent
-- ----------------------------

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `building_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `dormitory_admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('1', '1号楼', '计算机学院宿舍楼', '1');
INSERT INTO `building` VALUES ('2', '2号楼', '电信学院宿舍楼', '1');
INSERT INTO `building` VALUES ('3', '3号楼', '音乐学院宿舍楼', '1');
INSERT INTO `building` VALUES ('4', '999', '计科院', '1');
INSERT INTO `building` VALUES ('5', '99号楼', '计科院', '1');
INSERT INTO `building` VALUES ('6', '8112号楼', '计科院11', '3');
INSERT INTO `building` VALUES ('15', '88号楼', '美院', '2');

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `building_id` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int DEFAULT NULL,
  `available` int DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `building_id` (`building_id`),
  CONSTRAINT `dormitory_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('1', '1', '516', '10', '6', '88888866');
INSERT INTO `dormitory` VALUES ('2', '1', '517', '18', '9', '666666');
INSERT INTO `dormitory` VALUES ('3', '2', '321', '9', '3', '111111');

-- ----------------------------
-- Table structure for dormitory_admin
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_admin`;
CREATE TABLE `dormitory_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of dormitory_admin
-- ----------------------------
INSERT INTO `dormitory_admin` VALUES ('1', 'SleepWalker', '123abcABC', '张三', '男', '13312345678');
INSERT INTO `dormitory_admin` VALUES ('2', 'ww', '123123', '李四', '男', '13682738943');
INSERT INTO `dormitory_admin` VALUES ('3', 'abc', '123abcABC', '李四111', '女', '123222222111');
INSERT INTO `dormitory_admin` VALUES ('6', 'SleepWalker2', '123abcABC', '11', '男', '1234324324');
INSERT INTO `dormitory_admin` VALUES ('7', 'SleepWalker3', '123abcABC', '无名式', '男', '13682738943');

-- ----------------------------
-- Table structure for moveout
-- ----------------------------
DROP TABLE IF EXISTS `moveout`;
CREATE TABLE `moveout` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `dormitory_id` int DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `dormitory_id` (`dormitory_id`),
  CONSTRAINT `moveout_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `moveout_ibfk_2` FOREIGN KEY (`dormitory_id`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of moveout
-- ----------------------------
INSERT INTO `moveout` VALUES ('1', '1', '1', '毕业', '2022-10-09 13:07:45');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dormitory_id` int DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `dormitory_id` (`dormitory_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`dormitory_id`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '1', '王伟', '男', '1', '入住', '2022-10-18 22:55:56');
INSERT INTO `student` VALUES ('11', '72', '王博简', '女', '3', '迁出', '2022-10-17 20:56:12');
INSERT INTO `student` VALUES ('13', '77', '吕正浩', '女', '3', '迁出', '2022-10-17 20:56:42');
INSERT INTO `student` VALUES ('15', '30', '康鸿信', '女', '3', '迁出', '2018-04-02 05:57:49');
INSERT INTO `student` VALUES ('16', '74', '赵弘济', '女', '2', '入住', '2018-04-03 19:12:12');
INSERT INTO `student` VALUES ('17', '65', '林力夫', '男', '3', '入住', '2022-10-17 20:56:28');
INSERT INTO `student` VALUES ('18', '69', '周承泽', '女', '1', '入住', '2018-04-03 00:51:35');
INSERT INTO `student` VALUES ('19', '36', '熊阳羽', '女', '3', '入住', '2018-04-03 20:06:47');
INSERT INTO `student` VALUES ('22', '56', '夏侯才良', '女', '1', '入住', '2022-10-17 21:30:28');
INSERT INTO `student` VALUES ('23', '99', '章凝丹', '女', '1', '入住', '2022-10-17 21:34:33');
INSERT INTO `student` VALUES ('24', '999', '江欣可', '女', '3', '入住', '2022-10-17 23:45:49');
INSERT INTO `student` VALUES ('25', '678', '余睿思', '女', '1', '入住', '2022-10-17 23:50:16');
INSERT INTO `student` VALUES ('26', '653', '段琼英', '女', '1', '入住', '2022-10-17 23:51:18');
INSERT INTO `student` VALUES ('27', '79', '周承泽', '女', '1', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('29', '20', '康鸿信', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('30', '73', '周承泽', '男', '3', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('31', '83', '尹力夫', '男', '1', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('32', '36', '熊阳羽', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('33', '4', '林良弼', '女', '3', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('34', '80', '王博简', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('35', '98', '杨昊焱', '女', '1', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('37', '682', '杨昊焱', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('38', '849', '汤石英 ', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('39', '921', '权欣琳 ', '男', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('40', '95', '慎向珊 ', '女', '3', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('41', '772', '谢展鹏', '女', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('42', '350', '空霞飞 ', '女', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('43', '950', '林力夫', '男', '3', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('44', '963', '甘斯雅 ', '男', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('45', '389', '江忆山 ', '男', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('46', '620', '权欣琳 ', '女', '1', '入住', '2022-10-19 20:22:00');

-- ----------------------------
-- Table structure for system_admin
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of system_admin
-- ----------------------------
INSERT INTO `system_admin` VALUES ('1', 'SleepWalker_admin', '123abcABC', '管理员1', '888888');
SET FOREIGN_KEY_CHECKS=1;
