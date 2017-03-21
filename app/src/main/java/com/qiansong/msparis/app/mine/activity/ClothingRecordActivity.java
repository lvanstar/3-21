package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.ClothingRecordBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.mine.adapter.ClothingRecordAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 还衣记录
 */
public class ClothingRecordActivity extends BaseActivity {
    ClothingRecordActivity context;
    ListView record;
    PullToRefreshView refresh;//下拉刷新
    ClothingRecordAdapter adapter;//adapter

    ClothingRecordBean bean;
    int page=1;
    int size=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_record);
        context=this;
        setTitleBar();
        init();
        setListener();
    }

    private void setListener() {
        refresh.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshView view) {

                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onHeaderRefreshComplete();
                    }
                }, 1000);
            }
        });
        refresh.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(PullToRefreshView view) {
                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onFooterRefreshComplete();
                    }
                }, 1000);
            }
        });
        record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(context,ClothingRecordDetailsActivity.class));
            }
        });
    }

    private void init() {
        refresh= (PullToRefreshView) findViewById(R.id.refresh);
        record= (ListView) findViewById(R.id.record);
        refresh.setFooter(true);
        data();
    }

    private void data(){
        HttpServiceClient.getInstance().order_return(MyApplication.token,null,null).enqueue(new Callback<ClothingRecordBean>() {
            @Override
            public void onResponse(Call<ClothingRecordBean> call, Response<ClothingRecordBean> response) {
                if (!response.isSuccessful()||!"ok".equals(response.body().getStatus()))return;
                bean=response.body();
                adapter = new ClothingRecordAdapter(context,bean.getData().getRows());
                record.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ClothingRecordBean> call, Throwable t) {

            }
        });
    }
    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("还衣记录");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
//        titleBar.setRightImageResource(R.mipmap.ic_launcher);//设置右边图标
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//设置背景
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
            }
        });
    }
}
