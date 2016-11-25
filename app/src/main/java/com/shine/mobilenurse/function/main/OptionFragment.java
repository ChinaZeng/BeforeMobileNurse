package com.shine.mobilenurse.function.main;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.db.OptionDao;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.OnRecyItemClickListener;
import com.shine.mobilenurse.function.Res;
import com.shine.mobilenurse.function.adapter.TabOptionChooseAdapter;
import com.shine.mobilenurse.view.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzw on 2016/11/22.
 * 描述:tab切换选项fragment
 */

public class OptionFragment extends BaseFragment implements OnRecyItemClickListener<Option>, PopupWindow.OnDismissListener, ViewPager.OnPageChangeListener {

    public static final String FIRST_INTO = "first_into";

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabLayout_right)
    ImageView tabLayoutRight;
    @BindView(R.id.fragment_option_ll)
    LinearLayout fragmentOptionLl;

    private OptionDao optionDao;
    private List<Option> options;
    private Option nowOption;
    private int nowPotion;
    private PopupWindow popupWindow;
    private TabOptionChooseAdapter adapter;

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


    public PopupWindow getPopupWindow() {
        return popupWindow;
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
        viewpager.setOnPageChangeListener(this);
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
     *
     * @param option
     * @return
     */
    private int comPos(Option option) {
        int pos = -1;
        for (int i = 0; i < options.size(); i++) {
            if (option.equals(options.get(i))) {
                pos = i;
                this.nowPotion = i;
                this.nowOption = option;
                break;
            }
        }
        return pos;
    }


    @OnClick(R.id.tabLayout_right)
    public void onClick() {
        showPop();
    }

    @Override
    public void OnItemClick(View view, Option option, int pos) {
        this.nowOption = option;
        this.nowPotion = pos;
        adapter.setChoosePos(pos);
        viewpager.setCurrentItem(nowPotion, true);
        popupWindow.dismiss();
    }

    @Override
    public void onDismiss() {
        ((MainActivity) getActivity()).pupDismissFragmentBgAnim();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        OptionFragment.this.nowPotion = position;
        OptionFragment.this.nowOption = options.get(position);
        if (adapter != null)
            adapter.setChoosePos(nowPotion);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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


    private void showPop() {
        if (popupWindow == null || adapter == null) {
            initPup();
            showPop();
        } else {
            popupWindow.showAtLocation(fragmentOptionLl, Gravity.TOP, 0, 0);
            ((MainActivity) getActivity()).pupShowFragmentBgAnim();
        }
    }

    private void initPup() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_pup_tab_choose, null);

        view.findViewById(R.id.pup_tab_choose_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();
            }
        });

        RecyclerView pupRecy = (RecyclerView) view.findViewById(R.id.pup_tab_choose_recy);
        pupRecy.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new TabOptionChooseAdapter(getActivity(), nowPotion);
        adapter.setOnRecyItemClickListener(this);
        pupRecy.setAdapter(adapter);
        adapter.addItems(options);

        popupWindow = new PopupWindow(getContext());
        popupWindow.setContentView(view);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        //设置为true  扫描枪失去作用
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(this);
    }

}
