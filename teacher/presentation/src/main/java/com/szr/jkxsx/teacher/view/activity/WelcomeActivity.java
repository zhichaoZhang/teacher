package com.szr.jkxsx.teacher.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.szr.jkxsx.teacher.view.fragment.WelcomeFragment;

/**
 * 欢迎界面
 *
 * Created by zczhang on 15/12/6.
 */
public class WelcomeActivity extends SingleFragmentActivity implements SingleFragmentActivity.ChildFragmentManager{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return WelcomeFragment.newInstance();
    }

    @Override
    public void changeFragment(Fragment fragment, boolean isAddBackStack, int enterAnim, int exitAnim) {
        showFragment(fragment, isAddBackStack, enterAnim, exitAnim);
    }

    @Override
    public void backFragment() {
        back2LastFragmentIfExist();
    }

    @Override
    public void closeActivity() {
        this.finish();
    }
}
