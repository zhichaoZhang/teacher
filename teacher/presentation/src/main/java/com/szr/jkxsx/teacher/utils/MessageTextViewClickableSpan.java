package com.szr.jkxsx.teacher.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import timber.log.Timber;

/**
 * Created by fengruicong on 15-6-2.
 */
public class MessageTextViewClickableSpan extends ClickableSpan {
    public static final int CLICK_TYPE_USER = 0;
    public static final int CLICK_TYPE_NONE = -1;
    private int clickType;
//    private String clickId;
//    private Context context;
    private int color;

    public MessageTextViewClickableSpan(Context context, int clickType, String clickId) {
        this.clickType = clickType;
//        this.clickId = clickId;
//        this.context = context;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void onClick(View widget) {
        switch (clickType) {
            //跳转到个人页面
            case CLICK_TYPE_USER:
                Timber.i("个人页还未开发。。。");
//                IntentHelper.getInstance().startPersonalActivity(widget.getContext(), clickId);
                break;
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
        if (color == 0) {
            ds.setColor(Color.BLACK);
        } else {
            ds.setColor(color);
        }
    }
}
