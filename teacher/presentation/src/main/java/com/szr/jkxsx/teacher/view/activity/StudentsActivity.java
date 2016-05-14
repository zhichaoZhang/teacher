package com.szr.jkxsx.teacher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.szr.jkxsx.teacher.view.fragment.StudentListFragment;

/**
 * 学生列表界面
 *
 * Created by zczhang on 15/10/15.
 */
public class StudentsActivity extends SingleFragmentActivity implements SingleFragmentActivity.ChildFragmentManager{

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, StudentsActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return StudentListFragment.newInstance();
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
