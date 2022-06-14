# 创建用户服务所需数据
CREATE DATABASE /*!32312 IF NOT EXISTS*/`newbee_mall_cloud_user_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `newbee_mall_cloud_user_db`;

DROP TABLE IF EXISTS `tb_newbee_mall_admin_user`;

# 创建管理员用户表

CREATE TABLE `tb_newbee_mall_admin_user` (
                                             `admin_user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
                                             `login_user_name` varchar(50) NOT NULL COMMENT '管理员登陆名称',
                                             `login_password` varchar(50) NOT NULL COMMENT '管理员登陆密码',
                                             `nick_name` varchar(50) NOT NULL COMMENT '管理员显示昵称',
                                             `locked` tinyint(4) DEFAULT '0' COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
                                             PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 新增管理员用户数据

INSERT INTO `tb_newbee_mall_admin_user` (`admin_user_id`, `login_user_name`, `login_password`, `nick_name`, `locked`)
VALUES
(1,'admin','e10adc3949ba59abbe56e057f20f883e','十三',0),
(2,'newbee-admin1','e10adc3949ba59abbe56e057f20f883e','新蜂01',0),
(3,'newbee-admin2','e10adc3949ba59abbe56e057f20f883e','新蜂02',0);

DROP TABLE IF EXISTS `tb_newbee_mall_user`;

# 创建商城用户表

CREATE TABLE `tb_newbee_mall_user` (
                                       `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
                                       `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
                                       `login_name` varchar(11) NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
                                       `password_md5` varchar(32) NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
                                       `introduce_sign` varchar(100) NOT NULL DEFAULT '' COMMENT '个性签名',
                                       `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '注销标识字段(0-正常 1-已注销)',
                                       `locked_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
                                       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                                       PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 新增商城用户数据

INSERT INTO `tb_newbee_mall_user` (`user_id`, `nick_name`, `login_name`, `password_md5`, `introduce_sign`, `is_deleted`, `locked_flag`, `create_time`)
VALUES
(1,'十三','13700002703','e10adc3949ba59abbe56e057f20f883e','我不怕千万人阻挡，只怕自己投降',0,0,'2022-05-22 08:44:57'),
(6,'陈尼克','13711113333','e10adc3949ba59abbe56e057f20f883e','测试用户陈尼克',0,0,'2022-05-22 08:44:57');