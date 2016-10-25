package com.shine.mobilenurse;

import com.shine.mobilenurse.base.BaseApplication;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class MobileEnurseApp extends BaseApplication {

    private static MobileEnurseApp appInstance = null;

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

}
