package com.lznby.base.network.configure;

/**
 * 服务器响应Json对应,必须是符合Restful的.
 *
 * @author Lznby
 */
public class InfoEntity<T> extends Object {
    /**
     * 错误消息
     */
    private String msg;
    /**
     * 错误代码
     */
    private int code;
    /**
     * 数据
     */
    private T data;

    public InfoEntity() {
    }

    public InfoEntity(String message, int code, T data) {
        this.msg = message;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
