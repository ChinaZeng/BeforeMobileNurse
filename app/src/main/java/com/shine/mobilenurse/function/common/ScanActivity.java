package com.shine.mobilenurse.function.common;


import android.content.Intent;
import android.os.Handler;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.function.login.LoginActivity;
import com.shine.mobilenurse.utils.LogPrint;
import com.xys.libzxing.zxing.activity.CaptureActivity;

public class ScanActivity extends CaptureActivity {

    private static final String TAG = "ScanActivity";

    @Override
    protected void scanSuccess(String s) {
        super.scanSuccess(s);
        LogPrint.log_e(TAG, "扫描结果:" + s);

//        UI.showLoadingDialog(this, R.string.scan_success_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainActivity();
            }
        }, 2000);
    }

    private void goMainActivity() {
//        UI.cancelLoadingDialog();
        setResult(RESULT_OK, new Intent(this, LoginActivity.class));
        UI.showMainActivity(this);
        finish();
    }


    @Override
    protected void scanFailed() {
        super.scanFailed();
        UI.showToast(this, R.string.scan_failed_message);
        this.finish();
    }

}
