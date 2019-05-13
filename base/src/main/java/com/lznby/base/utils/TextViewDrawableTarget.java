package com.lznby.base.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lznby.base.R;
import com.lznby.base.widgets.ImageTextView;


/**
 * @author Lznby  (首页中要使用)
 *
 * Glide加载TextView中的Drawable(这边是加载左侧的,感觉可以写一个抽象类)
 *
 * 参考：https://www.jianshu.com/p/b8d9ecf90297
 *
 *
 * 使用方法：
 * GlideApp.with(this)
 *         .load(url)
 *         .into(new TextViewDrawableTarget(imageTextView,Orientation.LEFT))
 */

public class TextViewDrawableTarget extends CustomViewTarget<ImageTextView,Drawable> {


    /**
     * 方向
     */
    private Orientation orientation;

    /**
     * Constructor that defaults {@code waitForLayout} to {@code false}.
     *
     * @param view
     */
    public TextViewDrawableTarget(@NonNull ImageTextView view, Orientation orientation) {
        super(view);
        this.orientation = orientation;
    }

    @Override
    protected void onResourceCleared(@Nullable Drawable placeholder) {

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        //加载失败例如url=null，此时使用 fallback不生效
        //这里的缺省图片记得换
        switch (orientation) {
            case LEFT:
                view.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(view.getContext(),R.mipmap.more),null, null, null);
                break;
            case TOP:
                view.setCompoundDrawablesWithIntrinsicBounds(null,ContextCompat.getDrawable(view.getContext(),R.mipmap.more), null, null);
                break;
            case RIGHT:
                view.setCompoundDrawablesWithIntrinsicBounds(null,null, ContextCompat.getDrawable(view.getContext(),R.mipmap.more), null);
                break;
            default:
                view.setCompoundDrawablesWithIntrinsicBounds(null,null, null, ContextCompat.getDrawable(view.getContext(),R.mipmap.more));
                break;
        }
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

        switch (orientation) {
            case LEFT:
                view.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                break;
            case TOP:
                view.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
                break;
            case RIGHT:
                view.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
                break;
            default:
                view.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
                break;
        }
    }

    public enum Orientation {
        /**
         * 加载图片的位置
         */
        LEFT, RIGHT,TOP,BOTTOM
    }

}
