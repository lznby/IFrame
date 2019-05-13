package com.lznby.base.widgets.loadingviwe;

/**
 * @author Lznby
 * <p>
 * 状态
 */
public interface LoadingState {
    /**
     * 错误状态
     */
    int ERROR = 0;
    /**
     * 加载中
     */
    int LOADING = 1;
    /**
     * 无网络
     */
    int NO_NETWORK = 2;
    /**
     * 为空
     */
    int EMPTY = 3;
    /**
     * 内容
     */
    int CONTENT = 4;

}
