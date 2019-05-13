package com.lznby.base.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;
/**
 *
 * JetPack BaseActivity
 *
 * @author Lznby
 *
 * @param <VM> 对应实现BaseViewModel的子类(或BaseViewModel)
 * @param <K> BaseViewModel实现类中对应的实体类的类型 BaseViewModel<A,K>
 * @param <Binding> ViewDataBinding
 */
public abstract class BaseActivity<VM extends BaseActivityViewModel,K,Binding extends ViewDataBinding> extends AppCompatActivity {

    /**
     * 管理RxJava线程
     */
    private ListCompositeDisposable mDisposable = new ListCompositeDisposable();

    /**
     * 缺省的ViewModel
     */
    protected VM viewModel;

    /**
     * 缺省的Binding
     */
    public Binding binding;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // dataBinding方式设置布局
        binding = DataBindingUtil.setContentView(this,setLayout());
        // 配置ViewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(getClazz());
        viewModel.setActivity(() -> this);
        // viewModel绑定LifecycleObserver(这样ViewModel中可以执行一些与生命周期有关的逻辑代码)
        this.getLifecycle().addObserver(viewModel);
        // 绑定该数据的UI代码(将LiveData数据与视图进行单向绑定,LiveData数据变化时,对应的视图会自动刷新
        final Observer<K> observable = this::bindView;
        viewModel.getLiveData().observe(this,observable);
        // 让xml内绑定的LiveData和Observer建立连接,也正是应为这段代码,让LiveData能感知Activity的生命周期
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm,viewModel);
        // 写一些其他的应该在onCreate方法中的业务逻辑
        doOnCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑RxJava
        mDisposable.dispose();
        // 解绑ViewModel的LifecycleObserver
        this.getLifecycle().removeObserver(viewModel);
    }


    /**
     * 布局文件的id
     *
     * @return  return R.layout.xxx
     */
    protected abstract @LayoutRes
    int setLayout();

    /**
     * 设置需要与数据绑定的操作
     *
     * @param entity 实体类对象
     */
    abstract protected void bindView(K entity);

    /**
     * 获取ViewModel的class,即VM.class
     *
     * @return VM.class
     */
    protected abstract Class<VM> getClazz();

    /**
     * 写一些在onCreate中的逻辑(相当于onCreate)
     *
     * @param savedInstanceState 保存状态信息
     */
    protected abstract void doOnCreate(@Nullable Bundle savedInstanceState);

    /**
     * 将RxJava的Disposable添加到ListCompositeDisposable中用于在onDestroy中回收
     *
     * @param disposable RxJava返回的Disposable对象
     */
    public void addDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }

}
