package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.CouponAdapter;
import com.qiansong.msparis.app.mine.bean.CouponBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * coalei
 * <p>
 * 优惠券列表
 */
public class CouponActivity extends BaseActivity {

    @InjectView(R.id.coupon_list)
    XRecyclerView couponList;
    @InjectView(R.id.coupon_edit)
    EditText couponEdit;
    @InjectView(R.id.coupon_button)
    TextView couponButton;
    @InjectView(R.id.look_old_coupon)
    TextView lookOldCoupon;
    private ETitleBar titleBar;

    private CouponAdapter adapter;
    private CheckBox checkBox;
    private TextView couponHeatNum;
    //用户tonken
    private String token = "";
    private CouponBean bean = null;
    private int page = 1;
    private  int page_size = 10;
    private BaseBean baseBean;

    List<CouponBean.DataBean.RowsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        couponList.setLayoutManager(linearLayoutManager);
        couponList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        couponList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        adapter = new CouponAdapter(this);
        couponList.setAdapter(adapter);
        couponList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page =1;
                requestData();
            }

            @Override
            public void onLoadMore() {
                page +=1;
                requestData();
            }
        });
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
//        View view = inflater.inflate(R.layout.item_coupon_heat, null);
//        couponList.addHeaderView(view);
//        checkBox = (CheckBox) view.findViewById(R.id.coupon_checkbox);
//        couponHeatNum = (TextView) view.findViewById(R.id.coupon_heat_num);

        //没有输入数据CheckBox是不可点击的
        couponEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    couponButton.setTextColor(getResources().getColor(R.color.white));
                    couponButton.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    couponButton.setClickable(true);
                } else {
                    couponButton.setTextColor(getResources().getColor(R.color.gray));
                    couponButton.setBackgroundResource(R.drawable.tv_textview_white_bg);
                    couponButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    /**
     * 网络请求  获取优惠券列表
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().mycoupon(token,page+"",page_size+"")
                    .enqueue(new Callback<CouponBean>() {
                        @Override
                        public void onResponse(Call<CouponBean> call, Response<CouponBean> response) {
                            couponList.refreshComplete();
                            couponList.loadMoreComplete();
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                bean = response.body();
                                if ("ok".equals(bean.getStatus())) {
                                    initData();
                                }else{
                                    ToastUtil.showMessage(bean.getError().getMessage());
                                }
                            } else {
                                ToastUtil.showMessage("网络连接异常");
                            }
                        }
                        @Override
                        public void onFailure(Call<CouponBean> call, Throwable t) {

                        }
                    });
        }

    }

    /**
     * 网络请求  兑换优惠券
     */
    public void exchangeCoupon(String code) {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",token);
        map.put("code",code);
        map.put("channel","android");
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().exchange_coupon(body)
                    .enqueue(new Callback<BaseBean>() {
                        @Override
                        public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                baseBean = response.body();
                                if ("ok".equals(baseBean.getStatus())) {
                                    ToastUtil.showMessage("兑换成功");
                                }else{
                                    ToastUtil.showMessage(baseBean.getError().getMessage());
                                }
                            } else {
                                ToastUtil.showMessage("网络连接异常");
                            }
                        }
                        @Override
                        public void onFailure(Call<BaseBean> call, Throwable t) {
                        }
                    });
        }

    }

    /**
     * 填充数据
     */
    public void initData() {
        if (page == 1) {
            list = bean.getData().getRows();
            adapter.setData(list);
            couponList.setLoadingMoreEnabled(true);
        } else {
            list.addAll(bean.getData().getRows());
            adapter.setData(list);
            if (list.size() == Integer.parseInt(bean.getData().getTotal())) {
                couponList.setLoadingMoreEnabled(false);
            }

        }
    }


    /**
     *  点击事件
     * @param view
     */
    @OnClick({R.id.look_old_coupon, R.id.coupon_button})
    public void onClick(View view) {
        switch (view.getId()) {
            //查看历史优惠券
            case R.id.look_old_coupon:
                Intent intent = new Intent();
                startActivity(new Intent(CouponActivity.this, OldCouponActivity.class));
                break;
            // 兑换优惠券
            case R.id.coupon_button:
                exchangeCoupon(couponEdit.getText().toString().trim());
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("优惠券");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("积分明细");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                CouponActivity.super.onBackPressed();
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
