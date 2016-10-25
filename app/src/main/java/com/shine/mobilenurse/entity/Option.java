package com.shine.mobilenurse.entity;

/**
 * Created by zzw on 2016/10/13.
 * 描述:
 */

public class Option extends BaseEntity {

    private String name;
    private String tag;
    private int type;

    public Option() {

    }

    /**
     * @param name
     * @param tag
     * @param type 0表示竖直  1表示水平
     */
    public Option(String name, String tag, int type) {
        this.name = name;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
