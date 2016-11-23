package com.shine.mobilenurse.eventBusMessage;

/**
 * Created by zzw on 2016/11/22.
 * 描述:
 */

public class TabPosMessage {
    private String fragmentName;

    public TabPosMessage() {
    }

    public TabPosMessage(String fragmentName) {
        this.fragmentName = fragmentName;
    }


    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}
