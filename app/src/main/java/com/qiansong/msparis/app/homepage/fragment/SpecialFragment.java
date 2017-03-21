package com.qiansong.msparis.app.homepage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.HomeTopicsBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 专题馆
 */

public class SpecialFragment extends BaseFragment {
    private View view;
    private PullToRefreshView refresh;

    HomeTopicsBean bean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_special,null);
        setID();setListener();init();
        return view;
    }
    //填充
    private void init() {
        HttpServiceClient.getInstance().home_topics(MyApplication.token,1,100).enqueue(new Callback<HomeTopicsBean>() {
            @Override
            public void onResponse(Call<HomeTopicsBean> call, Response<HomeTopicsBean> response) {
                if (!response.isSuccessful()||!response.body().getStatus().equals("ok"))return;
                bean=response.body();
                ( (ListView)view.findViewById(R.id.list)).setAdapter(new MyAdapter());

            }

            @Override
            public void onFailure(Call<HomeTopicsBean> call, Throwable t) {

            }
        });
    }
    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return bean.getData().getRows()!=null?bean.getData().getRows().size():0;
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
                convertView=View.inflate(getActivity(), R.layout.item_zhuanti,null);
                viewholder=new ViewHolder(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder= (ViewHolder) convertView.getTag();
            }
            if (null!=bean.getData().getRows().get(position).getImage_src())
            ExclusiveUtils.setImageUrl(viewholder.image_src,bean.getData().getRows().get(position).getImage_src(),-1);
            return convertView;
        }

        private class ViewHolder{
            SimpleDraweeView image_src;
            public ViewHolder(View view){
                image_src= (SimpleDraweeView) view.findViewById(R.id.image_src);
            }
        }
    }
    //监听
    private void setListener() {
        refresh.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshView view) {
                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onHeaderRefreshComplete();
                    }
                }, 1000);
            }
        });
    }
    //id
    private void setID() {
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
    }
}
