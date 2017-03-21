package com.qiansong.msparis.app.homepage.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.MessageBean;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.homepage.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/3/15.
 * <p>
 * 消息列表
 */

public class MessageListActivity extends BaseActivity {

    @InjectView(R.id.messageListTitleTv)
    TextView messageListTitleTv;
    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.messageListRecycler)
    XRecyclerView messageListRecycler;

    private MessageListActivity INSTANCE;
    private int type;
    private List<MessageBean.DataEntity.RowsEntity>datas;
    private MessageListAdapter adapter;
    private static int SIZE=10;
    private static int PAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE=this;
        type=getIntent().getIntExtra(GlobalConsts.INIT_DATA,-1);

        initView();
        setViews();
        setListeners();
    }


    private void initView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(INSTANCE);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messageListRecycler.setLayoutManager(linearLayoutManager);
        messageListRecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        messageListRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        messageListRecycler.setArrowImageView(R.mipmap.iconfont_downgrey);
    }

    private void setViews(){
        datas=new ArrayList<>();
        adapter=new MessageListAdapter(INSTANCE,datas);
        messageListRecycler.setAdapter(adapter);

        switch (type){
            case 1:
                messageListTitleTv.setText("系统消息");
                break;
            case 2:
                messageListTitleTv.setText("物流消息");
                break;
            case 3:
                messageListTitleTv.setText("还衣消息");
                break;
        }
    }

    private void setListeners(){

        /**
         * 刷新加载
         */
        messageListRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE=1;
                initData(type,1,PAGE,SIZE);
                messageListRecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                PAGE++;
                initData(type,2,PAGE,SIZE);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initData(int type,final int TYPE,int PAGE, int SIZE){

        dialog.show();
        HttpServiceClient.getInstance().messageList(MyApplication.token,type,PAGE,SIZE).enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                dialog.cancel();
                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){

                        MessageBean.DataEntity entity=response.body().getData();
                        if (1 == TYPE) {
                            datas=new ArrayList<>();
                            mergeData(entity.getRows());
                        } else {
                            if (entity.getRows().size()!=0) {
                                mergeData(entity.getRows());
                                messageListRecycler.loadMoreComplete();
                            } else {
                                messageListRecycler.setNoMore(true);
//                                wardrobeList.loadMoreComplete();
//                                wardrobeList.setLoadingMoreEnabled(false);
                            }
                        }

                    }else {
                        ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

                dialog.cancel();
            }
        });
    }


    /**
     * 更新数据集合
     * @param data
     */
    private void mergeData(List<MessageBean.DataEntity.RowsEntity>data){
        if(data!=null){
            datas.addAll(data);
            adapter.mergeData(datas);
        }
    }
}
