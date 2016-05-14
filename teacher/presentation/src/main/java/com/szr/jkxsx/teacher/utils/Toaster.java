package com.szr.jkxsx.teacher.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 自定义Toast提示
 * Created by zcZhang on 15/3/26.
 */
public class Toaster {
    private static Toast toast;

    public static void showLongToast(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
        }
        toast.setText(msg);
        toast.show();
    }

    public static void showShortToast(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

}
