/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.goods.cloud.newbee.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页分类数据VO(第三级)
 */
@Data
public class ThirdLevelCategoryVO implements Serializable {

    @Schema(title ="当前三级分类id")
    private Long categoryId;

    @Schema(title ="当前分类级别")
    private Byte categoryLevel;

    @Schema(title ="当前三级分类名称")
    private String categoryName;
}
