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
 * 更换手机号码
 */
public class ReplacePhoneActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 手机号码
     */
    @InjectView(R.id.replace_phone_number)
    EditText replacePhoneNumber;
    /**
     * 验证码
     */
    @InjectView(R.id.replace_code)
    EditText replaceCode;
    /**
     * 获取验证码
     */
    @InjectView(R.id.replace_code_button)
    TextView replaceCodeButton;

    /**
     * 语音验证码title
     */
    @InjectView(R.id.replace_Voice_title)
    TextView replace_Voice_title;
    /**
     * 实验语音验证码
     */
    @InjectView(R.id.replace_Voice_code)
    TextView replaceVoiceCode;
    /**
     * 更换
     */
    @InjectView(R.id.replace_submit)
    TextView replaceSubmit;

    private  String mobile="";
    boolean isPhone = false;
    boolean isCode = false;
    boolean isTime = true;


    private BaseBean baseBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_phone);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
    }

    /**
     * 加载页面
     */
    public void initView() {

        replacePhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    if (isCode) {
                        replaceSubmit.setTextColor(getResources().getColor(R.color.white));
                        replaceSubmit.setBackgroundResource(R.drawable.tv_textview_black_bg);
                        replaceSubmit.setClickable(true);
                    }
                    if (isTime) {
                        //获取验证码
                        replaceCodeButton.setTextColor(getResources().getColor(R.color.violet));
                        replaceCodeButton.setBackgroundResource(R.drawable.tv_textview_violet);
                        replaceCodeButton.setClickable(true);
                        isPhone = true;
                    } else {
                        replaceCodeButton.setTextColor(getResources().getColor(R.color.gray));
                        replaceCodeButton.setBackgroundResource(R.drawable.tv_textview_gray);
                        replaceCodeButton.setClickable(false);
                    }
                    //输入手机号码为11位之后验证码自动获取焦点
                    replaceCode.requestFocus();
                } else {
                    isPhone = false;
                    replaceSubmit.setTextColor(getResources().getColor(R.color.gray));
                    replaceSubmit.setBackgroundResource(R.drawable.tv_textview_white_bg);
                    replaceSubmit.setClickable(false);
                    //获取验证码
                    replaceCodeButton.setTextColor(getResources().getColor(R.color.gray));
                    replaceCodeButton.setBackgroundResource(R.drawable.tv_textview_gray);
                    replaceCodeButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        replaceCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4 && isPhone) {
                    replaceSubmit.setTextColor(getResources().getColor(R.color.white));
                    replaceSubmit.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    replaceSubmit.setClickable(true);
                    isCode = true;
                } else {
                    isCode = false;
                    replaceSubmit.setTextColor(getResources().getColor(R.color.gray));
                    replaceSubmit.setBackgroundResource(R.drawable.tv_textview_gray);
                    replaceSubmit.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    /**
     * 网络请求 更换手机号
     */
    public void requestData(String code) {
        dialog.show();
        mobile = TXShareFileUtil.getInstance().getString(GlobalConsts.USER_MOBILE, null);
        Map<String,Object> map=new HashMap<>();
        map.put("mobile",mobile);
        map.put("code",code);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().replace_mobile(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if(response.isSuccessful()){
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
//                                setViewData();
                                ToastUtil.showMessage("手机号码更换成功  请重新登录");
                                Intent intent = new Intent();
                                intent.setClass(ReplacePhoneActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUtil.showMessage(baseBean.getError().getMessage());
                            }
                        }else {
                            //接口访问失败

                        }

                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 获取验证码
     */
    public void requestSms() {
        dialog.show();
        String phone = replacePhoneNumber.getText().toString().trim();
        HttpServiceClient.getInstance().sms(phone)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            BaseBean bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                ToastUtil.showMessage("短信已发送,请查收验证码");
                                replaceCode.requestFocus();//获取光标
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
     * 语音验证码
     */
    public void requestVoice() {
        dialog.show();
        String phone = replacePhoneNumber.getText().toString().trim();
        HttpServiceClient.getInstance().voice(phone)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                ToastUtil.showMessage("请注意接听语音验证码");
                                replaceVoiceCode.setVisibility(View.GONE);
                                replace_Voice_title.setText("电话拨打中...请留意相关电话");
                                replaceCode.requestFocus();//获取光标
                            } else {
                                ToastUtil.showMessage(baseBean.getError().getMessage());
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
     * 填充数据
     */
    public void initData() {

    }


    /**
     * 点击事件
     *
     * @param
     */
    @OnClick({R.id.replace_code_button, R.id.replace_Voice_code, R.id.replace_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            //获取验证码码
            case R.id.replace_code_button:
                requestSms();
                setTime();
                break;
            //使用语音验证码
            case R.id.replace_Voice_code:
                if (replacePhoneNumber.getText().toString().trim().length() == 11) {
                    requestVoice();
                } else {
                    ToastUtil.showMessage("手机号码为11位");
                }
                break;
            //更换
            case R.id.replace_submit:
                requestData(replaceCode.getText().toString());
                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("更换新手机号");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                ReplacePhoneActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//            }
//        });
    }

    /****
     * 启动定时器
     */
    public void setTime() {
        //获取验证码
        isTime = false;
        replaceCodeButton.setTextColor(getResources().getColor(R.color.gray));
        replaceCodeButton.setBackgroundResource(R.drawable.tv_textview_gray);
        replaceCodeButton.setClickable(false);
        CountDownTimer cdt = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                replaceCodeButton.setText(millisUntilFinished / 1000 + "秒后重发");
            }

            @Override
            public void onFinish() {
                //获取验证码
                replaceCodeButton.setTextColor(getResources().getColor(R.color.violet));
                replaceCodeButton.setBackgroundResource(R.drawable.tv_textview_violet);
                replaceCodeButton.setClickable(true);
                isPhone = true;
                isTime = true;
                replaceCodeButton.setText("重新获取验证码");
            }
        };

        cdt.start();
    }


}
