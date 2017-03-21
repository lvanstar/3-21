package com.qiansong.msparis.app.homepage.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.adapter.ProductBrandAdapter;
import com.qiansong.msparis.app.commom.bean.BrandDetailsBean;
import com.qiansong.msparis.app.commom.bean.ProductBrandBean;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.MSParisCarouselView;
import com.qiansong.msparis.app.homepage.view.PullToRefreshLunView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//品牌详情
public class BrandDetailsActivity extends BaseActivity {
    BrandDetailsActivity context;
    XRecyclerView sku_list;
    private List<ProductBean.DataEntity.RowsEntity> datas;
    private ProductBrandAdapter adapter;
    MSParisCarouselView carouselView;//轮播

    PullToRefreshLunView refresh;
    BrandDetailsBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_details);
        context=this;
        setTitleBar();findID();init();setListener();
    }

    private void setListener() {
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
        refresh.setFooter(true);
        refresh.setOnFooterRefreshListener(new PullToRefreshLunView.OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(PullToRefreshLunView view) {
                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onFooterRefreshComplete();
                    }
                }, 1000);
            }
        });
    }
    View head;
    private void init_data(){
        head=View.inflate(this,R.layout.carousel_top,null);
        carouselView = (MSParisCarouselView)head.findViewById(R.id.carouselView);


        List<String> data=new ArrayList<>();
        for (int i=0;i<bean.getData().getBanner().size();i++){
            data.add(bean.getData().getBanner().get(i).get(0));
        }
        carouselView.setData(data);
        carouselView.startTurning(3000);

        titleBar.setTitle(bean.getData().getName());
        list();
    }
    //逻辑
    private void init() {
        dialog.show();
        HttpServiceClient.getInstance().brand(Integer.valueOf(getIntent().getStringExtra("id"))).enqueue(new Callback<BrandDetailsBean>() {
            @Override
            public void onResponse(Call<BrandDetailsBean> call, Response<BrandDetailsBean> response) {
                if (response.isSuccessful()&&response.body().getStatus().equals("ok")){
                    bean=response.body();
                    init_data();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<BrandDetailsBean> call, Throwable t) {

            }
        });


    }
    //list
    private void list() {
      HttpServiceClient.getInstance().product_brand("abc",0,"","100",0,100).enqueue(new Callback<ProductBrandBean>() {
          @Override
          public void onResponse(Call<ProductBrandBean> call, Response<ProductBrandBean> response) {
              if (!response.isSuccessful()||!"ok".equals(response.body().getStatus()))return;
              adapter = new ProductBrandAdapter(context, response.body().getData().getRows());
              sku_list.addHeaderView(head);
              sku_list.setAdapter(adapter);
          }

          @Override
          public void onFailure(Call<ProductBrandBean> call, Throwable t) {

          }
      });

    }

    //id
    private void findID() {
        refresh= (PullToRefreshLunView) findViewById(R.id.refresh);
        sku_list = (XRecyclerView) findViewById(R.id.sku_list);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 2);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sku_list.setLayoutManager(linearLayoutManager);
        sku_list.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        sku_list.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        sku_list.setArrowImageView(R.mipmap.ic_launcher);
        sku_list.setPullRefreshEnabled(true);
    }

    //设置标题栏
    ETitleBar titleBar;
    private void setTitleBar() {
        titleBar= (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("Kata spade");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#151515"));
        titleBar.setRightTitle("订阅");
    }
}
