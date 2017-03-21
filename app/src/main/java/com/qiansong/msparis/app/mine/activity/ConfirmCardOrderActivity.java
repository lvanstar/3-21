package com.qiansong.msparis.app.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.bean.LoginBean;
import com.qiansong.msparis.app.mine.bean.PriceCardBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * kevin.cao
 * 购买会员卡确认订单页
 */
public class ConfirmCardOrderActivity extends BaseActivity {

    /**
     * 会员卡图片
     */
    @InjectView(R.id.confirm_card_image)
    SimpleDraweeView confirmCardImage;
    /**
     * 会员卡名字
     */
    @InjectView(R.id.confirm_card_name)
    TextView confirmCardName;
    /**
     * 会员卡价格
     */
    @InjectView(R.id.confirm_card_price)
    TextView confirmCardPrice;
    /**
     * 体验卡押金
     */
    @InjectView(R.id.confirm_card_title1)
    TextView confirmCardTitle1;
    @InjectView(R.id.confirm_card_layout1)
    LinearLayout confirmCardLayout1;
    /**
     * 优惠券
     */
    @InjectView(R.id.confirm_card_title2)
    TextView confirmCardTitle2;
    @InjectView(R.id.confirm_card_layout2)
    LinearLayout confirmCardLayout2;
    /**
     * 总金额
     */
    @InjectView(R.id.confirm_card_title3)
    TextView confirmCardTitle3;
    /**
     * 优惠券
     */
    @InjectView(R.id.confirm_card_title4)
    TextView confirmCardTitle4;
    /**
     * 会员折扣
     */
    @InjectView(R.id.confirm_card_title5)
    TextView confirmCardTitle5;
    /**
     * 合计
     */
    @InjectView(R.id.confirm_card_count_price)
    TextView confirmCardCountPrice;
    /**
     * 提交订单
     */
    @InjectView(R.id.confirm_card_button)
    TextView confirmCardButton;
    private ETitleBar titleBar;

    private String cardId = "";
    private String type = "";

    private  PriceCardBean priceCardBean;
    private  String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_card_order);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        if(cardId.length()>0 && type.length()>0){
            requestData();
        }
    }

    /**
     * 初始化页面
     */
    public void initView() {
        //获取女神卡id  类型
        type = getIntent().getStringExtra("type");
        cardId = getIntent().getStringExtra("card_id");

    }


    /**
     *  获取基本数据
     */
    public  void requestData(){
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String ,String > map = new HashMap<>();
        map.put("access_token",token);
        map.put("type",type);
        map.put("card_id",cardId);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        HttpServiceClient.getInstance().price_card(body)
                .enqueue(new Callback<PriceCardBean>() {
                    @Override
                    public void onResponse(Call<PriceCardBean> call, Response<PriceCardBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            priceCardBean = response.body();
                            if ("ok".equals(priceCardBean.getStatus())) {
                                setData();
                            } else {
                                ToastUtil.showMessage(priceCardBean.getError().getMessage());
                            }
                        } else {
                            ToastUtil.showMessage("网络异常");
                        }
                    }
                    @Override
                    public void onFailure(Call<PriceCardBean> call, Throwable t) {
                    }
                });
    }

    /**
     *  设置数据
     *
     */
    public  void setData(){
        confirmCardPrice.setText("￥ "+priceCardBean.getData().getCard());
        confirmCardTitle1.setText("￥ "+priceCardBean.getData().getDeposit());
        confirmCardTitle2.setText("-￥ "+priceCardBean.getData().getCoupon());

        confirmCardTitle3.setText("￥ "+priceCardBean.getData().getOriginal_total());
        confirmCardTitle4.setText("-￥ "+priceCardBean.getData().getCoupon());
        confirmCardTitle5.setText("-￥ "+priceCardBean.getData().getMember());
        confirmCardCountPrice.setText("合计 ：￥ "+priceCardBean.getData().getTotal());
    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.confirm_card_layout1, R.id.confirm_card_layout2, R.id.confirm_card_button})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //押金
            case R.id.confirm_card_layout1:
                break;
            //优惠券
            case R.id.confirm_card_layout2:
                break;
            //提交订单
            case R.id.confirm_card_button:
                intent.setClass(ConfirmCardOrderActivity.this, PayCardActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("确认订单");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                ConfirmCardOrderActivity.super.onBackPressed();
            }
        });
    }
}
