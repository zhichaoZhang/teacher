package com.szr.jkxsx.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szr.jkxsx.data.common.Constants;
import com.szr.jkxsx.data.exception.RequestException;
import com.szr.jkxsx.data.service.UserService;
import com.szr.jkxsx.domain.UserRepository;
import com.szr.jkxsx.domain.entities.LogonReturnInfo;
import com.szr.jkxsx.domain.entities.ResponseDataWrapper;

import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * 用户相关数据仓库
 *
 * Created by zczhang on 15/10/11.
 */
public class UserRepositoryImpl implements UserRepository{
    private UserService userService;

    public UserRepositoryImpl(UserService userService) {
        this.userService = userService;
    }

    public LogonReturnInfo login(String userName, String userPwd) throws RequestException {
        Response response = userService.userLogin(userName, userPwd);

        String cookie = "";
        for (Header header : response.getHeaders()) {
            if (header.getName().equals("Set-Cookie")) {
                cookie = header.getValue();
                break;
            }
        }
        Gson gson = new Gson();
        ResponseDataWrapper<LogonReturnInfo> data = gson.fromJson(new String(((TypedByteArray) response.getBody()).getBytes()), new TypeToken<ResponseDataWrapper<LogonReturnInfo>>() {
        }.getType());

        if (data.status == null || !data.status.equals(Constants.NET_CODE_SUCCESS)) {
            throw new RequestException(data.status, data.msg);
        }
        LogonReturnInfo logonReturnInfo = new LogonReturnInfo();
        logonReturnInfo.jsessionid = cookie;
        return logonReturnInfo;
    }

    public boolean teacherRegister(String teacherName, String loginName,
                                               String password, String mobile, String avatar, String identity, int sex) throws RequestException {
        ResponseDataWrapper data = userService.teacherRegister(teacherName, loginName, password, mobile, avatar, identity, sex);
        if(!data.status.equals(Constants.NET_CODE_SUCCESS)) {
            throw new RequestException(data.status, data.msg);
        }

        return true;
    }

}
