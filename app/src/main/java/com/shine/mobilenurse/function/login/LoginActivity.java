package com.shine.mobilenurse.function.login;


import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.base.BaseActivity;
import com.shine.mobilenurse.utils.ViewUtil;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends BaseActivity {

    private final static int SCAN_LOGIN_REQUEST_CODE = 0x001;
    private final static String[] spinnerArray = new String[]{"心血管内科护理单元", "耳鼻喉科护理单元"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void findViewId() {
        super.findViewId();
    }

    @Override
    protected void initView() {
        super.initView();
//        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerArray));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED && requestCode == SCAN_LOGIN_REQUEST_CODE) {
            this.finish();
        }
    }

    /**
     * 二扫描登陆点击事件
     *
     * @param v
     */
    public void scanLogin(View v) {
        UI.showScanActivity(this, SCAN_LOGIN_REQUEST_CODE);
    }

    /**
     * 登陆按钮
     *
     * @param view
     */
    public void login(View view) {
        UI.showMainActivity(this);
        this.finish();
    }


}
