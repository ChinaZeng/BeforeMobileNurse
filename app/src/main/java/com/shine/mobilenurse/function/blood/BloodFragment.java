package com.shine.mobilenurse.function.blood;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:采血管理
 */

public class BloodFragment extends BaseFragment {


    public static BloodFragment newInstance() {
        return new BloodFragment();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initTitle() {
        setTitle("采血管理");
    }
}
