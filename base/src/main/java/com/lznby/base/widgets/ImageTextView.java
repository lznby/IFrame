package com.lznby.base.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.lznby.base.R;


/**
 * 有上下左右图片的TextView
 *
 * @author Lznby
 */
public class ImageTextView extends AppCompatTextView {
    private int mLeftWidth;
    private int mLeftHeight;
    private int mTopWidth;
    private int mTopHeight;
    private int mRightWidth;
    private int mRightHeight;
    private int mBottomWidth;
    private int mBottomHeight;

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    public void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView);

        mLeftWidth = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableLeftWidth, 0);
        mLeftHeight = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableLeftHeight, 0);
        mTopWidth = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableTopWidth, 0);
        mTopHeight = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableTopHeight, 0);
        mRightWidth = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableRightWidth, 0);
        mRightHeight = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableRightHeight, 0);
        mBottomWidth = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableBottomWidth, 0);
        mBottomHeight = typedArray.getDimensionPixelOffset(R.styleable.ImageTextView_drawableBottomHeight, 0);

        typedArray.recycle();

        setDrawablesSize();
    }

    private void setDrawablesSize() {
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (int i = 0; i < compoundDrawables.length; i++) {
            switch (i) {
                case 0:
                    setDrawableBounds(compoundDrawables[0], mLeftWidth, mLeftHeight);
                    break;
                case 1:
                    setDrawableBounds(compoundDrawables[1], mTopWidth, mTopHeight);
                    break;
                case 2:
                    setDrawableBounds(compoundDrawables[2], mRightWidth, mRightHeight);
                    break;
                case 3:
                    setDrawableBounds(compoundDrawables[3], mBottomWidth, mBottomHeight);
                    break;
                default:

                    break;
            }

        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    private void setDrawableBounds(Drawable drawable, int width, int height) {
        if (drawable != null) {
            double scale = ((double) drawable.getIntrinsicHeight()) / ((double) drawable.getIntrinsicWidth());
            drawable.setBounds(0, 0, width, height);
            Rect bounds = drawable.getBounds();
            //高宽只给一个值时，自适应
            if (bounds.right != 0 || bounds.bottom != 0) {
                if (bounds.right == 0) {
                    bounds.right = (int) (bounds.bottom / scale);
                    drawable.setBounds(bounds);
                }
                if (bounds.bottom == 0) {
                    bounds.bottom = (int) (bounds.right * scale);
                    drawable.setBounds(bounds);
                }
            }

        }
    }

    public int getLeftWidth() {
        return mLeftWidth;
    }

    public void setLeftWidth(int leftWidth) {
        mLeftWidth = leftWidth;
    }

    public int getLeftHeight() {
        return mLeftHeight;
    }

    public void setLeftHeight(int leftHeight) {
        mLeftHeight = leftHeight;
    }

    public int getTopWidth() {
        return mTopWidth;
    }

    public void setTopWidth(int topWidth) {
        mTopWidth = topWidth;
    }

    public int getTopHeight() {
        return mTopHeight;
    }

    public void setTopHeight(int topHeight) {
        mTopHeight = topHeight;
    }

    public int getRightWidth() {
        return mRightWidth;
    }

    public void setRightWidth(int rightWidth) {
        mRightWidth = rightWidth;
    }

    public int getRightHeight() {
        return mRightHeight;
    }

    public void setRightHeight(int rightHeight) {
        mRightHeight = rightHeight;
    }

    public int getBottomWidth() {
        return mBottomWidth;
    }

    public void setBottomWidth(int bottomWidth) {
        mBottomWidth = bottomWidth;
    }

    public int getBottomHeight() {
        return mBottomHeight;
    }

    public void setBottomHeight(int bottomHeight) {
        mBottomHeight = bottomHeight;
    }
}

