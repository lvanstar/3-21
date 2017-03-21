package com.qiansong.msparis.app.wardrobe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.homepage.view.sortlistview.SideBar;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.CityBean;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.wardrobe.adapter.CityListAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 城市选择
 *
 * */
public class LocationActivity extends BaseActivity {
    private CityListAdapter adapter;
    @InjectView(R.id.location_title)
     TextView title;
    @InjectView(R.id.country_lvcountry)
    ListView listView;
    @InjectView(R.id.sidrbar)
    SideBar sideBar;
    @InjectView(R.id.location_address)
    TextView address_Tv;

    private static LocationActivity INSTANCE;
    private List<CityBean>cityBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        INSTANCE = this;
        setContentView(R.layout.activity_location);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        setViews();
        setListeners();
    }


    private void setViews() {
        title.setText("当前城市-"+MyApplication.cityName);
        address_Tv.setText(MyApplication.cityName);
        cityBeanList=getCityBeanList();
        MyApplication.azList.clear();
        for (int i=0;i<cityBeanList.size();i++){
            MyApplication.azList.add(cityBeanList.get(i).getPingyin());
        }
//        Collections.sort(bean.getBrands(), pinyinComparator);
        adapter=new CityListAdapter(INSTANCE,cityBeanList);
        listView.setAdapter(adapter);

    }


    private void setListeners() {

        //返回
        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {       //给城市listview设置监听
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(LocationActivity.this, ProductDetailsActivity.class);
                    intent.putExtra(GlobalConsts.INIT_DATA,cityBeanList.get(i));
                    setResult(2, intent);
                    finish();
            }
        });

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    listView.setSelection(position);
                }

            }
        });
    }


    /**
     * 拼接数据
     * @return
     */

    private List<CityBean> getCityBeanList(){
        cityBeanList=new ArrayList<>();
        //获取地址数据
        ConfigsBean.DataEntity.BookingCitiesEntityX entityX= MyApplication.getBookingEntity();

        List<ConfigsBean.DataEntity.BookingCitiesEntityX.BookingCitiesEntity>entities=entityX.getBooking_cities();
            for (int i=0;i<entities.size();i++){
                for (int j=0;j<entities.get(i).getCities().size();j++){
                    CityBean cityBean=new CityBean(entities.get(i).getCities().get(j).getKey(),entities.get(i).getCities().get(j).getName(),entities.get(i).getCities().get(j).pinyin);
                    cityBeanList.add(cityBean);
                }
            }

        return cityBeanList;
    }


}
