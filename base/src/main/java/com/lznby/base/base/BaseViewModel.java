package com.lznby.base.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * JetPack ViewModel
 *
 * postValue和setValue使用区别.
 *
 * getLiveData.postValue用于子(非UI)线程.
 *
 * getLiveData.setValue 用于主(UI)线程.
 *
 * @author Lznby
 *
 * @param <E> LiveData对应实体类的类型
 */
public class BaseViewModel<E> extends ViewModel implements LifecycleObserver {
    /**
     * 管理RxJava线程
     */
    private ListCompositeDisposable mDisposable = new ListCompositeDisposable();

    /**
     * LiveData对象
     */
    private MutableLiveData<E> liveData;


    /**
     * 获取LiveData对象
     *
     * @return  liveData
     */
    public MutableLiveData<E> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }


    /**
     * 将RxJava的Disposable添加到ListCompositeDisposable中用于在onDestroy中回收
     *
     * @param disposable RxJava返回的Disposable对象
     */
    protected void addDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        if(!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        super.onCleared();
    }



    /**
     * 用于写一些在onCreate中执行的逻辑.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){}

    /**
     * 用于写一些在onStart中执行的逻辑.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){}

    /**
     * 用于写一些在onResume中执行的逻辑.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){}

    /**
     * 用于写一些在onPause中执行的逻辑.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){}

    /**
     * 用于写一些在onStop中执行的逻辑.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){}

    /**
     * 用于写一些在onDestroy中执行的逻辑.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        if(!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
