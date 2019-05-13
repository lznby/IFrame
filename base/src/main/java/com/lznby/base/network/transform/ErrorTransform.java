package com.lznby.base.network.transform;


import com.lznby.base.network.configure.InfoEntity;
import com.lznby.base.network.configure.ResponseCode;
import com.lznby.base.network.exception.ApiException;

import org.json.JSONException;

import java.util.ServiceConfigurationError;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Retrofit 错误转换器
 *
 * @author Lznby
 */
public class ErrorTransform<T> implements ObservableTransformer<T, T> {
    private int code = 0;
    private String errorMessage = "";
    private InfoEntity entity;

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(this::onThrowable)
                .flatMap(this::flat);
    }


    private ObservableSource<T> flat(T t) {
        if (t instanceof InfoEntity) {
            entity = (InfoEntity) t;
            return Observable.create(this::create);
        }
        return Observable.just(t);
    }

    /**
     * 处理对应异常类型(是抛异常还是怎么的,还是设置Entity中Code值用与处理)
     *
     * @param emitter
     */
    private void create(ObservableEmitter<T> emitter) {
        try {
            //
            switch (entity.getCode()) {
                //请求成功
                case ResponseCode.SUCCESS:
                    emitter.onNext((T) entity);
                    break;
                case ResponseCode.TOKEN_TIMEOUT:
                    /* 这里进行很多登出之后 很多的置空的行为 比如设置登录状态 修改对应的sp文件 */
                //请求失败
                case ResponseCode.FAIL:
                    emitter.onNext((T) entity);
                    break;
                default:
                    throw new ApiException(entity.getMsg(), entity.getCode());
            }
        } catch (Exception e) {
            emitter.onError(e);
        } finally {
            emitter.onComplete();
        }
    }

    /**
     * 判断异常类型
     *
     * @param throwable
     * @return
     */
    private Observable<T> onThrowable(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException response = (HttpException) throwable;
            code = response.code();
            if (code >= 400 && code < 500) {
                errorMessage = "客户端请求错误,状态码：" + code;
            } else if (code >= 500 && code < 600) {
                errorMessage = "服务器状态错误,状态码：" + code;
            } else {
                errorMessage = "未知的状态错误,状态码：" + code;
            }
            throw new ApiException(errorMessage, code);
        } else if (throwable instanceof ServiceConfigurationError) {
            throw new ApiException("服务器错误", 0);
        } else if (throwable instanceof JSONException) {
            throw new ApiException("数据解析错误", 0);
        }
        return Observable.error(throwable);
    }
}