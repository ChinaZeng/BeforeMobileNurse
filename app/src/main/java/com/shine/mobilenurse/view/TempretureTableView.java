package com.shine.mobilenurse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
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


    public TempretureTableView(Context context) {
        this(context, null);
    }

    public TempretureTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempretureTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null)
            initTypedArray(context, attrs);
        init(context);
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

        a.recycle();
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int specWMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specHMode = MeasureSpec.getMode(heightMeasureSpec);
//
//        int specWSize = MeasureSpec.getSize(widthMeasureSpec);
//        switch (specWMode) {
//            case MeasureSpec.AT_MOST:
//                if (tempretureDayList != null && tempretureDayList.size() != 0) {
//                    Paint paint = new Paint();
//                    paint.setTextSize(tabCommonFontSize);
//                    float a = meauTextWidth(paint, tempretureDayList.get(0).getDate());
//                    screenW = a * (tempretureDayList.size() + 1);
//                    specWSize = (int) screenW;
//                }
//
//                break;
//
//            case MeasureSpec.EXACTLY:
//
//                break;
//        }
//        setMeasuredDimension(specWSize, MeasureSpec.getSize(heightMeasureSpec));
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
     * 第三部:画表格
     *
     * @param canvas
     */
    private void drawTable(Canvas canvas) {
        if (tempretureDayList == null || tempretureDayList.size() == 0)
            return;

        drawTableList(canvas, comDataList());
        drawTableList(canvas, comInToHospitalNumList());
        drawTableList(canvas, comcutNumList());





        drawTableList(canvas, comInToNumList());
        drawTableList(canvas, comOutNumList());
        drawTableList(canvas, comShitNumByDayList());
        drawTableList(canvas, comHeightList());
        drawTableList(canvas, comWeightList());
        drawTableList(canvas, comMaiXiangList());
        drawTableList(canvas, comSheTaiList());

//        paint.setColor(Color.BLACK);
//        canvas.drawLine(padLeft, tempH, dayW * (tempretureDayList.size() + 1) + padLeft, tempH, paint);
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
     * 计算脉象
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
        invalidate();
    }


    public void setTempretureDay(List<TempretureDay> tempretureDayList) {
        this.tempretureDayList = tempretureDayList;
        invalidate();
    }


    public void setData(TempretureInfos tempretureInfos, List<TempretureDay> tempretureDayList) {
        this.infos = tempretureInfos;
        this.tempretureDayList = tempretureDayList;
        invalidate();
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
                    canvas.drawText(table.getTxt() + "", table.getX() + (table.getW() - sw) / 2, table.getY() + sH, paint);
                    break;

                case CENTENT_BOTTOM:
                    canvas.drawText(table.getTxt() + "", table.getX() + (table.getW() - sw) / 2, table.getY() + table.getH(), paint);
                    break;

                case CENTER:
                    canvas.drawText(table.getTxt() + "", table.getX() + ((table.getW() - sw) / 2), table.getY() + ((table.getH() - sH) / 2) + sH, paint);
                    break;
            }
        }
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
