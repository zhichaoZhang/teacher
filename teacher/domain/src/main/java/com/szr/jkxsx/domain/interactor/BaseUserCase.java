package com.szr.jkxsx.domain.interactor;

import rx.Observable;

/**
 * 根据RxJava的特性进行修改的获取层
 * 推荐这种方式进行Domain的逻辑隔离, 可以对Observable,
 * 但是从某种方面来看, 过于依赖某种框架对整个项目结构是有伤害的
 *
 * Created by zcZhang on 3/23/15.
 */
public interface BaseUserCase<T> {

    public Observable<T> request();
}
