package com.szr.jkxsx.teacher.viewmodelmapper;

/**
 * 逻辑实体转换成页面实体
 *
 * Created by zczhang on 16/2/3.
 */
public interface BaseViewModelMapper<I, O> {

    O transform(I domainModel);

}
