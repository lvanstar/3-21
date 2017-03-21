package com.qiansong.msparis.app.commom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.BitmapImageUtil;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.find.FindFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.util.ActivityUtils;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.fulldress.FullDressFragment;
import com.qiansong.msparis.app.homepage.HomePageFragment;
import com.qiansong.msparis.app.homepage.activity.MessageCenterActivity;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.mine.MineFragment;
import com.qiansong.msparis.app.wardrobe.fragment.WardRobeFragment;

import java.util.ArrayList;

/**
 * 程序入口activity
 */

public class MainActivity extends BaseActivity {


    public static MainActivity INSTANCE;
    public static ViewPager vp;
    private ArrayList<Fragment> list = new ArrayList<>();
    private MyMainAdapter adapter;
    public static View line;
    private ImageView[] ivs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INSTANCE=this;
        //侵入式模块
        BarTintManger.getBar(INSTANCE);
        setViews();
//        setListeners();
        initFragmentViewPager();

    }


    private void setViews(){
        vp = (ViewPager) findViewById(R.id.main_viewpager);
        line=findViewById(R.id.activity_main);
        ivs = new ImageView[]{(ImageView) findViewById(R.id.bottom_iv0), (ImageView) findViewById(R.id.bottom_iv1), (ImageView) findViewById(R.id.bottom_iv2), (ImageView) findViewById(R.id.bottom_iv3), (ImageView) findViewById(R.id.bottom_iv4)};

        setSelectedItem(0);
    }


    private void setListeners(){


    }


    /**
     * 初始化fragment
     */
    private void initFragmentViewPager() {
        list.add(new HomePageFragment());
        list.add(new WardRobeFragment());
        list.add(new FullDressFragment());
        list.add(new FindFragment());
        list.add(new MineFragment());
        adapter = new MyMainAdapter(getSupportFragmentManager(), list);
        vp.setOffscreenPageLimit(list.size());                        //设置幕后item的缓存数目
        vp.setAdapter(adapter);                             //给ViewPage设置适配器
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int count = ivs.length;
                for (int i = 0; i < count; i++) {
                    if (position != 5 || i != 0) {
                        ivs[(position + i) % count].setBackgroundResource(getResources().getIdentifier("t" + (position + i) % count + (i == 0 ? "r" : ""), "mipmap", MainActivity.this.getPackageName()));
                    }else {
                        count += 1;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * 页面选择
     * @param pos
     */
    public void setSelectedItem(int pos) {
        vp.setCurrentItem(pos, false);
    }


    /**
     * 点击事件
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.rl0:
                setSelectedItem(0);
                break;
            case R.id.rl1:
                setSelectedItem(1);
                break;
            case R.id.rl2:
                setSelectedItem(2);
                break;
            case R.id.rl3:
                setSelectedItem(3);
                break;
            case R.id.rl4:
                setSelectedItem(4);
                break;


        }
    }

    /**
     * 推出APP
     */
    private long exitTimeMillis = System.currentTimeMillis();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            long currentTime = System.currentTimeMillis();
            if(currentTime-exitTimeMillis==0||currentTime-exitTimeMillis>1500){
                exitTimeMillis = System.currentTimeMillis();
                ContentUtil.makeToast(MainActivity.this, "再按一次退出");
                return false;
            }else{
                //finish();
                ActivityUtils.getInstance().popAllActivities();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.token= TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN,"");
        MyApplication.isFirst=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.IS_FIRST,false);
        MyApplication.isLogin=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.IS_LOGIN,false);
        Eutil.makeLog("token="+MyApplication.token);
//        Eutil.pay(INSTANCE);
    }
}
