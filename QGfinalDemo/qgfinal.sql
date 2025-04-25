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

 Date: 25/04/2025 23:55:17
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
INSERT INTO `admin` VALUES (1, '第一个管理员', 'admin', 'admin', '13433384487', '778005729@qq.com', '42f65da1-359a-493b-a711-b6e3b3c47d3c.jpg', '正常', 'ADMIN');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '交易信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of deal
-- ----------------------------
INSERT INTO `deal` VALUES (1, '即买即付', '已收货', '2025-04-22T11:56', 2, 1, 1);
INSERT INTO `deal` VALUES (2, '即买即付', '已退货', '2025-04-22T21:02', 2, 1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

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
INSERT INTO `log` VALUES (17, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-22T15:55');
INSERT INTO `log` VALUES (18, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T17:20');
INSERT INTO `log` VALUES (19, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T17:23');
INSERT INTO `log` VALUES (20, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T20:31');
INSERT INTO `log` VALUES (21, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T20:41');
INSERT INTO `log` VALUES (22, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T20:42');
INSERT INTO `log` VALUES (23, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T20:54');
INSERT INTO `log` VALUES (24, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T21:01');
INSERT INTO `log` VALUES (25, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-22T21:01');
INSERT INTO `log` VALUES (26, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-22T21:02');
INSERT INTO `log` VALUES (27, '购买商品成功', '购买商品', 'user2', '127.0.0.1', '2025-04-22T21:02');
INSERT INTO `log` VALUES (28, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T21:40');
INSERT INTO `log` VALUES (29, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T21:42');
INSERT INTO `log` VALUES (30, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-22T23:44');
INSERT INTO `log` VALUES (31, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-23T09:17');
INSERT INTO `log` VALUES (32, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-23T09:19');
INSERT INTO `log` VALUES (33, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-23T12:57');
INSERT INTO `log` VALUES (34, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-23T13:31');
INSERT INTO `log` VALUES (35, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-23T17:47');
INSERT INTO `log` VALUES (36, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-23T17:48');
INSERT INTO `log` VALUES (37, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-23T18:54');
INSERT INTO `log` VALUES (38, '更新商品成功', '更新商品', 'user', '127.0.0.1', '2025-04-23T18:54');
INSERT INTO `log` VALUES (39, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-23T22:41');
INSERT INTO `log` VALUES (40, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-23T22:41');
INSERT INTO `log` VALUES (41, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-23T22:48');
INSERT INTO `log` VALUES (42, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-23T22:48');
INSERT INTO `log` VALUES (43, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-23T23:21');
INSERT INTO `log` VALUES (44, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-24T19:54');
INSERT INTO `log` VALUES (45, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T01:52');
INSERT INTO `log` VALUES (46, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T11:26');
INSERT INTO `log` VALUES (47, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T11:29');
INSERT INTO `log` VALUES (48, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T11:32');
INSERT INTO `log` VALUES (49, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T11:34');
INSERT INTO `log` VALUES (50, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T11:38');
INSERT INTO `log` VALUES (51, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:19');
INSERT INTO `log` VALUES (52, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:21');
INSERT INTO `log` VALUES (53, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:24');
INSERT INTO `log` VALUES (54, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:26');
INSERT INTO `log` VALUES (55, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:27');
INSERT INTO `log` VALUES (56, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T13:55');
INSERT INTO `log` VALUES (57, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:57');
INSERT INTO `log` VALUES (58, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T13:57');
INSERT INTO `log` VALUES (59, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T13:59');
INSERT INTO `log` VALUES (60, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T14:00');
INSERT INTO `log` VALUES (61, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-25T14:01');
INSERT INTO `log` VALUES (62, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T14:02');
INSERT INTO `log` VALUES (63, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T14:04');
INSERT INTO `log` VALUES (64, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-25T14:04');
INSERT INTO `log` VALUES (65, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T14:04');
INSERT INTO `log` VALUES (66, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T14:05');
INSERT INTO `log` VALUES (67, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T17:40');
INSERT INTO `log` VALUES (68, '登录成功', '登录', 'admin', '127.0.0.1', '2025-04-25T17:41');
INSERT INTO `log` VALUES (69, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-25T17:49');
INSERT INTO `log` VALUES (70, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-25T19:33');
INSERT INTO `log` VALUES (71, '发布商品成功', '发布商品', 'user2', '127.0.0.1', '2025-04-25T19:34');
INSERT INTO `log` VALUES (72, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T22:31');
INSERT INTO `log` VALUES (73, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T22:34');
INSERT INTO `log` VALUES (74, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T23:17');
INSERT INTO `log` VALUES (75, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-25T23:26');
INSERT INTO `log` VALUES (76, '登录成功', '登录', 'user2', '127.0.0.1', '2025-04-25T23:27');
INSERT INTO `log` VALUES (77, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T23:36');
INSERT INTO `log` VALUES (78, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T23:50');
INSERT INTO `log` VALUES (79, '登录成功', '登录', 'user', '127.0.0.1', '2025-04-25T23:51');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '好人是怎么炼成的', '又名《小丑是怎么炼成的》', 10.00, 11, '书籍', '', '2025-04-22T11:50', '已发布', 1, 2, 2);
INSERT INTO `product` VALUES (2, '我的小丑生涯', '讲述了我的种种小丑经历', 10.00, 10, '书籍', '', '2025-04-26T19:34', '未发布', 2, 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '举报记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, '2025-04-22T11:57', '用户', '我请求封禁用户[ ID: 1 ]: 这个人我看着就不爽', '批准', 2, 1);
INSERT INTO `report` VALUES (2, '2025-04-23T09:19', '商品', '我请求下架商品[ ID: 1 ]: 我不喜欢你', '反对', 2, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '私聊语句' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sentence
-- ----------------------------
INSERT INTO `sentence` VALUES (1, '2025-04-22T11:45', '我向你发起了会话~', 1, 'SYSTEM', 1);
INSERT INTO `sentence` VALUES (2, '2025-04-22T11:53', '我向你发起了会话~', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (3, '2025-04-22T11:54', '我向你发起了会话~', 2, 'USER', 3);
INSERT INTO `sentence` VALUES (4, '2025-04-22T11:56', '看着作者面子上我买了', 2, 'USER', 3);
INSERT INTO `sentence` VALUES (5, '2025-04-22T11:57', '我请求封禁用户[ ID: 1 ]: 这个人我看着就不爽', 2, 'USER', 2);
INSERT INTO `sentence` VALUES (6, '2025-04-22T21:02', '谢谢惠顾于[2025-04-22T21:02]购买商品[好人是怎么炼成的]', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (7, '2025-04-22T21:03', '您于[2025-04-22T21:03]进行评价[真是美妙呢]', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (8, '2025-04-23T09:19', '我请求下架商品[ ID: 1 ]: 我不喜欢你', 2, 'USER', 2);
INSERT INTO `sentence` VALUES (9, '2025-04-23T12:58', '您于[2025-04-22T11:57]提交的举报已批准', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (10, '2025-04-23T12:58', '您于[2025-04-23T09:19]提交的举报不被批准', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (11, '2025-04-23T17:48', '您于[2025-04-23T17:48]进行评价[hhh]', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (12, '2025-04-23T17:48', '您于[2025-04-23T17:48]成功退订商品[好人是怎么炼成的]', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (13, '2025-04-23T22:41', '您的商品[好人是怎么炼成的]在[2025-04-23T22:41]开始发货', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (14, '2025-04-23T22:48', '您的商品[好人是怎么炼成的]在[2025-04-23T22:48]已经到达,请接收~', 1, 'SYSTEM', 2);
INSERT INTO `sentence` VALUES (15, '2025-04-23T22:49', '您的商品[好人是怎么炼成的]在[2025-04-23T22:49]时被用户[第二个用户]接收了', 1, 'SYSTEM', 1);
INSERT INTO `sentence` VALUES (16, '2025-04-25T22:31', '666你没事禁我干嘛', 1, 'USER', 3);
INSERT INTO `sentence` VALUES (17, '2025-04-25T23:36', '你是个好人', 1, 'USER', 3);

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
INSERT INTO `systeminfo` VALUES (1, 'SYSTEM', 1, 2, 2, 0.00100000, 2, 3, 2, 0);

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
INSERT INTO `user` VALUES (1, '第一个用户', 'user', 'user', '13433384487', '778005729@qq.com', 'a3fe08d6-2325-4206-a22f-7e8b09014755.jpeg', 'USER', 2, '正常', '123456', 2);
INSERT INTO `user` VALUES (2, '第二个用户', 'user2', 'user2', '13415123554', '778005729@outlook.com', '821de165-12b2-42cf-98e9-ce50139ef575.jpeg', 'USER', 0, '正常', '123456', 0);

SET FOREIGN_KEY_CHECKS = 1;
