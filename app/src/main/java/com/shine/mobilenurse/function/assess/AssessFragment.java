package com.shine.mobilenurse.function.assess;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:评估管理
 */

public class AssessFragment extends BaseFragment {

    public static AssessFragment newInstance() {
        return new AssessFragment();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initTitle() {
        setTitle("评估管理");
    }
}
