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
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.bean.LoginBean;
import com.qiansong.msparis.app.mine.bean.ThirdLoginBean;

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
 * kevin .cao
 * <p>
 * 绑定用户手机号码
 */
public class BinDingPhoneNumActivity extends BaseActivity {


    /**
     * 提示
     */
    @InjectView(R.id.band_title)
    TextView bandTitle;
    /**
     * 输入手机号码
     */
    @InjectView(R.id.band_phone_num)
    EditText bandPhoneNum;
    /**
     * 验证码
     */
    @InjectView(R.id.band_code)
    EditText bandCode;

    /**
     * 获取验证码按钮
     */
    @InjectView(R.id.band_code_button)
    TextView bandCodeButton;
    /**
     * 绑定按钮
     */
    @InjectView(R.id.band_button)
    TextView bandButton;
    /**
     * 语音验证码
     */
    @InjectView(R.id.band_Voice_code)
    TextView bandVoiceCode;
    @InjectView(R.id.band_Voice_title)
    TextView band_Voice_title;
    //第三方登录成功之后传递过来的数据
    private ThirdLoginBean thirdLoginBean;

    boolean isPhone = false;
    boolean isCode = false;
    boolean isTime = true;

    private BaseBean baseBean;

    private LoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_ding_phone_num);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
    }

    /**
     * 加载页面
     */
    public void initView() {
        thirdLoginBean = (ThirdLoginBean)getIntent().getSerializableExtra(GlobalConsts.ADDRESS_INTENT);


        bandPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    if (isCode) {
                        bandButton.setTextColor(getResources().getColor(R.color.white));
                        bandButton.setBackgroundResource(R.drawable.tv_textview_black_bg);
                        bandButton.setClickable(true);
                    }
                    if (isTime) {
                        //获取验证码
                        bandCodeButton.setTextColor(getResources().getColor(R.color.violet));
                        bandCodeButton.setBackgroundResource(R.drawable.tv_textview_violet);
                        bandCodeButton.setClickable(true);
                        isPhone = true;
                    } else {
                        bandCodeButton.setTextColor(getResources().getColor(R.color.gray));
                        bandCodeButton.setBackgroundResource(R.drawable.tv_textview_gray);
                        bandCodeButton.setClickable(false);
                    }
                    //输入手机号码为11位之后验证码自动获取焦点
                    bandCode.requestFocus();
                } else {
                    isPhone = false;
                    bandButton.setTextColor(getResources().getColor(R.color.gray));
                    bandButton.setBackgroundResource(R.drawable.tv_textview_gray);
                    bandButton.setClickable(false);
                    //获取验证码
                    bandCodeButton.setTextColor(getResources().getColor(R.color.gray));
                    bandCodeButton.setBackgroundResource(R.drawable.tv_textview_white_bg);
                    bandCodeButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        bandCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4 && isPhone) {
                    bandButton.setTextColor(getResources().getColor(R.color.white));
                    bandButton.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    bandButton.setClickable(true);
                    isCode = true;
                } else {
                    isCode = false;
                    bandButton.setTextColor(getResources().getColor(R.color.gray));
                    bandButton.setBackgroundResource(R.drawable.tv_textview_gray);
                    bandButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    /**
     * 语音验证码
     */
    public void requestVoice() {
        dialog.show();
        String phone = bandPhoneNum.getText().toString().trim();
        HttpServiceClient.getInstance().voice(phone)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        if (response.isSuccessful()) {
                            dialog.cancel();
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                ToastUtil.showMessage("请注意接听语音验证码");
                                bandVoiceCode.setVisibility(View.GONE);
                                band_Voice_title.setText("电话拨打中...请留意相关电话");
                                bandCode.requestFocus();//获取光标
                            } else {
                                ContentUtil.makeTestToast(BinDingPhoneNumActivity.this, baseBean.getError().getMessage());
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
     * 获取验证码
     */
    public void requestSms() {
        dialog.show();
        String phone = bandPhoneNum.getText().toString().trim();
        HttpServiceClient.getInstance().sms(phone)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            BaseBean bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                ContentUtil.makeTestToast(BinDingPhoneNumActivity.this, "短信已发送,请查收验证码");
                                bandCode.requestFocus();//获取光标
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
     * 网络请求  绑定手机号
     */
    public void requestData() {
        dialog.show();
        if(thirdLoginBean != null ){
            Map<String ,String > map = new HashMap<>();
            map.put("open_id",thirdLoginBean.open_id);
            map.put("type",thirdLoginBean.type);
            map.put("mobile",bandPhoneNum.getText().toString().trim());
            map.put("code",bandCode.getText().toString().trim());
            map.put("nickname",thirdLoginBean.nickname);
            map.put("gender",thirdLoginBean.gender);
            map.put("head_portrait",thirdLoginBean.head_portrait);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

            HttpServiceClient.getInstance()
                    .binding_mobile(body)
                    .enqueue(new Callback<LoginBean>() {
                        @Override
                        public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                loginBean = response.body();
                                if ("ok".equals(loginBean.getStatus())) {
                                    setViewData();
                                } else {
                                   ToastUtil.showMessage(loginBean.getError().getMessage());
                                }
                            } else {

                            }

                        }

                        @Override
                        public void onFailure(Call<LoginBean> call, Throwable t) {

                        }
                    });
        }
    }


    /**
     * 绑定手机号
     * 设置会员的信息*
     **/
    public void setViewData() {
        //保存token值到内存
        TXShareFileUtil.getInstance().putString(GlobalConsts.ACCESS_TOKEN, loginBean.getData().getAccess_token());
        TXShareFileUtil.getInstance().putBoolean(GlobalConsts.IS_LOGIN, true);
        TXShareFileUtil.getInstance().putString(GlobalConsts.MOBILE, loginBean.getData().getMobile());
        MyApplication.isLogin = true;
        MyApplication.token = loginBean.getData().getAccess_token();

        //判断是不是新用户  0否 1是
        if (loginBean.getData().getNew_user() != null && Integer.parseInt(loginBean.getData().getNew_user()) == 1) {
            Intent intent = new Intent();
            intent.setClass(BinDingPhoneNumActivity.this, UserMessageActivity.class);
            startActivity(intent);
        } else {
            this.finish();
        }
    }
    /**
     * 填充数据
     */
    public void initData() {

    }



    /**
     *
     * 点击事件
     * @param view
     */
    @OnClick({R.id.band_code_button, R.id.band_button, R.id.band_Voice_code})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //获取验证码
            case R.id.band_code_button:
                requestSms();
//                dialog.show();
                setTime();
                break;
            //绑定按钮
            case R.id.band_button:
                intent.setClass(BinDingPhoneNumActivity.this, UserMessageActivity.class);
                startActivity(intent);
                break;
            //语音验证码
            case R.id.band_Voice_code:
                if (bandPhoneNum.getText().toString().trim().length() == 11) {
                    requestVoice();
                } else {
                    ToastUtil.showMessage("手机号码为11位");
                }
                break;
        }
    }
    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("绑定手机号");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                BinDingPhoneNumActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(BinDingPhoneNumActivity.this, UpdateAddressActivity.class);
//                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_ADD);
//                startActivity(intent);
//            }
//        });
    }


    /****
     * 启动定时器
     */
    public void setTime() {
        //获取验证码
        isTime = false;
        bandCodeButton.setTextColor(getResources().getColor(R.color.gray));
        bandCodeButton.setBackgroundResource(R.drawable.tv_textview_white_bg);
        bandCodeButton.setClickable(false);
        CountDownTimer cdt = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                bandCodeButton.setText(millisUntilFinished / 1000 + "秒后重发");
            }

            @Override
            public void onFinish() {
                //获取验证码
                bandCodeButton.setTextColor(getResources().getColor(R.color.violet));
                bandCodeButton.setBackgroundResource(R.drawable.tv_textview_violet);
                bandCodeButton.setClickable(true);
                isPhone = true;
                isTime = true;
                bandCodeButton.setText("重新获取验证码");
            }
        };

        cdt.start();
    }

}
