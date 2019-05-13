package com.lznby.base.network.transform;


import com.lznby.base.network.configure.InfoEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * RxJava中将解析后BaseEntity<T>的对象转换为T对象
 *
 * @author Lznby
 *
 * @param <T>
 */
public class RestfulTransformer<T> implements ObservableTransformer<InfoEntity<T>, T> {
    private InfoEntity<T> entity;

    @Override
    public ObservableSource<T> apply(Observable<InfoEntity<T>> upstream) {
        return upstream
                .compose(new ErrorTransform<>())
                .flatMap(this::flat);
    }

    private ObservableSource<? extends T> flat(InfoEntity<T> tInfoEntity) {
        this.entity = tInfoEntity;
        return Observable.create(this::create);
    }

    private void create(ObservableEmitter<T> emitter) {
        try {
            if (entity.getData() != null) {
                emitter.onNext(entity.getData());
            }
        } catch (Exception e) {
            emitter.onError(e);
        } finally {
            emitter.onComplete();
        }
    }
}