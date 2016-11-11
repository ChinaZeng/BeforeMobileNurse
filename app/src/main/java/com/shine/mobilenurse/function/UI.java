package com.shine.mobilenurse.function;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.shine.mobilenurse.function.common.ImageActivity;
import com.shine.mobilenurse.function.common.ScanActivity;
import com.shine.mobilenurse.view.LoadingDialog;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class UI {

    private static LoadingDialog dialog;
    private static Toast mToast;

    /**
     * 设置对话框取消监听
     *
     * @param listener
     */
    public static void setDialogOnCancelListener(DialogInterface.OnCancelListener listener) {
        if (listener == null || dialog == null)
            return;
        dialog.setOnCancelListener(listener);
    }

    /**
     * 显示对话框
     *
     * @param msg
     */
    public static void showLoadingDialog(Context context, String msg) {
        if (msg == null)
            return;
        if (dialog == null) {
            dialog = new LoadingDialog(context);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.setMessage(msg);
        if (!dialog.isShowing())
            dialog.show();
    }

    public static void showLoadingDialog(Context context, int msgId) {
        showLoadingDialog(context, context.getResources().getString(msgId));
    }


    /**
     * 取消对话框
     */
    public static void cancelLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public static void showToast(Context context, String msg) {
        if (msg == null)
            return;

        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(Context context, int id) {
        if (id == 0)
            return;
        showToast(context, context.getString(id));
    }


    public static void showScanActivity(Activity activity, int request) {
        Intent intent = new Intent(activity, ScanActivity.class);
        activity.startActivityForResult(intent, request);
    }


    public static void showMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void showImageActivity(Activity activity, Bundle bundle){
        Intent intent = new Intent(activity, ImageActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

}
