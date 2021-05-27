DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
  `id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `resource` varchar(256) NOT NULL,
  `code`     varchar(256) NOT NULL,
  `handle`   varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
  `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
) AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
  `role_id`       bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`)
) DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `email`         varchar(64)  NOT NULL,
  `username`      varchar(32)  NOT NULL,
  `password`      varchar(1024) NOT NULL,
  `avatar`        varchar(1024)                                    ,
  `resume`        varchar(512) DEFAULT NULL,
  `register_time` datetime     DEFAULT NULL,
  `login_time`    datetime  DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
) DEFAULT CHARSET=utf8mb4;

