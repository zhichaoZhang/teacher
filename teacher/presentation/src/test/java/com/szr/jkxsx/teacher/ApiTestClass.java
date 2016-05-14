package com.szr.jkxsx.teacher;


import com.squareup.okhttp.OkHttpClient;
import com.szr.jkxsx.domain.repository.service.FileWebService;
import com.szr.jkxsx.domain.repository.service.UserService;
import com.szr.jkxsx.domain.repository.service.json.ResponseDataWrapper;
import com.szr.jkxsx.domain.repository.utils.Constants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * api接口测试类
 * <p/>
 * 因接口请求不包含Android代码,junit测试框架可满足
 * <p/>
 * <p/>
 * Created by zczhang on 15/11/23.
 */

//@RunWith(MockitoJUnitRunner.class)
public class ApiTestClass {
    private RestAdapter mRestAdapter;

    @Before
    public void initRestAdapter() {
        RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.FULL;
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(20 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_DOMAIN_JKXSX)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(logLevel)
                .build();
    }

    /**
     * 单纯的测试java代码方法
     */
    @Test
    public void testUseAble() {
        int a = 1;
        int b = 1;
        Assert.assertEquals(a, b);
    }

    /**
     * 使用Mock测试Android相关类
     */
    private static final String FAKE_STRING = "健康学数学教师版";
//    @Mock
//    Context mMockContext;
//
//    @Test
//    public void readStringFromContext_LocalizedString() {
//        // Given a mocked Context injected into the object under test...
//        when(mMockContext.getString(R.string.app_name))
//                .thenReturn(FAKE_STRING);
////        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mMockContext);
////
////        // ...when the stri ng is returned from the object under test...
////        String result = myObjectUnderTest.getHelloWorldString();
//            String result = "健康学数学教师版";
//        // ...then the result should be the expected one.
//        assertThat(result, is(FAKE_STRING));
//    }

    @Test
    public void testAppConfigInterface() {
        FileWebService fileWebService = mRestAdapter.create(FileWebService.class);
        ResponseDataWrapper<String> result = fileWebService.uploadFile(new TypedFile("multipart/form-data", new File("/Users/joye/Downloads/ic_tip.png")));
        System.out.println("result----->" + result.results);
        assertThat(Constants.NET_CODE_SUCCESS, is(result.status));
    }

    @Test
    public void testUploadFileInterface() {
        FileWebService fileWebService = mRestAdapter.create(FileWebService.class);
        ResponseDataWrapper<String> result = fileWebService.uploadFile(new TypedFile("multipart/form-data", new File("/Users/joye/Downloads/ic_tip.png")));
        System.out.println("result----->" + result.results);
        assertThat(Constants.NET_CODE_SUCCESS, is(result.status));
    }

    @Test
    public void testLoginInterface() {
        UserService userService = mRestAdapter.create(UserService.class);
        Response response = userService.userLogin("1", "123456");
        System.out.println("response.getBody()----->" + response.getBody().toString());
        assertThat(null, is(nullValue()));
    }

    @Test
    public void testRegister() {
        UserService userService = mRestAdapter.create(UserService.class);
        ResponseDataWrapper data = userService.teacherRegister("1", "1", "1", "1", "1", "1", 0);
        System.out.println("rdata----->" + data.results.toString());
        assertThat(null, is(nullValue()));
    }
}
