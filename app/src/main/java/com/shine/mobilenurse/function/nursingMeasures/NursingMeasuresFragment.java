package com.shine.mobilenurse.function.nursingMeasures;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:护理措施
 */

public class NursingMeasuresFragment extends BaseFragment {

    public static NursingMeasuresFragment newInstance() {
        return new NursingMeasuresFragment();
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initTitle() {
        setTitle("护理措施");
    }
}
