package com.chinaunicom.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *    author : Android
 *    github : https://github.com/renw7/AndroidProject
 *    time   : 2018/10/18
 *    desc   : FragmentPagerAdapter 基类
 */
public class BaseFragmentAdapter<F extends Fragment> extends FragmentPagerAdapter {

    /** Fragment集合 */
    private final List<F> mFragmentSet = new ArrayList<>();

    /** 当前显示的Fragment */
    private F mCurrentFragment;

    public BaseFragmentAdapter(FragmentActivity activity) {
        this(activity.getSupportFragmentManager());
    }

    public BaseFragmentAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    public BaseFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @NonNull
    @Override
    public F getItem(int position) {
        return mFragmentSet.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentSet.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            // 记录当前的Fragment对象
            mCurrentFragment = (F) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    public void addFragment(F fragment) {
        mFragmentSet.add(fragment);
    }

    /**
     * 获取Fragment集合
     */
    public List<F> getAllFragment() {
        return mFragmentSet;
    }

    /**
     * 获取当前的Fragment
     */
    public F getCurrentFragment() {
        return mCurrentFragment;
    }
}