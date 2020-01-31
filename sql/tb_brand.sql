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

 Date: 30/01/2020 10:23:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名称',
  `image` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品牌图片地址',
  `letter` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品牌的首字母',
  `seq` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 325414 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_brand
-- ----------------------------
INSERT INTO `tb_brand` VALUES (1115, 'HTC', '', 'H', 1);
INSERT INTO `tb_brand` VALUES (1528, 'LG', '', 'L', 1);
INSERT INTO `tb_brand` VALUES (1912, 'NEC', '', 'N', 2);
INSERT INTO `tb_brand` VALUES (2032, 'OPPO', 'http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg', 'O', 3);
INSERT INTO `tb_brand` VALUES (2505, 'TCL', '', 'T', 2);
INSERT INTO `tb_brand` VALUES (3177, '爱贝多', '', 'A', 3);
INSERT INTO `tb_brand` VALUES (3539, '安桥', '', 'A', 1);
INSERT INTO `tb_brand` VALUES (3941, '白金', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (4986, '波导', '', 'B', 5);
INSERT INTO `tb_brand` VALUES (6522, '朵唯', '', 'D', 6);
INSERT INTO `tb_brand` VALUES (6742, '飞利浦', 'http://img12.360buyimg.com/popshop/jfs/t18361/122/1318410299/1870/36fe70c9/5ac43a4dNa44a0ce0.jpg', 'F', 7);
INSERT INTO `tb_brand` VALUES (7174, '富可视', '', 'F', NULL);
INSERT INTO `tb_brand` VALUES (7203, '富士通', '', 'F', NULL);
INSERT INTO `tb_brand` VALUES (7420, '格力', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (7817, '海尔', 'http://image.leyou.com/group1/M00/00/00/wKg4ZVrYZcyAVOzoAAB846UcmLg013.png', 'H', NULL);
INSERT INTO `tb_brand` VALUES (7888, '海信', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (8214, '黑莓', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (8551, '华硕', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (8557, '华为', 'http://img10.360buyimg.com/popshop/jfs/t5662/36/8888655583/7806/1c629c01/598033b4Nd6055897.jpg', 'H', NULL);
INSERT INTO `tb_brand` VALUES (8740, '惠普', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (9420, '金立', '', 'J', NULL);
INSERT INTO `tb_brand` VALUES (9637, '京瓷', '', 'J', NULL);
INSERT INTO `tb_brand` VALUES (10317, '康佳', '', 'K', NULL);
INSERT INTO `tb_brand` VALUES (10640, '酷派', 'http://img10.360buyimg.com/popshop/jfs/t2521/347/883897149/3732/91c917ec/5670cf96Ncffa2ae6.jpg', 'K', NULL);
INSERT INTO `tb_brand` VALUES (11516, '联想', 'http://img11.360buyimg.com/popshop/jfs/t6799/74/1348425607/15774/bc286188/59804c98N1944175e.jpg', 'L', NULL);
INSERT INTO `tb_brand` VALUES (12286, '漫步者', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (12669, '魅族', 'http://img13.360buyimg.com/popshop/jfs/t3511/131/31887105/4943/48f83fa9/57fdf4b8N6e95624d.jpg', 'M', NULL);
INSERT INTO `tb_brand` VALUES (13066, '摩托罗拉', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (13539, '诺基亚', 'http://img11.360buyimg.com/popshop/jfs/t2572/102/189476501/7717/16cc5814/563b33d4N6c59780c.jpg', 'N', NULL);
INSERT INTO `tb_brand` VALUES (14026, 'Apple', 'http://img12.360buyimg.com/popshop/jfs/t2989/240/151377693/3895/30ad9044/574d36dbN262ef26d.jpg', 'A', NULL);
INSERT INTO `tb_brand` VALUES (14478, '青橙', '', 'Q', NULL);
INSERT INTO `tb_brand` VALUES (15127, '三星', 'http://img14.360buyimg.com/popshop/jfs/t2701/34/484677369/7439/ee13e8fa/5716e2c4Nc925baf3.jpg', 'S', NULL);
INSERT INTO `tb_brand` VALUES (15539, '神舟', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (16506, '索爱', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (16538, '索尼', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (16975, '天语', '', 'T', NULL);
INSERT INTO `tb_brand` VALUES (17193, '外星人', '', 'W', NULL);
INSERT INTO `tb_brand` VALUES (17424, '威图', '', 'W', NULL);
INSERT INTO `tb_brand` VALUES (17440, '微软', '', 'W', NULL);
INSERT INTO `tb_brand` VALUES (18078, 'NAMO', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (18362, '小辣椒', 'http://img14.360buyimg.com/popshop/g14/M00/09/10/rBEhV1K6uOwIAAAAAAAGxx0kTvQAAHWYwPIloIAAAbf434.png', 'X', NULL);
INSERT INTO `tb_brand` VALUES (18374, '小米', 'http://img10.360buyimg.com/popshop/jfs/t7084/169/439244907/4647/724c7958/598042c9N6e4e79e5.jpg', 'X', NULL);
INSERT INTO `tb_brand` VALUES (20130, '优思', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (20710, '长虹', '', 'C', NULL);
INSERT INTO `tb_brand` VALUES (21011, '中兴', 'http://img13.360buyimg.com/popshop/jfs/t2551/271/1317227522/3284/7419745e/56a855a3Ne38ee719.jpg', 'Z', NULL);
INSERT INTO `tb_brand` VALUES (21360, 'beats', '', 'b', NULL);
INSERT INTO `tb_brand` VALUES (21553, '本易', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (22261, 'tonino lamborghini', '', 'T', NULL);
INSERT INTO `tb_brand` VALUES (22575, '布鲁雅尔', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (23049, '广信', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (23130, '夏普', 'http://img12.360buyimg.com/popshop/jfs/t2458/315/2153417538/12751/2ff4e617/56b15fe9Naae1551d.jpg', 'X', NULL);
INSERT INTO `tb_brand` VALUES (25376, '马歇尔', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (25591, 'vivo', 'http://img13.360buyimg.com/popshop/jfs/t2458/318/1092287831/5774/b7b4ff89/563b3484N9ba68e13.jpg', 'V', NULL);
INSERT INTO `tb_brand` VALUES (27094, '努比亚', 'http://img14.360buyimg.com/popshop/jfs/t2101/155/882410684/3730/b24b14db/5631cd12N7548352d.jpg', 'N', NULL);
INSERT INTO `tb_brand` VALUES (27306, '360', 'http://img10.360buyimg.com/popshop/jfs/t5182/149/2437584670/15334/2e1ebf3/591aa1abN602ebecf.jpg', 'S', NULL);
INSERT INTO `tb_brand` VALUES (32315, '其他', '', 'Q', NULL);
INSERT INTO `tb_brand` VALUES (36404, '金圣达', '', 'J', NULL);
INSERT INTO `tb_brand` VALUES (36652, '贝尔丰', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (36654, '大显（DaXian）', '', 'D', NULL);
INSERT INTO `tb_brand` VALUES (36657, '朗界（RugGear）', '', 'L', NULL);
INSERT INTO `tb_brand` VALUES (38125, 'SUGAR', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (38126, '美图（meitu）', 'http://img14.360buyimg.com/popshop/jfs/t3553/225/1930378991/5787/c2ebdd72/5836e479N88a98abb.jpg', 'M', NULL);
INSERT INTO `tb_brand` VALUES (38605, 'MANN', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (43644, '21KE', '', 'E', NULL);
INSERT INTO `tb_brand` VALUES (45227, '易百年', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (45693, '威途（vertu）', '', 'V', NULL);
INSERT INTO `tb_brand` VALUES (45757, '欧奇（ouki）', '', 'o', NULL);
INSERT INTO `tb_brand` VALUES (49144, 'SPIGEN', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (50450, '亿和源（YHYON）', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (51040, 'BIHEE', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (52302, '优购（UooGou）', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (53317, '迪美（DIM）', '', 'D', NULL);
INSERT INTO `tb_brand` VALUES (54347, '尼凯恩（neken）', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (55524, 'B&O PLAY', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (56411, '誉品（YEPEN）', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (59110, '诺亚信（NOAIN）', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (60106, '奥乐迪奥（AoleDior）', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (63032, '一加', 'http://img14.360buyimg.com/popshop/jfs/t2398/233/975959106/6263/a786f5b8/563b33ffN9c288c6c.jpg', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (63833, '雷蛇（Razer）', '', 'L', NULL);
INSERT INTO `tb_brand` VALUES (68927, '詹姆士（GERMY）', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (89010, '纽曼（Newman）', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (91515, '锤子（smartisan）', 'http://img13.360buyimg.com/popshop/jfs/t1954/102/907711365/5487/9f26868f/5631ccdeNe8df5efb.jpg', 'C', NULL);
INSERT INTO `tb_brand` VALUES (102501, '赛博宇华（SOP）', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (104490, '谷歌（Google）', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (109150, '爱贝多（Babyfit）', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (110303, '万利达（Malata）', '', 'W', NULL);
INSERT INTO `tb_brand` VALUES (113718, '优它（YOTAPHONE）', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (114119, 'TETC', '', 'T', NULL);
INSERT INTO `tb_brand` VALUES (123856, '炫华（xuanhua）', '', 'X', NULL);
INSERT INTO `tb_brand` VALUES (128080, '托尼洛·兰博基尼（Tonino Lamborghini）', '', 'T', NULL);
INSERT INTO `tb_brand` VALUES (129098, '木糖醇（MuTuCu）', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (130455, '爱玛（EMMA）', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (131546, '硕尼姆（Sonim）', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (133747, 'CAT', '', 'C', NULL);
INSERT INTO `tb_brand` VALUES (134922, 'ZUK', '', 'Z', NULL);
INSERT INTO `tb_brand` VALUES (135967, 'sungee', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (149070, 'AGM', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (151370, 'SOYES', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (151844, 'KODAK', '', 'K', NULL);
INSERT INTO `tb_brand` VALUES (155608, '施耐德（schneider）', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (159313, 'MOQI', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (174275, 'Essentials', '', 'E', NULL);
INSERT INTO `tb_brand` VALUES (178969, 'SAMWEI', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (179105, '小霸王（SUBOR）', '', 'X', NULL);
INSERT INTO `tb_brand` VALUES (179528, 'VETAS', '', 'V', NULL);
INSERT INTO `tb_brand` VALUES (179624, '优学派', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (180213, '纽曼（Newman）', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (194213, 'DEVIALET', '', 'D', NULL);
INSERT INTO `tb_brand` VALUES (196065, 'SHANGPREE', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (197126, 'IMOO', '', 'I', NULL);
INSERT INTO `tb_brand` VALUES (203084, 'FNNI', '', 'F', NULL);
INSERT INTO `tb_brand` VALUES (204089, '小格雷', '', 'X', NULL);
INSERT INTO `tb_brand` VALUES (205278, 'COTTEE', '', 'C', NULL);
INSERT INTO `tb_brand` VALUES (209178, 'YAAO', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (214915, '纽曼', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (219484, 'CONQUEST', '', 'C', NULL);
INSERT INTO `tb_brand` VALUES (221149, '铂爵（BIOJUET）', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (222294, '荣耀', '', 'R', NULL);
INSERT INTO `tb_brand` VALUES (223023, '保千里（PROTRULY）', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (223199, 'Blu', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (223316, 'unruly', '', 'U', NULL);
INSERT INTO `tb_brand` VALUES (225085, 'Tonino Lamborghini', '', 'T', NULL);
INSERT INTO `tb_brand` VALUES (228280, '守护宝', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (233984, '8848', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (234746, 'Gold Elite', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (238798, 'HONOR', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (240112, 'BIXING', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (246053, '锐族', '', 'R', NULL);
INSERT INTO `tb_brand` VALUES (247149, '国美（GOME）', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (247652, '中兴健康（ZTE Health）', '', 'Z', NULL);
INSERT INTO `tb_brand` VALUES (248132, 'YOSTAR', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (249404, '创星', '', 'C', NULL);
INSERT INTO `tb_brand` VALUES (253520, 'VERTU', '', 'V', NULL);
INSERT INTO `tb_brand` VALUES (261196, 'LBER', '', 'L', NULL);
INSERT INTO `tb_brand` VALUES (261480, '滝风（ION MEDIC）', '', 'L', NULL);
INSERT INTO `tb_brand` VALUES (262180, '梦幻门（MENG HUAN MEN）', '', 'M', NULL);
INSERT INTO `tb_brand` VALUES (262334, '意龍（E&L）', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (264451, '艾尼卡（Anica）', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (265247, 'DMZ DAMUZHI', '', 'D', NULL);
INSERT INTO `tb_brand` VALUES (267981, 'UNNO', '', 'U', NULL);
INSERT INTO `tb_brand` VALUES (271696, '红鸟', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (276102, '紐萬（Niuwan）', '', 'N', NULL);
INSERT INTO `tb_brand` VALUES (276381, 'Podor', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (276780, '海语（HAIYU）', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (278268, 'YISON', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (278755, 'SOAP', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (279173, 'renebelle', '', 'R', NULL);
INSERT INTO `tb_brand` VALUES (280120, 'AKESR', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (280152, 'YOTA', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (284571, 'HOOLFINE', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (285331, '金柏利', '', 'J', NULL);
INSERT INTO `tb_brand` VALUES (286273, 'BLACKPOULO', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (287292, 'SIMPLEWAY', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (290104, 'KaiDC', '', 'K', NULL);
INSERT INTO `tb_brand` VALUES (290682, '奥缇斯（autrise）', '', 'A', NULL);
INSERT INTO `tb_brand` VALUES (302250, '橄榄树（Olive tree）', '', 'G', NULL);
INSERT INTO `tb_brand` VALUES (304654, 'HIGE', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (314866, '360手机', '', 'S', NULL);
INSERT INTO `tb_brand` VALUES (316578, '克里特（kreta）', '', 'k', NULL);
INSERT INTO `tb_brand` VALUES (316596, 'HOOLFINE', '', 'H', NULL);
INSERT INTO `tb_brand` VALUES (316618, 'RONXS', '', 'R', NULL);
INSERT INTO `tb_brand` VALUES (325396, '飞利浦（PHILIPS）', '', 'P', NULL);
INSERT INTO `tb_brand` VALUES (325398, '比亚兹（ESK）', '', 'B', NULL);
INSERT INTO `tb_brand` VALUES (325399, '亿色(ESR)', '', 'Y', NULL);
INSERT INTO `tb_brand` VALUES (325400, '阿里斯顿', NULL, 'A', NULL);
INSERT INTO `tb_brand` VALUES (325402, 'ABC', NULL, 'A', NULL);
INSERT INTO `tb_brand` VALUES (325403, 'ABC', NULL, 'A', NULL);
INSERT INTO `tb_brand` VALUES (325404, '黑马', 'http://www.itheima.com/', 'H', NULL);
INSERT INTO `tb_brand` VALUES (325405, '传智', 'http://www.baidu.com', 'C', NULL);
INSERT INTO `tb_brand` VALUES (325406, '黑马', '11', 'H', NULL);
INSERT INTO `tb_brand` VALUES (325411, '迷你', 'http://localhost:9101/img/12.jpg', 'M', NULL);
INSERT INTO `tb_brand` VALUES (325412, '新立', 'http://localhost:9101/img/11.jpg', 'X', NULL);
INSERT INTO `tb_brand` VALUES (325413, '我', NULL, '1', NULL);

SET FOREIGN_KEY_CHECKS = 1;
