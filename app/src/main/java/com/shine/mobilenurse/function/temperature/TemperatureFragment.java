package com.shine.mobilenurse.function.temperature;

import android.media.TimedMetaData;
import android.os.Handler;
import android.view.View;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureDay;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureInfos;
import com.shine.mobilenurse.function.UI;
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
            int count = 6;//和View中保持一致
            TempretureDay.TimeData[] timeDatas = new TempretureDay.TimeData[count];
            for (int j = 0; j < count; j++) {
                TempretureDay.TimeData timeData = null;
                if (j % count == 0) {
                    timeData = tempretureDay.new TimeData(2, 85, 35.0f, 80);
                } else if (j % count == 1) {
                    timeData = tempretureDay.new TimeData(6, 100, 37.0f, 70);
                } else if (j % count == 2) {
                    timeData = tempretureDay.new TimeData(9, 130, 39.0f, 190);
                } else if (j % count == 3) {
                    timeData = tempretureDay.new TimeData(15, 90, 37.0f, 80);
                } else if (j % count == 4) {
                    timeData = tempretureDay.new TimeData(18, 80, 35.0f, 70);
                } else if (j % count == 5) {
                    timeData = tempretureDay.new TimeData(23, 100, 40.0f, 60);
                }

                timeDatas[j] = timeData;
            }
            tempretureDay.setTimeDatas(timeDatas);
        }
        tempretureTableView.setData(infos, tempretureDayList);
    }

}
