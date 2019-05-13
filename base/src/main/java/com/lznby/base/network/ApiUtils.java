package com.lznby.base.network;


import android.content.Context;

import com.lznby.base.BuildConfig;
import com.lznby.base.network.configure.Api;
import com.lznby.base.network.interceptor.UserInterceptor;
import com.lznby.base.utils.CacheUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 枚举实现的单例Api工具包
 *
 * @author Lznby
 */
public enum ApiUtils {

    /**
     * 枚举实现的单例
     */
    INSTANCE;

    public Api getApi(Context context) {
        UserInterceptor userInterceptor = new UserInterceptor();
        OkHttpClient client = provideOkHttpClient(context).newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Api.HOST)
                //使用Gson作为数据转换器
                .addConverterFactory(GsonConverterFactory.create())
                //使用RxJava2作为回调适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                //添加网络访问工具OKHttpClient
                .callFactory(client)
                .build()
                .create(Api.class);
    }

    private OkHttpClient provideOkHttpClient(Context context) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                //超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                // HTTP过滤器 方便日志打印查看
                .addInterceptor(new HttpLoggingInterceptor())
                // 添加GET请求缓存,缓存大小为10Mib
                .cache(CacheUtils.getCache(context, 20,false))
                .build().newBuilder();
        // 调试模式使用拦截器(打印请求信息)
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //设置打印级别
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }

}
