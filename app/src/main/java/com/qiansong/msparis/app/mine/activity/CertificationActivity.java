package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.IDCardUtil;
import com.qiansong.msparis.app.mine.bean.CertificationBean;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.FollowBrandBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.util.Eutil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 实名认证-yechen
 */
public class CertificationActivity extends AppCompatActivity {
    public static String intent_key="CertificationActivity";
    CertificationActivity context;
    CertificationBean bean;
    String type;//0未认证 1身份证实名认证 2押金认证
    LinearLayout layout_0,layout_1,layout_2;
    EditText et_name,et_id;
    TextView user_realname_edit;//按钮

    int input_num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        context=this;
        setTitleBar();
        type=getIntent().getStringExtra(intent_key);
        if (type==null)return;

        init();

    }

    private void setListener_0() {
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if (!"".equals(et_name.getText().toString())){
                  user_realname_edit.setBackgroundColor(Color.parseColor("#333333"));
                  user_realname_edit.setTextColor(context.getResources().getColor(R.color.white));
              }else {
                  user_realname_edit.setBackground(context.getResources().getDrawable(R.drawable.shap_kuang));
                  user_realname_edit.setTextColor(context.getResources().getColor(R.color.font19));
              }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void user_realname_edit(View view){
        String name=et_name.getText().toString();
        String id=et_id.getText().toString();
        if ("".equals(name)&&"".equals(id)){
            ContentUtil.makeToast(context,"请输入姓名和身份证信息");
            return;
        }else if (!"".equals(name)&&"".equals(id)){
            ContentUtil.makeToast(context,"请输入身份证信息");
            return;
        }else if ("".equals(name)&&!"".equals(id)){
            ContentUtil.makeToast(context,"请输入姓名");
            return;
        }
        if (input_num<20&&!IDCardUtil.validateIDNum(id).isLegal()){//身份证合法性
            ContentUtil.makeToast(context,IDCardUtil.validateIDNum(id).getError());
            input_num++;
            return;
        }
        input_num=0;
        HttpServiceClient.getInstance().user_realname_edit(MyApplication.token,name,id).enqueue(new Callback<FollowBrandBean>() {
            @Override
            public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) {
                    return;
                }
                finish();
            }

            @Override
            public void onFailure(Call<FollowBrandBean> call, Throwable t) {

            }
        });
    }
    private void init_data_0(){
        TextView yajing= (TextView) findViewById(R.id.yajing);
        yajing.setText(Eutil.getHighlight("#8A2293","您也可以通过支付认证押金继续","支付认证押金"));
         et_name= (EditText) findViewById(R.id.et_name);
         et_id= (EditText) findViewById(R.id.et_id);
        user_realname_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_realname_edit(v);
            }
        });
        setListener_0();
    }
    private void init_data_1(){
        HttpServiceClient.getInstance().user_realname(MyApplication.token).enqueue(new Callback<CertificationBean>() {
            @Override
            public void onResponse(Call<CertificationBean> call, Response<CertificationBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) return;
                bean=response.body();
                ((TextView)findViewById(R.id.id_number)).setText(bean.getData().getId_number());
                ((TextView)findViewById(R.id.real_name)).setText(bean.getData().getReal_name());

            }

            @Override
            public void onFailure(Call<CertificationBean> call, Throwable t) {

            }
        });
    }
    private void init_data_2(){
    }
    private void init() {

        layout_0= (LinearLayout) findViewById(R.id.layout_0);
        layout_1= (LinearLayout) findViewById(R.id.layout_1);
        layout_2= (LinearLayout) findViewById(R.id.layout_2);
        user_realname_edit= (TextView) findViewById(R.id.user_realname_edit);
        if ("0".equals(type)){
            layout_0.setVisibility(View.VISIBLE);
            layout_1.setVisibility(View.GONE);
            layout_2.setVisibility(View.GONE);
            init_data_0();
        }else if ("1".equals(type)){
            layout_0.setVisibility(View.GONE);
            layout_1.setVisibility(View.VISIBLE);
            layout_2.setVisibility(View.GONE);
            init_data_1();
        }else if ("2".equals(type)){
            layout_0.setVisibility(View.GONE);
            layout_1.setVisibility(View.GONE);
            layout_2.setVisibility(View.VISIBLE);
            init_data_2();
        }

    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar)findViewById(R.id.title_bar);
        titleBar.setTitle("实名或押金认证");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
//        titleBar.setRightImageResource(R.mipmap.ic_launcher);//设置右边图标
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//设置背景
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
            }
        });
    }
}
