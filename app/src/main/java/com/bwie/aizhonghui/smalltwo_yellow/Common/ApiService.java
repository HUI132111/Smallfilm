package com.bwie.aizhonghui.smalltwo_yellow.Common;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by DANGEROUS_HUI on 2017/11/15.
 */
//@Headers({"Content-Type: application/json","Accept: */*"})
public interface ApiService {
    @Headers({"Content-Type: application/json","Accept: */*"})
    @POST("member/register")
    Observable<ResponseBody> getData(@Body RequestBody body);

//    @POST("member/register")
//    Call<ResponseBody> getData(@Body RequestBody body);

}
