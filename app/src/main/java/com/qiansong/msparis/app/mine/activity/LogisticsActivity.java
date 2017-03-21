package com.qiansong.msparis.app.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.mine.adapter.TimelineAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/23.
 * <p>
 * 物流详情
 */

public class LogisticsActivity extends BaseActivity {


    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.logistics_lv)
    XRecyclerView logisticsLv;

    private LogisticsActivity INSTANCE;
    private TimelineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logistics);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE=this;

        setViews();
        setListeners();
    }

    private void setViews(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(INSTANCE);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        logisticsLv.setLayoutManager(linearLayoutManager);
        logisticsLv.setPullRefreshEnabled(false);
        View header= LayoutInflater.from(INSTANCE).inflate(R.layout.header_logistics,null);
        logisticsLv.addHeaderView(header);

        adapter=new TimelineAdapter(INSTANCE);
        logisticsLv.setAdapter(adapter);
    }

    private void setListeners(){

        //返回
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
