package com.shine.mobilenurse.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.shine.mobilenurse.db.sql.Selector;
import com.shine.mobilenurse.db.sql.WhereBuilder;
import com.shine.mobilenurse.entity.BaseEntity;
import com.shine.mobilenurse.utils.LogPrint;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class BaseDao {
    protected final String TAG = "BaseDao";

    protected Context context;
    protected DBOpenHelper dbOpenHelper;

    public BaseDao(Context context) {
        this.context = context;
        dbOpenHelper = new DBOpenHelper(context);
    }

    public SQLiteDatabase getDataBase() {
        SQLiteDatabase myDB;
        try {
            myDB = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException exception) {
            myDB = dbOpenHelper.getReadableDatabase();
        }
        return myDB;
    }

    public synchronized <T extends BaseEntity> T findFirst(Selector selector) {
        List<T> list = findAll(selector);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public synchronized <T extends BaseEntity> List<T> findAll(Selector selector) {
        String sql = selector.toString();
        LogPrint.log_d(TAG, "查询SQL:" + sql);

        List<T> list = new ArrayList<T>();

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbOpenHelper.getReadableDatabase();
            db.beginTransaction();
            cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Object obj = selector.getEntity().newInstance();
                for (Field field : selector.getEntity().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getGenericType().toString().toUpperCase().equals(String.class.toString().toUpperCase())) {
                        field.set(obj, cursor.getString(cursor.getColumnIndex(field.getName().toUpperCase())));
                    } else if (field.getGenericType().toString().toUpperCase().equals(Integer.class.toString().toUpperCase())) {
                        field.set(obj, cursor.getInt(cursor.getColumnIndex(field.getName().toUpperCase())));
                    }
                }
                list.add((T) obj);
            }
            LogPrint.log_d(TAG, selector.getEntity().getSimpleName() + " 查询到条目数：" + list.size());
            cursor.close();
            db.endTransaction();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogPrint.log_d(TAG, "查询" + selector.getEntity().getSimpleName() + "时发生错误！");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return list;
    }

    public synchronized <T extends BaseEntity> boolean insert(T t) {
        if (t == null) {
            return false;
        }

        List<T> list = new ArrayList<T>();
        list.add(t);
        return insert(list);
    }

    public synchronized <T extends BaseEntity> boolean insert(List<T> list) {
        if (list != null && list.isEmpty()) {
            return false;
        }

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            int ret = 0;
            for (T t : list) {
                String tableName = t.getClass().getSimpleName();
                ContentValues contentValues = new ContentValues();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String key = field.getName();
                    String value = field.get(t) == null ? "" : field.get(t).toString();
                    contentValues.put(key, value);
                }

                LogPrint.log_d(TAG, tableName + " 插入数据[" + contentValues.toString() + "]");
                if (db.insert(tableName, null, contentValues) > 0) {//返回新添记录的行号
                    ret++;
                }
            }
            db.setTransactionSuccessful();//事务成功标志
            LogPrint.log_d(TAG, String.format("受影响条数:%d, %s！", ret, ret == list.size() ? "插入成功" : "插入失败"));
            return ret == list.size();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogPrint.log_d(TAG, "插入时发生错误！");
        } finally {
            db.endTransaction();
            db.close();
        }
        return false;
    }

    protected synchronized <T extends BaseEntity> boolean update(T t, WhereBuilder sWhere) {
        if (t == null || sWhere == null) {
            return false;
        }

        List<T> list = new ArrayList<T>();
        list.add(t);
        return update(list, sWhere);
    }

    public synchronized <T extends BaseEntity> boolean update(List<T> list, WhereBuilder sWhere) {
        if (list != null && list.isEmpty() || sWhere == null) {
            return false;
        }

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            long ret = 0;
            for (T t : list) {
                String tableName = t.getClass().getSimpleName();
                ContentValues contentValues = new ContentValues();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String key = field.getName();
                    String value = field.get(t) == null ? "" : field.get(t).toString();
                    contentValues.put(key, value);
                }

                LogPrint.log_d(TAG, tableName + " 修改数据[" + contentValues.toString() + "]");
                if (db.update(tableName, contentValues, sWhere.toString(), null) > 0) {
                    ret++;
                }
            }
            db.setTransactionSuccessful();//事务成功标志
            LogPrint.log_d(TAG, String.format("受影响条数:%d, %s！", ret, ret == list.size() ? "修改成功" : "修改失败"));
            return ret == list.size();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogPrint.log_d(TAG, "修改时发生错误！");
        } finally {
            db.endTransaction();
            db.close();
        }
        return false;
    }

    public synchronized <T extends BaseEntity> boolean replace(T t) {
        if (t == null) {
            return false;
        }

        List<T> list = new ArrayList<T>();
        list.add(t);
        return replace(list);
    }

    public synchronized <T extends BaseEntity> boolean replace(List<T> list) {
        if (list != null && list.isEmpty()) {
            return false;
        }

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            long ret = 0;
            for (T t : list) {
                String tableName = t.getClass().getSimpleName();
                ContentValues contentValues = new ContentValues();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String key = field.getName();
                    String value = field.get(t) == null ? "" : field.get(t).toString();
                    contentValues.put(key, value);
                }

                LogPrint.log_d(TAG, tableName + " 插入或更新数据[" + contentValues.toString() + "]");
                if (db.replace(tableName, null, contentValues) > 0) {
                    ret++;
                }
            }
            db.setTransactionSuccessful();//事务成功标志
            LogPrint.log_d(TAG, String.format("受影响条数:%d, %s！", ret, ret == list.size() ? "插入或更新成功" : "插入或更新失败"));
            return ret == list.size();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            LogPrint.log_d(TAG, "插入或更新时发生错误！");
        } finally {
            db.endTransaction();
            db.close();
        }
        return false;
    }

    protected synchronized boolean delete(Class<? extends BaseEntity> clazz, WhereBuilder sWhere) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            LogPrint.log_d(TAG, clazz.getSimpleName() + " 删除数据[" + sWhere.toString() + "]");
            int ret = db.delete(clazz.getSimpleName(), sWhere.toString(), null);
            db.setTransactionSuccessful();//事务成功标志
            LogPrint.log_d(TAG, String.format("受影响条数:%d, %s！", ret, ret > 0 ? "删除成功" : "删除失败"));
            return ret > 0;
        } catch (Exception e) {
            e.printStackTrace();
            LogPrint.log_d(TAG, "删除时发生错误！");
        } finally {
            db.endTransaction();
            db.close();
        }
        return false;
    }

    protected synchronized boolean clear(Class<? extends BaseEntity> clazz) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            String tableName = clazz.getSimpleName();
            LogPrint.log_d(TAG, "清空表:" + tableName);
            db.execSQL(String.format("delete from %s", tableName));
            db.setTransactionSuccessful();//事务成功标志
            LogPrint.log_d(TAG, "清空成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LogPrint.log_d(TAG, "清空时发生错误！");
        } finally {
            db.endTransaction();
            db.close();
        }
        return false;
    }
}
