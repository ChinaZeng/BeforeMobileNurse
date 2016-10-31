package com.shine.mobilenurse.base;

import android.app.Application;
import android.hardware.barcode.Scanner;

import com.shine.mobilenurse.scan.ScanHandler;

/**
 * Created by zzw on 2016/9/27.
 * 描述:一些初始化在这里面进行
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        Scanner.m_handler = new ScanHandler();
        Scanner.InitSCA();
        super.onCreate();
    }
}
