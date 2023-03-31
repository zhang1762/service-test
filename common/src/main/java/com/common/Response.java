package com.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private int code;
    public String msg;
    public T data;

    public static Response buildFail(Status status) {
        Response response = new Response();
        response.setCode(status.getCode());
        response.setMsg(status.getMsg());
        return response;
    }

    public static Response buildFail(Integer code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public static Response buildSuccess(Object data) {
        Response response = new Response();
        response.setCode(Status.SUCCESS.getCode());
        response.setMsg(Status.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setCode(Status.SUCCESS.getCode());
        response.setMsg(Status.SUCCESS.getMsg());
        return response;
    }
}
