
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_authority`
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authcode` varchar(30) NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `requestURI` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authcode` (`authcode`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('1', 'user_mgr', '用户管理', 'user manage', '/admin/user');
INSERT INTO `t_authority` VALUES ('2', 'role_mgr', '角色管理相关权限', '角色管理', '/admin/role');
INSERT INTO `t_authority` VALUES ('3', 'authority_mgr', '权限管理', '权限管理', '/admin/authority');
INSERT INTO `t_authority` VALUES ('4', 'product_mgr', '商品管理', '商品管理', '/admin/product');
INSERT INTO `t_authority` VALUES ('5', 'product_kind_mgr', '商品类别管理', '商品类别管理', '/admin/ProductCategory');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '系统管理员');
INSERT INTO `t_role` VALUES ('2', '一般用户', '一般用户');
INSERT INTO `t_role` VALUES ('3', '普通用户', '普通用户');
INSERT INTO `t_role` VALUES ('4', 'ggg', 'sss');

-- ----------------------------
-- Table structure for `t_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `roleId` bigint(20) NOT NULL,
  `authorityId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`authorityId`),
  KEY `FK8A79DDE5B29678AA` (`roleId`),
  KEY `FK8A79DDE5B7C1449E` (`authorityId`),
  CONSTRAINT `FK8A79DDE5B29678AA` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK8A79DDE5B7C1449E` FOREIGN KEY (`authorityId`) REFERENCES `t_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_authority
-- ----------------------------
INSERT INTO `t_role_authority` VALUES ('1', '1');
INSERT INTO `t_role_authority` VALUES ('1', '2');
INSERT INTO `t_role_authority` VALUES ('1', '3');
INSERT INTO `t_role_authority` VALUES ('1', '4');
INSERT INTO `t_role_authority` VALUES ('1', '5');
INSERT INTO `t_role_authority` VALUES ('4', '2');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `accountId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountId` (`accountId`),
  KEY `FKCB63CCB637E89E67` (`accountId`),
  CONSTRAINT `FKCB63CCB637E89E67` FOREIGN KEY (`accountId`) REFERENCES `t_user_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '2013-01-16 09:58:18', '111111', 'admin', '1');

-- ----------------------------
-- Table structure for `t_user_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_account`;
CREATE TABLE `t_user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_account
-- ----------------------------
INSERT INTO `t_user_account` VALUES ('1', '汉中路2号', '13888888888', '02522223333');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK331DEE5FB29678AA` (`roleId`),
  KEY `FK331DEE5FB7EBCE14` (`userId`),
  CONSTRAINT `FK331DEE5FB29678AA` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK331DEE5FB7EBCE14` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');

-- 2013-2-25 added by QiuZhiquan --

DROP TABLE IF EXISTS `t_authority`;

CREATE TABLE `t_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authcode` varchar(30) NOT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `requestURI` varchar(50) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authcode` (`authcode`),
  KEY `FK76D60138818A5D0` (`parent_id`),
  CONSTRAINT `FK76D60138818A5D0` FOREIGN KEY (`parent_id`) REFERENCES `t_authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_authority` */

insert  into `t_authority`(`id`,`authcode`,`comment`,`name`,`requestURI`,`parent_id`) values (1,'user_mgr','用户管理','user manage','/admin/user',NULL),(2,'role_mgr','角色管理相关权限','角色管理','/admin/role',NULL),(3,'authority_mgr','权限管理','权限管理','/admin/authority',NULL),(4,'product_mgr','商品管理','商品管理','/admin/product',NULL),(5,'product_kind_mgr','商品类别管理','商品类别管理','/admin/ProductCategory',NULL),(12,'user_mgr_add',NULL,'_add',NULL,1),(13,'user_mgr_update',NULL,'_update',NULL,1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`remark`,`roleName`) values (1,'系统管理员具有所有权限','系统管理员');

/*Table structure for table `t_role_authority` */

DROP TABLE IF EXISTS `t_role_authority`;

CREATE TABLE `t_role_authority` (
  `roleId` bigint(20) NOT NULL,
  `authorityId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`authorityId`),
  KEY `FK8A79DDE5B29678AA` (`roleId`),
  KEY `FK8A79DDE5B7C1449E` (`authorityId`),
  CONSTRAINT `FK8A79DDE5B29678AA` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK8A79DDE5B7C1449E` FOREIGN KEY (`authorityId`) REFERENCES `t_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_authority` */

insert  into `t_role_authority`(`roleId`,`authorityId`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,13);
