package com.lznby.base.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Objects;

import okhttp3.Cache;

/**
 * 缓存工具类:如果手机上有SD卡优先将缓存存放到SD卡上,否者使用手机内部存储
 * 参考文献：https://blog.csdn.net/u011494050/article/details/39671159(有点问题)
 *
 * @author Lznby
 */
public class CacheUtils {

    /**
     * 获取当前应用缓存文件路径
     *
     * @param context 上下文
     * @param prioritySdCache 是否优先存储在SD卡中
     * @return 应用缓存路径
     */
    public static String getDiskCacheDir(Context context, boolean prioritySdCache) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            if (prioritySdCache) {
                cachePath = Objects.requireNonNull(context.getExternalCacheDir()).getPath();
            } else {
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 传入缓存大小MIB大小,自动转化为Byte,并返回。
     *
     * @param cacheMib 要转换的Mib大小
     * @return 转换后的Byte大小
     */
    public static int getCacheSize(int cacheMib) {
        return cacheMib * 1024 * 1024;
    }

    /**
     * 获取Cache
     *
     * @param context         上下文
     * @param cacheMib        缓存大小(单位：Mib)
     * @param prioritySdCache 是否优先存储在SD卡中
     * @return Cache对象
     */
    public static Cache getCache(Context context, int cacheMib, boolean prioritySdCache) {
        return new Cache(new File(getDiskCacheDir(context, prioritySdCache)), getCacheSize(cacheMib));
    }
}
