# 创建订单服务所需数据
CREATE DATABASE /*!32312 IF NOT EXISTS*/`newbee_mall_cloud_order_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `newbee_mall_cloud_order_db`;

DROP TABLE IF EXISTS `tb_newbee_mall_order`;

CREATE TABLE `tb_newbee_mall_order` (
                                        `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单表主键id',
                                        `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
                                        `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户主键id',
                                        `total_price` int(11) NOT NULL DEFAULT '1' COMMENT '订单总价',
                                        `pay_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
                                        `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0.无 1.支付宝支付 2.微信支付',
                                        `pay_time` timestamp DEFAULT NULL COMMENT '支付时间',
                                        `order_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
                                        `extra_info` varchar(100) NOT NULL DEFAULT '' COMMENT '订单body',
                                        `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
                                        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
                                        PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dump of table tb_newbee_mall_order_address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_newbee_mall_order_address`;

CREATE TABLE `tb_newbee_mall_order_address` (
                                                `order_id` bigint(20) NOT NULL,
                                                `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
                                                `user_phone` varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
                                                `province_name` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
                                                `city_name` varchar(32) NOT NULL DEFAULT '' COMMENT '城',
                                                `region_name` varchar(32) NOT NULL DEFAULT '' COMMENT '区',
                                                `detail_address` varchar(64) NOT NULL DEFAULT '' COMMENT '收件详细地址(街道/楼宇/单元)',
                                                PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单收货地址关联表';

# Dump of table tb_newbee_mall_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tb_newbee_mall_order_item`;

CREATE TABLE `tb_newbee_mall_order_item` (
                                             `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
                                             `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单主键id',
                                             `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联商品id',
                                             `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
                                             `goods_cover_img` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
                                             `selling_price` int(11) NOT NULL DEFAULT '1' COMMENT '下单时商品的价格(订单快照)',
                                             `goods_count` int(11) NOT NULL DEFAULT '1' COMMENT '数量(订单快照)',
                                             `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_newbee_mall_user_address`;

CREATE TABLE `tb_newbee_mall_user_address` (
                                               `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                               `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户主键id',
                                               `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
                                               `user_phone` varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
                                               `default_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为默认 0-非默认 1-是默认',
                                               `province_name` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
                                               `city_name` varchar(32) NOT NULL DEFAULT '' COMMENT '城',
                                               `region_name` varchar(32) NOT NULL DEFAULT '' COMMENT '区',
                                               `detail_address` varchar(64) NOT NULL DEFAULT '' COMMENT '收件详细地址(街道/楼宇/单元)',
                                               `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
                                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                               PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
    ) ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';