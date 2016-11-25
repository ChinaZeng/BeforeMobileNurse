package com.shine.mobilenurse.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shine.mobilenurse.R;


/**
 * Created by zzw on 2016/11/25.
 * 描述:
 */

public class CheckDialog extends Dialog {

    private TextView title, defaultTime, nowTime, operatorName, checkSure;
    private Context context;
    private LinearLayout xiangmuLl;
    private String[] xiangmuName;

    private final int MAX_COUNDOWN_TIME = 5;//执行的最长时间是多少秒
    private int countdownTime;//当前还有多少秒才执行

    //倒计时开关  是否在时间内显示对话框
    private boolean isInTheTimeDisplay = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setRema();
        }
    };

    public interface OnSureClickListener {
        void onSureClick();
    }

    private OnSureClickListener onSureClickListener;

    public CheckDialog(Context context, String[] xiangmuName) {
        this(context, R.style.dialog_check_fail, xiangmuName);

    }

    public CheckDialog(Context context, int themeResId, String[] xiangmuName) {
        super(context, themeResId);
        this.xiangmuName = xiangmuName;
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_check, null);
        title = (TextView) view.findViewById(R.id.dialog_check_title);
        defaultTime = (TextView) view.findViewById(R.id.dialog_check_default_time);
        nowTime = (TextView) view.findViewById(R.id.dialog_check_now_time);
        operatorName = (TextView) view.findViewById(R.id.dialog_check_operator_name);
        xiangmuLl = (LinearLayout) view.findViewById(R.id.dialog_check_xiangmu_ll);
        addXiangMuName();

        view.findViewById(R.id.dialog_check_dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        checkSure = (TextView) view.findViewById(R.id.dialog_check_sure);

        checkSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSureClickListener != null) {
                    countdownTime = 0;
                    dismiss();
                }

            }
        });

        setContentView(view);
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                countdownTime = MAX_COUNDOWN_TIME;
                isInTheTimeDisplay = true;
                setRema();
            }
        });

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isInTheTimeDisplay = false;
                if (countdownTime == 0 && onSureClickListener != null) {
                    onSureClickListener.onSureClick();
                }
                countdownTime = MAX_COUNDOWN_TIME;
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) context.getResources().getDimension(R.dimen.space_300);
        getWindow().setAttributes(p);
        getWindow().setGravity(Gravity.CENTER);
    }


    public void setOnSureClickListener(OnSureClickListener onSureClickListener) {
        this.onSureClickListener = onSureClickListener;
    }


    private void addXiangMuName() {
        for (String name : xiangmuName) {
            TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_check_xiangmu_name, xiangmuLl, false);
            textView.setText(name);
            xiangmuLl.addView(textView);
        }
    }

    public void setXiangmuName(String[] xiangmuName) {
        this.xiangmuName = xiangmuName;
        xiangmuLl.removeAllViews();
        addXiangMuName();
    }

    private void setRema() {
        if (isInTheTimeDisplay) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    countdownTime--;
                    checkSure.setText("执行(" + countdownTime + "s)");
                    if (countdownTime == 0) {
                        dismiss();
                    }
                    handler.sendEmptyMessage(0);
                }
            }, 1000);
        }
    }


}
