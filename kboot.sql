/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : kboot

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 10/08/2018 16:29:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kboot_permission
-- ----------------------------
DROP TABLE IF EXISTS `kboot_permission`;
CREATE TABLE `kboot_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enable` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `path` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `permission_key` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `resource` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKarkyg4p1bouosuixjo7rebdjn` (`parent_id`),
  CONSTRAINT `FKarkyg4p1bouosuixjo7rebdjn` FOREIGN KEY (`parent_id`) REFERENCES `kboot_permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of kboot_permission
-- ----------------------------
BEGIN;
INSERT INTO `kboot_permission` VALUES (1, b'1', '系统管理', '', 'system', '', 'CATEGORY', 0, NULL, 'fa fa-cogs');
INSERT INTO `kboot_permission` VALUES (2, b'1', '权限管理', '/system/permission', 'system:permission', '/system/permission/list', 'MENU', 0, 1, 'fa fa-key');
INSERT INTO `kboot_permission` VALUES (3, b'1', '角色管理', '/system/role', 'system:role', '/system/role/list', 'MENU', 1, 1, 'fa fa-users');
INSERT INTO `kboot_permission` VALUES (4, b'1', '用户管理', '/system/user', 'system:user', '/system/user/list', 'MENU', 1, 1, 'fa fa-user');
INSERT INTO `kboot_permission` VALUES (5, b'1', '创建', '', 'system:user:create', '/system/user/save,/system/user/roleList', 'FUNCTION', 0, 4, 'fa fa-user-plus');
INSERT INTO `kboot_permission` VALUES (6, b'1', '编辑', '', 'system:user:edit', '/system/user/get,/system/user/save,/system/user/roleList', 'FUNCTION', 0, 4, 'fa fa-edit');
INSERT INTO `kboot_permission` VALUES (7, b'1', '删除', '', 'system:user:delete', '/system/user/delete', 'FUNCTION', NULL, 4, 'fa fa-trash');
INSERT INTO `kboot_permission` VALUES (8, b'1', '创建', NULL, 'system:permission:create', '/system/permission/save', 'FUNCTION', 0, 2, 'fa fa-plus');
INSERT INTO `kboot_permission` VALUES (9, b'1', '状态变更', NULL, 'system:permission:updateEnable', '/system/permission/updateEnable', 'FUNCTION', 0, 2, NULL);
INSERT INTO `kboot_permission` VALUES (10, b'1', '删除', NULL, 'system:permission:delete', '/system/permission/delete', 'FUNCTION', 0, 2, NULL);
INSERT INTO `kboot_permission` VALUES (11, b'1', '编辑', NULL, 'system:permission:update', '/system/permission/get,/system/permission/save', 'FUNCTION', 0, 2, 'fa fa-edit');
INSERT INTO `kboot_permission` VALUES (12, b'1', '状态变更', NULL, 'system:user:updateEnable', '/system/user/updateEnable', 'FUNCTION', 0, 4, NULL);
INSERT INTO `kboot_permission` VALUES (13, b'1', '密码重置', NULL, 'system:user:resetPassword', '/system/user/resetPassword', 'FUNCTION', 0, 4, NULL);
INSERT INTO `kboot_permission` VALUES (14, b'1', '创建', NULL, 'system:role:create', '/system/role/save', 'FUNCTION', 0, 3, 'fa fa-plus');
INSERT INTO `kboot_permission` VALUES (15, b'1', '编辑', NULL, 'system:role:edit', '/system/role/get,/system/role/save', 'FUNCTION', 0, 3, 'fa fa-edit');
INSERT INTO `kboot_permission` VALUES (16, b'1', '删除', NULL, 'system:role:delete', '/system/role/delete', 'FUNCTION', 0, 3, 'fa fa-trash');
INSERT INTO `kboot_permission` VALUES (17, b'1', '状态变更', NULL, 'system:role:updateEnable', '/system/role/updateEnable', 'FUNCTION', 0, 3, NULL);
INSERT INTO `kboot_permission` VALUES (18, b'1', '授权', NULL, 'system:role:grant', '/system/role/permissionList,/system/role/grant', 'FUNCTION', 0, 3, NULL);
COMMIT;

-- ----------------------------
-- Table structure for kboot_role
-- ----------------------------
DROP TABLE IF EXISTS `kboot_role`;
CREATE TABLE `kboot_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `role_key` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `role_name` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iace11lm41qsi7dstkaiecion` (`role_key`),
  UNIQUE KEY `UK_eu9uvi1fl9j2kmtul6bmcu0mh` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of kboot_role
-- ----------------------------
BEGIN;
INSERT INTO `kboot_role` VALUES (1, '拥有系统全部权限，请谨慎分配。', b'1', 'admin', '管理员');
INSERT INTO `kboot_role` VALUES (3, '测试', b'1', 'normal', '普通用户');
COMMIT;

-- ----------------------------
-- Table structure for kboot_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `kboot_role_permission`;
CREATE TABLE `kboot_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK6l1rpsk0jgvg41t538728fjm4` (`permission_id`),
  CONSTRAINT `FK4ehlewddmsjetvam13ef633iw` FOREIGN KEY (`role_id`) REFERENCES `kboot_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK6l1rpsk0jgvg41t538728fjm4` FOREIGN KEY (`permission_id`) REFERENCES `kboot_permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of kboot_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `kboot_role_permission` VALUES (1, 1);
INSERT INTO `kboot_role_permission` VALUES (3, 1);
INSERT INTO `kboot_role_permission` VALUES (1, 2);
INSERT INTO `kboot_role_permission` VALUES (1, 3);
INSERT INTO `kboot_role_permission` VALUES (3, 3);
INSERT INTO `kboot_role_permission` VALUES (1, 4);
INSERT INTO `kboot_role_permission` VALUES (3, 4);
INSERT INTO `kboot_role_permission` VALUES (1, 5);
INSERT INTO `kboot_role_permission` VALUES (3, 5);
INSERT INTO `kboot_role_permission` VALUES (1, 6);
INSERT INTO `kboot_role_permission` VALUES (3, 6);
INSERT INTO `kboot_role_permission` VALUES (1, 7);
INSERT INTO `kboot_role_permission` VALUES (3, 7);
INSERT INTO `kboot_role_permission` VALUES (1, 8);
INSERT INTO `kboot_role_permission` VALUES (1, 9);
INSERT INTO `kboot_role_permission` VALUES (1, 10);
INSERT INTO `kboot_role_permission` VALUES (1, 11);
INSERT INTO `kboot_role_permission` VALUES (1, 12);
INSERT INTO `kboot_role_permission` VALUES (1, 13);
INSERT INTO `kboot_role_permission` VALUES (3, 13);
INSERT INTO `kboot_role_permission` VALUES (1, 14);
INSERT INTO `kboot_role_permission` VALUES (1, 15);
INSERT INTO `kboot_role_permission` VALUES (1, 16);
INSERT INTO `kboot_role_permission` VALUES (1, 17);
INSERT INTO `kboot_role_permission` VALUES (1, 18);
INSERT INTO `kboot_role_permission` VALUES (3, 18);
COMMIT;

-- ----------------------------
-- Table structure for kboot_user
-- ----------------------------
DROP TABLE IF EXISTS `kboot_user`;
CREATE TABLE `kboot_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `enable` bit(1) DEFAULT NULL,
  `password` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `tel` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `avatar` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dxesfklauarqhov4147i100ud` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of kboot_user
-- ----------------------------
BEGIN;
INSERT INTO `kboot_user` VALUES (6, 'test', b'1', 'f379eaf3c831b04de153469d1bec345e', '18676037292', '李四', 'avatar/user1533889493907.jpg');
INSERT INTO `kboot_user` VALUES (7, 'admin', b'1', 'f379eaf3c831b04de153469d1bec345e', '13203314875', '夏悸', 'avatar/user71533889611815.jpg');
COMMIT;

-- ----------------------------
-- Table structure for kboot_user_role
-- ----------------------------
DROP TABLE IF EXISTS `kboot_user_role`;
CREATE TABLE `kboot_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKnviybsn4jexeg8t4n0n4bagi5` (`role_id`),
  CONSTRAINT `FKnviybsn4jexeg8t4n0n4bagi5` FOREIGN KEY (`role_id`) REFERENCES `kboot_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKrgdhhtcvdp38598e9uhxd3pb` FOREIGN KEY (`user_id`) REFERENCES `kboot_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of kboot_user_role
-- ----------------------------
BEGIN;
INSERT INTO `kboot_user_role` VALUES (7, 1);
INSERT INTO `kboot_user_role` VALUES (6, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
