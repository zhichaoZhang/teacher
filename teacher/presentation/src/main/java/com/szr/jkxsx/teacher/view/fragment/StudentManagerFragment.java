package com.szr.jkxsx.teacher.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseFragment;

/**
 * Created by zczhang on 15/12/27.
 */
public class StudentManagerFragment extends BaseFragment{

    public static StudentManagerFragment newInstance() {
        StudentManagerFragment studentManagerFragment = new StudentManagerFragment();
        return studentManagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_manager, null);
    }

    @Override
    public void initialize() {

    }
}
