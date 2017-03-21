package com.qiansong.msparis.app.wardrobe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.selfview.CustomViewPager;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.adapter.MyMainAdapter;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.util.Eutil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/15.
 * 购物袋-日常
 */

public class ShoppingBagDailyFragment extends BaseFragment {
    private View view;

    CustomViewPager bag_vp;
    private MyMainAdapter adapter;
    RecyclerView bag_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_bag_daily, null);
        ButterKnife.inject(this, view);
        init();

        return view;
    }
    private void setListener() {
        bag_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                 Eutil.makeLog("当前页面偏移的百分比:"+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void init_data(PackagesBean all_bean){
        bag_vp= (CustomViewPager) view.findViewById(R.id.bag_vp);
        bag_list= (RecyclerView) view.findViewById(R.id.bag_list);
        ArrayList<Fragment> page_items=new ArrayList<>();
//        OneShoppingBagFragment bag1,bag2,bag3,bag4;
//        bag1=new OneShoppingBagFragment();bag2=new OneShoppingBagFragment();bag3=new OneShoppingBagFragment();bag4=new OneShoppingBagFragment();
        for (int n=0;n<bean.getData().getUser_package().size();n++){
            page_items.add(new OneShoppingBagFragment(bean.getData().getUser_package().get(n).getPackage_id()));
        }
        adapter = new MyMainAdapter(getChildFragmentManager(), page_items);
        bag_vp.setOffscreenPageLimit(page_items.size()); //设置幕后item的缓存数目
        bag_vp.setPagingEnabled(true);
        bag_vp.setAdapter(adapter);
        setSelectedItem(0);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setAutoMeasureEnabled(true);
        bag_list.setLayoutManager(linearLayoutManager);
        bag_list.setHasFixedSize(true);
        bag_list.setNestedScrollingEnabled(false);
        //设置适配器
        mAdapter = new GalleryAdapter();
        bag_list.setAdapter(mAdapter);

        setListener();
    }
    PackagesBean bean;
    private void init() {
        dialog.show();
        HttpServiceClient.getInstance().packages(TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN,"")).enqueue(new Callback<PackagesBean>() {
            @Override
            public void onResponse(Call<PackagesBean> call, Response<PackagesBean> response) {
                if (!response.isSuccessful()||!response.body().getStatus().equals("ok"))return;
                dialog.dismiss();
                bean=response.body();
                init_data(bean);

            }

            @Override
            public void onFailure(Call<PackagesBean> call, Throwable t) {

            }
        });

    }

    GalleryAdapter mAdapter;

    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;

        public GalleryAdapter() {
            mInflater = LayoutInflater.from(getActivity());
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }
            SimpleDraweeView bag_pic_unselect,bag_pic_select;
//            TextView mTxt;
        }

        @Override
        public int getItemCount() {
            return bean.getData().getUser_package().size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.item_one_bag,
                    viewGroup, false);
            GalleryAdapter.ViewHolder viewHolder = new GalleryAdapter.ViewHolder(view);

            viewHolder.bag_pic_unselect = (SimpleDraweeView) view.findViewById(R.id.bag_pic_unselect);
            viewHolder.bag_pic_select = (SimpleDraweeView) view.findViewById(R.id.bag_pic_select);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final GalleryAdapter.ViewHolder viewHolder, final int i) {
            if (i==bag_vp.getCurrentItem()){
                viewHolder.bag_pic_unselect.setVisibility(View.GONE);
                viewHolder.bag_pic_select.setAnimation(Eutil.getScaleAnimation_small_to_big());
                viewHolder.bag_pic_select.setVisibility(View.VISIBLE);
            }else {
                viewHolder.bag_pic_select.setVisibility(View.GONE);
                viewHolder.bag_pic_select.setVisibility(View.VISIBLE);
                viewHolder.bag_pic_unselect.setVisibility(View.VISIBLE);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelectedItem(i);
                }
            });
//            viewHolder.mImg.setImageResource(mDatas.get(i));
        }

    }
    /**
     * 选择页面
     */
    public void setSelectedItem(int pos) {
        bag_vp.setCurrentItem(pos, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
