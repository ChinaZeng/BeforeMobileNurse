package com.shine.mobilenurse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureInfos;
import com.shine.mobilenurse.entity.tempretureEntity.TempretureDay;
import com.shine.mobilenurse.utils.TDUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2016/10/11.
 * 描述:
 */

public class TempretureTableView extends View {

    private OnLoadOk onLoadOk;


    private Paint paint;
    private float screenW;
    private float screenH;

    private float padTop;
    private float padLeft;
    private float padRight;
    private float padBottom;

    private final float TABLE_PADDING_TOP_AND_BOTTOM = 6.0f;
    private final int LOGO_W_AND_H = 50;

    //当前绘制到的高度
    private float tempH;

    //上方医院logo
    private Drawable logo;
    //医院名字
    private String name;
    //logo和name之间的间距
    private float logoAndNamePadding;
    //name的文字大小
    private float nameTextSize;
    //name的文字颜色
    private int nameTextColor;

    //个人信息
    private TempretureInfos infos;
    //个人信息栏字体大小
    private float infosTextSize;
    //和上方logo栏的间距
    private float infoAndLogoPadding;
    //个人信息栏字体颜色
    private int infosTextColor;

    //表格数据
    private List<TempretureDay> tempretureDayList;
    //每一天在屏幕上所占的宽度
    float dayW;
    //表格和上方信息栏的间距
    private float tabAndInfoPadding;
    //表格一般的文字大小
    private float tabCommonFontSize;
    //表格一般的文字颜色
    private int tabCommonFontColor;
    //呼吸分为多少段
    private int bloodsCount;

    //一个时间段item的宽度
    private float timeItemW;
    //一天时间段数量
    private int dayItemTimeCount;
    //时间段间隔
    private int timeLag;
    //时间开始时段
    private int startTime;
    //时间结束时段
    private int endTime;
    //脉搏开始提示数值
    private int maiBoStart;
    //脉搏结束提示数值
    private int maiBoEnd;
    //脉搏和时间每一个单元之间分为多少格 按照温度为1单位
    private int maiBoAndTempGeCount;
    //体温开始提示数值
    private int tempStart;
    //体温结束提示数值
    private int tempEnd;

    //是否显示体温
    private Boolean isShowTempre;
    //是否显示口温
    private Boolean isShowMouthTempre;
    //是否显示腋温
    private Boolean isShowAxillaryTempre;
    //是否显示肛温
    private Boolean isShowAnalTempre;
    //是否显示脉搏
    private Boolean isShowMaiBo;

    //记录所有时间段的tabale信息 里面一个list代表一天的tab信息
    private List<List<Table>> allDayTableList = new ArrayList<>();

    //记录所有时间段的口温信息 里面一个list代表一天的口温
    private List<List<FloatPoint>> allMouthTemprePointList = new ArrayList<>();
    //记录所有时间段的腋温信息 里面一个list代表一天的腋温
    private List<List<FloatPoint>> allAxillaryTemprePointList = new ArrayList<>();
    //记录所有时间段的肛温信息 里面一个list代表一天的肛温
    private List<List<FloatPoint>> allAnalTemprePointList = new ArrayList<>();
    //记录所有时间段的体温信息 里面一个list代表一天的体温
    private List<List<FloatPoint>> allTemprePointList = new ArrayList<>();
    //脉搏point集合
    //记录所有时间段的温度信息 里面一个list代表一天的温度
    private List<List<FloatPoint>> allMaiBoPointList = new ArrayList<>();

    //脉搏提示
    private String[] maiBoHintStrings;

    //血压提示
    private String[] tempHintStrings;

    public TempretureTableView(Context context) {
        this(context, null);
    }

    public TempretureTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempretureTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs);
        init(context);
    }

    public interface OnLoadOk {
        void loadOk(View view);
    }

    public void setOnLoadOk(OnLoadOk onLoadOk) {
        this.onLoadOk = onLoadOk;
    }

    private void init(Context context) {
        int[] is = TDUtils.getScreenWAndH(context);
        screenW = is[0];
        screenH = is[1];
        paint = new Paint();
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TempretureTableView);
        padLeft = a.getDimensionPixelSize(R.styleable.TempretureTableView_padLeft, 5);
        padRight = a.getDimensionPixelSize(R.styleable.TempretureTableView_padRight, 5);
        padBottom = a.getDimensionPixelSize(R.styleable.TempretureTableView_padBottom, 10);
        padTop = a.getDimensionPixelSize(R.styleable.TempretureTableView_padTop, 10);
        logo = a.getDrawable(R.styleable.TempretureTableView_logo);
        if (logo == null)
            logo = context.getResources().getDrawable(R.mipmap.ic_launcher);
        name = a.getString(R.styleable.TempretureTableView_name);
        logoAndNamePadding = a.getDimension(R.styleable.TempretureTableView_logoAndNamePadding, 5.0f);
        nameTextSize = a.getDimensionPixelSize(R.styleable.TempretureTableView_nameTextSize, 18);
        nameTextColor = a.getColor(R.styleable.TempretureTableView_nameTextColor, Color.BLACK);
        infosTextSize = a.getDimensionPixelSize(R.styleable.TempretureTableView_infosTextSize, 18);
        infosTextColor = a.getColor(R.styleable.TempretureTableView_infosTextColor, Color.BLACK);
        infoAndLogoPadding = a.getDimension(R.styleable.TempretureTableView_infoAndLogoPadding, 5);
        tabAndInfoPadding = a.getDimension(R.styleable.TempretureTableView_tabAndInfoPadding, 5);
        tabCommonFontSize = a.getDimensionPixelSize(R.styleable.TempretureTableView_tabCommonFontSize, 16);
        tabCommonFontColor = a.getColor(R.styleable.TempretureTableView_tabCommonFontColor, Color.BLACK);
        bloodsCount = a.getInteger(R.styleable.TempretureTableView_bloodsCount, 2);
        timeLag = a.getInteger(R.styleable.TempretureTableView_timeLag, 4);
        if (timeLag == 0 || timeLag > 24)
            timeLag = 4;
        startTime = a.getInteger(R.styleable.TempretureTableView_startTime, 0);
        endTime = a.getInteger(R.styleable.TempretureTableView_endTime, 24);
        maiBoStart = a.getInteger(R.styleable.TempretureTableView_maiBoStart, 20);
        maiBoEnd = a.getInteger(R.styleable.TempretureTableView_maiBoEnd, 180);
        tempStart = a.getInteger(R.styleable.TempretureTableView_tempStart, 34);
        tempEnd = a.getInteger(R.styleable.TempretureTableView_tempEnd, 42);
        maiBoAndTempGeCount = a.getInteger(R.styleable.TempretureTableView_maiBoAndTempGeCount, 5);

        int count = tempEnd - tempStart;
        maiBoHintStrings = new String[count];
        tempHintStrings = new String[count];
        int c2 = (maiBoEnd - maiBoStart) / count;
        for (int i = 0; i < count; i++) {
            tempHintStrings[i] = tempEnd - i + "";
            maiBoHintStrings[i] = maiBoEnd - i * c2 + "";
        }

        isShowTempre = a.getBoolean(R.styleable.TempretureTableView_isShowTempre, true);
        isShowAxillaryTempre = a.getBoolean(R.styleable.TempretureTableView_isShowAxillaryTempre, true);
        isShowMouthTempre = a.getBoolean(R.styleable.TempretureTableView_isShowMouthTempre, true);
        isShowAnalTempre = a.getBoolean(R.styleable.TempretureTableView_isShowAnalTempre, true);
        isShowMaiBo = a.getBoolean(R.styleable.TempretureTableView_isShowMaiBo, true);

        a.recycle();
        setBackgroundColor(Color.WHITE);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int specHMode = MeasureSpec.getMode(heightMeasureSpec);
//
//        float specHSize = MeasureSpec.getSize(heightMeasureSpec);
//        switch (specHMode) {
//            case MeasureSpec.UNSPECIFIED:
//            case MeasureSpec.AT_MOST:
//                if (tempretureDayList != null && tempretureDayList.size() != 0) {
//                    Paint paint = new Paint();
//                    paint.setTextSize(tabCommonFontSize);
//                    float infoH=meauTextHeight(paint,"科室");
//                    float comTableH=meauTextHeight(paint,"日期")+2*TABLE_PADDING_TOP_AND_BOTTOM;
//                    specHSize=padTop+LOGO_W_AND_H+infoH+infoAndLogoPadding+
//                            comTableH*13+timeItemW*(tempEnd-tempStart)*maiBoAndTempGeCount;
//                }
//
//                break;
//
//            case MeasureSpec.EXACTLY:
//
//                break;
//        }
//        setMeasuredDimension((int) screenW, (int) specHSize);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTop(canvas);
        drawInfos(canvas);
        drawTable(canvas);
    }


    /**
     * 第一步:画logo和名字
     *
     * @param canvas
     */
    private void drawTop(Canvas canvas) {
        tempH = 0.0f;
        //1.将Drawable装换为Bitmap
        BitmapDrawable bd = (BitmapDrawable) logo;
        Bitmap logoBitmap = bd.getBitmap();
        //2.得到Bitmap的宽高
//        int logoBW = logoBitmap.getWidth();
//        int logoBH = logoBitmap.getHeight();
        int logoBW = LOGO_W_AND_H;
        int logoBH = LOGO_W_AND_H;

        //3.得到医院名称name的宽高
        paint.setTextSize(nameTextSize);
        paint.setColor(nameTextColor);
        float nameW = meauTextWidth(paint, name);
        float nameH = meauTextHeight(paint, name);

        //4.设置左右padding,使文字和logo居中显示
        float paddingLR = (screenW - logoBW - nameW - logoAndNamePadding - padLeft - padRight) / 2;

        //5.画logo
        Paint logoPaint = new Paint();
        logoPaint.setAntiAlias(true);
//        canvas.drawBitmap(logoBitmap, paddingLR + padLeft, padTop, logoPaint);
        canvas.drawBitmap(logoBitmap, null, new Rect((int) (paddingLR + padLeft), (int) padTop,
                (int) (paddingLR + padLeft + logoBW), (int) (padTop + logoBH)), logoPaint);
        //6.画文字
        canvas.drawText(name, paddingLR + padLeft + logoBW + logoAndNamePadding, padTop + nameH, paint);
        String tiwendan = "体温单";
        float tiwendanW = meauTextWidth(paint, tiwendan);
        //一个文字的宽度
        float aW = tiwendanW / 3;
        //图标的高度和医院高度差,为了使体温单三个字在下方垂直居中显示
        float x = logoBH - nameH;
        //体温单每个文字之间的间隔
        float pad = (nameW - tiwendanW) / 4;
        String[] s = new String[]{"体", "温", "单"};
        //为了使体温单三个字在医院名字下方居中显示
        float startY = x / 2 + padTop + nameH + aW / 2;
        for (int i = 0; i < s.length; i++) {
            canvas.drawText(s[i], paddingLR + padLeft + logoBW + logoAndNamePadding + pad * (i + 1) + aW * i, startY, paint);
        }

        tempH += (padTop + logoBH);
    }

    /**
     * 第二步:画个人信息栏
     *
     * @param canvas
     */
    private void drawInfos(Canvas canvas) {
        if (infos == null)
            return;
        paint.setColor(infosTextColor);
        paint.setTextSize(infosTextSize);

        String department = "科室: " + infos.getDepartment();
        String bedsNums = "床号: " + infos.getBedNum();
        String name = "姓名: " + infos.getName();
        String age = "年龄: " + infos.getAge();
        String medicalRecordsNum = "住院病历号: " + infos.getMedicalRecordsNum();
        String inToHospitalDate = "入院日期: " + infos.getInToHospitalDate();
        String[] infosArray = new String[]{department, bedsNums, name, age, medicalRecordsNum, inToHospitalDate};
        StringBuilder sb = new StringBuilder();
        for (String t : infosArray) {
            sb.append(t);
        }
        //要画的总文字
        String s = sb.toString();
        //测量出总的文字宽度
        float sw = meauTextWidth(paint, s);
        //为了使文字不超出屏幕
//        while (sw > screenW) {
//            paint.setTextSize(infosTextSize - 1);
//            sw = meauTextWidth(paint, s);
//        }
        //测量每个字段之间的间隔宽度
        float pad = (screenW - sw) / infosArray.length;
        //测量出一个空格的宽度
        float aw = meauTextWidth(paint, "a");
        //算出有多少个空格
        int a = (int) (pad / aw);
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < infosArray.length; i++) {
            sb1.append(infosArray[i]);
            if (i != infosArray.length - 1) {
                for (int j = 0; j < a; j++) {
                    sb1.append(" ");
                }
            }
        }
        //加上空格后的字符串
        String s1 = sb1.toString();
        float sh = meauTextHeight(paint, s1);
        canvas.drawText(s1, padLeft, tempH + sh + infoAndLogoPadding, paint);
        tempH += (sh + infoAndLogoPadding);
    }

    /**
     * 第三部:画后面的数据
     *
     * @param canvas
     */
    private void drawTable(Canvas canvas) {
        if (tempretureDayList == null || tempretureDayList.size() == 0)
            return;
        drawTableList(canvas, comDataList());
        drawTableList(canvas, comInToHospitalNumList());
        drawTableList(canvas, comcutNumList());
        drawMid(canvas);
        drawTableList(canvas, comBraveList());
        drawTableList(canvas, comBloodsList());
        drawTableList(canvas, comInToNumList());
        drawTableList(canvas, comOutNumList());
        drawTableList(canvas, comShitNumByDayList());
        drawTableList(canvas, comHeightList());
        drawTableList(canvas, comWeightList());
        drawTableList(canvas, comMaiXiangList());
        drawTableList(canvas, comSheTaiList());

        if (onLoadOk != null)
            onLoadOk.loadOk(this);
    }

    /**
     * 画中间时间段部分
     *
     * @param canvas
     */
    private void drawMid(Canvas canvas) {
        drawTableList(canvas, comTimeItemData());
        drawMidTable(canvas);
        comTempAndMaiBoPointList();
        drawMouthTempre(canvas);
        drawAxillaryTempre(canvas);
        drawAnalTempre(canvas);
        drawMaiBo(canvas);
//        drawTempre(canvas);
    }

    /**
     * 画口温曲线
     *
     * @param canvas
     */
    private void drawMouthTempre(Canvas canvas) {
        if (!isShowMouthTempre)
            return;
        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        boolean t = false;
        for (int i = 0; i < allMouthTemprePointList.size(); i++) {
            List<FloatPoint> points = allMouthTemprePointList.get(i);
            if (points != null && points.size() > 0) {
                for (int j = 0; j < points.size(); j++) {
                    FloatPoint point = points.get(j);
                    if (j == 0 && !t) {
                        path.moveTo(point.getX(), point.getY());
                        t = true;
                    } else {
                        path.lineTo(point.getX(), point.getY());
                    }
                    canvas.drawCircle(point.getX(), point.getY(), 3, paint);
                }
            }
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path.close();
    }


    /**
     * 画提体温曲线
     *
     * @param canvas
     */
    private void drawTempre(Canvas canvas) {
        if (!isShowTempre)
            return;

        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        boolean t = false;
        for (int i = 0; i < allTemprePointList.size(); i++) {
            List<FloatPoint> points = allTemprePointList.get(i);
            if (points != null && points.size() > 0) {
                for (int j = 0; j < points.size(); j++) {
                    FloatPoint point = points.get(j);
                    if (j == 0 && !t) {
                        path.moveTo(point.getX(), point.getY());
                        t = true;
                    } else {
                        path.lineTo(point.getX(), point.getY());
                    }
                    canvas.drawCircle(point.getX(), point.getY(), 3, paint);
                }
            }
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path.close();
    }


    /**
     * 画腋温曲线
     *
     * @param canvas
     */
    private void drawAxillaryTempre(Canvas canvas) {

        if (!isShowAxillaryTempre)
            return;

        Paint paint = new Paint();
        Path path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        boolean t = false;
        for (int i = 0; i < allAxillaryTemprePointList.size(); i++) {
            List<FloatPoint> points = allAxillaryTemprePointList.get(i);
            if (points != null && points.size() > 0) {
                for (int j = 0; j < points.size(); j++) {
                    FloatPoint point = points.get(j);
                    if (j == 0 && !t) {
                        path.moveTo(point.getX(), point.getY());
                        t = true;
                    } else {
                        path.lineTo(point.getX(), point.getY());
                    }
                    canvas.drawLine(point.getX() - 3, point.getY() - 3, point.getX() + 3, point.getY() + 3, paint);
                    canvas.drawLine(point.getX() - 3, point.getY() + 3, point.getX() + 3, point.getY() - 3, paint);
                }
            }
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path.close();
    }


    /**
     * 画肛温曲线
     *
     * @param canvas
     */
    private void drawAnalTempre(Canvas canvas) {

        if (!isShowAnalTempre)
            return;

        Paint paint = new Paint();
        Path path = new Path();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        boolean t = false;
        for (int i = 0; i < allAnalTemprePointList.size(); i++) {
            List<FloatPoint> points = allAnalTemprePointList.get(i);
            if (points != null && points.size() > 0) {
                for (int j = 0; j < points.size(); j++) {
                    FloatPoint point = points.get(j);
                    if (j == 0 && !t) {
                        path.moveTo(point.getX(), point.getY());
                        t = true;
                    } else {
                        path.lineTo(point.getX(), point.getY());
                    }
                    canvas.drawCircle(point.getX(), point.getY(), 3, paint);
                }
            }
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path.close();
    }

    /**
     * 画脉搏曲线
     *
     * @param canvas
     */
    private void drawMaiBo(Canvas canvas) {

        if (!isShowMaiBo)
            return;

        Paint paint = new Paint();
        Path path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        boolean t = false;
        for (int i = 0; i < allMaiBoPointList.size(); i++) {
            List<FloatPoint> points = allMaiBoPointList.get(i);
            if (points != null && points.size() > 0) {
                for (int j = 0; j < points.size(); j++) {
                    FloatPoint point = points.get(j);
                    if (j == 0 && !t) {
                        path.moveTo(point.getX(), point.getY());
                        t = true;
                    } else {
                        path.lineTo(point.getX(), point.getY());
                    }
                    canvas.drawCircle(point.getX(), point.getY(), 3, paint);
                }
            }
        }
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path.close();
    }


    /**
     * 计算出要画的脉搏和温度 点位置
     */
    private void comTempAndMaiBoPointList() {

        allTemprePointList.clear();
        allMouthTemprePointList.clear();
        allMaiBoPointList.clear();
        allAnalTemprePointList.clear();
        allAxillaryTemprePointList.clear();

        for (int i = 0; i < tempretureDayList.size(); i++) {
            List<Table> dayTables = allDayTableList.get(i);
            float startX = dayTables.get(0).getX();
            float startY = dayTables.get(0).getY() + dayTables.get(0).getH();
            //一个时间段一格多宽
            float taw = dayW / dayItemTimeCount;
            TempretureDay.TimeData[] timeDatas = tempretureDayList.get(i).getTimeDatas();
            if (timeDatas != null) {
                List<FloatPoint> tempreDaylist = new ArrayList<>();
                List<FloatPoint> mouthTempreDaylist = new ArrayList<>();//一天的口温
                List<FloatPoint> axillaryTempreDayList = new ArrayList<>();//一天的腋温
                List<FloatPoint> AnalTempreDayList = new ArrayList<>();//一天的肛温
                List<FloatPoint> maiBoDaylist = new ArrayList<>();//一天的脉搏
                //每一格代表的温度值
                float a = 1.0f / maiBoAndTempGeCount;
                //每一格代表的脉搏值
                float b = (float) (maiBoEnd - maiBoStart) / (float) ((tempEnd - tempStart) * maiBoAndTempGeCount);
                //每一格代表的时间值
                float c = (float) (endTime - startTime) / (float) (dayItemTimeCount);
                for (TempretureDay.TimeData timeData : timeDatas) {
                    if (timeData != null) {
                        int timeNum = timeData.getTimeNum();
                        if (timeNum != 0) {
                            //体温和脉搏的X坐标
                            float x = startX + (timeNum - startTime) / c * taw;

                            //计算体温温的的Y点坐标
                            if (timeData.getTemperature() != 0 && isShowTempre) {
                                //（最大温度-当前温度）*
                                float my = startY + (((tempEnd - timeData.getTimeNum()) / a) * timeItemW);
                                FloatPoint point = new FloatPoint(x, my);
                                tempreDaylist.add(point);
                            }


                            //计算口温的的Y点坐标
                            if (timeData.getMouthTemperature() != 0 && isShowMouthTempre) {
                                //（最大温度-当前温度）*
                                float my = startY + (((tempEnd - timeData.getMouthTemperature()) / a) * timeItemW);
                                FloatPoint point = new FloatPoint(x, my);
                                mouthTempreDaylist.add(point);
                            }

                            //计算腋温的的Y点坐标
                            if (timeData.getAxillaryTemperature() != 0 && isShowAxillaryTempre) {
                                //（最大温度-当前温度）*
                                float my = startY + (((tempEnd - timeData.getAxillaryTemperature()) / a) * timeItemW);
                                FloatPoint point = new FloatPoint(x, my);
                                axillaryTempreDayList.add(point);
                            }

                            //计算肛温的的Y点坐标
                            if (timeData.getAnalTemperature() != 0 && isShowAnalTempre) {
                                //（最大温度-当前温度）*
                                float my = startY + (((tempEnd - timeData.getAnalTemperature()) / a) * timeItemW);
                                FloatPoint point = new FloatPoint(x, my);
                                AnalTempreDayList.add(point);
                            }

                            //计算脉搏的Y点坐标
                            if (timeData.getPulse() != 0 && isShowMaiBo) {
                                float y = startY + ((maiBoEnd - timeData.getPulse()) / b) * timeItemW;
                                FloatPoint point = new FloatPoint(x, y);
                                maiBoDaylist.add(point);
                            }
                        }
                    }
                }
                allTemprePointList.add(tempreDaylist);
                allMouthTemprePointList.add(mouthTempreDaylist);
                allAxillaryTemprePointList.add(axillaryTempreDayList);
                allAnalTemprePointList.add(AnalTempreDayList);
                allMaiBoPointList.add(maiBoDaylist);
            }
        }
    }


    /**
     * 画中间表格及hint
     *
     * @param canvas
     */
    private void drawMidTable(Canvas canvas) {
        float midTabH = (tempEnd - tempStart) * maiBoAndTempGeCount * timeItemW;
        //画竖线:
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawLine(padLeft, tempH, padLeft, tempH + midTabH, paint);
        for (int i = 0; i < allDayTableList.size(); i++) {
            List<Table> dayTabList = allDayTableList.get(i);
            for (int j = 0; j < dayTabList.size(); j++) {
                Table table = dayTabList.get(j);
                if (j == 0) {
//                    paint.setColor(Color.RED);
                    paint.setColor(Color.BLACK);
                } else {
                    paint.setColor(Color.GRAY);
                }
                canvas.drawLine(table.getX(), table.getY() + table.getH(), table.getX(), table.getY() + table.getH() + midTabH, paint);
                //画最后一根线
                if (j == dayTabList.size() - 1) {
//                    paint.setColor(Color.RED);
                    canvas.drawLine(table.getX() + table.getW(), table.getY() + table.getH(), table.getX() + table.getW(), table.getY() + table.getH() + midTabH, paint);
                }
            }
        }

        //记录中间粗线的Y坐标
        Float[] hintsY = new Float[tempEnd - tempStart];
        //画横线
        int t = 1;
        hintsY[0] = tempH;
        for (int i = 1; i < (tempEnd - tempStart) * maiBoAndTempGeCount; i++) {
            float y = tempH + i * timeItemW;
            if (i % maiBoAndTempGeCount == 0) {
                paint.setColor(Color.BLACK);
                hintsY[t] = y;
                t++;
            } else {
                paint.setColor(Color.GRAY);
            }
            canvas.drawLine(dayW + padLeft, y, screenW - padRight, y, paint);
        }

        //画提示
        float hitMidX = padLeft + dayW / 2;
        //画提示中间线
        paint.setColor(Color.BLACK);
        canvas.drawLine(hitMidX, tempH, hitMidX, tempH + midTabH, paint);

        //画提示文字
        paint.setTextSize(tabCommonFontSize);
        float sh = meauTextHeight(paint, "8");
        for (int i = 1; i < tempHintStrings.length; i++) {
            String maiboS = maiBoHintStrings[i];
            String temprS = tempHintStrings[i];
            float y = hintsY[i];
            float startX;
            startX = padLeft + (dayW / 2 - meauTextWidth(paint, maiboS)) / 2;
            canvas.drawText(maiboS, startX, y + sh / 2, paint);
            startX = padLeft + dayW / 2 + (dayW / 2 - meauTextWidth(paint, temprS)) / 2;
            canvas.drawText(temprS, startX, y + sh / 2, paint);
        }
        float th_1 = tempH;
        //画脉搏 词
        String maibos = "脉搏";
        float y = meauTextHeight(paint, maibos);
        float x = meauTextWidth(paint, maibos);
        canvas.drawText(maibos, padLeft + (dayW / 2 - x) / 2, th_1 + y, paint);
        th_1 += y;

        //画体温 词
        String tiwen = "体温";
        float th_2 = tempH;
        y = meauTextHeight(paint, tiwen);
        x = meauTextWidth(paint, tiwen);
        canvas.drawText(tiwen, padLeft + dayW / 2 + (dayW / 2 - x) / 2, th_2 + y, paint);
        th_2 += y;


        paint.setTextSize(tabCommonFontSize * 2 / 3);
        //画(次/分)
        String maiBoDanWei = "(次/分)";
        y = meauTextHeight(paint, maiBoDanWei);
        x = meauTextWidth(paint, maiBoDanWei);
        canvas.drawText(maiBoDanWei, padLeft + (dayW / 2 - x) / 2, th_1 + y + 2, paint);
        th_1 += y + 2;

        //画(℃)
        String sheshidu = "(℃)";
        y = meauTextHeight(paint, sheshidu);
        x = meauTextWidth(paint, sheshidu);
        canvas.drawText(sheshidu, padLeft + dayW / 2 + (dayW / 2 - x) / 2, th_2 + y + 2, paint);
        th_2 += y + 2;

        paint.setTextSize(tabCommonFontSize);
        //画脉搏第一个数值
        String maiBoHint0 = maiBoHintStrings[0];
        y = meauTextHeight(paint, maiBoHint0);
        x = meauTextWidth(paint, maiBoHint0);
        canvas.drawText(maiBoHint0, padLeft + (dayW / 2 - x) / 2, th_1 + y + 2, paint);

        //画体温第一个数值
        String tempreHint0 = tempHintStrings[0];
        y = meauTextHeight(paint, tempreHint0);
        x = meauTextWidth(paint, tempreHint0);
        canvas.drawText(tempreHint0, padLeft + dayW / 2 + (dayW / 2 - x) / 2, th_2 + y + 2, paint);


        tempH += midTabH;
    }

    /**
     * 计算每个时间段呼吸量  timeDatas和对应的时间段数量count要一致
     *
     * @return
     */
    private List<Table> comBraveList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "呼吸(次/分)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "呼吸(次/分)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));

        for (int i = 0; i < tempretureDayList.size(); i++) {
            float startX = padLeft + (i + 1) * dayW;
            TempretureDay.TimeData[] timeDatas = tempretureDayList.get(i).getTimeDatas();

            if (timeDatas == null)
                timeDatas = new TempretureDay.TimeData[dayItemTimeCount];

            if (timeDatas.length < dayItemTimeCount)
                return list;


            for (int j = 0; j < timeDatas.length; j++) {
                Table table = null;
                Table.TxtType type;
                if (j % 2 == 0) {
                    type = Table.TxtType.CENTENT_TOP;
                } else {
                    type = Table.TxtType.CENTENT_BOTTOM;
                }
                String s = "";
                TempretureDay.TimeData data = timeDatas[j];
                if (data != null) {
                    s += timeDatas[j].getBraveNum();
                }
                if (j == timeDatas.length - 1) {
                    table = new Table(startX + timeItemW * j, tempH, timeItemW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, Color.RED
                            , tabCommonFontSize * 2 / 3, s, type, 0, Color.RED
                            , Color.BLACK, Color.BLACK);
                } else {
                    table = new Table(startX + timeItemW * j, tempH, timeItemW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, Color.RED
                            , tabCommonFontSize * 2 / 3, s, type, 0, Color.BLACK
                            , Color.BLACK, Color.BLACK);
                }
                list.add(table);
            }
        }

        tempH += aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2;
        return list;
    }


    /**
     * 计算时间段的table的数据
     * dayItemTimeCount在这里面计算出  由开始时间  结束时间  还有时间间隔算出
     *
     * @return
     */
    private List<Table> comTimeItemData() {
        allDayTableList.clear();
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "时  间");
        List<Table> list = new ArrayList<>();//总的list
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "时  间", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        //根据时间间隔算出一天分为多少段
        dayItemTimeCount = (endTime - startTime) / timeLag;

        //每个时间段的宽度
        timeItemW = dayW / dayItemTimeCount;

        int[] times = new int[dayItemTimeCount];
        for (int x = 0; x < dayItemTimeCount; x++) {
            times[x] = startTime + timeLag * (x + 1);
        }


        for (int i = 0; i < tempretureDayList.size(); i++) {
            float startX = padLeft + (i + 1) * dayW;
            List<Table> dayList = new ArrayList<>();//这一天的时间段list
            for (int j = 0; j < times.length; j++) {
                Table table = null;
                if (j == times.length - 1) {
                    table = new Table(startX + timeItemW * j, tempH, timeItemW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                            , tabCommonFontSize * 2 / 3, times[j] + "", Table.TxtType.CENTER, 0, Color.RED
                            , Color.BLACK, Color.BLACK);
                } else {
                    table = new Table(startX + timeItemW * j, tempH, timeItemW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                            , tabCommonFontSize * 2 / 3, times[j] + "", Table.TxtType.CENTER, 0, Color.BLACK
                            , Color.BLACK, Color.BLACK);
                }
                list.add(table);
                dayList.add(table);
            }
            allDayTableList.add(dayList);
        }
        tempH += aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2;
        return list;
    }


    /**
     * 计算血压(自己规定段数)
     *
     * @return
     */
    private List<Table> comBloodsList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "血压(mmHg)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "血压(mmHg)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            TempretureDay.Blood[] bloods = tempretureDay.getBloods();
            float startX = padLeft + (i + 1) * dayW;
            float a = dayW / bloodsCount;//里面内容每个宽度
            for (int j = 0; j < bloodsCount; j++) {
                String s = "";
                if (bloods != null && j < bloods.length) {
                    TempretureDay.Blood blood = bloods[j];
                    if (blood != null)
                        s = blood.getHighBlood() + "/" + blood.getLowBlood();
                }
                Table table;
                if (j == bloodsCount - 1) {
                    table = new Table(startX + a * j, tempH, a, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                            , tabCommonFontSize, s, Table.TxtType.CENTER, 0, Color.RED
                            , Color.BLACK, Color.BLACK);
                } else {
                    table = new Table(startX + a * j, tempH, a, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                            , tabCommonFontSize, s, Table.TxtType.CENTER, 0, Color.BLACK
                            , Color.BLACK, Color.BLACK);
                }
                list.add(table);

            }
        }
        tempH += aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2;
        return list;
    }

    /**
     * 计算血压(根据传入的数据动态分段)
     *
     * @return
     */
    private List<Table> comBloodsList2() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "血压(mmHg)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "血压(mmHg)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            TempretureDay.Blood[] bloods = tempretureDay.getBloods();
            float startX = padLeft + (i + 1) * dayW;
            float a = dayW;//每个内容的宽度
            if (bloods != null && bloods.length > 0) {
                a = dayW / bloods.length;
            }

            String s = "";
            if (bloods == null || bloods.length == 0) {
                list.add(new Table(startX, tempH, a, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                        , tabCommonFontSize, s, Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK));
            } else {
                for (int j = 0; j < bloods.length; j++) {
                    TempretureDay.Blood blood = bloods[j];
                    if (blood != null) {
                        s = blood.getHighBlood() + "/" + blood.getLowBlood();
                    }
                    Table table;
                    if (j == bloodsCount - 1) {
                        table = new Table(startX + a * j, tempH, a, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                                , tabCommonFontSize, s, Table.TxtType.CENTER, 0, Color.RED
                                , Color.BLACK, Color.BLACK);
                    } else {
                        table = new Table(startX + a * j, tempH, a, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2, tabCommonFontColor
                                , tabCommonFontSize, s, Table.TxtType.CENTER, 0, Color.BLACK
                                , Color.BLACK, Color.BLACK);
                    }
                    list.add(table);
                }
            }
        }
        tempH += aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2;
        return list;
    }

    /**
     * 计算日期栏
     *
     * @return
     */
    private List<Table> comDataList() {
        //每一天在屏幕上占据的宽度
        dayW = (screenW - padLeft - padRight) / (tempretureDayList.size() + 1);


        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "日   期");
        List<Table> dateList = new ArrayList<>();
        dateList.add(new Table(padLeft, tempH + tabAndInfoPadding, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "日  期", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH + tabAndInfoPadding, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getDate(), Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH + tabAndInfoPadding, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getDate(), Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            dateList.add(table);
        }
        tempH += (tabAndInfoPadding + aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return dateList;
    }

    /**
     * 计算住院天数
     *
     * @return
     */
    private List<Table> comInToHospitalNumList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "住院天数");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "住院天数", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getInToHospitalNum() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getInToHospitalNum() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    /**
     * 计算出量
     *
     * @return
     */
    private List<Table> comOutNumList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "出量(ml)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "出量(ml)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getOutNum() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getOutNum() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    /**
     * 计算大便
     *
     * @return
     */
    private List<Table> comShitNumByDayList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "大便(次/日)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "大便(次/日)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getShitNumByDay() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getShitNumByDay() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    /**
     * 计算升高
     *
     * @return
     */
    private List<Table> comHeightList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "身高(cm)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "身高(cm)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getHeight() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getHeight() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    /**
     * 计算体重
     *
     * @return
     */
    private List<Table> comWeightList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "体重(cm)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "体重(cm)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getWeight() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getWeight() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    /**
     * 计算入量
     *
     * @return
     */
    private List<Table> comInToNumList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "入量(ml)");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "入量(ml)", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getInToNum() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getInToNum() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    /**
     * 计算脉象
     *
     * @return
     */
    private List<Table> comMaiXiangList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "脉象");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "脉象", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getMaiXiang() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getMaiXiang() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }


    /**
     * 计算舌苔
     *
     * @return
     */
    private List<Table> comSheTaiList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "舌苔");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "舌苔", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getSheTai() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getSheTai() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }


    /**
     * 计算手术后天数
     *
     * @return
     */
    private List<Table> comcutNumList() {
        Paint paint = new Paint();
        paint.setTextSize(tabCommonFontSize);
        float aFontH = meauTextHeight(paint, "手术后天数");
        List<Table> list = new ArrayList<>();
        list.add(new Table(padLeft, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                , tabCommonFontColor, tabCommonFontSize, "手术后天数", Table.TxtType.CENTER, Color.BLACK, Color.RED
                , Color.BLACK, Color.BLACK));
        for (int i = 0; i < tempretureDayList.size(); i++) {
            TempretureDay tempretureDay = tempretureDayList.get(i);
            Table table = null;
            if (i == tempretureDayList.size() - 1) {//最后一个具有上右下边框，右边框颜色为BLACK
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getCutNum() + "", Table.TxtType.CENTER, 0, Color.BLACK
                        , Color.BLACK, Color.BLACK);
            } else {//中间具有上右下边框，右边框颜色为RED
                table = new Table(padLeft + (i + 1) * dayW, tempH, dayW, aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2
                        , tabCommonFontColor, tabCommonFontSize, tempretureDay.getCutNum() + "", Table.TxtType.CENTER, 0, Color.RED
                        , Color.BLACK, Color.BLACK);
            }
            list.add(table);
        }
        tempH += (aFontH + TABLE_PADDING_TOP_AND_BOTTOM * 2);
        return list;
    }

    public void setTempretureInfos(TempretureInfos tempretureInfos) {
        this.infos = tempretureInfos;
//        invalidate();
    }


    public void setTempretureDay(List<TempretureDay> tempretureDayList) {
        this.tempretureDayList = tempretureDayList;
//        invalidate();
    }


    public void setData(TempretureInfos tempretureInfos, List<TempretureDay> tempretureDayList) {
        this.infos = tempretureInfos;
        this.tempretureDayList = tempretureDayList;
//        invalidate();
    }

    /**
     * 画一般的表   只有边框和文字
     *
     * @param canvas
     * @param tableList
     */
    private void drawTableList(Canvas canvas, List<Table> tableList) {

        if (tableList == null || tableList.size() == 0)
            return;

        Paint paint = new Paint();
        for (Table table : tableList) {
            //画边线
            if (table.getTopLineColor() != 0) {
                paint.setColor(table.getTopLineColor());
                canvas.drawLine(table.getX(), table.getY(), table.getX() + table.getW(), table.getY(), paint);
            }

            if (table.getBottomLineColor() != 0) {
                paint.setColor(table.getBottomLineColor());
                canvas.drawLine(table.getX(), table.getY() + table.getH(), table.getX() + table.getW(), table.getY() + table.getH(), paint);
            }

            if (table.getLeftLineColor() != 0) {
                paint.setColor(table.getLeftLineColor());
                canvas.drawLine(table.getX(), table.getY(), table.getX(), table.getY() + table.getH(), paint);
            }
            if (table.getRightLineColor() != 0) {
                paint.setColor(table.getRightLineColor());
                canvas.drawLine(table.getX() + table.getW(), table.getY(), table.getX() + table.getW(), table.getY() + table.getH(), paint);
            }

            //画文字
            paint.setTextSize(table.getFontSize());
            paint.setColor(table.getFontColor());
            float sw = meauTextWidth(paint, table.getTxt() + "");
            float sH = meauTextHeight(paint, table.getTxt() + "");
            switch (table.getTextType()) {
                case CENTENT_TOP:
                    canvas.drawText(table.getTxt() + "", table.getX() + (table.getW() - sw) / 2, table.getY() + sH + TABLE_PADDING_TOP_AND_BOTTOM, paint);
                    break;

                case CENTENT_BOTTOM:
                    canvas.drawText(table.getTxt() + "", table.getX() + (table.getW() - sw) / 2, table.getY() + table.getH() - TABLE_PADDING_TOP_AND_BOTTOM, paint);
                    break;

                case CENTER:
                    canvas.drawText(table.getTxt() + "", table.getX() + ((table.getW() - sw) / 2), table.getY() + ((table.getH() - sH) / 2) + sH, paint);
                    break;
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setTempEnd(int tempEnd) {
        this.tempEnd = tempEnd;
    }

    public void setPadTop(float padTop) {
        this.padTop = padTop;
    }

    public void setPadLeft(float padLeft) {
        this.padLeft = padLeft;
    }

    public void setPadRight(float padRight) {
        this.padRight = padRight;
    }

    public void setPadBottom(float padBottom) {
        this.padBottom = padBottom;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }

    public void setLogoAndNamePadding(float logoAndNamePadding) {
        this.logoAndNamePadding = logoAndNamePadding;
    }

    public void setNameTextSize(float nameTextSize) {
        this.nameTextSize = nameTextSize;
    }

    public void setNameTextColor(int nameTextColor) {
        this.nameTextColor = nameTextColor;
    }

    public void setInfos(TempretureInfos infos) {
        this.infos = infos;
    }

    public void setInfosTextSize(float infosTextSize) {
        this.infosTextSize = infosTextSize;
    }


    public void setInfoAndLogoPadding(float infoAndLogoPadding) {
        this.infoAndLogoPadding = infoAndLogoPadding;
    }

    public void setInfosTextColor(int infosTextColor) {
        this.infosTextColor = infosTextColor;
    }

    public void setTempretureDayList(List<TempretureDay> tempretureDayList) {
        this.tempretureDayList = tempretureDayList;
    }

    public void setTabAndInfoPadding(float tabAndInfoPadding) {
        this.tabAndInfoPadding = tabAndInfoPadding;
    }

    public void setTabCommonFontSize(float tabCommonFontSize) {
        this.tabCommonFontSize = tabCommonFontSize;
    }

    public void setTabCommonFontColor(int tabCommonFontColor) {
        this.tabCommonFontColor = tabCommonFontColor;
    }

    public void setBloodsCount(int bloodsCount) {
        this.bloodsCount = bloodsCount;
    }

    public void setDayItemTimeCount(int dayItemTimeCount) {
        this.dayItemTimeCount = dayItemTimeCount;
    }

    public void setTimeLag(int timeLag) {
        this.timeLag = timeLag;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setMaiBoStart(int maiBoStart) {
        this.maiBoStart = maiBoStart;
    }

    public void setMaiBoEnd(int maiBoEnd) {
        this.maiBoEnd = maiBoEnd;
    }

    public void setMaiBoAndTempGeCount(int maiBoAndTempGeCount) {
        this.maiBoAndTempGeCount = maiBoAndTempGeCount;
    }

    public void setTempStart(int tempStart) {
        this.tempStart = tempStart;
    }

    /**
     * 测量文字高度
     * 文字居中:y加上高度的一般
     *
     * @param paint
     * @param s
     * @return
     */
    private float meauTextHeight(Paint paint, String s) {
        Rect textBounds = new Rect();
        paint.getTextBounds(s, 0, s.length(), textBounds);
        int textHeight = textBounds.bottom - textBounds.top;
        return textHeight;
    }

    public float getTempH() {
        return tempH;
    }

    /**
     * 测量文字宽度
     *
     * @param paint
     * @param s
     * @return
     */
    private float meauTextWidth(Paint paint, String s) {
        Rect textBounds = new Rect();
        paint.getTextBounds(s, 0, s.length(), textBounds);
        int textWidth = textBounds.right - textBounds.left;
        return textWidth;
    }

    static class FloatPoint {
        private float x;
        private float y;

        public FloatPoint() {
        }

        public FloatPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

    /**
     * Table对象
     */
    static class Table {

        enum TxtType {
            CENTER, CENTENT_TOP, CENTENT_BOTTOM
        }

        private float x;//起始X坐标
        private float y;//起始Y坐标
        private float w;//宽
        private float h;//高
        private int fontColor;//字体颜色
        private float fontSize;//字体大小
        private String txt;//字体内容
        private TxtType textType;//对其方式
        private int leftLineColor;//
        private int rightLineColor;
        private int topLineColor;
        private int bottomLineColor;

        public Table(float x, float y, float w, float h, int fontColor, float fontSize, String txt, TxtType textType, int leftLineColor, int rightLineColor, int topLineColor, int bottomLineColor) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.fontColor = fontColor;
            this.fontSize = fontSize;
            this.txt = txt;
            this.textType = textType;
            this.leftLineColor = leftLineColor;
            this.rightLineColor = rightLineColor;
            this.topLineColor = topLineColor;
            this.bottomLineColor = bottomLineColor;
        }

        public TxtType getTextType() {
            return textType;
        }


        public float getX() {
            return x;
        }


        public float getY() {
            return y;
        }


        public float getW() {
            return w;
        }


        public float getH() {
            return h;
        }


        public int getFontColor() {
            return fontColor;
        }


        public float getFontSize() {
            return fontSize;
        }


        public String getTxt() {
            return txt;
        }


        public int getLeftLineColor() {
            return leftLineColor;
        }


        public int getRightLineColor() {
            return rightLineColor;
        }


        public int getTopLineColor() {
            return topLineColor;
        }


        public int getBottomLineColor() {
            return bottomLineColor;
        }

    }
}
