package com.shine.mobilenurse.function.assess;

import com.shine.mobilenurse.R;
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
        return R.layout.fragment_assess;
    }

}
