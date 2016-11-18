package com.shine.mobilenurse.function.skinTest;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:皮试管理
 */

public class SkinTestFragment extends BaseFragment {

    public static SkinTestFragment newInstance() {
        return new SkinTestFragment();
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }


    @Override
    protected void initTitle() {
        setTitle("皮试管理");
    }
}
