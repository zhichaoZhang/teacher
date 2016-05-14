package com.szr.jkxsx.data.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 文件操作工具类
 * <p/>
 * Created by zcZhang on 15/6/3.
 */
public class FileUtils {

    /**
     * 向指定文件中写入内容
     *
     * @param content
     * @param path
     * @param fileName
     * @return
     */
    public static boolean write2File(String content, String path, String fileName) {
        OutputStreamWriter writer = null;
        BufferedWriter bufferedWriter = null;
//        if (getAvailableExternalMemorySize() < content.length()) {//手机内部存储容量不足，并且外部存储不可用，直接返回
//            System.out.println("存储空间不足！");
//            throw new RequestException("存储空间不足,数据缓存失败！");
//        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new File(path, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static String readFromFile(String filePath, String fileName) {
        String result = "";
        BufferedReader bufferedReader = null;
        try {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } else {
                return result;
            }
            result = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
