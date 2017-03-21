package com.qiansong.msparis.app.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.bean.EvaluateBean;
import com.qiansong.msparis.app.commom.bean.OrderDeatilBean;
import com.qiansong.msparis.app.commom.selfview.StarBarView;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.mine.adapter.EvaluateAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 衣橱 还衣后 评价操作 yechen
 */
public class EvaluateActivity extends BaseActivity {
    EvaluateAdapter adapter;

    private EvaluateActivity INSTANCE;

    ListView list_evaluate;
    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.list_evaluate)
    ListView listEvaluate;
    @InjectView(R.id.starbar)
    StarBarView starbar;
    @InjectView(R.id.evaluate_Et)
    EditText evaluateEt;
    @InjectView(R.id.evaluate_sbumintTv)
    TextView evaluateSbumintTv;
    @InjectView(R.id.activity_evaluate)
    LinearLayout activityEvaluate;

    private OrderDeatilBean.DataEntity entity;
    private int startNumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.inject(this);
        BarTintManger.getBar(this);
        INSTANCE=this;
        entity= (OrderDeatilBean.DataEntity) getIntent().getSerializableExtra(GlobalConsts.INIT_DATA);
        initView();
        setListeners();
    }

    //填充
    private void initView() {
        list_evaluate = (ListView) findViewById(R.id.list_evaluate);
        adapter = new EvaluateAdapter(INSTANCE,entity.getOrder_detail());
        list_evaluate.setAdapter(adapter);
        ListUtils.setListViewHeightsOmag(list_evaluate);
    }


    private void setListeners(){

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取物流星级评价
        starbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNumber=(int) starbar.getStarRating();
            }
        });


        /**
         * 提交评价
         */
        evaluateSbumintTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

                EvaluateBean bean=new EvaluateBean();
                bean.access_token= MyApplication.token;
                bean.order_id=entity.getId();
                bean.express_remark=evaluateEt.getText().toString();
                bean.express_vote=startNumber;
                bean.order_split_id=3;
                bean.order_item=adapter.getDataEntities();
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(bean));
                HttpServiceClient.getInstance().order_comment(body).enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if(response.isSuccessful()){
                            if("ok".equals(response.body().getStatus())){
                                ContentUtil.makeToast(INSTANCE,"评价成功!");
                                finish();
                            }else {
                                ContentUtil.makeToast(INSTANCE,response.body().getError().getMessage());
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {
                        dialog.cancel();
                    }
                });
            }
        });
    }




}
