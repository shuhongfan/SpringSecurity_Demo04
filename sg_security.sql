/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.120.20_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 192.168.120.20:3306
 Source Schema         : sg_security

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 30/08/2022 09:15:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '部门管理', 'dept', 'system/dept/index', '0', '0', 'system:dept:list', '#', NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES (2, '测试', 'test', 'system/test/index', '0', '0', 'system:test:list', '#', NULL, NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int NULL DEFAULT 0 COMMENT 'del_flag',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'CEO', 'ceo', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, 'Coder', 'coder', '0', 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT 0 COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, 'admin', 'admin', '$2a$10$voqe5SmvT5OkCZZ36q9Ph.yz6pWT/MkkKl7VLnp/Un8c5UuToo8bK', '0', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 1);

SET FOREIGN_KEY_CHECKS = 1;
