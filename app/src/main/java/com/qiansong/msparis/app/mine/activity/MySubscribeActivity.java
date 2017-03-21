package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.FollowBrandBean;
import com.qiansong.msparis.app.commom.bean.MyBrandBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.PullToRefreshLunView;

import java.util.Hashtable;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的订阅(品牌) YECHEN
 */
public class MySubscribeActivity extends BaseActivity {
    MySubscribeActivity context;
    MyBrandBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subscribe);
        context=this;
        setTitleBar();
        init();
    }
    private void init_data(){
        ListView dingyue_list= (ListView) findViewById(R.id.dingyue_list);
        dingyue_list.setAdapter(new MySubscribeAdapter());
    }
    PullToRefreshLunView refresh;
    private void init() {
        refresh= (PullToRefreshLunView) findViewById(R.id.refresh);
        refresh.setOnHeaderRefreshListener(new PullToRefreshLunView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshLunView view) {

                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onHeaderRefreshComplete();
                    }
                }, 1000);
            }
        });
        HttpServiceClient.getInstance().user_brands(MyApplication.token,"1","1000").enqueue(new Callback<MyBrandBean>() {
            @Override
            public void onResponse(Call<MyBrandBean> call, Response<MyBrandBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) return;
                bean=response.body();
                init_data();
            }

            @Override
            public void onFailure(Call<MyBrandBean> call, Throwable t) {

            }
        });

    }

    //设置标题栏
    private void setTitleBar(){
        ETitleBar titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("我的订阅");//设置标题
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
                startActivity(new Intent(context,ClothingRecordActivity.class));
            }
        });
    }
     class MySubscribeAdapter extends BaseAdapter{
         @Override
         public int getCount() {
             return bean!=null?bean.getData().getRows().size():0;
         }

         @Override
         public Object getItem(int position) {
             return bean.getData().getRows().get(position);
         }

         @Override
         public long getItemId(int position) {
             return position;
         }

         @Override
         public View getView(final int position, View convertView, ViewGroup parent) {
             final ViewHolder viewholder;
             if(convertView==null){
                 convertView=View.inflate(context, R.layout.item_dingyue,null);
                 viewholder=new ViewHolder(convertView);
                 convertView.setTag(viewholder);
             }else {
                 viewholder= (ViewHolder) convertView.getTag();
             }
             viewholder.name.setText(bean.getData().getRows().get(position).getName());
             ExclusiveUtils.setImageUrl(viewholder.logo,bean.getData().getRows().get(position).getLogo(),-1);
             viewholder.dingyue.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (bean.getData().getRows().get(position).is_dingyue){
                         viewholder.dingyue.setText("订阅");
                         viewholder.dingyue.setTextColor(context.getResources().getColor(R.color.font19));
                         bean.getData().getRows().get(position).is_dingyue=false;
                         follow_brands(position,bean.getData().getRows().get(position).getId()+"",false,viewholder);
                     }else {
                         viewholder.dingyue.setText("已订阅");
                         viewholder.dingyue.setTextColor(context.getResources().getColor(R.color.font20));
                         bean.getData().getRows().get(position).is_dingyue=true;
                         follow_brands(position,bean.getData().getRows().get(position).getId()+"",true,viewholder);
                     }
                 }
             });
             return convertView;
         }
         /**
          * 品牌关注-取消 TODO true 订阅  false 取消订阅
          */
         public  void follow_brands(final int position, String id, boolean is_attent, final ViewHolder finalViewHolder){
             Map<String,String> map=new Hashtable<>();
             map.put("access_token",MyApplication.token);
             map.put("id",id);
             RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));

             if (is_attent) {
                 HttpServiceClient.getInstance().user_brands_0(body).enqueue(new Callback<FollowBrandBean>() {
                     @Override
                     public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
                         if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) {
                             finalViewHolder.dingyue.setText("订阅");
                             bean.getData().getRows().get(position).is_dingyue=false;
                             finalViewHolder.dingyue.setTextColor(context.getResources().getColor(R.color.font19));
                             return;
                         }
                         ContentUtil.makeToast(MyApplication.getApp(),"订阅成功");
                     }

                     @Override
                     public void onFailure(Call<FollowBrandBean> call, Throwable t) {
                         finalViewHolder.dingyue.setText("订阅");
                         bean.getData().getRows().get(position).is_dingyue=false;
                         finalViewHolder.dingyue.setTextColor(context.getResources().getColor(R.color.font19));
                     }
                 });
                 return;
             }
             HttpServiceClient.getInstance().user_brands_1(body).enqueue(new Callback<FollowBrandBean>() {
                 @Override
                 public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
                     if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) {
                         finalViewHolder.dingyue.setText("已订阅");
                         bean.getData().getRows().get(position).is_dingyue=true;
                         finalViewHolder.dingyue.setTextColor(context.getResources().getColor(R.color.font20));
                         return;
                     }
                     ContentUtil.makeToast(MyApplication.getApp(),"取消订阅成功");
                 }

                 @Override
                 public void onFailure(Call<FollowBrandBean> call, Throwable t) {
                     finalViewHolder.dingyue.setText("已订阅");
                     bean.getData().getRows().get(position).is_dingyue=true;
                     finalViewHolder.dingyue.setTextColor(context.getResources().getColor(R.color.font20));
                 }
             });
         }
         private class ViewHolder{
             private SimpleDraweeView logo;
             private TextView name,dingyue;
             public ViewHolder(View view){
                 logo= (SimpleDraweeView) view.findViewById(R.id.logo);
                 name= (TextView) view.findViewById(R.id.name);
                 dingyue= (TextView) view.findViewById(R.id.dingyue);

             }
         }
     }
}
