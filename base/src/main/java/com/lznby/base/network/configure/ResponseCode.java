package com.lznby.base.network.configure;

/**
 * 服务器返回结果Code值(各种状态对应的值，由服务器端确定需要自行配置)
 *
 * @author Lznby
 */
public interface ResponseCode {
    /**
     * 请求成功
     */
    int SUCCESS = 1;
    /**
     * token过期
     */
    int TOKEN_TIMEOUT = 0;
    /**
     * 请求失败
     */
    int FAIL = -1;
    /**
     * 其他错误
     */
}
