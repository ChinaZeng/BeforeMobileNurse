package com.shine.mobilenurse.function.call;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:呼叫管理
 */

public class CallFragment extends BaseFragment {

    public static CallFragment newInstance() {
        return new CallFragment();
    }

    @Override
    protected int getLayoutId() {
        return 0;

    }

    @Override
    protected void initTitle() {
        setTitle("呼叫管理");
    }

}
