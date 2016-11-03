package com.shine.mobilenurse.net;

import android.text.TextUtils;


import rx.Subscriber;

/**
 * Created by zzw on 2016/11/3.
 * 描述:RxJava数据请求失败统一处理
 */

public abstract class NetCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg;
        msg = e.getMessage();
//        if (e instanceof HttpException) {
//            HttpException httpException = (HttpException) e;
//            int code = httpException.code();
//            msg = httpException.getMessage();
//            LogUtil.d("code=" + code);
//            if (code == 504) {
//                msg = "网络不给力";
//            }
//            if (code == 502 || code == 404) {
//                msg = "服务器异常，请稍后再试";
//            }
//        } else {
//            msg = e.getMessage();
//        }
//        if (! MyApplication.isNetworkAvailable(MyApplication.getInstance())) {
//            msg = "请检查网络连接";
//        }
        if (!TextUtils.isEmpty(msg)) {
            onFailure(msg);
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
