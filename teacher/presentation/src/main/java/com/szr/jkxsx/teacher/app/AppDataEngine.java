package com.szr.jkxsx.teacher.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.szr.jkxsx.data.common.Constants;

/**
 * 基于SharePreference的全局数据存储引擎
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public class AppDataEngine {
    private SharedPreferences sharedPreferences;

    public AppDataEngine(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public int getScreenWidth() {
        return sharedPreferences.getInt("screen_width", 0);
    }

    public String getApiDomain() {
        return sharedPreferences.getString("api_domain", Constants.API_DOMAIN_JKXSX);
    }

    public String getUserId() {
        return "123";
    }
}
