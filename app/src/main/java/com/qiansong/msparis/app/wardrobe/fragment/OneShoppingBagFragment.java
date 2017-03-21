package com.qiansong.msparis.app.wardrobe.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.activity.MainActivity;
import com.qiansong.msparis.app.commom.bean.EditPakegeTimeBean;
import com.qiansong.msparis.app.commom.bean.OnePackagesBean;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.selfview.CalendarView.CalendarLayout;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.view.PullToRefreshView;
import com.qiansong.msparis.app.wardrobe.activity.MakeOrderActivity;
import com.qiansong.msparis.app.wardrobe.activity.ShoppingBagActivity;
import com.qiansong.msparis.app.wardrobe.adapter.ShoppingBagAdapter;
import com.qiansong.msparis.app.wardrobe.selfview.CalendarView;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.homepage.util.Eutil;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import butterknife.ButterKnife;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/2/15.
 * 购物袋-单个
 */

@SuppressLint("ValidFragment")
public class OneShoppingBagFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    ListView shop_list;
    ShoppingBagAdapter adapter;
    PullToRefreshView refresh;

    String packages_id;
    OnePackagesBean bean;

    public TextView time;
    public   static TextView   money;
    LinearLayout see_all;//TIP
    ImageView down;
    TextView shuomiang;
    boolean isAll=false;//false 收起  true 张开

    @SuppressLint("ValidFragment")
    public OneShoppingBagFragment(String packages_id) {
        this.packages_id = packages_id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_one_bag, null);
        ButterKnife.inject(this, view);
        data();
        return view;
    }

    private void setListener() {
        view.findViewById(R.id.to_make_order).setOnClickListener(this);
        view.findViewById(R.id.set_time).setOnClickListener(this);
        see_all.setOnClickListener(this);
        refresh.setOnHeaderRefreshListener(new PullToRefreshView.OnHeaderRefreshListener() {

            @Override
            public void onHeaderRefresh(PullToRefreshView view) {
                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {
                        refresh.onHeaderRefreshComplete();
                    }
                }, 1000);
            }
        });

    }

    public void init() {
        money= (TextView) view.findViewById(R.id.money);
        time= (TextView) view.findViewById(R.id.time);
        see_all= (LinearLayout) view.findViewById(R.id.see_all);
        down= (ImageView) view.findViewById(R.id.down);
        shuomiang= (TextView) view.findViewById(R.id.shuomiang);



        time.setText("租赁周期："+Eutil.getStrTime2(bean.getData().getDelivery_date()+"")+"至"+Eutil.getStrTime2(bean.getData().getSend_back_date()+""));
        refresh= (PullToRefreshView) view.findViewById(R.id.refresh);
        refresh.setFooter(false);
        refresh.setOnFooterRefreshListener(new PullToRefreshView.OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(PullToRefreshView view) {
                // TODO Auto-generated method stub
                view.postDelayed(new Runnable() {
                    public void run() {

                        refresh.onFooterRefreshComplete();
                    }
                }, 1000);
            }
        });

        shop_list = (ListView) view.findViewById(R.id.shop_list);
        //判断空格子
        int init_grid_qty=Integer.valueOf(this.bean.getData().getInit_grid_qty());
        if (init_grid_qty>bean.getData().getPackage_items().size()){
            for (int s=0;s<(init_grid_qty-bean.getData().getPackage_items().size());s++){
                OnePackagesBean.DataBean.PackageItemsBean packageItemsBean=new OnePackagesBean.DataBean.PackageItemsBean();
                packageItemsBean.isEmpty=true;
                bean.getData().getPackage_items().add(packageItemsBean);
            }
        }else if (init_grid_qty<this.bean.getData().getPackage_items().size()){

        }

        adapter = new ShoppingBagAdapter(getActivity(), this.bean.getData().getPackage_items());
        shop_list.setAdapter(adapter);

        setListener();
    }
    private void data(){
        HttpServiceClient.getInstance().packages_items(MyApplication.token,packages_id).enqueue(new Callback<OnePackagesBean>() {
            @Override
            public void onResponse(Call<OnePackagesBean> call, Response<OnePackagesBean> response) {
                if (!response.isSuccessful() || !"ok".equals(response.body().getStatus())) return;
                bean=response.body();
                init();
            }

            @Override
            public void onFailure(Call<OnePackagesBean> call, Throwable t) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.to_make_order:to_make_order();//确认订单
                break;
            case R.id.set_time://时间周期更换
                editTime();
                break;
            case R.id.see_all://查看更多
                if (isAll){
                    shuomiang.startAnimation(Eutil.getScaleAnimation_see_little());
                    shuomiang.setVisibility(View.GONE);
                    down.setImageResource(R.mipmap.up);
                    isAll=false;
                }else {
                    shuomiang.startAnimation(Eutil.getScaleAnimation_see_all());
                    shuomiang.setVisibility(View.VISIBLE);
                    down.setImageResource(R.mipmap.down);
                    isAll=true;
                }
                break;
        }
    }

    /**
     * 更换租赁周期
     */
    private void editTime(){
            HttpServiceClient.getInstance().package_scheuule(MyApplication.token, "1", MyApplication.region_code).enqueue(new Callback<RentalMonitor>() {
                @Override
                public void onResponse(Call<RentalMonitor> call, Response<RentalMonitor> response) {

                    if (response.isSuccessful()) {
                        if ("ok".equals(response.body().getStatus())) {
                            RentalMonitor.DataEntity entity = response.body().getData();
                            final CalendarView view = new CalendarView(getActivity(), entity,1);
                            view.show(((ShoppingBagActivity)getActivity()).activity_shopping_bag);

                            CalendarLayout.mConfirmV.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    view.dismiss();
                                    //这里拿到的是两个时间戳
                                    Eutil.makeLog("01~02: " + CalendarLayout.mCalendarPageOne.getSelectedRange());
                                    //更新时间
                                    setTime(CalendarLayout.mCalendarPageOne.getSelectedRange()[0],CalendarLayout.mCalendarPageOne.getSelectedRange()[1]);
                                }
                            });
                        } else {
                            ContentUtil.makeToast(getActivity(), response.body().getError().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<RentalMonitor> call, Throwable t) {

                }
            });

    }
    //更新租赁周期
    private void setTime(final int start_time, final int end_time){
        Map<String,Object> map=new HashMap<>();
        map.put("access_token", MyApplication.token);
        map.put("package_id",bean.getData().getPackage_id());
        map.put("start_date",start_time);
        map.put("end_date",end_time);
        map.put("region_code",MyApplication.region_code);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        HttpServiceClient.getInstance().packages_time(body).enqueue(new Callback<EditPakegeTimeBean>() {
            @Override
            public void onResponse(Call<EditPakegeTimeBean> call, Response<EditPakegeTimeBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    ContentUtil.makeToast(getActivity(),"更新成功");
                    time.setText("租赁周期："+Eutil.getStrTime2(start_time+"")+"至"+Eutil.getStrTime2(end_time+""));

                }
            }

            @Override
            public void onFailure(Call<EditPakegeTimeBean> call, Throwable t) {

            }
        });
    }
    //确认订单
    private void to_make_order(){
        int select_num=0;
        for (int i=0;i<bean.getData().getPackage_items().size();i++){
            if (bean.getData().getPackage_items().get(i).isSelect){
                select_num++;
            }
        }
        if (select_num==0){
            ContentUtil.makeToast(getActivity(),"请至少选择一件衣服");
            return;
        }
        Intent intent=new Intent(getActivity(),MakeOrderActivity.class);

        intent.putExtra(MakeOrderActivity.intent_key,new Gson().toJson(bean));
        startActivity(intent);
    }
}
