package com.shine.mobilenurse.function.main;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.HeaderSpanSizeLookup;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.function.OnRecyItemClickListener;
import com.shine.mobilenurse.function.adapter.MainFragmentMidAdapter;
import com.shine.mobilenurse.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zzw on 2016/10/13.
 * 描述:首页fragment
 */
public class MainFragment extends BaseFragment implements View.OnClickListener, OnRecyItemClickListener<String> {

    @BindView(R.id.main_fragment_recy)
    LRecyclerView mainFragmentRecy;

    private TextView top0, top1, top2, top3, Bottom0, Bottom1;

    private MainFragmentMidAdapter adapter = null;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MainFragmentMidAdapter(getActivity());
        adapter.setOnRecyItemClickListener(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        mainFragmentRecy.setAdapter(mLRecyclerViewAdapter);

        mainFragmentRecy.addItemDecoration(new SpaceItemDecoration(1));
        //setLayoutManager
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((LRecyclerViewAdapter) mainFragmentRecy.getAdapter(), manager.getSpanCount()));
        mainFragmentRecy.setLayoutManager(manager);

        View viewTop = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment_top, null);
        View viewBottom = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment_bottom, null);
        initTopAndBottom(viewTop, viewBottom);
        mLRecyclerViewAdapter.addHeaderView(viewTop);
        mLRecyclerViewAdapter.addFooterView(viewBottom);

        mainFragmentRecy.setPullRefreshEnabled(false);
    }

    private void initTopAndBottom(View topView, View bottomView) {
        top0 = (TextView) topView.findViewById(R.id.top_0);
        top1 = (TextView) topView.findViewById(R.id.top_1);
        top2 = (TextView) topView.findViewById(R.id.top_2);
        top3 = (TextView) topView.findViewById(R.id.top_3);
        Bottom0 = (TextView) bottomView.findViewById(R.id.bottom_0);
        Bottom1 = (TextView) bottomView.findViewById(R.id.bottom_1);
        top0.setOnClickListener(this);
        top1.setOnClickListener(this);
        top2.setOnClickListener(this);
        top3.setOnClickListener(this);
        Bottom0.setOnClickListener(this);
        Bottom1.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        adapter.addItems(initOptionMidList());
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * 得到Top数据
     *
     * @return
     */
    private List<String> initOptionTopList() {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            stringList.add("测试选项" + i);
        }
        return stringList;
    }

    /**
     * 得到中间数据
     *
     * @return
     */
    private List<String> initOptionMidList() {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            stringList.add("测试选项" + i);
        }
        return stringList;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_0:
                break;
            case R.id.top_1:
                break;
            case R.id.top_2:
                break;
            case R.id.top_3:
                break;
            case R.id.bottom_0:
                break;
            case R.id.bottom_1:
                break;
        }
    }

    @Override
    public void OnItemClick(View view, String s, int pos) {
        ((MainActivity) getActivity()).chooseFragment(1, 2);
    }
}
