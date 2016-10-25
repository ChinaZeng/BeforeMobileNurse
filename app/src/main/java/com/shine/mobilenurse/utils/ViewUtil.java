package com.shine.mobilenurse.utils;

import android.app.Activity;
import android.view.View;

/**
 * Created by zzw on 2016/9/27.
 * 描述:
 */

public class ViewUtil {

    @SuppressWarnings("unchecked")
    public static <T> T $(View view, int id) {
        try {
            return (T) view.findViewById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T $(Activity activity, int id) {
        try {
            return (T) activity.findViewById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
