/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.order.cloud.newbee.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单列表页面VO
 */
@Data
public class NewBeeMallOrderListVO implements Serializable {

    private Long orderId;

    @Schema(title ="订单号")
    private String orderNo;

    @Schema(title ="订单价格")
    private Integer totalPrice;

    @Schema(title ="订单支付方式")
    private Byte payType;

    @Schema(title ="订单状态码")
    private Byte orderStatus;

    @Schema(title ="订单状态")
    private String orderStatusString;

    @Schema(title ="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(title ="订单项列表")
    private List<NewBeeMallOrderItemVO> newBeeMallOrderItemVOS;
}
