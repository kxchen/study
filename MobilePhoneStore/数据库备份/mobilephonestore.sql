/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50172
Source Host           : 127.0.0.1:3306
Source Database       : mobilephonestore

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2016-08-31 07:42:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('1', 'admin', 'admin');
INSERT INTO `admin_info` VALUES ('2', '33', '33');

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '华为');
INSERT INTO `brand` VALUES ('2', '美图');
INSERT INTO `brand` VALUES ('3', '小米');
INSERT INTO `brand` VALUES ('4', '苹果');
INSERT INTO `brand` VALUES ('5', '魅族');
INSERT INTO `brand` VALUES ('6', '三星');
INSERT INTO `brand` VALUES ('7', '摩托罗拉');
INSERT INTO `brand` VALUES ('8', 'OPPO');
INSERT INTO `brand` VALUES ('9', 'VIVO');
INSERT INTO `brand` VALUES ('10', '大米');

-- ----------------------------
-- Table structure for mobile_phone
-- ----------------------------
DROP TABLE IF EXISTS `mobile_phone`;
CREATE TABLE `mobile_phone` (
  `mob_phone_id` int(255) NOT NULL AUTO_INCREMENT,
  `brand_id` int(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `ope_system_id` int(11) NOT NULL,
  `net_type_id` int(11) NOT NULL,
  `scr_size_id` int(11) NOT NULL,
  `color` varchar(255) NOT NULL,
  `price` double(255,2) NOT NULL,
  `real_price` double(255,2) NOT NULL,
  `descipt` varchar(10000) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `pixels` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `rom` varchar(255) DEFAULT NULL,
  `state` int(255) NOT NULL,
  PRIMARY KEY (`mob_phone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mobile_phone
-- ----------------------------
INSERT INTO `mobile_phone` VALUES ('17', '8', 'R7', '3', '3', '1', '白色', '2999.00', '2899.00', '    ', '1450077997295.jpg', '2015-12-21 00:00:00', '1500万', '3GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('18', '4', 'iphone6S', '2', '6', '3', '白色', '5999.00', '4899.00', '', '1450145002579.jpg', '2015-12-15 00:00:00', '1500万', '3GB', '128GB', '1');
INSERT INTO `mobile_phone` VALUES ('20', '6', 'galaxy-note', '3', '4', '4', '白色', '4999.00', '4699.00', '    ', '1450145395477.jpg', '2015-12-15 00:00:00', '1500万', '3GB', '64GB', '1');
INSERT INTO `mobile_phone` VALUES ('21', '6', 'galaxy-s4', '3', '5', '2', '太空灰', '3000.00', '2899.00', '    ', '1450145604245.jpg', '2015-12-15 00:00:00', '1300万', '2GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('22', '4', 'Iphone 4S', '2', '1', '2', '黑色', '2999.00', '2599.00', '    ', '1450145691529.png', '2015-12-15 00:00:00', '500万', '1GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('23', '6', 'lumia 640', '4', '6', '1', '黑色', '4999.00', '3899.00', '    ', '1450145782012.jpg', '2015-12-17 00:00:00', '1500万', '2GB', '64GB', '1');
INSERT INTO `mobile_phone` VALUES ('24', '3', 'mi4c', '3', '3', '1', '黑色', '1999.00', '1999.00', '    ', '1450145844705.jpg', '2015-12-18 00:00:00', '1200万', '3GB', '16GB', '0');
INSERT INTO `mobile_phone` VALUES ('25', '5', 'MX4', '5', '6', '1', '黑色', '1999.00', '1799.00', '    ', '1450145940574.jpg', '2015-12-15 00:00:00', '2000万', '2GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('26', '5', 'MX5', '3', '6', '4', '白色', '2000.00', '1999.00', '    ', '1450146008461.jpg', '2015-12-15 00:00:00', '2000万', '3GB', '16GB', '1');
INSERT INTO `mobile_phone` VALUES ('27', '6', 'note 4', '3', '4', '4', '白色', '4999.00', '4899.00', '    ', '1450146064246.jpg', '2015-12-15 00:00:00', '1500万', '3GB', '128GB', '1');
INSERT INTO `mobile_phone` VALUES ('28', '6', 'galaxy-note5', '3', '6', '4', '金色', '4999.00', '3899.00', '    ', '1450146127002.jpg', '2015-12-15 00:00:00', '1500万', '3GB', '16GB', '1');
INSERT INTO `mobile_phone` VALUES ('29', '1', 'P8', '3', '3', '3', '黑色', '2999.00', '2899.00', '    ', '1450146185282.jpg', '2015-12-15 00:00:00', '1500万', '3GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('30', '9', 'X5', '3', '3', '3', '白色', '2999.00', '2599.00', '    ', '1450146242434.jpg', '2015-12-15 00:00:00', '2000万', '3GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('31', '1', '荣耀 7i', '3', '2', '1', '金色', '2000.00', '1999.00', '    ', '1450146298829.jpg', '2015-12-15 00:00:00', '1300万', '2GB', '16GB', '1');
INSERT INTO `mobile_phone` VALUES ('32', '7', 'Z1', '3', '3', '2', '黑色', '2999.00', '2899.00', '    ', '1450146372926.png', '2015-12-15 00:00:00', '1500万', '3GB', '32GB', '1');
INSERT INTO `mobile_phone` VALUES ('33', '5', '魅蓝', '3', '3', '4', '白色', '1999.00', '1799.00', '    ', '1450147999872.jpg', '2015-12-15 00:00:00', '1500万', '2GB', '16GB', '1');

-- ----------------------------
-- Table structure for network_type
-- ----------------------------
DROP TABLE IF EXISTS `network_type`;
CREATE TABLE `network_type` (
  `net_type_id` int(255) NOT NULL AUTO_INCREMENT,
  `net_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`net_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of network_type
-- ----------------------------
INSERT INTO `network_type` VALUES ('1', '电信3G');
INSERT INTO `network_type` VALUES ('2', '电信2G');
INSERT INTO `network_type` VALUES ('3', '移动4G');
INSERT INTO `network_type` VALUES ('4', '电信4G');
INSERT INTO `network_type` VALUES ('5', '联通4G');
INSERT INTO `network_type` VALUES ('6', '全网通');

-- ----------------------------
-- Table structure for operating_system
-- ----------------------------
DROP TABLE IF EXISTS `operating_system`;
CREATE TABLE `operating_system` (
  `ope_system_id` int(11) NOT NULL AUTO_INCREMENT,
  `ope_system_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ope_system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operating_system
-- ----------------------------
INSERT INTO `operating_system` VALUES ('2', 'IOS');
INSERT INTO `operating_system` VALUES ('3', ' Android');
INSERT INTO `operating_system` VALUES ('4', 'Windows');
INSERT INTO `operating_system` VALUES ('5', 'Ubuntu');
INSERT INTO `operating_system` VALUES ('6', '未知系统');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `order_detail_id` int(255) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(255) DEFAULT NULL,
  `mob_phone_id` int(255) DEFAULT NULL,
  `buy_price` double(255,2) DEFAULT NULL,
  `amount` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('38', '145016659733612', '23', '3899.00', '1');
INSERT INTO `order_detail` VALUES ('39', '145016659733612', '24', '1999.00', '1');
INSERT INTO `order_detail` VALUES ('40', '145016666883712', '30', '2599.00', '1');
INSERT INTO `order_detail` VALUES ('41', '145016666883712', '33', '1799.00', '1');
INSERT INTO `order_detail` VALUES ('48', '145026356185412', '18', '5899.00', '1');
INSERT INTO `order_detail` VALUES ('49', '145026368786212', '32', '2899.00', '1');
INSERT INTO `order_detail` VALUES ('51', '145026379576512', '28', '3899.00', '1');
INSERT INTO `order_detail` VALUES ('52', '145026393943612', '18', '4899.00', '1');
INSERT INTO `order_detail` VALUES ('53', '145026451317212', '28', '3899.00', '4');
INSERT INTO `order_detail` VALUES ('54', '145026461230312', '21', '2899.00', '2');
INSERT INTO `order_detail` VALUES ('56', '145035537715212', '18', '4899.00', '1');
INSERT INTO `order_detail` VALUES ('57', '145035537715212', '18', '4899.00', '1');
INSERT INTO `order_detail` VALUES ('58', '145035537715212', '28', '3899.00', '1');
INSERT INTO `order_detail` VALUES ('59', '145035537715212', '28', '3899.00', '1');
INSERT INTO `order_detail` VALUES ('64', '145069016766112', '18', '4899.00', '1');
INSERT INTO `order_detail` VALUES ('65', '146227537929654', '21', '2899.00', '1');
INSERT INTO `order_detail` VALUES ('66', '146227537929654', '28', '3899.00', '1');
INSERT INTO `order_detail` VALUES ('67', '146227542632250', '18', '4899.00', '1');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_id` bigint(255) NOT NULL,
  `user_id` int(254) DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `total_price` double(255,2) DEFAULT NULL,
  `is_pay` int(255) DEFAULT NULL,
  `is_deliver` int(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('145026356185412', '12', '2015-12-16 00:00:00', '5899.00', '1', '0');
INSERT INTO `order_info` VALUES ('145026368786212', '12', '2015-12-16 00:00:00', '2899.00', '1', '0');
INSERT INTO `order_info` VALUES ('145026379576512', '12', '2015-12-16 00:00:00', '3899.00', '0', '0');
INSERT INTO `order_info` VALUES ('145026393943612', '12', '2015-12-16 00:00:00', '4899.00', '0', '0');
INSERT INTO `order_info` VALUES ('145026451317212', '12', '2015-12-16 00:00:00', '15596.00', '0', '0');
INSERT INTO `order_info` VALUES ('145026461230312', '12', '2015-12-16 00:00:00', '5798.00', '0', '0');
INSERT INTO `order_info` VALUES ('145069016766112', '12', '2015-12-21 00:00:00', '4899.00', '0', '0');
INSERT INTO `order_info` VALUES ('146227537929654', '54', '2016-05-03 00:00:00', '6798.00', '0', '0');
INSERT INTO `order_info` VALUES ('146227542632250', '50', '2016-05-03 00:00:00', '4899.00', '0', '0');

-- ----------------------------
-- Table structure for screen_size
-- ----------------------------
DROP TABLE IF EXISTS `screen_size`;
CREATE TABLE `screen_size` (
  `scr_size_id` int(255) NOT NULL AUTO_INCREMENT,
  `scr_size_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`scr_size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of screen_size
-- ----------------------------
INSERT INTO `screen_size` VALUES ('1', '5.0寸');
INSERT INTO `screen_size` VALUES ('2', '4.0寸');
INSERT INTO `screen_size` VALUES ('3', '4.7寸');
INSERT INTO `screen_size` VALUES ('4', '5.5寸');

-- ----------------------------
-- Table structure for shop_cart
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE `shop_cart` (
  `shop_cart_id` int(255) NOT NULL AUTO_INCREMENT,
  `quantity` int(255) DEFAULT NULL,
  `user_id` int(255) DEFAULT NULL,
  `mob_phone_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`shop_cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(50) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(150) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `regtime` datetime DEFAULT NULL,
  `state` int(255) unsigned zerofill DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('10', '2', '22', '2', '2', '2', '2', '2015-12-01 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('11', 'xx', 'xx', 'xx', 'xx', 'xx', 'xx', '2015-12-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('12', 'ckx', '陈凯旋', 'ckx', '15255391553', '滁州职院', '1005851528@qq.com', '2015-12-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', null);
INSERT INTO `user_info` VALUES ('13', 'xzh', 'xzh', 'admin', '10086', '2517', '123@qq.com', '2015-12-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('15', 'hxx', '何笑笑', 'hxx', '123456', '5414', '1005851528@qq.com', '2015-12-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('16', 'lxl', 'asdfa', '123', 'casdf', 'afdsa', '1005851528@qq.com', '2015-12-09 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('17', '33', '发生的故事的', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('18', '33', '电饭锅', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('20', '55', '十分', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('21', '465', '生动', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('22', '讽德诵功', ' 是', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('23', '人撒地方', '撒旦', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('24', ' 十大', '撒旦', null, null, null, null, null, '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', null);
INSERT INTO `user_info` VALUES ('25', 'chenaki', 'dbauiguaigf', '123456', '5456416', 'dsfsaf', 'c-kx@outlook.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '30EFE731C61F402986EE6928216F1971');
INSERT INTO `user_info` VALUES ('26', 'chenkai', 'chenhjlk', '123', 'cmzl;kjc', 'adqdreq', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '1F529AE4B03D4E9998DAA13338DEE0A0');
INSERT INTO `user_info` VALUES ('27', 'qqwe', 'dada', 'qweqedq', 'dada', 'sdacas', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '1BADF0E7CF564771A0BCA3604FCD968A');
INSERT INTO `user_info` VALUES ('28', 'ckkk', 'sefs', '123', 'safs', 'fsaf', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'FD2B24DAAFCB4E4DAC1B7AD78E42E476');
INSERT INTO `user_info` VALUES ('29', 'ckkkkkk', 'afca', 'jaifj', 'ascf', 'sdca', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '50A1BE695A58403385B6EC33E4850FE1');
INSERT INTO `user_info` VALUES ('30', 'ckxxx', 'sasas', 'ssss', 'sasasa', 'ssa', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'A6030C569D824AAA98C1331E2C458843');
INSERT INTO `user_info` VALUES ('31', 'sssss', 'sss', 'sss', 'ss', 'ssss', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '01CF2667394948EEA84E4B77EA0D6FDD');
INSERT INTO `user_info` VALUES ('32', 'sabdgd', 'aaa', 'sasaa', 'aa', 'sss', '734668128@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'E5F47FA4ACB746C9A0A2070D33C1414F');
INSERT INTO `user_info` VALUES ('33', 'ddd', 'ddd', 'ddddd', 'ddd', '滁州职院', '1005851528@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '2ADD63F581F84066B1A7421CE98A6CB6');
INSERT INTO `user_info` VALUES ('34', 'ddddd', 'dddd', 'dddd', 'dddd', '滁州职院', 'luna_test@139.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '10E7E3112F7D42B5BD598EC9E0810A18');
INSERT INTO `user_info` VALUES ('35', 'admin', 'aaa', 'admin', 'aaa', '滁州职院', '734668128@qq.com', '2015-12-20 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', 'AD722825E2BD464DACC17F3ECBD52853');
INSERT INTO `user_info` VALUES ('36', 'xxx', 'xxx', 'xxx', 'xxx', 'xxx', '857667863@qq.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '18A8F3A996DA470D89794A34C677AC2E');
INSERT INTO `user_info` VALUES ('37', 'aaa', 'aaa', 'aaa', 'aaa', 'fddsf', 'xzh0212@outlook.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'EADA9158CC1D4B08B93DE8EA72706528');
INSERT INTO `user_info` VALUES ('38', 'aa', ' aaa', 'admin', ' aaa', 'aa', '1005851528@qq.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '0ABBA5C00392405CB65F388191CB7C97');
INSERT INTO `user_info` VALUES ('39', 'nsjfh', 'sadas', 'fafca', 'sadcsa', 'sdad', 'asdada@1234.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'A618BA9A07E94F4BA76F40BF1E86BEDA');
INSERT INTO `user_info` VALUES ('40', 'sdfs', 'adsda', 'asda', 'adsda', 'sada', '123@qq.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'BB88CDAE3E564616B0E447619B94B1F4');
INSERT INTO `user_info` VALUES ('41', 'dad', 'asda', 'asda', 'asda', 'dasd', '123@qq.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'BCAC72B6AA0C423D95C8BE5D4933B8DE');
INSERT INTO `user_info` VALUES ('42', 'werq', 'dwqd', 'swdq', 'wdq', 'sawsd', '1005851528@qq.com', '2015-12-21 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '5236D70629B4477FA8E2CD322B2EC8DB');
INSERT INTO `user_info` VALUES ('43', 'admin', 'fsgd', 'admin', '234234235', 'gdfg', '1005851528@qq.com', '2016-04-08 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', 'AAF8B125CA6142EFA32B74AC0CB8C33B');
INSERT INTO `user_info` VALUES ('44', 'ewewaef', 'dfhdksfkl', 'qwert123', 'adasfs', 'asdads', '123455695949@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '2BED06CB47F94B909F7B14F660DFDFB8');
INSERT INTO `user_info` VALUES ('45', 'qwert', 'qwert', 'qwert', 'qwert', 'qwert', '1458629450@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '02ACF5ACBBF04E4B8A1546ABCCBDA567');
INSERT INTO `user_info` VALUES ('50', 'qwert', '123', '123', '123', 'sads', '14586294502@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', 'A2D671E9EAB443979BFC8FC750D5C9F6');
INSERT INTO `user_info` VALUES ('51', 'shy', '孙怀云', '201294', '18855043125', '滁州职业技术学院', '3038107648@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', 'F15683B45419464EA99040C3548512D8');
INSERT INTO `user_info` VALUES ('52', 'qwert', '123', '123', '123', '123', '1458629450@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '0B98CEECA82B440D8BEC2CF99FFB0E97');
INSERT INTO `user_info` VALUES ('53', '大大', '冷', '123456', '15178108028', '滁州职业技术学院', '1335416839@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '7A7CAC4DD3604AF59BE5EEA96365A209');
INSERT INTO `user_info` VALUES ('54', '星空', '李健', '123456', '1348914791234', '滁职', '2297436978@qq.com', '2016-05-03 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', 'C21DB69634E84E5DB99DB9C50D22F17B');
INSERT INTO `user_info` VALUES ('55', '3333', '3333', '3333', '3333', '222', '2374666950@qq.com', '2016-07-25 00:00:00', '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '7B9908FFC66E413AA4CAFEA3A2AE0D99');

-- ----------------------------
-- View structure for hot_sale_view
-- ----------------------------
DROP VIEW IF EXISTS `hot_sale_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `hot_sale_view` AS SELECT DISTINCT
mobile_phone_view.mob_phone_id,
Sum(order_detail.amount) AS sum,
mobile_phone_view.net_type_name,
mobile_phone_view.scr_size_name,
mobile_phone_view.brand_id,
mobile_phone_view.model,
mobile_phone_view.ope_system_id,
mobile_phone_view.net_type_id,
mobile_phone_view.scr_size_id,
mobile_phone_view.color,
mobile_phone_view.price,
mobile_phone_view.real_price,
mobile_phone_view.descipt,
mobile_phone_view.img_path,
mobile_phone_view.reg_date,
mobile_phone_view.pixels,
mobile_phone_view.ram,
mobile_phone_view.rom,
mobile_phone_view.state,
mobile_phone_view.brand_name,
mobile_phone_view.ope_system_name
FROM
order_detail
INNER JOIN mobile_phone_view ON order_detail.mob_phone_id = mobile_phone_view.mob_phone_id
GROUP BY
mobile_phone_view.mob_phone_id
ORDER BY
sum DESC ;

-- ----------------------------
-- View structure for mobile_phone_view
-- ----------------------------
DROP VIEW IF EXISTS `mobile_phone_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `mobile_phone_view` AS SELECT
network_type.net_type_name,
screen_size.scr_size_name,
mobile_phone.mob_phone_id,
mobile_phone.brand_id,
mobile_phone.model,
mobile_phone.ope_system_id,
mobile_phone.net_type_id,
mobile_phone.scr_size_id,
mobile_phone.color,
mobile_phone.price,
mobile_phone.real_price,
mobile_phone.descipt,
mobile_phone.img_path,
mobile_phone.reg_date,
mobile_phone.pixels,
mobile_phone.ram,
mobile_phone.rom,
mobile_phone.state,
brand.brand_name,
operating_system.ope_system_name
FROM
mobile_phone
INNER JOIN network_type ON mobile_phone.net_type_id = network_type.net_type_id
INNER JOIN screen_size ON mobile_phone.scr_size_id = screen_size.scr_size_id
INNER JOIN brand ON mobile_phone.brand_id = brand.brand_id
INNER JOIN operating_system ON mobile_phone.ope_system_id = operating_system.ope_system_id ;

-- ----------------------------
-- View structure for order_detail_view
-- ----------------------------
DROP VIEW IF EXISTS `order_detail_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `order_detail_view` AS SELECT
order_detail.order_detail_id,
order_detail.order_id,
order_detail.mob_phone_id,
order_detail.buy_price,
order_detail.amount,
order_info_view.user_id,
order_info_view.submit_time,
order_info_view.total_price,
order_info_view.is_pay,
order_info_view.is_deliver,
order_info_view.login_name,
order_info_view.real_name,
order_info_view.`password`,
order_info_view.phone,
order_info_view.address,
order_info_view.email,
order_info_view.regtime,
mobile_phone_view.net_type_name,
mobile_phone_view.scr_size_name,
mobile_phone_view.brand_id,
mobile_phone_view.model,
mobile_phone_view.ope_system_id,
mobile_phone_view.net_type_id,
mobile_phone_view.scr_size_id,
mobile_phone_view.color,
mobile_phone_view.price,
mobile_phone_view.real_price,
mobile_phone_view.descipt,
mobile_phone_view.img_path,
mobile_phone_view.reg_date,
mobile_phone_view.pixels,
mobile_phone_view.ram,
mobile_phone_view.rom,
mobile_phone_view.state,
mobile_phone_view.brand_name,
mobile_phone_view.ope_system_name
FROM
order_detail
INNER JOIN order_info_view ON order_detail.order_id = order_info_view.order_id
INNER JOIN mobile_phone_view ON order_detail.mob_phone_id = mobile_phone_view.mob_phone_id ;

-- ----------------------------
-- View structure for order_info_view
-- ----------------------------
DROP VIEW IF EXISTS `order_info_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `order_info_view` AS SELECT
order_info.order_id,
order_info.user_id,
order_info.submit_time,
order_info.total_price,
order_info.is_pay,
order_info.is_deliver,
user_info.login_name,
user_info.real_name,
user_info.`password`,
user_info.phone,
user_info.address,
user_info.email,
user_info.regtime
FROM
order_info
INNER JOIN user_info ON order_info.user_id = user_info.user_id ;

-- ----------------------------
-- View structure for shop_cart_view
-- ----------------------------
DROP VIEW IF EXISTS `shop_cart_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `shop_cart_view` AS SELECT
shop_cart.user_id,
user_info.login_name,
user_info.real_name,
user_info.`password`,
user_info.phone,
user_info.address,
user_info.email,
user_info.regtime,
shop_cart.shop_cart_id,
shop_cart.quantity,
shop_cart.mob_phone_id,
mobile_phone_view.net_type_name,
mobile_phone_view.scr_size_name,
mobile_phone_view.brand_id,
mobile_phone_view.model,
mobile_phone_view.ope_system_id,
mobile_phone_view.net_type_id,
mobile_phone_view.scr_size_id,
mobile_phone_view.color,
mobile_phone_view.price,
mobile_phone_view.real_price,
mobile_phone_view.descipt,
mobile_phone_view.img_path,
mobile_phone_view.reg_date,
mobile_phone_view.pixels,
mobile_phone_view.ram,
mobile_phone_view.rom,
mobile_phone_view.state,
mobile_phone_view.brand_name,
mobile_phone_view.ope_system_name
FROM
mobile_phone_view
INNER JOIN shop_cart ON shop_cart.mob_phone_id = mobile_phone_view.mob_phone_id
INNER JOIN user_info ON shop_cart.user_id = user_info.user_id ;
