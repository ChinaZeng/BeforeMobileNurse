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
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by zzw on 2016/10/13.
 * 描述:体温单
 */

public class TemperatureFragment extends BaseFragment implements TempretureTableView.OnLoadOk {

    private static final String TAG = "TemperatureFragment";

    private TempretureTableView tempretureTableView;
    private TempretureLayout tempretureLayout;
    private String path;
    private Boolean isFirst;

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
        tempretureLayout = ViewUtil.$(view, R.id.tempretureLayout);
        tempretureLayout.setOnClickListener(this);
//        tempretureTableView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        addData();
    }

    private void addData() {
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
        tempretureTableView = null;
        tempretureTableView = new TempretureTableView(getActivity());
        isFirst = true;
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


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tempretureLayout:
                if (!isFirst) {
                    PhotoView imageView = new PhotoView(getContext());
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    imageView.setImageBitmap(createViewBitmap(tempretureTableView));
                    imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                        @Override
                        public void onPhotoTap(View view, float x, float y) {
                            addData();
                        }
                    });
                    tempretureLayout.removeAllViews();
                    tempretureLayout.addView(imageView);
                }
                break;
        }
    }

    private Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * 图片保存
     *
     * @param bm   保存的图片
     * @param path 路径
     * @param name 名字
     * @return
     */
    private boolean saveBitmap(Bitmap bm, String path, String name) {

        File filePath = new File(path);
        if (!filePath.exists())
            filePath.mkdirs();
        String fileN = path + File.separatorChar + name;
        File f = new File(fileN);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
        }
        return true;

    }

    @Override
    public void loadOk(View view) {
        if (isFirst) {
            isFirst = false;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    String path = Environment.getExternalStorageDirectory() + File.separator + "MobileNurse";
//                    String name = "Temperature.jpeg";
//                    saveBitmap(createViewBitmap(tempretureTableView), path, name);
//                }
//            }).start();
        }


    }
}
