package com.qiansong.msparis.app.mine.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.WishListBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.mine.adapter.WishAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/27.
 * 心愿单-0全部 1日常 2礼服
 */

@SuppressLint("ValidFragment")
public class WishListAllFragment extends BaseFragment {
    private View view;
    private GridView list_wish;
    private PullToRefreshView refresh;
    String type;//0全部 1日常 2礼服
    WishAdapter adapter;
    WishListBean bean;

    @SuppressLint("ValidFragment")
    public WishListAllFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_wish_all,null);
        setID();setListener();init();
        return view;
    }
    //填充
    private void init() {
        HttpServiceClient.getInstance().user_wish(MyApplication.token,type,null,null).enqueue(new Callback<WishListBean>() {
            @Override
            public void onResponse(Call<WishListBean> call, Response<WishListBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    bean=response.body();
                    adapter=new WishAdapter(getActivity(),bean.getData().getRows());
                    list_wish.setAdapter(adapter);
                    list_wish.setFocusable(false);
                }
            }

            @Override
            public void onFailure(Call<WishListBean> call, Throwable t) {

            }
        });

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
        list_wish= (GridView) view.findViewById(R.id.list_wish);
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
    }
}
