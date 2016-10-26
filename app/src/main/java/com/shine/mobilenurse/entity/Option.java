package com.shine.mobilenurse.entity;

/**
 * Created by zzw on 2016/10/13.
 * 描述:
 */

public class Option extends BaseEntity {

    private String name;
    private String teg;
    private int type;

    public Option() {

    }

    /**
     * @param name
     * @param teg
     * @param type 0表示竖直  1表示水平
     */
    public Option(String name, String teg, int type) {
        this.name = name;
        this.teg = teg;
        this.type = type;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeg() {
        return teg;
    }

    public void setTeg(String teg) {
        this.teg = teg;
    }
}
