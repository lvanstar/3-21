package com.qiansong.msparis.app.wardrobe.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.wardrobe.adapter.CommentsListAdapter;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/14.
 * <p>
 * 评论列表
 */

public class CommentsListActivity extends BaseActivity {

    @InjectView(R.id.comments_list)
    XRecyclerView commentsList;

    private CommentsListActivity INSTANCE;
    private CommentsListAdapter adapter;
    private CommentsBean.DataEntity bean;
    private List<CommentsBean.DataEntity.RowsEntity>datas;
    private String id;
    private static String SIZE="10";
    private static int PAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_list);
        ButterKnife.inject(this);
        INSTANCE=this;
        id=getIntent().getStringExtra(GlobalConsts.INIT_DATA);
        setInit();
        setListeners();
        initData(PAGE,SIZE,1);
    }


    /**
     * 初始化listview
     */
    private void setInit(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(INSTANCE);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentsList.setLayoutManager(linearLayoutManager);
        commentsList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        commentsList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        commentsList.setArrowImageView(R.mipmap.iconfont_downgrey);
    }

    /**
     * 适配数据
     */
    private void setViews() {

        datas=new ArrayList<>();
        adapter=new CommentsListAdapter(INSTANCE,datas);
        commentsList.setAdapter(adapter);

    }

    private void initData(int PAGE, final String SIZE, final int TYPE){

        HttpServiceClient.getInstance().product_comments(MyApplication.token,id,PAGE,SIZE).enqueue(new Callback<CommentsBean>() {
            @Override
            public void onResponse(Call<CommentsBean> call, Response<CommentsBean> response) {
                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){

                        bean=response.body().getData();
                        if (1 == TYPE) {
                            setViews();
                            mergeData(bean.getRows());
                        } else {
                            if (bean.getRows().size()!=0) {
                                mergeData(bean.getRows());
                                commentsList.loadMoreComplete();
                            } else {
                                commentsList.loadMoreComplete();
                                commentsList.setLoadingMoreEnabled(false);
                            }
                        }

                    }else {
                        ContentUtil.makeToast(INSTANCE,"获取失败");
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentsBean> call, Throwable t) {

            }
        });
    }

    /**
     * 数据合并
     * @param data
     */
    private void mergeData(List<CommentsBean.DataEntity.RowsEntity> data){
        if(data!=null){
            datas.addAll(data);
            adapter.updateData(datas);
        }

    }

    /**
     * 监听
     */
    private void setListeners() {

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 加载数据
         */
        commentsList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                PAGE=1;
                initData(PAGE,SIZE,1);
                commentsList.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                PAGE++;
                initData(PAGE,SIZE,2);
            }
        });
    }
}
