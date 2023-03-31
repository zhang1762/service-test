package com.common;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS(200,"成功"),
    EXCEPTION(10000,"服务内部错误"),
    PARAM_ERROR(20000,"参数错误"),
    PARAM_NOT_EXIST(20001,"请求参数不存在数据");
    private int code;
    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
