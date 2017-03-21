package com.qiansong.msparis.app.find.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.find.adapter.AddClothesAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/9.
 * <p>
 * 添加美衣
 */

public class AddClothesActivity extends BaseActivity {

    @InjectView(R.id.add_clothes_recyclerView)
    XRecyclerView addClothesRecyclerView;

    private AddClothesActivity INSTANCE;
    private AddClothesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE=this;
        setTitleBar();
        setViews();
        setListeners();
    }


    private void setViews(){
        GridLayoutManager linearLayoutManager = new GridLayoutManager(INSTANCE,2);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addClothesRecyclerView.setLayoutManager(linearLayoutManager);
        addClothesRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        addClothesRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        addClothesRecyclerView.setArrowImageView(R.mipmap.ic_launcher);

        adapter=new AddClothesAdapter(INSTANCE);
        addClothesRecyclerView.setAdapter(adapter);
    }

    private void setListeners(){}


    //设置标题栏
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.add_clothes_title);
        titleBar.setTitle("添加美衣");//设置标题
        titleBar.setTitleColor(getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
