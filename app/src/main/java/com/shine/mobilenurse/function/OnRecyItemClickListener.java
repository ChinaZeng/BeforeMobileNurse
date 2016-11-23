package com.shine.mobilenurse.function;

import android.view.View;

import butterknife.OnItemClick;

/**
 * Created by zzw on 2016/11/23.
 * 描述:
 */

public interface OnRecyItemClickListener<T> {
    void OnItemClick(View view, T t, int pos);
}
