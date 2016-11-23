package com.shine.mobilenurse.function.signs;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:生命体征
 */

public class SignsFragment extends BaseFragment {
    public static SignsFragment newInstance() {
        return new SignsFragment();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signs;
    }
}
