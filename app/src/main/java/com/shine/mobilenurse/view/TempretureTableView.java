package com.shine.mobilenurse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.shine.mobilenurse.utils.TDUtils;

/**
 * Created by zzw on 2016/10/11.
 * 描述:
 */

public class TempretureTableView extends View {

    private Paint paint;
    private float screenW, screenH;


    public TempretureTableView(Context context) {
        this(context, null);
    }

    public TempretureTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TempretureTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }


    private void init(Context context) {
        int[] is = TDUtils.getScreenWAndH(context);
        screenW = is[0];
        screenH = is[1];

        paint = new Paint();
        paint.setTextSize(TDUtils.sp2px(context,20f));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("你好啊", 50, 50, paint);
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
}
