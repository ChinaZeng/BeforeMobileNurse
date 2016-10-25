package com.shine.mobilenurse.db.sql;


import android.util.Log;

import com.shine.mobilenurse.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */
public class Selector {

    protected Class<? extends BaseEntity> entity;
    protected String tableName;

    protected WhereBuilder whereBuilder;
    protected List<OrderBy> orderByList;
    protected int limit = 0;
    protected int offset = 0;

    public Class<? extends BaseEntity> getEntity() {
        return entity;
    }

    private Selector(Class<? extends BaseEntity> entity) {
        this.entity = entity;
        this.tableName = entity.getSimpleName();
    }

    public static Selector from(Class<? extends BaseEntity> entity) {
        return new Selector(entity);
    }

    public Selector where(String columnName, Object value) {
        this.whereBuilder = WhereBuilder.b(columnName, value);
        return this;
    }


    public Selector where(WhereBuilder whereBuilder) {
        this.whereBuilder = whereBuilder;
        return this;
    }

    public Selector and(String columnName, Object value) {
        this.whereBuilder.and(columnName, value);
        return this;
    }

    public Selector or(String columnName, Object value) {
        this.whereBuilder.or(columnName, value);
        return this;
    }

    public Selector exp(String expr) {
        this.whereBuilder.exp(expr);
        return this;
    }

    public Selector orderBy(String columnName) {
        if (orderByList == null) {
            orderByList = new ArrayList<OrderBy>(2);
        }
        orderByList.add(new OrderBy(columnName));
        return this;
    }

    public Selector orderBy(String columnName, boolean desc) {
        if (orderByList == null) {
            orderByList = new ArrayList<OrderBy>(2);
        }
        orderByList.add(new OrderBy(columnName, desc));
        return this;
    }

    public Selector limit(int limit) {
        this.limit = limit;
        return this;
    }

    public Selector offset(int offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SELECT ");
        result.append("*");
        result.append(" FROM ").append(tableName);
        if (whereBuilder != null) {
            result.append(" WHERE").append(whereBuilder.toString());
        }
        if (orderByList != null) {
            for (int i = 0; i < orderByList.size(); i++) {
                result.append(" ORDER BY ").append(orderByList.get(i).toString());
            }
        }
        if (limit > 0) {
            result.append(" LIMIT ").append(limit);
            result.append(" OFFSET ").append(offset);
        }
        return result.toString();
    }

    protected class OrderBy {
        private String columnName;
        private boolean desc;

        public OrderBy(String columnName) {
            this.columnName = columnName;
        }

        public OrderBy(String columnName, boolean desc) {
            this.columnName = columnName;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return columnName + (desc ? " DESC" : " ASC");
        }
    }

}
