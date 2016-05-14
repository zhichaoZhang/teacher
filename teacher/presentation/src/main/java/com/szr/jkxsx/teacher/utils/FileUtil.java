package com.szr.jkxsx.teacher.utils;

import android.os.Environment;

import com.szr.jkxsx.data.common.Constants;


/**
 * Created by fengruicong on 15-7-1.
 */
public class FileUtil {

    public static StringBuilder getFilePath() {
        StringBuilder filePath = new StringBuilder();//文件存储完整路径
        filePath.append(getExternalMemoryPath()).append(Constants.CACHE_FILE_PATH);
        return filePath;
    }

    public static String getExternalMemoryPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
}
