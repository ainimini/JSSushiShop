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

 Date: 30/01/2020 10:24:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_pref
-- ----------------------------
DROP TABLE IF EXISTS `tb_pref`;
CREATE TABLE `tb_pref`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cate_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `buy_money` int(11) NULL DEFAULT NULL COMMENT '消费金额',
  `pre_money` int(11) NULL DEFAULT NULL COMMENT '优惠金额',
  `start_time` date NULL DEFAULT NULL COMMENT '活动开始日期',
  `end_time` date NULL DEFAULT NULL COMMENT '活动截至日期',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pref
-- ----------------------------
INSERT INTO `tb_pref` VALUES (1, NULL, 100, 30, NULL, NULL, NULL, NULL);
INSERT INTO `tb_pref` VALUES (2, NULL, 300, 100, NULL, NULL, NULL, NULL);
INSERT INTO `tb_pref` VALUES (3, 1, 50, 10, NULL, NULL, NULL, NULL);
INSERT INTO `tb_pref` VALUES (4, 2, 100, 40, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
