package com.lznby.frame.exmaple.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lznby.base.BR;
import com.lznby.base.base.BaseActivity;
import com.lznby.base.widgets.IToolbar;
import com.lznby.base.widgets.loadingviwe.LoadingState;
import com.lznby.base.widgets.loadingviwe.XLoadingView;
import com.lznby.frame.R;
import com.lznby.frame.databinding.ActivityMainBinding;

/**
 * @author Lznby
 */
public class MainActivity extends BaseActivity<MainActivityVm,Object,ActivityMainBinding> {

    /**
     * 测试XLoadingView
     */
    XLoadingView xLoadingView;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindView(Object entity) {

    }

    @Override
    protected Class<MainActivityVm> getClazz() {
        return MainActivityVm.class;
    }

    @Override
    protected void doOnCreate(@Nullable Bundle savedInstanceState) {
//        initLoading();
        initToolbar();
    }

    public void initToolbar() {
        IToolbar toolbar = new IToolbar();
        toolbar.leftIconClick = view -> finish();
        toolbar.title.set("自定义Toolbar");
        toolbar.showRightIcon.set(false);
        toolbar.rightText.set("为空");
        toolbar.showRightText.set(true);
//        toolbar.rightTextColor.set(R.color.colorPrimary);
        toolbar.rightTextColor.set(R.color.colorAccent);
        toolbar.rightTextClick = view -> {
            viewModel.state.set(LoadingState.ERROR);
            toolbar.rightText.set("转变");
        };
        binding.setToolbarVm(toolbar);
        binding.setVariable(BR.toolbarVm,toolbar);
    }

    /**
     * 初始化XLoadingView
//     */
//    public void initLoading() {
//
//        // 第一种方法(在代码里设置)
//        xLoadingView = XLoadingView.wrap(this);
//        // 第二种方法(布局里面包裹)
//        xLoadingView = findViewById(R.id.xloading_view);
//        xLoadingView.showLoading();
//        xLoadingView.setOnRetryClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xLoadingView.showLoading();
//            }
//        });
//    }
}
