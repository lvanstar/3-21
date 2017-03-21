package com.qiansong.msparis.app.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.UserWardrobeBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.mine.adapter.NowAccompaniedAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/20.
 * 我的衣橱-此刻相伴
 */

public class NowAccompaniedFragment extends BaseFragment {
    private View view;
    ListView list_now;
    PullToRefreshView refresh;

    NowAccompaniedAdapter adapter;

    UserWardrobeBean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_now_accompanied, null);
        ButterKnife.inject(this, view);
        findID();
        setListener();
        init();
        return view;
    }
    //    逻辑
    private void init() {
        list_now.setFocusable(false);
        adapter=new NowAccompaniedAdapter(getActivity(),null);
        list_now.setAdapter(adapter);
        HttpServiceClient.getInstance().user_wardrobe(MyApplication.token,"1",null,null).enqueue(new Callback<UserWardrobeBean>() {
            @Override
            public void onResponse(Call<UserWardrobeBean> call, Response<UserWardrobeBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    adapter.updata(response.body().getData().getRows());
                }
            }

            @Override
            public void onFailure(Call<UserWardrobeBean> call, Throwable t) {

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
    private void findID() {
        list_now= (ListView) view.findViewById(R.id.list_now);
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
    }
}
