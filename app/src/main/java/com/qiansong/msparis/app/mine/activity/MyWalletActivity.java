package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.bean.MyWallet;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin .cao
 * <p>
 * 我的钱包
 */
public class MyWalletActivity extends BaseActivity {

    @InjectView(R.id.wallet_text1)
    TextView walletText1;
    @InjectView(R.id.wallet_list1)
    LinearLayout walletList1;
    @InjectView(R.id.wallet_text2)
    TextView walletText2;
    @InjectView(R.id.wallet_list2)
    LinearLayout walletList2;

    //用户tonken
    private String token = "";
    private MyWallet bean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    @Override
    protected void onResume() {
        requestData();
        super.onResume();
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
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取收货地址
        HttpServiceClient.getInstance().myWallet(token)
                .enqueue(new Callback<MyWallet>() {
                    @Override
                    public void onResponse(Call<MyWallet> call, Response<MyWallet> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                if (bean != null) {
                                    initData();
                                }
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<MyWallet> call, Throwable t) {

                    }
                });
    }

    /**
     * 填充数据
     */
    public void initData() {
        walletText1.setText(bean.getData().getCoupon_number() + "张");
        walletText2.setText("￥" + bean.getData().getDeposit());
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.wallet_list1, R.id.wallet_list2})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //优惠券
            case R.id.wallet_list1:
                intent.setClass(MyWalletActivity.this, CouponActivity.class);
                startActivity(intent);
                break;
            //我的押金
            case R.id.wallet_list2:
                intent.setClass(MyWalletActivity.this, DepositActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("我的钱包");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("跳过");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                MyWalletActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent();
////                intent.setClass(UserMessageActivity.this, UpdateAddressActivity.class);
////                startActivity(intent);
//                UserMessageActivity.super.onBackPressed();
//            }
//        });
    }


}
