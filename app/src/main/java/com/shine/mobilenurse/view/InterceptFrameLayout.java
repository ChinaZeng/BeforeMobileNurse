package com.shine.mobilenurse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by zzw on 2016/11/18.
 * 描述: 拦截点击  扫描枪在 popupWindow.setFocusable(true)后失去作用，所以控制父布局的点击拦截
 * 显示popupWindow的时候将点击事件拦截，显示popupWindow的时候将点击事件拦截消失的时候不拦截
 */

public class InterceptFrameLayout extends FrameLayout {

    public static final int INTERCEPT = 0x11;
    public static final int NOT_INTERCEPT = 0x12;

    private int interceptType;


    public InterceptFrameLayout(Context context) {
        this(context, null);
    }

    public InterceptFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InterceptFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        interceptType = NOT_INTERCEPT;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (interceptType == INTERCEPT)
            return true;
        return super.onInterceptTouchEvent(ev);
    }

    public int getInterceptType() {
        return interceptType;
    }

    public void setInterceptType(int interceptType) {
        this.interceptType = interceptType;
    }
}
