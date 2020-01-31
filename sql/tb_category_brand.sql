/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.128
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.1.128:3306
 Source Schema         : changgou_goods

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/01/2020 10:23:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_category_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_category_brand`;
CREATE TABLE `tb_category_brand`  (
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `brand_id` int(11) NOT NULL COMMENT '品牌ID',
  PRIMARY KEY (`category_id`, `brand_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category_brand
-- ----------------------------
INSERT INTO `tb_category_brand` VALUES (14, 2505);
INSERT INTO `tb_category_brand` VALUES (42, 12);
INSERT INTO `tb_category_brand` VALUES (111, 1528);
INSERT INTO `tb_category_brand` VALUES (189, 1528);
INSERT INTO `tb_category_brand` VALUES (205, 8557);
INSERT INTO `tb_category_brand` VALUES (558, 1115);
INSERT INTO `tb_category_brand` VALUES (558, 2032);
INSERT INTO `tb_category_brand` VALUES (560, 8557);

SET FOREIGN_KEY_CHECKS = 1;
