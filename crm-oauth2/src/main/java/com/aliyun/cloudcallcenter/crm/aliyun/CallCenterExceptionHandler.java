package com.aliyun.cloudcallcenter.crm.aliyun;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author edward
 * @date 2017/11/22
 */

@ControllerAdvice
public class CallCenterExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(CallCenterServiceException.class)
    public ExceptionResponse handleChatbotServiceException(CallCenterServiceException exception) {
        return new ExceptionResponse(exception.getRequestId(), exception.getErrorCode(), exception.getMessage());
    }
}
