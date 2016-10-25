package com.shine.mobilenurse.net;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public interface OnNetRequestListener {
    void onSuccess(String url, String s);

    void onFailure(String url, String strMsg);
}
