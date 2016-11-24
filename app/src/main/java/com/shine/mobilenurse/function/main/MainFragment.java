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
import com.shine.mobilenurse.db.OptionDao;
import com.shine.mobilenurse.entity.Option;
import com.shine.mobilenurse.function.OnRecyItemClickListener;
import com.shine.mobilenurse.function.adapter.MainFragmentMidAdapter;
import com.shine.mobilenurse.view.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;


/**
 * Created by zzw on 2016/10/13.
 * 描述:首页fragment
 */
public class MainFragment extends BaseFragment implements View.OnClickListener, OnRecyItemClickListener<Option> {

    @BindView(R.id.main_fragment_recy)
    LRecyclerView mainFragmentRecy;

    private MainFragmentMidAdapter adapter = null;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private OptionDao optionDao;


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
        optionDao = OptionDao.getInstance(getContext());
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
        TextView top0 = (TextView) topView.findViewById(R.id.top_0);
        TextView top1 = (TextView) topView.findViewById(R.id.top_1);
        TextView top2 = (TextView) topView.findViewById(R.id.top_2);
        TextView top3 = (TextView) topView.findViewById(R.id.top_3);
        TextView Bottom0 = (TextView) bottomView.findViewById(R.id.bottom_0);
        TextView Bottom1 = (TextView) bottomView.findViewById(R.id.bottom_1);
        View bottomLine0 = bottomView.findViewById(R.id.bottom_line_0);
        top0.setOnClickListener(this);
        top1.setOnClickListener(this);
        top2.setOnClickListener(this);
        top3.setOnClickListener(this);
        Bottom0.setOnClickListener(this);
        Bottom1.setOnClickListener(this);

        TextView[] topTvList = new TextView[]{top0, top1, top2, top3};
        List<Option> topList = initOptionTopList();
        int a = topList.size() > topTvList.length ? topTvList.length : topList.size();
        for (int i = 0; i < a; i++) {
            topTvList[i].setText(topList.get(i).getName());
            topTvList[i].setVisibility(View.VISIBLE);
        }

        TextView[] bottomTvList = new TextView[]{Bottom0, Bottom1};
        List<Option> bottomList = initOptionBottomList();
        int b = bottomList.size() > bottomTvList.length ? bottomTvList.length : bottomList.size();
        if (b > 1)
            bottomLine0.setVisibility(View.VISIBLE);
        for (int i = 0; i < b; i++) {
            bottomTvList[i].setText(bottomList.get(i).getName());
            bottomTvList[i].setVisibility(View.VISIBLE);
        }
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
    private List<Option> initOptionTopList() {
        List<Option> list = optionDao.findTop();
        if (list == null || list.size() == 0) {
            Option option1 = new Option(1, 1, "医嘱执行", 0);
            Option option2 = new Option(2, 2, "生命体征", 0);
            Option option3 = new Option(3, 3, "记账管理", 0);
            Option option4 = new Option(4, 4, "床位管理", 0);
            list.add(option1);
            list.add(option2);
            list.add(option3);
            list.add(option4);

            optionDao.insert(list);
        }
        return list;
    }


    /**
     * 得到中间数据
     *
     * @return
     */
    private List<Option> initOptionMidList() {
        List<Option> list = optionDao.findMid();
        if (list == null || list.size() == 0) {
            Option option5 = new Option(5, 5, "标本绑定", 1);
            Option option6 = new Option(6, 6, "采血管理", 1);
            Option option7 = new Option(7, 7, "皮试管理", 1);
            Option option8 = new Option(8, 8, "巡视管理", 1);
            Option option9 = new Option(9, 9, "评估管理", 1);
            Option option10 = new Option(10, 10, "体温单", 1);
            Option option11 = new Option(11, 11, "护理措施", 1);
            Option option12 = new Option(12, 12, "信息查询", 1);
            Option option13 = new Option(13, 13, "呼叫管理", 1);
            Option option14 = new Option(14, 14, "通知管理", 1);
            Option option15 = new Option(15, 15, "宣教 ", 1);
            Option option16 = new Option(16, 16, "远程打印", 1);
            list.add(option5);
            list.add(option6);
            list.add(option7);
            list.add(option8);
            list.add(option9);
            list.add(option10);
            list.add(option11);
            list.add(option12);
            list.add(option13);
            list.add(option14);
            list.add(option15);
            list.add(option16);
            optionDao.insert(list);
        }
        return list;
    }


    /**
     * 得到Bottom数据
     *
     * @return
     */
    private List<Option> initOptionBottomList() {
        List<Option> list = optionDao.findBottom();
        if (list == null || list.size() == 0) {
            Option option17 = new Option(17, 17, "检验结果", 2);
            Option option18 = new Option(18, 18, "检查结果", 2);
            list.add(option17);
            list.add(option18);
            optionDao.insert(list);
        }
        return list;
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
    public void OnItemClick(View view, Option option, int pos) {
        ((MainActivity) getActivity()).chooseFragment(1, 2);

    }
}
