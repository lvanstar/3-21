package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * coalei
 * <p>
 * 支付订单
 */
public class PayCardActivity extends BaseActivity {

    @InjectView(R.id.pay_card_price)
    TextView payCardPrice;
    @InjectView(R.id.pay_card_name)
    TextView payCardName;
    @InjectView(R.id.pay_card_zfb)
    RadioButton payCardZfb;
    @InjectView(R.id.pay_card_wx)
    RadioButton payCardWx;
    @InjectView(R.id.pay_card_button)
    TextView payCardButton;
    @InjectView(R.id.pay_card_layout1)
    LinearLayout payCardLayout1;
    @InjectView(R.id.pay_card_layout2)
    LinearLayout payCardLayout2;


    private ETitleBar titleBar;

    private String pay_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
        initData();
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

    }

    /**
     * 填充数据
     */
    public void initData() {
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.pay_card_zfb, R.id.pay_card_wx, R.id.pay_card_button,R.id.pay_card_layout1, R.id.pay_card_layout2})
    public void onClick(View view) {
        switch (view.getId()) {
            //支付宝
            case R.id.pay_card_layout1:
            case R.id.pay_card_zfb:
                payCardZfb.setChecked(true);
                payCardWx.setChecked(false);
                pay_type = "zfb";
                break;
            //微信
            case R.id.pay_card_layout2:
            case R.id.pay_card_wx:
                payCardZfb.setChecked(false);
                payCardWx.setChecked(true);
                pay_type = "wx";
                break;
            //提交
            case R.id.pay_card_button:
                ToastUtil.showMessage(pay_type);
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("支付订单");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("积分明细");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                PayCardActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent();
////                intent.setClass(AddressActivity.this, UpdateAddressActivity.class);
////                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_ADD);
////                startActivity(intent);
//            }
//        });
    }

}
