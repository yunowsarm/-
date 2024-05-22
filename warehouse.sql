/*
 Navicat Premium Data Transfer

 Source Server         : JavaEE
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 21/05/2024 17:27:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `manager` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tale_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '曼因团队', '夜之城圣多明戈的Megabuilding H4公寓', '大卫 马丁内斯', '11223344556');
INSERT INTO `customer` VALUES (2, '光顾的大学生团队', '江宁大学城中所有大学', '经常光顾苏果超市的同学', '00998877665');
INSERT INTO `customer` VALUES (3, '地下王国的提姆', '提姆村', '提姆们', '33557799001');
INSERT INTO `customer` VALUES (4, 'QQ牧场里的动物', 'QQ空间里的QQ牧场', '所有食草动物', '11557799224');
INSERT INTO `customer` VALUES (5, 'QQ农场的植物', 'QQ空间里的QQ农场', '植物们', '3216598760');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '货物id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物名',
  `provider_id` int NULL DEFAULT NULL COMMENT '供货商id',
  `price` int NULL DEFAULT NULL COMMENT '价格',
  `size` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `packages` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '包装',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '鸡胸肉', 4, 3, '300g/个', '60个/箱');
INSERT INTO `goods` VALUES (2, '夜之城特调', 1, 88, '200ml/瓶', '24瓶/箱');
INSERT INTO `goods` VALUES (3, '银手环', 1, 88, '330ml/瓶', '24瓶/箱');
INSERT INTO `goods` VALUES (4, '提姆薯片', 3, 2, '200g/包', '24包/箱');
INSERT INTO `goods` VALUES (5, '提姆胸甲', 3, 20000, '500g纳米合金胸甲', '1件/箱');
INSERT INTO `goods` VALUES (6, '牧草', 2, 200, '5kg/捆', '100捆/箱');

-- ----------------------------
-- Table structure for input_form
-- ----------------------------
DROP TABLE IF EXISTS `input_form`;
CREATE TABLE `input_form`  (
  `form_id` int NOT NULL AUTO_INCREMENT,
  `provider_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  `house_id` int NULL DEFAULT NULL,
  `change_number` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (`form_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of input_form
-- ----------------------------
INSERT INTO `input_form` VALUES (1, 1, '2024-05-19 11:59:08', 2, 1, 5000, 1);

-- ----------------------------
-- Table structure for leftmenu
-- ----------------------------
DROP TABLE IF EXISTS `leftmenu`;
CREATE TABLE `leftmenu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `open` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of leftmenu
-- ----------------------------
INSERT INTO `leftmenu` VALUES (1, 0, '仓库管理系统', '&#xe68e;', '', 1);
INSERT INTO `leftmenu` VALUES (2, 1, '基础管理', '&#xe857;', '', 0);
INSERT INTO `leftmenu` VALUES (3, 1, '仓库管理', '&#xe645;', '', 1);
INSERT INTO `leftmenu` VALUES (4, 1, '系统管理', '&#xe614;', '', 0);
INSERT INTO `leftmenu` VALUES (5, 2, '客户管理', '&#xe66f;', '/sys/toCustomerManager', 0);
INSERT INTO `leftmenu` VALUES (6, 2, '供应商管理', '&#xe698;', '/sys/toProviderManager', 0);
INSERT INTO `leftmenu` VALUES (7, 3, '入库', '&#xe65b;', '/sys/toInputManager', 0);
INSERT INTO `leftmenu` VALUES (8, 3, '出库', '&#xe65a;', '/sys/toOutputManager', 0);
INSERT INTO `leftmenu` VALUES (9, 4, '角色管理', '&#xe650;', '/sys/toRoleManager', 0);
INSERT INTO `leftmenu` VALUES (10, 4, '用户管理', '&#xe612;', '/sys/toUserManager', 0);
INSERT INTO `leftmenu` VALUES (11, 2, '商品管理', '&#xe658;', '/sys/toGoodsManager', 0);
INSERT INTO `leftmenu` VALUES (12, 3, '仓库基础设置', '&#xe716;', '/sys/toWarehouseManager', 0);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL,
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `sex` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 0, '2113212024', '123456', '2113212024史子龙', 21, '男');

-- ----------------------------
-- Table structure for output_form
-- ----------------------------
DROP TABLE IF EXISTS `output_form`;
CREATE TABLE `output_form`  (
  `form_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `goods_id` int NULL DEFAULT NULL,
  `house_id` int NULL DEFAULT NULL,
  `change_number` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (`form_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of output_form
-- ----------------------------
INSERT INTO `output_form` VALUES (1, 1, '2024-05-19 11:59:36', 2, 1, 5000, 1);

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider`  (
  `provider_id` int NOT NULL AUTO_INCREMENT COMMENT '供货商id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供货商名称',
  `address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `manager` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `tale_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人电话',
  PRIMARY KEY (`provider_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of provider
-- ----------------------------
INSERT INTO `provider` VALUES (1, '夜之城荒坂大厦供应商', '夜之城荒坂大厦', '荒坂', '1234567890');
INSERT INTO `provider` VALUES (2, 'QQ农场供应商', 'QQ牧场斜对面', '农场主', '0987654321');
INSERT INTO `provider` VALUES (3, '地下王国首都供应商', '圣域', '艾利斯 逐星', '1357908642');
INSERT INTO `provider` VALUES (4, '江宁蔬果市场供应商', '江宁大学城', '不愿透露姓名的史某人', '2468097531');
INSERT INTO `provider` VALUES (5, 'QQ牧场供应商', 'QQ农场斜对面', '牧场主', '1243568790');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int NOT NULL COMMENT '角色编号',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `available` int NULL DEFAULT NULL,
  `createtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (0, '超级管理员', '拥有所有菜单权限', 1, '2024-05-19 19:31:03');
INSERT INTO `role` VALUES (1, '1号仓库管理员', '能够操作1号仓库', 1, '2024-05-19 19:32:10');
INSERT INTO `role` VALUES (2, '2号仓库管理员', '能够操作2号仓库', 1, '2024-05-19 19:32:48');
INSERT INTO `role` VALUES (3, '3号仓库管理员', '能够操作3号仓库', 1, '2024-05-19 19:40:03');
INSERT INTO `role` VALUES (4, '4号仓库管理员', '能够操作4号仓库', 1, '2024-05-19 19:50:14');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `goods_id` int NULL DEFAULT NULL,
  `house_id` int NOT NULL,
  `number` int NULL DEFAULT NULL,
  `upper_alert` int NULL DEFAULT NULL,
  `under_alert` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (5, 3, 5000, 10000, 0);
INSERT INTO `stock` VALUES (4, 3, 5000, 10000, 0);
INSERT INTO `stock` VALUES (1, 2, 5000, 10000, 0);
INSERT INTO `stock` VALUES (2, 1, 5000, 10000, 0);
INSERT INTO `stock` VALUES (3, 1, 5000, 10000, 0);
INSERT INTO `stock` VALUES (6, 4, 5000, 10000, 0);

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `house_id` int NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`house_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, '大鸟转转转酒吧仓库', '夜之城德拉曼总部斜对面的高架桥下');
INSERT INTO `warehouse` VALUES (2, '文鼎广场苏果超市仓库', '江苏省南京市江宁区文鼎广场大学城仓库');
INSERT INTO `warehouse` VALUES (3, '提姆超市仓库', '地底瀑布的黑暗蘑菇地提姆村');
INSERT INTO `warehouse` VALUES (4, 'QQ农场仓库', 'QQ空间中的QQ农场');

SET FOREIGN_KEY_CHECKS = 1;
