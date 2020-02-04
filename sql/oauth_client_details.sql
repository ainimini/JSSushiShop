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

 Date: 04/02/2020 16:35:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端ID，主要用于标识对应的应用',
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端秘钥，BCryptPasswordEncoder加密',
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应的范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证模式',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证后重定向地址',
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT '令牌有效期',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '令牌刷新周期',
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('changgou', NULL, '$2a$10$Yvkp3xzDcri6MAsPIqnzzeGBHez1QZR3A079XDdmNU4R725KrkXi2', 'app', 'authorization_code,password,refresh_token,client_credentials', 'http://localhost', NULL, 43200, 43200, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('userweb', NULL, '$2a$10$Yvkp3xzDcri6MAsPIqnzzeGBHez1QZR3A079XDdmNU4R725KrkXi2', 'app', 'password,refresh_token', 'http://localhost', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
