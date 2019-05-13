package com.lznby.base.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.lznby.base.R;

/**
 * @author Lznby
 * <p>
 * GlideUtils
 */
public class GlideUtils {

    public static void loadImage(ImageView view, String url) {
        getDrawableBuild(view).load(url).into(view);
    }

    /**
     * 获取统一配置的GlideBuilder
     *
     * @param view
     */
    private static RequestBuilder<Drawable> getDrawableBuild(ImageView view) {
        RequestBuilder<Drawable> builder = GlideApp.with(view).asDrawable();
        ((GlideRequest<Drawable>) builder).error(R.mipmap.icon_fail);
        ((GlideRequest<Drawable>) builder).fallback(R.mipmap.icon_loading);
        ((GlideRequest<Drawable>) builder).placeholder(R.mipmap.icon_car);
        return builder;
    }

}
