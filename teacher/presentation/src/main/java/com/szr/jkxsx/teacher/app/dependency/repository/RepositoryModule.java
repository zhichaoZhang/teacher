package com.szr.jkxsx.teacher.app.dependency.repository;

import com.szr.jkxsx.data.UserRepositoryImpl;
import com.szr.jkxsx.data.service.UserService;
import com.szr.jkxsx.domain.UserRepository;

import dagger.Module;
import dagger.Provides;

/**
 * 数据仓库依赖
 * <p/>
 * Created by zcZhang on 15/6/12.
 */
@Module(includes = ApiServiceModule.class)
public class RepositoryModule {
    @Provides
    UserRepository provideUserRepository(UserService userService) {
        return new UserRepositoryImpl(userService);
    }
}
