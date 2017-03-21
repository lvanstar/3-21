package com.qiansong.msparis.app.mine.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.qiansong.msparis.BaseFragment;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.util.AndroidUtil;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.homepage.activity.SearchActivity;
import com.qiansong.msparis.app.mine.activity.LoginActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kevin on 2017/2/16.
 * <p>
 * 兑换 女神卡
 */

public class ExchangeCardFragment extends BaseFragment {

    @InjectView(R.id.card_exchange_code)
    EditText cardExchangeCode;
    @InjectView(R.id.card_exchange_button)
    TextView cardExchangeButton;
    private View view;

    private  BaseBean bean;
    private  String  token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_exchange_card, null);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    /**
     * 加载页面
     */
    public void initView() {
        cardExchangeCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    cardExchangeButton.setTextColor(getResources().getColor(R.color.white));
                    cardExchangeButton.setBackgroundResource(R.drawable.tv_textview_black_bg);
                    cardExchangeButton.setClickable(true);
                } else {
                    cardExchangeButton.setTextColor(getResources().getColor(R.color.gray));
                    cardExchangeButton.setBackgroundResource(R.drawable.tv_textview_white_bg);
                    cardExchangeButton.setClickable(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //点击兑换
        cardExchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
                AndroidUtil.hideSoftInput(getActivity());
            }
        });

    }

    /**
     * 网络请求
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        Map<String,Object> map=new HashMap<>();
        map.put("access_token",token);
        map.put("code",cardExchangeCode.getText().toString());
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));

        HttpServiceClient.getInstance().exchange_card(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                ToastUtil.showMessage("兑换成功");
                            } else {
                                ToastUtil.showMessage(bean.getError().getMessage());
                            }
                        } else {
                        }
                    }
                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
    }

    /**
     * 填充数据
     */
    public void initData() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
