package com.lznby.frame.exmaple.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.lznby.frame.R;

/**
 * @author Lznby
 * <p>
 * GlideUtils
 */
public class GlideUtils {

    public static void loadImage(ImageView view, String url) {
//        getDrawableBuild(view).load(url).into(view);
    }

    /**
     * 获取统一配置的GlideBuilder
     *
     * @param view
     */
    private static RequestBuilder<Drawable> getDrawableBuild(ImageView view) {
        GlideRequest<Drawable> builder = GlideApp.with(view).asDrawable();
        builder.error(R.mipmap.icon_fail);
        builder.fallback(R.mipmap.icon_loading);
        builder.placeholder(R.mipmap.icon_car);
        return builder;
    }

}
