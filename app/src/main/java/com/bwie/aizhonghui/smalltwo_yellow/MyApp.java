package com.bwie.aizhonghui.smalltwo_yellow;

import com.bwie.aizhonghui.mvpfmodule.base.BaseApplication;
import com.mob.MobSDK;

/**
 * Created by DANGEROUS_HUI on 2017/11/15.
 */

public class MyApp extends BaseApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this,"227d6e4659aa0","8333e4bbd73a9119efcd662adb401516");
    }
}
