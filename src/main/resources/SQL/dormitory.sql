/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80028
Source Host           : localhost:3306
Source Database       : dormitory

Target Server Type    : MYSQL
Target Server Version : 80028
File Encoding         : 65001

Date: 2022-12-03 13:39:22
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
  `create_date` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of absent
-- ----------------------------
INSERT INTO `absent` VALUES ('8', '1', '1', '16', '1', '2022-12-06 16:00:00.000', '请假');
INSERT INTO `absent` VALUES ('9', '1', '1', '1', '1', '2022-12-23 05:02:00.000', '请假');
INSERT INTO `absent` VALUES ('10', '2', '3', '15', '1', '2022-12-01-08 20:08.000', '请假');
INSERT INTO `absent` VALUES ('11', '1', '2', '32', '1', '2022-12-03 05:31:22.000', '请假');
INSERT INTO `absent` VALUES ('12', '1', '2', '32', '1', '2021-12-23 20:08:07.000', '迟到');
INSERT INTO `absent` VALUES ('13', '1', '2', '45', '1', '2021-06-09 14:08:05.000', '迟到');
INSERT INTO `absent` VALUES ('14', '1', '2', '42', '1', '2022-12-03 05:35:55.000', '迟到');
INSERT INTO `absent` VALUES ('15', '2', '3', '24', '1', '2022-12-03 05:37:24.000', '迟到');
INSERT INTO `absent` VALUES ('16', '1', '2', '39', '1', '2022-12-03 05:37:56.000', '请假');
INSERT INTO `absent` VALUES ('17', '2', '3', '33', '1', '2022-12-03 05:38:54.000', '跑了');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('1', '1号楼', '计算机学院宿舍楼', '1');
INSERT INTO `building` VALUES ('2', '2号楼', '电信学院宿舍楼', '1');
INSERT INTO `building` VALUES ('3', '3号楼', '音乐学院宿舍楼', '1');
INSERT INTO `building` VALUES ('4', '999', '计科院', '1');
INSERT INTO `building` VALUES ('15', '88号楼', '美院', '2');
INSERT INTO `building` VALUES ('16', '231', '舞蹈学院', '2');
INSERT INTO `building` VALUES ('17', '110', '计科院', '1');
INSERT INTO `building` VALUES ('18', '38', '舞蹈学院', '3');
INSERT INTO `building` VALUES ('21', '441', '计科院', '5');
INSERT INTO `building` VALUES ('22', '38', '音乐学院', '4');
INSERT INTO `building` VALUES ('23', '122', '舞蹈学院', '3');
INSERT INTO `building` VALUES ('24', '208', '音乐学院', '5');
INSERT INTO `building` VALUES ('25', '427', '美术学院', '4');
INSERT INTO `building` VALUES ('26', '303', '舞蹈学院', '3');
INSERT INTO `building` VALUES ('27', '452', '计科院', '3');
INSERT INTO `building` VALUES ('28', '97', '舞蹈学院', '2');
INSERT INTO `building` VALUES ('29', '205', '舞蹈学院', '5');
INSERT INTO `building` VALUES ('30', '447', '计科院', '2');
INSERT INTO `building` VALUES ('31', '30', '外语学院', '4');
INSERT INTO `building` VALUES ('32', '372', '美术学院', '3');
INSERT INTO `building` VALUES ('33', '72', '舞蹈学院', '5');
INSERT INTO `building` VALUES ('34', '208', '外语学院', '3');
INSERT INTO `building` VALUES ('35', '358', '美术学院', '4');
INSERT INTO `building` VALUES ('36', '227', '计科院', '4');
INSERT INTO `building` VALUES ('37', '58', '外语学院', '2');
INSERT INTO `building` VALUES ('38', '399', '美术学院', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('1', '1', '516', '10', '5', '88888866');
INSERT INTO `dormitory` VALUES ('2', '1', '517', '18', '9', '666666');
INSERT INTO `dormitory` VALUES ('3', '2', '321', '9', '5', '111111');
INSERT INTO `dormitory` VALUES ('5', '1', '506', '5', '4', '75192194744');
INSERT INTO `dormitory` VALUES ('6', '4', '692', '11', '10', '68669995345');
INSERT INTO `dormitory` VALUES ('8', '3', '560', '2', '1', '52338405002');
INSERT INTO `dormitory` VALUES ('9', '4', '210', '19', '12', '50207095861');
INSERT INTO `dormitory` VALUES ('10', '2', '237', '30', '20', '44897696013');
INSERT INTO `dormitory` VALUES ('35', '3', '805', '25', '18', '20328310919');
INSERT INTO `dormitory` VALUES ('36', '3', '362', '21', '5', '83869762347');
INSERT INTO `dormitory` VALUES ('37', '1', '611', '25', '3', '25684325113');
INSERT INTO `dormitory` VALUES ('45', '3', '503', '8', '8', '111111111');
INSERT INTO `dormitory` VALUES ('47', '31', '106', '24', '14', '16797444641');
INSERT INTO `dormitory` VALUES ('48', '29', '683', '22', '4', '94550807355');
INSERT INTO `dormitory` VALUES ('49', '34', '189', '25', '2', '6089729880');
INSERT INTO `dormitory` VALUES ('50', '34', '314', '23', '19', '45226441456');
INSERT INTO `dormitory` VALUES ('51', '35', '58', '25', '14', '76972117612');
INSERT INTO `dormitory` VALUES ('52', '31', '905', '24', '1', '60469456541');
INSERT INTO `dormitory` VALUES ('53', '37', '521', '21', '19', '8569569381');
INSERT INTO `dormitory` VALUES ('54', '37', '522', '23', '10', '87995686282');
INSERT INTO `dormitory` VALUES ('55', '15', '933', '22', '20', '73725719591');
INSERT INTO `dormitory` VALUES ('58', '21', '223', '21', '14', '83788615862');
INSERT INTO `dormitory` VALUES ('59', '34', '600', '24', '16', '59695076797');
INSERT INTO `dormitory` VALUES ('60', '23', '739', '21', '7', '28363952125');
INSERT INTO `dormitory` VALUES ('61', '34', '184', '22', '7', '40793291575');
INSERT INTO `dormitory` VALUES ('62', '37', '687', '22', '19', '82701035488');
INSERT INTO `dormitory` VALUES ('64', '33', '697', '24', '4', '68833248667');
INSERT INTO `dormitory` VALUES ('65', '21', '892', '21', '11', '18894821515');
INSERT INTO `dormitory` VALUES ('66', '2', '934宿舍', '8', '8', '123222222');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of dormitory_admin
-- ----------------------------
INSERT INTO `dormitory_admin` VALUES ('1', 'SleepWalker', '123abcABC', '张三', '男', '13312345678');
INSERT INTO `dormitory_admin` VALUES ('2', 'ww', '123123', '李四', '男', '13682738943');
INSERT INTO `dormitory_admin` VALUES ('3', 'abc', '123abcABC', '李四111', '女', '123222222111');
INSERT INTO `dormitory_admin` VALUES ('4', 'ee', '123abcABC', '无名式', '女', '1234324324');
INSERT INTO `dormitory_admin` VALUES ('5', 'ss', '123abcABC', '1', '男', '243');
INSERT INTO `dormitory_admin` VALUES ('6', 'SleepWalker2', '123abcABC', '11', '男', '1234324324');
INSERT INTO `dormitory_admin` VALUES ('7', 'SleepWalker3', '123abcABC', '无名式', '男', '13682738943');
INSERT INTO `dormitory_admin` VALUES ('11', 'aaa', '123abcABC', 'bbb', '女', '18318253155');
INSERT INTO `dormitory_admin` VALUES ('12', 'aaa1', '123abcABC', 'bbb', '男', '18318253155');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of moveout
-- ----------------------------
INSERT INTO `moveout` VALUES ('1', '1', '1', '毕业', '2022-10-09 13:07:45');
INSERT INTO `moveout` VALUES ('2', '19', '3', '跑路', '2022-10-26 11:14:13');
INSERT INTO `moveout` VALUES ('3', '46', '2', '毕业', '2022-10-26 15:53:08');
INSERT INTO `moveout` VALUES ('4', '16', '1', '毕业', '2022-12-03 13:01:28');
INSERT INTO `moveout` VALUES ('5', '22', '1', '退学', '2022-12-03 13:01:36');
INSERT INTO `moveout` VALUES ('6', '24', '3', '退宿', '2022-12-03 13:01:45');
INSERT INTO `moveout` VALUES ('7', '18', '1', '毕业', '2022-12-03 13:02:44');
INSERT INTO `moveout` VALUES ('8', '26', '1', '毕业', '2022-12-03 13:02:48');
INSERT INTO `moveout` VALUES ('9', '29', '2', '退宿', '2022-12-03 13:02:51');
INSERT INTO `moveout` VALUES ('10', '31', '1', '推卸', '2022-12-03 13:02:55');

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
INSERT INTO `student` VALUES ('16', '74', '赵弘济', '女', '1', '迁出', '2022-10-25 21:27:54');
INSERT INTO `student` VALUES ('17', '65', '林力夫', '男', '1', '入住', '2022-10-25 21:27:50');
INSERT INTO `student` VALUES ('18', '69', '周承泽', '女', '1', '迁出', '2018-04-03 00:51:35');
INSERT INTO `student` VALUES ('19', '36', '熊阳羽', '女', '3', '迁出', '2018-04-03 20:06:47');
INSERT INTO `student` VALUES ('22', '56', '夏侯才良', '女', '1', '迁出', '2022-10-17 21:30:28');
INSERT INTO `student` VALUES ('23', '99', '章凝丹', '女', '1', '入住', '2022-10-17 21:34:33');
INSERT INTO `student` VALUES ('24', '999', '江欣可', '女', '3', '迁出', '2022-10-17 23:45:49');
INSERT INTO `student` VALUES ('25', '678', '余睿思', '女', '1', '入住', '2022-10-17 23:50:16');
INSERT INTO `student` VALUES ('26', '653', '段琼英', '女', '1', '迁出', '2022-10-17 23:51:18');
INSERT INTO `student` VALUES ('27', '79', '周承泽', '女', '1', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('29', '20', '康鸿信', '男', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('30', '73', '周承泽1', '男', '3', '迁出', '2022-12-03 13:01:10');
INSERT INTO `student` VALUES ('31', '83', '尹力夫', '男', '1', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('32', '36', '熊阳羽', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('33', '4', '林良弼', '女', '3', '入住', '2022-12-03 13:01:10');
INSERT INTO `student` VALUES ('34', '80', '王博简', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('35', '98', '杨昊焱1', '女', '1', '入住', '2022-12-03 13:01:10');
INSERT INTO `student` VALUES ('37', '682', '杨昊焱', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('38', '849', '汤石英 ', '男', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('39', '921', '权欣琳 ', '男', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('40', '95', '慎向珊 ', '女', '3', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('41', '772', '谢展鹏', '女', '2', '入住', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('42', '350', '空霞飞 ', '女', '2', '迁出', '2022-10-19 20:22:00');
INSERT INTO `student` VALUES ('43', '950', '林力夫', '男', '1', '入住', '2022-10-25 21:30:12');
INSERT INTO `student` VALUES ('44', '963', '甘斯雅 ', '男', '1', '入住', '2022-12-03 13:01:10');
INSERT INTO `student` VALUES ('45', '389', '江忆山 ', '男', '2', '入住', '2022-12-03 13:01:10');
INSERT INTO `student` VALUES ('46', '620', '权欣琳2', '女', '2', '入住', '2022-12-03 13:01:10');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of system_admin
-- ----------------------------
INSERT INTO `system_admin` VALUES ('1', 'SleepWalker_admin', '123abcABC', '管理员1', '888888');
INSERT INTO `system_admin` VALUES ('2', '2', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('3', '3', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('4', '4', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('5', '5', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('6', '6', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('7', '7', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('8', '8', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('9', '9', '123abcABC', null, null);
INSERT INTO `system_admin` VALUES ('10', '10', '123abcABC', null, null);
SET FOREIGN_KEY_CHECKS=1;
