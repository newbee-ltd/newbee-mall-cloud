/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.goods.cloud.newbee.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import ltd.common.cloud.newbee.enums.ServiceResultEnum;
import ltd.common.cloud.newbee.dto.PageQueryUtil;
import ltd.common.cloud.newbee.dto.Result;
import ltd.common.cloud.newbee.dto.ResultGenerator;
import ltd.common.cloud.newbee.util.BeanUtil;
import ltd.goods.cloud.newbee.config.annotation.TokenToAdminUser;
import ltd.goods.cloud.newbee.controller.param.BatchIdParam;
import ltd.goods.cloud.newbee.controller.param.GoodsAddParam;
import ltd.goods.cloud.newbee.controller.param.GoodsEditParam;
import ltd.goods.cloud.newbee.entity.GoodsCategory;
import ltd.goods.cloud.newbee.entity.LoginAdminUser;
import ltd.goods.cloud.newbee.entity.NewBeeMallGoods;
import ltd.goods.cloud.newbee.entity.UpdateStockNumDTO;
import ltd.goods.cloud.newbee.service.NewBeeMallCategoryService;
import ltd.goods.cloud.newbee.service.NewBeeMallGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@RestController
@Tag(description = "v1", name = "后台管理系统商品模块接口")
@RequestMapping("/goods/admin")
public class NewBeeAdminGoodsInfoController {

    private static final Logger logger = LoggerFactory.getLogger(NewBeeAdminGoodsInfoController.class);

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;
    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Operation(summary = "商品列表", description = "可根据名称和上架状态筛选")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,
                       @RequestParam(required = false) @Parameter(description = "商品名称") String goodsName,
                       @RequestParam(required = false) @Parameter(description = "上架状态 0-上架 1-下架") Integer goodsSellStatus, @TokenToAdminUser @Parameter(hidden = true) LoginAdminUser adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 10) {
            return ResultGenerator.genFailResult("分页参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        if (StringUtils.hasText(goodsName)) {
            params.put("goodsName", goodsName);
        }
        if (goodsSellStatus != null) {
            params.put("goodsSellStatus", goodsSellStatus);
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallGoodsService.getNewBeeMallGoodsPage(pageUtil));
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Operation(summary = "新增商品信息", description = "新增商品信息")
    public Result save(@RequestBody @Valid GoodsAddParam goodsAddParam, @TokenToAdminUser @Parameter(hidden = true) LoginAdminUser adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        NewBeeMallGoods newBeeMallGoods = new NewBeeMallGoods();
        BeanUtil.copyProperties(goodsAddParam, newBeeMallGoods);
        String result = newBeeMallGoodsService.saveNewBeeMallGoods(newBeeMallGoods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Operation(summary = "修改商品信息", description = "修改商品信息")
    public Result update(@RequestBody @Valid GoodsEditParam goodsEditParam, @TokenToAdminUser @Parameter(hidden = true) LoginAdminUser adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        NewBeeMallGoods newBeeMallGoods = new NewBeeMallGoods();
        BeanUtil.copyProperties(goodsEditParam, newBeeMallGoods);
        String result = newBeeMallGoodsService.updateNewBeeMallGoods(newBeeMallGoods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 详情
     */
    @GetMapping("/detail/{id}")
    @Operation(summary = "获取单条商品信息", description = "根据id查询")
    public Result info(@PathVariable("id") Long id, @TokenToAdminUser @Parameter(hidden = true) LoginAdminUser adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        Map goodsInfo = new HashMap(8);
        NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(id);
        if (goods == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        goodsInfo.put("goods", goods);
        GoodsCategory thirdCategory;
        GoodsCategory secondCategory;
        GoodsCategory firstCategory;
        thirdCategory = newBeeMallCategoryService.getGoodsCategoryById(goods.getGoodsCategoryId());
        if (thirdCategory != null) {
            goodsInfo.put("thirdCategory", thirdCategory);
            secondCategory = newBeeMallCategoryService.getGoodsCategoryById(thirdCategory.getParentId());
            if (secondCategory != null) {
                goodsInfo.put("secondCategory", secondCategory);
                firstCategory = newBeeMallCategoryService.getGoodsCategoryById(secondCategory.getParentId());
                if (firstCategory != null) {
                    goodsInfo.put("firstCategory", firstCategory);
                }
            }
        }
        return ResultGenerator.genSuccessResult(goodsInfo);
    }

    /**
     * 批量修改销售状态
     */
    @RequestMapping(value = "/updateStatus/{sellStatus}", method = RequestMethod.PUT)
    @Operation(summary = "批量修改销售状态", description = "批量修改销售状态")
    public Result delete(@RequestBody BatchIdParam batchIdParam, @PathVariable("sellStatus") int sellStatus, @TokenToAdminUser @Parameter(hidden = true) LoginAdminUser adminUser) {
        logger.info("adminUser:{}", adminUser.toString());
        if (batchIdParam == null || batchIdParam.getIds().length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (sellStatus != 0 && sellStatus != 1) {
            return ResultGenerator.genFailResult("状态异常！");
        }
        if (newBeeMallGoodsService.batchUpdateSellStatus(batchIdParam.getIds(), sellStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    /**
     * 详情
     */
    @GetMapping("/goodsDetail")
    @Operation(summary = "获取单条商品信息", description = "根据id查询")
    public Result goodsDetail(@RequestParam("goodsId") Long goodsId) {
        NewBeeMallGoods goods = newBeeMallGoodsService.getNewBeeMallGoodsById(goodsId);
        return ResultGenerator.genSuccessResult(goods);
    }

    /**
     * 根据ids查询商品列表
     */
    @GetMapping("/listByGoodsIds")
    @Operation(summary = "根据ids查询商品列表", description = "根据ids查询")
    public Result getNewBeeMallGoodsByIds(@RequestParam("goodsIds") List<Long> goodsIds) {
        List<NewBeeMallGoods> newBeeMallGoods = newBeeMallGoodsService.getNewBeeMallGoodsByIds(goodsIds);
        return ResultGenerator.genSuccessResult(newBeeMallGoods);
    }

    /**
     * 修改库存
     */
    @PutMapping("/updateStock")
    @Operation(summary = "修改库存", description = "")
    public Result updateStock(@RequestBody UpdateStockNumDTO updateStockNumDTO) {
        return ResultGenerator.genSuccessResult(newBeeMallGoodsService.updateStockNum(updateStockNumDTO.getStockNumDTOS()));
    }

}