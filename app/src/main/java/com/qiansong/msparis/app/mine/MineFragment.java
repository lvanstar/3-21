package com.qiansong.msparis.app.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiansong.msparis.app.commom.util.AccountUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.ToastUtil;
import com.qiansong.msparis.app.commom.util.iosdialog.IosDialogActivity;
import com.qiansong.msparis.app.find.activity.CircleFriendsActivity;
import com.qiansong.msparis.app.mine.activity.InvoiceActivity;
import com.qiansong.msparis.app.mine.activity.MemberActivity;
import com.qiansong.msparis.app.mine.activity.MembershipCardActivity;
import com.qiansong.msparis.app.mine.activity.MyCardActivity;
import com.qiansong.msparis.app.mine.activity.MySubscribeActivity;
import com.qiansong.msparis.app.mine.activity.MyWardrobeActivity;
import com.qiansong.msparis.app.mine.activity.SetUpActivity;
import com.qiansong.msparis.app.mine.bean.GetUserBean;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.ExclusiveUtils;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;
import com.qiansong.msparis.app.mine.activity.CertificationActivity;
import com.qiansong.msparis.app.mine.activity.LoginActivity;
import com.qiansong.msparis.app.mine.activity.MyOrderActivity;
import com.qiansong.msparis.app.mine.activity.MyWalletActivity;
import com.qiansong.msparis.app.mine.activity.ToInviteActivity;
import com.qiansong.msparis.app.mine.activity.UserActivity;
import com.qiansong.msparis.app.mine.activity.WishListActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/4.
 * <p>
 * 我的首页
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    public View view;
    @InjectView(R.id.mine_shezhi)
    RelativeLayout mineShezhi;
    @InjectView(R.id.mine_user_image)
    SimpleDraweeView mineUserImage;
    @InjectView(R.id.mine_user_name)
    TextView mineUserName;
    @InjectView(R.id.mine_title)
    TextView mineTitle;
    @InjectView(R.id.mine_member)
    TextView mineMember;
    @InjectView(R.id.mine_member_card)
    TextView mineMemberCard;
    @InjectView(R.id.main_head_layout)
    LinearLayout mainHeadLayout;
    @InjectView(R.id.mine_order_iv1)
    ImageView mineOrderIv1;
    @InjectView(R.id.mine_order_num1)
    TextView mineOrderNum1;
    @InjectView(R.id.mine_order_ly1)
    RelativeLayout mineOrderLy1;
    @InjectView(R.id.mine_order_iv2)
    ImageView mineOrderIv2;
    @InjectView(R.id.mine_order_num2)
    TextView mineOrderNum2;
    @InjectView(R.id.mine_order_ly2)
    RelativeLayout mineOrderLy2;
    @InjectView(R.id.mine_order_iv3)
    ImageView mineOrderIv3;
    @InjectView(R.id.mine_order_num3)
    TextView mineOrderNum3;
    @InjectView(R.id.mine_order_ly3)
    RelativeLayout mineOrderLy3;
    @InjectView(R.id.mine_order_iv4)
    ImageView mineOrderIv4;
    @InjectView(R.id.mine_order_num4)
    TextView mineOrderNum4;
    @InjectView(R.id.mine_order_ly4)
    RelativeLayout mineOrderLy4;
    @InjectView(R.id.mine_list1)
    ImageView mineList1;
    @InjectView(R.id.mine_list1_text)
    TextView mineList1Text;
    @InjectView(R.id.mine_list1_point)
    ImageView mineList1Point;
    @InjectView(R.id.mine_list1_num)
    TextView mineList1Num;
    @InjectView(R.id.mine_list1_layout)
    RelativeLayout mineList1Layout;
    @InjectView(R.id.mine_list2)
    ImageView mineList2;
    @InjectView(R.id.mine_list2_text)
    TextView mineList2Text;
    @InjectView(R.id.mine_list2_point)
    ImageView mineList2Point;
    @InjectView(R.id.mine_list2_num)
    TextView mineList2Num;
    @InjectView(R.id.mine_list2_layout)
    RelativeLayout mineList2Layout;
    @InjectView(R.id.mine_list3)
    ImageView mineList3;
    @InjectView(R.id.mine_list3_text)
    TextView mineList3Text;
    @InjectView(R.id.mine_list3_point)
    ImageView mineList3Point;
    @InjectView(R.id.mine_list3_num)
    TextView mineList3Num;
    @InjectView(R.id.mine_list3_layout)
    RelativeLayout mineList3Layout;
    @InjectView(R.id.mine_list4)
    ImageView mineList4;
    @InjectView(R.id.mine_list4_text)
    TextView mineList4Text;
    @InjectView(R.id.mine_list4_point)
    ImageView mineList4Point;
    @InjectView(R.id.mine_list4_num)
    TextView mineList4Num;
    @InjectView(R.id.mine_list4_layout)
    RelativeLayout mineList4Layout;
    @InjectView(R.id.mine_list5)
    ImageView mineList5;
    @InjectView(R.id.mine_list5_point)
    ImageView mineList5Point;
    @InjectView(R.id.mine_list5_num)
    TextView mineList5Num;
    @InjectView(R.id.mine_list5_text)
    TextView mineList5Text;
    @InjectView(R.id.mine_list5_layout)
    RelativeLayout mineList5Layout;
    @InjectView(R.id.mine_list6)
    ImageView mineList6;
    @InjectView(R.id.mine_list6_text)
    TextView mineList6Text;
    @InjectView(R.id.mine_list6_point)
    ImageView mineList6Point;
    @InjectView(R.id.mine_list6_num)
    TextView mineList6Num;
    @InjectView(R.id.mine_list6_layout)
    RelativeLayout mineList6Layout;
    @InjectView(R.id.mine_list7)
    ImageView mineList7;
    @InjectView(R.id.mine_list7_text)
    TextView mineList7Text;
    @InjectView(R.id.mine_list7_point)
    ImageView mineList7Point;
    @InjectView(R.id.mine_list7_message)
    TextView mineList7Message;
    @InjectView(R.id.mine_list7_layout)
    RelativeLayout mineList7Layout;
    @InjectView(R.id.mine_list8)
    ImageView mineList8;
    @InjectView(R.id.mine_list8_text)
    TextView mineList8Text;
    @InjectView(R.id.mine_list8_point)
    ImageView mineList8Point;
    @InjectView(R.id.mine_list8_num)
    TextView mineList8Num;
    @InjectView(R.id.mine_list8_layout)
    RelativeLayout mineList8Layout;
    @InjectView(R.id.mine_list9)
    ImageView mineList9;
    @InjectView(R.id.mine_list9_text)
    TextView mineList9Text;
    @InjectView(R.id.mine_list9_point)
    ImageView mineList9Point;
    @InjectView(R.id.mine_list9_num)
    TextView mineList9Num;
    @InjectView(R.id.mine_list9_layout)
    RelativeLayout mineList9Layout;


    //用户tonken
    private String token="";
    private GetUserBean bean = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        ButterKnife.inject(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        initData();
        super.onResume();
    }

    /**
     * 网络请求
     */
    public void requestData() {
        HttpServiceClient.getInstance().User(token)
                .enqueue(new Callback<GetUserBean>() {
                    @Override
                    public void onResponse(Call<GetUserBean> call, Response<GetUserBean> response) {
                        if(response.isSuccessful()){
                            bean = response.body();
                            if ("ok".equals(bean.getStatus())) {
                                    setViewData();
                            } else {
                                mainHeadLayout.setVisibility(View.GONE);
                            }
                        }else {
                            //接口访问失败

                        }

                    }

                    @Override
                    public void onFailure(Call<GetUserBean> call, Throwable t) {

                    }
                });

    }

    /**
     * 设置会员的信息*
     **/
    public void setViewData() {
        ExclusiveUtils.setImageUrl(mineUserImage, bean.getData().getHead_portrait(), -1);
        mineUserName.setText(bean.getData().getNickname());
        if(bean.getData().getUser_card()!=null ){
            mineTitle.setText(bean.getData().getUser_card().getName() + " 有效期至：" + bean.getData().getUser_card().getExpiry_date());
        }else{
            mineTitle.setVisibility(View.INVISIBLE);
        }
        mainHeadLayout.setVisibility(View.VISIBLE);
        if (Integer.parseInt(bean.getData().getOrders().getPending_num()) > 0) {
            mineOrderNum1.setText(bean.getData().getOrders().getPending_num());
            mineOrderNum1.setVisibility(View.VISIBLE);
        } else {
            mineOrderNum1.setVisibility(View.GONE);
        }
        if (Integer.parseInt(bean.getData().getOrders().getTo_be_delivered_num()) > 0) {
            mineOrderNum2.setText(bean.getData().getOrders().getPending_num());
            mineOrderNum2.setVisibility(View.VISIBLE);
        } else {
            mineOrderNum2.setVisibility(View.GONE);
        }
        if (Integer.parseInt(bean.getData().getOrders().getTo_be_received_num()) > 0) {
            mineOrderNum3.setText(bean.getData().getOrders().getPending_num());
            mineOrderNum3.setVisibility(View.VISIBLE);
        } else {
            mineOrderNum3.setVisibility(View.GONE);
        }
        if (Integer.parseInt(bean.getData().getOrders().getTo_be_commented_num()) > 0) {
            mineOrderNum4.setText(bean.getData().getOrders().getPending_num());
            mineOrderNum4.setVisibility(View.VISIBLE);
        } else {
            mineOrderNum4.setVisibility(View.GONE);
        }

        mineList2Num.setText(bean.getData().getWardrobe_num());
        mineList3Num.setText(bean.getData().getUser_card_num());
        mineList5Num.setText(bean.getData().getFavorite_product_num());
        mineList6Num.setText(bean.getData().getFavorite_brand_num());

        switch (Integer.parseInt(bean.getData().getAuthentication_type())) {
            case 0:
                mineList8Num.setText("未认证");
                break;
            case 1:
                mineList8Num.setText("实名认证");
                break;
            case 2:
                mineList8Num.setText("押金认证");
                break;
        }

        mineList9Num.setText("￥"+bean.getData().getInvoice_amount());
    }


    /**
     * 请求接口
     */
    public void initData() {
        token = TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN, null);
        if (token != null && token.length() > 0) {
            requestData();
        } else {
            //退出登陆的时候要把数据全部清空
            mainHeadLayout.setVisibility(View.GONE);
            ExclusiveUtils.setImageUrl(mineUserImage, "", -1);
//            mineUserImage.setBackgroundResource(R.drawable.mine_tx);
            mineUserName.setText("点击登录");
            mineTitle.setVisibility(View.VISIBLE);
            mineTitle.setText("登录后可享受更多特权");
            mineOrderNum1.setVisibility(View.GONE);
            mineOrderNum2.setVisibility(View.GONE);
            mineOrderNum3.setVisibility(View.GONE);
            mineOrderNum4.setVisibility(View.GONE);
            mineList2Num.setText("");
            mineList3Num.setText("");
            mineList5Num.setText("");
            mineList6Num.setText("");
            mineList8Num.setText("未认证");
            mineList9Num.setText("");
        }

    }

    @OnClick({R.id.mine_shezhi, R.id.mine_user_image, R.id.mine_user_name, R.id.mine_member, R.id.mine_member_card, R.id.mine_order_iv1, R.id.mine_order_iv2, R.id.mine_order_iv3, R.id.mine_order_iv4, R.id.mine_list1_layout, R.id.mine_list2_layout, R.id.mine_list3_layout, R.id.mine_list4_layout, R.id.mine_list5_layout, R.id.mine_list6_layout, R.id.mine_list7_layout, R.id.mine_list8_layout,R.id.mine_list9_layout})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //登录
            case R.id.mine_user_name:
            case R.id.mine_user_image:
                if(token != null && token.length() > 0){
                    intent.setClass(getActivity(), UserActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            //设置
            case R.id.mine_shezhi:
                intent.setClass(getActivity(), SetUpActivity.class);
                startActivity(intent);
                break;
            //会员中心
            case R.id.mine_member:
                intent.setClass(getActivity(), MemberActivity.class);
                startActivity(intent);
                break;
            //购买会员卡
            case R.id.mine_member_card:
                intent.setClass(getActivity(), MembershipCardActivity.class);
                startActivity(intent);
                break;
            //代付款
            case R.id.mine_order_iv1:

                break;
            //待发货
            case R.id.mine_order_iv2:
                break;
            // 待收货
            case R.id.mine_order_iv3:
                break;
            // 待评价
            case R.id.mine_order_iv4:
                break;
            //我的订单
            case R.id.mine_list1_layout:
                if(AccountUtil.showLoginView(getActivity()))return;
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            //我的衣橱
            case R.id.mine_list2_layout:
                startActivity(new Intent(getActivity(), MyWardrobeActivity.class));
                break;
            //我的女神卡
            case R.id.mine_list3_layout:
                startActivity(new Intent(getActivity(), MyCardActivity.class));
                break;
            //我的钱包
            case R.id.mine_list4_layout:
                intent.setClass(getActivity(), MyWalletActivity.class);
                startActivity(intent);
                break;
            //心愿单
            case R.id.mine_list5_layout:
                if (AccountUtil.showLoginView(getActivity()))return;
                intent.setClass(getActivity(), WishListActivity.class);
                startActivity(intent);
                break;
            //我的订阅
            case R.id.mine_list6_layout:
                if (AccountUtil.showLoginView(getActivity()))return;
                intent.setClass(getActivity(), MySubscribeActivity.class);
                startActivity(intent);
                break;
            //邀请有奖
            case R.id.mine_list7_layout:
                if (AccountUtil.showLoginView(getActivity()))return;
                startActivity(new Intent(getActivity(), ToInviteActivity.class));
                break;
            //实名认证
            case R.id.mine_list8_layout:
                if (AccountUtil.showLoginView(getActivity()))return;
                intent.setClass(getActivity(), CertificationActivity.class);
                intent.putExtra(CertificationActivity.intent_key,bean.getData().getAuthentication_type());
                startActivity(intent);
                break;
            //发票
            case R.id.mine_list9_layout:
//                ProductDetail detail = new ProductDetail.Builder()
////                        .setTitle("")
////                        .setDesc(goodsDetailsBean.data.product.sku+goodsDetailsBean.data.product.name+goodsDetailsBean.data.product.brand.description)
////                        .setNote("")
////                        .setPicture(goodsDetailsBean.data.product.images.get(0).image_src)
////                        .setUrl("")
////                        .setShow(0)
//                        .create();
//                QiYuUtil.StartQiYu(getActivity(),detail);
//                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"15111296292" )));
//                startActivity(new Intent(getActivity(), InvoiceActivity.class));
                startActivity(new Intent(getActivity(), IosDialogActivity.class));
                break;
        }
    }

    /**
     * 加载页面
     */
    public void initView() {
        mineList8Text.setText("实名或押金认证");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
