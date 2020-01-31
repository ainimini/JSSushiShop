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

 Date: 30/01/2020 10:24:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_spec
-- ----------------------------
DROP TABLE IF EXISTS `tb_spec`;
CREATE TABLE `tb_spec`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `options` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格选项',
  `seq` int(11) NULL DEFAULT NULL COMMENT '排序',
  `template_id` int(11) NULL DEFAULT NULL COMMENT '模板ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_spec
-- ----------------------------
INSERT INTO `tb_spec` VALUES (26, '尺码', '165,170,175', NULL, 43);
INSERT INTO `tb_spec` VALUES (27, '网络制式', '3G,4G', NULL, 42);
INSERT INTO `tb_spec` VALUES (28, '手机屏幕尺寸', '5寸,5.5寸', NULL, 42);
INSERT INTO `tb_spec` VALUES (32, '机身内存', '2G,4G', NULL, 42);
INSERT INTO `tb_spec` VALUES (33, '电视屏幕尺寸', '20英寸,50英寸,60英寸', NULL, 43);
INSERT INTO `tb_spec` VALUES (34, '存储', '16G,32G,64G', NULL, 42);
INSERT INTO `tb_spec` VALUES (36, '像素', '300万像素,800万像素', 1, 42);
INSERT INTO `tb_spec` VALUES (37, '电视音响效果', '立体声,环绕,小影院', NULL, 43);
INSERT INTO `tb_spec` VALUES (38, '111', '1231321,313131,3123132,2313,23,2313213,31321313', 11, 42);
INSERT INTO `tb_spec` VALUES (39, '测试', '实施,学习,实施,测试,显示,s11', 1, 42);

SET FOREIGN_KEY_CHECKS = 1;
