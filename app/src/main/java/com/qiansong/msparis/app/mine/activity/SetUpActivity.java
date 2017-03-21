package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.commom.util.iosdialog.IosDialogActivity;
import com.qiansong.msparis.app.commom.util.iosdialog.widget.AlertDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * coalei
 * <p>
 * 设置页面
 */
public class SetUpActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.setup_list1)
    LinearLayout setupList1;
    @InjectView(R.id.setup_list2)
    LinearLayout setupList2;
    @InjectView(R.id.setup_list3)
    LinearLayout setupList3;
    @InjectView(R.id.setup_list4)
    LinearLayout setupList4;
    @InjectView(R.id.setup_exit_login)
    TextView setupExitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
        initData();
    }

    /**
     * 加载页面
     */
    public void initView() {

    }

    /**
     * 网络请求
     */
    public void requestData() {

    }

    /**
     * 填充数据
     */
    public void initData() {

    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.setup_list1, R.id.setup_list2, R.id.setup_list3, R.id.setup_list4, R.id.setup_exit_login})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //我的地址
            case R.id.setup_list1:
                intent.setClass(this, AddressActivity.class);
                startActivity(intent);
                break;
            //更换手机号
            case R.id.setup_list2:
                intent.setClass(this, VerificationOldPhoneActivity.class);
                startActivity(intent);
                break;
            //给我评分
            case R.id.setup_list3:
                break;
            //关于
            case R.id.setup_list4:
                break;
            //退出登陆
            case R.id.setup_exit_login:
                new AlertDialog(SetUpActivity.this).builder()
                        .setMsg("确定退出登录？")
                        .setCancelable(false)
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showAnimToast("退出登录");
                                TXShareFileUtil.getInstance().putString(GlobalConsts.ACCESS_TOKEN, "");
                                TXShareFileUtil.getInstance().putBoolean(GlobalConsts.IS_LOGIN, false);
                                MyApplication.isLogin = false;
                            }
                        }).show();


                break;
        }
    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("设置");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                SetUpActivity.super.onBackPressed();
            }
        });
//        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(ChatYeActivity.this,"点击了标题栏右边",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
