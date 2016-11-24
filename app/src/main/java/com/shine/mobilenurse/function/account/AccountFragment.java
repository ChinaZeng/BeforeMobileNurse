package com.shine.mobilenurse.function.account;

import android.os.Bundle;
import android.view.View;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.utils.LogPrint;

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
        return R.layout.fragment_account;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogPrint.log_d("zzz","AccountFragment__onstart");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogPrint.log_d("zzz","AccountFragment__onCreate");

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogPrint.log_d("zzz","AccountFragment__onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogPrint.log_d("zzz","AccountFragment__onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogPrint.log_d("zzz","AccountFragment__onPause");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogPrint.log_d("zzz","AccountFragment__onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogPrint.log_d("zzz","AccountFragment__onDestroyView");
    }
}
