/*
Navicat MySQL Data Transfer

Source Server         : 120.79.192.253mariadb5.5
Source Server Version : 50556
Source Host           : 120.79.192.253:3306
Source Database       : admin_dev

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-10-23 18:22:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bike`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限Id',
  `resource` varchar(256) NOT NULL COMMENT '权限对应的资源',
  `code` varchar(256) NOT NULL COMMENT '权限的代码/通配符,对应代码中@hasAuthority(xx)',
  `handle` varchar(256) NOT NULL COMMENT '对应的资源操作',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user', 'user:list', 'list');
INSERT INTO `permission` VALUES ('2', 'user', 'user:add', 'add');
INSERT INTO `permission` VALUES ('3', 'user', 'user:update', 'update');
INSERT INTO `permission` VALUES ('4', 'user', 'user:delete', 'delete');
INSERT INTO `permission` VALUES ('5', 'role', 'role:list', 'list');
INSERT INTO `permission` VALUES ('6', 'role', 'role:add', 'add');
INSERT INTO `permission` VALUES ('7', 'role', 'role:update', 'update');
INSERT INTO `permission` VALUES ('8', 'role', 'role:delete', 'delete');
INSERT INTO `permission` VALUES ('9', 'article', 'article:list', 'list');
INSERT INTO `permission` VALUES ('10', 'permission', 'permission:list', 'list');
INSERT INTO `permission` VALUES ('11', 'permission', 'permission:add', 'add');
INSERT INTO `permission` VALUES ('12', 'permission', 'permission:delete', 'delete');
INSERT INTO `permission` VALUES ('13', 'permission', 'permission:update', 'update');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('6', 'ROLE_ABC');
INSERT INTO `role` VALUES ('5', 'ROLE_ADD');
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('3', 'ROLE_TEST');
INSERT INTO `role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色Id',
  `permission_id` bigint(20) unsigned NOT NULL COMMENT '权限Id',
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('2', '9');
INSERT INTO `role_permission` VALUES ('2', '10');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('3', '2');
INSERT INTO `role_permission` VALUES ('3', '3');
INSERT INTO `role_permission` VALUES ('3', '4');
INSERT INTO `role_permission` VALUES ('3', '5');
INSERT INTO `role_permission` VALUES ('3', '6');
INSERT INTO `role_permission` VALUES ('3', '7');
INSERT INTO `role_permission` VALUES ('3', '8');
INSERT INTO `role_permission` VALUES ('3', '9');
INSERT INTO `role_permission` VALUES ('3', '10');
INSERT INTO `role_permission` VALUES ('5', '1');
INSERT INTO `role_permission` VALUES ('5', '5');
INSERT INTO `role_permission` VALUES ('5', '9');
INSERT INTO `role_permission` VALUES ('5', '10');
INSERT INTO `role_permission` VALUES ('6', '1');
INSERT INTO `role_permission` VALUES ('6', '5');
INSERT INTO `role_permission` VALUES ('6', '6');
INSERT INTO `role_permission` VALUES ('6', '9');
INSERT INTO `role_permission` VALUES ('6', '10');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `avatar` varchar(1024) DEFAULT 'https://www.tupianku.com/view/large/13862/640.jpeg' COMMENT '头像',
  `resume` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `login_time` datetime DEFAULT NULL COMMENT '上一次登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_user_username` (`username`),
  UNIQUE KEY `ix_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin@qq.com', 'admin', '$2a$10$95xU5ZpkLz7ZrEk/hSvxHOZliUYKo0K1iRnUD8iStSdv.O9c3rvtC', 'https://www.tupianku.com/view/large/13862/640.jpeg', 'IT', '2018-02-01 00:00:00', '2018-10-23 18:21:54');
INSERT INTO `user` VALUES ('2', 'user@qq.com', 'user', '$10$95xU5ZpkLz7ZrEk/hSvxHOZliUYKo0K1iRnUD8iStSdv.O9c3rvtC', 'https://www.tupianku.com/view/large/13862/640.jpeg', 'A normal user', '2018-02-02 00:00:00', '2018-08-06 09:28:35');
INSERT INTO `user` VALUES ('3', 'test@qq.com', 'test', '$2a$10$ItmuMLR4wUb5jjkBzg/CwuQADK7d9qbU725e.KyT/lLJEZeykDL62', 'https://www.tupianku.com/view/large/13862/640.jpeg', 'To Test', '2018-02-03 00:00:00', '2018-08-01 16:35:15');
INSERT INTO `user` VALUES ('4', '1107022342@qq.com', '233moutian', '$2a$10$Svdaok0w7Qb43j7OuYOQO.O0mLeC2Nz8I.9IKpjZ2pECqbASKA9F2', 'https://www.tupianku.com/view/large/13862/640.jpeg', null, null, null);
INSERT INTO `user` VALUES ('5', '747608835@qq.com', 'zhaozeyu', '$2a$10$LTlekj8efcDp1oVN/abUpuM4wDgGczyCMKR7VDG2THlmcgCSdZdOK', 'https://www.tupianku.com/view/large/13862/640.jpeg', null, null, null);
INSERT INTO `user` VALUES ('6', '1107022349@qq.com', 'sbzzw', '$2a$10$w61MlFWJkYHqhQ5z/9XlcOlWiCgzGmhdegP20DQ/Jxc1HKCNF9VJ6', 'https://www.tupianku.com/view/large/13862/640.jpeg', null, null, null);
INSERT INTO `user` VALUES ('7', '7321231231@qq.com', 'edfwe', '$2a$10$PB.TFtLVm3c6sL8UYPmlaeiDrltskC1YrQG1y5FAZX3RdtnfNT3Hy', 'https://www.tupianku.com/view/large/13862/640.jpeg', null, null, null);
INSERT INTO `user` VALUES ('8', '11007022342@qq.com', '987654321', '$2a$10$511DvHfr1z7Vj/fec.2m3.crVlFuQqNlOG6UIqczvFEjGUxdu.cXK', 'https://www.tupianku.com/view/large/13862/640.jpeg', null, null, null);
INSERT INTO `user` VALUES ('9', '54632846@qq.com', 'sihbw', '$2a$10$8m1ziXK0JE4ZU1AdAuYh2OgKWY4dZ0qYhPodvjsKjtc.UEqIGrh8.', 'https://www.tupianku.com/view/large/13862/640.jpeg', null, null, null);

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户Id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_fk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('4', '3');
INSERT INTO `user_role` VALUES ('5', '2');
INSERT INTO `user_role` VALUES ('6', '2');
INSERT INTO `user_role` VALUES ('7', '2');
INSERT INTO `user_role` VALUES ('8', '2');
INSERT INTO `user_role` VALUES ('9', '2');
