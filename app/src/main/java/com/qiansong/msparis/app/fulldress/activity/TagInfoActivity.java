package com.qiansong.msparis.app.fulldress.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.fulldress.adapter.SizeAdapter;
import com.qiansong.msparis.app.fulldress.adapter.TopAdapter;
import com.qiansong.msparis.app.fulldress.bean.SizeBean;
import com.qiansong.msparis.app.wardrobe.adapter.WardRobeProductAdapter;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.util.DateUtil;
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
 * Created by lizhaozhao on 2017/2/15.
 * <p>
 * 标签详情
 */

public class TagInfoActivity extends BaseActivity {

    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.tag_info_title)
    TextView tagInfoTitle;
    @InjectView(R.id.tag_info_recyclerView)
    XRecyclerView tagInfoRecyclerView;
    List<ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity>tagsEntities;
    private TopAdapter topAdapter;
    private CheckBox[] checkBoxes;
    private MyBroadcastReceiver3 myBroadcastReceiver;


    private TagInfoActivity INSTANCE;
    View header;//头
    private List<SizeBean> sizeBeanList;
    private RecyclerView recyclerView;//横向list
    private SizeAdapter sizeAdapter;
    private WardRobeProductAdapter wardRobeProductAdapter;
    private List<ProductBean.DataEntity.RowsEntity>datas;
    private String TagsId;
    private static int SIZE=10;
    private static int PAGE=1;
    public static String date="";
    private String ss=""; //获取筛选的所以数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_info);
        ButterKnife.inject(this);
        INSTANCE=this;
        TagsId=getIntent().getStringExtra(GlobalConsts.INIT_DATA);
        initView();
        setViews();
        setListeners();
    }


    /**
     * 初始化RecyclerView
     */

    private void initView(){

        GridLayoutManager linearLayoutManager = new GridLayoutManager(INSTANCE, 2);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tagInfoRecyclerView.setLayoutManager(linearLayoutManager);
        tagInfoRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        tagInfoRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        tagInfoRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        header = LayoutInflater.from(INSTANCE).inflate(R.layout.tag_info_layout, (ViewGroup)findViewById(android.R.id.content), false);
        recyclerView = (RecyclerView) header.findViewById(R.id.size_list);
        checkBoxes=new CheckBox[]{(CheckBox) header.findViewById(R.id.wardrobe_top_today),(CheckBox) header.findViewById(R.id.wardrobe_top_tomorrow),(CheckBox) header.findViewById(R.id.wardrobe_top_more)};


        /**
         * 注册广播
         */
        myBroadcastReceiver=new MyBroadcastReceiver3();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(GlobalConsts.FILE_DATASS);
        registerReceiver(myBroadcastReceiver,intentFilter);
    }

    /**
     * 数据适配
     */
    private void setViews(){
        datas=new ArrayList<>();
        //获取后天的日期
        checkBoxes[1].setText("后天"+ DateUtil.getTommorDay(DateUtil.LOOKING_FORMAT));
        //获取明天的日期
        checkBoxes[0].setText("明天"+DateUtil.getToDay(DateUtil.LOOKING_FORMAT));
        wardRobeProductAdapter = new WardRobeProductAdapter(INSTANCE,datas);
        tagInfoRecyclerView.setAdapter(wardRobeProductAdapter);
        tagInfoRecyclerView.addHeaderView(header);
        //获取初始数据
        ConfigsBean.DataEntity.ProductFilterEntity entity=MyApplication.getProductEntity();

        //获取排序集合
        ConfigsBean.DataEntity.FilterPanelEntity filterPanelEntity=MyApplication.getFilterEntity();
        tagsEntities=new ArrayList<>();
        for (int i=0;i<filterPanelEntity.getDaily_tags_panel().size();i++){
            if("s".equals(filterPanelEntity.getDaily_tags_panel().get(i).substring(0,1))){
                int id= Integer.parseInt(filterPanelEntity.getDaily_tags_panel().get(i).substring(1,filterPanelEntity.getDaily_tags_panel().get(i).length()));
                for (int j=0;j<entity.getSpecifications().size();j++){
                    if(id==entity.getSpecifications().get(j).getId()){
                        ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean=entity.getSpecifications().get(j);
                        bean.panel="s"+id;
                        tagsEntities.add(bean);
                    }
                }
            }

            if("t".equals(filterPanelEntity.getDaily_tags_panel().get(i).substring(0,1))){
                int id= Integer.parseInt(filterPanelEntity.getDaily_tags_panel().get(i).substring(1,filterPanelEntity.getDaily_tags_panel().get(i).length()));
                for (int j=0;j<entity.getTags().size();j++){
                    if(id==entity.getTags().get(j).getId()){
                        ConfigsBean.DataEntity.ProductFilterEntity.TagsEntity bean=entity.getTags().get(j);
                        bean.panel="t"+id;
                        tagsEntities.add(bean);
                    }
                }
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(INSTANCE);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        topAdapter =new TopAdapter(INSTANCE,tagsEntities,3);
        recyclerView.setAdapter(topAdapter);
    }


    /**
     *   获取数据
     * @param access_token
     * @param page
     * @param page_size
     */
    private void initData(String access_token,String tag_id,
                         String filter,final int TYPE,
                         int page,int page_size){

        Call<ProductBean> call= HttpServiceClient.getInstance().product_tags(access_token,tag_id,filter,page,page_size);
        call.enqueue(new Callback<ProductBean>() {
            @Override
            public void onResponse(Call<ProductBean> call, Response<ProductBean> response) {
                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){
                        ProductBean.DataEntity entity=response.body().getData();
                        if (1 == TYPE) {
//                            setViews();
                            datas=new ArrayList<>();
                            mergeData(entity.getRows());
                        } else {
                            if (entity.getRows().size()!=0) {
                                mergeData(entity.getRows());
                                tagInfoRecyclerView.loadMoreComplete();
                            } else {
                                tagInfoRecyclerView.loadMoreComplete();
                                tagInfoRecyclerView.setLoadingMoreEnabled(false);
                            }
                        }
                    }else {
                        ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ProductBean> call, Throwable t) {

            }
        });

    }


    private void mergeData(List<ProductBean.DataEntity.RowsEntity>data){
        if(data!=null){
            datas.addAll(data);
            wardRobeProductAdapter.updateData(datas);
        }
    }

    /**
     * 事件监听
     */
    private void setListeners(){

        //返回
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tagInfoRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                PAGE=1;
                initData(MyApplication.token,TagsId,"",1,PAGE,SIZE);
                tagInfoRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                PAGE++;
                initData(MyApplication.token, TagsId, "",2, PAGE, SIZE);
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
                                TagInfoActivity.date=today;
                                initData(MyApplication.token,TagsId,date+ss,1,PAGE,SIZE);
                                break;
                            case 1:
                                String t;
                                t="d:"+DateUtil.getTommorDay(DateUtil.DATE_FORMAT)+"|";
                                TagInfoActivity.date=t;
                                initData(MyApplication.token,TagsId,t+ss,1,PAGE,SIZE);
                                break;
                            case 2:
                                break;
                        }

                    }else {
                        initData(MyApplication.token,TagsId,"",1,PAGE,SIZE);
                    }
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(MyApplication.token,TagsId,"",1,PAGE,SIZE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    /**
     * 广播接收器用于接收头部选择逻辑的值
     */
    public class MyBroadcastReceiver3 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
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
                    initData(MyApplication.token,TagsId,ss.substring(0,ss.length()-1),1,PAGE,SIZE);
                    ContentUtil.makeLog("lzz","key:"+ss.substring(0,ss.length()-1));
                }

            }

        }
    }
}
