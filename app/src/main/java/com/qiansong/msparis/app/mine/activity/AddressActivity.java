package com.qiansong.msparis.app.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.mine.adapter.AddressItemAdapter;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * coalei
 * <p>
 * 地址页面
 */
public class AddressActivity extends BaseActivity {
    /**
     * Listview
     */
    @InjectView(R.id.address_list)
    XRecyclerView addressList;
    private ETitleBar titleBar;
    private AddressItemAdapter addressItemAdapter;
    //用户tonken
    private String token="";
    private AddressBean bean = null;

    private int page = 1;
    private int page_size = 10;

    List<AddressBean.DataBean.RowsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        page=1;
        requestData();
    }
    /**
     * 加载页面
     */
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressList.setLayoutManager(linearLayoutManager);
        addressList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        addressList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        addressItemAdapter = new AddressItemAdapter(this);
        addressList.setAdapter(addressItemAdapter);

        addressList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                requestData();
            }

            @Override
            public void onLoadMore() {
                page+=1;
                requestData();
            }
        });

//        addressList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (is_makeorder){
//                    Intent intent = AddressActivity.this.getIntent();
//                    intent.putExtra(MakeOrderActivity.intent_key,bean.getData().getRows().get(position));
//                    setResult(MakeOrderActivity.resultCode,intent);
//                    finish();
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.setClass(AddressActivity.this, UpdateAddressActivity.class);
//                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_UPDATE);
//                startActivity(intent);
//            }
//        });

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取收货地址
        HttpServiceClient.getInstance().address(token,"",page+"",page_size+"")
                .enqueue(new Callback<AddressBean>() {
                    @Override
                    public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                        addressList.refreshComplete();
                        addressList.loadMoreComplete();
                        dialog.cancel();
                        if(response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showAnimToast(bean.getError().getMessage());
                            }
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<AddressBean> call, Throwable t) {

                    }
                });


    }

    /**
     * 填充数据
     */
    public void initData() {
        if (page == 1) {
            list = bean.getData().getRows();
            addressItemAdapter.setData(list);
            addressList.setLoadingMoreEnabled(true);
        } else {
            list.addAll(bean.getData().getRows());
            addressItemAdapter.setData(list);
            if (list.size() == Integer.parseInt(bean.getData().getTotal()+"")) {
                addressList.setLoadingMoreEnabled(false);
            }
        }
    }



    /**
     * 设置title
     */
    private void setTitleBar() {
        titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("收货地址");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setRightTitle("添加");
        titleBar.setRightTitleVisibility(View.VISIBLE);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                AddressActivity.super.onBackPressed();
            }
        });
        titleBar.setRightLayoutClickListener(new View.OnClickListener() {//右边点击事件
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(AddressActivity.this, UpdateAddressActivity.class);
//                intent.putExtra(GlobalConsts.ADDRESS_TYPE,GlobalConsts.ADDRESS_TYPE_ADD);
                startActivity(intent);
            }
        });
    }

}
