package com.lznby.base.network.interceptor;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lznby.base.BuildConfig;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 应用拦截器(设置签名、统一添加请求头等需要结合实际重写)
 * 说明：OKHttp拦截器分为两种,一种是应用拦截器、一种是网络拦截器。具体的话去看OkHttp官方的wiki.
 *
 * @author Lznby
 */
public class UserInterceptor implements Interceptor {

    public UserInterceptor() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        // 判断网络状态
        boolean isConnection = NetworkUtils.isConnected();
        //看需求进行加请求头的操作 第一次进来的时候首先移除请求头
        if (isConnection) {
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    // 添加需要的版本号（以版本号为例,其他结合实际添加）
                    .addHeader("version", BuildConfig.VERSION_NAME)
                    .build();
        } else {
            // 无网络提示
            ToastUtils.showShort("无网络！请稍后重试");
            // 强制使用本地缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        //判断Http状态码，设置消息（Http状态码可以参考 HttpURLConnection 类）
        Response response = chain.proceed(request);
        if (response.code() >= 400 && response.code() < 500) {
            return response.newBuilder().code(response.code()).message("客户端请求异常,状态码：" + response.code()).build();
        }
        if (response.code() >= 500 && response.code() < 600) {
            return response.newBuilder().code(response.code()).message("服务器状态异常,状态码：" + response.code()).build();
        }
        return response;
    }
}