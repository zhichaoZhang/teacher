package com.szr.jkxsx.teacher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseActivity;
import com.szr.jkxsx.teacher.app.BaseFragment;
import com.szr.jkxsx.teacher.view.fragment.StudentManagerFragment;
import com.szr.jkxsx.teacher.view.fragment.StudentRegisterFragment;
import com.szr.jkxsx.teacher.view.fragment.SyllabusFragment;
import com.szr.jkxsx.teacher.view.fragment.TeacherManagerFragment;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 管理员管理页面
 * <p/>
 * Created by zczhang on 15/12/6.
 */
public class SuperManagerActivity extends BaseActivity {
    @InjectView(R.id.ll_menu_item_new_student)
    LinearLayout llMenuItemNewStudent;
    @InjectView(R.id.ll_menu_item_student_manager)
    LinearLayout llMenuItemStudentManager;
    @InjectView(R.id.ll_menu_item_teacher_manager)
    LinearLayout llMenuItemTeacherManager;
    @InjectView(R.id.ll_menu_item_syllabus)
    LinearLayout llMenuItemSyllabus;
    private WeakReference<BaseFragment> teacherManagerFragmentWeakReference;
    private WeakReference<BaseFragment> newStudentFragmentWeakReference;
    private WeakReference<BaseFragment> studentManagerFragmentWeakReference;
    private WeakReference<BaseFragment> syllabusFragmentWeakReference;
    private BaseFragment currentFragment = null;//当前正在显示的fragment

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SuperManagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_manager);
        ButterKnife.inject(this);
        clickItemNewStudent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        teacherManagerFragmentWeakReference = null;
        newStudentFragmentWeakReference = null;
        studentManagerFragmentWeakReference = null;
        syllabusFragmentWeakReference = null;
    }

    private void showFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fragments_transition_in, R.anim.fragments_transition_out);
        //隐藏之前显示的fragment
        if (currentFragment != null && currentFragment != fragment) {
            transaction.hide(currentFragment);
        }
        if (currentFragment != fragment) {
            this.currentFragment = fragment;
            //需要显示的fragment
            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(R.id.fl_super_manager_fragment_container, fragment);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    //新学员
    @OnClick(R.id.ll_menu_item_new_student)
    void clickItemNewStudent() {
        if (newStudentFragmentWeakReference == null || newStudentFragmentWeakReference.get() == null) {
            newStudentFragmentWeakReference = new WeakReference<BaseFragment>(StudentRegisterFragment.newInstance());
        }
        showFragment(newStudentFragmentWeakReference.get());
        unSelectAllItem();
        llMenuItemNewStudent.setSelected(true);
    }

    //管理学员
    @OnClick(R.id.ll_menu_item_student_manager)
    void clickItemStudentManager() {
        if (studentManagerFragmentWeakReference == null || studentManagerFragmentWeakReference.get() == null) {
            studentManagerFragmentWeakReference = new WeakReference<BaseFragment>(StudentManagerFragment.newInstance());
        }
        showFragment(studentManagerFragmentWeakReference.get());
        unSelectAllItem();
        llMenuItemStudentManager.setSelected(true);
    }

    //教师管理
    @OnClick(R.id.ll_menu_item_teacher_manager)
    void clickItemTeacherManager() {
        if (teacherManagerFragmentWeakReference == null || teacherManagerFragmentWeakReference.get() == null) {
            teacherManagerFragmentWeakReference = new WeakReference<BaseFragment>(TeacherManagerFragment.newInstance());
        }
        showFragment(teacherManagerFragmentWeakReference.get());
        unSelectAllItem();
        llMenuItemTeacherManager.setSelected(true);
    }

    //课程表
    @OnClick(R.id.ll_menu_item_syllabus)
    void clickItemSyllabus() {
        if (syllabusFragmentWeakReference == null || syllabusFragmentWeakReference.get() == null) {
            syllabusFragmentWeakReference = new WeakReference<BaseFragment>(SyllabusFragment.newInstance());
        }
        showFragment(syllabusFragmentWeakReference.get());
        unSelectAllItem();
        llMenuItemSyllabus.setSelected(true);
    }

    private void unSelectAllItem() {
        llMenuItemNewStudent.setSelected(false);
        llMenuItemStudentManager.setSelected(false);
        llMenuItemTeacherManager.setSelected(false);
        llMenuItemSyllabus.setSelected(false);
    }
}
