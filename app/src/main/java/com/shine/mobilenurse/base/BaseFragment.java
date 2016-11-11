package com.shine.mobilenurse.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by zzw on 2016/9/27.
 * 描述:Fragment基础类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private CompositeSubscription mCompositeSubscription;

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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden)
            onUnsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}

