package com.shine.mobilenurse.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(DBConstants.SQL_OPTION);
//            db.execSQL(DBConstants.SQL_TUser);
//            db.execSQL(DBConstants.SQL_TMember);
//            db.execSQL(DBConstants.SQL_MainPage);
//            db.execSQL(DBConstants.SQL_TNews);
//            db.execSQL(DBConstants.SQL_TNotice);
//            db.execSQL(DBConstants.SQL_TSChat);
//            db.execSQL(DBConstants.SQL_TGChat);
//            db.execSQL(DBConstants.SQL_TSvr);
//            db.execSQL(DBConstants.SQL_TRecord);
//            db.execSQL(DBConstants.SQL_TBSugar);
//            db.execSQL(DBConstants.SQL_TBPressure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO更改数据库版本的操作
    }
}
