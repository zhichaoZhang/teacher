package com.szr.jkxsx.teacher.utils;

import android.content.Intent;
import android.text.TextUtils;

import java.util.HashMap;

public class GlobalParamsUtil {
    public HashMap<String, Object> mMaParams;

    private static volatile GlobalParamsUtil mGpUtil = null;

    private GlobalParamsUtil() {
        mMaParams = new HashMap<String, Object>();
    }

    public static GlobalParamsUtil getInstance() {
        if (mGpUtil == null) {
            synchronized (GlobalParamsUtil.class) {
                if (mGpUtil == null) {
                    mGpUtil = new GlobalParamsUtil();
                }
            }
        }
        return mGpUtil;
    }

    public String push(Object v) {
        synchronized (this) {
            for (int i = 0; ; i++) {
                String key = Integer.toString(i);

                if (mMaParams.containsKey(key))
                    continue;

                mMaParams.put(key, v);
                return key;
            }
        }
    }

    public Object get(String key) {
        synchronized (this) {
            Object val = null;

            if (mMaParams.containsKey(key))
                val = mMaParams.remove(key);

            return val;
        }
    }

    public String push(String key, Object v) {
        synchronized (this) {
            mMaParams.put(key, v);
            return key;
        }
    }

    /**
     * 将参数加入Intent中
     *
     * @param key
     * @param obj
     * @param intent
     */
    public void addGlobalParamToIntent(String key, Object obj, Intent intent) {
        String globalParamKey = GlobalParamsUtil.getInstance().push(obj);
        intent.putExtra(key, globalParamKey);
    }

    /**
     * 从Intent中取得Global的参数，失败返回null
     *
     * @param key
     * @param intent
     * @return null if not succeed
     */
    public Object getGlobalParamFromIntent(String key, Intent intent) {
        String globalParamKey = intent.getStringExtra(key);
        if (TextUtils.isEmpty(globalParamKey)) {
            return null;
        } else {
            return GlobalParamsUtil.getInstance().get(globalParamKey);
        }
    }
}
