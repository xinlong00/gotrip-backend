package com.ytzl.gotrip.utils.exception;

/**
 * @author xinlong
 */

public class GotripException extends Exception{

    //错误码
    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public GotripException (String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
