package com.hzsmk.common.base;


import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description 返回实体参数
 * @Date 2019/11/21 18:00
 * @Author jiangjiaheng
 */
@Data
public class RestResponse implements Serializable {

    private String code;
    private String msg;
    private Object response;

    public RestResponse() {
    }

    private static final RestResponse SUCCESS_RESULT = successResult(ApiConsts.SUCCESS_DESC);

    /**
     * 默认成功返回
     *
     * @return
     */
    public static RestResponse success() {
        return SUCCESS_RESULT;
    }

    /**
     * 含有返回数据的成功返回
     *
     * @param data
     * @return
     */
    public static RestResponse successResult(Object data) {
        RestResponse result = new RestResponse();
        result.setCode(ApiConsts.SUCCESS_CODE);
        result.setMsg(ApiConsts.SUCCESS_DESC);
        if (Objects.nonNull(data)) {
            result.setResponse(data);
        } else {
            result.setResponse("");
        }
        return result;
    }

    /**
     * 含有错误码及自定义错误信息的错误返回
     *
     * @param respCode
     * @return
     */
    public static RestResponse errorResult(String respCode, String respDesc) {
        RestResponse result = new RestResponse();
        result.setCode(respCode);
        result.setResponse(null);
        result.setMsg(respDesc);
        return result;
    }

    /**
     * 业务异常返回
     *
     * @param errorDesc
     * @return
     */
    public static RestResponse errorBusinessResult(String errorDesc) {
        return errorResult(ApiConsts.BUSINESS_ERROR, errorDesc);
    }


}
