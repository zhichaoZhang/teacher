package com.szr.jkxsx.teacher.views;

/**
 * 基础的视图逻辑的抽象
 *
 * Created by zczhang on 15/10/8.
 */
public interface BaseView {
    /**
     * 显示一个消息提醒
     *
     * @param msg
     */
    void onMessage(String msg);

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 显示自定义文案提示
     * @param msg
     * @param canceledOnTouchOutside
     */
    void showLoading(String msg, boolean canceledOnTouchOutside);


    /**
     * 隐藏加载动画
     */
    void hideLoading();
}
