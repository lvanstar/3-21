package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.mine.adapter.PrivileqesAdapter;
import com.qiansong.msparis.app.mine.bean.OldCouponBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.OldCouponAdapter;
import com.qiansong.msparis.app.mine.bean.PrivilegeBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * coalei
 * <p>
 * 优惠券列表
 */
public class AllPrivilegesActivity extends BaseActivity {

    @InjectView(R.id.coupon_list)
    XRecyclerView couponList;
    private ETitleBar titleBar;

    private PrivileqesAdapter adapter;
    //用户tonken
    private String token = "";
    private PrivilegeBean bean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_privileges);
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
        adapter = new PrivileqesAdapter(this);
        couponList.setAdapter(adapter);
        couponList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
            @Override
            public void onLoadMore() {
            }
        });

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().member_privilege(token)
                    .enqueue(new Callback<PrivilegeBean>() {
                        @Override
                        public void onResponse(Call<PrivilegeBean> call, Response<PrivilegeBean> response) {
                            couponList.refreshComplete();
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
                        public void onFailure(Call<PrivilegeBean> call, Throwable t) {

                        }
                    });
        }

    }
    /**
     * 填充数据
     */
    public void initData() {
        adapter.setData(bean.getData().getRows());
    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("全部特权");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                AllPrivilegesActivity.super.onBackPressed();
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
