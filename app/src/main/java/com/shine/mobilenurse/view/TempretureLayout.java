package com.shine.mobilenurse.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.shine.mobilenurse.utils.LogPrint;

/**
 * Created by zzw on 2016/11/11.
 * 描述:
 */

public class TempretureLayout extends LinearLayout {

    private final int MAX_LENGTH=300;

    private ViewDragHelper viewDragHelper;
//    private GestureDetector gestureDetector;

    public TempretureLayout(Context context) {
        this(context,null);
    }

    public TempretureLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TempretureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        viewDragHelper=ViewDragHelper.create(this,1.0f,new TempretureViewDragHelperCallback());
//        gestureDetector=new GestureDetector(context,new MyGestureListener());
    }


    class  TempretureViewDragHelperCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {

            if(child instanceof TempretureTableView)
                return true;

            return false;
        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(top<-MAX_LENGTH){
                return -MAX_LENGTH;
            }

            if(top>MAX_LENGTH){
                return MAX_LENGTH;
            }

            return top;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return viewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
//        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

//    private int nowAnimPos=0;
//    private int beforeAnimPos=0;
//    private float[] animMultiple=new float[]{1.0f,1.5f,2.0f};
//
//    private int nowX,nowY;
//
//    class  MyGestureListener extends GestureDetector.SimpleOnGestureListener{
//        //double触发
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {

//            int w=getWidth();
//            int h=getHeight();
//
//            beforeAnimPos=nowAnimPos;
//            nowAnimPos+=1;
//            if(nowAnimPos>=animMultiple.length)
//                nowAnimPos=0;
//            float x=e.getX();
//            float y=e.getY();
//
//            if(nowX==0){
//                nowX=getLeft();
//            }
//            if(nowY==0){
//                nowY=getTop();
//            }
//
//            float s= animMultiple[nowAnimPos]-animMultiple[beforeAnimPos];
//            ScaleAnimation animation=new ScaleAnimation(nowX,nowY,
//                   nowX- s*w,nowY-s*h,x,y);
//            animation.setFillAfter(true);
//            startAnimation(animation);

//            return super.onDoubleTap(e);
//        }
//
//        //每一次点击都要触发
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            return super.onSingleTapUp(e);
//        }
//
//        //double不触发在触发
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent e) {
//            return super.onSingleTapConfirmed(e);
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            LogPrint.log_d("zzz","onScroll");
//            return super.onScroll(e1, e2, distanceX, distanceY);
//        }
//
//    }

}
