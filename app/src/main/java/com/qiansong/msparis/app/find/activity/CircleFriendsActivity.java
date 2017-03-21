package com.qiansong.msparis.app.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.ListUtils;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.find.adapter.FindClothesAdapter;
import com.qiansong.msparis.app.find.adapter.FindEvaluateAdapter;
import com.qiansong.msparis.app.find.bean.FindDetailItemBean;
import com.qiansong.msparis.app.find.view.MultiImageView;

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
 * 朋友圈详细信息
 */
public class CircleFriendsActivity extends BaseActivity {

    @InjectView(R.id.find_head_back)
    RelativeLayout findHeadBack;
    @InjectView(R.id.find_head_share)
    RelativeLayout findHeadShare;
    @InjectView(R.id.find_head_start)
    RelativeLayout findHeadStart;
    @InjectView(R.id.find_head_start_num)
    TextView findHeadStartNum;
    @InjectView(R.id.find_user_image)
    SimpleDraweeView findUserImage;
    @InjectView(R.id.find_user_name)
    TextView findUserName;
    @InjectView(R.id.find_button)
    TextView findButton;
    @InjectView(R.id.find_user_messages)
    TextView findUserMessages;
    @InjectView(R.id.find_image_list)
    MultiImageView findImageList;
    @InjectView(R.id.find_image_image)
    ImageView findImageImage;
    @InjectView(R.id.find_image_time)
    TextView findImageTime;
    @InjectView(R.id.find_image_address)
    TextView findImageAddress;
    @InjectView(R.id.find_image_layout)
    LinearLayout findImageLayout;
    @InjectView(R.id.find_clothes_num)
    TextView findClothesNum;
    @InjectView(R.id.find_clothes_list)
    GridView findClothesList;
    @InjectView(R.id.find_clothes_layout)
    LinearLayout findClothesLayout;
    @InjectView(R.id.find_evaluate_list)
    GridView findEvaluateList;
    @InjectView(R.id.find_evaluate_layout)
    LinearLayout findEvaluateLayout;
    @InjectView(R.id.find_evaluate_edittext)
    ImageView findEvaluateEdittext;
    @InjectView(R.id.find_evaluate_num)
    TextView findEvaluateNum;
    @InjectView(R.id.find_evaluate_layout_num)
    LinearLayout findEvaluateLayoutNum;
    @InjectView(R.id.find_evaluate)
    TextView findEvaluate;
    @InjectView(R.id.find_evaluate_button)
    TextView findEvaluateButton;
    @InjectView(R.id.find_head_start_image)
    ImageView findHeadStartImage;
    @InjectView(R.id.find_evaluate_text)
    TextView findEvaluateText;

    public String token = "";
    public FindDetailItemBean bean;
    public FindClothesAdapter clothesAdapter;
    public FindEvaluateAdapter evaluateAdapter;

    //是否点赞过
    private int is_like = -1;
    //是否收藏
    private int is_follow = -1;
    private String circle_friends_id;
    private BaseBean basebean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_friends);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    /**
     * 初始化页面
     */
    public void initView() {
        //获取传递id
        circle_friends_id = getIntent().getStringExtra("circle_friends_id");
        circle_friends_id = "1";
        requestData();

        //美衣adapter
        clothesAdapter = new FindClothesAdapter(this);
        findClothesList.setAdapter(clothesAdapter);
        //评论adapter 设置自适应高度
        ListUtils.setGridViewHeightBasedOnChildren(findEvaluateList, 1);
        evaluateAdapter = new FindEvaluateAdapter(this);
        findEvaluateList.setAdapter(evaluateAdapter);
    }

    /**
     * 网络请求  页面基本数据
     */
    public void requestData() {
        dialog.show();
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        //获取收货地址
        HttpServiceClient.getInstance().find_detail(token, circle_friends_id)
                .enqueue(new Callback<FindDetailItemBean>() {
                    @Override
                    public void onResponse(Call<FindDetailItemBean> call, Response<FindDetailItemBean> response) {
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
                    public void onFailure(Call<FindDetailItemBean> call, Throwable t) {
                    }
                });
    }

    /**
     * 网络请求 关注
     */
    public void requestDataFollow() {
        dialog.show();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("id", circle_friends_id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        //获取收货地址
        HttpServiceClient.getInstance().find_follow(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            basebean = response.body();
                            if ("ok".equals(basebean.getStatus())) {
                                requestData();
                                ToastUtil.showAnimToast("已关注");
                            } else {
                                ToastUtil.showAnimToast(basebean.getError().getMessage());
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
     * 网络请求 取消关注
     */
    public void requestDataDelete() {
        dialog.show();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("id", circle_friends_id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        //获取收货地址
        HttpServiceClient.getInstance().find_follow_update(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            basebean = response.body();
                            if ("ok".equals(basebean.getStatus())) {
                                requestData();
                                ToastUtil.showAnimToast("已取消关注");
                            } else {
                                ToastUtil.showAnimToast(basebean.getError().getMessage());
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
     * 网络请求 取消关注
     */
    public void requestDataLike() {
        dialog.show();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("id", circle_friends_id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtil.toJson(map));
        //获取收货地址
        HttpServiceClient.getInstance().find_like(body)
                .enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                        dialog.cancel();
                        if (response.isSuccessful()) {
                            basebean = response.body();
                            if ("ok".equals(basebean.getStatus())) {
                                requestData();
                                if(is_like == 0){
                                    ToastUtil.showAnimToast("点赞成功");
                                }else if(is_like == 1) {
                                    ToastUtil.showAnimToast("已取消赞");
                                }
                            } else {
                                ToastUtil.showAnimToast(basebean.getError().getMessage());
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
        // 0未点赞 1已点赞
        if ("0".equals(bean.getData().getIs_like())) {
            findHeadStartImage.setBackgroundResource(R.drawable.icon_no);
            is_like = 0;
        } else if ("1".equals(bean.getData().getIs_like())) {
            findHeadStartImage.setBackgroundResource(R.drawable.icon_yes);
            is_like = 1;
        }

        //设置关注数  大于99 的要显示为99+
        if (bean.getData().getConcerns_num() != null) {
            if (bean.getData().getConcerns_num().length() > 2) {
                findHeadStartNum.setText("99+");
            } else {
                findHeadStartNum.setText(bean.getData().getConcerns_num() + "");
            }
        }

        //设置头像 名字 关注 评价内荣
        ExclusiveUtils.setImageUrl(findUserImage, bean.getData().getUser().getHead_portrait(), -1);
        findUserName.setText(bean.getData().getUser().getNickname());
        //0未关注 1已关注 2.不可关注
        if ("0".equals(bean.getData().getIs_follow())) {
            findButton.setBackgroundResource(R.drawable.tv_find_text);
            findButton.setTextColor(getResources().getColor(R.color.white));
            findHeadStartNum.setTextColor(getResources().getColor(R.color.violet));
            is_follow = 0;
        } else if ("1".equals(bean.getData().getIs_follow())) {
            findButton.setBackgroundResource(R.drawable.tv_find_text_no);
            findButton.setTextColor(getResources().getColor(R.color.gray));
            findHeadStartNum.setTextColor(getResources().getColor(R.color.gray));
            findButton.setText("已关注");
            is_follow = 1;
        } else if ("2".equals(bean.getData().getIs_follow())) {
            findButton.setBackgroundResource(R.drawable.tv_find_text_no);
            findButton.setTextColor(getResources().getColor(R.color.gray));
            findHeadStartNum.setTextColor(getResources().getColor(R.color.gray));
            is_follow = 2;
            //不可关注干脆直接隐藏这个按钮
            findButton.setVisibility(View.GONE);
        }
        findUserMessages.setText(bean.getData().getContent());

        //设置晒图列表
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < bean.getData().getImages().size(); i++) {
            lists.add(bean.getData().getImages().get(i).getSrc());
        }
        findImageList.setList(lists);

        //设置位置还有时间
        findImageTime.setText(bean.getData().getCreated_at());
        if (bean.getData().getAddress().length() > 0) {
            findImageImage.setVisibility(View.VISIBLE);
            findImageAddress.setText(bean.getData().getAddress());
        } else {
            findImageImage.setVisibility(View.GONE);
        }


        //设置美衣列表
//        List<FindDetailItemBean.DataBean.ClothesBean> getClothes = new ArrayList<FindDetailItemBean.DataBean.ClothesBean>();
//        for (int i = 0; i <3; i++) {
//            FindDetailItemBean.DataBean.ClothesBean clothes = new FindDetailItemBean.DataBean.ClothesBean();
//            clothes.setCover_img("https://gdp.alicdn.com/imgextra/i2/890482188/TB2dlBUgmFjpuFjSszhXXaBuVXa_!!890482188.jpg");
//            getClothes.add(clothes);
//        }
//        bean.getData().setClothes(getClothes);
        if (bean.getData().getClothes() == null || bean.getData().getClothes().size() == 0) {
            findClothesLayout.setVisibility(View.GONE);
        } else {
            findClothesNum.setText("美衣 (" + bean.getData().getConcerns_num() + ")");
            findClothesLayout.setVisibility(View.VISIBLE);
            clothesAdapter.setData(bean.getData().getClothes());
        }

        //设置评论列表
//        List<FindDetailItemBean.DataBean.CommentsAllBean> getComments = new ArrayList<>();
//        for (int i = 0; i <20; i++) {
//            FindDetailItemBean.DataBean.CommentsAllBean comment = new FindDetailItemBean.DataBean.CommentsAllBean();
//            comment.setContent("春天化作黄叶远去\n" +"我还守在原地 寸步不离\n" +"都说爱情是一场游戏");
//            comment.setCreated_at("2017-10-19");
//            getComments.add(comment);
//        }
//        bean.getData().setComments(getComments);
        if (bean.getData().getComments() == null || bean.getData().getComments().size() == 0) {
            findEvaluateLayout.setVisibility(View.GONE);
        } else {
            if (bean.getData().getComment_num().length() > 2) {
                findEvaluate.setText("评价 (99+)");
                findEvaluateNum.setText("99+");
            } else {
                findEvaluate.setText("评价 (" + bean.getData().getComment_num() + ")");
                findEvaluateNum.setText(bean.getData().getComment_num());
            }
            if (Integer.parseInt(bean.getData().getComment_num()) <= 10) {
                findEvaluateButton.setVisibility(View.GONE);
            } else {
                findEvaluateButton.setVisibility(View.VISIBLE);
            }

            findEvaluateLayout.setVisibility(View.VISIBLE);
            evaluateAdapter.setData(bean.getData().getComments());
            //评论adapter 设置自适应高度
            ListUtils.setGridViewHeightBasedOnChildren(findEvaluateList, 1);
        }
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.find_head_back, R.id.find_head_share, R.id.find_head_start, R.id.find_head_start_num, R.id.find_button, R.id.find_evaluate_layout_num, R.id.find_evaluate_button, R.id.find_evaluate_text})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //返回
            case R.id.find_head_back:
                super.onBackPressed();
                break;
            //粉丝
            case R.id.find_head_share:
                break;
            //点赞
            case R.id.find_head_start:
                requestDataLike();
                break;
            //数子
            case R.id.find_head_start_num:
                break;
            //关注按钮
            case R.id.find_button:
                if (is_follow == 0) {
                    //关注
                    requestDataFollow();
                } else if (is_follow == 1) {
                    //取消关注
                    requestDataDelete();
                }
                break;
            //点击说点什么
            case R.id.find_evaluate_text:
                intent.setClass(CircleFriendsActivity.this, EvaluateAllActivity.class);
                intent.putExtra("evaluate_type", true);
                intent.putExtra("find_id", circle_friends_id);
                startActivity(intent);
                break;
            //点击评论数字 、查看更多
            case R.id.find_evaluate_button:
            case R.id.find_evaluate_layout_num:
                intent.setClass(CircleFriendsActivity.this, EvaluateAllActivity.class);
                intent.putExtra("find_id", circle_friends_id);
                startActivity(intent);
                break;

        }
    }
}
