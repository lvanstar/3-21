package com.qiansong.msparis.app.commom.callback;


import com.qiansong.msparis.app.commom.bean.AliBean;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.bean.BrandDetailsBean;
import com.qiansong.msparis.app.commom.bean.BrandHomeBean;
import com.qiansong.msparis.app.commom.bean.CardListBean;
import com.qiansong.msparis.app.commom.bean.ClothingRecordBean;
import com.qiansong.msparis.app.commom.bean.CommentsBean;
import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.bean.EditPakegeTimeBean;
import com.qiansong.msparis.app.commom.bean.FindListBean;
import com.qiansong.msparis.app.commom.bean.HomePageBean;
import com.qiansong.msparis.app.commom.bean.HomeTopicsBean;
import com.qiansong.msparis.app.commom.bean.MessageBean;
import com.qiansong.msparis.app.commom.bean.OnceJsonBean;
import com.qiansong.msparis.app.commom.bean.OnePackagesBean;
import com.qiansong.msparis.app.commom.bean.OrderDailyBean;
import com.qiansong.msparis.app.commom.bean.OrderDeatilBean;
import com.qiansong.msparis.app.commom.bean.OrderListBean;
import com.qiansong.msparis.app.commom.bean.PackageAddBean;
import com.qiansong.msparis.app.commom.bean.PackageSizeBean;
import com.qiansong.msparis.app.commom.bean.PackagesBean;
import com.qiansong.msparis.app.commom.bean.PriceBean;
import com.qiansong.msparis.app.commom.bean.ProductBrandBean;
import com.qiansong.msparis.app.commom.bean.ProductsBean;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;
import com.qiansong.msparis.app.commom.bean.ReturnBean;
import com.qiansong.msparis.app.commom.bean.ShoppingCartBean;
import com.qiansong.msparis.app.commom.bean.UserInvoicesBean;
import com.qiansong.msparis.app.commom.bean.UserWardrobeBean;
import com.qiansong.msparis.app.commom.bean.VersionBean;
import com.qiansong.msparis.app.commom.bean.WeixinPreBean;
import com.qiansong.msparis.app.commom.bean.WishListBean;
import com.qiansong.msparis.app.find.bean.CommentsAllBean;
import com.qiansong.msparis.app.find.bean.FindDetailItemBean;
import com.qiansong.msparis.app.find.bean.MyCircleBean;
import com.qiansong.msparis.app.commom.bean.YajinOrderBean;
import com.qiansong.msparis.app.fulldress.bean.AppointmentRecordBean;
import com.qiansong.msparis.app.fulldress.bean.LookingTimeBean;
import com.qiansong.msparis.app.homepage.bean.SearchBean;
import com.qiansong.msparis.app.homepage.bean.SearchResultBean;
import com.qiansong.msparis.app.mine.bean.AddressBean;
import com.qiansong.msparis.app.mine.bean.BuyCardBean;
import com.qiansong.msparis.app.mine.bean.CertificationBean;
import com.qiansong.msparis.app.mine.bean.CouponBean;
import com.qiansong.msparis.app.mine.bean.GetUserBean;
import com.qiansong.msparis.app.mine.bean.IntegralBean;
import com.qiansong.msparis.app.mine.bean.MyCardBean;
import com.qiansong.msparis.app.mine.bean.MyWallet;
import com.qiansong.msparis.app.mine.bean.OldCouponBean;
import com.qiansong.msparis.app.commom.bean.FollowBrandBean;
import com.qiansong.msparis.app.commom.bean.InviteBean;
import com.qiansong.msparis.app.commom.bean.MyBrandBean;
import com.qiansong.msparis.app.commom.bean.ProductBean;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.fulldress.bean.AppointmentBean;
import com.qiansong.msparis.app.mine.bean.BuyCarDetailsBean;
import com.qiansong.msparis.app.mine.bean.DepositBean;
import com.qiansong.msparis.app.mine.bean.LoginBean;
import com.qiansong.msparis.app.mine.bean.MineBean;
import com.qiansong.msparis.app.mine.bean.PriceCardBean;
import com.qiansong.msparis.app.mine.bean.PrivilegeBean;
import com.qiansong.msparis.app.mine.bean.PushImgBean;
import com.qiansong.msparis.app.mine.bean.SeleteCouponBean;
import com.qiansong.msparis.app.mine.bean.SignsBean;
import com.qiansong.msparis.app.mine.bean.SignsRequestBean;
import com.qiansong.msparis.app.mine.bean.ThirdBean;
import com.qiansong.msparis.app.mine.bean.UseDetailsBean;
import com.qiansong.msparis.app.mine.bean.UserBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by lizhaozhao on 16/4/20.
 */
public interface InterfaceService {


    /**
     * 更新版本检查
     */

//    @FormUrlEncoded
    @GET(GlobalConsts.VERSION)
    Call<VersionBean>version(@Query("platform") String platform);


    /**
     * 首页
     */
    @GET("/home")
    Call<HomePageBean> home(@Query("access_token") String access_token);

    /**
     * 品牌馆
     */
    @GET("/brand/home")
    Call<BrandHomeBean> brand_home(@Query("access_token") String access_token);

    /**
     * 基本配置
     */
    @GET(GlobalConsts.CONFIGS)
    Call<ConfigsBean>congigs(@Query("brand") int brand, @Query("product_filter") long product_filter,
                             @Query("send_cities") int send_cities, @Query("booking_cities") int booking_cities,
                             @Query("filter_panel")int filter_panel, @Query("sort")int sort, @Query("user_size")int user_size);

    /**
     * 品牌详情
     */
    @GET("/brand")
    Call<BrandDetailsBean> brand(@Query("id") int id);

    /**
     * 根据关键字，获取商品列表信息
     */
    @GET("/product/brand")
    Call<ProductBrandBean> product_brand(@Query("access_token") String access_token,
                                         @Query("brand_id") int brand_id,
                                         @Query("start") String start,
                                         @Query("size") String size,
                                         @Query("page") int page,
                                         @Query("page_size") int page_size);

    /**
     * 标签商品列表
     */
    @GET("/product/brand")
    Call<ProductBean> product_tags(@Query("access_token") String access_token,
                                         @Query("tag_id") String tag_id,
                                         @Query("filter") String start,
                                         @Query("page") int page,
                                         @Query("page_size") int page_size);

    /**
     * 购物袋列表
     */
    @GET("/packages")
    Call<PackagesBean> packages(@Query("access_token") String access_token);
    /**
     * 返回用户当前选择购物袋的商品列表
     */
    @GET("packages/items")
    Call<OnePackagesBean> packages_items(@Query("access_token") String access_token, @Query("package_id") String package_id);
    /**
     * 获取短信验证码
     */
    @GET(GlobalConsts.VOICE)
    Call<BaseBean>voice(@Query("mobile") String mobile);

    /**
     * 获取短信验证码
     */
    @GET(GlobalConsts.SMS)
    Call<BaseBean>sms(@Query("mobile") String mobile);

    /**
     * 登录
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.LOGIN)
    Call<LoginBean>login(@Body RequestBody route);


    /**
     * 第三方登录
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.THIRDPARTY)
    Call<ThirdBean>thirdParty(@Body RequestBody route);

    /**
     * 绑定手机号
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.MOBILE)
    Call<LoginBean>binding_mobile(@Body RequestBody route);


    /**
     * 根据token个人中心数据
     */
    @GET(GlobalConsts.GETUSER)
    Call<GetUserBean>User(@Query("access_token") String access_token);

    /**
     * 根据token获取会员中心首页数据
     */
    @GET(GlobalConsts.CENTRE)
    Call<MineBean>centre(@Query("access_token") String access_token);

    /**
     * 获取用户基本信息
     */
    @GET(GlobalConsts.USER)
    Call<UserBean>getUser(@Query("access_token") String access_token);

    /**
     * 所有特权
     */
    @GET(GlobalConsts.USER_PRIVILEGE)
    Call<PrivilegeBean>member_privilege(@Query("access_token") String access_token);

    /**
     * 更新会员基本信息
     */
//    @FormUrlEncoded
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.USER)
    Call<BaseBean>updateUser(@Body RequestBody route);
//    Call<BaseBean>updateUser(@Field("access_token") String access_token,
//                             @Field("nickname") String nickname,
//                             @Field("gender") String gender,
//                             @Field("dob") String dob,
//                             @Field("fit_height") String fit_height,
//                             @Field("head_portrait") String head_portrait,
//                             @Field("size") String size);
    /**
     * 兑换会员卡
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("card/exchange-card")
    Call<BaseBean>exchange_card(@Body RequestBody route);


    /**
     * 验证旧手机
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("common/check-sms")
    Call<BaseBean>check_sms(@Body RequestBody route);

    /**
     * 更换手机号
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("user/replace-mobile")
    Call<BaseBean>replace_mobile(@Body RequestBody route);


    /**
     * 获取用户的地址
     */
    @GET(GlobalConsts.ADDRESS)
    Call<AddressBean>address(@Query("access_token") String access_token, @Query("city_code") String city_code, @Query("page") String page, @Query("page_size") String page_size);

    /**
     * 修改或者添加用户地址
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.ADDRESS)
    Call<BaseBean>address_update(@Body RequestBody route);

    /**
     * 删除用户的地址  delete 请求方式
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @HTTP(method = "DELETE", path = GlobalConsts.ADDRESS, hasBody = true)
    Call<BaseBean>address_delete(@Body RequestBody route);



    /**
     * 获取女神卡列表
     */
    @GET(GlobalConsts.CARD)
    Call<BuyCardBean>card(@Query("access_token") String access_token);

    /**
     * 获取女神卡详情
     */
    @GET(GlobalConsts.CARD_DETAIL)
    Call<BuyCarDetailsBean>card_detail(@Query("access_token") String access_token, @Query("id") String id);

    /**
     * 我的钱包
     */
    @GET(GlobalConsts.WALLET)
    Call<MyWallet>myWallet(@Query("access_token") String access_token);

    /**
     * 我的押金
     */
    @GET(GlobalConsts.DEPOSIT)
    Call<DepositBean>mydeposit(@Query("access_token") String access_token,@Query("type") String type, @Query("page") String page, @Query("page_size") String page_size);

    /**
     * 我的优惠券
     */
    @GET(GlobalConsts.COUPON)
    Call<CouponBean>mycoupon(@Query("access_token") String access_token, @Query("page") String page, @Query("page_size") String page_size);


    /**
     * 兑换优惠券
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("user/exchange-coupon")
    Call<BaseBean>exchange_coupon(@Body RequestBody route);

    /**
     * 历史优惠券
     */
    @GET(GlobalConsts.COUPON_HISTORY)
    Call<OldCouponBean>mycoupon_history(@Query("access_token") String access_token, @Query("page") String page, @Query("page_size") String page_size);

    /**
     * 选择优惠券
     */
    @GET(GlobalConsts.COUPON_HISTORY)
    Call<SeleteCouponBean>coupon_selete(@Query("access_token") String access_token,
                                        @Query("page") String page,
                                        @Query("page_size") String page_size);


    /**
     *  商品筛选列表
     */
    @GET(GlobalConsts.PRODUCT_LIAT)
    Call<ProductBean>product_list(@Query("access_token")String access_token,@Query("category")int category,@Query("type")int type,
                                  @Query("filter")String filter,@Query("sort")String sort,
                                  @Query("page")int page,@Query("page_size")int page_size);


    /**
     * 商品评论
     */
    @GET(GlobalConsts.PRODUCT_COMMENTS)
    Call<CommentsBean>product_comments(@Query("access_token")String access_token, @Query("product_id")String product_id, @Query("page")int page,
                                       @Query("page_size")String page_size);
    /**
     *  获取我的女神卡使用明细
     */
    @GET(GlobalConsts.CARDS+"/1")
    Call<UseDetailsBean>user_cards(@Query("access_token") String access_token, @Query("page") String page, @Query("page_size") String page_size);

    /**
     * 商品详情
     */
    @GET(GlobalConsts.PRODUCT)
    Call<ProductsBean>product(@Query("access_token")String access_token, @Query("id")String id);

    /**
     * 收藏／取消 商品
     */
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = GlobalConsts.WISH, hasBody = true)
        Call<BaseBean>wish(@Field("access_token") String access_token, @Field("id") String id);


    /**
     * 获取我的女神卡
     */
    @GET(GlobalConsts.USER_CARD)
    Call<MyCardBean>user_card(@Query("access_token") String access_token);

    /**
     * 激活我的女神卡
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.ACTIVATION)
    Call<BaseBean>activation(@Body RequestBody route);

    /**
     * 预约时间
     */
    @GET(GlobalConsts.BOOKING_TIME)
    Call<LookingTimeBean>booking_time(@Query("access_token") String access_token, @Query("date") String date, @Query("type") String type, @Query("store_id") String store_id);

    /**
     * 预约详情
     */
    @GET(GlobalConsts.BOOKING_INFO)
    Call<AppointmentBean>booking_info(@Query("access_token") String access_token);

    /**
     * 取消预约
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @HTTP(method = "DELETE", path = GlobalConsts.BOOKING_INFO, hasBody = true)
    Call<AppointmentBean>booking_delete(@Body RequestBody route);


    /**
     * 创建预约
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.BOOKING_INFO)
    Call<BaseBean>create_booking_info(@Body RequestBody route);
    /**
     * 预约历史
     */
    @GET(GlobalConsts.BOOKING)
    Call<AppointmentRecordBean>booking(@Query("access_token") String access_token, @Query("page") String page, @Query("page_size") String page_size);


    /**
     * 根据关键字搜索
     */
    @GET(GlobalConsts.KEYWORD)
    Call<SearchResultBean>product_keyword(@Query("access_token") String access_token,
                                          @Query("keyword") String keyword,
                                          @Query("mode") int category,
                                          @Query("page") int page,
                                          @Query("page_size") int page_size);
    /**
     * 根据关键字联想
     */
    @GET(GlobalConsts.KEY_AUTOCOMPLETE)
    Call<SearchBean>key_autocomplete(@Query("access_token") String access_token,
                                     @Query("keyword") String keyword);


    /**
     * 图片上传获得相对路径
     * */
    @Multipart
    @POST(GlobalConsts.PUSH_IMAGE)
    Call<PushImgBean>pushImage(@Part("file[]\"; filename=\"image.png\"") RequestBody file);

    /**
     * 专题馆
     */
    @GET("home/topics")
    Call<HomeTopicsBean>home_topics(@Query("access_token") String access_token, @Query("access_token") int page, @Query("access_token") int page_size);
    /**
     * 购物车列表
     */
    @GET("cart/dress")
    Call<ShoppingCartBean>cart_dress(@Query("access_token") String access_token);

    /**
     * 关注的品牌列表
     */
    @GET("user/brands")
    Call<MyBrandBean>user_brands(@Query("access_token") String access_token, @Query("page") String page, @Query("page_size") String page_size);

    /**
     * 品牌订阅-取消
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/brands")
    Call<FollowBrandBean>user_brands_0(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @HTTP(method = "DELETE", path = "user/brands", hasBody = true)
    Call<FollowBrandBean>user_brands_1(@Body RequestBody requestBody);


    /**
     * 加入购物袋
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.PACKAGE_ADD)
    Call<PackageAddBean>packageAdd(@Body RequestBody body);

    /**
     * 获取购物袋商品可借周期
     */
    @GET(GlobalConsts.PACKAGE_SCHEDULE)
    Call<RentalMonitor>package_scheuule(@Query("access_token")String access_token, @Query("package_id")String package_id, @Query("region_code")String region_code);


    /**
     * 获取商品可借周期
     */
    @GET(GlobalConsts.SCHEDULE)
    Call<RentalMonitor>scheuule(@Query("access_token")String access_token,@Query("mode")int mode,@Query("product_id")String product_id,@Query("region_code")String region_code);


    /**
     * 获取购物袋剩余商品数量
     * @param access_token
     * @return
     */
    @GET(GlobalConsts.PACKAGE_SIZE)
    Call<PackageSizeBean>packages_size(@Query("access_token")String access_token);

    /**
     * 邀请有奖
     */
    @GET("user/inviter")
    Call<InviteBean>user_inviter(@Query("access_token") String access_token);

    /**
     * 更新邀请人
     */
    @FormUrlEncoded
    @POST("user/inviter")
    Call<FollowBrandBean>user_inviter(@Field("access_token") String access_token, @Field("invite_people")String invite_people);

    /**
     * 获取实名认证信息
     */
    @GET("user/realname")
    Call<CertificationBean>user_realname(@Query("access_token") String access_token);

    /**
     * 用户实名认证信息提交
     */
    @FormUrlEncoded
    @POST("user/realname")
    Call<FollowBrandBean>user_realname_edit(@Field("access_token") String access_token, @Field("real_name")String real_name, @Field("id_number")String id_number);

    /**
     * 计算租赁订单价格
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("price/daily")
    Call<PriceBean>price_daily(@Body RequestBody requestBody);

    /**
     * 用户心愿单列表数据
     * 0全部 1日常 2礼服
     */
    @GET("user/wish")
    Call<WishListBean>user_wish(@Query("access_token") String access_token, @Query("type") String type,
                                @Query("page") String page, @Query("page_size") String page_size);

    /**
     *
     * 积分明细
     */
    @GET("user/points")
    Call<IntegralBean>user_points(@Query("access_token") String access_token, @Query("page") String page, @Query("page_size") String page_size);

    /**
     *
     * 获取签到数据
     */
    @GET("user/points")
    Call<SignsBean>signs(@Query("access_token") String access_token, @Query("date") String date);

    /**
     *
     * 获取签到数据
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("user/points")
    Call<SignsRequestBean>Requst_signs(@Body RequestBody route);


    /**
     * 创建租赁订单
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/daily")
    Call<OrderDailyBean>order_daily(@Body RequestBody requestBody);

    /**
     * 我的订单 - 租赁订单
     */
    @GET(GlobalConsts.USER_ORDERS)
    Call<OrderListBean>user_orders(@Query("access_token") String access_token,@Query("type")int type,@Query("page")int page,@Query("page_size")int page_size);

    /**
     * 我的订单 - 女神卡列表
     */
    @GET(GlobalConsts.USER_CRAD_LIST)
    Call<CardListBean>cardList(@Query("access_token") String access_token,@Query("page")int page,@Query("page_size")int page_size);

    /**
     *  订单详情 租赁
     */
    @GET(GlobalConsts.USER_ORDER_DEATIL)
    Call<OrderDeatilBean>user_orderDearil(@Query("access_token") String access_token,@Query("id")String id);


    /**
     * 还衣记录列表
     */
    @GET("order/return")
    Call<ClothingRecordBean>order_return(@Query("access_token") String access_token,
                                         @Query("page") String page, @Query("page_size") String page_size);

    /**
     * 暂停会员期
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("user/pause-card")
    Call<BaseBean>pause_card(@Body RequestBody route);

    /**
     * 确认订单
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("price/card")
    Call<PriceCardBean>price_card(@Body RequestBody route);

    /**
     *  评价
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(GlobalConsts.ORDER_COMMENT)
    Call<BaseBean>order_comment(@Body RequestBody body);

    /**
     * 更新运单号
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/return-bill")
    Call<FollowBrandBean>order_returnbill(@Body RequestBody requestBody);

    /**
     * 商品收藏 - 取消
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST(GlobalConsts.WISH)
    Call<BaseBean>to_wish(@Body RequestBody requestBody);

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @HTTP(method = "DELETE", path = GlobalConsts.WISH, hasBody = true)
    Call<BaseBean>to_no_wish(@Body RequestBody requestBody);

    /**
     * 支付宝支付的数据进行加密
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/alipay-encryption")
    Call<AliBean>alipay_sign(@Body RequestBody requestBody);

    /**
     * 开票记录列表
     */
    @GET("user/invoices")
    Call<UserInvoicesBean>user_invoices(@Query("access_token") String access_token,
                                        @Query("page") String page, @Query("page_size") String page_size);


    /**
     * 更新租赁周期
     * 提交更新新的购物袋送达日期和返还日期
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("packages/time")
    Call<EditPakegeTimeBean>packages_time(@Body RequestBody requestBody);

    /**
     * 获取嗮图详情
     */
    @GET("find/detail")
    Call<FindDetailItemBean>find_detail(@Query("access_token") String access_token,
                                        @Query("id") String id);

    /**
     * 获取所有评价
     */
    @GET("find/comments")
    Call<CommentsAllBean>find_comments(@Query("access_token") String access_token,
                                       @Query("id") String id,
                                       @Query("page") String page,
                                       @Query("page_size") String page_size);
    /**
     * 获取我的嗮图详情
     */
    @GET("find/user")
    Call<MyCircleBean>find_user(@Query("access_token") String access_token,
                                @Query("id") String id,
                                @Query("page") String page,
                                @Query("page_size") String page_size);

    /**
     * 关注会员
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("find/follow")
    Call<BaseBean>find_follow(@Body RequestBody requestBody);

    /**
     * 取消关注会员
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @HTTP(method = "DELETE", path = "find/follow", hasBody = true)
    Call<BaseBean>find_follow_update(@Body RequestBody requestBody);

    /**
     * 点赞或者取消赞
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("find/like")
    Call<BaseBean>find_like(@Body RequestBody requestBody);

    /**
     * 创建晒图评论
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("find/comments")
    Call<BaseBean>find_comments(@Body RequestBody requestBody);

    /**
     * 消息中心
     * @param access_token
     * @return
     */
    @GET(GlobalConsts.MESSAGE_CENTER)
    Call<MessageBean>messageCenter(@Query("access_token")String access_token);

    /**
     * 消息列表
     * @param access_token
     * @param type
     * @param page
     * @param page_size
     * @return
     */
    @GET(GlobalConsts.MESSAGE_LIST)
    Call<MessageBean>messageList(@Query("access_token")String access_token,@Query("type")int type,@Query("page")int page,@Query("page_size")int page_size);

    /**
     * 发现列表
     * @param access_token
     * @param type
     * @param page
     * @param page_size
     * @return
     */
    @GET(GlobalConsts.FIND)
    Call<FindListBean>findList(@Query("access_token")String access_token,@Query("type")int type,@Query("page")int page,@Query("page_size")int page_size);

    /**
     * 提交生成用户押金认证订单
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/deposit-certificate")
    Call<YajinOrderBean>order_deposit_certificate(@Body RequestBody requestBody);


    /**
     * 微信支付预支付订单
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("order/wx-prepay")
    Call<WeixinPreBean>weixin_prepay(@Body RequestBody requestBody);

    /**
     * 发现图片上传
     */
    @Multipart
    @POST("common/upload")
    Call<PushImgBean>pushimg(@Part("file[]\"; filename=\"image.png\"")RequestBody img1,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img2,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img3,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img4,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img5,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img6,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img7,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img8,
                             @Part("file[]\"; filename=\"image.png\"")RequestBody img9);


    /**
     * 我的衣橱 - 此刻相伴+预定中
     */
    @GET("user/wardrobe")
    Call<UserWardrobeBean>user_wardrobe(@Query("access_token") String access_token,
                                        @Query("type") String type,
                                        @Query("page") String page,
                                        @Query("page_size") String page_size);

    /**
     * 我的衣橱 - 曾经拥有
     */
    @GET("user/history-wardrobe ")
    Call<OnceJsonBean>user_history_wardrobe (@Query("access_token") String access_token,
                                             @Query("page") String page,
                                             @Query("page_size") String page_size);


    /**
     *  还衣确认
     */
    @GET("order/return-confirm")
    Call<ReturnBean>order_return_confirm (@Query("access_token") String access_token, @Query("id") String id);

}

