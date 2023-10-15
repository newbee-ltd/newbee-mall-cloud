/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2023 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.user.cloud.newbee.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import ltd.common.cloud.newbee.enums.ServiceResultEnum;
import ltd.common.cloud.newbee.dto.Result;
import ltd.common.cloud.newbee.dto.ResultGenerator;
import ltd.common.cloud.newbee.pojo.MallUserToken;
import ltd.common.cloud.newbee.util.BeanUtil;
import ltd.common.cloud.newbee.util.NumberUtil;
import ltd.user.cloud.newbee.config.annotation.TokenToMallUser;
import ltd.user.cloud.newbee.controller.param.MallUserLoginParam;
import ltd.user.cloud.newbee.controller.param.MallUserRegisterParam;
import ltd.user.cloud.newbee.controller.param.MallUserUpdateParam;
import ltd.user.cloud.newbee.controller.vo.NewBeeMallUserVO;
import ltd.user.cloud.newbee.entity.MallUser;
import ltd.user.cloud.newbee.service.NewBeeMallUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@RestController
@Tag(description = "v1", name = "新蜂商城用户操作相关接口")
@RequestMapping("/users/mall")
public class NewBeeMallCloudPersonalController {

    @Resource
    private NewBeeMallUserService newBeeMallUserService;

    private static final Logger logger = LoggerFactory.getLogger(NewBeeMallCloudPersonalController.class);

    @PostMapping("/login")
    @Operation(summary = "登录接口", description = "返回token")
    public Result<String> login(@RequestBody @Valid MallUserLoginParam mallUserLoginParam) {
        if (!NumberUtil.isPhone(mallUserLoginParam.getLoginName())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String loginResult = newBeeMallUserService.login(mallUserLoginParam.getLoginName(), mallUserLoginParam.getPasswordMd5());

        logger.info("login api,loginName={},loginResult={}", mallUserLoginParam.getLoginName(), loginResult);

        //登录成功
        if (StringUtils.hasText(loginResult) && loginResult.length() == 32) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }


    @PostMapping("/logout")
    @Operation(summary = "登出接口", description = "清除token")
    public Result<String> logout(@TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        Boolean logoutResult = newBeeMallUserService.logout(loginMallUserToken.getToken());

        logger.info("logout api,loginMallUser={}", loginMallUserToken.getUserId());

        //登出成功
        if (logoutResult) {
            return ResultGenerator.genSuccessResult();
        }
        //登出失败
        return ResultGenerator.genFailResult("logout error");
    }


    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "")
    public Result register(@RequestBody @Valid MallUserRegisterParam mallUserRegisterParam) {
        if (!NumberUtil.isPhone(mallUserRegisterParam.getLoginName())) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
        }
        String registerResult = newBeeMallUserService.register(mallUserRegisterParam.getLoginName(), mallUserRegisterParam.getPassword());

        logger.info("register api,loginName={},loginResult={}", mallUserRegisterParam.getLoginName(), registerResult);

        //注册成功
        if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
            return ResultGenerator.genSuccessResult();
        }
        //注册失败
        return ResultGenerator.genFailResult(registerResult);
    }

    @PutMapping("/update")
    @Operation(summary = "修改用户信息", description = "")
    public Result updateInfo(@RequestBody @Parameter(description = "用户信息") MallUserUpdateParam mallUserUpdateParam, @TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        Boolean flag = newBeeMallUserService.updateUserInfo(mallUserUpdateParam, loginMallUserToken.getUserId());
        if (flag) {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        } else {
            //返回失败
            Result result = ResultGenerator.genFailResult("修改失败");
            return result;
        }
    }

    @GetMapping("/detail")
    @Operation(summary = "获取用户信息", description = "")
    public Result<NewBeeMallUserVO> getUserDetail(@TokenToMallUser @Parameter(hidden = true)  MallUserToken  loginMallUserToken) {
        NewBeeMallUserVO mallUserVO = new NewBeeMallUserVO();
        MallUser userDetailByToken = newBeeMallUserService.getUserDetailByToken(loginMallUserToken.getToken());
        BeanUtil.copyProperties(userDetailByToken, mallUserVO);
        return ResultGenerator.genSuccessResult(mallUserVO);
    }

    @RequestMapping(value = "/getDetailByToken", method = RequestMethod.GET)
    public Result getMallUserByToken(@RequestParam("token") String token) {
        MallUser userDetailByToken = newBeeMallUserService.getUserDetailByToken(token);
        if (userDetailByToken != null) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(userDetailByToken);
            return result;
        }
        return ResultGenerator.genFailResult("无此用户数据");
    }
}
