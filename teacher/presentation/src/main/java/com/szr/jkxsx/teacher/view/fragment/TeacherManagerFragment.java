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
import com.szr.jkxsx.teacher.view.adapter.TeacherManagerListAdapter;
import com.szr.jkxsx.teacher.viewmodel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师管理页面
 *
 * Created by zczhang on 15/12/27.
 */
public class TeacherManagerFragment extends BaseListFragment{
    private LinearLayoutManager mLayoutManager = null;
    private TeacherManagerListAdapter mAdapter = null;

    public static TeacherManagerFragment newInstance() {
        TeacherManagerFragment teacherManagerFragment = new TeacherManagerFragment();
        return teacherManagerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teacher_manager, null);
    }

    @Override
    public void initialize() {
        mAdapter = new TeacherManagerListAdapter(getContext());
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        List<TeacherViewModel> teacherViewModelList = new ArrayList<>();
        for(int i=0; i<10; i++) {
            TeacherViewModel teacherViewModel = new TeacherViewModel();
            teacherViewModelList.add(teacherViewModel);
        }
        mAdapter.setViewModels(teacherViewModelList);
    }
}
