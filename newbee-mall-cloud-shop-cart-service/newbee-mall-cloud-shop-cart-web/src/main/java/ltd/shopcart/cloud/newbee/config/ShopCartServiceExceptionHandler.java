/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.shopcart.cloud.newbee.config;

import ltd.common.cloud.newbee.enums.ServiceResultEnum;
import ltd.common.cloud.newbee.dto.Result;
import ltd.common.cloud.newbee.exception.NewBeeMallException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ShopCartServiceExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ShopCartServiceExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e) {
        log.error("ShopCartServiceExceptionHandler:",e);
        Result result = new Result();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object bindException(MethodArgumentNotValidException e) {
        log.error("ShopCartServiceExceptionHandler:",e);
        Result result = new Result();
        result.setResultCode(510);
        BindingResult bindingResult = e.getBindingResult();
        result.setMessage(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        log.error("ShopCartServiceExceptionHandler:",e);
        Result result = new Result();
        result.setResultCode(500);
        //区分是否为自定义异常
        if (e instanceof NewBeeMallException) {
            result.setMessage(e.getMessage());
            if (e.getMessage().equals(ServiceResultEnum.NOT_LOGIN_ERROR.getResult()) || e.getMessage().equals(ServiceResultEnum.TOKEN_EXPIRE_ERROR.getResult())) {
                result.setResultCode(416);
            }
        } else {
            e.printStackTrace();
            result.setMessage("未知异常，请查看控制台日志并检查配置文件。");
        }
        return result;

    }
}
