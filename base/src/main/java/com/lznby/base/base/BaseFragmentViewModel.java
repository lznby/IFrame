package com.lznby.base.base;

import android.support.v4.app.Fragment;

import com.lznby.base.callback.GetFragmentCallback;


/**
 * @author Lznby
 *
 *@param  <F> 使用到当前ViewModel的Fragment
 *@param  <T> ViewModel中LiveData缺省持有的对象
 */
public class BaseFragmentViewModel<F extends Fragment, T> extends BaseViewModel<T> {
    /**
     * 获取Fragment的回调
     */
    public GetFragmentCallback<F> fragment;

    /**
     * 设置Fragment(在Activity里可以不用设置)
     *
     * @param fragment Fragment
     */
    public void setFragment(GetFragmentCallback<F> fragment) {
        this.fragment = fragment;
    }
}
