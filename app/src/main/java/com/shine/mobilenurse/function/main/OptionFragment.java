package com.shine.mobilenurse.function.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zzw on 2016/11/22.
 * 描述:tab切换选项fragment
 */

public class OptionFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TableLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_options;
    }

    public static OptionFragment newInstance() {
        return new OptionFragment();
    }


    @Override
    protected void initView(View view) {
        super.initView(view);


    }
}
