package com.szr.jkxsx.teacher.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.szr.jkxsx.teacher.utils.InputTypeUtil;

/**
 * 基础的activity
 * <p/>
 * Created by zczhang on 15/10/8.
 */
public class BaseActivity extends AppCompatActivity {
    private boolean touchCloseInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTouchCloseInput(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    protected void setTouchCloseInput(boolean touchCloseInput) {
        this.touchCloseInput = touchCloseInput;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchCloseInput) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) { // 类型为Down才处理，还有Move/Up之类的
                if (this.getCurrentFocus() != null) { // 获取当前焦点
                    InputTypeUtil.closeSoftKeyBoard(this, getCurrentFocus());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
