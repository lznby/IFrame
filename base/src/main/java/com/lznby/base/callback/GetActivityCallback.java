package com.lznby.base.callback;

/**
 * ViewModel中获取Activity实例
 *
 * @author Lznby
 */
public interface GetActivityCallback<T> {

    /**
     * 回调获取Activity
     *
     * @return Activity
     */
    T getActivity();
}
