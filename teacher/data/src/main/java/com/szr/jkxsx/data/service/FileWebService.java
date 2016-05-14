package com.szr.jkxsx.data.service;

import com.szr.jkxsx.domain.entities.ResponseDataWrapper;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * 文件服务
 * <p/>
 * Created by zczhang on 15/11/16.
 */
public interface FileWebService {
    @Multipart
    @POST("/SizeruiManager/common/upload")
    ResponseDataWrapper<String> uploadFile(@Part("file") TypedFile file);

    @Multipart
    @POST("/SizeruiManager/common/upload")
    void uploadFileHaveCallback(@Part("file") TypedFile file, Callback<String> cb);
}
