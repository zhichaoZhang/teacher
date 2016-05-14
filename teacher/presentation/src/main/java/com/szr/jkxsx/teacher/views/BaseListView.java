package com.szr.jkxsx.teacher.views;

/**
 * 基础的列表视图抽象逻辑
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public interface BaseListView extends BaseView {

    /**
     * 开始下拉刷新动画
     */
    void startRefresh();

    /**
     * 停止下拉刷新动画
     */
    void stopRefresh();

    /**
     * 显示加载更多动画
     */
    void showMoreProgress();

    /**
     * 隐藏加载更多动画
     */
    void hideMoreProgress();

    /**
     * 显示空白页面
     */
    void showEmptyView();

    /**
     * 显示错误页面
     */
    void showErrorView();

    /**
     * 隐藏空白页面
     */
    void hideEmptyView();

    /**
     * 设置页面提示
     *
     * @param tip
     */
    void getViewTip(String tip);

    /**
     * 设置错误或空数据图片
     * @param imgId
     */
    void getViewImg(int imgId);
}
