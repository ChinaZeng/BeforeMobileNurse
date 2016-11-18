package com.shine.mobilenurse.function.notice;

import com.shine.mobilenurse.base.BaseFragment;

/**
 * Created by zzw on 2016/10/13.
 * 描述:通知管理
 */

public class NoticeFragment extends BaseFragment {

    public static NoticeFragment newInstance() {
        return new NoticeFragment();
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }


    @Override
    protected void initTitle() {
        setTitle("通知管理");
    }
}
