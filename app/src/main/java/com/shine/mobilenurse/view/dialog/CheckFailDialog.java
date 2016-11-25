package com.shine.mobilenurse.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.shine.mobilenurse.R;

/**
 * Created by zzw on 2016/11/25.
 * 描述:
 */

public class CheckFailDialog extends Dialog {

    private TextView title, content;
    private ImageView imageView;
    private Context context;

    public CheckFailDialog(Context context) {
        this(context, R.style.dialog_check_fail);
    }

    public CheckFailDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_check_fail, null);
        title = (TextView) view.findViewById(R.id.dialog_check_fail_title);
        content = (TextView) view.findViewById(R.id.dialog_check_fail_content);
        imageView = (ImageView) view.findViewById(R.id.dialog_check_fail_img);
        view.findViewById(R.id.dialog_check_fail_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = (int) context.getResources().getDimension(R.dimen.space_300);
        getWindow().setAttributes(p);
        getWindow().setGravity(Gravity.CENTER);
    }

    private void setTitle(String title) {
        this.title.setText(title);
        this.title.invalidate();
    }

    private void setContent(String content) {
        this.content.setText(content);
        this.content.invalidate();
    }

    private void setImageViewRes(int id) {
        this.imageView.setImageResource(id);
        this.imageView.invalidate();
    }
}
