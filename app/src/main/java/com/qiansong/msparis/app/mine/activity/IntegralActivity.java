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
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.IntgegralItemAdapter;
import com.qiansong.msparis.app.mine.bean.IntegralBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * coalei
 * <p>
 * 积分明细
 */
public class IntegralActivity extends BaseActivity {

    @InjectView(R.id.integral_list)
    XRecyclerView integralList;
    private ETitleBar titleBar;
    private IntgegralItemAdapter adapter ;

    private String token = "";
    private IntegralBean bean = null;
    private int page = 1;
    private  int page_size = 10;
    private TextView integralNum;

    List<IntegralBean.DataBean.RowsBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(IntegralActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        integralList.setLayoutManager(linearLayoutManager);
        integralList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        integralList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        //t
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //第一个参数为xml文件中view的id，第二个参数为此view的父组件，可以为null，android会自动寻找它是否拥有父组件
        View view = inflater.inflate(R.layout.item_integral_heat, null);
        integralList.addHeaderView(view);
        integralNum = (TextView) view.findViewById(R.id.integral_num);

        adapter = new IntgegralItemAdapter(this);
        integralList.setAdapter(adapter);

        integralList.setLoadingListener(new XRecyclerView.LoadingListener() {
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
    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().user_points(token,page+"",page_size+"")
                    .enqueue(new Callback<IntegralBean>() {
                        @Override
                        public void onResponse(Call<IntegralBean> call, Response<IntegralBean> response) {
                            integralList.refreshComplete();
                            integralList.loadMoreComplete();
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
                        public void onFailure(Call<IntegralBean> call, Throwable t) {

                        }
                    });
        }
    }

    /**
     * 填充数据
     */
    public void initData() {
        integralNum.setText("我的积分:"+bean.getData().getPoints());
        adapter.setData(bean.getData().getRows());

        if (page == 1) {
            list = bean.getData().getRows();
            adapter.setData(list);
            integralList.setLoadingMoreEnabled(true);
        } else {
            list.addAll(bean.getData().getRows());
            adapter.setData(list);
            if (list.size() == Integer.parseInt(bean.getData().getTotal()+"")) {
                integralList.setLoadingMoreEnabled(false);
            }

        }
    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("积分明细");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                IntegralActivity.super.onBackPressed();
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
