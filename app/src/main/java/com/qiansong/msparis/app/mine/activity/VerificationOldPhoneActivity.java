package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;

import java.util.HashMap;
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
 * 验证旧手机
 */
public class VerificationOldPhoneActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 输入验证码
     */
    @InjectView(R.id.old_phone_edit)
    EditText oldPhoneEdit;
    /**
     * 倒计时
     */
    @InjectView(R.id.old_phone_text)
    TextView oldPhoneText;

    /**
     * title
     */
    @InjectView(R.id.old_phone_title)
    TextView oldPhoneTitle;
    /**
     * 下一步
     */
    @InjectView(R.id.old_phone_submit)
    TextView oldPhoneSubmit;
    //tv_textview_black_bg

    private BaseBean bean;
    private String mobile = "";
    boolean isTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_old_phone);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestSms();
        setTime();
    }

    /**
     * 加载页面
     */
    public void initView() {
        mobile = TXShareFileUtil.getInstance().getString(GlobalConsts.USER_MOBILE, null);
        oldPhoneTitle.setText("我们已经发送了一条验证短信至" + mobile + "，请输入短信中的验证码，若手机已经停用请联系客服处理");
        oldPhoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    oldPhoneSubmit.setTextColor(getResources().getColor(R.color.white));
                    oldPhoneSubmit.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    oldPhoneSubmit.setClickable(true);
                } else {
                    oldPhoneSubmit.setTextColor(getResources().getColor(R.color.gray));
                    oldPhoneSubmit.setBackgroundResource(R.drawable.tv_textview_gray);
                    oldPhoneSubmit.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 获取验证码
     */
    public void requestSms() {
        HttpServiceClient.getInstance().sms(mobile)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        if (response.isSuccessful()) {
                            BaseBean bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                ToastUtil.showMessage("短信已发送,请查收验证码");
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {

                        }

                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
    }


    /**
     * 网络请求
     */
    public void requestData(String user_mobile, String code) {
        Map<String,Object> map=new HashMap<>();
        map.put("mobile",user_mobile);
        map.put("code",code);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().check_sms(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {
                    }
                });
    }

    /**
     * 数据处理
     */
    public void initData() {
        Intent intent = new Intent();
        intent.setClass(VerificationOldPhoneActivity.this, ReplacePhoneActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 点击事件
     *
     * @param
     */
    @OnClick({R.id.old_phone_text, R.id.old_phone_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击获取验证码
            case R.id.old_phone_text:
                requestSms();
                setTime();
                break;
            //下一步
            case R.id.old_phone_submit:
                requestData(mobile, oldPhoneEdit.getText().toString());
                break;
        }
    }


    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("验证旧手机");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                VerificationOldPhoneActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    /****
     * 启动定时器
     */
    public void setTime() {
        //获取验证码
        isTime = false;
        oldPhoneText.setTextColor(getResources().getColor(R.color.gray));
        oldPhoneText.setClickable(false);
        CountDownTimer cdt = new CountDownTimer(50000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                oldPhoneText.setText(millisUntilFinished / 1000 + " 秒后重发");
            }

            @Override
            public void onFinish() {
                //获取验证码
                oldPhoneText.setTextColor(getResources().getColor(R.color.violet));
                oldPhoneText.setClickable(true);
                isTime = true;
                oldPhoneText.setText("获取验证码");
            }
        };

        cdt.start();
    }


}
