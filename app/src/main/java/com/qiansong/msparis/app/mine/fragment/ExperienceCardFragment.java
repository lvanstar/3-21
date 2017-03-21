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
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.DepositAdapter;
import com.qiansong.msparis.app.mine.bean.DepositBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 2017/2/16.
 * <p>
 *  我的押金 —— 体验卡
 */

public class ExperienceCardFragment extends BaseFragment {

    @InjectView(R.id.deposit_list)
    XRecyclerView depositList;
    private View view;
    private DepositAdapter adapter;
    //用户tonken
    private String token="";
    private DepositBean bean = null ;
    private int page = 1;
    private int page_size= 10;
    //请求获得的总数
    private int total= 0;
    //全部数据
    private int wholeTotal= 0;
    List<DepositBean.DataBean.RowsBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_experience_card, null);
        ButterKnife.inject(this, view);
        initView();
        requestData();
        return view;
    }

    /**
     * 加载页面
     */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        depositList.setLayoutManager(linearLayoutManager);
        depositList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        depositList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        adapter = new DepositAdapter(getActivity());
        depositList.setAdapter(adapter);

        depositList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                page_size=10;
                requestData();
                depositList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                if(total < wholeTotal){
                    page+=1;
                    requestData();
                }else{
                    ToastUtil.showMessage("没有更多了",0);
                    depositList.loadMoreComplete();
                }
            }
        });


    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取收货地址
        HttpServiceClient.getInstance().mydeposit(token ,"2",page+"" ,page_size+"")
                .enqueue(new Callback<DepositBean>() {
                    @Override
                    public void onResponse(Call<DepositBean> call, Response<DepositBean> response) {
                        dialog.cancel();
                        bean = response.body();
                        if(bean != null ){
                            if ("ok".equals(bean.getStatus())) {
                                setViewData();
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DepositBean> call, Throwable t) {

                    }
                });


    }
    /**
     * 设置会员的信息
     **/
    public void setViewData() {
        wholeTotal = Integer.parseInt(bean.getData().getTotal());
        total +=bean.getData().getRows().size();
        if (page == 1) {
            list = bean.getData().getRows();
            adapter.setData(list);
            depositList.setLoadingMoreEnabled(true);
        } else {
            list.addAll(bean.getData().getRows());
            adapter.setData(list);
            if (list.size() == Integer.parseInt(bean.getData().getTotal())) {
                depositList.setLoadingMoreEnabled(false);
            }

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
