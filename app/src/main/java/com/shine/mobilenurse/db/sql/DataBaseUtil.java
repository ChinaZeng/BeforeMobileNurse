package com.shine.mobilenurse.db.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.shine.mobilenurse.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */
public class DataBaseUtil {
        public static final String TAG = "DatabaseUtil";
        /**数据库表操作类型*/
        public enum OPERATION_TYPE{
            /**表新增字段*/
            ADD,
            /**表删除字段*/
            DELETE,

            ERROR
        }
        /**
         * 升级表，增加字段
         * @param db
         * @param clazz
         */
        public static <T extends BaseEntity> void upgradeTable(SQLiteDatabase db, T clazz, OPERATION_TYPE type){
            String tableName = extractTableName(clazz);

            db.beginTransaction();
            try {

                //重命名表
                String tempTableName = tableName + "_temp";
                String sql = "ALTER TABLE" +tableName+ "RENAME TO" +tempTableName;
                db.execSQL(sql);

                //创建表
                try {
                    sql = getCreateTableStatements(clazz);
                    db.execSQL(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                    createTable(db,clazz);
                }

                //Load data
                String columns;
                if(type == OPERATION_TYPE.ADD){
                    columns = Arrays.toString(getColumnNames(db, tempTableName)).replace("[","").replace("]","");
                }else if(type == OPERATION_TYPE.DELETE){
                    columns = Arrays.toString(getColumnNames(db,tableName)).replace("[","").replace("]","");
                }else {
                    throw new IllegalArgumentException();
                }
                sql = "INSERT INTO "+tableName +
                        "("+ columns+")" +
                       " SELECT "+ columns+" FROM "+tempTableName;
                db.execSQL(sql);

                //Drop temp table
                sql = "DROP TABLE IF EXISTS" +tempTableName;
                db.execSQL(sql);

                db.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                db.endTransaction();
            }
        }


        /**
         * 获取表名
         */
        private static <t extends BaseEntity> String extractTableName(t clazz) {
            return clazz.getClass().getSimpleName();
        }

        /**
         * 获取表的列名
         * @param db
         * @param tableName
         * @return
         */
        public static String[] getColumnNames(SQLiteDatabase db,String tableName){
            String[] columnNames = null;
            Cursor cursor = null;
            try {
                cursor = db.rawQuery("PRAGMA table_info("+tableName+")",null);
                if(cursor != null){
                    int columnIndex = cursor.getColumnIndex("name");
                    if(columnIndex == -1){
                        return null;
                    }

                    int index = 0;
                    columnNames = new String[cursor.getCount()];
                    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                        columnNames[index] = cursor.getString(columnIndex);
                        index++;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(cursor != null) {
                    cursor.close();
                }
            }
            return columnNames;
        }


        public static <T extends BaseEntity> String getCreateTableStatements(T tClass){
            StringBuffer sql = new StringBuffer("create table "+tClass.getClass().getSimpleName()+"(");
            Field[] fields = tClass.getClass().getDeclaredFields();
            for(Field field:fields){
                sql.append(field.getName()+" TEXT,");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(")");
            return sql.toString();
        }

        private static <T>  void createTable(SQLiteDatabase db,T t){
            db.execSQL("");
        }
}
