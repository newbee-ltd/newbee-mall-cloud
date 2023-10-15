/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2023 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.goods.cloud.newbee.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品详情页VO
 */
@Data
public class NewBeeMallGoodsDetailVO implements Serializable {

    @Schema(title ="商品id")
    private Long goodsId;

    @Schema(title ="商品名称")
    private String goodsName;

    @Schema(title ="商品简介")
    private String goodsIntro;

    @Schema(title ="商品图片地址")
    private String goodsCoverImg;

    @Schema(title ="商品价格")
    private Integer sellingPrice;

    @Schema(title ="商品标签")
    private String tag;

    @Schema(title ="商品图片")
    private String[] goodsCarouselList;

    @Schema(title ="商品原价")
    private Integer originalPrice;

    @Schema(title ="商品详情字段")
    private String goodsDetailContent;
}
