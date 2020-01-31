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

 Date: 30/01/2020 10:23:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_album
-- ----------------------------
DROP TABLE IF EXISTS `tb_album`;
CREATE TABLE `tb_album`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相册名称',
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相册封面',
  `image_items` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片列表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_album
-- ----------------------------
INSERT INTO `tb_album` VALUES (2, 's12', 'http://192.168.200.128:8080/group1/M00/00/00/wKjIgF0IrtmAf_tXAAALP8HQLWA987.jpg', '[{\"url\":\"http://192.168.200.128:8080/group1/M00/00/00/wKjIgF0IrUWAYdkyAAANt9KDpWU669.jpg\",\"uid\":1561719575032,\"status\":\"success\"},{\"url\":\"http://192.168.200.128:8080/group1/M00/00/00/wKjIgF0IrUWAZ0UTAAAZLmoT79w845.jpg\",\"uid\":1561719575039,\"status\":\"success\"},{\"url\":\"http://192.168.200.128:8080/group1/M00/00/00/wKjIgF0IrUWAXnipAAAQ9pXk-oA727.jpg\",\"uid\":1561719575042,\"status\":\"success\"},{\"url\":\"http://192.168.200.128:8080/group1/M00/00/00/wKjIgF0IrUaAT4sFAAAZIgrXilA369.jpg\",\"uid\":1561719575046,\"status\":\"success\"},{\"url\":\"http://192.168.200.128:8080/group1/M00/00/00/wKjIgF0IrUaADogZAAATxAf-zBo522.jpg\",\"uid\":1561719575049,\"status\":\"success\"}]');

SET FOREIGN_KEY_CHECKS = 1;
