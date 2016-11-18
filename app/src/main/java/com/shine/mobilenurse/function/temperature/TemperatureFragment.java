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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.base.BaseFragment;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureDay;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureInfos;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.utils.ViewUtil;
import com.shine.mobilenurse.view.TempretureLayout;
import com.shine.mobilenurse.view.TempretureTableView;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.IPhotoView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by zzw on 2016/10/13.
 * 描述:体温单
 */

public class TemperatureFragment extends BaseFragment implements TempretureTableView.OnLoadOk, TempretureLayout.OnDoubleClickListener {

    private static final String TAG = "TemperatureFragment";

    private TempretureTableView tempretureTableView;
    private TempretureLayout tempretureLayout;
    private Boolean isLoadOk;

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
        tempretureLayout = ViewUtil.$(view, R.id.tempretureLayout);
        tempretureLayout.setOnDoubleClickListener(this);
//        tempretureLayout.setOnClickListener(this);//为了触发双击;
    }


    @Override
    protected void initTitle() {
        setTitle("体温单");
    }

    @Override
    protected void initData() {
        super.initData();
        addData();
    }

    private void addData() {
        TempretureInfos infos = new TempretureInfos("肝胆外科", "03", "王三二", "82", "女", "201537746", "2016-06-07");
        List<TempretureDay> tempretureDayList = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            TempretureDay tempretureDay = new TempretureDay();
            tempretureDay.setDate("2016.10.0" + i);
            tempretureDayList.add(tempretureDay);
            int count = 6;//和设置View中的dayItemTimeCount保持一致
            TempretureDay.TimeData[] timeDatas = new TempretureDay.TimeData[count];
            for (int j = 0; j < count; j++) {
                TempretureDay.TimeData timeData = null;
                if (j % count == 0) {
                    timeData = tempretureDay.new TimeData(2, 85, 85, 40.5f, 34.0f, 35.0f, 36.0f, 90, "");
                } else if (j % count == 1) {
                    timeData = tempretureDay.new TimeData(8, 95, 95, 40.5f, 36.0f, 37.0f, 38.0f, 80, "");
                } else if (j % count == 2) {
                    timeData = tempretureDay.new TimeData(10, 105, 105, 40.5f, 38.0f, 39.0f, 40.0f, 70, "");
                } else if (j % count == 3) {
                    timeData = tempretureDay.new TimeData(15, 85, 85, 40.5f, 36.0f, 37.0f, 39.0f, 80, "");
                } else if (j % count == 4) {
                    timeData = tempretureDay.new TimeData(20, 95, 95, 40.5f, 35.0f, 36.5f, 38.0f, 100, "");
                } else if (j % count == 5) {
                    timeData = tempretureDay.new TimeData(23, 90, 90, 40.5f, 34.5f, 36.5f, 36.5f, 90, "");
                }

                timeDatas[j] = timeData;
            }
            tempretureDay.setTimeDatas(timeDatas);
        }
        tempretureTableView = null;
        tempretureTableView = new TempretureTableView(getContext());
        isLoadOk = false;
        tempretureTableView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1200));
        tempretureTableView.setName("达州市中西医院结合医院");
        tempretureTableView.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        tempretureTableView.setNameTextColor(Color.rgb(0, 185, 169));
        tempretureTableView.setData(infos, tempretureDayList);
        tempretureTableView.setOnLoadOk(this);
        tempretureLayout.removeAllViews();
        tempretureLayout.addView(tempretureTableView);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            addData();
        }
    }


    private Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    @Override
    public void loadOk(View view) {
        if (!isLoadOk) {
            isLoadOk = true;
            //重新摆放位置
            float h = tempretureTableView.getTempH();
            ViewGroup.LayoutParams layoutParams = tempretureTableView.getLayoutParams();
            layoutParams.width = LinearLayoutCompat.LayoutParams.MATCH_PARENT;
            layoutParams.height = (int) h + 20;
            tempretureTableView.setLayoutParams(layoutParams);
            UI.showToast(getContext(), "温馨提示:双击可放大");
        }
    }

    @Override
    public void OnDoubleClick(View view) {
        if (isLoadOk) {
            final PhotoView imageView = new PhotoView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageBitmap(createViewBitmap(tempretureTableView));
            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    imageView.setScale(IPhotoView.DEFAULT_MIN_SCALE);
                }
            });
            tempretureLayout.removeAllViews();
            tempretureLayout.addView(imageView);
        }
    }
}
