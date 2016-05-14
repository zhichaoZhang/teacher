package com.szr.jkxsx.domain.executor;

/**
 * 线程池, 主要用于子线程
 *
 * Created by zcZhang on 3/12/15.
 */
public interface ThreadExecutor {
    public void execute(Runnable runnable);
}
