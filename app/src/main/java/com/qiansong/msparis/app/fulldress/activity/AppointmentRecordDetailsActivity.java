package com.qiansong.msparis.app.fulldress.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.fulldress.bean.AppointmentBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 预约详情
 */
public class AppointmentRecordDetailsActivity extends BaseActivity {

    @InjectView(R.id.details_imageView)
    SimpleDraweeView detailsImageView;
    @InjectView(R.id.details_title)
    TextView detailsTitle;
    @InjectView(R.id.details_text1)
    TextView detailsText1;
    @InjectView(R.id.details_text2)
    TextView detailsText2;
    @InjectView(R.id.details_text3)
    TextView detailsText3;
    @InjectView(R.id.details_text4)
    TextView detailsText4;
    @InjectView(R.id.details_text5)
    TextView detailsText5;
    @InjectView(R.id.details_text6)
    TextView detailsText6;
    @InjectView(R.id.details_text7)
    TextView detailsText7;
    private ETitleBar titleBar;
    //用户tonken
    private String token = "";
    private AppointmentBean baseBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_record_details);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {

    }

    /**
     * 网络请求
     */
    public void requestData() {
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().booking_info(token)
                .enqueue(new Callback<AppointmentBean>() {
                    @Override
                    public void onResponse(Call<AppointmentBean> call, Response<AppointmentBean> response) {
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showMessage(baseBean.getError().getMessage());
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<AppointmentBean> call, Throwable t) {

                    }
                });

    }

    /**
     *  取消预约
     */
    public void requestData_detale() {
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",token);
        map.put("id",baseBean.getData().getId()+"");
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().booking_delete(body)
                .enqueue(new Callback<AppointmentBean>() {
                    @Override
                    public void onResponse(Call<AppointmentBean> call, Response<AppointmentBean> response) {
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                ToastUtil.showMessage("取消成功");
                                AppointmentRecordDetailsActivity.this.finish();
                            } else {
                                ToastUtil.showMessage(baseBean.getError().getMessage());
                            }
                        } else {

                        }
                    }
                    @Override
                    public void onFailure(Call<AppointmentBean> call, Throwable t) {
                    }
                });
    }

    /**
     * 填充数据
     */
    public void initData() {

//        ExclusiveUtils.setImageUrl(detailsImageView, baseBean.getData().getHead_portrait(), -1);
        detailsText1.setText(baseBean.getData().getCity_name()+" | "+baseBean.getData().getStore_name());

        //1礼服 2婚纱 3 化妆
        if(Integer.parseInt(baseBean.getData().getType()) ==1){
            detailsText2.setText("礼服");
        }else if(Integer.parseInt(baseBean.getData().getType()) ==2){
            detailsText2.setText("婚纱");
        }else{
            detailsText2.setText("化妆");
        }
        detailsText3.setText(baseBean.getData().getNum()+"");
        detailsText4.setText(baseBean.getData().getDate()+"  "+baseBean.getData().getStart_time());
        detailsText5.setText(baseBean.getData().getName());
        detailsText6.setText(baseBean.getData().getMobile());
        detailsText7.setText(baseBean.getData().getRemark());


    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("我的预约");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setRightTitle("取消预约");
        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                AppointmentRecordDetailsActivity.super.onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
                requestData_detale();

            }
        });
    }

}
