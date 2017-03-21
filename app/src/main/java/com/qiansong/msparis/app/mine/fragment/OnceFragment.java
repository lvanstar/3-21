package com.qiansong.msparis.app.mine.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.OnceJsonBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.mine.adapter.OnceAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/20.
 * 我的衣橱-曾经拥有
 */

public class OnceFragment extends BaseFragment {
    private View view;
    private GridView list_once;
    private PullToRefreshView refresh;
    OnceAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_once,null);
        setID();setListener();init();
        return view;
    }
    //填充
    private void init() {
        adapter=new OnceAdapter(getActivity(),null);
        list_once.setAdapter(adapter);
        HttpServiceClient.getInstance().user_history_wardrobe(MyApplication.token,null,null).enqueue(new Callback<OnceJsonBean>() {
            @Override
            public void onResponse(Call<OnceJsonBean> call, Response<OnceJsonBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    adapter.updata(response.body().getData().getRows());
                }
            }

            @Override
            public void onFailure(Call<OnceJsonBean> call, Throwable t) {

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
        refresh.setFooter(true);
        refresh.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(PullToRefreshView view) {
                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onFooterRefreshComplete();
                    }
                }, 1000);
            }
        });
    }
    //id
    private void setID() {
        list_once= (GridView) view.findViewById(R.id.list_once);
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
    }
}
