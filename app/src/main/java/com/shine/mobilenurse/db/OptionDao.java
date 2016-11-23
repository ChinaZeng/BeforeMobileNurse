package com.shine.mobilenurse.db;

import android.content.Context;

import com.shine.mobilenurse.db.sql.Selector;

import java.util.List;


/**
 * Created by zzw on 2016/10/19.
 * 描述:
 */

public class OptionDao extends BaseDao {

    private volatile static OptionDao optionDao;

    private OptionDao(Context context) {
        super(context);
    }

    public static synchronized OptionDao getChatDao(Context context) {
        if (optionDao == null) {
            optionDao = new OptionDao(context);
        }
        return optionDao;
    }

//    public synchronized List<Option> findAll() {
//        return findAll(Selector.from(Option.class));
//    }
//
//    public synchronized List<Option> findTop() {
//        return findAll(Selector.from(Option.class).where("TYPE", 0));
//    }
//
//    public synchronized List<Option> findMid0() {
//        return findAll(Selector.from(Option.class).where("TYPE", 1));
//    }
//
//    public synchronized List<Option> findMid1() {
//        return findAll(Selector.from(Option.class).where("TYPE", 2));
//    }
//
//    public synchronized List<Option> findBottom() {
//        return findAll(Selector.from(Option.class).where("TYPE", 3));
//    }

}
