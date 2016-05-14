package com.szr.jkxsx.teacher.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.utils.Toaster;
import com.szr.jkxsx.teacher.viewmodel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zczhang on 15/12/27.
 */
public class TeacherManagerListAdapter extends BaseListAdapter{
    private Context mContext;
    private List<TeacherViewModel> mViewModels;

    public TeacherManagerListAdapter(Context context) {
        this.mContext = context;
        mViewModels = new ArrayList<>();
    }

    public void setViewModels(List<TeacherViewModel> mViewModels) {
        this.mViewModels = mViewModels;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.viewholder_teacher_manager, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mViewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @OnClick(R.id.rl_root)
        void clickItem() {
            Toaster.showShortToast(mContext, "点击item");
        }
    }
}
