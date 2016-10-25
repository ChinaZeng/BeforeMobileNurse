package com.shine.mobilenurse.net;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class NetRequest {
    private static NetRequest instance = null;

    public static NetRequest getInstance() {
        if (instance == null) {
            instance = new NetRequest();
            synchronized (NetRequest.class) {
                if (instance == null) {
                    instance = new NetRequest();
                }
            }
        }
        return instance;
    }



}
