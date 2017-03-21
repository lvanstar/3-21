package com.qiansong.msparis.app.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.CardListBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.mine.adapter.VipOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/21.
 * <p>
 * 女神卡订单
 */

public class VipOrderFragment extends BaseFragment {

    @InjectView(R.id.buy_list)
    XRecyclerView leaseOrderLv;
    @InjectView(R.id.buyCard_nothingTv)
    TextView nothingTv;
    private View view;
    private VipOrderAdapter adapter;
    private static int SIZE=10;
    private static int PAGE=1;
    private List<CardListBean.DataEntity.RowsEntity>datas;

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
        adapter=new VipOrderAdapter(getActivity(),datas);
        leaseOrderLv.setAdapter(adapter);
    }


    private void setListeners(){

        leaseOrderLv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE=1;
                initData(1,PAGE,SIZE);
                leaseOrderLv.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                PAGE++;
                initData(2,PAGE,SIZE);
            }
        });
    }

    private void initData(final int TYPE,int PAGE,int SIZE){

        HttpServiceClient.getInstance().cardList(MyApplication.token,PAGE,SIZE).enqueue(new Callback<CardListBean>() {
            @Override
            public void onResponse(Call<CardListBean> call, Response<CardListBean> response) {
                if (response.isSuccessful()){
                    if ("ok".equals(response.body().getStatus())){
                        CardListBean.DataEntity entity=response.body().getData();
                        if (1 == TYPE) {
                            nothingTv.setVisibility("0".equals(entity.getTotal())?View.VISIBLE:View.GONE);
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
            public void onFailure(Call<CardListBean> call, Throwable t) {

            }
        });
    }


    private void mergeData(List<CardListBean.DataEntity.RowsEntity>data){
        if(data!=null){
            datas.addAll(data);
            adapter.mergeData(datas);
        }else {
            adapter.mergeData(datas);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initData(1,PAGE,SIZE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
