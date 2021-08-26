package com.hzsmk.common.exception;

/**
 * 业务异常
 *
 * @author jiangjiaheng
 * @date 2019/11/23 18:12:02
 */
public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
