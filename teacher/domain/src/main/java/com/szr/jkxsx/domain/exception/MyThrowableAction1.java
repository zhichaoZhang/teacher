package com.szr.jkxsx.domain.exception;


import rx.functions.Action1;

/**
 * 统一处理错误消息的action
 * <p/>
 * Created by zcZhang on 15/6/10.
 */
public class MyThrowableAction1<Throwable> implements Action1<Throwable>{

    public MyThrowableAction1() {}

    @Override
    public void call(Throwable t) {
        if (t instanceof RequestException) {
            //如果是用户未登录，退出app重新登陆
//            if (((RequestException) t).getErrorCode().equals(Constants.SESSION_ERROR_CODE)) {
//                dialog = DialogUtils.getInstance().createSingleButtonDialog(context, "登录状态异常，请重新登录！", "确定", false, this, "提示");
//                if (dialog != null) {
//                    dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//                        @Override
//                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                                clickPositionButton();
//                            }
//                            return false;
//                        }
//                    });
//                }
//            }
        }
    }

}
