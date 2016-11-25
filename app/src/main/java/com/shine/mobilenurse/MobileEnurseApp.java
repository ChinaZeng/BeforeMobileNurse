package com.shine.mobilenurse;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.shine.mobilenurse.base.BaseApplication;
import com.shine.mobilenurse.view.dialog.CheckFailDialog;
import com.shine.mobilenurse.view.dialog.CheckOkDialog;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class MobileEnurseApp extends BaseApplication {

    private static MobileEnurseApp appInstance = null;

    private CheckFailDialog checkFailDialog;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    /**
     * 获取APP对象引用
     *
     * @return
     */
    public static MobileEnurseApp getInstance() {
        return appInstance;
    }

    /**
     * 是否网络可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }



}
