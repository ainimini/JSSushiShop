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

 Date: 04/02/2020 16:33:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `provinceid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `cityid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `areaid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '县/区',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `is_default` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是默认 1默认 0否',
  `alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (59, 'lijialong', NULL, NULL, NULL, '13900112222', '金燕龙办公楼', '李嘉诚', '0', NULL);
INSERT INTO `tb_address` VALUES (60, 'lijialong', NULL, NULL, NULL, '13700221122', '修正大厦', '李佳红', '0', NULL);
INSERT INTO `tb_address` VALUES (61, 'lijialong', NULL, NULL, NULL, '13301212233', '中腾大厦', '李佳星', '1', NULL);
INSERT INTO `tb_address` VALUES (62, 'heima', NULL, NULL, NULL, '13700221122', '西直门', '赵三', '0', NULL);
INSERT INTO `tb_address` VALUES (63, 'heima', NULL, NULL, NULL, '11011011', '永春武馆', '李小龙', '0', '家里');
INSERT INTO `tb_address` VALUES (64, 'heima', NULL, NULL, NULL, '999111', '咏春武馆总部', '叶问', '0', '师爷家');
INSERT INTO `tb_address` VALUES (65, 'heima', NULL, NULL, NULL, '13301212233', '北京市昌平区', '张三', '1', '北京家');

SET FOREIGN_KEY_CHECKS = 1;
