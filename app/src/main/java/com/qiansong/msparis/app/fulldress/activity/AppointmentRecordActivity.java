package com.qiansong.msparis.app.fulldress.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.fulldress.bean.AppointmentRecordBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.fulldress.adapter.AppointmentRecordAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 预约记录
 */
public class AppointmentRecordActivity extends BaseActivity {

    @InjectView(R.id.appointment_record_list)
    XRecyclerView appointmentRecordList;

    private ETitleBar titleBar;
    private AppointmentRecordAdapter adapter;
    //用户tonken
    private String token = "";
    private AppointmentRecordBean baseBean = null ;
    private int page = 1;
    private int page_size = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_record);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AppointmentRecordActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        appointmentRecordList.setLayoutManager(linearLayoutManager);
        appointmentRecordList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        appointmentRecordList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        adapter = new AppointmentRecordAdapter(this);
        appointmentRecordList.setAdapter(adapter);
        appointmentRecordList.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().booking(token, page+"" ,page_size+"")
                .enqueue(new Callback<AppointmentRecordBean>() {
                    @Override
                    public void onResponse(Call<AppointmentRecordBean> call, Response<AppointmentRecordBean> response) {
                        appointmentRecordList.refreshComplete();
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showMessage(baseBean.getError().getMessage());
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<AppointmentRecordBean> call, Throwable t) {

                    }
                });


    }

    /**
     * 填充数据
     */
    public void initData() {
        adapter.setData(baseBean.getData().getRows());

    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("预约记录");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                AppointmentRecordActivity.super.onBackPressed();
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
