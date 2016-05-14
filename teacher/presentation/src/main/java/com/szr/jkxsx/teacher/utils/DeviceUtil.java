package com.szr.jkxsx.teacher.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by yamlee on 15/8/6.
 */
public class DeviceUtil {
    private static final String UNIQUEID = "UNIQUEID";
    private static String sID = null;

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取系统sdk级别
     * @return
     */
    public static int getOsSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备名称
     * @return
     */
    public static String getDeviceName() {
        return Build.MODEL;
    }

    public static String getDeviceId(Context context) {
        if (context == null) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = telephonyManager.getSimSerialNumber();
        }
        if (TextUtils.isEmpty(deviceId)) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String macAddress = wifiManager.getConnectionInfo().getMacAddress();
            deviceId = EnDecodeUtil.md5(macAddress);
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        if(TextUtils.isEmpty(deviceId)){
            deviceId = EnDecodeUtil.md5(uniqueId(context));
        }
        return deviceId;
    }

    public synchronized static String uniqueId(Context context) {
        if (sID == null) {
            File uniqueid = new File(context.getFilesDir(), UNIQUEID);
            try {
                if (!uniqueid.exists())
                    writeUniqueidFile(uniqueid);
                sID = readUniqueidFile(uniqueid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static String readUniqueidFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeUniqueidFile(File installation) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(installation);
            String id = UUID.randomUUID().toString();
            out.write(id.getBytes());
        } finally {
            if(out != null) {
                out.close();
            }
        }

    }
}
