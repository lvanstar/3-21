package com.qiansong.msparis.app.find.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.find.adapter.MyCircleItemAdapter;
import com.qiansong.msparis.app.find.bean.CommentsAllBean;
import com.qiansong.msparis.app.find.bean.MyCircleBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的朋友圈
 */
public class MyCircleActivity extends BaseActivity {

    @InjectView(R.id.find_head_back)
    RelativeLayout findHeadBack;
    @InjectView(R.id.find_head_share)
    RelativeLayout findHeadShare;
    @InjectView(R.id.find_head_start_image)
    ImageView findHeadStartImage;
    @InjectView(R.id.find_head_start)
    RelativeLayout findHeadStart;
    @InjectView(R.id.find_head_start_num)
    TextView findHeadStartNum;
    @InjectView(R.id.find_user_image)
    SimpleDraweeView findUserImage;
    @InjectView(R.id.find_user_name)
    TextView findUserName;
    @InjectView(R.id.find_text1)
    TextView findText1;
    @InjectView(R.id.find_text2)
    TextView findText2;
    @InjectView(R.id.find_list)
    XRecyclerView findList;

    private MyCircleItemAdapter adapter;
    private MyCircleBean bean;
    //用户tonken
    private String token="";

    private int page = 1;
    private int page_size = 10;

    private String  usreId ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);
        ButterKnife.inject(this);
        initView();
    }

    /**
     * 初始化页面
     */
    public void initView() {
        findHeadStart.setVisibility(View.GONE);
        //获取用户id
        usreId = getIntent().getStringExtra("user_id");
        usreId = "1";

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyCircleActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findList.setLayoutManager(linearLayoutManager);
        findList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        findList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        adapter = new MyCircleItemAdapter(this);
        findList.setAdapter(adapter);

        requestData();
    }

    /**
     * 网络请求  页面基本数据
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取收货地址
        HttpServiceClient.getInstance().find_user(token,usreId,page+"",page_size+"")
                .enqueue(new Callback<MyCircleBean>() {
                    @Override
                    public void onResponse(Call<MyCircleBean> call, Response<MyCircleBean> response) {
                        findList.refreshComplete();
                        findList.loadMoreComplete();
                        dialog.cancel();
                        if(response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showAnimToast(bean.getError().getMessage());
                            }
                        }else{
                            ToastUtil.showAnimToast("网络异常");
                        }
                    }
                    @Override
                    public void onFailure(Call<MyCircleBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 设置页面数据
     */
    public void initData() {
        adapter.setData(bean.getData().getRows());
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.find_head_back, R.id.find_head_share})
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.find_head_back:
                super.onBackPressed();
                break;
            //分享
            case R.id.find_head_share:
                break;
        }
    }
}
