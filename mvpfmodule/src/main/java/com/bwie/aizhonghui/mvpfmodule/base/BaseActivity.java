package com.bwie.aizhonghui.mvpfmodule.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by DANGEROUS_HUI on 2017/11/13.
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AutoLayoutActivity {
    public P presenter;
    public abstract P initPresenter();
    public abstract void init();
    public abstract int getLayoutId();
    private boolean isStatus=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(getLayoutId());
        presenter=initPresenter();
        initView();
    }

    /**
     * 设置透明状态 沉浸式
     */
    public void initCj(boolean status){
        isStatus =status;
        if(isStatus){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }
    /**
     * 设置ActionBai 显示隐藏
     */
    public void showActionBar(boolean sta){
        if(sta){
            getSupportActionBar().show();
        }else {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.deatach();
        }

    }
}
