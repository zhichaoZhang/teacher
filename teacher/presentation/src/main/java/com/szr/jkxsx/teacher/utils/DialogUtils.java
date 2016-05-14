package com.szr.jkxsx.teacher.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.szr.jkxsx.teacher.R;
import com.szr.jkxsx.teacher.app.BaseApplication;

/**
 * 对话框工具类
 * <p/>
 * Created by zcZhang on 15/6/10.
 */
public class DialogUtils {
    private volatile static DialogUtils singleton;
    private int choosePosition = 0;
    private Context mContext;
    public static DialogUtils getInstance() {
        if (singleton == null) {
            synchronized (DialogUtils.class) {
                if (singleton == null) {
                    singleton = new DialogUtils();
                }
            }
        }
        return singleton;
    }

    private DialogUtils(){
        mContext = BaseApplication.getInstance().getApplicationContext();
    }


    /**
     *
     *
     * @param context
     * @param content 内容
     * @param buttonText 按钮文字
     * @param isTouchOutClose 是否点击外部消失
     * @param clickListener 按钮的点击监听
     * @param title 标题
     * @return Dialog
     */

    /**
     * 对话框按钮点击事件
     */
    public interface DialogButtonClickListener {
        void clickPositionButton();

        void clickNavigationButton();
    }


    public interface WheelChooseListener {
        void clickConfirm(int position);
//        void clickConfirm(int position);
//        void clickCancle();
    }

    /**
     * 选择对话框点击事件监听
     */
    public interface SingleChooseClickListener {
        void onClick(DialogInterface dialog, int which);
    }

    /**
     * 创建一个选择对话框
     * @param context
     * @param items
     * @param title
     * @param icon
     * @param chooseClickListener
     * @return
     */
    public Dialog createChooseDialog(Context context, String[] items, String title, int icon, final SingleChooseClickListener chooseClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(icon != 0) {
            builder.setIcon(icon);
        }
        if(!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if(items == null) {
            Toaster.showShortToast(context, "内容不能为空!");
            items = new String[0];
        }
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(chooseClickListener != null) {
                    chooseClickListener.onClick(dialogInterface, i);
                }
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

}

