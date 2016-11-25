package com.shine.mobilenurse.function;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.shine.mobilenurse.function.common.ImageActivity;
import com.shine.mobilenurse.function.common.ScanActivity;
import com.shine.mobilenurse.function.main.MainActivity;
import com.shine.mobilenurse.view.dialog.CheckOkDialog;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class UI {

    private static Toast mToast;

    private static CheckOkDialog checkOkDialog;

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

    /**
     * 显示核对成功对话框
     */
    public static void showCheckOkDialog(Context context) {
        if (checkOkDialog == null) {
            checkOkDialog = new CheckOkDialog(context);
        }
        if (!checkOkDialog.isShowing())
            checkOkDialog.show();
    }


    public static void showScanActivity(Activity activity, int request) {
        Intent intent = new Intent(activity, ScanActivity.class);
        activity.startActivityForResult(intent, request);
    }


    public static void showMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public static void showImageActivity(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, ImageActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


}
