package com.szr.jkxsx.teacher.utils;

import android.content.Context;

import com.szr.jkxsx.teacher.view.activity.CommonWebViewActivity;
import com.szr.jkxsx.teacher.view.activity.LoginActivity;
import com.szr.jkxsx.teacher.view.activity.RegisterActivity;
import com.szr.jkxsx.teacher.view.activity.StudentsActivity;
import com.szr.jkxsx.teacher.view.activity.SuperManagerActivity;


/**
 * intent跳转控制器
 * <p/>
 * Created by zczhang on 15-6-5.
 */
public class IntentHelper {
    private volatile static IntentHelper singleton;

    private IntentHelper() {
    }

    public static IntentHelper getInstance() {
        if (singleton == null) {
            synchronized (IntentHelper.class) {
                if (singleton == null) {
                    singleton = new IntentHelper();
                }
            }
        }
        return singleton;
    }

    /**
     * 跳转到登陆页面
     * @param context
     */
    public void startLoginActivity(Context context) {
        context.startActivity(LoginActivity.getCallingIntent(context));
    }

    /**
     * 跳转到老师注册界面
     * @param context
     */
    public void startTeacherRegisterActivity(Context context) {
        context.startActivity(RegisterActivity.getCallingIntent(context));
    }

    /**
     * 跳转到学生列表界面
     * @param context
     */
    public void startStudentsActivity(Context context) {
        context.startActivity(StudentsActivity.getCallingIntent(context));
    }

    /**
     * 跳转到管理员管理界面
     * @param context
     */
    public void startSuperManagerActivity(Context context) {
        context.startActivity(SuperManagerActivity.getCallingIntent(context));
    }

    /**
     * 跳转到一个通用的web页面
     *
     * @param context
     * @param url
     */
    public void startCommonWebViewActivity(Context context, String url) {
        context.startActivity(CommonWebViewActivity.getCallingIntent(context, url));
    }

}
