package com.qiansong.msparis.app.wardrobe.fragment;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.bean.PackageSizeBean;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayoutTwo;
import com.qiansong.msparis.app.commom.util.AccountUtil;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.SweetAlertDialog;
import com.qiansong.msparis.app.fulldress.adapter.TopAdapter;
import com.qiansong.msparis.app.wardrobe.activity.ShoppingBagActivity;
import com.qiansong.msparis.app.wardrobe.adapter.WardRobeProductAdapter;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;
import com.qiansong.msparis.app.wardrobe.selfview.ShoppingBagView2;
import com.qiansong.msparis.app.wardrobe.selfview.TopLeftPopup;
import com.qiansong.msparis.app.wardrobe.selfview.TopMiddlePopup;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.CityBean;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.util.DateUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
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
 * 日常衣橱
 */

public class WardRobeFragment extends BaseFragment {


    @InjectView(R.id.wardrobe_lefticon_iv)
    ImageView wardrobeLefticonIv;
    @InjectView(R.id.wardrobe_righticon_iv)
    ImageView wardrobeRighticonIv;
    @InjectView(R.id.wardrobe_select_rl)
    RelativeLayout wardrobeSelectRl;
    @InjectView(R.id.wardrobe_list)
    XRecyclerView wardrobeList;
    @InjectView(R.id.wardrobe_tv01)
    TextView wardrobeTv01;
    @InjectView(R.id.wardrobe_iv)
    ImageView wardrobeIv;
    @InjectView(R.id.wardrobe_rl)
    RelativeLayout title;
    private View view;
    RecyclerView recyclerView;
    @InjectView(R.id.wardrobe_nothingTv)
    TextView nothingTv;



    private WardRobeProductAdapter wardRobeProductAdapter;
    private TopMiddlePopup middlePopup;
    private CheckBox[] checkBoxes;
    private static int SIZE=10;
    private static int PAGE=1;
    private List<ProductBean.DataEntity.RowsEntity>datas;
    private TopAdapter topAdapter;
    private MyBroadcastReceiver myBroadcastReceiver;
    List<ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity>tagsEntities;
    private ShoppingBagView2 shoppingBagView2;
    private int type=1;
    public static String ss=""; //获取筛选的所以数据
    private String sort="";//排序规则
    public static String date="";
     private CityBean cityBean;//城市数据
    public static String regsion_code="";//城市代码

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_wardrobe, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setViews();
        setListeners();
    }


    /**
     * 加载页面
     */
    public void initView() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wardrobeList.setLayoutManager(linearLayoutManager);
        wardrobeList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        wardrobeList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        wardrobeList.setArrowImageView(R.mipmap.iconfont_downgrey);
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.wardrobe_top_layout, (ViewGroup) view.findViewById(android.R.id.content), false);
        wardrobeList.addHeaderView(header);
        recyclerView= (RecyclerView) header.findViewById(R.id.wardrobe_top_recycler);
//        size_List = (RecyclerView) header.findViewById(R.id.size_list);
//        categoryList = (RecyclerView) header.findViewById(R.id.color_list);
//        materialList = (RecyclerView) header.findViewById(R.id.material_list);
//        seasonList = (RecyclerView) header.findViewById(R.id.season_list);
        checkBoxes=new CheckBox[]{(CheckBox) header.findViewById(R.id.wardrobe_top_today),(CheckBox) header.findViewById(R.id.wardrobe_top_tomorrow),(CheckBox) header.findViewById(R.id.wardrobe_top_more)};
//        more_tv= (CheckBox) header.findViewById(R.id.wardrobe_top_more);
//        today_Tv= (CheckBox) header.findViewById(R.id.wardrobe_top_today);
//        tomorrow_Tv= (CheckBox) header.findViewById(R.id.wardrobe_top_tomorrow);
//        size= (TextView) header.findViewById(R.id.wardrobe_top_size);
//        jijie= (TextView) header.findViewById(R.id.wardrobe_top_jijie);
//        changhe= (TextView) header.findViewById(R.id.wardrobe_top_changhe);
//        leixing= (TextView) header.findViewById(R.id.wardrobe_top_leixing);


        /**
         * 注册广播
         */
        myBroadcastReceiver=new MyBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(GlobalConsts.FILE_DATA);
        getActivity().registerReceiver(myBroadcastReceiver,intentFilter);

    }


    /**
     * 适配数据
     */
    private void setViews() {

        //获取后天的日期
        checkBoxes[1].setText("后天"+DateUtil.getTommorDay(DateUtil.LOOKING_FORMAT));
        //获取明天的日期
        checkBoxes[0].setText("明天"+DateUtil.getToDay(DateUtil.LOOKING_FORMAT));

        datas=new ArrayList<>();

        wardRobeProductAdapter = new WardRobeProductAdapter(getActivity(),datas);
        wardrobeList.setAdapter(wardRobeProductAdapter);

        //获取初始数据
        ConfigsBean.DataEntity.ProductFilterEntity entity=MyApplication.getProductEntity();

        //获取排序集合
        ConfigsBean.DataEntity.FilterPanelEntity filterPanelEntity=MyApplication.getFilterEntity();
        tagsEntities=new ArrayList<>();
        for (int i=0;i<filterPanelEntity.getDaily_panel().size();i++){
            if("s".equals(filterPanelEntity.getDaily_panel().get(i).substring(0,1))){
              int id= Integer.parseInt(filterPanelEntity.getDaily_panel().get(i).substring(1,filterPanelEntity.getDaily_panel().get(i).length()));
               for (int j=0;j<entity.getSpecifications().size();j++){
                   if(id==entity.getSpecifications().get(j).getId()){
                       ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean=entity.getSpecifications().get(j);
                       bean.panel="s"+id;
                       tagsEntities.add(bean);
                   }
               }
            }

            if("t".equals(filterPanelEntity.getDaily_panel().get(i).substring(0,1))){
                int id= Integer.parseInt(filterPanelEntity.getDaily_panel().get(i).substring(1,filterPanelEntity.getDaily_panel().get(i).length()));
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
        topAdapter =new TopAdapter(getActivity(),tagsEntities,1);
        recyclerView.setAdapter(topAdapter);


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


    /**
     * 监听事件
     */
    private void setListeners() {

        //左边点击事件
        wardrobeLefticonIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new TopLeftPopup(getActivity(),MyApplication.getSortEntity().getDress_sort(),1).show(title);
            }
        });

        //右边点击事件
        wardrobeRighticonIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AccountUtil.showLoginView(getActivity()))return;
                Intent intent=new Intent(getActivity(),ShoppingBagActivity.class);
                intent.putExtra("ShoppingBagActivity","1");
                startActivity(intent);
            }
        });

        //切换title选项
        wardrobeSelectRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPopup();

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


        //选择更多日期
//        checkBoxes[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            }
//        });
////
////        //选择明天日期
//        checkBoxes[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//
//
//                }else {
//
//                }
//            }
//        });
////
////        //选择后天日期
//        checkBoxes[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//
//                }else {
//
//                }
//            }
//        });

        /**
         * 刷新加载
         */
        wardrobeList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE=1;
                initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                wardrobeList.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,2,PAGE,SIZE);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    private void setPopup() {

        wardrobeIv.setImageResource(R.mipmap.wardrobe_to);

        middlePopup = new TopMiddlePopup(getActivity(),wardrobeIv,new TopMiddlePopup.onClickListener() {
            @Override
            public void onButtonR() {

                type=1;
                wardrobeTv01.setText("日常衣橱");
                initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                ContentUtil.makeLog("lzz","oNbutton:");
                middlePopup.dismiss();
            }

            @Override
            public void onButtonVIP() {
                type=2;
                wardrobeTv01.setText("VIP衣橱");
                initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
                ContentUtil.makeLog("lzz","oNbutton2222:");
                middlePopup.dismiss();
            }
        });
        if("日常衣橱".equals(wardrobeTv01.getText().toString())){
           middlePopup.Riv.setVisibility(View.VISIBLE);
            middlePopup.VIPiv.setVisibility(View.GONE);
        }else {
            middlePopup.Riv.setVisibility(View.GONE);
            middlePopup.VIPiv.setVisibility(View.VISIBLE);
        }
        middlePopup.show(title);
    }


    /**
     *   获取数据
     * @param access_token
     * @param category
     * @param type
     * @param page
     * @param page_size
     */
    private   void initData(String access_token,int category,int type,
                         String filter,String sort,
                         final int TYPE,int page,int page_size){

        dialog.show();
        Call<ProductBean>call= HttpServiceClient.getInstance().product_list(access_token,category,type,filter,sort,page,page_size);
        call.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {
                dialog.cancel();
                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){

                        ProductBean.DataEntity entity=response.body().getData();
                        if (1 == TYPE) {
                            nothingTv.setVisibility("0".equals(entity.getTotal())?View.VISIBLE:View.GONE);
                            datas=new ArrayList<>();
                            mergeData(entity.getRows());
                        } else {
                            if (entity.getRows().size()!=0) {
                                mergeData(entity.getRows());
                                wardrobeList.loadMoreComplete();
                            } else {
                                wardrobeList.setNoMore(true);
//                                wardrobeList.loadMoreComplete();
//                                wardrobeList.setLoadingMoreEnabled(false);
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
     * 更新数据集合
     * @param data
     */
    private void mergeData(List<ProductBean.DataEntity.RowsEntity>data){
        if(data!=null){
            datas.addAll(data);
            wardRobeProductAdapter.updateData(datas);
        }else {
            wardRobeProductAdapter.updateData(datas);
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
                            wardrobeRighticonIv.setImageResource(R.mipmap.full_gwc);
                        }else {
                            wardrobeRighticonIv.setImageResource(R.mipmap.full_gwc1);
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
        initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss,sort,1,PAGE,SIZE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcastReceiver);
        ContentUtil.makeLog("lzz","33333");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            if(requestCode==66){
                CityBean cityBean= (CityBean) data.getSerializableExtra(GlobalConsts.INIT_DATA);
//                shoppingBagView2.setAddress(cityBean.getName());
                MyApplication.region_code=cityBean.getKey();
                MyApplication.cityName=cityBean.getName();
            }
        }
    }


    /**
     * 广播接收器用于接收头部选择逻辑的值
     */
    public class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
           if(intent!=null){
               sort=intent.getStringExtra(GlobalConsts.VALUE);

               if("".equals(sort)){
                   ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean= (ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity) intent.getSerializableExtra(GlobalConsts.INIT_DATA);
                   int postion=intent.getIntExtra(GlobalConsts.POSTION,-1);
                   tagsEntities.set(postion,bean);
                   Map<String,String>map=new HashMap<>();

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
                   String sss="";
                   for (String key:map.keySet()){
                       String values= map.get(key);
                       key+=":"+values;
                       sss+=key+"|";
                   }
                   WardRobeFragment.ss=sss;
                   if(!"".equals(sss)){
                       initData(MyApplication.token,1,type,"c:"+MyApplication.region_code+"|"+ss.substring(0,ss.length()-1),sort,1,PAGE,SIZE);
                       ContentUtil.makeLog("lzz","ssss:"+ss.substring(0,ss.length()-1));
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
