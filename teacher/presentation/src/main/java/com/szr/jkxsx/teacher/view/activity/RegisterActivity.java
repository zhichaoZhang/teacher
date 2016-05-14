package com.szr.jkxsx.teacher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.szr.jkxsx.teacher.view.fragment.RegisterFragment;


/**
 * 老师注册
 * <p/>
 * Created by zczhang on 15/10/15.
 */
public class RegisterActivity extends SingleFragmentActivity implements SingleFragmentActivity.ChildFragmentManager {

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RegisterActivity.class);
        return intent;
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

    @Override
    protected Fragment createFragment() {
        return RegisterFragment.newInstance();
    }
}
