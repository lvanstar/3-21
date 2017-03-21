package com.qiansong.msparis.app.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.FollowBrandBean;
import com.qiansong.msparis.app.commom.bean.InviteBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 邀请有奖
 */
public class ToInviteActivity extends BaseActivity {
    ToInviteActivity context;
    InviteBean bean;
    //对应信息 看接口文档 和接口一样的名字
    TextView invitation_code,invite_people,invitation_number,reward_days,ok;
    EditText invite_people_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_invite);
        context=this;
        setTitleBar();
        init();
    }
    private void init_data(){
        invite_people_et= (EditText) findViewById(R.id.invite_people_et);
        invitation_code= (TextView) findViewById(R.id.invitation_code);
        ok= (TextView) findViewById(R.id.ok);
        invite_people= (TextView) findViewById(R.id.invite_people);
        invitation_number= (TextView) findViewById(R.id.invitation_number);
        reward_days= (TextView) findViewById(R.id.reward_days);
        invitation_code.setText(bean.getData().getInvitation_code());
        if ("".equals(bean.getData().getInvite_people())){
            invite_people_et.setVisibility(View.VISIBLE);
            invite_people.setVisibility(View.GONE);
            ok.setVisibility(View.VISIBLE);
        }else {
            invite_people_et.setVisibility(View.GONE);
            invite_people.setVisibility(View.VISIBLE);
            invite_people.setText(bean.getData().getInvite_people());
            ok.setVisibility(View.GONE);
        }
        invitation_number.setText(bean.getData().getInvitation_number()+"人");
        reward_days.setText(bean.getData().getReward_days()+"天");
        GridView gridView= (GridView) findViewById(R.id.gridView);
        gridView.setFocusable(false);
        gridView.setAdapter(new MyAdapter());
        ListUtils.setGridViewHeightBasedOnChildren(gridView,3);
        invite_people_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(invite_people_et)){
                    ok.setAlpha(0.6f);
                }else {
                    ok.setAlpha(1.0f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 更新邀请人
     * @param view
     */
    public void invite_people(View view){
        if ("".equals(invite_people_et.getText().toString()))return;
        HttpServiceClient.getInstance().user_inviter(MyApplication.token,invite_people_et.getText().toString()).enqueue(new Callback<FollowBrandBean>() {
            @Override
            public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) return;
                invite_people_et.setVisibility(View.GONE);
                invite_people.setVisibility(View.VISIBLE);
                invite_people.setText(invite_people_et.getText().toString());
                ok.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<FollowBrandBean> call, Throwable t) {

            }
        });
    }
    private void init() {
        HttpServiceClient.getInstance().user_inviter(MyApplication.token).enqueue(new Callback<InviteBean>() {
            @Override
            public void onResponse(Call<InviteBean> call, Response<InviteBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) return;
                bean=response.body();
                init_data();
            }

            @Override
            public void onFailure(Call<InviteBean> call, Throwable t) {

            }
        });

    }
    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return bean!=null? bean.getData().getReward_information().size():0;
        }

        @Override
        public Object getItem(int position) {
            return bean.getData().getReward_information().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewholder;
            if(convertView==null){
                convertView=View.inflate(context, R.layout.item_invite,null);
                viewholder=new ViewHolder(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder= (ViewHolder) convertView.getTag();
            }
            ExclusiveUtils.setImageUrl(viewholder.img,bean.getData().getReward_information().get(position),-1);
            return convertView;
        }


        private class ViewHolder{
            private SimpleDraweeView img;
            public ViewHolder(View view){
                img= (SimpleDraweeView) view.findViewById(R.id.img);
            }
        }
    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("邀请有奖");//设置标题
        titleBar.setTitleColor(context.getResources().getColor(R.color.font19));
        titleBar.setLeftImageResource(R.mipmap.back);//设置左边图标
//        titleBar.setRightTitle("还衣记录");//设置右边文字
//        titleBar.setLeftTitle("返回");//设置左边文字
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
//                startActivity(new Intent(context,ClothingRecordActivity.class));
            }
        });
    }
}
