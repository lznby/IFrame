package com.lznby.base.utils;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxPermission 工具类.
 *
 * @author Lznby
 */
public class RxPermissionUtils {

    /**
     * 一次请求所有权限(在Activity中请求)
     *
     * @param activity          请求权限的Activity
     * @param onNext            请求结果处理
     * @param permissions       权限列表集合
     * @return                  Disposable,便于RxJava绑定生命周期进行线程回收
     */
    public static Disposable checkPermission(AppCompatActivity activity, Consumer<Boolean> onNext, String... permissions) {
        return new RxPermissions(activity)
                .request(permissions)
                .subscribe(onNext, Throwable::printStackTrace);
    }

    /**
     * 一个一个的请求权限(在Activity中请求)
     *
     * @param activity          请求权限的Activity
     * @param onNext            请求结果处理
     * @param permissions       权限列表结合
     * @return                  Disposable,便于RxJava绑定生命周期进行线程回收
     */
    public static Disposable checkPermissionOneByOne(AppCompatActivity activity, Consumer<Permission> onNext, String... permissions) {
        return new RxPermissions(activity)
                .requestEachCombined(permissions)
                .subscribe(onNext, Throwable::printStackTrace);
    }

    /**
     * 一次请求所有权限(在Fragment中请求)
     *
     * @param fragment          请求权限的Fragment
     * @param onNext            请求结果处理
     * @param permissions       权限列表集合
     * @return                  Disposable,便于RxJava绑定生命周期进行线程回收
     */
    public static Disposable checkPermission(Fragment fragment, Consumer<Boolean> onNext, String... permissions) {
        return new RxPermissions(fragment)
                .request(permissions)
                .subscribe(onNext, Throwable::printStackTrace);
    }

    /**
     * 一个一个的请求权限(在Fragment中请求)
     *
     * @param fragment          请求权限的Fragment
     * @param onNext            请求结果处理
     * @param permission        权限列表集合
     * @return                  Disposable,便于RxJava绑定生命周期进行线程回收
     */
    public static Disposable checkPermissionOneByOne(Fragment fragment, Consumer<Permission> onNext, String... permission) {
        return new RxPermissions(fragment)
                .requestEachCombined(permission)
                .subscribe(onNext, Throwable::printStackTrace);
    }
}
