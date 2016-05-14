package com.szr.jkxsx.domain.entities;

/**
 * 服务器响应的数据包装
 *
 * Created by zczhang on 3/11/15.
 */
public class ResponseDataWrapper<T> extends ResponseContainer {
    public T results;
}
