package com.qiansong.msparis.app.wardrobe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.ShoppingCartBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.fulldress.activity.MakeOrderFulldressActivity;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.wardrobe.adapter.ShoppingCarAdapter;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/15.
 * 购物袋-礼服
 */

public class ShoppingBagFulldressFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    ListView shop_list;
    ShoppingCarAdapter adapter;
    PullToRefreshView refresh;
    ShoppingCartBean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_car_fulldress, null);
        ButterKnife.inject(this, view);
        init();

        return view;
    }

    private void setListener() {
        view.findViewById(R.id.to_make_order).setOnClickListener(this);
        shop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.to_make_order:
                to_make_order();
                break;
        }
    }
    private void to_make_order(){
        int select_num=0;
        for (int i=0;i<bean.getData().size();i++){
            for (int k=0;k<bean.getData().get(i).getItems().size();k++){
                if (bean.getData().get(i).getItems().get(k).isSelect){
                    select_num++;
                }
            }
        }
        if (select_num==0){
            ContentUtil.makeToast(getActivity(),"请至少选择一件衣服");
            return;
        }
        Intent intent=new Intent(getActivity(),MakeOrderFulldressActivity.class);
        intent.putExtra(MakeOrderFulldressActivity.intent_key,new Gson().toJson(bean));
        startActivity(intent);
    }
    private void init_dtata(){
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
        shop_list = (ListView) view.findViewById(R.id.shop_list);
        adapter = new ShoppingCarAdapter(getActivity(), bean.getData());
        shop_list.setAdapter(adapter);
    }
    private void init() {
        HttpServiceClient.getInstance().cart_dress(MyApplication.token).enqueue(new Callback<ShoppingCartBean>() {
            @Override
            public void onResponse(Call<ShoppingCartBean> call, Response<ShoppingCartBean> response) {
                if (!response.isSuccessful()||!response.body().getStatus().equals("ok"))return;
                bean=response.body();
                init_dtata();
                setListener();

            }

            @Override
            public void onFailure(Call<ShoppingCartBean> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
