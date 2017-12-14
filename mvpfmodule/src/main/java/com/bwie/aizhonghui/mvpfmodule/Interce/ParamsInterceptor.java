package com.bwie.aizhonghui.mvpfmodule.Interce;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DANGEROUS_HUI on 2017/11/14.
 */

public class ParamsInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Response response=chain.proceed(request);
        return response;
    }
}
