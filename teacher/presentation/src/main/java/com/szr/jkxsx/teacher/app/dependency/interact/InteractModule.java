package com.szr.jkxsx.teacher.app.dependency.interact;


import com.szr.jkxsx.domain.UserRepository;
import com.szr.jkxsx.domain.interactor.LoginUserCase;
import com.szr.jkxsx.domain.interactor.TeacherRegisterUserCase;
import com.szr.jkxsx.teacher.app.dependency.repository.RepositoryModule;

import dagger.Module;
import dagger.Provides;

/**
 * 交互器依赖
 * <p/>
 * Created by zcZhang on 15/6/12.
 */
@Module(includes = RepositoryModule.class)
public class InteractModule {

    @Provides
    LoginUserCase provideLoginInteractor(UserRepository userRepository) {
        return new LoginUserCase(userRepository);
    }

    @Provides
    TeacherRegisterUserCase provideTeacherRegisterInteractor(UserRepository userRepository) {
        return new TeacherRegisterUserCase(userRepository);
    }
}
