package com.shine.mobilenurse.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Subscription;


/**
 * Created by zzw on 2016/9/27.
 * 描述:Fragment基础类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected Subscription subscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return null;
        }
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (view != null) {
            initView(view);
            initData();
        }
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 返回资源id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     *
     * @param view
     */
    protected void initView(View view) {
        findViewId(view);
        setViewListener();
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * findId
     */
    protected void findViewId(View view) {

    }

    /**
     * 设置相关监听
     */
    protected void setViewListener() {

    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

