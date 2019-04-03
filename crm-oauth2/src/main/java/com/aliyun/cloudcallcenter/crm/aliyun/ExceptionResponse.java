package com.aliyun.cloudcallcenter.crm.aliyun;

;

/**
 * @author edward
 * @date 2017/11/22
 */
public class ExceptionResponse {
    private String requestId;
    private String errorCode;
    private String message;

    public ExceptionResponse(String requestId, String errorCode, String message) {
        this.requestId = requestId;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
