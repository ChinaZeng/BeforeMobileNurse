package com.shine.mobilenurse.function.account;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:记账管理
 */

public class AccountFragment extends BaseFragment {


    public static AccountFragment newInstance() {
        return new AccountFragment();
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initTitle() {
        setTitle("记账管理");
    }


}
