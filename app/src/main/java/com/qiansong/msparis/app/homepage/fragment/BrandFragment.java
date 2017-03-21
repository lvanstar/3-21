package com.qiansong.msparis.app.homepage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BrandHomeBean;
import com.qiansong.msparis.app.homepage.view.sortlistview.CharacterParser;
import com.qiansong.msparis.app.homepage.view.sortlistview.PinyinComparator;
import com.qiansong.msparis.app.homepage.view.sortlistview.SideBar;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.bean.HotDataBean;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.homepage.view.sortlistview.SortAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/10.
 * 首页品牌馆
 */

public class BrandFragment extends BaseFragment {
    private View view;
    TextView abc_one;

    List<HotDataBean> hot_data;//热门品牌
    List<String> all_data;//所有品牌 字母




    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog1;
    private SortAdapter adapter;
    private ConfigsBean.DataEntity.BrandEntity bean;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    BrandHomeBean bean_brand;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.frgment_brand_homepage,null);
        ButterKnife.inject(this,view);
        bean = MyApplication.getBrandEntity();
//        Eutil.makeLog("brand"+bean.brands.get(0).getId());
        init();
        initsort();
        return view;
    }
    //填充
    private void init() {
//        dialog.show();
        HttpServiceClient.getInstance().brand_home(MyApplication.token).enqueue(new Callback<BrandHomeBean>() {
            @Override
            public void onResponse(Call<BrandHomeBean> call, Response<BrandHomeBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
//                    dialog.dismiss();
                    Eutil.makeLog("品牌馆数据请求成功");
                    bean_brand=response.body();
                    init_data();

                    setListener();
                }
            }

            @Override
            public void onFailure(Call<BrandHomeBean> call, Throwable t) {

            }
        });

    }

   //监听
    private void setListener() {
    }
    private void initsort() {
        Collections.sort(bean.brands, new Comparator<ConfigsBean.DataEntity.BrandEntity.BrandsEntity>() {
            @Override
            public int compare(ConfigsBean.DataEntity.BrandEntity.BrandsEntity p1, ConfigsBean.DataEntity.BrandEntity.BrandsEntity p2) {
                if (p1.getSort() > p2.getSort())
                    return 1;
                else if (p1.getSort() < p2.getSort())
                    return -1;
                else
                    return 0;
            }
        });
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        filledData(bean.brands);
        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        dialog1 = (TextView) view.findViewById(R.id.dialog);
        sideBar.setTextView(dialog1);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) view.findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }
        });


        // 根据a-z进行排序源数据
        Collections.sort(bean.getBrands(), pinyinComparator);
        adapter = new SortAdapter(getActivity(),bean);
        sortListView.setAdapter(adapter);
        sortListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                return false;
            }
        });
    }
    /**
     * 为ListView填充数据
     * @param //date
     * @return
     */
    private void filledData(List<ConfigsBean.DataEntity.BrandEntity.BrandsEntity>  data){
        MyApplication.azList.clear();
        for(int i=0; i<data.size(); i++){
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(data.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                data.get(i).sort_az=sortString.toUpperCase();
            }else{
                data.get(i).sort_az="#";
            }
            //保存字母
            if(!MyApplication.azList.contains(data.get(i).sort_az)){
                if(sortString.matches("[A-Z]")){
                    MyApplication.azList.add(data.get(i).sort_az);
                }else if (!MyApplication.azList.contains("#")){
                    MyApplication.azList.add("#");
                }
            }
        }

    }
   //填充
    private void init_data() {
        adapter.setBean(bean_brand);
//        hot_brands=(GridView)view.findViewById(R.id.hot_brands);
//        hot_brands.setAdapter(new HotBarndAdapter());
//        ListUtils.setGridViewHeightBasedOnChildren(hot_brands,4);


    }


//    //热门品牌
//    private class HotBarndAdapter extends BaseAdapter{
//        @Override
//        public int getCount() {
//            return bean_brand.getData().getBrand_commend()!=null?bean_brand.getData().getBrand_commend().size():0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return bean_brand.getData().getBrand_commend().get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            final HotBarndAdapter.ViewHolder viewholder;
//            if(convertView==null){
//                convertView=View.inflate(getActivity(), R.layout.item_hotbrand,null);
//                viewholder=new HotBarndAdapter.ViewHolder(convertView);
//                convertView.setTag(viewholder);
//            }else {
//                viewholder= (HotBarndAdapter.ViewHolder) convertView.getTag();
//            }
//            ExclusiveUtils.setImageUrl(viewholder.logo,bean_brand.getData().getBrand_commend().get(position).getLogo(),-1);
//            return convertView;
//        }
//
//        private class ViewHolder{
//            SimpleDraweeView logo;
//            public ViewHolder(View view){
//                logo= (SimpleDraweeView) view.findViewById(R.id.logo);
//            }
//        }
//    }




























    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
