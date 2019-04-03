package com.aliyun.cloudcallcenter.crm.aliyun;

/**
 * @author edward
 * @date 2017/11/22
 */
public class CallCenterServiceException extends RuntimeException {

    private String requestId;
    private String errorCode;

    public CallCenterServiceException(String requestId, String errorCode) {
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public CallCenterServiceException(String requestId, String errorCode, String message) {
        super(message);
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public CallCenterServiceException(String requestId, String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public CallCenterServiceException(String requestId, String errorCode, Throwable cause) {
        super(cause);
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public CallCenterServiceException(String requestId, String errorCode, String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
