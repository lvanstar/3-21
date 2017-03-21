package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayoutThree;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.CalendarUtil.CalendarDay;
import com.qiansong.msparis.app.commom.util.CalendarUtil.CalendarUtils;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.bean.SignsBean;
import com.qiansong.msparis.app.mine.bean.SignsRequestBean;

import java.util.ArrayList;
import java.util.Calendar;
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
 * 签到
 */
public class SignActivity extends BaseActivity {

    @InjectView(R.id.sign_button)
    ImageView signButton;
    @InjectView(R.id.sign_num)
    TextView signNum;
    @InjectView(R.id.sign_day)
    TextView signDay;
    @InjectView(R.id.imageView3)
    ImageView imageView3;

    @InjectView(R.id.sign_calendar)
    CalendarLayoutThree calendarLayoutThree;
    private ETitleBar titleBar;
    //用户tonken
    private String token = "";
    private SignsBean bean = null;
    private SignsRequestBean signsRequestBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        //获取现在的时间
        requestData(DateUtil.NowString(0));
    }

    /**
     * 加载页面
     */
    public void initView() {


        calendarLayoutThree.bindData(generateSignedData());

    }


    /**
     * 数据
     * @return
     */
    public List<Integer> generateSignedData() {
        ArrayList<Integer> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        CalendarDay.today().copyTo(calendar);
        for (int i = 0; i < 30; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Log.i("dates", CalendarUtils.convert2Integer(calendar)+"");
            list.add(CalendarUtils.convert2Integer(calendar));
        }
        return list;
    }

    /**
     * 网络请求  获取数据
     */
    public void requestData(String date) {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().signs(token, date)
                .enqueue(new Callback<SignsBean>() {
                    @Override
                    public void onResponse(Call<SignsBean> call, Response<SignsBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if (bean != null) {
                                if ("ok".equals(bean.getStatus())) {
                                    setData();
                                } else {
                                    ToastUtil.showMessage(bean.getError().getMessage());
                                }
                            }
                        } else {
                            ToastUtil.showMessage("网络异常");
                        }
                    }

                    @Override
                    public void onFailure(Call<SignsBean> call, Throwable t) {

                    }
                });

    }


    /**
     * 网络请求  签到
     */
    public void requestSingnData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",token);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        HttpServiceClient.getInstance().Requst_signs(body)
                .enqueue(new Callback<SignsRequestBean>() {
                    @Override
                    public void onResponse(Call<SignsRequestBean> call, Response<SignsRequestBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            signsRequestBean = response.body();
                            if (signsRequestBean != null) {
                                if ("ok".equals(signsRequestBean.getStatus())) {
                                    ToastUtil.showMessage(signsRequestBean.getData().getMessage());
                                    //获取现在的时间
                                    requestData(DateUtil.NowString(0));
                                } else {
                                    ToastUtil.showMessage(signsRequestBean.getError().getMessage());
                                }
                            }
                        } else {
                            ToastUtil.showMessage("网络异常");
                        }
                    }

                    @Override
                    public void onFailure(Call<SignsRequestBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.sign_button, R.id.sign_num})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击签到
            case R.id.sign_button:
                //未签到才能签到
                if(bean.getData().getIs_sign() == 0){
                    requestSingnData();
                }
                break;
            // 点击我的积分
            case R.id.sign_num:
                break;
        }
    }

    /**
     * 填充数据
     */
    public void setData() {
        //1已签到 0未签到
        if(bean.getData().getIs_sign() == 1){
            signButton.setBackgroundResource(R.drawable.mine_yqd);
        }else if(bean.getData().getIs_sign() == 0){
            signButton.setBackgroundResource(R.drawable.mine_qd);
        }
        signNum.setText("我的积分："+bean.getData().getPoints());
        signDay.setText("已连续签到："+bean.getData().getConsecutive_day()+" 天");
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("签到");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
//        titleBar.setRightTitle("积分明细");
//        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                SignActivity.super.onBackPressed();
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
