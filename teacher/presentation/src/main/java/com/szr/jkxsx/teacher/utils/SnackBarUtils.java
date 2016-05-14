package com.szr.jkxsx.teacher.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 底部提示
 * <p/>
 * Created by zcZhang on 15/6/18.
 */
public class SnackBarUtils {
    public static void showLongSnackBar(View view, String msg, String actionTitle) {
        try {
            if (view != null && view.getContext() != null && msg != null && actionTitle != null) {
                Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                        .setAction(actionTitle, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showShortSnackBar(View view, String msg, String actionTitle) {
        try {
            if (view != null && view.getContext() != null && msg != null && actionTitle != null) {
                Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                        .setAction(actionTitle, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showClickSnackBar(View view, String msg, String actionTitle, View.OnClickListener clickListener) {
        try {
            Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction(actionTitle, clickListener).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
