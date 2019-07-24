package com.lznby.base.widgets;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lznby.base.R;


/**
 * @author Lznby
 * <p>
 * 自定义Toolbar ViewModel(可复用型)
 */
public class IToolbar {

    /**
     * Toolbar标题
     */
    public ObservableField<String> title = new ObservableField<>("");
    /**
     * 左侧文字
     */
    public ObservableField<String> leftText = new ObservableField<>("");
    /**
     * 右侧文字
     */
    public ObservableField<String> rightText = new ObservableField<>("");
    /**
     * 左侧字体颜色
     */
    public ObservableInt leftTextColor = new ObservableInt(R.color.colorAccent);
    /**
     * 右侧字体颜色
     */
    public ObservableInt rightTextColor = new ObservableInt(R.color.colorAccent);
    /**
     * 标题字体颜色
     */
    public ObservableInt titleTextColor = new ObservableInt(R.color.colorPrimary);
    /**
     * 背景颜色
     */
    public ObservableInt titleBackgroundColor = new ObservableInt(R.color.colorWhite);
    /**
     * 左侧图片资源地址(缺省值按自己实际情况配置)
     */
    public ObservableInt leftIcon = new ObservableInt(R.mipmap.back_black);
    /**
     * 右侧图片资源地址(缺省值按自己实际情况配置)
     */
    public ObservableInt rightIcon = new ObservableInt(R.mipmap.back_black);

    /**
     * 设置左侧图标显示隐藏
     */
    public ObservableBoolean showLeftIcon = new ObservableBoolean(true);
    /**
     * 设置左侧文字显示隐藏
     */
    public ObservableBoolean showLeftText = new ObservableBoolean(false);
    /**
     * 设置右侧图标显示隐藏
     */
    public ObservableBoolean showRightIcon = new ObservableBoolean(true);
    /**
     * 设置右侧文字显示隐藏
     */
    public ObservableBoolean showRightText = new ObservableBoolean(true);

    /**
     * 缺省的点击事件
     */
    private ClickCallback defaultClick = view -> {
    };
    /**
     * 左侧文字点击事件
     */
    public ClickCallback leftTextClick = defaultClick;
    /**
     * 右侧文字点击事件
     */
    public ClickCallback rightTextClick = defaultClick;
    /**
     * 左侧图标点击事件
     */
    public ClickCallback leftIconClick = defaultClick;
    /**
     * 右侧图标点击事件
     */
    public ClickCallback rightIconClick = defaultClick;

    /**
     * 回调(点击事件)
     */
    public interface ClickCallback {
        /**
         * 简单的点击时间回调
         *
         * @param view
         */
        void doSomething(View view);
    }

    /**
     * 设置图片
     *
     * @param imageView
     * @param drawableRes
     */
    @BindingAdapter("toolbarSrc")
    public static void setImageSrc(ImageView imageView, @DrawableRes int drawableRes) {
        imageView.setImageResource(drawableRes);
    }

    /**
     * 设置背景颜色
     *
     * @param view
     * @param colorRes
     */
    @BindingAdapter("toolbarBackgroundColor")
    public static void setBackgroundColor(View view, @ColorRes int colorRes) {
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), colorRes));
    }

    /**
     * 设置字体颜色
     *
     * @param textView
     * @param colorRes
     */
    @BindingAdapter("toolbarTextColor")
    public static void setTextColor(TextView textView, @ColorRes int colorRes) {
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), colorRes));
    }

    public ClickCallback getDefaultClick() {
        return defaultClick;
    }

    public void setDefaultClick(ClickCallback defaultClick) {
        this.defaultClick = defaultClick;
    }

    public ClickCallback getLeftTextClick() {
        return leftTextClick;
    }

    public void setLeftTextClick(ClickCallback leftTextClick) {
        this.leftTextClick = leftTextClick;
    }

    public ClickCallback getRightTextClick() {
        return rightTextClick;
    }

    public void setRightTextClick(ClickCallback rightTextClick) {
        this.rightTextClick = rightTextClick;
    }

    public ClickCallback getLeftIconClick() {
        return leftIconClick;
    }

    public void setLeftIconClick(ClickCallback leftIconClick) {
        this.leftIconClick = leftIconClick;
    }

    public ClickCallback getRightIconClick() {
        return rightIconClick;
    }

    public void setRightIconClick(ClickCallback rightIconClick) {
        this.rightIconClick = rightIconClick;
    }

    //https://www.imooc.com/article/44692
}
