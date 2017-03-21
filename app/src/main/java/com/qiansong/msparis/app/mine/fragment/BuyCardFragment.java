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
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.mine.adapter.BuyCardAdapter;
import com.qiansong.msparis.app.mine.bean.BuyCardBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 2017/2/16.
 * <p>
 *  可以购买的女神卡列表
 */

public class BuyCardFragment extends BaseFragment {

    @InjectView(R.id.buy_list)
    XRecyclerView buyList;
    private View view;
    private BuyCardAdapter adapter;
    //用户tonken
    private String token="";
    private BuyCardBean bean = null ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_buy_card, null);
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
        buyList.setLayoutManager(linearLayoutManager);
        buyList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        buyList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        buyList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData();
            }

            @Override
            public void onLoadMore() {

            }
        });

        adapter = new BuyCardAdapter(getActivity());
        buyList.setAdapter(adapter);

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            HttpServiceClient.getInstance().card(token)
                    .enqueue(new Callback<BuyCardBean>() {
                        @Override
                        public void onResponse(Call<BuyCardBean> call, Response<BuyCardBean> response) {
                            buyList.refreshComplete();
                            dialog.cancel();
                            if (response.isSuccessful()) {
                                bean = response.body();
                                if ("ok".equals(bean.getStatus())) {
                                    setViewData();
                                }else{
                                    ToastUtil.showMessage(bean.getError().getMessage());
                                }
                            }else{
                                ToastUtil.showMessage("网络异常");
                            }
                        }
                        @Override
                        public void onFailure(Call<BuyCardBean> call, Throwable t) {

                        }
                    });
        }

    }
    /**
     * 设置会员的信息
     **/
    public void setViewData() {
        adapter.setData(bean.getData().getRows());
    }

    /**
     * 填充数据
     */
    public void initData() {
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
