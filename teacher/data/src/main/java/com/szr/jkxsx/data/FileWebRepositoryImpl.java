package com.szr.jkxsx.data;


import com.szr.jkxsx.data.common.Constants;
import com.szr.jkxsx.data.exception.RequestException;
import com.szr.jkxsx.data.service.FileWebService;
import com.szr.jkxsx.domain.entities.ResponseDataWrapper;

import java.io.File;

import retrofit.mime.TypedFile;

/**
 * 文件相关的仓库
 * <p/>
 * Created by zczhang on 15/11/20.
 */
public class FileWebRepositoryImpl {
    private FileWebService mFileWebService;

    public FileWebRepositoryImpl(FileWebService fileWebService) throws RequestException {
        this.mFileWebService = fileWebService;
    }

    /**
     * 上传文件到服务器
     * @param filePath
     * @return
     */
    public String uploadFile2Server(String filePath) {
        File uploadFile = new File(filePath);
        if (!uploadFile.exists()) throw new RequestException("文件不存在,请重新选择!");
        ResponseDataWrapper<String> data = mFileWebService.uploadFile(new TypedFile("multipart/form-data", new File(filePath)));
        if (!data.status.equals(Constants.NET_CODE_SUCCESS)) {
            throw new RequestException(data.msg);
        }
        return data.results;
    }
}
