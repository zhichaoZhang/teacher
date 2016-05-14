package com.szr.jkxsx.teacher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.szr.jkxsx.data.common.Constants;

/**
 * 108253
 * Created by fengruicong on 15-5-28.
 */
public class GetuiPushReceiver extends BroadcastReceiver {
    private final int TYPE_BUSINESS = 0;
    private final int TYPE_TOPIC_SUCCESS = 21;
    private final int TYPE_TOPIC_FAILED = 22;
    private final int TYPE_REPLY_LIKE = 23;
    private final int TYPE_REPLY_COMMENT = 24;
    private final int TYPE_SALE_START = 25;
    private final int TYPE_COMMENT_REPLYD = 26;
    //目前通知栏可最多显示两条推送消息
    private int messageNotificationId1 = 1001;
    private int messageNotificationId2 = 1002;

    @Override
    public void onReceive(Context context, Intent intent) {
//        Bundle bundle = intent.getExtras();
//        Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));
////        String serverMessage = "";
////        optServerMessage(context, serverMessage);
//        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
//            case PushConsts.GET_MSG_DATA:
//                // 获取透传（payload）数据
//                byte[] payload = bundle.getByteArray("payload");
//                if (payload != null) {
//                    String data = new String(payload);
//                    Timber.i("收到的推送消息为：" + data);
//                    // 接收处理透传（payload）数据
//                    optServerMessage(context, data);
//                }
//                break;
//            case PushConsts.GET_CLIENTID:
//                // 获取ClientID(CID)
//                String cid = bundle.getString("clientid");
//                String preClientId = Constants.clientId;
//                //当前clientId为空或非空的clientId与最新获取的不一致则进行重新绑定
//                if (TextUtils.isEmpty(preClientId) || !preClientId.equals(cid)) {
//                    Constants.clientId = cid;
//                    Log.d("GetuiSdkDemo", "Got ClientID:" + cid);
//                    Log.d("GetuiSdkDemo", "Save ClientID:" + preClientId);
//                    /* 第三方应用需要将ClientID上传到第三方服务器，并且将当前用户帐号和ClientID进行关联，
//                    以便以后通过用户帐号查找ClientID进行消息推送。有些情况下ClientID可能会发生变化，为保证获取最新的ClientID，
//                    请应用程序在每次获取ClientID广播后，都能进行一次关联绑定 */
//                    PushPresenterImpl presenter = DaggerPresenterComponent.builder().build().pushPresenter();
//                    presenter.setContext(context);
//                    presenter.bindClient(cid);
//                } else {
//                    Log.d("GetuiSdkDemo", "clientid  已绑定");
//                }
//                break;
//            //添加其他case
//            //.........
//            default:
//                break;
//        }
    }

    private void optServerMessage(Context context, String serverMessage) {
//        AppConfigDataEngine dataEngine = NearApplication.getInstance().getAppConfigDataEngine();
//        //更新推送来的总数
//        dataEngine.updateMessageCount();
//        //未登录时不推送消息到通知栏
//        if (dataEngine.getUserId() != 0) {
//            //接收的所有消息均发送到通知栏
//            updateNotificatiion(context, serverMessage);
//        }
    }

    //更新通知栏
    private void updateNotificatiion(Context context, String serverMessage) {
//        PushMessage pushMessage = new Gson().fromJson(serverMessage, PushMessage.class);
//        String title = pushMessage.getTitle();
//        String content = pushMessage.getContent();
//        int type = pushMessage.getType();
//        int topicType = pushMessage.getTopic_type();
//        NotificationManager messageNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent messageIntent = new Intent();
//        messageIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        messageIntent.setComponent(new ComponentName(context.getPackageName(), "com.qfpay.near.view.activity.HomeActivity"));
//        messageIntent.putExtra("push", true);
//        messageIntent.putExtra("serverMessage", serverMessage);
//        PendingIntent messagePendingIntent = PendingIntent.getActivity(context, UUID.randomUUID().hashCode(), messageIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        int preNotifyId = getNotifyId(context);
//        //统计收到推送的次数
//        if (type == TYPE_REPLY_COMMENT || type == TYPE_COMMENT_REPLYD) {
//            MobclickAgent.onEvent(context, "message_open_app", "comment");
//        } else if (type == TYPE_REPLY_LIKE) {
//            MobclickAgent.onEvent(context, "message_open_app", "like");
//        } else if (type == TYPE_SALE_START && topicType == 1) {
//            MobclickAgent.onEvent(context, "message_open_app", "topic_sale");
//        } else if (type == TYPE_SALE_START && topicType == 0) {
//            MobclickAgent.onEvent(context, "message_open_app", "topic");
//        } else if (type == TYPE_TOPIC_SUCCESS || type == TYPE_TOPIC_FAILED) {
//            MobclickAgent.onEvent(context, "message_open_app", "check");
//        } else if (type == TYPE_BUSINESS) {
//            MobclickAgent.onEvent(context, "message_open_app", "business");
//        }
//        //评论相关
//        if (type == TYPE_REPLY_COMMENT || type == TYPE_COMMENT_REPLYD) {
//            title = pushMessage.getContent();
//            content = pushMessage.getComment_content();
//        } else if (TextUtils.isEmpty(title)) {
//            title = "好近";
//        }
//        Notification messageNotification = new NotificationCompat.Builder(context)
//                .setContentTitle(title)
//                .setContentText(content)
//                .setContentIntent(messagePendingIntent)
//                .setTicker(content)
//                .setShowWhen(true)
//                .setSmallIcon(DeviceUtil.getOsSdkInt() < 21 ? R.mipmap.ic_launcher : R.drawable.icon_none_alpha)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
//                .setAutoCancel(true)
//                .build();
//
//        if (preNotifyId != messageNotificationId1) {
//            messageNotificationManager.notify(messageNotificationId1, messageNotification);
//            setPreNotifyId(context, messageNotificationId1);
//        } else {
//            messageNotificationManager.notify(messageNotificationId2, messageNotification);
//            setPreNotifyId(context, messageNotificationId2);
//        }
    }

    private void setPreNotifyId(Context context, int notifyId) {
        SharedPreferences sp = context.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt("preNotifyId", notifyId).commit();
    }

    private int getNotifyId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getInt("preNotifyId", messageNotificationId2);
    }
}
