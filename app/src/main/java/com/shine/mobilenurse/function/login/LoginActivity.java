package com.shine.mobilenurse.function.login;


import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shine.mobilenurse.R;
import com.shine.mobilenurse.eventBusMessage.LoginMessage;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.base.BaseActivity;
import com.shine.mobilenurse.utils.ViewUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends BaseActivity {

    //    private Intent m_intent;
//    private Tag m_tagFromIntent;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;

    private final static int SCAN_LOGIN_REQUEST_CODE = 0x001;
    private final static String[] spinnerArray = new String[]{"心血管内科护理单元", "耳鼻喉科护理单元"};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }



    @Override
    protected void initView() {
        super.initView();
//        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerArray));

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                UI.showToast(this, "使用nfid功能登陆,请在系统设置中先启用NFC功能！");
            } else {
                pendingIntent = PendingIntent.getActivity(
                        this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

                IntentFilter ndef = new IntentFilter();
                ndef.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
                ndef.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
                ndef.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
                try {
                    ndef.addDataType("*/*");    /* Handles all MIME based dispatches.
                                       You should specify only the ones that you need. */
                } catch (IntentFilter.MalformedMimeTypeException e) {
                    throw new RuntimeException("fail", e);
                }
                intentFiltersArray = new IntentFilter[]{ndef,};
                techListsArray = new String[][]{new String[]{MifareClassic.class.getName()}};
            }
        } else {
            UI.showToast(this, "设备不支持NFC！");
        }
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        EventBus.getDefault().register(this);
        if (nfcAdapter != null)
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
        super.onResume();
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

    /**
     * 扫描枪登陆 EventBus传递
     *
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginMessage(LoginMessage loginMessage) {
        UI.showToast(this, loginMessage.getLoginCode());
        UI.showMainActivity(this);
        this.finish();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processAdapterAction(intent);
    }

    private void processAdapterAction(Intent intent) {
        //当系统检测到tag中含有NDEF格式的数据时，且系统中有activity声明可以接受包含NDEF数据的Intent的时候，系统会优先发出这个action的intent。
        //得到是否检测到ACTION_NDEF_DISCOVERED触发                           序号1
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            System.out.println("ACTION_NDEF_DISCOVERED");
            //处理该intent
            processIntent(intent);
            return;
        }
        //当没有任何一个activity声明自己可以响应ACTION_NDEF_DISCOVERED时，系统会尝试发出TECH的intent.即便你的tag中所包含的数据是NDEF的，但是如果这个数据的MIME type或URI不能和任何一个activity所声明的想吻合，系统也一样会尝试发出tech格式的intent，而不是NDEF.
        //得到是否检测到ACTION_TECH_DISCOVERED触发                           序号2
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            System.out.println("ACTION_TECH_DISCOVERED");
            //处理该intent
            processIntent(intent);
            return;
        }
        //当系统发现前两个intent在系统中无人会接受的时候，就只好发这个默认的TAG类型的
        //得到是否检测到ACTION_TAG_DISCOVERED触发                           序号3
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            System.out.println("ACTION_TAG_DISCOVERED");
            //处理该intent
            processIntent(intent);
        }
    }

    private void processIntent(Intent intent) {
//        m_intent = intent;
//        //取出封装在intent中的TAG
//        m_tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        //System.out.print(m_tagFromIntent);
//        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//        for (String tech : tagFromIntent.getTechList()) {
//            System.out.println(tech);
//        }
        if (intent != null) {
            byte[] myNFCID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
            String id = bytesToHexString(myNFCID);

            if (!TextUtils.isEmpty(id)) {
                UI.showToast(this, "卡片ID：" + id);
                UI.showMainActivity(this);
                this.finish();
            } else {
                UI.showToast(this, "读取错误!");
            }
        } else {
            UI.showToast(this, "读取错误!");
        }


//        boolean auth = false;
//        //读取TAG
//        MifareClassic mfc = MifareClassic.get(tagFromIntent);
//        try {
//            String metaInfo = "";
//            //Enable I/O operations to the tag from this TagTechnology object.
//            mfc.connect();
//            int type = mfc.getType();//获取TAG的类型
//            int sectorCount = mfc.getSectorCount();//获取TAG中包含的扇区数
//            String typeS = "";
//            switch (type) {
//                case MifareClassic.TYPE_CLASSIC:
//                    typeS = "TYPE_CLASSIC";
//                    break;
//                case MifareClassic.TYPE_PLUS:
//                    typeS = "TYPE_PLUS";
//                    break;
//                case MifareClassic.TYPE_PRO:
//                    typeS = "TYPE_PRO";
//                    break;
//                case MifareClassic.TYPE_UNKNOWN:
//                    typeS = "TYPE_UNKNOWN";
//                    break;
//            }
//
//            metaInfo += "卡片类型：" + typeS + "\n共" + sectorCount + "个扇区\n共"
//                    + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize() + "B\n";
//
//            for (int j = 0; j < sectorCount; j++) {
//                //Authenticate a sector with key A.
//                auth = mfc.authenticateSectorWithKeyA(j,
//                        MifareClassic.KEY_DEFAULT);
//                int bCount;
//                int bIndex;
//                if (auth) {
//                    metaInfo += "Sector " + j + ":验证成功\n";
//                    // 读取扇区中的块
//                    bCount = mfc.getBlockCountInSector(j);
//                    bIndex = mfc.sectorToBlock(j);
//                    for (int i = 0; i < bCount; i++) {
//                        byte[] data = mfc.readBlock(bIndex);
//                        metaInfo += "Block " + bIndex + " : "
//                                + bytesToHexString(data) + "\n";
//                        bIndex++;
//                    }
//                } else {
//                    metaInfo += "Sector " + j + ":验证失败\n";
//                }
//            }
//            resultView_MF1.setText(metaInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    //字符序列转换为16进制字符串
    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }
}
