package com.szr.jkxsx.domain;

import com.szr.jkxsx.domain.entities.LogonReturnInfo;

/**
 *
 * Created by zczhang on 16/2/3.
 */
public interface UserRepository {
    LogonReturnInfo login(String userName, String userPwd);

    boolean teacherRegister(String teacherName, String loginName,
                            String password, String mobile, String avatar, String identity, int sex);
}
