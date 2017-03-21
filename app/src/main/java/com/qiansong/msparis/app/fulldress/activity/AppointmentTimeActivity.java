package com.qiansong.msparis.app.fulldress.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.fulldress.adapter.AppointmentItemAdapter;
import com.qiansong.msparis.app.fulldress.bean.LookingTimeBean;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 选择预约时间
 */
public class AppointmentTimeActivity extends BaseActivity {

    @InjectView(R.id.appointment_layout)
    LinearLayout appointmentLay;
    @InjectView(R.id.appointment_day_1)
    TextView appointmentDay1;
    @InjectView(R.id.appointment_time_1)
    TextView appointmentTime1;
    @InjectView(R.id.appointment_lay_1)
    LinearLayout appointmentLay1;
    @InjectView(R.id.appointment_day_2)
    TextView appointmentDay2;
    @InjectView(R.id.appointment_time_2)
    TextView appointmentTime2;
    @InjectView(R.id.appointment_lay_2)
    LinearLayout appointmentLay2;
    @InjectView(R.id.appointment_day_3)
    TextView appointmentDay3;
    @InjectView(R.id.appointment_time_3)
    TextView appointmentTime3;
    @InjectView(R.id.appointment_lay_3)
    LinearLayout appointmentLay3;
    @InjectView(R.id.appointment_day_4)
    TextView appointmentDay4;
    @InjectView(R.id.appointment_lay_4)
    LinearLayout appointmentLay4;
    @InjectView(R.id.appointment_list)
    XRecyclerView appointmentList;
    private ETitleBar titleBar;
    private AppointmentItemAdapter adapter;

    //用户tonken
    private String token = "";
    public LookingTimeBean userBean = null;
    public String data = "";
    public String type = "";
    public String store_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_time);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        type = getIntent().getStringExtra("type");
        store_id = getIntent().getStringExtra("store_id");
        data= DateUtil.NowString(0);

        appointmentTime1.setText(DateUtil.getDateOne(0));
        appointmentTime2.setText(DateUtil.getDateOne(1));
        appointmentTime3.setText(DateUtil.getDateOne(2));

        GridLayoutManager linearLayoutManager = new GridLayoutManager(AppointmentTimeActivity.this,4);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        appointmentList.setLayoutManager(linearLayoutManager);
        appointmentList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        appointmentList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        appointmentList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData();
            }

            @Override
            public void onLoadMore() {

            }
        });


        adapter = new AppointmentItemAdapter(this);
        appointmentList.setAdapter(adapter);


    }

    /**
     * 网络请求
     */
    public void requestData() {
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().booking_time(token,data,type,store_id)
                .enqueue(new Callback<LookingTimeBean>() {
                    @Override
                    public void onResponse(Call<LookingTimeBean> call, Response<LookingTimeBean> response) {
                        appointmentList.refreshComplete();
                        if (response.isSuccessful()) {
                            userBean = response.body();
                            if ("ok".equals(userBean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showMessage(userBean.getError().getMessage());
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<LookingTimeBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 填充数据
     */
    public void initData() {
        adapter.setDate(data);//设置天的格式
        adapter.setData(userBean.getData().getRows());
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.appointment_lay_1, R.id.appointment_lay_2, R.id.appointment_lay_3, R.id.appointment_lay_4})
    public void onClick(View view) {
        switch (view.getId()) {
            //今天
            case R.id.appointment_lay_1:
                setBackground(1);
                data = DateUtil.NowString(0);
                requestData();
                break;
            //明天
            case R.id.appointment_lay_2:
                setBackground(2);
                data = DateUtil.NowString(1);
                requestData();
                break;
            //后天
            case R.id.appointment_lay_3:
                setBackground(3);
                data = DateUtil.NowString(2);
                requestData();
                break;
            //更多
            case R.id.appointment_lay_4:
                setBackground(4);
//                //选择时间返回日期
//                CalendarView calendarView = new CalendarView(this);
//                calendarView.setSetOnclickListener(new CalendarView.setOnclickListener() {
//                    @Override
//                    public void reurnDate(String date) {
//                        ToastUtil.showMessage(date);
//                    }
//                });
//                calendarView.show(appointmentLay);
//                MaterialCalendarView materialCalendarView = new MaterialCalendarView(this);
//                materialCalendarView.showContextMenuForChild(appointmentLay);

//                data = ;
//                requestData();
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("预约时间");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                AppointmentTimeActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(AppointmentTimeActivity.this, AppointmentRecordActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    //点击设置背景颜色
    public void setBackground(int index) {
        appointmentDay1.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentTime1.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentLay1.setBackgroundResource(R.drawable.tv_textview_color_gray);

        appointmentDay2.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentTime2.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentLay2.setBackgroundResource(R.drawable.tv_textview_color_gray);

        appointmentDay3.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentTime3.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentLay3.setBackgroundResource(R.drawable.tv_textview_color_gray);

        appointmentDay4.setTextColor(getResources().getColor(R.color.__picker_black_40));
        appointmentLay4.setBackgroundResource(R.drawable.tv_textview_color_gray);
        switch (index) {
            case 1:
                appointmentDay1.setTextColor(getResources().getColor(R.color.white));
                appointmentTime1.setTextColor(getResources().getColor(R.color.white));
                appointmentLay1.setBackgroundResource(R.drawable.tv_textview_color_violet);
                break;
            case 2:
                appointmentDay2.setTextColor(getResources().getColor(R.color.white));
                appointmentTime2.setTextColor(getResources().getColor(R.color.white));
                appointmentLay2.setBackgroundResource(R.drawable.tv_textview_color_violet);
                break;
            case 3:
                appointmentDay3.setTextColor(getResources().getColor(R.color.white));
                appointmentTime3.setTextColor(getResources().getColor(R.color.white));
                appointmentLay3.setBackgroundResource(R.drawable.tv_textview_color_violet);
                break;
            case 4:
                appointmentDay4.setTextColor(getResources().getColor(R.color.white));
                appointmentLay4.setBackgroundResource(R.drawable.tv_textview_color_violet);
                break;
        }
    }
}
