package com.szr.jkxsx.teacher.presenters;

import com.szr.jkxsx.teacher.views.BaseView;

/**
 * 基础的视图控制器
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public interface BasePresenter<T extends BaseView> {
    /**
     * 为presenter设置页面控制接口
     * @param view
     */

    void setView(T view);

    /**
     * 在presenter构造完成后调用
     */
    void onCreate();

    /**
     * 在引用界面销毁时调用
     */
    void onDestroy();
}
