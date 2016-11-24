package com.shine.mobilenurse.function.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zzw on 2016/11/22.
 * 描述:tab切换选项fragment
 */

public class OptionFragment extends BaseFragment {

    public static final String FIRST_INTO = "first_into";

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabLayout_right)
    ImageView tabLayoutRight;

    private OptionDao optionDao;
    private List<Option> options;
    private Option nowOption;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        nowOption = (Option) bundle.getSerializable(FIRST_INTO);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_options;
    }

    public static OptionFragment newInstance() {
        return new OptionFragment();
    }


    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        optionDao = OptionDao.getInstance(getContext());
    }


    @Override
    protected void initData() {
        super.initData();
        options = optionDao.findShow();
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager(), options);
        viewpager.setAdapter(adapter);
        viewpager.setPageMargin(10);
        //设置tablayout为滑动模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(comPos(nowOption), true);
    }


    /**
     * 扫描枪登陆 EventBus传递
     *
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOptionMessage(Option option) {
        if (options != null) {
            viewpager.setCurrentItem(comPos(option), true);
        }
    }

    /**
     * 比较得到滚动的位置
     * @param option
     * @return
     */
    private int comPos(Option option) {
        int pos = -1;
        for (int i = 0; i < options.size(); i++) {
            if (option.equals(options.get(i))) {
                pos = i;
                this.nowOption = option;
                break;
            }
        }
        return pos;
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

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }
}
