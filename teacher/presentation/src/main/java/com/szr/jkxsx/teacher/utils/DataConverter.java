package com.szr.jkxsx.teacher.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fengruicong on 15-6-2.
 */
public class DataConverter {
    /**
     * 根据商品创建返回固定的产品描述
     */
    public static String getDateConverter(String timestamp, boolean showMinHour, String serverTime) {
        String timeDes = "";
        try {
            Date now;
            SimpleDateFormat df;
            if (showMinHour) {
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                df = new SimpleDateFormat("yyyy-MM-dd");
            }
            if (TextUtils.isEmpty(serverTime)) {
                now = new Date(System.currentTimeMillis());
            } else {
                now = df.parse(serverTime);
            }
            Date modelDate = df.parse(timestamp);
            Calendar nowCalendar = Calendar.getInstance();
            Calendar modelCalendar = DateUtil.getCalendar(modelDate);
            int dayX = nowCalendar.get(Calendar.DAY_OF_YEAR) - modelCalendar.get(Calendar.DAY_OF_YEAR);//DateUtil.getDayOfMonth(nowCalendar) - DateUtil.getDayOfMonth(modelCalendar);
            int yearX = DateUtil.getYear(nowCalendar) - DateUtil.getYear(modelCalendar);
            int monthX = DateUtil.getMonth(nowCalendar) - DateUtil.getMonth(modelCalendar);
            //仅对1天以内的时间做显示的特殊处理
            if (dayX < 1 && yearX == 0 && monthX == 0) {
                if (showMinHour) {
                    long l = now.getTime() - modelDate.getTime();
                    long day = l / (24 * 60 * 60 * 1000);
                    long hour = (l / (60 * 60 * 1000) - day * 24);
                    long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
                    if (hour < 1) {
                        if (min == 0) {
                            timeDes = "刚刚";
                        } else {
                            timeDes = min + "分钟前";
                        }
                    } else {
                        timeDes = hour + "小时前";
                    }
                } else {
                    timeDes = "今天";
                }
            } else if (dayX == 1 && yearX == 0) {
                timeDes = "昨天";
            } else if (yearX == 0) {
//                timeDes = DateUtil.getMonth(modelCalendar) + "月" + DateUtil.getDayOfMonth(modelCalendar) + "日  周" + DateUtil.getDayOfWeekCustom(modelCalendar);
                timeDes = DateUtil.getMonth(modelCalendar) + "月" + DateUtil.getDayOfMonth(modelCalendar) + "日";
            } else {
                timeDes = DateUtil.getYear(modelCalendar) + "年" +
                        DateUtil.getMonth(modelCalendar) + "月" + DateUtil.getDayOfMonth(modelCalendar) + "日";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeDes;
    }

    /**
     * 单个单词
     */
    public static SpannableString getSingleSpannableString(Context context, String sourceStr, String patternStr, int clickType, String clickId, int color) {
        SpannableString spannableString = new SpannableString(sourceStr);
        if (TextUtils.isEmpty(patternStr)) {
            return spannableString;
        }
        MessageTextViewClickableSpan span = new MessageTextViewClickableSpan(context, clickType, clickId);
        span.setColor(color);
        int start = 0, end = 0;
        if (sourceStr.contains(patternStr)) {
            start = sourceStr.indexOf(patternStr);
            end = start + patternStr.length();
        }
        spannableString.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableString;
    }

    public static SpannableString getSpannableString(String sourceStr) {
        SpannableString spannableString = new SpannableString(sourceStr);
        return spannableString;
    }

    /**
     * 多个加粗设置
     */
    public static void setSpan(SpannableString spannableString, Context context, String sourceStr, String patternStr, int clickType, String clickId, int color) {
        int start, end = 0;
        while (end <= sourceStr.length()) {
            MessageTextViewClickableSpan span = new MessageTextViewClickableSpan(context, clickType, clickId);
            span.setColor(color);
            String nextSourceStr = sourceStr.substring(end, sourceStr.length());
            if (nextSourceStr.contains(patternStr)) {
                start = nextSourceStr.indexOf(patternStr) + end;
                end = start + patternStr.length();
            } else {
                break;
            }
            spannableString.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
    }

    public static void setSpan(SpannableString spannableString, Context context, int start, int end, int clickType, String clickId, int color) {
        MessageTextViewClickableSpan span = new MessageTextViewClickableSpan(context, clickType, clickId);
        span.setColor(color);
        spannableString.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }
}
