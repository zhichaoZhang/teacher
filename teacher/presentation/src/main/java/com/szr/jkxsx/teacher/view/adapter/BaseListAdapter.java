package com.szr.jkxsx.teacher.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 列表适配器基类
 *
 * Created by zcZhang on 15/6/12.
 */
public class BaseListAdapter extends RecyclerView.Adapter{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
