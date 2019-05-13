package com.lznby.base.network.configure;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.lznby.base.network.ApiUtils;
import com.lznby.base.utils.TimberUtil;

import java.util.Stack;

/**
 * 重写Application,网络请求Api注册
 *
 * @author Lznby
 */
public class App extends Application implements Application.ActivityLifecycleCallbacks{
    /**
     * 网络请求Api
     */
    public static Api api;
    /**
     * 全局Context
     */
    private static Context context;

    /**
     * Activity栈
     */
    private static Stack<Activity> stack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        //Android 7.0 关于 FileUriExposedException 错误
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }

        // 设置log自动在apk为debug版本时打开，在release版本时关闭
        TimberUtil.setLogAuto();

        //全局调用网络请求
        api = ApiUtils.INSTANCE.getApi(this);
    }

    /**
     * 全局获取Context
     *
     * @return  全局Context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取Activity栈
     *
     * @return Activity栈
     */
    public Stack<Activity> getStack() {
        return stack;
    }

    /**
     * 获取当前Activity
     *
     * @return Activity
     */
    public static Activity getCurrentActivity() {
        return stack.lastElement();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        stack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        stack.remove(activity);
    }
}
