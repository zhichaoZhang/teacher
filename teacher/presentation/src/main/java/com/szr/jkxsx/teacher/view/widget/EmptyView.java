package com.szr.jkxsx.teacher.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szr.jkxsx.teacher.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 通用空白提示页
 *
 * Created by zczhang on 15/9/1.
 */
public class EmptyView extends RelativeLayout {
    @InjectView(R.id.iv_empty)
    ImageView ivEmpty;
    @InjectView(R.id.tv_empty)
    TextView tvEmpty;

    public EmptyView(Context context) {
        super(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_empty_page, this);
        ButterKnife.inject(this, view);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_empty_page, this);
        ButterKnife.inject(this, view);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_empty_page, this);
        ButterKnife.inject(this, view);
    }

    public void setEmptyImg(int resourceId) {
        ivEmpty.setImageResource(resourceId);
    }

    public void setEmptyText(String tip) {
        tvEmpty.setText(tip);
    }
}
