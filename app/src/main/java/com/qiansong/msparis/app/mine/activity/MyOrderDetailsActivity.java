package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.OrderDeatilBean;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.CountdownTimer;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.adapter.OrderDetailInfoAdapter;
import com.qiansong.msparis.app.wardrobe.activity.PayActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/23.
 * <p>
 * 我的订单详情
 */

public class MyOrderDetailsActivity extends BaseActivity {


    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.order_details_title)
    TextView orderDetailsTitle;
    @InjectView(R.id.order_details_Timer_Tv)
    TextView timerTv;
    @InjectView(R.id.order_details_cancel_Tv)
    TextView cancel;
    @InjectView(R.id.order_details_code_Tv)
    TextView orderDetailsCodeTv;
    @InjectView(R.id.order_details_times_Tv)
    TextView orderDetailsTimesTv;
    @InjectView(R.id.order_details_status_Tv)
    TextView orderDetailsStatusTv;
    @InjectView(R.id.order_details_tv001)
    TextView orderDetailsTv001;
    @InjectView(R.id.order_details_cancelTv)
    TextView orderDetailsCancelTv;
    @InjectView(R.id.tv_dingwei222)
    ImageView tvDingwei222;
    @InjectView(R.id.order_details_addressTv)
    TextView orderDetailsAddressTv;
    @InjectView(R.id.order_details_nameTv)
    TextView orderDetailsNameTv;
    @InjectView(R.id.order_details_phoneTv)
    TextView orderDetailsPhoneTv;
    @InjectView(R.id.Rl01)
    RelativeLayout Rl01;
    @InjectView(R.id.make_order_address_Ll)
    LinearLayout makeOrderAddressLl;
    @InjectView(R.id.order_details_time_Tv)
    TextView orderDetailsTimeTv;
    @InjectView(R.id.order_details_lv)
    ListView orderDetailsLv;
    @InjectView(R.id.order_details_mumber_Tv)
    TextView orderDetailsMumberTv;
    @InjectView(R.id.order_details_mumberPrice_Tv)
    TextView orderDetailsMumberPriceTv;
    @InjectView(R.id.order_details_price_Tv)
    TextView orderDetailsPriceTv;
    @InjectView(R.id.order_details_pay_Tv)
    TextView orderDetailsPayTv;
    @InjectView(R.id.order_details_out_chooseTv)
    TextView orderDetailsOutChooseTv;
    @InjectView(R.id.order_detail_out_tv)
    TextView orderDetailOutTv;
    @InjectView(R.id.order_detail_out_Rl)
    RelativeLayout orderDetailOutRl;
    @InjectView(R.id.order_detail_001Tv)
    TextView orderDetail001Tv;
    @InjectView(R.id.order_detail_remarkTv)
    TextView orderDetailRemarkTv;
    @InjectView(R.id.order_detail_totalPriceTv)
    TextView orderDetailTotalPriceTv;
    @InjectView(R.id.order_detail_longPriceTv)
    TextView orderDetailLongPriceTv;
    @InjectView(R.id.order_detail_longPriceRl)
    RelativeLayout orderDetailLongPriceRl;
    @InjectView(R.id.order_detail_depositTv)
    TextView orderDetailDepositTv;
    @InjectView(R.id.order_detail_depositRl)
    RelativeLayout orderDetailDepositRl;
    @InjectView(R.id.order_detail_discountTv)
    TextView orderDetailDiscountTv;
    @InjectView(R.id.order_detail_discountRl)
    RelativeLayout orderDetailDiscountRl;
    @InjectView(R.id.order_detail_vipTv)
    TextView orderDetailVipTv;
    @InjectView(R.id.order_detail_vipRl)
    RelativeLayout orderDetailVipRl;
    @InjectView(R.id.order_detail_shipTv)
    TextView orderDetailShipTv;
    @InjectView(R.id.order_detail_StatusRl)
    RelativeLayout statusRl;
    @InjectView(R.id.order_details_cancelRl)
    RelativeLayout cancelRl;
    @InjectView(R.id.order_detail_timePayLl)
    View timrPayLl;

    private MyOrderDetailsActivity INSTANCE;
    private OrderDeatilBean.DataEntity entity;

    public OptionsPickerView optionsPickerView;
    private OrderDetailInfoAdapter adapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE = this;
        id = getIntent().getStringExtra(GlobalConsts.INIT_DATA);
        initTimer();
    }


    private void initTimer() {

        CountdownTimer timer = new CountdownTimer(1800000, 1000) {
            @Override
            public void onTick(long millisUntilFinished, int percent) {

                if (millisUntilFinished != 0) {
                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                    String ms = format.format(millisUntilFinished);
                    timerTv.setText(ms.substring(0, 2) + "分钟" + ms.substring(3, ms.length()) + "秒");
                }
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    private void setViews() {

        orderDetailsCodeTv.setText("订单编号:" + entity.getOrder_no());
        orderDetailsTimesTv.setText("下单时间:" + entity.getCreated_at());
        orderDetailsCancelTv.setText(entity.getCancel_reason());
        orderDetailsNameTv.setText("收货人:" + entity.getShipping_name());
        orderDetailsAddressTv.setText("收货地址:" + entity.getShipping_address());
        orderDetailsPhoneTv.setText(entity.getShipping_mobile());
        orderDetailsTimeTv.setText("租赁日期:" + entity.getRental_date());
        orderDetailsMumberTv.setText("服装格子增项X" + entity.getClothing_lattice_num());
        orderDetailsMumberPriceTv.setText("¥" + entity.getClothing_lattice_price());
        orderDetailTotalPriceTv.setText("¥"+entity.getAmount_price());
        orderDetailLongPriceTv.setText("¥"+entity.getLongtime_price());
        orderDetailDepositTv.setText("¥"+entity.getAmount_shipping());
        orderDetailDiscountTv.setText("¥"+entity.getDiscount_price());
        orderDetailVipTv.setText("¥"+entity.getMembership_discount_price());
        orderDetailShipTv.setText("¥"+entity.getAmount_price());
        orderDetailsPriceTv.setText("合计: ¥"+entity.getTotal_sale());
        orderDetailRemarkTv.setText(entity.getRemark());


        //是否付费延期
        if (0 == entity.getIs_pay_longtime()) {
            orderDetailOutRl.setVisibility(View.GONE);
            orderDetailLongPriceRl.setVisibility(View.GONE);
            orderDetailsOutChooseTv.setText("未选择");
        } else {
            orderDetailOutRl.setVisibility(View.VISIBLE);
            orderDetailLongPriceRl.setVisibility(View.VISIBLE);
            orderDetailsOutChooseTv.setText("已选择");
            orderDetailOutTv.setText("(" + "个周期,¥" + entity.getLongtime_price() + ")");
        }

        //设置商品信息
        adapter=new OrderDetailInfoAdapter(INSTANCE,entity.getOrder_detail());
        orderDetailsLv.setAdapter(adapter);
        ListUtils.setListViewHeightBasedOnChildrens(orderDetailsLv);


        //1.待支付 2.已支付 3.部分发货 4.已发货 5.待归还 6. 部分归还7.已完成 8.超时 9.取消 10.部分退货 11整单退货 12.已关闭 13.已收货 14.已逾期
        switch (entity.getStatus()){
            case "1":
                orderDetailsStatusTv.setText("待付款");
                statusRl.setVisibility(View.VISIBLE);
                orderDetailsPayTv.setVisibility(View.VISIBLE);
                cancel.setText("取消订单");
                orderDetailsPayTv.setText("去支付");
                cancelRl.setVisibility(View.GONE);
                timrPayLl.setVisibility(View.VISIBLE);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCancelOrder();
                    }
                });

                orderDetailsPayTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(INSTANCE, PayActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "2":
                orderDetailsStatusTv.setText("已付款");
                statusRl.setVisibility(View.VISIBLE);
                cancel.setText("申请退款");
                cancelRl.setVisibility(View.GONE);
                orderDetailsPayTv.setVisibility(View.GONE);
                timrPayLl.setVisibility(View.GONE);
                break;
            case "4":
                orderDetailsStatusTv.setText("已发货");
                statusRl.setVisibility(View.VISIBLE);
                cancel.setText("申请退款");
                orderDetailsPayTv.setText("查看物流");
                cancelRl.setVisibility(View.GONE);
                orderDetailsPayTv.setVisibility(View.VISIBLE);
                timrPayLl.setVisibility(View.GONE);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(INSTANCE,LogisticsActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "7":
                orderDetailsStatusTv.setText("已完成");
                statusRl.setVisibility(View.VISIBLE);
                cancel.setText("查看评价");
                cancelRl.setVisibility(View.GONE);
                orderDetailsPayTv.setVisibility(View.GONE);
                timrPayLl.setVisibility(View.GONE);
                break;
            case "8":
                orderDetailsStatusTv.setText("已超时");
                statusRl.setVisibility(View.GONE);
                cancelRl.setVisibility(View.VISIBLE);
                orderDetailsCancelTv.setText("订单超时");
                timrPayLl.setVisibility(View.GONE);
                break;
            case "9":
                orderDetailsStatusTv.setText("已取消");
                statusRl.setVisibility(View.GONE);
                cancelRl.setVisibility(View.VISIBLE);
                timrPayLl.setVisibility(View.GONE);
                break;
            case "12":
                orderDetailsStatusTv.setText("已关闭");
                statusRl.setVisibility(View.GONE);
                cancelRl.setVisibility(View.GONE);
                timrPayLl.setVisibility(View.GONE);
                break;
            case "13":
                orderDetailsStatusTv.setText("已收货");
                statusRl.setVisibility(View.VISIBLE);
                cancelRl.setVisibility(View.GONE);
                timrPayLl.setVisibility(View.GONE);
                cancel.setVisibility(View.VISIBLE);
                cancel.setText("去评价");

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(INSTANCE,EvaluateActivity.class);
                        intent.putExtra(GlobalConsts.INIT_DATA,entity);
                        startActivity(intent);
                    }
                });
                break;

        }


        orderDetailsStatusTv.setText("已收货");
        statusRl.setVisibility(View.VISIBLE);
        cancelRl.setVisibility(View.GONE);
        timrPayLl.setVisibility(View.GONE);
        cancel.setVisibility(View.VISIBLE);
        cancel.setText("去评价");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(INSTANCE,EvaluateActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,entity);
                startActivity(intent);
            }
        });

    }


    private void iniData() {

        HttpServiceClient.getInstance().user_orderDearil(MyApplication.token, id).enqueue(new Callback<OrderDeatilBean>() {
            @Override
            public void onResponse(Call<OrderDeatilBean> call, Response<OrderDeatilBean> response) {
                if (response.isSuccessful()) {
                    if ("ok".equals(response.body().getStatus())) {
                        entity = response.body().getData();
                        ContentUtil.makeLog("lzz","entityDeatils:"+ JsonUtil.toJson(entity));
                        setViews();
                        setListeners();
                    } else {
                        ContentUtil.makeToast(INSTANCE, response.body().getError().getMessage());
                    }
                }else {
                    ContentUtil.makeLog("lzz","1111");
                }
            }

            @Override
            public void onFailure(Call<OrderDeatilBean> call, Throwable t) {

                ContentUtil.makeLog("lzz","00000"+t.toString());
            }
        });
    }

    private void setListeners() {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void showCancelOrder(){
        final ArrayList<String> list=new ArrayList<>();
        list.add("我不想买了");
        list.add("信息填写错误,重新购买");
        list.add("卖家缺货");
        list.add("其他原因");
        optionsPickerView = new OptionsPickerView.Builder(INSTANCE, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                ContentUtil.makeLog("lzz",list.get(options1));
                orderDetailsStatusTv.setText("已取消");
                statusRl.setVisibility(View.GONE);
                cancelRl.setVisibility(View.VISIBLE);
                timrPayLl.setVisibility(View.GONE);
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("选择取消订单原因")
                .setOutSideCancelable(false)//点击外部dismiss, default true
                .setSubCalSize(18)//确定取消按钮大小
                .setLineSpacingMultiplier(1.8f) //设置两横线之间的间隔倍数（范围：1.2 - 2.0倍 文字高度）
                .setDividerColor(Color.parseColor("#CBB6D8"))//设置分割线的颜色
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(18)//设置滚轮文字大小
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .build();
        optionsPickerView.setPicker(list);
        optionsPickerView.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        iniData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
