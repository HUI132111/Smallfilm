package com.bwie.aizhonghui.smalltwo_yellow;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bwie.aizhonghui.mvpfmodule.base.BaseActivity;
import com.bwie.aizhonghui.mvpfmodule.base.BasePresenter;
import com.bwie.aizhonghui.smalltwo_yellow.S_interface.FSinterface;
import com.bwie.aizhonghui.smalltwo_yellow.fragments.fragmentsm;
import com.bwie.aizhonghui.smalltwo_yellow.fragments.fragmentwd;
import com.bwie.aizhonghui.smalltwo_yellow.fragments.fragmentyy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton rayy;
    private RadioButton rawd;
    private ViewPager vp;
    private List<Fragment> fragmentList;
    private MyPagerAdapter myPagerAdapter;
    private ImageView ivsm;
    private FSinterface fSinterface;
    private int mk=0;


    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void init() {
        Fresco.initialize(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        showActionBar(false);
        initCj(true);
        rayy = findViewById(R.id.rabtn_yy);
        rawd = findViewById(R.id.rabtn_wd);
        ivsm = findViewById(R.id.iv_smdc);
        vp = findViewById(R.id.vp_sy);
        rayy.setOnClickListener(this);
        rawd.setOnClickListener(this);
        ivsm.setOnClickListener(this);
        rayy.setChecked(true);
        initData();
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(myPagerAdapter);
        vp.setCurrentItem(1);

    }

    private void initData() {
        fragmentList=new ArrayList<>();
        fragmentyy fy=new fragmentyy();
        fragmentwd fw=new fragmentwd();
        fragmentsm fm=new fragmentsm();
        fragmentList.add(fy);
        fragmentList.add(fm);
        fragmentList.add(fw);
    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.rabtn_wd:
             mk=0;
             vp.setCurrentItem(2);
             ivsm.setEnabled(true);
             break;
         case R.id.rabtn_yy:
             mk=0;
             vp.setCurrentItem(0);
             ivsm.setEnabled(true);
             break;
         case R.id.iv_smdc:
             mk++;
             vp.setCurrentItem(1);
             if(mk==2){
                 ivsm.setEnabled(false);
             }
             break;
     }
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
