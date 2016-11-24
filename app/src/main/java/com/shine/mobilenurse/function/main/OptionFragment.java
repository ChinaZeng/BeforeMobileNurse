package com.shine.mobilenurse.function.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.db.OptionDao;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.Res;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zzw on 2016/11/22.
 * 描述:tab切换选项fragment
 */

public class OptionFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabLayout_right)
    ImageView tabLayoutRight;

    private OptionDao optionDao;

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
        optionDao = OptionDao.getInstance(getContext());
    }


    @Override
    protected void initData() {
        super.initData();
        List<Option> options = optionDao.findShow();
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager(), options);
        viewpager.setAdapter(adapter);
        viewpager.setPageMargin(10);
        //设置tablayout为滑动模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewpager);
    }


    class MyPagerAdapter extends FragmentStatePagerAdapter {
        private List<Option> list;

        public MyPagerAdapter(FragmentManager fm, List<Option> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return Res.getFragmentFromOpId(list.get(position).getId());
        }

        @Override
        public int getCount() {
            return list.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getName();
        }
    }
}
