package com.shine.mobilenurse.function.infoquery;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:信息查询
 */

public class InfoQueryFragmeng extends BaseFragment {

    public static InfoQueryFragmeng newInstance() {
        return new InfoQueryFragmeng();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }


    @Override
    protected void initTitle() {
        setTitle("信息查询");
    }
}
