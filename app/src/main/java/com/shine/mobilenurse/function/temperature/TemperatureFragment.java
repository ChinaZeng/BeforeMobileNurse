package com.shine.mobilenurse.function.temperature;

import android.view.View;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureDay;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureInfos;
import com.shine.mobilenurse.utils.ViewUtil;
import com.shine.mobilenurse.view.TempretureTableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2016/10/13.
 * 描述:体温单
 */

public class TemperatureFragment extends BaseFragment {

    private TempretureTableView tempretureTableView;

    public static TemperatureFragment newInstance() {
        return new TemperatureFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tempreture;
    }

    @Override
    protected void findViewId(View view) {
        super.findViewId(view);
        tempretureTableView = ViewUtil.$(view, R.id.tempretureTableView);
    }


    @Override
    protected void initData() {
        super.initData();
        TempretureInfos infos = new TempretureInfos("肝胆外科", "03", "王三二", "82", "女", "201537746", "2016-06-07");
        List<TempretureDay> tempretureDayList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            TempretureDay tempretureDay = new TempretureDay();
            tempretureDay.setDate("2016.10.0" + i);
            tempretureDayList.add(tempretureDay);
        }
        tempretureTableView.setData(infos, tempretureDayList);
    }
}
