package com.hzsmk.common.exception.handle;

import com.hzsmk.common.base.ApiConsts;
import com.hzsmk.common.base.RestResponse;
import com.hzsmk.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;

/**
 * 全局异常处理
 *
 * @author jiaangjiaheng
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常
     */
    @ExceptionHandler(Throwable.class)
    public RestResponse handleException(Throwable e) throws Throwable {
        // 如果异常为ServletException, 直接抛出, 不做处理. 只处理业务异常返回系统错误
        if (e instanceof ServletException) {
            throw e;
        } else if (e instanceof HttpMessageNotReadableException) {
            return RestResponse.errorResult(ApiConsts.PARAM_ERROR, "参数错误!");
        } else {
            // 打印堆栈信息
            log.error("system error:{}", e);
            return RestResponse.errorResult(ApiConsts.UNKNOW_ERROR, ApiConsts.UNKNOW_ERROR_DESC);
        }
    }


    /**
     * 处理接口参数异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public RestResponse handleMethodArgumentNotValidException(Exception e) {
        try {
            String errorMsg = null;
            if (e instanceof MethodArgumentNotValidException) {
                errorMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            } else {
                errorMsg = ((BindException) e).getBindingResult().getFieldError().getDefaultMessage();
            }

            log.error("参数异常:{}", errorMsg);
            return RestResponse.errorResult(ApiConsts.PARAM_ERROR, errorMsg);
        } catch (Exception ex) {
            log.error("system error:{}", e);
            return RestResponse.errorResult(ApiConsts.PARAM_ERROR, ApiConsts.PARAM_ERROR_DESC);
        }
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResponse handleBuinessException(BusinessException e) {
        if (StringUtils.isNotBlank(e.getCode())) {
            log.info("业务异常,code:{},message:{}", e.getCode(), e.getMessage());
            return RestResponse.errorResult(e.getCode(), e.getMessage());
        }
        if (StringUtils.isNotBlank(e.getMessage())) {
            log.info("业务异常:{}", e.getMessage());
            return RestResponse.errorBusinessResult(e.getMessage());
        } else {
            log.error("system error:{}", e);
            return RestResponse.errorBusinessResult(ApiConsts.BUSINESS_ERROR_DESC);
        }
    }
}
