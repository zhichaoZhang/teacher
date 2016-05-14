package com.szr.jkxsx.teacher.view.takepicUtils;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/**
 * Created by fengruicong on 15-6-18.
 */
public class ImageUploadUtils {
    public static String getPICTURE_DIR() {
        return getSdcardPath() + "/szr/" + "pictures/";
    }

    public static String getThumbnailDir() {
        return getSdcardPath() + "/szr/" + "thumbnail/";
    }

    /**
     * 取sdcard的路径
     *
     * @return 如果Sdcard没有，或者不可写，返回null
     */
    public static String getSdcardPath() {
        if (haveSdcard()) {
            return Environment.getExternalStorageDirectory()
                    .getPath();
        } else {
            return null;
        }
    }

    /**
     * 是否有SDCARD
     *
     * @return 有SDCARD, 返回true, 否则返回false
     */
    public static boolean haveSdcard() {
        return (Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED));
    }

    /**
     * 图片压缩算法
     *
     * @param options        Bitmap.Options
     * @param minSideLength  最小显示区
     * @param maxNumOfPixels 你想要的宽度 * 你想要的高度
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    public static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 获取内存卡剩余空间
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getFreeSizeOfSDCard() {
        long SDFreeSize;//存储卡剩余空间大小 kb
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (18 <= Build.VERSION.SDK_INT) {
            SDFreeSize = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong() / 1024;
        } else {
            SDFreeSize = statFs.getAvailableBlocks() * statFs.getBlockSize() / 1024;
        }
        return SDFreeSize;
    }

    private static final char[] HEX_CHAR_ARRAY = "0123456789abcdef".toCharArray();
    // 32 bytes from sha-256 -> 64 hex chars.
    private static final char[] SHA_256_CHARS = new char[64];

    /**
     * Returns the hex string of the given byte array representing a SHA256 hash.
     */
    public static String sha256BytesToHex(byte[] bytes) {
        return bytesToHex(bytes, SHA_256_CHARS);
    }

    private static String bytesToHex(byte[] bytes, char[] hexChars) {
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_CHAR_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_CHAR_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
