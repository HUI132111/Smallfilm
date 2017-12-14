package com.bwie.aizhonghui.mvpfmodule.base;

/**
 * Created by DANGEROUS_HUI on 2017/11/13.
 */

public class BasePresenter<V> {
    private V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }

    public void deatach(){
        mView=null;
    }
}
