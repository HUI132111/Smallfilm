package com.bwie.aizhonghui.smalltwo_yellow;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mvpfmodule.base.BaseActivity;
import com.bwie.aizhonghui.mvpfmodule.base.BasePresenter;
import com.bwie.aizhonghui.smalltwo_yellow.presenter.HomePresenter;
import com.bwie.aizhonghui.smalltwo_yellow.view.HomeView;
import com.squareup.haha.trove.TObjectFunction;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity<HomeView,HomePresenter> implements HomeView, View.OnClickListener {


    private EditText etuser;
    private EditText etpwd;
    private ImageView iveye;
    private Button btnlogin;
    private int kk=1;
    private TextView qh;
    private boolean qhresult=false;
    private boolean ycpwd=false;
    private RelativeLayout redjs;
    private TextView tvdjs;
    private Timer timer;
    private int miniter=0;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int k=msg.what;
            miniter=k;
            if(k>0){
                tvdjs.setText(k+"s后");
            }else {
                redjs.setVisibility(View.GONE);
                iveye.setVisibility(View.VISIBLE);
                iveye.setImageResource(R.drawable.reyzm);
                tvdjs.setText("30s后");
                ycpwd=false;
                timer.cancel();
            }

        }
    };
    private EventHandler eventHandler;

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void init() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        showActionBar(false);
        initCj(true);
        initSMS();
        etuser = findViewById(R.id.et_user);
        etpwd = findViewById(R.id.et_pwd);
        iveye = findViewById(R.id.iv_eye);
        btnlogin = findViewById(R.id.btn_login);
        qh = findViewById(R.id.tv_log_qh);
        tvdjs = findViewById(R.id.tv_djs);
        redjs = findViewById(R.id.re_djs);
        redjs.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        iveye.setOnClickListener(this);
        qh.setOnClickListener(this);

    }

    private void initSMS() {
        // 处理你自己的逻辑
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "kaikaikai", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        System.out.println("==成功==");
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(LoginActivity.this,"收到验证码",Toast.LENGTH_SHORT).show();
                           }
                       });
                    }else if(event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        System.out.println("==登录成功==");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        };

            // 注册监听器
   SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailure(String msg) {

    }

    @Override
    public void success(String msg) {
        System.out.println("==成功=="+msg);

    }

    @Override
    public void failure(String msg) {
        System.out.println("==失败=="+msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
//                String user = etuser.getText().toString();
//                String pwd = etpwd.getText().toString();
//                presenter.login(user,pwd);
               // SMSSDK.submitVerificationCode("86",etuser.getText().toString(),etpwd.getText().toString());
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                break;
            case R.id.iv_eye:
                if(ycpwd){
                    if(kk%2==0){
                        iveye.setImageResource(R.drawable.showpwd);
                        etpwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }else {
                        iveye.setImageResource(R.drawable.hidepwd);
                        etpwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    kk++;
                }else {
                    String s = etuser.getText().toString();
                    SMSSDK.getVerificationCode("86",s);
                    iveye.setVisibility(View.GONE);
                    redjs.setVisibility(View.VISIBLE);
                    timer = new Timer();
                    TimerTask task=new TimerTask() {
                        int kk=30;
                        @Override
                        public void run() {
                            kk--;
                            Message msg=Message.obtain();
                            msg.what=kk;
                            handler.sendMessage(msg);
                        }
                    };
                    timer.schedule(task,1000,1000);
                }

                break;
            case R.id.tv_log_qh:
                Drawable drawable;
                SpannableString s;
                if(qhresult){
                    drawable = getResources().getDrawable(R.drawable.yzm);
                    s = new SpannableString("输入短信验证码");
                    if(miniter>0){
                        redjs.setVisibility(View.VISIBLE);
                        iveye.setVisibility(View.GONE);
                    }else {
                        iveye.setImageResource(R.drawable.hqyzm);
                    }
                    etpwd.setText("");
                    qhresult=false;
                    ycpwd=false;
                }else {
                     drawable = getResources().getDrawable(R.drawable.pwdlogin);
                     iveye.setImageResource(R.drawable.showpwd);
                     etpwd.setText("");
                     s = new SpannableString("输入登录密码");
                     redjs.setVisibility(View.GONE);
                     iveye.setVisibility(View.VISIBLE);
                     qhresult=true;
                     ycpwd=true;
                }
                 drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                 etpwd.setCompoundDrawables(drawable,null,null,null);
                 etpwd.setHint(s);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
