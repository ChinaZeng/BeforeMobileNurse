package com.shine.mobilenurse.function.specimen;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:标本绑定
 */

public class SpecimenFragment extends BaseFragment {

    public static SpecimenFragment newInstance() {
        return new SpecimenFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_specimen;
    }
}
