package com.lznby.base.base;

import android.app.Activity;

import com.lznby.base.callback.GetActivityCallback;

/**
 * BaseActivityViewModel
 *
 * @author Lznby
 *
 * @param  <A> 使用到当前ViewModel的Activity
 * @param  <T> ViewModel中LiveData缺省持有的对象
 */
public class BaseActivityViewModel<A extends Activity, T> extends BaseViewModel<T>{
    /**
     * 获取Activity的回调
     */
    public GetActivityCallback<A> activity;

    /**
     * 设置Activity(在Fragment中可以不用设置)
     *
     * @param activity Activity
     */
    public void setActivity(GetActivityCallback<A> activity) {
        this.activity = activity;
    }

}
