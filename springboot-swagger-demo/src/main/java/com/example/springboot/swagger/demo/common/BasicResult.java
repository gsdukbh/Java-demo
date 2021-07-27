package com.example.springboot.swagger.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leejiawei
 */
@Data
@ApiModel(value = "通用接口返回", description = "Common Api ")
public class BasicResult<T> {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";

    @ApiModelProperty(value = "响应码", name = "code", required = true, example = "" + SUCCESS_CODE)
    private int code;

    @ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
    private String msg;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    private BasicResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private BasicResult() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    private BasicResult(int code, String msg) {
        this(code, msg, null);
    }

    private BasicResult(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> BasicResult<T> success() {
        return new BasicResult<>();
    }

    public static <T> BasicResult<T> successWithData(T data) {
        return new BasicResult<>(data);
    }

    public static <T> BasicResult<T> failWithCodeAndMsg(int code, String msg) {
        return new BasicResult<>(code, msg, null);
    }

    public static <T> BasicResult<T> buildWithParam(BasicResult param) {
        return new BasicResult<>(param.getCode(), param.getMsg(), null);
    }
}
