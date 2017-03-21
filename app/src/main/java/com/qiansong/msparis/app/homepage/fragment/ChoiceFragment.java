package com.qiansong.msparis.app.homepage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.activity.WebViewActivity;
import com.qiansong.msparis.app.commom.bean.HomePageBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.homepage.activity.BrandDetailsActivity;
import com.qiansong.msparis.app.homepage.adapter.DayNewAdapter;
import com.qiansong.msparis.app.homepage.adapter.NewThemeAdapter;
import com.qiansong.msparis.app.homepage.adapter.RecommendAdapter;
import com.qiansong.msparis.app.homepage.adapter.RookieAdapter;
import com.qiansong.msparis.app.homepage.view.MSParisCarouselView;
import com.qiansong.msparis.app.homepage.view.PullToRefreshLunView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/10.
 * 首页精选
 */

public class ChoiceFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    MSParisCarouselView carouselView;//轮播
    private RecyclerView daynew_list,recommend_list,rookie_list;//每日上新/小编推荐
    ListView theme;//本期主题
    PullToRefreshLunView refresh;
    ScrollView scroll;

    Handler handler;

    HomePageBean bean;

    TextView feature_1,feature_2,feature_3,feature_4;//服装特点

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.frgment_choice_homepage, null);
        ButterKnife.inject(this, view);
        findID();
        setListener();
        init();
        return view;
    }
    private void init_data(){
        List<String> data=new ArrayList<>();
        for (int i=0;i<bean.getData().getBanner().size();i++) {
            data.add(bean.getData().getBanner().get(i).getImage_src2x());
        }
        carouselView.setData(data);
        carouselView.startTurning(3000);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        daynew_list.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommend_list.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rookie_list.setLayoutManager(linearLayoutManager2);
        //设置适配器
        DayNewAdapter mAdapter = new DayNewAdapter(getActivity(), bean.getData().getNew_arrivals());
        daynew_list.setAdapter(mAdapter);
        RecommendAdapter rAdapter = new RecommendAdapter(getActivity(), bean.getData().getProduct_commend());
        recommend_list.setAdapter(rAdapter);
        RookieAdapter iAdapter = new RookieAdapter(getActivity(),bean.getData().getBanner_rookie());
        rookie_list.setAdapter(iAdapter);

        theme.setAdapter(new NewThemeAdapter(getActivity(),bean.getData().getTopics()));
        ListUtils.setListViewHeightBasedOnChildren(theme);

        theme.setFocusable(false);
        daynew_list.setFocusable(false);
        rookie_list.setFocusable(false);
        feature_1.setFocusable(false);
        feature_2.setFocusable(false);
        feature_3.setFocusable(false);
        feature_4.setFocusable(false);
        if (bean.getData().getFeature().size()>0)
        feature_1.setText(bean.getData().getFeature().get(0).getName());
        if (bean.getData().getFeature().size()>1)
        feature_2.setText(bean.getData().getFeature().get(1).getName());
        if (bean.getData().getFeature().size()>2)
        feature_3.setText(bean.getData().getFeature().get(2).getName());
        if (bean.getData().getFeature().size()>3)
        feature_4.setText(bean.getData().getFeature().get(3).getName());

        scroll= (ScrollView) view.findViewById(R.id.scroll);
        handler =new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                scroll.scrollTo(0,message.what);
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(0,50);
    }
    //逻辑
    private void init() {
//        dialog.show();
        HttpServiceClient.getInstance().home("abc").enqueue(new Callback<HomePageBean>() {
            @Override
            public void onResponse(Call<HomePageBean> call, Response<HomePageBean> response) {
                if (response.isSuccessful()&&response.body().getStatus().equals("ok")){
//                    ContentUtil.makeLog("yc","成功");
//                    dialog.dismiss();
                    bean=response.body();
                    init_data();
                    TXShareFileUtil.getInstance().putString("ChoiceFragment",new Gson().toJson(bean));
                    return;
                }
                bean=new Gson().fromJson(TXShareFileUtil.getInstance().getString("ChoiceFragment",""),HomePageBean.class);
                if (bean!=null)
                init_data();
            }

            @Override
            public void onFailure(Call<HomePageBean> call, Throwable t) {
                bean=new Gson().fromJson(TXShareFileUtil.getInstance().getString("ChoiceFragment",""),HomePageBean.class);
                if (bean!=null)
                init_data();
            }
        });

    }
    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.feature_1:feature_1(view);break;
          case R.id.feature_2:feature_2(view);break;
          case R.id.feature_3:feature_3(view);break;
          case R.id.feature_4:feature_4(view);break;
      }
    }
    /**
     * 四个服装特色
     * @param view
     */
    public void feature_1(View view){
        startActivity(new Intent(getActivity(), WebViewActivity.class));
    }
    public void feature_2(View view){
        startActivity(new Intent(getActivity(), WebViewActivity.class));
    }
    public void feature_3(View view){
        startActivity(new Intent(getActivity(), WebViewActivity.class));
    }
    public void feature_4(View view){
        startActivity(new Intent(getActivity(), WebViewActivity.class));
    }
    //监听
    private void setListener() {
        feature_1.setOnClickListener(this);
        feature_2.setOnClickListener(this);
        feature_3.setOnClickListener(this);
        feature_4.setOnClickListener(this);
        theme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(),BrandDetailsActivity.class));
            }
        });
//        daynew_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(getActivity(),ProductDetailsActivity.class));
//            }
//        });
        carouselView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ContentUtil.makeToast(getActivity(), "点击了第" + (position + 1) + "个");
            }
        });
        refresh.setOnHeaderRefreshListener(new PullToRefreshLunView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshLunView view) {

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
        carouselView = (MSParisCarouselView) view.findViewById(R.id.carouselView);
        daynew_list= (RecyclerView) view.findViewById(R.id.daynew_list);
        recommend_list= (RecyclerView) view.findViewById(R.id.recommend_list);
        rookie_list= (RecyclerView) view.findViewById(R.id.rookie_list);
        theme= (ListView) view.findViewById(R.id.theme);
        refresh= (PullToRefreshLunView) view.findViewById(R.id.refresh);
        feature_1= (TextView) view.findViewById(R.id.feature_1);
        feature_2= (TextView) view.findViewById(R.id.feature_2);
        feature_3= (TextView) view.findViewById(R.id.feature_3);
        feature_4= (TextView) view.findViewById(R.id.feature_4);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
