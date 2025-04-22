/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : qgfinal

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 22/04/2025 11:58:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号在线和存在状态',
  `role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'ADMIN' COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员个人信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '第一个管理员', 'admin', 'admin', '13433384487', '778005729@qq.com', '', '正常', 'ADMIN');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `score` int NULL DEFAULT 0 COMMENT '评分',
  `comment_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评论内容',
  `deal_id` int NULL DEFAULT NULL COMMENT '交易号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `deal_id`(`deal_id` ASC) USING BTREE,
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`deal_id`) REFERENCES `deal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 0, '2025-04-22T11:56', '坑人', 1);

-- ----------------------------
-- Table structure for deal
-- ----------------------------
DROP TABLE IF EXISTS `deal`;
CREATE TABLE `deal`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `pay_method` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付方式',
  `deal_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易状态',
  `deal_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易时间',
  `user_id` int NULL DEFAULT NULL COMMENT '交易者',
  `product_id` int NULL DEFAULT NULL COMMENT '交易商品',
  `product_num` int NULL DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `deal_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `deal_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '交易信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deal
-- ----------------------------
INSERT INTO `deal` VALUES (1, '即买即付', '待发货', '2025-04-22T11:56', 2, 1, 1);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `operate_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作内容',
  `operate_type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作种类',
  `operate_username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作者',
  `ip` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP',
  `operate_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES (1, '注册成功', '注册', 'admin', '127.0.0.1', '2025-04-22T11:44');
INSERT INTO `log` VALUES (2, '注册成功', '注册', 'user', '127.0.0.1', '2025-04-22T11:45');
INSERT INTO `log` VALUES (3, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-22T11:49');
INSERT INTO `log` VALUES (4, '发布商品成功', '发布商品', 'user', '127.0.0.1', '2025-04-22T11:50');
INSERT INTO `log` VALUES (5, '更新账户成功', '修改账户', 'user', '127.0.0.1', '2025-04-22T11:50');
INSERT INTO `log` VALUES (6, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-22T11:51');
INSERT INTO `log` VALUES (7, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-22T11:51');
INSERT INTO `log` VALUES (8, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T11:51');
INSERT INTO `log` VALUES (9, '更新账户成功', '修改账户', 'admin', '127.0.0.1', '2025-04-22T11:52');
INSERT INTO `log` VALUES (10, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-22T11:52');
INSERT INTO `log` VALUES (11, '注册成功', '注册', 'user2', '127.0.0.1', '2025-04-22T11:53');
INSERT INTO `log` VALUES (12, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-22T11:53');
INSERT INTO `log` VALUES (13, '更新账户成功', '修改账户', 'user2', '127.0.0.1', '2025-04-22T11:54');
INSERT INTO `log` VALUES (14, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-22T11:54');
INSERT INTO `log` VALUES (15, '购买商品成功', '购买商品', 'user2', '127.0.0.1', '2025-04-22T11:56');
INSERT INTO `log` VALUES (16, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T11:58');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品描述',
  `price` double(8, 2) NULL DEFAULT NULL COMMENT '价格',
  `stock` int NULL DEFAULT NULL COMMENT '库存',
  `category` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品图片',
  `publish_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  `publish_status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布状态',
  `user_id` int NULL DEFAULT NULL COMMENT '发布者',
  `score` int NULL DEFAULT 0 COMMENT '评分',
  `popularity` int NULL DEFAULT 0 COMMENT '流量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '好人是怎么炼成的', '又名《小丑是怎么炼成的》', 10.00, 9, '书籍', '', '2025-04-22T11:50', '已发布', 1, 1, 1);

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `report_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '举报时间',
  `report_type` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '举报种类',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '举报内容',
  `result` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '举报结果',
  `user_id` int NULL DEFAULT NULL COMMENT '举报者',
  `pointer_id` int NULL DEFAULT NULL COMMENT '举报对象',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '举报记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, '2025-04-22T11:57', '用户', '我请求封禁用户[ ID: 1 ]: 这个人我看着就不爽', '待处理', 2, 1);

-- ----------------------------
-- Table structure for sentence
-- ----------------------------
DROP TABLE IF EXISTS `sentence`;
CREATE TABLE `sentence`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `sentence_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发表时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '语句内容',
  `user_id` int NULL DEFAULT NULL COMMENT '聊天者',
  `user_role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '聊天者角色',
  `session_id` int NULL DEFAULT NULL COMMENT '隶属会话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '私聊语句' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sentence
-- ----------------------------
INSERT INTO `sentence` VALUES (1, '2025-04-22T11:45', '我向你发起了会话~', 1, 'SYSTEM', 1);
INSERT INTO `sentence` VALUES (2, '2025-04-22T11:53', '我向你发起了会话~', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (3, '2025-04-22T11:54', '我向你发起了会话~', 2, 'USER', 3);
INSERT INTO `sentence` VALUES (4, '2025-04-22T11:56', '看着作者面子上我买了', 2, 'USER', 3);
INSERT INTO `sentence` VALUES (5, '2025-04-22T11:57', '我请求封禁用户[ ID: 1 ]: 这个人我看着就不爽', 2, 'USER', 2);

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `session_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `head_id` int NULL DEFAULT NULL COMMENT '先发起会话的人',
  `head_role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '先手角色',
  `hind_id` int NULL DEFAULT NULL COMMENT '后发消息的人',
  `hind_role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '后手角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '私聊记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES (1, '2025-04-22T11:45', 1, 'SYSTEM', 1, 'USER');
INSERT INTO `session` VALUES (2, '2025-04-22T11:53', 1, 'SYSTEM', 2, 'USER');
INSERT INTO `session` VALUES (3, '2025-04-22T11:54', 2, 'USER', 1, 'USER');

-- ----------------------------
-- Table structure for systeminfo
-- ----------------------------
DROP TABLE IF EXISTS `systeminfo`;
CREATE TABLE `systeminfo`  (
  `id` int NOT NULL DEFAULT 1 COMMENT '单例系统ID',
  `role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'SYSTEM' COMMENT '单例系统标识',
  `admin_num` int NULL DEFAULT 0 COMMENT '管理员数量',
  `user_num` int NULL DEFAULT 0 COMMENT '用户数量',
  `product_num` int NULL DEFAULT 0 COMMENT '商品数量',
  `total_money` double(8, 8) NULL DEFAULT 0.00000000 COMMENT '吞吐金额总量以w为单位',
  `report_num` int NULL DEFAULT 0 COMMENT '举报数量',
  `session_num` int NULL DEFAULT 0 COMMENT '会话数量',
  `deal_num` int NULL DEFAULT 0 COMMENT '订单数量',
  `comment_num` int NULL DEFAULT 0 COMMENT '评论数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统对象' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of systeminfo
-- ----------------------------
INSERT INTO `systeminfo` VALUES (1, 'SYSTEM', 1, 2, 1, 0.00100000, 1, 3, 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引',
  `name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'USER' COMMENT '角色',
  `reputation` int NULL DEFAULT 0 COMMENT '信誉',
  `status` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号在线和存在状态',
  `pay_password` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付密码',
  `popularity` int NULL DEFAULT 0 COMMENT '流量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户个人信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '第一个用户', 'user', 'user', '13433384487', '778005729@qq.com', '', 'USER', 0, '正常', '123456', 0);
INSERT INTO `user` VALUES (2, '第二个用户', 'user2', 'user2', '13415123554', '778005729@outlook.com', '', 'USER', 1, '正常', '123456', 1);

SET FOREIGN_KEY_CHECKS = 1;
