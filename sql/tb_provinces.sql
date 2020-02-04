/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.128
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.1.128:3306
 Source Schema         : changgou_user

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 04/02/2020 16:35:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_provinces
-- ----------------------------
DROP TABLE IF EXISTS `tb_provinces`;
CREATE TABLE `tb_provinces`  (
  `provinceid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省份ID',
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省份名称',
  PRIMARY KEY (`provinceid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '省份信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_provinces
-- ----------------------------
INSERT INTO `tb_provinces` VALUES ('110000', '北京市');
INSERT INTO `tb_provinces` VALUES ('120000', '天津市');
INSERT INTO `tb_provinces` VALUES ('130000', '河北省');
INSERT INTO `tb_provinces` VALUES ('140000', '山西省');
INSERT INTO `tb_provinces` VALUES ('150000', '内蒙古自治区');
INSERT INTO `tb_provinces` VALUES ('210000', '辽宁省');
INSERT INTO `tb_provinces` VALUES ('220000', '吉林省');
INSERT INTO `tb_provinces` VALUES ('230000', '黑龙江省');
INSERT INTO `tb_provinces` VALUES ('310000', '上海市');
INSERT INTO `tb_provinces` VALUES ('320000', '江苏省');
INSERT INTO `tb_provinces` VALUES ('330000', '浙江省');
INSERT INTO `tb_provinces` VALUES ('340000', '安徽省');
INSERT INTO `tb_provinces` VALUES ('350000', '福建省');
INSERT INTO `tb_provinces` VALUES ('360000', '江西省');
INSERT INTO `tb_provinces` VALUES ('370000', '山东省');
INSERT INTO `tb_provinces` VALUES ('410000', '河南省');
INSERT INTO `tb_provinces` VALUES ('420000', '湖北省');
INSERT INTO `tb_provinces` VALUES ('430000', '湖南省');
INSERT INTO `tb_provinces` VALUES ('440000', '广东省');
INSERT INTO `tb_provinces` VALUES ('450000', '广西壮族自治区');
INSERT INTO `tb_provinces` VALUES ('460000', '海南省');
INSERT INTO `tb_provinces` VALUES ('500000', '重庆市');
INSERT INTO `tb_provinces` VALUES ('510000', '四川省');
INSERT INTO `tb_provinces` VALUES ('520000', '贵州省');
INSERT INTO `tb_provinces` VALUES ('530000', '云南省');
INSERT INTO `tb_provinces` VALUES ('540000', '西藏自治区');
INSERT INTO `tb_provinces` VALUES ('610000', '陕西省');
INSERT INTO `tb_provinces` VALUES ('620000', '甘肃省');
INSERT INTO `tb_provinces` VALUES ('630000', '青海省');
INSERT INTO `tb_provinces` VALUES ('640000', '宁夏回族自治区');
INSERT INTO `tb_provinces` VALUES ('650000', '新疆维吾尔自治区');
INSERT INTO `tb_provinces` VALUES ('710000', '台湾省');
INSERT INTO `tb_provinces` VALUES ('810000', '香港特别行政区');
INSERT INTO `tb_provinces` VALUES ('820000', '澳门特别行');

SET FOREIGN_KEY_CHECKS = 1;
