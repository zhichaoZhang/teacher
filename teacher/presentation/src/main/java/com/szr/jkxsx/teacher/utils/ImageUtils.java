package com.szr.jkxsx.teacher.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


import com.szr.jkxsx.teacher.app.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * 关于图片的工具类
 * <p/>
 * Created by zcZhang on 15/5/2.
 */
public class ImageUtils {
    public static final String AVATAR = "_avatar";//100*100
    public static final String MICRO = "_140";//暂无用
    public static final String SMALL = "_320";
    public static final String MIDDLE = "_320";//暂无用
    public static final String LARGE = "_640";
    public static final String HUGE = "_900";
    private static ImageUtils imageUtils;
    private static HashMap<String, Integer> imageSizeTypeMap;
    private static int screenWidth = BaseApplication.getInstance().getAppConfigDataEngine().getScreenWidth();

    public static ImageUtils newInstance() {
        if (imageUtils == null) {
            imageUtils = new ImageUtils();
            initHashMap(screenWidth);
        }
        return imageUtils;
    }

    /**
     * bitmap数据转字节数据
     *
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {

        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
//            if (needRecycle)
//                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                e.printStackTrace();
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }

    /**
     * 字节数组转Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    public String generateFormatSizeImageUrl(String url, String imageType) {
        if(url == null) {
            return url;
        }

        if (url.startsWith("http://7xia80.com1.z0.glb.clouddn.com")) {
            StringBuilder stringBuilder = new StringBuilder(url);
            stringBuilder.append("!").append(imageSizeTypeMap.get(imageType)).append("q75.jpg");
            return stringBuilder.toString();
        }

        if (url.contains("pic.qfpay.com")) {
            return url;
        }

        if (url.contains("dpfile")) {
            return url;
        }

        if (url.contains("haojin.in")) {
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        sb.append(imageType);
        return sb.toString();
    }

    /**
     * 根据不同屏幕分辨率设定不同页面的图片请求尺寸
     *
     * @param screenWidth
     */
    private static void initHashMap(int screenWidth) {
        imageSizeTypeMap = new HashMap<>();
        if (screenWidth >= 1080) {
            imageSizeTypeMap.put(MICRO, 100);
            imageSizeTypeMap.put(AVATAR, 200);
            imageSizeTypeMap.put(MIDDLE, 400);
            imageSizeTypeMap.put(LARGE, 600);
            imageSizeTypeMap.put(HUGE, 1200);
        } else if (screenWidth >= 640) {
            imageSizeTypeMap.put(MICRO, 100);
            imageSizeTypeMap.put(AVATAR, 200);
            imageSizeTypeMap.put(MIDDLE, 200);
            imageSizeTypeMap.put(LARGE, 400);
            imageSizeTypeMap.put(HUGE, 800);
        } else if (screenWidth >= 480) {
            imageSizeTypeMap.put(MICRO, 100);
            imageSizeTypeMap.put(AVATAR, 200);
            imageSizeTypeMap.put(MIDDLE, 200);
            imageSizeTypeMap.put(LARGE, 400);
            imageSizeTypeMap.put(HUGE, 600);
        } else if (screenWidth >= 320) {
            imageSizeTypeMap.put(MICRO, 100);
            imageSizeTypeMap.put(AVATAR, 100);
            imageSizeTypeMap.put(MIDDLE, 100);
            imageSizeTypeMap.put(LARGE, 200);
            imageSizeTypeMap.put(HUGE, 400);
        } else {
            imageSizeTypeMap.put(MICRO, 100);
            imageSizeTypeMap.put(AVATAR, 100);
            imageSizeTypeMap.put(MIDDLE, 100);
            imageSizeTypeMap.put(LARGE, 200);
            imageSizeTypeMap.put(HUGE, 400);
        }
    }
}
