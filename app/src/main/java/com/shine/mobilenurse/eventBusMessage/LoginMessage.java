package com.shine.mobilenurse.eventBusMessage;

/**
 * Created by zzw on 2016/11/15.
 * 描述:
 */

public class LoginMessage {

    private String loginCode;

    public LoginMessage() {
    }

    public LoginMessage(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
