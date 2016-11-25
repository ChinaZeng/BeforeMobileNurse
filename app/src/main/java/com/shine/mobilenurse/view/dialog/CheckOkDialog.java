package com.shine.mobilenurse.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.shine.mobilenurse.R;

/**
 * Created by zzw on 2016/11/25.
 * 描述:
 */

public class CheckOkDialog extends Dialog {

    private Context context;

    public CheckOkDialog(Context context) {
        this(context, R.style.dialog_check_ok);
    }

    public CheckOkDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }


    private void initView(Context context) {
        this.context = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_check_ok, null);
        setContentView(contentView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) context.getResources().getDimension(R.dimen.space_140);
        p.height = (int) context.getResources().getDimension(R.dimen.space_140);
        getWindow().setAttributes(p);
        getWindow().setGravity(Gravity.CENTER);
    }


}
