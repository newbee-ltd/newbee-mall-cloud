# 创建购物车服务所需数据

CREATE DATABASE /*!32312 IF NOT EXISTS*/`newbee_mall_cloud_cart_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `newbee_mall_cloud_cart_db`;

DROP TABLE IF EXISTS `tb_newbee_mall_shopping_cart_item`;

CREATE TABLE `tb_newbee_mall_shopping_cart_item` (
                                                     `cart_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物项主键id',
                                                     `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
                                                     `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联商品id',
                                                     `goods_count` int(11) NOT NULL DEFAULT '1' COMMENT '数量(最大为5)',
                                                     `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
                                                     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
                                                     PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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