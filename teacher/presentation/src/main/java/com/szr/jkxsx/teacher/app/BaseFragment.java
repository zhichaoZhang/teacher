package com.szr.jkxsx.teacher.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szr.jkxsx.teacher.utils.Toaster;
import com.szr.jkxsx.teacher.views.BaseView;

import butterknife.ButterKnife;

/**
 * 基础的fragment
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void injectViews(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    public void onMessage(String msg) {
//        if(root != null) {
//            SnackBarUtils.showLongSnackBar(root, msg, "我知道了");
//        }
        Toaster.showLongToast(BaseApplication.getInstance(), msg);
    }

    @Override
    public void showLoading() {
        showLoading("正在加载...", true);
    }

    @Override
    public void showLoading(String msg, boolean canceledOnTouchOutside) {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    public abstract void initialize();
}
