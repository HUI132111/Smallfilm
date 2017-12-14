package com.bwie.aizhonghui.mvpfmodule.base;

/**
 * Created by DANGEROUS_HUI on 2017/11/13.
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showFailure(String msg);
}
