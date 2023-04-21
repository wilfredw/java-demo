package com.wei.java.design.pattern.template.service.v3;


public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 8641213350581044979L;
    protected String errorCode = "SYSTEM_ERROR";
    protected String errorMsg = "";

    public BaseException() {
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

    public BaseException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.errorMsg = cause.getMessage();
    }

    public BaseException(IEnum enums) {
        super(enums.getDesc());
        this.errorCode = enums.getKey();
        this.errorMsg = enums.getDesc();
    }


    public BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public String getCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public String toString() {
        return "BaseException [errorCode=" + this.errorCode + ", errorMsg=" + this.errorMsg + "]";
    }
}
