package com.szr.jkxsx.teacher.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import timber.log.Timber;

/**
 * 闹钟相关工具类
 *
 * Created by zczhang on 15/8/14.
 */
public class AlarmUtils {

    /**
     * 设定一个订单未兑换定时提醒服务
     * @param context 上下文
     * @param timeDiff 距目标时间的时间差
     */
    public static void setOrderAlarm(Context context, String orderId,  long timeDiff) {
//        Intent intent = new Intent(context, AlarmReceiver.class);
//        intent.putExtra("order_id", orderId);
//        intent.setAction("com.qfpay.near.action.setalarm");
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, (int) (timeDiff / 1000));
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        Timber.i(context+"设置了一下提醒,提醒时间为"+timeDiff/1000+"s后");
    }
}
