package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.mine.adapter.MemberCardDetailssAdapter;
import com.qiansong.msparis.app.mine.bean.BuyCarDetailsBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * coalei
 * <p>
 * 女神卡详情
 */
public class MemberCardDetailsActivity extends BaseActivity {

    @InjectView(R.id.member_details_image)
    SimpleDraweeView memberDetailsImage;
    @InjectView(R.id.member_details_title)
    TextView memberDetailsTitle;
    @InjectView(R.id.member_details_checkbox)
    CheckBox memberDetailsCheckbox;
    @InjectView(R.id.member_details_xieyi)
    TextView memberDetailsXieyi;
    @InjectView(R.id.member_details_price)
    TextView memberDetailsPrice;
    @InjectView(R.id.member_details_button)
    TextView memberDetailsButton;
    @InjectView(R.id.member_details_notice)
    TextView memberDetailsNotice;
    @InjectView(R.id.member_details_list)
    GridView memberDetailsList;


    private ETitleBar titleBar;
    private MemberCardDetailssAdapter adapter;
    //用户tonken
    private String token="";
    private BuyCarDetailsBean bean= null;
    //女神卡的id
    private String  cardId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_card);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        //获取女神卡id
        cardId=getIntent().getStringExtra(GlobalConsts.MEMBERCARD_ID);
        memberDetailsButton.setClickable(false);
        //只有同意协议才能购卡
        memberDetailsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    memberDetailsButton.setTextColor(getResources().getColor(R.color.white));
                    memberDetailsButton.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    memberDetailsButton.setClickable(true);
                }else{
                    memberDetailsButton.setTextColor(getResources().getColor(R.color.gray));
                    memberDetailsButton.setBackgroundResource(R.drawable.tv_textview_white_bg);
                    memberDetailsButton.setClickable(false);
                }
            }
        });

        //设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(memberDetailsList,4);

        adapter = new MemberCardDetailssAdapter(this);
        memberDetailsList.setAdapter(adapter);

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取女神卡详情
        HttpServiceClient.getInstance().card_detail(token ,cardId)
                .enqueue(new Callback<BuyCarDetailsBean>() {
                    @Override
                    public void onResponse(Call<BuyCarDetailsBean> call, Response<BuyCarDetailsBean> response) {
                        dialog.cancel();
                        if(response.isSuccessful()){
                        bean = response.body();
                        if ("ok".equals(bean.getStatus())) {
                            if(bean != null ){
                                initData();
                            }
                        } else {

                        }
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<BuyCarDetailsBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 填充数据
     */
    public void initData() {
        ExclusiveUtils.setImageUrl(memberDetailsImage, bean.getData().getCover_img(), -1);
        memberDetailsTitle.setText(bean.getData().getName());
        memberDetailsNotice.setText(bean.getData().getNotice());
        memberDetailsPrice.setText("合计：￥"+bean.getData().getPrice());
        adapter.setData(bean.getData().getCard_role());
    }

    /**
     *  点击事件
     * @param view
     */
    @OnClick({R.id.member_details_xieyi, R.id.member_details_button})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //女神协议
            case R.id.member_details_xieyi:
                break;
            //立即购卡
            case R.id.member_details_button:
                intent.setClass(MemberCardDetailsActivity.this, ConfirmCardOrderActivity.class);
                intent.putExtra("type","card");//购买订单
                intent.putExtra("card_id",cardId+"");//女神卡主键
                startActivity(intent);
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("女神卡详情");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("积分明细");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                MemberCardDetailsActivity.super.onBackPressed();
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
