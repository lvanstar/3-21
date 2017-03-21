package com.qiansong.msparis.app.find.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.selfview.ETitleBar;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.find.adapter.EvaluateAdapter;
import com.qiansong.msparis.app.find.bean.CommentsAllBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * kevin.cao
 * <p>
 * 所有评价
 */
public class EvaluateAllActivity extends BaseActivity {


    @InjectView(R.id.title_bar)
    ETitleBar titleBar;
    @InjectView(R.id.evaluate_list)
    XRecyclerView evaluateList;
    @InjectView(R.id.evaluate_text)
    EditText evaluateText;
    @InjectView(R.id.evaluate_submit)
    RelativeLayout evaluateSubmit;

    //用户tonken
    private String token = "";
    private CommentsAllBean bean = null;
    private BaseBean baseBean;

    private int page = 1;
    private int page_size = 10;

    private EvaluateAdapter adapter;

    private String find_id = "";
    List<CommentsAllBean.DataBean.RowsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_all);
        ButterKnife.inject(this);
        setTitleBar();
        initView();
        requestData();
    }

    /**
     * 初始化页面
     */
    public void initView() {
        boolean isShowEdit = getIntent().getBooleanExtra("evaluate_type", false);
        find_id = getIntent().getStringExtra("find_id");
        //显示键盘
        if (isShowEdit) {
            evaluateText.setFocusable(true);
            evaluateText.setFocusableInTouchMode(true);
            evaluateText.requestFocus();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EvaluateAllActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        evaluateList.setLayoutManager(linearLayoutManager);
        evaluateList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        evaluateList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);

        adapter = new EvaluateAdapter(this);
        evaluateList.setAdapter(adapter);

        evaluateList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                requestData();
            }

            @Override
            public void onLoadMore() {
                page += 1;
                requestData();
            }
        });
    }

    /**
     * 网络请求  页面基本数据
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        HttpServiceClient.getInstance().find_comments(token, find_id, page + "", page_size + "")
                .enqueue(new Callback<CommentsAllBean>() {
                    @Override
                    public void onResponse(Call<CommentsAllBean> call, Response<CommentsAllBean> response) {
                        evaluateList.refreshComplete();
                        evaluateList.loadMoreComplete();
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                initData();
                            } else {
                                ToastUtil.showAnimToast(bean.getError().getMessage());
                            }
                        } else {
                            ToastUtil.showAnimToast("网络异常");
                        }
                    }
                    @Override
                    public void onFailure(Call<CommentsAllBean> call, Throwable t) {
                    }
                });
    }

    /**
     * 网络请求  发布评价
     */
    public void requestFindComments(String text) {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("content", text);
        map.put("id", find_id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        HttpServiceClient.getInstance().find_comments(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            baseBean = response.body();
                            if ("ok".equals(baseBean.getStatus())) {
                                page = 1;
                                requestData();
                                evaluateText.setText("");
                            } else {
                                ToastUtil.showAnimToast(baseBean.getError().getMessage());
                            }
                        } else {
                            ToastUtil.showAnimToast("网络异常");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 设置页面数据
     */
    public void initData() {
        if (page == 1) {
            list = bean.getData().getRows();
            adapter.setData(list);
            evaluateList.setLoadingMoreEnabled(true);
        } else {
            list.addAll(bean.getData().getRows());
            adapter.setData(list);
            if (list.size() == Integer.parseInt(bean.getData().getTotal())) {
                evaluateList.setLoadingMoreEnabled(false);
            }

        }
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.evaluate_submit)
    public void onClick() {
        if (evaluateText.getText().toString().trim().length() > 0) {
            requestFindComments(evaluateText.getText().toString().trim());
        } else {
            ToastUtil.showAnimToast("总得说点什么吧");
        }

    }

    /**
     * 设置title
     */
    private void setTitleBar() {
        ETitleBar titleBar = (ETitleBar) findViewById(R.id.title_bar);
        titleBar.setTitle("所有评论");//设置标题
        titleBar.setBackgroundColor(Color.parseColor("#FFFFFF"));//设置背景
        titleBar.setTitleColor(Color.parseColor("#000000"));
        titleBar.setLeftImageResource(R.mipmap.mine_back);
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {//左边点击事件
            @Override
            public void onClick(View view) {
                EvaluateAllActivity.super.onBackPressed();
            }
        });
    }
}
