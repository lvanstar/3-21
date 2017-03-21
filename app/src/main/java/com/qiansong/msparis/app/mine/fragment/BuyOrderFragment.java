package com.qiansong.msparis.app.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.OrderListBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.mine.adapter.BuyOrderAdapter;
import com.qiansong.msparis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by caolei on 2017/2/21.
 * <p>
 * 购买订单
 */

public class BuyOrderFragment extends BaseFragment {

    @InjectView(R.id.buy_list)
    XRecyclerView leaseOrderLv;
    private View view;
    private BuyOrderAdapter adapter;
    private static int SIZE=10;
    private static int PAGE=1;
    private List<OrderListBean.DataEntity.RowsEntity>datas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_buy_card, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViews();
        setListeners();
    }


    private void setViews(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leaseOrderLv.setLayoutManager(linearLayoutManager);
        leaseOrderLv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        leaseOrderLv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        leaseOrderLv.setArrowImageView(R.mipmap.iconfont_downgrey);
        datas=new ArrayList<>();
        adapter=new BuyOrderAdapter(getActivity(),datas);
        leaseOrderLv.setAdapter(adapter);
    }


    private void setListeners(){


        leaseOrderLv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE=1;
                initData(1,1,PAGE,SIZE);
                leaseOrderLv.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                PAGE++;
                initData(1,2,PAGE,SIZE);
            }
        });
    }



    /**
     * 获取网络数据
     * @param type
     * @param page
     * @param page_size
     */
    private void initData(int type,final int TYPE,int page,int page_size){
        dialog.show();
        HttpServiceClient.getInstance().user_orders(MyApplication.token,type,page,page_size).enqueue(new Callback<OrderListBean>() {
            @Override
            public void onResponse(Call<OrderListBean> call, Response<OrderListBean> response) {
                dialog.cancel();
                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){
                        OrderListBean.DataEntity entity =response.body().getData();
                        if (1 == TYPE) {
                            datas=new ArrayList<>();
                            mergeData(entity.getRows());
                        } else {
                            if (entity.getRows().size()!=0) {
                                mergeData(entity.getRows());
                                leaseOrderLv.loadMoreComplete();
                            } else {
                                leaseOrderLv.loadMoreComplete();
                                leaseOrderLv.setLoadingMoreEnabled(false);
                            }
                        }
                    }else {
                        ContentUtil.makeToast(getActivity(),response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderListBean> call, Throwable t) {

            }
        });

    }


    /**
     * 数据合并
     * @param data
     */
    private void mergeData(List<OrderListBean.DataEntity.RowsEntity>data){

        if(data!=null){

            datas.addAll(data);
            adapter.update(datas);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(1,1,PAGE,SIZE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
