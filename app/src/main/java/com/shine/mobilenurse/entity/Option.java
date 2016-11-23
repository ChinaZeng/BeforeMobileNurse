package com.shine.mobilenurse.entity;

/**
 * Created by zzw on 2016/11/23.
 * 描述:
 */

public class Option extends BaseEntity {


    private int id;
    private int sort;
    private String name;


    public Option() {

    }

    public Option(int id, int sort, String name) {
        this.id = id;
        this.sort = sort;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
