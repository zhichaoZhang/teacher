package com.szr.jkxsx.teacher.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseFragment;

/**
 * 学生注册界面
 *
 * Created by zczhang on 15/10/15.
 */
public class StudentRegisterFragment extends BaseFragment {

    public static StudentRegisterFragment newInstance() {
        StudentRegisterFragment studentRegisterFragment = new StudentRegisterFragment();
        return studentRegisterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_register, null);
    }

    @Override
    public void initialize() {

    }
}
