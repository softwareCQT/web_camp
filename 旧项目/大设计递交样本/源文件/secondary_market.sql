/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : secondary_market

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-05-15 20:51:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `Id` varchar(20) NOT NULL,
  `Name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Message` varchar(500) DEFAULT NULL,
  `Sum` int(5) DEFAULT NULL,
  `Price` double(10,2) DEFAULT NULL,
  `Photo` varchar(100) DEFAULT NULL,
  `Buy_Sum` int(5) DEFAULT NULL,
  `Type` varchar(10) DEFAULT NULL,
  `Owner` varchar(20) DEFAULT NULL,
  `Condition1` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Owner` (`Owner`),
  CONSTRAINT `Owner` FOREIGN KEY (`Owner`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1557506861828', '床具', '很舒服很软，又大又便宜', '20', '120.80', 'img/goodsImg/1557506861828.jpg_240x240.jpg', '0', '家具', '1179374184', '允许发售');
INSERT INTO `commodity` VALUES ('1557507392781', 'nick', '跑步超舒服', '2', '1888.00', 'img/goodsImg/1557507392781.jpg', '2', '运动', '1179374184', '允许发售');
INSERT INTO `commodity` VALUES ('1557507458174', '詹姆斯球衣', '穿来打篮球超帅的', '0', '99.50', 'img/goodsImg/1557507458174.jpg', '1', '运动', '1179374184', '允许发售');
INSERT INTO `commodity` VALUES ('1557537537504', 'nick跑鞋', '超舒服，跑起来跟飞一样', '1', '399.00', 'img/goodsImg/1557537537504.jpg', '0', '运动', '1179374184', '允许发售');
INSERT INTO `commodity` VALUES ('1557537574531', '靠椅', '躺着很舒服喔', '1', '188.00', 'img/goodsImg/1557537574531.jpg', '0', '运动', '1179374184', '允许发售');
INSERT INTO `commodity` VALUES ('1557909739349', '被褥', '睡着很舒服', '1', '100.80', 'img/goodsImg/1557909739349.jpg_240x240.jpg', '0', '运动', '18312188184', '允许发售');

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1` (
  `Id` varchar(30) NOT NULL,
  `CreateTime` datetime NOT NULL,
  `Condition1` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Owner` varchar(20) NOT NULL,
  `Buyer` varchar(20) NOT NULL,
  `SendAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FromAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Relate_Message` varchar(255) DEFAULT NULL,
  `Price` double(10,2) NOT NULL,
  `GoodId` varchar(20) NOT NULL,
  `GoodSum` int(3) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `OwnerAndOrder` (`Owner`),
  KEY `BuyerAndOrder` (`Buyer`),
  KEY `GoodAndOrder` (`GoodId`),
  CONSTRAINT `BuyerAndOrder` FOREIGN KEY (`Buyer`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OwnerAndOrder` FOREIGN KEY (`Owner`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order1
-- ----------------------------
INSERT INTO `order1` VALUES ('1557538934656', '2019-05-11 01:42:15', '取消交易', '1179374184', '18312188184', '广东省化州市', null, '18312188184', '我是詹姆斯的敌人库里，我想买', '99.50', ' 1557507458174', '1');
INSERT INTO `order1` VALUES ('1557539258296', '2019-05-11 01:47:38', '取消交易', '1179374184', '18312188184', '广东省化州市', null, '18312188184', '我是詹姆斯的铁粉', '99.50', '1557507458174', '1');
INSERT INTO `order1` VALUES ('1557539496858', '2019-05-11 01:51:37', '取消交易', '1179374184', '18312188184', '广东省化州市', null, '18312188184', '我是詹姆斯的铁粉', '99.50', '1557507458174', '1');
INSERT INTO `order1` VALUES ('1557539786555', '2019-05-11 01:56:27', '收货成功', '1179374184', '18312188184', '广东省化州市', null, '18312188184', '我好喜欢nick', '1888.00', '1557507392781', '1');
INSERT INTO `order1` VALUES ('1557539799077', '2019-05-11 01:56:39', '收货成功', '1179374184', '18312188184', '广东省化州市', null, '18312188184', '我好喜欢nick', '1888.00', '1557507392781', '1');
INSERT INTO `order1` VALUES ('1557643510216', '2019-05-12 06:45:10', '待发货', '1179374184', '18312188184', '广东省广州市', null, '18312188184', '我看上款东西', '99.50', '1557507458174', '1');

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `Commodity_Id` varchar(20) NOT NULL,
  `User_Id` varchar(20) NOT NULL,
  `Sum` int(4) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`Date`),
  KEY `UserAndCart` (`User_Id`),
  KEY `GoodAndCart` (`Commodity_Id`),
  CONSTRAINT `GoodAndCart` FOREIGN KEY (`Commodity_Id`) REFERENCES `commodity` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `UserAndCart` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES ('1557507458174', '18312188184', '1', '2019-05-14 14:45:16');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` varchar(20) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Priority` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Star` int(2) NOT NULL,
  `Money` double(10,2) NOT NULL,
  `Photo` varchar(100) DEFAULT NULL,
  `Sell` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1179374184', '5da56051376d66d3d230c6a360c1fd9', '男', '18244958701@139.com', '陈起廷', '13727747951', '普通用户', '0', '3875.50', 'img/userImg/1179374184.jpg', '是');
INSERT INTO `user` VALUES ('18312188184', '5da56051376d66d3d230c6a360c1fd9', '男', '18244958701@139.com', '叶高君', '13727747951', '管理员', '0', '96124.50', null, '是');
INSERT INTO `user` VALUES ('3118004942', '248ec82927d99b739d1afb829eeed19c', '男', '2287174603@qq.com', '赵松江', '13025176786', '普通用户', '0', '0.00', 'img/userImg/3118004942.jpg', '是');
