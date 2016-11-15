package com.shine.mobilenurse.utils;

import android.util.Log;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class LogPrint {
    public final static String TAG = "MobileEnurse";

    /**
     * 输出打印开关
     */
    public static boolean logPrint_key = true;

    /**
     * 打印输出信息
     *
     * @param tag 打印信息标题
     * @param msg 打印信息内容
     * @return
     */
    public static void log_v(String tag, String msg) {
        if (logPrint_key) {
            Log.v(tag, msg);
        }
    }


    public static void log_v(String msg) {
        if (logPrint_key) {
            Log.v(TAG, msg);
        }
    }

    /**
     * 打印输出Debug调试信息
     *
     * @param tag
     * @param msg
     * @return
     */
    public static void log_d(String tag, String msg) {
        if (logPrint_key) {
            Log.d(tag, msg);
        }
    }


    /**
     * 打印输出Debug调试信息
     *
     * @param msg
     * @return
     */
    public static void log_d(String msg) {
        if (logPrint_key) {
            Log.d(TAG, msg);
        }
    }


    /**
     * 答应输出提示信息information
     *
     * @param tag
     * @param msg
     * @return
     */
    public static void log_i(String tag, String msg) {
        if (logPrint_key) {
            Log.e(tag, msg);
        }
    }


    /**
     * 打印输出警告信息 Warning
     *
     * @param tag
     * @param msg
     * @return
     */
    public static void log_w(String tag, String msg) {
        if (logPrint_key) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印输出错误信息 error
     *
     * @param tag
     * @param msg
     * @return
     */
    public static void log_e(String tag, String msg) {
        if (logPrint_key) {
            Log.e(tag, msg);
        }
    }

    public static void log_e(String msg) {
        if (logPrint_key) {
            Log.e(TAG, msg);
        }
    }

    public static void println(String msg) {
        if (logPrint_key) {
            System.out.println(msg);
        }
    }
}
