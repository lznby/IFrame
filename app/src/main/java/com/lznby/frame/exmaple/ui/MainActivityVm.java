package com.lznby.frame.exmaple.ui;

import android.databinding.ObservableField;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.lznby.base.base.BaseActivityViewModel;
import com.lznby.base.widgets.loadingviwe.LoadingState;

/**
 * @author Lznby
 */
public class MainActivityVm extends BaseActivityViewModel<MainActivity,Object> {

    public ObservableField<Integer> state = new ObservableField<>(LoadingState.LOADING);

    public void setRetryClickListener(View view) {
        ToastUtils.showShort("点击了再次尝试");
        state.set(LoadingState.CONTENT);
    }

}
