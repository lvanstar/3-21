package com.qiansong.msparis.app.wardrobe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.app.commom.bean.PackageAddBean;
import com.qiansong.msparis.app.commom.bean.PackageSizeBean;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayout;
import com.qiansong.msparis.app.commom.util.AccountUtil;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.DialogUtil;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.homepage.activity.BrandDetailsActivity;
import com.qiansong.msparis.app.wardrobe.adapter.TagsAdapter;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;
import com.qiansong.msparis.app.wardrobe.selfview.CustomListView;
import com.qiansong.msparis.app.wardrobe.selfview.ShoppingBagView2;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.CityBean;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.bean.ProductsBean;
import com.qiansong.msparis.app.commom.selfview.observableScrollViewo.ObservableScrollView;
import com.qiansong.msparis.app.commom.selfview.observableScrollViewo.OnScrollChangedCallback;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.ShareUtil;
import com.qiansong.msparis.app.fulldress.activity.TagInfoActivity;
import com.qiansong.msparis.app.fulldress.view.HorizontalListView;
import com.qiansong.msparis.app.homepage.view.MSParisCarouselView;
import com.qiansong.msparis.app.wardrobe.adapter.CommentsAdapter;
import com.qiansong.msparis.app.wardrobe.adapter.ProductOrAdapter;
import com.qiansong.msparis.app.wardrobe.selfview.NumberProgressBar;
import com.qiansong.msparis.app.wardrobe.selfview.ShoppingBagView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kevin on 2017/2/8.
 * <p>
 * 商品详情
 */
public class ProductDetailsActivity extends BaseActivity {


    /**
     * 商品名称
     **/
    @InjectView(R.id.pro_tv_productName)
    TextView proTvProductName;
    /**
     * 商品SKU
     **/
    @InjectView(R.id.pro_tv_productSku)
    TextView proTvProductSku;
    /**
     * 尺码
     */
    @InjectView(R.id.pro_hs_size)
    RecyclerView proHsSize;
    /**
     * 尺码边上的问号
     **/
    @InjectView(R.id.pro_iv_prompt)
    ImageView proIvPrompt;
    /**
     * 添加到购物车
     **/
    @InjectView(R.id.pro_tv_addCart)
    TextView proTvAddCart;
    /**
     * 商品的价格
     **/
    @InjectView(R.id.pro_tv_price)
    TextView proTvPrice;
    /**
     * 评价
     **/
    @InjectView(R.id.pro_tv_appraisal)
    TextView proTvAppraisal;
    /**
     * 衣服类型
     */
    @InjectView(R.id.product_details_carouse)
    MSParisCarouselView productDetailsCarouse;
    @InjectView(R.id.product_comments_lv)
    CustomListView productCommentsLv;
    @InjectView(R.id.product_collect_iv)
    ImageView productCollectIv;
    @InjectView(R.id.product_ll)
    LinearLayout line;
    @InjectView(R.id.product_more_tv)
    TextView more_tv;


    @InjectView(R.id.pro_iv_share)
    ImageView share;
    @InjectView(R.id.pro_hs_sv)
    ObservableScrollView scrollView;

    @InjectView(R.id.pro_zan)
    ImageView collection;
    @InjectView(R.id.pro_xianding_Tv)
    TextView xiandingTv;
    @InjectView(R.id.pro_vip_Tv)
    ImageView proVipIv;
    @InjectView(R.id.pro_tv_brand)
    TextView brandTv;
    @InjectView(R.id.pro_tv_size)
    TextView sizeTv;
    @InjectView(R.id.product_Nocomments)
    LinearLayout Nocomments;
    @InjectView(R.id.pro_Tags_recycler)
    HorizontalListView tagsRecycler;// tags
    @InjectView(R.id.pro_tv_brand_Ll)
    View brandLl; //品牌跳转项
    @InjectView(R.id.pro_hs_progressBar01)
    NumberProgressBar numberProgressBar01;
    @InjectView(R.id.pro_hs_progressBar02)
    NumberProgressBar numberProgressBar02;
    @InjectView(R.id.pro_hs_progressBar03)
    NumberProgressBar numberProgressBar03;
    @InjectView(R.id.pullDress_price)
    TextView priceTv;
    @InjectView(R.id.product_details_pinlun_ll)
    View pinglun;
    @InjectView(R.id.product_pinglunLl)
    View pinglunLl;

    private ProductOrAdapter productOrAdapter;
    private TagsAdapter tagsAdapter;// tags
    public static List<ProductsBean.DataEntity.SpecificationsEntity> sizeBeanList;
    private CommentsAdapter commentsAdapter;//评论adapter
    private ProductDetailsActivity INSTANCE;
    List<String> urls;
    private ShoppingBagView bagView;//购物袋弹出视图
    private CalendarView calendarView;//日历弹出视图
    private String [] values;
    private ProductsBean.DataEntity dataEntity;//数据详情对象
    private List<PackagesBean.DataBean.UserPackageBean>datas;//购物袋列表数据
    private boolean STATUS=true;//判断是否第一次滑动
    private boolean isSelect;//判断尺寸是否被选中
    public static String region="";//送达城市
    public int postion;//表示购物袋下标
    private Timer timer01,timer02,timer03;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE = this;
        values=getIntent().getStringArrayExtra(GlobalConsts.INIT_DATA);



    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 加载页面
     */
    public void initView() {
        //设置轮播数据
        urls = new ArrayList<String>();
        if(dataEntity.getImage().size()>0){
            urls=dataEntity.getImage();
        }
        productDetailsCarouse.setData(dataEntity.getImage());
        productDetailsCarouse.startTurning(3000);

        //设置尺寸
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(INSTANCE);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        proHsSize.setLayoutManager(linearLayoutManager);
        productOrAdapter = new ProductOrAdapter(INSTANCE,sizeBeanList);
        proHsSize.setAdapter(productOrAdapter);

        //设置tags
        tagsAdapter=new TagsAdapter(INSTANCE,dataEntity.getTags());

        tagsRecycler.setAdapter(tagsAdapter);

        //商品名
        proTvProductName.setText(dataEntity.getName());
        //商品sku
        proTvProductSku.setText(dataEntity.getSpu());
        //商品品牌名称
        brandTv.setText(dataEntity.getBrand());
        //市场价
        proTvPrice.setText("¥"+dataEntity.getMarket_price());
        sizeTv.setText(dataEntity.getFabric()+"  "+dataEntity.getThickness());
        //收藏状态
        collection.setImageResource(dataEntity.getIs_favorite()==0?R.mipmap.icon_no:R.mipmap.icon_yes);
        xiandingTv.setText(dataEntity.getLimit_tag());
        proVipIv.setVisibility(dataEntity.getType_id()==1?View.GONE:View.VISIBLE);
        xiandingTv.setVisibility("".equals(dataEntity.getLimit_tag())?View.GONE:View.VISIBLE);


        priceTv.setText(("1".equals(values[0])?"2积点":dataEntity.getRental_price()/100+"/天"));
        priceTv.setTextColor("1".equals(values[0])?getResources().getColor(R.color.violet):getResources().getColor(R.color.font19));



    }


    private void setProgressBar(){
        numberProgressBar01.setProgress(0,90);
        timer01= new Timer();
        timer01.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numberProgressBar01.incrementProgressBy(1);
                    }
                });
            }
        },10,20);

        numberProgressBar02.setProgress(0,94);
        timer02= new Timer();
        timer02.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numberProgressBar02.incrementProgressBy(1);
                    }
                });
            }
        },10,20);

        numberProgressBar03.setProgress(0,98);
        timer03= new Timer();
        timer03.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numberProgressBar03.incrementProgressBy(1);
                    }
                });
            }
        },10,20);
    }

    /**
     * 填充数据
     */
    public void initData() {

        HttpServiceClient.getInstance().product("",values[0]).enqueue(new Callback<ProductsBean>() {
            @Override
            public void onResponse(Call<ProductsBean> call, Response<ProductsBean> response) {

                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){
                        dataEntity=response.body().getData();
                        sizeBeanList=dataEntity.getSpecifications();
                        initPackageSize();
                        initView();
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



    /**
     * 监听事件
     */
    private void setListeners() {

//        proHsSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                if (!sizeBeanList.get(0).getOptions().get(position).isOut) {
//                    if (sizeBeanList.get(0).getOptions().get(position).select) {
//                        sizeBeanList.get(0).getOptions().get(position).select= false;
//                    } else {
//                        sizeBeanList.get(0).getOptions().get(position).select = true;
//                    }
//                    sizeAdapter.updata(sizeBeanList);
////                }
//
//                sizeAdapter.updata(sizeBeanList);
//            }
//        });

        /**
         *  tags 点击响应事件
         */
        tagsRecycler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(INSTANCE, TagInfoActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,dataEntity.getTags().get(position).getId());
                startActivity(intent);
            }
        });

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        productCollectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AccountUtil.showLoginView(INSTANCE))return;
                Intent intent=new Intent(INSTANCE,ShoppingBagActivity.class);
                intent.putExtra("ShoppingBagActivity",values[1]+"");
                startActivityForResult(intent,31);
            }
        });

        //分享
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.share(INSTANCE,line);
            }
        });

        /**
         * 加入购物车
         */
        proTvAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 判断是否有被选中
                 */

                for (int i=0;i<sizeBeanList.size();i++){
                    for (int j=0;j<sizeBeanList.get(i).getOptions().size();j++){
                        if(sizeBeanList.get(i).getOptions().get(j).select){
                            isSelect=true;
                        }
                    }
                }

                if(isSelect){
                if(AccountUtil.showLoginView(INSTANCE)) return;
                    HttpServiceClient.getInstance().packages("token").enqueue(new Callback<PackagesBean>() {
                        @Override
                        public void onResponse(Call<PackagesBean> call, Response<PackagesBean> response) {

                            if(response.isSuccessful()){
                                if("ok".equals(response.body().getStatus())){
                                    datas=response.body().getData().getUser_package();
                                    //购物袋列表视图
                                    bagView=new ShoppingBagView(INSTANCE,datas,new ShoppingBagView.OnClickListener() {
                                        @Override
                                        public void OnClick(final int postion) {
                                            INSTANCE.postion=postion;
                                            getTime(datas.get(postion).getPackage_id(),MyApplication.region_code,1);

                                        }
                                    });
                                    bagView.show(line);

                                }else {
                                    ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PackagesBean> call, Throwable t) {

                        }
                    });
                }else {
                    ToastUtil.showAnimToast("请选中尺码");
                }

            }
        });

        //轮播监听
        productDetailsCarouse.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                //大图显示
                ExclusiveUtils.toShowBigImages(INSTANCE, (ArrayList<String>) urls,position);
            }
        });

        //更多评论
        more_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(INSTANCE,CommentsListActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,values[0]);
                startActivity(intent);
            }
        });


        //跳转标签筛选详情
        proIvPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INSTANCE,SizeActivity.class));
            }
        });

        //监听pop消失监听
//        bagView2.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                bagView.dismiss();
//            }
//        });

        //收藏与取消收藏
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpServiceClient.getInstance().wish(MyApplication.token,values[0]).enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {

                        if(response.isSuccessful()){
                            if("ok".equals(response.body().getStatus())){
                                ToastUtil.showAnimToast(dataEntity.getIs_favorite()==0?"收藏成功":"取消收藏");
                                collection.setImageResource(dataEntity.getIs_favorite()==0?R.mipmap.icon_yes:R.mipmap.icon_no);
                                dataEntity.setIs_favorite(dataEntity.getIs_favorite()==0?1:0);

                            }else {
                                ToastUtil.showAnimToast(response.body().getError().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                        ToastUtil.showAnimToast("网络错误");
                    }
                });
            }
        });

        /**
         * 跳转品牌详情
         */
        brandLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(INSTANCE, BrandDetailsActivity.class);
                intent.putExtra(GlobalConsts.INIT_DATA,dataEntity.getBrand_id());
                startActivity(intent);
            }
        });

        //滑动监听
        scrollView.setOnScrollChangedCallback(new OnScrollChangedCallback() {
            @Override
            public void onScroll(int l, int t) {

                /**
                 * 获取评论数
                 */
                if(STATUS){
                    STATUS=false;
                    HttpServiceClient.getInstance().product_comments(MyApplication.token,values[0],1,"10").enqueue(new Callback<CommentsBean>() {
                        @Override
                        public void onResponse(Call<CommentsBean> call, Response<CommentsBean> response) {
                            if(response.isSuccessful()){
                                if("ok".equals(response.body().getStatus())){

                                    if(response.body().getData().getTotal()>10){
                                        more_tv.setVisibility(View.VISIBLE);
                                    }else {
                                        more_tv.setVisibility(View.GONE);
                                        if(response.body().getData().getTotal()==0){
                                            Nocomments.setVisibility(View.VISIBLE);
                                            pinglun.setVisibility(View.GONE);
                                            pinglunLl.setVisibility(View.GONE);
                                        }

                                    }
                                    if(response.body().getData().getRows()!=null){
                                        commentsAdapter = new CommentsAdapter(INSTANCE,response.body().getData().getRows());
                                        productCommentsLv.setAdapter(commentsAdapter);
                                        ListUtils.setListViewHeightBasedOnChildrens(productCommentsLv);
                                        setProgressBar();
                                        proTvAppraisal.setText("("+response.body().getData().getTotal()+")");

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


    /**
     *  日历+地址选择视图
     * @param postion
     */
    private void setDateCandler(final int postion,RentalMonitor.DataEntity entity){

        calendarView=new CalendarView(INSTANCE,entity,1);
        calendarView.show(line);

        /**
         * 加入购物车
         */
        CalendarLayout.mConfirmV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.dismiss();
                int [] value=CalendarLayout.mCalendarPageOne.getSelectedRange();
                ContentUtil.makeLog("lzz","zhi:"+value[0]+",-----"+value[1]);
                Map<String,Object>map=new HashMap<>();
                map.put("access_token",MyApplication.token);
                map.put("package_id",datas.get(postion).getPackage_id());
                map.put("product_id",dataEntity.getId());
                map.put("specification_key","id:1");
                map.put("delivery_region",region);
                map.put("start_date",value[0]);
                map.put("end_date",value[1]);
                RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
                HttpServiceClient.getInstance().packageAdd(body).enqueue(new Callback<PackageAddBean>() {
                    @Override
                    public void onResponse(Call<PackageAddBean> call, Response<PackageAddBean> response) {
                        if(response.isSuccessful()){

                            if("ok".equals(response.body().getStatus())){
                                int size=response.body().getData().getItem_count();
                                datas.get(postion).setItems_count(size+"");
                                ShoppingBagView.myShoppingBagAdapter.update(datas);
                            }else {
                                ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PackageAddBean> call, Throwable t) {

                    }
                });
            }
        });

//        bagView2=new ShoppingBagView2(INSTANCE,entity, new ShoppingBagView2.OnClickListener() {
//            @Override
//            public void onAddress() {
//                Intent intent=new Intent(INSTANCE,LocationActivity.class);
//                startActivityForResult(intent,66);
//            }
//
//            @Override
//            public void onDateOk(final Date date) {
//                Map<String,Object>map=new HashMap<>();
//                map.put("access_token",MyApplication.token);
//                map.put("package_id",datas.get(postion).getPackage_id());
//                map.put("product_id",dataEntity.getId());
//                map.put("specification_key","id:1");
//                map.put("delivery_region",region);
//                map.put("start_date",110);
//                map.put("end_date",110);
//                RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
//                HttpServiceClient.getInstance().packageAdd(body).enqueue(new Callback<PackageAddBean>() {
//                    @Override
//                    public void onResponse(Call<PackageAddBean> call, Response<PackageAddBean> response) {
//                        if(response.isSuccessful()){
//
//                            if("ok".equals(response.body().getStatus())){
//                                int size=response.body().getData().getItem_count();
//                                datas.get(postion).setItems_count(size+"");
//                                ShoppingBagView.myShoppingBagAdapter.update(datas);
//                            }else {
//                                ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PackageAddBean> call, Throwable t) {
//
//                    }
//                });
//            }
//        });

    }


    /**
     * 获取购物袋商品数量
     */
    private void initPackageSize(){

        HttpServiceClient.getInstance().packages_size(MyApplication.token).enqueue(new Callback<PackageSizeBean>() {
            @Override
            public void onResponse(Call<PackageSizeBean> call, Response<PackageSizeBean> response) {
                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){

                        if(0!=response.body().getData().getCart_item_amount()||0!=response.body().getData().getPackage_item_amount()){
                            productCollectIv.setImageResource(R.mipmap.full_gwc);
                        }else {
                            productCollectIv.setImageResource(R.mipmap.full_gwc1);
                        }
                    }else {
                        ContentUtil.makeLog("lzz",response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<PackageSizeBean> call, Throwable t) {

            }
        });
    }


    /**
     * 获取商品可用周期
     *
     *  type=1 表示初始化 type=2表示后期选择城市更换日期选项
     */

    private void getTime(String packageId, String region_code, final int type){

        HttpServiceClient.getInstance().package_scheuule(MyApplication.token,packageId,region_code).enqueue(new Callback<RentalMonitor>() {
            @Override
            public void onResponse(Call<RentalMonitor> call, Response<RentalMonitor> response) {

                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){
                        RentalMonitor.DataEntity entity= response.body().getData();
                        if(1==type){
                            setDateCandler(postion,entity);
                        }else {

                        }

                    }else {
                        ToastUtil.showAnimToast(response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RentalMonitor> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==2){
            if(requestCode==66){
                CityBean bean= (CityBean) data.getSerializableExtra(GlobalConsts.INIT_DATA);
                INSTANCE.region=bean.getKey();

                /**
                 * 获取商品可用周期
                 */
                getTime(datas.get(postion).getPackage_id(),bean.getKey(),2);

            }else if(requestCode==31){
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer01!=null&&timer02!=null&&timer03!=null){
            timer01.cancel();
            timer02.cancel();
            timer03.cancel();
        }
    }


}
