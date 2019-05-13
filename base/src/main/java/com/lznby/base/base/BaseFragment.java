package com.lznby.base.base;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.databinding.library.baseAdapters.BR;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * JetPack BaseActivity
 *
 * @author Lznby
 * @param <VM>  对应实现BaseViewModel的子类(或BaseViewModel<K>)
 * @param <K>  BaseViewModel实现类中对应的实体类的类型BaseViewModel<F,K>
 * @param <Binding> ViewDataBinding
 */
public abstract class BaseFragment<VM extends BaseFragmentViewModel, K,Binding extends ViewDataBinding> extends Fragment {

    /**
     * 缺省的ViewModel
     */
    protected VM viewModel;

    /**
     * 管理RxJava线程
     */
    private ListCompositeDisposable mDisposable = new ListCompositeDisposable();

    /**
     * 缺省的Binding
     */
    public Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // dataBinding方式设置布局
        binding = DataBindingUtil.inflate(inflater,setLayout(), container, false);
        // 配置ViewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(getClazz());
        viewModel.setFragment(() -> this);
        // viewModel绑定LifecycleObserver(这样ViewModel中可以执行一些与生命周期有关的逻辑代码)
        this.getLifecycle().addObserver(viewModel);
        // 绑定该数据的UI代码(将LiveData数据与视图进行单向绑定,LiveData数据变化时,对应的视图会自动刷新
        final Observer<K> observer = this::bindView;
        // 这里警告是因为我板BaseViewModel<K>改成了BaseViewModel,因为不清楚怎么获取BaseViewModel的clazz
        viewModel.getLiveData().observe(this,observer);
        // 让xml内绑定的LiveData和Observer建立连接,也正是因为这段代码,让LiveData能够动态感知Activity的生命周期
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm,viewModel);

        // 用于写一些在onCreateView中处理的逻辑(即相当于onCreateView)
        doOnCreateView(savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 解绑回收RxJava线程
        mDisposable.dispose();
    }

    /**
     * 设置Fragment布局
     *
     * @return R.layout.xxx
     */
    public abstract @LayoutRes int setLayout();

    /**
     * 设置Application(注意:可以不用传值的,为空也行的)
     *
     * @return Application
     */
    public abstract Application getApplication();

    /**
     * 获取ViewModel的class,即VM.class
     *
     * @return VM.class
     */
    protected abstract Class<VM> getClazz();

    /**
     * 设置需要与数据绑定的操作(将视图与数据绑定,单向的)
     *
     * @param entity 实体类对象
     */
    abstract protected void bindView(K entity);

    /**
     * 用于写一些在onCreateView中处理的逻辑(即相当于onCreateView)
     *
     * @param savedInstanceState 用于保存恢复状态的值
     */
    protected abstract void doOnCreateView(@Nullable Bundle savedInstanceState);

    /**
     * 将RxJava的Disposable添加到ListCompositeDisposable中用于在onDestroy中回收
     *
     * @param disposable RxJava返回的Disposable对象
     */
    public void addDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }
}