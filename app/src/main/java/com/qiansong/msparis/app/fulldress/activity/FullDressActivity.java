package com.qiansong.msparis.app.fulldress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.app.commom.bean.ProductsBean;
import com.qiansong.msparis.app.commom.selfview.observableScrollViewo.ObservableScrollView;
import com.qiansong.msparis.app.commom.selfview.observableScrollViewo.OnScrollChangedCallback;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.ShareUtil;
import com.qiansong.msparis.app.fulldress.bean.SizeBean;
import com.qiansong.msparis.app.fulldress.view.HorizontalListView;
import com.qiansong.msparis.app.homepage.view.MSParisCarouselView;
import com.qiansong.msparis.app.wardrobe.activity.CommentsListActivity;
import com.qiansong.msparis.app.wardrobe.activity.ShoppingBagActivity;
import com.qiansong.msparis.app.wardrobe.activity.SizeActivity;
import com.qiansong.msparis.app.wardrobe.adapter.CommentsAdapter;
import com.qiansong.msparis.app.wardrobe.adapter.ProductSizeAdapter;
import com.qiansong.msparis.app.wardrobe.selfview.CustomListView;
import com.qiansong.msparis.app.wardrobe.selfview.ShoppingBagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/14.
 * <p>
 * 礼服详情
 */

public class FullDressActivity extends BaseActivity {


    @InjectView(R.id.fullDress_collect_iv)
    ImageView fullDressCollectIv;
    @InjectView(R.id.fullDress_carouse)
    MSParisCarouselView Carouse;
    @InjectView(R.id.fullDress_iv_share)
    ImageView proIvShare;
    @InjectView(R.id.pullDress_productName)
    TextView pullDressProductName;
    @InjectView(R.id.pullDress_productSku)
    TextView pullDressProductSku;
    @InjectView(R.id.pullDress_productBrand)
    TextView pullDressProductBrand;
    @InjectView(R.id.pullDress_price)
    TextView pullDressPrice;
    @InjectView(R.id.fullDress_size)
    HorizontalListView proHsSize;
    @InjectView(R.id.pro_iv_prompt)
    ImageView proIvPrompt;
    @InjectView(R.id.fullDress_addCart)
    TextView fullDressAddCart;
    @InjectView(R.id.pullDress_tv_price)
    TextView pullDressTvPrice;
    @InjectView(R.id.pullDress_tv_code)
    TextView pullDressTvCode;
    @InjectView(R.id.pullDress_tv_tag)
    TextView pullDressTvTag;
    @InjectView(R.id.pullDress_tv_mianliao)
    TextView pullDressTvMianliao;
    @InjectView(R.id.pullDress_tv_thickness)
    TextView pullDressTvThickness;
    @InjectView(R.id.pro_tv_appraisal)
    TextView proTvAppraisal;
    @InjectView(R.id.fullDress_comments_lv)
    CustomListView fullDressListView;
    @InjectView(R.id.fullDress_more_tv)
    TextView productMoreTv;
    @InjectView(R.id.fullDress_ll)
    LinearLayout line;
    @InjectView(R.id.fullDress_zan)
    ImageView collection;
    @InjectView(R.id.fullDress_sv)
    ObservableScrollView scrollView;


    private FullDressActivity INSTANCE;
    private List<String>urls;
    private List<SizeBean> sizeBeanList;
    private CommentsAdapter commentsAdapter;
    private ProductSizeAdapter sizeAdapter;
    private ShoppingBagView bagView;//购物袋弹出视图
    private String id;
    private ProductsBean.DataEntity dataEntity;//数据详情对象
    private boolean STATUS=true;//判断是否第一次滑动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_dress);
        ButterKnife.inject(this);
        INSTANCE=this;


        setListeners();
        initData();

    }


    private void initData(){


        HttpServiceClient.getInstance().product("",id).enqueue(new Callback<ProductsBean>() {
            @Override
            public void onResponse(Call<ProductsBean> call, Response<ProductsBean> response) {

                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){
                        dataEntity=response.body().getData();
                        setViews();
                        setListeners();
                    }else {
                        ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                    }
                }else {

                    ContentUtil.makeToast(INSTANCE,"加载失败");
                }

            }

            @Override
            public void onFailure(Call<ProductsBean> call, Throwable t) {
                ContentUtil.makeToast(INSTANCE,"网络错误");
            }
        });

    }

    private void setViews(){

//        bagView=new ShoppingBagView(INSTANCE, new ShoppingBagView.OnClickListener() {
//            @Override
//            public void OnFirst() {
//
//            }
//        });



        //设置轮播数据
        urls = new ArrayList<String>();
        if(dataEntity.getImage().size()>0){
            urls=dataEntity.getImage();
        }
        Carouse.setData(dataEntity.getImage());
        Carouse.startTurning(3000);


        //设置尺寸
//        sizeAdapter = new ProductSizeAdapter(INSTANCE, dataEntity.getSpecifications());
//        proHsSize.setAdapter(sizeAdapter);

        //商品名
        pullDressProductName.setText(dataEntity.getName());
        //商品sku
        pullDressProductSku.setText(dataEntity.getSpu());
        //商品品牌名称
        pullDressProductBrand.setText(dataEntity.getBrand());
        //市场价
        pullDressPrice.setText("¥"+dataEntity.getMarket_price());
        //收藏状态
        collection.setImageResource(dataEntity.getIs_favorite()==0?R.mipmap.icon_no:R.mipmap.icon_yes);
    }

    private void setListeners(){


        //退出
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //加入购物车
        fullDressAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bagView.show(line);
            }
        });

        //分享
        proIvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.share(INSTANCE,line);
            }
        });

        //轮播监听
        Carouse.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                //大图显示
                ExclusiveUtils.toShowBigImages(INSTANCE, (ArrayList<String>) urls,position);
            }
        });

        //更多评论
        productMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(INSTANCE,CommentsListActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,id);
                startActivity(intent);
            }
        });

        //标签筛选列表s
        pullDressProductSku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(INSTANCE,TagInfoActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,dataEntity.getSpecifications().get(0).getId());
                startActivity(intent);
            }
        });

        proIvPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INSTANCE,SizeActivity.class));
            }
        });

        //跳转购物车
        fullDressCollectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INSTANCE,ShoppingBagActivity.class));
            }
        });


        //收藏与取消收藏
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpServiceClient.getInstance().wish(MyApplication.token,id).enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

                        if(response.isSuccessful()){
                            if("ok".equals(response.body().getStatus())){
                                ContentUtil.makeToast(INSTANCE,dataEntity.getIs_favorite()==0?"收藏成功":"取消收藏");
                                collection.setImageResource(dataEntity.getIs_favorite()==0?R.mipmap.icon_yes:R.mipmap.icon_no);

                            }else {
                                ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                        ContentUtil.makeToast(INSTANCE,"网络错误");
                    }
                });
            }
        });

        /**
         * 滑动监听
         */
        scrollView.setOnScrollChangedCallback(new OnScrollChangedCallback() {
            @Override
            public void onScroll(int l, int t) {
                /**
                 * 获取评论数
                 */
                if(STATUS){
                    STATUS=false;
                    HttpServiceClient.getInstance().product_comments(MyApplication.token,id,1,"10").enqueue(new Callback<CommentsBean>() {
                        @Override
                        public void onResponse(Call<CommentsBean> call, Response<CommentsBean> response) {
                            if(response.isSuccessful()){
                                if("ok".equals(response.body().getStatus())){
                                    if(response.body().getData().getRows()!=null){
                                        commentsAdapter = new CommentsAdapter(INSTANCE,response.body().getData().getRows());
                                        fullDressListView.setAdapter(commentsAdapter);
                                        ListUtils.setListViewHeightBasedOnChildrens(fullDressListView);

                                        proTvAppraisal.setText("("+response.body().getData().getTotal()+")");
                                        if(response.body().getData().getTotal()>10){
                                            productMoreTv.setVisibility(View.VISIBLE);
                                        }else {
                                            productMoreTv.setVisibility(View.GONE);
                                        }
                                    }

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CommentsBean> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }



}
