package com.lznby.base.network.exception;

/**
 * 解析及服务器异常
 *
 * @author Lznby
 */
public class ApiException extends RuntimeException {
    private int code;
    private String json;

    public ApiException(){}

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ApiException(String message, int code, String json) {
        super(message);
        this.code = code;
        this.json = json;
    }

    public int getCode() {
        return code;
    }

    public String getJson() {
        return json;
    }

    public ApiException(String message){super(message);}

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}