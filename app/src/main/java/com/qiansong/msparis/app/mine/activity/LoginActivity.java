package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.bean.LoginBean;
import com.qiansong.msparis.app.mine.bean.ThirdBean;
import com.qiansong.msparis.app.mine.bean.ThirdLoginBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * caolei
 * <p>
 * 这是登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {

    @InjectView(R.id.title_bar)
    ETitleBar titleBar;

    /**
     * 手机号码
     */
    @InjectView(R.id.login_phone_number)
    EditText loginPhoneNumber;

    /**
     * 验证码
     */
    @InjectView(R.id.login_code)
    EditText loginCode;
    /**
     * 获取验证码
     */
    @InjectView(R.id.login_code_button)
    TextView loginCodeButton;
    /**
     * 用户注册协议
     */
    @InjectView(R.id.login_protocol)
    TextView loginProtocol;
    @InjectView(R.id.login_Voice_title)
    TextView loginVoiceTitle;
    /**
     * 语音验证码
     */
    @InjectView(R.id.login_Voice_code)
    TextView loginVoiceCode;
    /**
     * 登录
     */
    @InjectView(R.id.login_sign_in)
    TextView loginSignIn;
    /**
     * QQ
     */
    @InjectView(R.id.login_qq)
    TextView loginQq;
    /**
     * 微信登录
     */
    @InjectView(R.id.login_wei_xin)
    TextView loginWeiXin;
    /**
     * 微博登录
     */
    @InjectView(R.id.login_wei_bo)
    TextView loginWeiBo;

    //第三方登录保存信息
    private ThirdLoginBean thirdLoginBean;
    private ThirdBean thirdBean;

    boolean isPhone = false;
    boolean isCode = false;
    boolean isTime = true;

    private LoginBean bean = null;

    private BaseBean baseBean;
    private String third_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
    }


    /**
     * 加载页面
     */
    public void initView() {
        loginCodeButton.setOnClickListener(this);
        loginProtocol.setOnClickListener(this);
        loginVoiceCode.setOnClickListener(this);
        loginSignIn.setOnClickListener(this);
        loginQq.setOnClickListener(this);
        loginWeiXin.setOnClickListener(this);
        loginWeiBo.setOnClickListener(this);
        loginCodeButton.setClickable(false);

        loginPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    if (isCode) {
                        loginSignIn.setTextColor(getResources().getColor(R.color.white));
                        loginSignIn.setBackgroundResource(R.drawable.tv_textview_black_bg);
                        loginSignIn.setClickable(true);
                    }
                    if (isTime) {
                        //获取验证码
                        loginCodeButton.setTextColor(getResources().getColor(R.color.violet));
                        loginCodeButton.setBackgroundResource(R.drawable.tv_textview_violet);
                        loginCodeButton.setClickable(true);
                        isPhone = true;
                    } else {
                        loginCodeButton.setTextColor(getResources().getColor(R.color.gray));
                        loginCodeButton.setBackgroundResource(R.drawable.tv_textview_gray);
                        loginCodeButton.setClickable(false);
                    }
                    //输入手机号码为11位之后验证码自动获取焦点
                    loginCode.requestFocus();
                } else {
                    isPhone = false;
                    loginSignIn.setTextColor(getResources().getColor(R.color.gray));
                    loginSignIn.setBackgroundResource(R.drawable.tv_textview_white_bg);
                    loginSignIn.setClickable(false);
                    //获取验证码
                    loginCodeButton.setTextColor(getResources().getColor(R.color.gray));
                    loginCodeButton.setBackgroundResource(R.drawable.tv_textview_gray);
                    loginCodeButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        loginCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4 && isPhone) {
                    loginSignIn.setTextColor(getResources().getColor(R.color.white));
                    loginSignIn.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    loginSignIn.setClickable(true);
                    isCode = true;
                } else {
                    isCode = false;
                    loginSignIn.setTextColor(getResources().getColor(R.color.gray));
                    loginSignIn.setBackgroundResource(R.drawable.tv_textview_gray);
                    loginSignIn.setClickable(false);
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
        dialog.show();
        String phone = loginPhoneNumber.getText().toString().trim();
        HttpServiceClient.getInstance().sms(phone)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            BaseBean bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                ContentUtil.makeTestToast(LoginActivity.this, "短信已发送,请查收验证码");
                                loginCode.requestFocus();//获取光标
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
     * 直接登录
     */
    public void requestLogin() {
        dialog.show();
        String phone = loginPhoneNumber.getText().toString().trim();
        String code = loginCode.getText().toString().trim();
        Map<String, String> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("code", code);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        HttpServiceClient.getInstance().login(body)
                .enqueue(new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                setViewData();
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {
                            ToastUtil.showMessage("网络异常");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 语音验证码
     */
    public void requestVoice() {
        dialog.show();
        String phone = loginPhoneNumber.getText().toString().trim();
        HttpServiceClient.getInstance().voice(phone)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                ToastUtil.showMessage("请注意接听语音验证码");
                                loginVoiceCode.setVisibility(View.GONE);
                                loginVoiceTitle.setText("电话拨打中...请留意相关电话");
                                loginCode.requestFocus();//获取光标
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
     * 第三方登录
     * 1微信 2QQ 3新浪
     */
    public void requestThirdLogin() {
        dialog.show();
        Map<String, String> map = new HashMap<>();
        map.put("open_id", thirdLoginBean.open_id);
        map.put("type", thirdLoginBean.type);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().thirdParty(body)
                .enqueue(new Callback<ThirdBean>() {
                    @Override
                    public void onResponse(Call<ThirdBean> call, Response<ThirdBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            thirdBean = response.body();
                            if ("ok".equals(thirdBean.getStatus())) {
                                thirdLoginData("");
                                ToastUtil.showMessage("登录成功");
                            } else {
                                if ("11011".equals(thirdBean.getError().getCode())) {
                                    thirdLoginData(thirdBean.getError().getCode());
                                } else {
                                    ToastUtil.showMessage(thirdBean.getError().getMessage());
                                }
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ThirdBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 设置第三方登录信息
     */
    public void thirdLoginData(String errorCode) {
        TXShareFileUtil.getInstance().putString(GlobalConsts.ACCESS_TOKEN, thirdBean.getData().getAccess_token());
        if (thirdBean.getData().getMobile() != null && thirdBean.getData().getMobile().length() > 0) {
            TXShareFileUtil.getInstance().putString(GlobalConsts.USER_MOBILE, thirdBean.getData().getMobile());
        }
        //判断是不是新用户  新用户要去绑定手机号码
        if ("11011".equals(errorCode)) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, BinDingPhoneNumActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("third_data", thirdLoginBean);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        this.finish();
    }

    /**
     * 设置会员的信息*
     **/
    public void setViewData() {
        //保存token值到内存
        TXShareFileUtil.getInstance().putString(GlobalConsts.ACCESS_TOKEN, bean.getData().getAccess_token());
        TXShareFileUtil.getInstance().putString(GlobalConsts.MOBILE, bean.getData().getMobile());
        TXShareFileUtil.getInstance().putBoolean(GlobalConsts.IS_LOGIN, true);
        MyApplication.isLogin = true;
        MyApplication.token = bean.getData().getAccess_token();

        //判断是不是新用户  0否 1是
        if (bean.getData().getNew_user() != null && Integer.parseInt(bean.getData().getNew_user()) == 1) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, UserMessageActivity.class);
            startActivity(intent);
        }
        this.finish();
    }


    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            //获取验证码
            case R.id.login_code_button:
                requestSms();
//                dialog.show();
                setTime();
                break;
            //用户注册协议
            case R.id.login_protocol:

                break;
            //语音验证码
            case R.id.login_Voice_code:
                if (loginPhoneNumber.getText().toString().trim().length() == 11) {
                    requestVoice();
                } else {
                    ToastUtil.showMessage("手机号码为11位");
                }

                break;
            //登录
            case R.id.login_sign_in:
                requestLogin();
                break;
            //QQ登录
            case R.id.login_qq:
                third_type = "1";
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.SSOSetting(false);
                authorize(qq);

                break;
            //微信登录
            case R.id.login_wei_xin:
                third_type = "2";
                Platform wechat = ShareSDK.getPlatform(this, Wechat.NAME);
                wechat.SSOSetting(false);
                if (!wechat.isClientValid()) {
                    ToastUtil.showMessage("微信未安装,请先安装微信");
                }
                authorize(wechat);
                break;
            //微博登录
            case R.id.login_wei_bo:
                third_type = "3";
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                sina.SSOSetting(true);
                authorize(sina);
        }

    }

    /**
     * 选择第三方  进行登录
     *
     * @param platform
     * @param action
     * @param hashMap
     */
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
        ToastUtil.showMessage("onComplete", 0);
        if (action == Platform.ACTION_USER_INFOR) {
            //判断用户是否授权
            if (platform.isAuthValid()) {
                thirdLoginBean = new ThirdLoginBean();
                PlatformDb platformDb = platform.getDb();
//                thirdLoginBean.open_id =platformDb.get
                thirdLoginBean.type = third_type;
                thirdLoginBean.nickname = platformDb.getUserName();
                thirdLoginBean.gender = platformDb.getUserGender();
                thirdLoginBean.head_portrait = platformDb.getUserIcon();
                ToastUtil.showMessage(thirdLoginBean.toString());
                Log.i("kevin", thirdLoginBean.toString());
                requestThirdLogin();

            } else {
                ToastUtil.showMessage("用户已拒绝授权");
            }
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        ToastUtil.showMessage("onError", 0);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        ToastUtil.showMessage("onCancel", 0);
    }

    // 第三方登录选择 要数据不要功能
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("快捷登录");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                LoginActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(ChatYeActivity.this,"点击了标题栏右边",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /****
     * 启动定时器
     */
    public void setTime() {
        //获取验证码
        isTime = false;
        loginCodeButton.setTextColor(getResources().getColor(R.color.gray));
        loginCodeButton.setBackgroundResource(R.drawable.tv_textview_white_bg);
        loginCodeButton.setClickable(false);
        CountDownTimer cdt = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                loginCodeButton.setText(millisUntilFinished / 1000 + "秒后重发");
            }

            @Override
            public void onFinish() {
                //获取验证码
                loginCodeButton.setTextColor(getResources().getColor(R.color.violet));
                loginCodeButton.setBackgroundResource(R.drawable.tv_textview_violet);
                loginCodeButton.setClickable(true);
                isPhone = true;
                isTime = true;
                loginCodeButton.setText("获取验证码");
            }
        };

        cdt.start();
    }

}
