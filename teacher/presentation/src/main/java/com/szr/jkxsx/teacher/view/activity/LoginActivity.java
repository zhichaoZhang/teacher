package com.szr.jkxsx.teacher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.szr.jkxsx.teacher.view.fragment.LoginFragment;

/**
 * 登陆界面
 * <p/>
 * Created by zczhang on 15/10/11.
 */
public class LoginActivity extends SingleFragmentActivity implements SingleFragmentActivity.ChildFragmentManager{

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
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
