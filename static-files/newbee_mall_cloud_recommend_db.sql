# 创建推荐服务所需数据
CREATE DATABASE /*!32312 IF NOT EXISTS*/`newbee_mall_cloud_recommend_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `newbee_mall_cloud_recommend_db`;

# 创建首页推荐表

DROP TABLE IF EXISTS `tb_newbee_mall_index_config`;

CREATE TABLE `tb_newbee_mall_index_config` (
                                               `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键id',
                                               `config_name` varchar(50) NOT NULL DEFAULT '' COMMENT '显示字符(配置搜索时不可为空，其他可为空)',
                                               `config_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
                                               `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品id 默认为0',
                                               `redirect_url` varchar(100) NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
                                               `config_rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
                                               `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
                                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                               `create_user` int(11) NOT NULL DEFAULT '0' COMMENT '创建者id',
                                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
                                               `update_user` int(11) DEFAULT '0' COMMENT '修改者id',
                                               PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 新增首页推荐表数据

INSERT INTO tb_newbee_mall_index_config
(config_name, config_type, goods_id, redirect_url, config_rank, is_deleted, create_time, create_user, update_time, update_user)
VALUES
('热销商品 iPhone 12', 3, 10906, '##', 201, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('热销商品 华为Mate40 Pro', 3, 10908, '##', 300, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('新品上线 MackBook2021', 4, 10920, '##', 180, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('新品上线 华为 P50 Pro', 4, 10921, '##', 160, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('新品上线 Apple Watch', 4, 10919, '##', 101, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('纪梵希高定香榭天鹅绒唇膏', 5, 10233, '##', 80, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('P50 白色', 5, 10922, '##', 102, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('free buds pro', 5, 10930, '##', 102, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('iPhone 13', 5, 10916, '##', 101, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('华为Mate40 Pro', 5, 10907, '##', 80, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('MacBook Pro 2021', 5, 10920, '##', 100, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('WATCH 3 Pro', 5, 10928, '##', 99, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('塑料浴室座椅', 5, 10154, '##', 80, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('华为 soundx', 5, 10929, '##', 100, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('matepad pro', 5, 10906, '##', 0, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('热销商品 P40', 3, 10902, '##', 200, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('新品上线 华为 P50 Pocket', 4, 10925, '##', 200, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('新品上线 华为Mate X Pro', 4, 10926, '##', 200, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('华为 Mate 30 Pro', 5, 10927, '##', 101, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('新品上线 iPhone13', 4, 10915, '##', 190, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0),
('Air Pods 第三代', 3, 10918, '##', 301, 0, '2021-03-08 18:55:49', 0, '2021-03-08 18:55:49', 0);

DROP TABLE IF EXISTS `tb_newbee_mall_carousel`;

# 创建轮播图表

CREATE TABLE `tb_newbee_mall_carousel` (
                                           `carousel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id',
                                           `carousel_url` varchar(100) NOT NULL DEFAULT '' COMMENT '轮播图',
                                           `redirect_url` varchar(100) NOT NULL DEFAULT '''##''' COMMENT '点击后的跳转地址(默认不跳转)',
                                           `carousel_rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
                                           `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
                                           `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `create_user` int(11) NOT NULL DEFAULT '0' COMMENT '创建者id',
                                           `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                           `update_user` int(11) NOT NULL DEFAULT '0' COMMENT '修改者id',
                                           PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

# 新增轮播图数据

INSERT INTO `tb_newbee_mall_carousel` (`carousel_id`, `carousel_url`, `redirect_url`, `carousel_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`)
VALUES
(1,'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.jpg','##',200,1,'2021-08-23 17:50:45',0,'2021-11-10 00:23:01',0),
(2,'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner1.png','https://juejin.cn/book/7085254558678515742',13,0,'2021-11-29 00:00:00',0,'2021-11-29 00:00:00',0),
(3,'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner3.jpg','##',0,1,'2021-09-18 18:26:38',0,'2021-11-10 00:23:01',0),
(5,'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png','https://juejin.cn/book/7085254558678515742',0,0,'2021-11-29 00:00:00',0,'2021-11-29 00:00:00',0),
(6,'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner1.png','##',101,1,'2021-09-19 23:37:40',0,'2021-11-07 00:15:52',0),
(7,'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png','##',99,1,'2021-09-19 23:37:58',0,'2021-10-22 00:15:01',0);