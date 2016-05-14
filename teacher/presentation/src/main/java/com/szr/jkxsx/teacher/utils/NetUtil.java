package com.szr.jkxsx.teacher.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yamlee on 15/8/6.
 */
public class NetUtil {
    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkNetworkStatus(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null
                || networkInfo.isConnectedOrConnecting() == false) {
            return false;
        }
        return true;
    }

    /**
     * 检查wifi是否连接
     *
     * @return
     */
    public static boolean isWifiConnect(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager == null) {
            return false;
        }
        NetworkInfo mWifi = connManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    /**
     * 检查移动网络是否连接
     *
     * @return
     */
    public static boolean is3GConnect(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null
                && connManager.getActiveNetworkInfo().isAvailable() == false) {
            return false;
        }
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE) {
            if (info.getSubtype() >= 5) {
                return info.isConnected();
            }
        }
        return false;
    }

}
