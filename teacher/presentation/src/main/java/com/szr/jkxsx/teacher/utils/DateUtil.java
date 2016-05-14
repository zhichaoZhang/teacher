package com.szr.jkxsx.teacher.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fengruicong on 15-6-8.
 */
public class DateUtil {
    /***
     * 自定义每周日期展示样式
     *
     * @param modelCalendar
     * @return
     */
    public static String getDayOfWeekCustom(Calendar modelCalendar) {
        String mWay = getDayOfWeek(modelCalendar);
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mWay;
    }

    public static String getDayOfWeek(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
    }

    public static int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR) + 1;
    }

    public static Calendar getCalendar(Date modelDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(modelDate);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        return calendar;
    }

    public static String getFormatCurTime(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
