/*
Navicat MySQL Data Transfer

Source Server         : mySql
Source Server Version : 50137
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50137
File Encoding         : 65001

Date: 2017-11-29 15:29:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `attachmentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `attachmentName` varchar(50) DEFAULT NULL COMMENT '名称',
  `attachmentPath` varchar(50) DEFAULT NULL COMMENT '路径',
  `attachmentTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`attachmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES ('1', '手动备份20171129142347.xls', 'logs/backup', '2017-11-29 14:23:47');
INSERT INTO `attachment` VALUES ('2', '手动备份20171129142641.xls', 'logs/backup', '2017-11-29 14:26:41');
INSERT INTO `attachment` VALUES ('3', '手动备份20171129142920.xls', 'logs/backup', '2017-11-29 14:29:20');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志',
  `userName` varchar(30) DEFAULT NULL COMMENT '操作人',
  `createTime` datetime DEFAULT NULL COMMENT '时间',
  `content` text COMMENT '详细',
  `operation` varchar(300) DEFAULT NULL COMMENT '操作类型（增删改）',
  `ip` varchar(60) DEFAULT NULL COMMENT 'IP地址',
  `module` varchar(40) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', 'test', '2017-11-29 14:31:04', null, '退出', null, null);
INSERT INTO `log` VALUES ('2', 'test', '2017-11-29 14:31:14', null, '登录', '192.168.1.17', null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menuName` varchar(50) DEFAULT NULL COMMENT '名称',
  `menuUrl` varchar(100) DEFAULT NULL COMMENT '方法',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `menuDescription` varchar(200) DEFAULT NULL COMMENT '描述',
  `state` varchar(20) DEFAULT NULL COMMENT '状态/OPEN/CLOSED',
  `iconCls` varchar(50) DEFAULT NULL COMMENT '图标',
  `seq` int(11) DEFAULT NULL COMMENT '顺序排序',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'SSM系统', '', '-1', '主菜单', 'isParent', 'fa fa-university', '1');
INSERT INTO `menu` VALUES ('2', '系统管理', '', '1', '', 'isParent', 'fa fa-desktop', '1');
INSERT INTO `menu` VALUES ('3', '修改密码', 'javascript:editPassword();//', '1', '', 'close', 'fa fa-key', '4');
INSERT INTO `menu` VALUES ('4', '安全退出', 'logout.htm', '1', '', 'close', 'fa fa-user-times', '5');
INSERT INTO `menu` VALUES ('5', '菜单管理', 'menu/menuIndex.htm', '2', '', '', 'fa fa-sliders', '4');
INSERT INTO `menu` VALUES ('6', '角色管理', 'role/roleIndex.htm', '2', '', '', 'fa fa-users', '3');
INSERT INTO `menu` VALUES ('7', '用户管理', 'user/userIndex.htm', '2', '', '', 'fa fa-user', '2');
INSERT INTO `menu` VALUES ('8', '日志管理', 'log/logIndex.htm', '2', '', '', 'fa fa-tags', '4');

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `operationId` int(11) NOT NULL AUTO_INCREMENT COMMENT '具体的方法',
  `operationName` varchar(100) DEFAULT NULL COMMENT '方法名',
  `menuId` int(11) DEFAULT NULL COMMENT '所属菜单',
  `menuName` varchar(50) NOT NULL,
  `operationCode` varchar(50) DEFAULT NULL,
  `iconCls` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`operationId`),
  KEY `menuId` (`menuId`),
  CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`menuId`) REFERENCES `menu` (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=10015 DEFAULT CHARSET=utf8 COMMENT='具体的页面按钮上的方法\r\n（此自增ID至少从10000开始）';

-- ----------------------------
-- Records of operation
-- ----------------------------
INSERT INTO `operation` VALUES ('10000', '添加', '5', '菜单管理', 'add', '');
INSERT INTO `operation` VALUES ('10001', '修改', '5', '菜单管理', 'edit', '');
INSERT INTO `operation` VALUES ('10002', '删除', '5', '菜单管理', 'del', '');
INSERT INTO `operation` VALUES ('10003', '添加', '7', '用户管理', 'btn_add', 'glyphicon glyphicon-plus');
INSERT INTO `operation` VALUES ('10004', '修改', '7', '用户管理', 'btn_edit', 'glyphicon glyphicon-pencil');
INSERT INTO `operation` VALUES ('10005', '删除', '7', '用户管理', 'btn_delete', 'glyphicon glyphicon-remove');
INSERT INTO `operation` VALUES ('10006', '添加', '6', '角色管理', 'btn_add', 'glyphicon glyphicon-plus');
INSERT INTO `operation` VALUES ('10007', '修改', '6', '角色管理', 'btn_edit', 'glyphicon glyphicon-pencil');
INSERT INTO `operation` VALUES ('10008', '删除', '6', '角色管理', 'btn_delete', 'glyphicon glyphicon-remove');
INSERT INTO `operation` VALUES ('10009', '授权', '6', '角色管理', 'btn_rightCtrl', 'glyphicon glyphicon-eye-open');
INSERT INTO `operation` VALUES ('10010', '下载后台日志（log4j）', '8', '日志管理', 'btn_downloadLog4j', 'glyphicon glyphicon-download-alt');
INSERT INTO `operation` VALUES ('10011', '手动备份（业务操作）', '8', '日志管理', 'btn_manualBackup', 'glyphicon glyphicon-inbox');
INSERT INTO `operation` VALUES ('10012', '删除', '8', '日志管理', 'btn_delete', 'glyphicon glyphicon-remove');
INSERT INTO `operation` VALUES ('10013', '按钮管理', '5', '菜单管理', 'btnCtrl', '');
INSERT INTO `operation` VALUES ('10014', '备份日志记录', '8', '日志管理', 'btn_downloadLogBus', 'glyphicon glyphicon-download');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `menuIds` varchar(200) DEFAULT NULL COMMENT '菜单IDs',
  `operationIds` varchar(200) DEFAULT NULL COMMENT '按钮IDS',
  `roleDescription` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '1,2,3,4,5,6,7,8', '10000,10001,10002,10003,10004,10005,10006,10007,10008,10009,10010,10011,10012,10013,10014', '拥有全部权限的超级管理员角色');
INSERT INTO `role` VALUES ('2', '测试员', '1,2,3,4,6,7,8', '10006,10007,10008,10009,10003,10004,10005,10010,10011,10012,10014', '拥有系统所有业务功能的角色');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `tokenId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `userAgent` varchar(50) DEFAULT NULL COMMENT '用户（md5）',
  `token` varchar(100) DEFAULT NULL COMMENT 'md5(username+md5(useragent))',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `expireTime` datetime DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`tokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户登录信息（用于自动登录）';

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('1', '1', '0b239ed7c13c034f6b834897f0b54bba', '66895df164cf660402ab453a53496cd6', '2017-11-29 14:26:21', '2017-12-13 14:26:21');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `userDescription` varchar(200) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', null, '1', '超级管理员，供开发方使用');
INSERT INTO `user` VALUES ('2', 'test', 'test', null, '2', '测试员，供测试方使用');
