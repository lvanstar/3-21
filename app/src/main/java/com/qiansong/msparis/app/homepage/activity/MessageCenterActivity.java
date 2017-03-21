package com.qiansong.msparis.app.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.MessageBean;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/3/15.
 * <p>
 * 消息中心
 */

public class MessageCenterActivity extends BaseActivity {


    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.messageCenter_image01)
    ImageView messageCenterImage01;
    @InjectView(R.id.messageCenter_timeTv01)
    TextView messageCenterTimeTv01;
    @InjectView(R.id.messageCenter_valueTv01)
    TextView messageCenterValueTv01;
    @InjectView(R.id.messageCenterRl01)
    RelativeLayout messageCenterRl01;
    @InjectView(R.id.messageCenter_image02)
    ImageView messageCenterImage02;
    @InjectView(R.id.messageCenter_timeTv02)
    TextView messageCenterTimeTv02;
    @InjectView(R.id.messageCenter_valueTv02)
    TextView messageCenterValueTv02;
    @InjectView(R.id.messageCenterRl02)
    RelativeLayout messageCenterRl02;
    @InjectView(R.id.messageCenter_image03)
    ImageView messageCenterImage03;
    @InjectView(R.id.messageCenter_timeTv03)
    TextView messageCenterTimeTv03;
    @InjectView(R.id.messageCenter_valueTv03)
    TextView messageCenterValueTv03;
    @InjectView(R.id.messageCenterRl03)
    RelativeLayout messageCenterRl03;
    private MessageCenterActivity INSTANCE;
    private MessageBean.DataEntity dataEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE = this;



    }


    private void setViews() {

        if(dataEntity.getTotal()==0)return;
        for (int i=0;i<dataEntity.getRows().size();i++){
            if("1".equals(dataEntity.getRows().get(i).getType())){
                messageCenterTimeTv01.setText(dataEntity.getRows().get(i).getCreated_at());
                messageCenterValueTv01.setText(dataEntity.getRows().get(i).getContent());
            }else if("2".equals(dataEntity.getRows().get(i).getType())){
                messageCenterTimeTv02.setText(dataEntity.getRows().get(i).getCreated_at());
                messageCenterValueTv02.setText(dataEntity.getRows().get(i).getContent());
            }else if("3".equals(dataEntity.getRows().get(i).getType())){
                messageCenterTimeTv03.setText(dataEntity.getRows().get(i).getCreated_at());
                messageCenterValueTv03.setText(dataEntity.getRows().get(i).getContent());
            }
        }

            messageCenterImage01.setImageResource(MyApplication.isMessAGEONE?R.mipmap.msg1:R.mipmap.msg1_red);
            messageCenterImage02.setImageResource(MyApplication.isMessAGETWO?R.mipmap.msg2:R.mipmap.msg2_red);
            messageCenterImage03.setImageResource(MyApplication.isMessAGETHREE?R.mipmap.msg3:R.mipmap.msg3_red);
    }

    private void setListeners() {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messageCenterRl01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TXShareFileUtil.getInstance().putBoolean(GlobalConsts.MESSAGE_ONE,false);
                MyApplication.isMessAGEONE=false;
                Intent intent=new Intent(INSTANCE,MessageListActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,1);
                startActivity(intent);
            }
        });

        messageCenterRl02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TXShareFileUtil.getInstance().putBoolean(GlobalConsts.MESSAGE_TWO,false);
                MyApplication.isMessAGETWO=false;
                Intent intent=new Intent(INSTANCE,MessageListActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,2);
                startActivity(intent);
            }
        });

        messageCenterRl03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TXShareFileUtil.getInstance().putBoolean(GlobalConsts.MESSAGE_THREE,false);
                MyApplication.isMessAGETHREE=false;
                Intent intent=new Intent(INSTANCE,MessageListActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,3);
                startActivity(intent);
            }
        });
    }


    private void initData() {

        dialog.show();
        HttpServiceClient.getInstance().messageCenter(MyApplication.token).enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                dialog.cancel();
                if (response.isSuccessful()) {
                    if ("ok".equals(response.body().getStatus())) {
                        dataEntity = response.body().getData();
                        setViews();
                        setListeners();
                    } else {
                        ContentUtil.makeToast(INSTANCE, response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

                dialog.cancel();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
