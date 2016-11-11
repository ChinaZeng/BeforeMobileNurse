package com.shine.mobilenurse.function.temperature;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.TimedMetaData;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureDay;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureInfos;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.function.common.ImageActivity;
import com.shine.mobilenurse.utils.LogPrint;
import com.shine.mobilenurse.utils.TDUtils;
import com.shine.mobilenurse.utils.ViewUtil;
import com.shine.mobilenurse.view.TempretureLayout;
import com.shine.mobilenurse.view.TempretureTableView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;


/**
 * Created by zzw on 2016/10/13.
 * 描述:体温单
 */

public class TemperatureFragment extends BaseFragment {

    private static final String TAG = "TemperatureFragment";

//    private TempretureTableView tempretureTableView;
    private TempretureLayout tempretureLayout;
    private String path;

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
//        tempretureTableView = ViewUtil.$(view, R.id.tempretureTableView);
        tempretureLayout=ViewUtil.$(view,R.id.tempretureLayout);
        tempretureLayout.setOnClickListener(this);
//        tempretureTableView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        addData();
    }

    private void addData(){
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
        tempretureLayout.removeAllViews();
        TempretureTableView tempretureTableView=new TempretureTableView(getActivity());
        tempretureTableView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1200));
        tempretureTableView.setName("达州市中西医院结合医院");
        tempretureTableView.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        tempretureTableView.setNameTextColor(Color.rgb(0,185,169));
        tempretureTableView.setData(infos, tempretureDayList);
        tempretureLayout.addView(tempretureTableView);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            addData();
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tempretureLayout:

                break;
        }
    }

    private Bitmap createViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }


    /**
     * 图片保存
     * @param bm 保存的图片
     * @param path 路径
     * @param name 名字
     * @return
     */
    private boolean saveBitmap(Bitmap bm,String path,String name)  {

        File filePath = new File(path);
        if (!filePath.exists())
            filePath.mkdirs();

        File f=new File(path,name);
        FileOutputStream out=null;
        try {
            out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(out!=null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
        }

        return true;

    }

}
