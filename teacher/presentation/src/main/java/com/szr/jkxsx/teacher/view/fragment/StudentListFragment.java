package com.szr.jkxsx.teacher.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseListFragment;
import com.szr.jkxsx.teacher.view.adapter.StudentListAdapter;
import com.szr.jkxsx.teacher.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生列表界面
 *
 * Created by zczhang on 15/10/18.
 */
public class StudentListFragment extends BaseListFragment {
    private LinearLayoutManager mLayoutManager = null;
    private StudentListAdapter mAdapter = null;
    public static StudentListFragment newInstance() {
        StudentListFragment studentListFragment = new StudentListFragment();
        return studentListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_list, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initialize() {
        mAdapter = new StudentListAdapter(getContext());
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        List<StudentViewModel> teacherViewModelList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            StudentViewModel teacherViewModel = new StudentViewModel();
            teacherViewModelList.add(teacherViewModel);
        }
        mAdapter.setViewModels(teacherViewModelList);
    }
}
