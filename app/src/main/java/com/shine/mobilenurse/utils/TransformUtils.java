package com.shine.mobilenurse.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zzw on 2016/11/21.
 * 描述:
 */

public class TransformUtils {

//    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> observable) {
//                return observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
//            }
//        };
//    }
//
//    public static <T> Observable.Transformer<T, T> all_io() {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> observable) {
//                return observable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
//            }
//        };
//    }
}
