package com.shine.mobilenurse.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.shine.mobilenurse.R;


/**
 * Created by zzw on 2016/6/24.
 * 描述:
 */
public class LoadingDialog extends Dialog {

    private Context context;

    private View contentView;
    private TextView hintTextView;

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        initView(context);
    }

    public LoadingDialog(Context context) {
        this(context, R.style.dialog_loading);
    }

    private void initView(Context context) {
        contentView = LayoutInflater.from(context).inflate(R.layout.layout_progress, null);
        hintTextView = (TextView) contentView.findViewById(R.id.pro_text);
        setContentView(contentView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) context.getResources().getDimension(R.dimen.space_180);
        p.height = (int) context.getResources().getDimension(R.dimen.space_120);
        getWindow().setAttributes(p);
        getWindow().setGravity(Gravity.CENTER);
    }


    public void setMessage(int resId) {
        if (resId == 0)
            return;
        setMessage(context.getString(resId));//默认为加载
    }

    public void setMessage(String message) {
        if (message == null)
            return;
        hintTextView.setText(message);
        hintTextView.invalidate();
    }


}
