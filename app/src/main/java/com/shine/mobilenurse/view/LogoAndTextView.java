package com.shine.mobilenurse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.utils.TDUtils;

/**
 * Created by zzw on 2016/10/19.
 * 描述:
 */

public class LogoAndTextView extends LinearLayout {

    private String tag;
    private int textColor;
    private String text;
    private int textSize;
    private Drawable src;
    private ImageView imageView;
    private TextView textView;

    private int type;


    private final int DEFAULT_TEXT_SIZE = 6;


    public LogoAndTextView(Context context) {
        this(context, null);
    }

    public LogoAndTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoAndTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initView(context);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LogoAndTextView);
        textColor = a.getInteger(R.styleable.LogoAndTextView_textColor, context.getResources().getColor(R.color.color_525252));
        text = a.getString(R.styleable.LogoAndTextView_text);
        textSize = TDUtils.sp2px(context, a.getInteger(R.styleable.LogoAndTextView_textSize, DEFAULT_TEXT_SIZE));
        src = a.getDrawable(R.styleable.LogoAndTextView_src);
        tag = a.getString(R.styleable.LogoAndTextView_tag);
        type = a.getInt(R.styleable.LogoAndTextView_orientation, 0);
        a.recycle();
    }


    private void initView(Context context) {
        View view = null;

        if (type == 0) {
            setOrientation(VERTICAL);

            LayoutParams rootparms = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            setLayoutParams(rootparms);

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_0, null);
            imageView = (ImageView) view.findViewById(R.id.item_0_img);
            textView = (TextView) view.findViewById(R.id.item_0_text);

        } else if (type == 1) {
            setOrientation(HORIZONTAL);

            LayoutParams rootparms = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            setLayoutParams(rootparms);

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_1, null);
            imageView = (ImageView) view.findViewById(R.id.item_1_img);
            textView = (TextView) view.findViewById(R.id.item_1_text);
        }

        setGravity(Gravity.CENTER);
        if (src != null)
            imageView.setImageDrawable(src);
        textView.setText(text + "");
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        addView(view);
    }

    public void setTextColor(int color) {
        if (color != 0 && textView != null) {
            this.textColor = color;
            textView.setTextColor(color);
        }

    }

    public void setTag(String tag) {
        if (tag != null)
            this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTextSize(int textSize) {
        if (textSize != 0 && textView != null) {
            this.textSize = textSize;
            textView.setTextSize(TDUtils.sp2px(getContext(), textSize));
        }
    }

    public void setSrc(Drawable src) {
        if (src != null && imageView != null) {
            this.src = src;
            imageView.setImageDrawable(src);
        }

    }

    public void setText(String text) {
        if (text != null && textView != null) {
            this.text = text;
            textView.setText(text);
        }
    }
}
