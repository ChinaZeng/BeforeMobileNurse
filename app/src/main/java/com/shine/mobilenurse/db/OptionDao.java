package com.shine.mobilenurse.db;

import android.content.Context;

import com.shine.mobilenurse.db.sql.Selector;
import com.shine.mobilenurse.db.sql.WhereBuilder;
import com.shine.mobilenurse.entity.Option;

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

    public static synchronized OptionDao getInstance(Context context) {
        if (optionDao == null) {
            optionDao = new OptionDao(context);
        }
        return optionDao;
    }

    public synchronized List<Option> findAll() {
        return findAll(Selector.from(Option.class));
    }

    public synchronized List<Option> findTop() {
        return findAll(Selector.from(Option.class).where("TYPE", 0).orderBy("SORT"));
    }

    public synchronized List<Option> findMid() {
        return findAll(Selector.from(Option.class).where("TYPE", 1).orderBy("SORT"));
    }

    public synchronized List<Option> findBottom() {
        return findAll(Selector.from(Option.class).where("TYPE", 2).orderBy("SORT"));
    }

    public synchronized List<Option> findNotShow() {
        return findAll(Selector.from(Option.class).where("TYPE", 3).orderBy("SORT"));
    }


    public synchronized List<Option> findShow() {
        return findAll(Selector.from(Option.class).where(WhereBuilder.b().andNot("TYPE", 3)).orderBy("SORT"));
    }

}
