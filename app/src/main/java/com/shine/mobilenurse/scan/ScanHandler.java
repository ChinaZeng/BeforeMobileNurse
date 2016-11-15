package com.shine.mobilenurse.scan;

import android.hardware.barcode.Scanner;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.shine.mobilenurse.MobileEnurseApp;
import com.shine.mobilenurse.eventBusMessage.LoginMessage;
import com.shine.mobilenurse.function.UI;
import com.shine.mobilenurse.utils.LogPrint;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by zzw on 2016/10/31.
 * 描述:
 */

public class ScanHandler extends Handler {

    private static final String TAG = "ScanHandler";

    /**
     * 本测试程序是采用AudioTrack播放一个固定频率的声音发提示音的，你可以播放系统音或者播放一段音频
     */
    private final int duration = 1; // seconds
    private final int sampleRate = 2000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 1600; // hz

    private final byte generatedSnd[] = new byte[2 * numSamples];


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {

            case Scanner.BARCODE_READ: {
                String m = (String) msg.obj;
                LogPrint.log_d("扫描枪收到的数据：" + m);
                EventBus.getDefault().post(new LoginMessage(m));
                play();
                break;
            }
            case Scanner.BARCODE_NOREAD: {
                UI.showToast(MobileEnurseApp.getInstance(), "读取错误！");
                break;
            }

            default:
                break;
        }
    }


    private void genTone() {
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (double dVal : sample) {
            short val = (short) (dVal * 32767);
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

    }


    private void playSound() {
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, numSamples);
        audioTrack.play();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        audioTrack.release();

    }


    private void play() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                genTone();
                playSound();
            }
        });
        thread.start();
    }
}
