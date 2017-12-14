package com.bwie.aizhonghui.smalltwo_yellow;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bwie.aizhonghui.mvpfmodule.base.BaseActivity;
import com.bwie.aizhonghui.mvpfmodule.base.BasePresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.igexin.sdk.PushManager;
import com.squareup.haha.perflib.Main;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity{


    private ViewPager vp;
    private List<String> dhlist;
    private Button btnd;
    private SimpleDraweeView siv;
    private MyPagerAdapter myPagerAdapter;

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
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initCj(true);
        initCj();
        initPush();
        vp = findViewById(R.id.vp_dh);
        btnd = findViewById(R.id.btn_dhj);
        dhlist=new ArrayList<>();
        initData();
        myPagerAdapter = new MyPagerAdapter(dhlist);
        vp.setAdapter(myPagerAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if(position==3){
                    System.out.println("sdfdsfdfsfsd");
                    Uri imgurl=Uri.parse(dhlist.get(position));
//                    ImagePipeline imagePipeline=Fresco.getImagePipeline();
//                    imagePipeline.evictFromMemoryCache(imgurl);
//                    imagePipeline.evictFromDiskCache(imgurl);
//                    imagePipeline.evictFromCache(imgurl);
                DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                         .setUri(imgurl)
                         .setAutoPlayAnimations(true)
                         .build();
                 siv.setController(mDraweeController);
                 btnd.setVisibility(View.VISIBLE);
                 btnd.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         startActivity(new Intent(MainActivity.this,LoginActivity.class));
                          finish();
                     }
                 });
             }else {
                 btnd.setVisibility(View.GONE);
             }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPush() {
        PushManager.getInstance().initialize(this.getApplicationContext(), com.bwie.aizhonghui.smalltwo_yellow.PersonPush.PersonService.class);

        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.bwie.aizhonghui.smalltwo_yellow.PersonPush.PersonIntentService.class);
    }

    private void initCj() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void initData() {
        dhlist.add("res://"+getPackageName()+"/"+R.drawable.dhone);
        dhlist.add("res://"+getPackageName()+"/"+R.drawable.dhtwo);
        dhlist.add("res://"+getPackageName()+"/"+R.drawable.dhthree);
        dhlist.add("res://"+getPackageName()+"/"+R.drawable.dhlast);
    }

    class MyPagerAdapter extends PagerAdapter{
        private List<String> list;

        public MyPagerAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return dhlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=null;
            if(view==null){
                view=View.inflate(MainActivity.this,R.layout.main_dh,null);
            }
            siv = view.findViewById(R.id.siv_dh);
            SimpleDraweeView sivd=view.findViewById(R.id.iv_dhd);
            if(position==3){

            }else {
                sivd.setImageURI(Uri.parse(list.get(position)));
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
