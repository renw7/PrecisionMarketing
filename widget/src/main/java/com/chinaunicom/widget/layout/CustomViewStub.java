package com.chinaunicom.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hjq.widget.R;

/**
 *    author : Android
 *    github : https://github.com/renw7/AndroidProject
 *    time   : 2019/07/06
 *    desc   : 自定义 ViewStub（原生 ViewStub 的缺点：继承至 View，不支持 findViewById、动态添加和移除 View、监听显示隐藏）
 */
public final class CustomViewStub extends FrameLayout {

    private OnViewStubListener mListener;

    private final int mLayoutResource;

    private View mInflateView;

    public CustomViewStub(Context context) {
        this(context, null);
    }

    public CustomViewStub(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewStub(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomViewStub);
        mLayoutResource = array.getResourceId(R.styleable.CustomViewStub_android_layout, 0);
        array.recycle();

        // 隐藏自己
        setVisibility(GONE);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (mInflateView == null && visibility != GONE) {

            mInflateView = LayoutInflater.from(getContext()).inflate(mLayoutResource, this, false);
            FrameLayout.LayoutParams layoutParams = (LayoutParams) mInflateView.getLayoutParams();
            layoutParams.width = getLayoutParams().width;
            layoutParams.height = getLayoutParams().height;
            if (layoutParams.gravity == FrameLayout.LayoutParams.UNSPECIFIED_GRAVITY) {
                layoutParams.gravity = Gravity.CENTER;
            }
            mInflateView.setLayoutParams(layoutParams);
            addView(mInflateView);

            if (mListener != null) {
                mListener.onInflate(this, mInflateView);
            }
        }

        if (mListener != null) {
            mListener.onVisibility(this, visibility);
        }
    }

    /**
     * 获取填充的 View
     */
    public View getInflateView() {
        return mInflateView;
    }

    /**
     * 设置监听器
     */
    public void setOnViewStubListener(OnViewStubListener listener) {
        mListener = listener;
    }

    public interface OnViewStubListener {

        /**
         * 布局填充回调（可在此中做 View 初始化）
         *
         * @param stub              当前 ViewStub 对象
         * @param inflatedView      填充布局对象
         */
        void onInflate(CustomViewStub stub, View inflatedView);

        /**
         * 可见状态改变（可在此中做 View 更新）
         *
         * @param stub              当前 ViewStub 对象
         * @param visibility        可见状态参数改变
         */
        void onVisibility(CustomViewStub stub, int visibility);
    }
}