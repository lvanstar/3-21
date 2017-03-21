package com.qiansong.msparis.app.fulldress;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.bean.CityBean;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.bean.PackageSizeBean;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayout;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayoutTwo;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.SweetAlertDialog;
import com.qiansong.msparis.app.fulldress.adapter.ColorAdapter;
import com.qiansong.msparis.app.fulldress.adapter.SizeAdapter;
import com.qiansong.msparis.app.fulldress.adapter.TopAdapter;
import com.qiansong.msparis.app.wardrobe.activity.ShoppingBagActivity;
import com.qiansong.msparis.app.wardrobe.fragment.WardRobeFragment;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;
import com.qiansong.msparis.app.wardrobe.selfview.ShoppingBagView2;
import com.qiansong.msparis.app.wardrobe.selfview.TopLeftPopup;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/4.
 * <p>
 * 礼服首页
 */

public class FullDressFragment extends BaseFragment {

    private View view;
    View header;//头
    private LinearLayout top_select;
    private XRecyclerView sku_list;
    private RecyclerView recyclerView;
    private CheckBox[] checkBoxes;

    @InjectView(R.id.fullDress_remmend_Iv)
    ImageView remmendIv;
    @InjectView(R.id.fullDress_calander_Iv)
    ImageView calanderIv;
    @InjectView(R.id.fullDress_addCart_Iv)
    ImageView addcartIv;
    @InjectView(R.id.fullDress_title_Rl)
    View title;
    @InjectView(R.id.fullDress_Ll)
    View line;
    @InjectView(R.id.fullDress_nothingTv)
    TextView nothingTv;

    private List<ProductBean.DataEntity.RowsEntity> datas;
    private FulldressAdapter adapter;
    private SizeAdapter sizeAdapter, materialAdapter;
    private ColorAdapter colorAdapter;
    private static int SIZE=10;
    private static int PAGE=1;
    List<ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity>tagsEntities;
    private TopAdapter topAdapter;
    private ShoppingBagView2 shoppingBagView2;
    private int type=1;
    private String ss=""; //获取筛选的所以数据
    private String sort="";//排序规则
    public static String date="";
    private MyBroadcastReceiver2 myBroadcastReceiver;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_full_dress, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
        init();
    }

    //初始化
    private void initView() {

        sku_list = (XRecyclerView) view.findViewById(R.id.sku_list);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sku_list.setLayoutManager(linearLayoutManager);
        sku_list.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        sku_list.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        sku_list.setArrowImageView(R.mipmap.iconfont_downgrey);
        header = LayoutInflater.from(getActivity()).inflate(R.layout.fulldress_top_layout, (ViewGroup) view.findViewById(android.R.id.content), false);
        recyclerView= (RecyclerView) header.findViewById(R.id.fullDress_top_recycler);
        top_select = (LinearLayout) header.findViewById(R.id.top_select);
        checkBoxes=new CheckBox[]{(CheckBox) header.findViewById(R.id.wardrobe_top_today),(CheckBox) header.findViewById(R.id.wardrobe_top_tomorrow),(CheckBox) header.findViewById(R.id.wardrobe_top_more)};


        /**
         * 注册广播
         */
        myBroadcastReceiver=new MyBroadcastReceiver2();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(GlobalConsts.FILE_DATAS);
        getActivity().registerReceiver(myBroadcastReceiver,intentFilter);
    }

    //逻辑
    private void init() {

        //获取后天的日期
        checkBoxes[1].setText("后天"+ DateUtil.getTommorDay(DateUtil.LOOKING_FORMAT));
        //获取明天的日期
        checkBoxes[0].setText("明天"+DateUtil.getToDay(DateUtil.LOOKING_FORMAT));
        datas = new ArrayList<>();
        adapter = new FulldressAdapter(getActivity(), datas);
        sku_list.setAdapter(adapter);

        //获取初始数据
        ConfigsBean.DataEntity.ProductFilterEntity entity= MyApplication.getProductEntity();

        //获取排序集合
        ConfigsBean.DataEntity.FilterPanelEntity filterPanelEntity=MyApplication.getFilterEntity();

        tagsEntities=new ArrayList<>();
        for (int i=0;i<filterPanelEntity.getDress_panel().size();i++){
            if("s".equals(filterPanelEntity.getDress_panel().get(i).substring(0,1))){
                int id= Integer.parseInt(filterPanelEntity.getDress_panel().get(i).substring(1,filterPanelEntity.getDress_panel().get(i).length()));
                for (int j=0;j<entity.getSpecifications().size();j++){
                    if(id==entity.getSpecifications().get(j).getId()){
                        ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean=entity.getSpecifications().get(j);
                        bean.panel="s"+id;
                        tagsEntities.add(bean);
                    }
                }
            }

            if("t".equals(filterPanelEntity.getDress_panel().get(i).substring(0,1))){
                int id= Integer.parseInt(filterPanelEntity.getDress_panel().get(i).substring(1,filterPanelEntity.getDress_panel().get(i).length()));
                for (int j=0;j<entity.getTags().size();j++){
                    if(id==entity.getTags().get(j).getId()){
                        ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean=entity.getTags().get(j);
                        bean.panel="t"+id;
                        tagsEntities.add(bean);
                    }
                }
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        topAdapter =new TopAdapter(getActivity(),tagsEntities,2);
        recyclerView.setAdapter(topAdapter);
        sku_list.addHeaderView(header);


        /**
         * 日历视图
         */
//        shoppingBagView2=new ShoppingBagView2(getActivity(), new ShoppingBagView2.OnClickListener() {
//            @Override
//            public void onAddress() {
//                Intent intent=new Intent(getActivity(),LocationActivity.class);
//                startActivityForResult(intent,66);
//            }
//
//            @Override
//            public void onDateOk(Date date) {
//
//            }
//        });


    }

    //监听
    private void setListener() {

        /**
         * 刷新加载
         */
        sku_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE=1;
                initData(MyApplication.token,2,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                sku_list.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                initData(MyApplication.token,2,type,"c:"+MyApplication.region_code+"|"+ss,sort,2,PAGE,SIZE);
            }
        });

        /**
         * 跳转至购物车
         */
        addcartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ShoppingBagActivity.class);
                intent.putExtra("ShoppingBagActivity","2");
                startActivity(intent);
            }
        });

        remmendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TopLeftPopup(getActivity(),MyApplication.getSortEntity().getDress_sort(),2).show(title);
            }
        });

        for (int i=0;i<checkBoxes.length;i++){
            final int finalI = i;
            checkBoxes[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){

                        for (int j=0;j<checkBoxes.length;j++){
                            if(finalI!=j){
                                checkBoxes[j].setChecked(false);
                            }
                        }

                        switch (finalI){
                            case 0:
                                String today;
                                today="d:"+DateUtil.getToDay(DateUtil.DATE_FORMAT)+"|";
                                WardRobeFragment.date=today;
                                initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+date+ss,sort,1,PAGE,SIZE);
                                break;
                            case 1:
                                String t;
                                t="d:"+DateUtil.getTommorDay(DateUtil.DATE_FORMAT)+"|";
                                WardRobeFragment.date=t;
                                initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+t+ss,sort,1,PAGE,SIZE);
                                break;
                            case 2:
                                final CalendarView calendarView=new CalendarView(getActivity(),new RentalMonitor.DataEntity(),2);
                                calendarView.show(MainActivity.line);

                                CalendarLayoutTwo.mConfirmV.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        calendarView.dismiss();
                                        checkBoxes[2].setText(DateUtil.getDay(CalendarLayoutTwo.mCalendarPageTwo.getSelectedDate()));
                                        ContentUtil.makeLog("lzz","zhi:"+CalendarLayoutTwo.mCalendarPageTwo.getSelectedDate());
                                        String t;
                                        t="d:"+DateUtil.getDay(CalendarLayoutTwo.mCalendarPageTwo.getSelectedDate())+"|";
                                        WardRobeFragment.date=t;
                                        initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+t+ss,sort,1,PAGE,SIZE);
                                    }
                                });
                                break;
                        }

                    }else {
                        initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                    }
                }
            });
        }

        //
        calanderIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpServiceClient.getInstance().package_scheuule(MyApplication.token,"1",MyApplication.region_code).enqueue(new Callback<RentalMonitor>() {
                    @Override
                    public void onResponse(Call<RentalMonitor> call, Response<RentalMonitor> response) {

                        if(response.isSuccessful()){
                            if("ok".equals(response.body().getStatus())){
                                RentalMonitor.DataEntity entity= response.body().getData();
                                final CalendarView view=new CalendarView(getActivity(), entity,1);
                                view.show(MainActivity.line);

                                CalendarLayout.mConfirmV.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        view.dismiss();
                                        int [] value=CalendarLayout.mCalendarPageOne.getSelectedRange();
                                       ContentUtil.makeLog("lzz","zhi:"+value[0]+",-----"+value[1]);
                                    }
                                });
                            }else {
                                ContentUtil.makeToast(getActivity(),response.body().getError().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RentalMonitor> call, Throwable t) {

                    }
                });

            }
        });
    }


    /**
     *   获取数据
     * @param access_token
     * @param category
     * @param type
     * @param page
     * @param page_size
     */
    private void initData(String access_token,int category,int type,
                          String filter,String sort,
                          final int TYPE,int page,int page_size){


        dialog.show();
        Call<ProductBean> call= HttpServiceClient.getInstance().product_list(access_token,category,type,filter,sort,page,page_size);
        call.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {
                dialog.cancel();
                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){

                        ProductBean.DataEntity entity=response.body().getData();
                        if (1 == TYPE) {
//                            setViews();
                            nothingTv.setVisibility("0".equals(entity.getTotal())?View.VISIBLE:View.GONE);
                            datas=new ArrayList<>();
                            mergeData(entity.getRows());
                        } else {
                            if (entity.getRows().size()!=0) {
                                mergeData(entity.getRows());
                                sku_list.loadMoreComplete();
                            } else {
                                sku_list.loadMoreComplete();
                                sku_list.setLoadingMoreEnabled(false);
                            }
                        }
                    }else {
                        ContentUtil.makeToast(getActivity(),response.body().getError().getMessage());
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {

                dialog.cancel();
            }
        });

    }

    /**
     * 更新集合数据
     */
    private void mergeData(List<ProductBean.DataEntity.RowsEntity>data){
        if(data!=null){
            datas.addAll(data);
            adapter.updateData(datas);
        }else {
            adapter.updateData(datas);
        }
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
                            addcartIv.setImageResource(R.mipmap.full_gwc);
                        }else {
                            addcartIv.setImageResource(R.mipmap.full_gwc1);
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


    @Override
    public void onResume() {
        super.onResume();
        initPackageSize();
        initData(MyApplication.token,2,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            if(requestCode==66){
                CityBean bean= (CityBean) data.getSerializableExtra(GlobalConsts.INIT_DATA);
                CalendarLayout.setLocation(bean.getName());
                MyApplication.cityName=bean.getName();
                MyApplication.region_code=bean.getKey();
            }
        }
    }

    /**
     * 广播接收器用于接收头部选择逻辑的值
     */
    public class MyBroadcastReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                sort=intent.getStringExtra(GlobalConsts.VALUE);

                if("".equals(sort)){
                    ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean= (ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity) intent.getSerializableExtra(GlobalConsts.INIT_DATA);
                    int postion=intent.getIntExtra(GlobalConsts.POSTION,-1);

                    tagsEntities.set(postion,bean);

                    Map<String,String> map=new HashMap<>();

                    for (int i=0;i<tagsEntities.size();i++){
                        for (int j=0;j<tagsEntities.get(i).getOptions().size();j++){
                            if(tagsEntities.get(i).getOptions().get(j).select){

                                if(map.containsKey(tagsEntities.get(i).panel)){

                                    String id = map.get(tagsEntities.get(i).panel);
                                    id+=","+tagsEntities.get(i).getOptions().get(j).getId();
                                    map.put(tagsEntities.get(i).panel,id);

                                }else {
                                    map.put(tagsEntities.get(i).panel,tagsEntities.get(i).getOptions().get(j).getId());

                                }


                            }
                        }
                    }


                    for (String key:map.keySet()){
                        String values= map.get(key);
                        key+=":"+values;
                        ss+=key+"|";
                    }
                    if(!"".equals(ss)){
                        initData(MyApplication.token,2,type,"c:"+MyApplication.region_code+"|"+ss.substring(0,ss.length()-1),sort,1,PAGE,SIZE);
                        ContentUtil.makeLog("lzz","key:"+ss.substring(0,ss.length()-1));
                    }else {
                        initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                    }
                }else {
                    initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                }


            }

        }
    }





}
