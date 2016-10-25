package com.shine.mobilenurse.entity;

/**
 * Created by zzw on 2016/10/24.
 * 描述:
 */

public class Beds {

    private int bedNum;
    private String name;

    public Beds() {
    }

    public Beds(int bedNum, String name) {
        this.bedNum = bedNum;
        this.name = name;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
