package com.bwie.aizhonghui.smalltwo_yellow.presenter;

import android.app.Application;
import android.content.Context;

import com.bwie.aizhonghui.mvpfmodule.base.BasePresenter;
import com.bwie.aizhonghui.smalltwo_yellow.MyApp;
import com.bwie.aizhonghui.smalltwo_yellow.module.HomeCallback;
import com.bwie.aizhonghui.smalltwo_yellow.module.HomeModule;
import com.bwie.aizhonghui.smalltwo_yellow.view.HomeView;

/**
 * Created by DANGEROUS_HUI on 2017/11/15.
 */

public class HomePresenter extends BasePresenter<HomeView> {
   private HomeModule homeModule;
   private HomeView homeView;
    public HomePresenter(HomeView mView) {
        super(mView);
        this.homeView=mView;
        homeModule=new HomeModule();
    }

    public void login(String mobile,String pwd){
        homeModule.reg(mobile,pwd, new HomeCallback() {
            @Override
            public void success(String msg) {
                System.out.println("===p="+msg);
             homeView.success(msg);
            }

            @Override
            public void fialure(String msg) {
             homeView.failure(msg);
            }
        });
    }
}
