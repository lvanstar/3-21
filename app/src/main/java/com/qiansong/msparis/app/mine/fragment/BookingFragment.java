package com.qiansong.msparis.app.mine.fragment;

import android.os.Bundle;
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
import com.qiansong.msparis.app.mine.adapter.BookingAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/20.
 * 我的衣橱-预定中
 */

public class BookingFragment extends BaseFragment {
    private View view;
    private ListView list_booking;
    private PullToRefreshView refresh;

    BookingAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_booking,null);
        setID();setListener();init();
        return view;
    }
    //填充
    private void init() {
        adapter=new BookingAdapter(getActivity(),null);
        list_booking.setAdapter(adapter);
        list_booking.setFocusable(false);
        HttpServiceClient.getInstance().user_wardrobe(MyApplication.token,"2",null,null).enqueue(new Callback<UserWardrobeBean>() {
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
    private void setID() {
        list_booking= (ListView) view.findViewById(R.id.list_booking);
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
    }
}
