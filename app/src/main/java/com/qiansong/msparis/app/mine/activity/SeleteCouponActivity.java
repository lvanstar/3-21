package com.qiansong.msparis.app.mine.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.bean.SeleteCouponBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.mine.adapter.SeleteCouponAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * coalei
 * <p>
 * 选择优惠券列表
 */
public class SeleteCouponActivity extends BaseActivity {

    @InjectView(R.id.coupon_list)
    XRecyclerView couponList;
    private ETitleBar titleBar;

    private SeleteCouponAdapter adapter;
    private CheckBox checkBox;
    //用户tonken
    private String token = "";
    private SeleteCouponBean bean = null;
    private int page = 1;
    private  int page_size = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selete_coupon);
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
        adapter = new SeleteCouponAdapter(this);
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
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
        View view = inflater.inflate(R.layout.item_coupon_heat, null);
        couponList.addHeaderView(view);
        checkBox = (CheckBox) view.findViewById(R.id.coupon_checkbox);
//        couponHeatNum = (TextView) view.findViewById(R.id.coupon_heat_num);


    }

    /**
     * 网络请求  获取优惠券列表
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().coupon_selete(token,page+"",page_size+"")
                    .enqueue(new Callback<SeleteCouponBean>() {
                        @Override
                        public void onResponse(Call<SeleteCouponBean> call, Response<SeleteCouponBean> response) {
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
                        public void onFailure(Call<SeleteCouponBean> call, Throwable t) {

                        }
                    });
        }

    }

    /**
     * 填充数据
     */
    public void initData() {
//        adapter.setData(bean.getData().getRows());
    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("选择优惠券");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("积分明细");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                SeleteCouponActivity.super.onBackPressed();
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
