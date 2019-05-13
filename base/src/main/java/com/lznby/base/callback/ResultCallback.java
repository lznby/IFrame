package com.lznby.base.callback;

/**
 * @author Lznby
 */
public interface ResultCallback<T> {
    /**
     *
     * @param first
     * @param current
     * @param last
     */
    void onPage(int first, int current, int last);

    /**
     *
     * @param result
     */
    void onSuccess(T result);

    /**
     *
     * @param result
     */
    void onCacheSuccess(T result);

    /**
     *
     */
    void onFailure();

}
