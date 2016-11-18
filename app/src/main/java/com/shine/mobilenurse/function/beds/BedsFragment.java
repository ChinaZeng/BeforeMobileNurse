package com.shine.mobilenurse.function.beds;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:床位管理
 */

public class BedsFragment  extends BaseFragment{


    public static BedsFragment newInstance() {
        return new BedsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_beds;
    }

    @Override
    protected void initTitle() {
        setTitle("床位管理");
    }
}
