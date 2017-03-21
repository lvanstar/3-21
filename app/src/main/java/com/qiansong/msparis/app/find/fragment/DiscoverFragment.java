package com.qiansong.msparis.app.find.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.FindListBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.find.adapter.FindAdapter;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/8.
 *
 * 发现
 */

public class DiscoverFragment extends BaseFragment {

    private View view;
    private FindAdapter adapter;
    private static int SIZE=10;
    private static int PAGE=1;
    private static int type=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_discover,null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViews();
        setListeners();
    }


    private void setViews(){

    }

    private void setListeners(){

    }

    private void initData(int PAGE,int SIZE){

        dialog.show();
        HttpServiceClient.getInstance().findList(MyApplication.token,type,PAGE,SIZE).enqueue(new Callback<FindListBean>() {
            @Override
            public void onResponse(Call<FindListBean> call, Response<FindListBean> response) {
                dialog.cancel();
                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){

                    }else {
                        ContentUtil.makeToast(getActivity(),response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<FindListBean> call, Throwable t) {

                dialog.cancel();
            }
        });
    }
}
