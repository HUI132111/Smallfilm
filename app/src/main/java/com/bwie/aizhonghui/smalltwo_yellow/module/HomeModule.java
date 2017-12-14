package com.bwie.aizhonghui.smalltwo_yellow.module;

import com.bwie.aizhonghui.mvpfmodule.utils.RetrofitUtils;
import com.bwie.aizhonghui.smalltwo_yellow.Bean.RegBean;
import com.bwie.aizhonghui.smalltwo_yellow.Common.ApiService;
import com.bwie.aizhonghui.smalltwo_yellow.Common.ApiSmall;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DANGEROUS_HUI on 2017/11/15.
 */

public class HomeModule implements IHomeModule{
    @Override
    public void reg(String mobile, String password, final HomeCallback homeCallback) {
        RegBean regBean=new RegBean();
        RegBean.PdBean pdBean=new RegBean.PdBean();
        pdBean.setAccount(mobile);
        pdBean.setPassword(password);
        regBean.setPd(pdBean);
        regBean.setH2y_app_id("208810000001");
        Gson gson=new Gson();
        String s = gson.toJson(regBean);
        RequestBody body=RequestBody.create(MediaType.parse("application/json;?charset=utf-8"),s);
        new RetrofitUtils.Builder().build(ApiSmall.API_REG)
                .getRetrofit().create(ApiService.class)
                .getData(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        System.out.println("===连接成功==");
                    }
                }).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    String msg = body.string();
                    System.out.println("==k=="+msg);
                    homeCallback.success(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
               homeCallback.fialure("请求失败，请重试");
            }

            @Override
            public void onComplete() {

            }
        });

//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        try {
//                            System.out.println(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
    }
}
