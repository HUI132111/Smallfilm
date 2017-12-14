package com.bwie.aizhonghui.mvpfmodule.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by DANGEROUS_HUI on 2017/11/14.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "18b6fc772f", false);

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//                          return;
//                     }
//                 LeakCanary.install(this);

        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(this,"5a0adff5f29d984c6d000200","wandoujia", MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent. startWithConfigure(config);
    }
}
