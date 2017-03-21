package com.qiansong.msparis.app.mine.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.UseDetailsItemAdapter;
import com.qiansong.msparis.app.mine.bean.UseDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.caolei
 *
 * 查看明细
 *
 */
public class UseDetailsActivity extends BaseActivity {

    TextView useTitle;
    @InjectView(R.id.use_list)
    XRecyclerView useList;
    private ETitleBar titleBar;
    private UseDetailsItemAdapter adapter ;


    //用户tonken
    private String token = "";
    public UseDetailsBean userBean = null;
    private int page= 1;
    private int  page_size= 10;

    List<UseDetailsBean.DataBean.RowsBean> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_details);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UseDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        useList.setLayoutManager(linearLayoutManager);
        useList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        useList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        //t
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
        View view = inflater.inflate(R.layout.use_details_title, null);
        useList.addHeaderView(view);
        useTitle = (TextView) view.findViewById(R.id.use_title);


        adapter = new UseDetailsItemAdapter(this);
        useList.setAdapter(adapter);



        //设置下拉刷新
        useList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                requestData();
            }

            @Override
            public void onLoadMore() {
                page+=1;
                requestData();
            }
        });

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().user_cards(token,page+"" ,page_size+"")
                .enqueue(new Callback<UseDetailsBean>() {
                    @Override
                    public void onResponse(Call<UseDetailsBean> call, Response<UseDetailsBean> response) {
                        useList.refreshComplete();
                        useList.loadMoreComplete();
                        dialog.cancel();
                        if(response.isSuccessful()) {
                            userBean = response.body();
                            if (userBean != null) {
                                if ("ok".equals(userBean.getStatus())) {
                                    initData();
//                                    //禁止加载
//                                    if(userBean.getData().getRows().size() == 0){
//                                        useList.setLoadingMoreEnabled(false);
//                                    }else{
//                                        useList.setLoadingMoreEnabled(true);
//                                    }
                                } else {
                                    ToastUtil.showMessage(userBean.getError().getMessage());
                                }
                            }
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<UseDetailsBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 填充数据
     */
    public void initData() {
        if (page == 1) {
            list = userBean.getData().getRows();
            adapter.setData(list);
            useList.setLoadingMoreEnabled(true);
        } else {
            list.addAll(userBean.getData().getRows());
            adapter.setData(list);
            if (list.size() == Integer.parseInt(userBean.getData().getTotal())) {
                useList.setLoadingMoreEnabled(false);
            }

        }
    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("查看明细");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                UseDetailsActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(AddressActivity.this, UpdateAddressActivity.class);
//                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_ADD);
//                startActivity(intent);
//            }
//        });
    }

}
