package com.shine.mobilenurse.function.patrol;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:巡视管理
 */

public class PatrolFragment extends BaseFragment {

    public static PatrolFragment newInstance() {
        return new PatrolFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_patrol;
    }


}
