/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2023 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.recommend.cloud.newbee.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IndexInfoVO implements Serializable {

    @Schema(title ="轮播图(列表)")
    private List<NewBeeMallIndexCarouselVO> carousels;

    @Schema(title ="首页热销商品(列表)")
    private List<NewBeeMallIndexConfigGoodsVO> hotGoodses;

    @Schema(title ="首页新品推荐(列表)")
    private List<NewBeeMallIndexConfigGoodsVO> newGoodses;

    @Schema(title ="首页推荐商品(列表)")
    private List<NewBeeMallIndexConfigGoodsVO> recommendGoodses;
}
