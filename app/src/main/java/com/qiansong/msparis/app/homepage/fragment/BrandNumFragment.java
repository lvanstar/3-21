package com.qiansong.msparis.app.homepage.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BrandHomeBean;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.homepage.activity.BrandDetailsActivity;
import com.qiansong.msparis.app.homepage.bean.HotDataBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.util.Eutil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/10.
 * 首页品牌馆
 */

public class BrandNumFragment extends BaseFragment {
    private View view;
    TextView abc_one;
    GridView all_brands;

    List<HotDataBean> hot_data;//热门品牌
    List<String> all_data;//所有品牌 字母

    BrandHomeBean bean;
    SimpleDraweeView image_daily,image_dress;//上面两块
    GridView hot_brands;

    int select_abc=0;//选中的字母
    AllBarndAdapter allBarndAdapter;//所有品牌

    ConfigsBean.DataEntity.BrandEntity brandEntity;//品牌数据
    List<ConfigsBean.DataEntity.BrandEntity.BrandsEntity> brandlist;//品牌数据
    ListView barnd_list;
    AZadapter aZadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.frgment_brand_num_homepage,null);
        ButterKnife.inject(this,view);
//        brandEntity=MyApplication.getBrandEntity();
//        ContentUtil.makeLog("yc","brands.size()"+brandEntity.brands.size());
//        init();

        return view;
    }
    private void init_data(){
        barnd_list= (ListView) view.findViewById(R.id.barnd_list);
        image_daily= (SimpleDraweeView) view.findViewById(R.id.image_daily);
        image_dress= (SimpleDraweeView) view.findViewById(R.id.image_dress);
        ExclusiveUtils.setImageUrl(image_daily,bean.getData().getBanner().getImage_daily(),-1);
        ExclusiveUtils.setImageUrl(image_dress,bean.getData().getBanner().getImage_dress(),-1);

        abc_one= (TextView) view.findViewById(R.id.abc_one);
        all_brands=(GridView)view.findViewById(R.id.all_brands);
        hot_data=new ArrayList<>();
//        for (int i=0;i<12;i++)hot_data.add(new HotDataBean());
         hot_brands=(GridView)view.findViewById(R.id.hot_brands);
        hot_brands.setAdapter(new HotBarndAdapter());
        ListUtils.setGridViewHeightBasedOnChildren(hot_brands,4);

        all_data=new ArrayList<>();
        String[] all_arr=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","I","S","T","U","V","W","X","Y","Z"};
        for (int i=0;i<all_arr.length;i++)all_data.add(all_arr[i]);
        allBarndAdapter= new AllBarndAdapter();
        all_brands.setAdapter(allBarndAdapter);

        brand();
    }
    //管理品牌数据
    private void brand(){
        if (brandEntity==null)return;
        brandlist=new ArrayList<>();
        for (int i = 0;i<brandEntity.brands.size();i++){
            brandEntity .brands.get(i).sort_az= Eutil.nameToAbc(brandEntity.brands.get(i).getName());
        }
        for (int m=0;m< brandEntity .brands.size();m++){
            if ("A".equals(brandEntity .brands.get(m).sort_az)){
                brandlist.add(brandEntity .brands.get(m));
            }
        }
        ListUtils.setListViewHeightsOmag(barnd_list);
        aZadapter=new AZadapter();
        barnd_list.setAdapter(aZadapter);
        ListUtils.setListViewHeightsOmag(barnd_list);
    }
    //监听
    private void setListener() {
        hot_brands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), BrandDetailsActivity.class));
            }
        });
        all_brands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                select_abc=i;
                allBarndAdapter.notifyDataSetChanged();
                abc_one.setText(all_data.get(i));
                brandlist.clear();
                for (int m=0;m< brandEntity .brands.size();m++){
                    if (all_data.get(i).equals(brandEntity .brands.get(m).sort_az)){
                        brandlist.add(brandEntity .brands.get(m));
                    }
                }
                ListUtils.setListViewHeightsOmag(barnd_list);
                aZadapter.notifyDataSetChanged();
            }
        });
    }
    //填充
    private void init() {
        HttpServiceClient.getInstance().brand_home("abc").enqueue(new Callback<BrandHomeBean>() {
            @Override
            public void onResponse(Call<BrandHomeBean> call, Response<BrandHomeBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    bean=response.body();
                    init_data();
                    setListener();
                }
            }

            @Override
            public void onFailure(Call<BrandHomeBean> call, Throwable t) {

            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    //a~z品牌 下面的列表
    public class AZadapter extends BaseAdapter{
        @Override
        public int getCount() {
            return brandlist!=null?brandlist.size():0;
        }

        @Override
        public Object getItem(int position) {
            return brandlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewholder;
            if(convertView==null){
                convertView=View.inflate(getActivity(), R.layout.item_brand_a_z,null);
                viewholder=new ViewHolder(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder= (ViewHolder) convertView.getTag();
            }
            viewholder.name.setText(brandlist.get(position).getName());
            return convertView;
        }

        private class ViewHolder{
            TextView name;
            public ViewHolder(View view){
                name= (TextView) view.findViewById(R.id.name);
            }
        }
    }
    //热门品牌
    private class HotBarndAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return bean.getData().getBrand_commend()!=null?bean.getData().getBrand_commend().size():0;
        }

        @Override
        public Object getItem(int position) {
            return bean.getData().getBrand_commend().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewholder;
            if(convertView==null){
                convertView=View.inflate(getActivity(), R.layout.item_hotbrand,null);
                viewholder=new ViewHolder(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder= (ViewHolder) convertView.getTag();
            }
            ExclusiveUtils.setImageUrl(viewholder.logo,bean.getData().getBrand_commend().get(position).getLogo(),-1);
            return convertView;
        }

        private class ViewHolder{
            SimpleDraweeView logo;
            public ViewHolder(View view){
                logo= (SimpleDraweeView) view.findViewById(R.id.logo);
            }
        }
    }
    //所有品牌 字母
    private class AllBarndAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return all_data!=null?all_data.size():0;
        }

        @Override
        public Object getItem(int position) {
            return all_data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolders viewholder;
            if(convertView==null){
                convertView=View.inflate(getActivity(), R.layout.item_allbrand,null);
                viewholder=new ViewHolders(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder= (ViewHolders) convertView.getTag();
            }
            viewholder.abc.setText(all_data.get(position));
            if (position==select_abc){
               viewholder.abc.setTextColor(Color.parseColor("#BE45C4"));
            }else {
                viewholder.abc.setTextColor(Color.parseColor("#000000"));
            }
            return convertView;
        }

        private class ViewHolders{
            private TextView abc;
            public ViewHolders(View view){
                abc = (TextView) view.findViewById(R.id.abc);
            }
        }
    }
}
