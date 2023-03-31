package com.user.controller;

import com.common.Response;
import com.common.Status;
import com.common.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获
 */
@Slf4j
@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Response handleException(Exception e) {
        log.error("GlobalException:", e);
        return Response.buildFail(Status.EXCEPTION);
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public Response handleException(BusinessException e) {
        log.error("GlobalException.businessException:", e);
        return Response.buildFail(e.getCode(), e.getMessage());
    }

}
