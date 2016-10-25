package com.shine.mobilenurse.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;



/**
 * Created by zzw on 2016/10/25.
 * 描述:item居中显示
 */

public class HorCenterRecyclerView extends RecyclerView {
    public HorCenterRecyclerView(Context context) {
        super(context);
    }

    public HorCenterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorCenterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int lastWidth;
//      原始测量
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() > 0) {
            int newWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                newWidth += getChildAt(i).getMeasuredWidth();
            }
            if (lastWidth != newWidth) {
                lastWidth = newWidth;
                int empty = getMeasuredWidth() - newWidth;
                if (empty > 0) {
                    if (getPaddingLeft() == empty / 2) {
                        return;
                    }
                    setPadding(empty / 2, 0, empty / 2, 0);
                    //如果不再一次onLayout，子view就不会有padding
                    super.onLayout(changed, l, t, r, b);
                }
            }
        }
    }

    /**
     * VERTICAL时候横向item之间有间距, 水平的时候没有间距
     */
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        if (getChildCount() > 0) {
//           int []a =comWidth();
//            int newWidth = a[0];
//            if (lastWidth != newWidth) {
//                lastWidth = newWidth;
//                if(a[1]==0){
//                    int empty = getMeasuredWidth() - newWidth;
//                    if (empty > 0) {
//                        if (getPaddingLeft() == empty / 2) {
//                            return;
//                        }
//                        setPadding(empty / 2, 0, empty / 2, 0);
//                        //如果不再一次onLayout，子view就不会有padding
////                        super.onLayout(changed, l, t, r, b);
//                    }
//                }else {
//                    int empty = (getMeasuredWidth() - newWidth)/(a[1]+1);
//                    LogPrint.log_d("111","childW:"+a[0]);
//                    LogPrint.log_d("111","recyW:"+getMeasuredWidth());
//                    LogPrint.log_d("111","a[1]:"+a[1]);
//                    LogPrint.log_d("111","间距:"+(getMeasuredWidth() - newWidth));
//                    LogPrint.log_d("111","empty:"+empty);
//                    for(int i=0;i<getChildCount();i++){
//                        View view=getChildAt(i);
//                        view.setPadding(empty,0,0,0);
//                    }
////                    super.onLayout(changed, l, t, r, b);
//
//                }
//            }
//        }
//    }
//
//    /**
//     *
//     * @return int[0] 测量一行的宽度 int[1]一行当前数值有几个
//     */
//    private int[] comWidth(){
//        int [] a=new int[2];
//        if(getLayoutManager() instanceof StaggeredGridLayoutManager){
//            StaggeredGridLayoutManager sglm= (StaggeredGridLayoutManager) getLayoutManager();
//            if(sglm.getOrientation()==StaggeredGridLayoutManager.VERTICAL){
//                if(getAdapter()!=null){
//                    if(getAdapter().getItemCount()>=sglm.getSpanCount()){
//                        for (int i = 0; i < sglm.getSpanCount(); i++) {
//                            a[0] += getChildAt(i).getMeasuredWidth();
//                        }
//                        a[1]=sglm.getSpanCount();
//                    }else {
//                        a[1]=getAdapter().getItemCount();
//                    }
//                }
//            }
//        }
//        if(a[0]==0){
//            for (int i = 0; i < getChildCount(); i++) {
//                a[0] += getChildAt(i).getMeasuredWidth();
//             }
//        }
//        return a;
//    }

    //    //固定宽度的居中方式
    //    protected void onMeasure(int widthSpec, int heightSpec) {
    //        if (getAdapter()==null) {
    //            super.onMeasure(widthSpec, heightSpec);
    //        } else {
    //            int specHeight = MeasureSpec.getSize(heightSpec);
    //            int maxHeight = MeasureSpec.makeMeasureSpec(specHeight, MeasureSpec.AT_MOST);
    //            //item_data weidth:60dp
    //            setMeasuredDimension(DensityUtil.dip2px(getContext(),60*getAdapter().getItemCount()), maxHeight);
    //        }
    //    }

}