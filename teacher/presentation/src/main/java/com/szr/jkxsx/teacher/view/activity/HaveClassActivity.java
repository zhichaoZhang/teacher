package com.szr.jkxsx.teacher.view.activity;

import android.support.v4.app.Fragment;

/**
 * 上课界面
 *
 * Created by zczhang on 15/10/15.
 */
public class HaveClassActivity extends SingleFragmentActivity implements SingleFragmentActivity.ChildFragmentManager{
    @Override
    protected Fragment createFragment() {
        return null;
    }

    @Override
    public void changeFragment(Fragment fragment, boolean isAddBackStack, int enterAnim, int exitAnim) {

    }

    @Override
    public void backFragment() {

    }

    @Override
    public void closeActivity() {

    }
}
