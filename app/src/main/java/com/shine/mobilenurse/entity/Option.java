package com.shine.mobilenurse.entity;


/**
 * Created by zzw on 2016/11/23.
 * 描述:
 */

public class Option extends BaseEntity {

    private Integer id;

    private Integer sort;
    private String name;
    /**
     * 上方表示0  中间表示1  下方表示2 不显示 3
     */
    private Integer type;


    public Option() {

    }


    public Option(int id, int sort, String name, int type) {
        this.id = id;
        this.sort = sort;
        this.name = name;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public boolean equals(Option option) {
        if (option == null)
            return false;
        return this.getId() == option.getId();
    }
}
