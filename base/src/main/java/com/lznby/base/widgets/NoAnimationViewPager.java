package com.lznby.base.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止ViewPager切换动画
 *
 * @author Lznby
 */
public class NoAnimationViewPager extends ViewPager {
    public NoAnimationViewPager(@NonNull Context context) {
        super(context);
    }

    public NoAnimationViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        // 去除切换动画效果(第二个参数改为false)
        super.setCurrentItem(item,false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;//false表示不拦截 事件传给子控件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //onInterceptTouchEvent返回false或者true都会调用此方法 如果在onInterceptTouchEvent不拦截 在此方法中返回true或者false  viewpager都不能滑动
        return false;
    }
}
