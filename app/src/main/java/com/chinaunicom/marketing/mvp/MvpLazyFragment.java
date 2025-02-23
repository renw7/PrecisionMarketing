package com.chinaunicom.marketing.mvp;

import com.chinaunicom.marketing.common.MyLazyFragment;
import com.chinaunicom.marketing.mvp.proxy.IMvpPresenterProxy;
import com.chinaunicom.marketing.mvp.proxy.MvpPresenterProxyImpl;

/**
 *    author : Android
 *    github : https://github.com/renw7/AndroidProject
 *    time   : 2018/11/17
 *    desc   : MVP 懒加载 Fragment 基类
 */
public abstract class MvpLazyFragment extends MyLazyFragment implements IMvpView {

    private IMvpPresenterProxy mMvpProxy;

    @Override
    protected void initFragment() {
        mMvpProxy = createPresenterProxy();
        mMvpProxy.bindPresenter();
        super.initFragment();
    }

    protected IMvpPresenterProxy createPresenterProxy() {
        return new MvpPresenterProxyImpl(this);
    }

    @Override
    public void onDestroy() {
        if (mMvpProxy != null) {
            mMvpProxy.unbindPresenter();
        }
        super.onDestroy();
    }
}