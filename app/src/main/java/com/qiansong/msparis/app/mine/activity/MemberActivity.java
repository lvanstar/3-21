package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.MemberItemAdapter;
import com.qiansong.msparis.app.mine.bean.MineBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * coalei
 * <p>
 * 会员中心
 */
public class MemberActivity extends BaseActivity {

    @InjectView(R.id.member_image)
    SimpleDraweeView memberImage;
    @InjectView(R.id.member_rule)
    TextView memberRule;
    @InjectView(R.id.member_name)
    TextView memberName;
    @InjectView(R.id.member_lv)
    TextView memberLv;
    @InjectView(R.id.member_num)
    TextView memberNum;
    @InjectView(R.id.member_title)
    TextView memberTitle;
    @InjectView(R.id.member_huodong)
    TextView memberHuodong;
    @InjectView(R.id.member_gridview)
    GridView gridview;
    @InjectView(R.id.member_layout)
    LinearLayout memberLayout;

    private ETitleBar titleBar;

    private MemberItemAdapter adapter;

    //用户tonken
    private String token="";
    private  MineBean bean = null ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
        initData();
    }

    @Override
    protected void onResume() {
        requestData();
        super.onResume();
    }

    /**
     * 加载页面
     */
    public void initView() {
        ListUtils.setGridViewHeightBasedOnChildren(gridview,4);
        adapter = new MemberItemAdapter(this);
        gridview.setAdapter(adapter);

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().centre(token)
                    .enqueue(new Callback<MineBean>() {
                        @Override
                        public void onResponse(Call<MineBean> call, Response<MineBean> response) {
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                bean = response.body();
                                if ("ok".equals(bean.getStatus())) {
                                    setViewData();
                                }else{
                                    ToastUtil.showMessage(bean.getError().getMessage());
                                }
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<MineBean> call, Throwable t) {

                        }
                    });
        }

    }


    /**
     * 设置会员的信息*
     **/
    public void setViewData() {
        ExclusiveUtils.setImageUrl(memberImage, bean.getData().getHead_portrait(), -1);
        memberName.setText(bean.getData().getLevel_name());
        memberLv.setText(bean.getData().getLevel_name());
        memberNum.setText(bean.getData().getIntegral()+"积分");
        memberTitle.setText("距离下次升级还差 "+bean.getData().getUpgrade_need_intergral()+" 积分");
        adapter.setData(bean.getData().getMember_privilege());
        //是否已经签到了  1已签到 0未签到
        if(Integer.parseInt(bean.getData().getIs_sign()) == 1){
            memberRule.setText("已签到");
        }else if(Integer.parseInt(bean.getData().getIs_sign()) == 0){
            memberRule.setText("未签到");
        }

    }
    /**
     * 填充数据
     */
    public void initData() {
    }


    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.member_rule, R.id.member_num,R.id.member_layout})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //会员特权
            case R.id.member_layout:
                intent.setClass(MemberActivity.this, AllPrivilegesActivity.class);
                startActivity(intent);
                break;
            //签到
            case R.id.member_rule:
                intent.setClass(MemberActivity.this, SignActivity.class);
                startActivity(intent);
                break;
            //积分明细
            case R.id.member_num:
//                intent.setClass(MemberActivity.this, IntegralActivity.class);
//                startActivity(intent);
                break;
        }
    }
    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("会员中心");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setRightTitle("积分明细");
        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                MemberActivity.super.onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MemberActivity.this, IntegralActivity.class);
                startActivity(intent);
            }
        });
    }

}
