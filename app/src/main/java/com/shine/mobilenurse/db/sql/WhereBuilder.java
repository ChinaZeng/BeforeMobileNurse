package com.shine.mobilenurse.db.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */
public class WhereBuilder {

    private final List<String> wheres;

    private WhereBuilder() {
        this.wheres = new ArrayList<String>();
    }

    public static WhereBuilder b() {
        return new WhereBuilder();
    }

    public static WhereBuilder b(String columnName, Object value) {
        WhereBuilder result = new WhereBuilder();
        result.and(columnName, value);
        return result;
    }


    public WhereBuilder and(String columnName, Object value) {
        wheres.add(String.format(" AND " + columnName + " = '%s' ", value.toString()));
        return this;
    }

    public WhereBuilder or(String columnName, Object value) {
        wheres.add(String.format(" OR " + columnName + " = '%s' ", value.toString()));
        return this;
    }


    public WhereBuilder exp(String expr) {
        wheres.add(" " + expr);
        return this;
    }

    @Override
    public String toString() {
        if (wheres.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(" 1=1");
        for (String item : wheres) {
            sb.append(item);
        }
        return sb.toString();
    }
}
