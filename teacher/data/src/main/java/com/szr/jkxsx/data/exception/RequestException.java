package com.szr.jkxsx.data.exception;


import com.szr.jkxsx.data.common.Constants;

/**
 * 网络请求过程中的异常信息, 包括连接失效, 超时等等
 *
 */
public class RequestException extends RuntimeException {
    private String mErrorMessage = "";
    private String mErrorCode = "";

    public RequestException(String msg) {
        this.mErrorMessage = msg;
    }

    public RequestException(String errorCode, String errorMsg) {
        this.mErrorCode = errorCode;
        this.mErrorMessage = errorMsg;

        if(errorCode == null) {
            this.mErrorCode = Constants.NET_CODE_RESPONSE_PRAM_ERROR;
            this.mErrorMessage  = Constants.NET_CODE_RESPONSE_PRAM_ERROR_MSG;
        }
    }

    public String getErrorMsg() {
        return mErrorMessage;
    }

    public String getErrorCode() {
        return mErrorCode;
    }
}
