package com.chinaunicom.marketing.common;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.chinaunicom.base.BaseDialogFragment;
import com.hjq.toast.ToastUtils;

import butterknife.ButterKnife;

/**
 *    author : Android
 *    github : https://github.com/renw7/AndroidProject
 *    time   : 2019/05/18
 *    desc   : 项目中的 Dialog 基类
 */
public final class MyDialogFragment {

    public static class Builder<B extends MyDialogFragment.Builder>
            extends BaseDialogFragment.Builder<B> {

        public Builder(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public B setContentView(@NonNull View view) {
            // 使用 ButterKnife 注解
            ButterKnife.bind(this, view);
            return super.setContentView(view);
        }

        /**
         * 显示吐司
         */
        public void toast(CharSequence text) {
            ToastUtils.show(text);
        }

        public void toast(@StringRes int id) {
            ToastUtils.show(id);
        }

        public void toast(Object object) {
            ToastUtils.show(object);
        }
    }
}