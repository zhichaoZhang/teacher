package com.szr.jkxsx.teacher.app;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.view.widget.EmptyView;
import com.szr.jkxsx.teacher.views.BaseListView;

import butterknife.InjectView;
import butterknife.Optional;

/**
 * 带列表的fragment基类
 *
 * Created by zczhang on 15/10/15.
 */
public abstract class BaseListFragment extends BaseFragment implements BaseListView {
    @Optional
    @InjectView(R.id.recycler_view)
    public RecyclerView recyclerView;
    @Optional
    @InjectView(R.id.swipe_refresh_layout)
    public SwipeRefreshLayout swipeRefreshLayout;
    @Optional
    @InjectView(R.id.pb_loading_more)
    View loadMore;
    @Optional
    @InjectView(R.id.empty_view)
    EmptyView emptyView;

    @Override
    public void startRefresh() {
        if(swipeRefreshLayout != null) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void stopRefresh() {
        if(swipeRefreshLayout != null) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void showMoreProgress() {
        loadMore.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMoreProgress() {
        loadMore.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void getViewTip(String tip) {

    }

    @Override
    public void getViewImg(int imgId) {

    }
}
