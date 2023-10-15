/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.order.cloud.newbee.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import ltd.common.cloud.newbee.enums.ServiceResultEnum;
import ltd.common.cloud.newbee.dto.PageQueryUtil;
import ltd.common.cloud.newbee.dto.PageResult;
import ltd.common.cloud.newbee.dto.Result;
import ltd.common.cloud.newbee.dto.ResultGenerator;
import ltd.common.cloud.newbee.exception.NewBeeMallException;
import ltd.common.cloud.newbee.pojo.MallUserToken;
import ltd.order.cloud.newbee.config.annotation.TokenToMallUser;
import ltd.order.cloud.newbee.controller.param.SaveOrderParam;
import ltd.order.cloud.newbee.controller.vo.NewBeeMallOrderDetailVO;
import ltd.order.cloud.newbee.controller.vo.NewBeeMallOrderListVO;
import ltd.order.cloud.newbee.entity.MallUserAddress;
import ltd.order.cloud.newbee.service.NewBeeMallOrderService;
import ltd.order.cloud.newbee.service.NewBeeMallUserAddressService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(description = "v1", name = "新蜂商城订单操作相关接口")
@RequestMapping("/orders/mall")
public class NewBeeMallOrderController {

    @Resource
    private NewBeeMallOrderService newBeeMallOrderService;
    @Resource
    private NewBeeMallUserAddressService newBeeMallUserAddressService;

    @PostMapping("/saveOrder")
    @Operation(summary = "生成订单接口", description = "传参为地址id和待结算的购物项id数组")
    public Result<String> saveOrder(@Parameter(description = "订单参数") @RequestBody SaveOrderParam saveOrderParam, @TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        if (saveOrderParam == null || saveOrderParam.getCartItemIds() == null || saveOrderParam.getAddressId() == null) {
            NewBeeMallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        if (saveOrderParam.getCartItemIds().length < 1) {
            NewBeeMallException.fail(ServiceResultEnum.PARAM_ERROR.getResult());
        }
        MallUserAddress address = newBeeMallUserAddressService.getMallUserAddressById(saveOrderParam.getAddressId());
        if (!loginMallUserToken.getUserId().equals(address.getUserId())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.REQUEST_FORBIDEN_ERROR.getResult());
        }
        //保存订单并返回订单号
        String saveOrderResult = newBeeMallOrderService.saveOrder(loginMallUserToken.getUserId(), address, Arrays.asList(saveOrderParam.getCartItemIds()));
        Result result = ResultGenerator.genSuccessResult();
        result.setData(saveOrderResult);
        return result;
    }

    @GetMapping("/order/{orderNo}")
    @Operation(summary = "订单详情接口", description = "传参为订单号")
    public Result<NewBeeMallOrderDetailVO> orderDetailPage(@Parameter(description = "订单号") @PathVariable("orderNo") String orderNo, @TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        return ResultGenerator.genSuccessResult(newBeeMallOrderService.getOrderDetailByOrderNo(orderNo, loginMallUserToken.getUserId()));
    }

    @GetMapping("/order")
    @Operation(summary = "订单列表接口", description = "传参为页码")
    public Result<PageResult<List<NewBeeMallOrderListVO>>> orderList(@Parameter(description = "页码") @RequestParam(required = false) Integer pageNumber,
                                                                     @Parameter(description = "订单状态:0.待支付 1.待确认 2.待发货 3:已发货 4.交易成功") @RequestParam(required = false) Integer status,
                                                                     @TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        params.put("userId", loginMallUserToken.getUserId());
        params.put("orderStatus", status);
        params.put("page", pageNumber);
        params.put("limit", 5);
        //封装分页请求参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallOrderService.getMyOrders(pageUtil));
    }

    @PutMapping("/order/{orderNo}/cancel")
    @Operation(summary = "订单取消接口", description = "传参为订单号")
    public Result cancelOrder(@Parameter(description = "订单号") @PathVariable("orderNo") String orderNo, @TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        String cancelOrderResult = newBeeMallOrderService.cancelOrder(orderNo, loginMallUserToken.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(cancelOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(cancelOrderResult);
        }
    }

    @PutMapping("/order/{orderNo}/finish")
    @Operation(summary = "确认收货接口", description = "传参为订单号")
    public Result finishOrder(@Parameter(description = "订单号") @PathVariable("orderNo") String orderNo, @TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        String finishOrderResult = newBeeMallOrderService.finishOrder(orderNo, loginMallUserToken.getUserId());
        if (ServiceResultEnum.SUCCESS.getResult().equals(finishOrderResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(finishOrderResult);
        }
    }

    @GetMapping("/paySuccess")
    @Operation(summary = "模拟支付成功回调的接口", description = "传参为订单号和支付方式")
    public Result paySuccess(@Parameter(description = "订单号") @RequestParam("orderNo") String orderNo, @Parameter(description = "支付方式") @RequestParam("payType") int payType) {
        String payResult = newBeeMallOrderService.paySuccess(orderNo, payType);
        if (ServiceResultEnum.SUCCESS.getResult().equals(payResult)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(payResult);
        }
    }

}
