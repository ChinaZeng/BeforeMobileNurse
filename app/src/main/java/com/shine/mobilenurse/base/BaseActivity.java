package com.shine.mobilenurse.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import rx.Subscription;


/**
 * Created by zzw on 2016/9/27.
 * 描述:Activity基础类
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Subscription subscription;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        initView();
        init(savedInstanceState);
        initData();
    }

    /**
     * 返回资源id
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 初始化状态
     *
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState) {
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        findViewId();
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
    protected void findViewId() {
    }

    /**
     * 设置相关监听
     */
    protected void setViewListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.toolbar_back) {
//            TDevice.hideSoftKeyboard(getCurrentFocus());
//            onBackPressed();
//        }
    }

}
