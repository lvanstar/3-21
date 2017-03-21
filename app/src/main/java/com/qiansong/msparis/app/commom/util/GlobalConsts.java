package com.qiansong.msparis.app.commom.util;

/**
 * Created by lizhaozhao on 2017/2/4.
 *
 * 常量类
 */

public class GlobalConsts {
    public static final String  ACCESS_TOKEN = "access_token";
    public static final String  USER_MOBILE = "";
    public static final String  IS_LOGIN = "is_Login";
    public static final String  IS_FIRST = "is_First";

    /** 地址添加或者修改**/
    public static final String ADDRESS_INTENT="address";
//    public static final String ADDRESS_TYPE="address_type";
//    public static final String ADDRESS_TYPE_ADD="address_type_add";
//    public static final String ADDRESS_TYPE_UPDATE="address_type_update";

    /** 女生卡列表到女神卡详情 **/
    public static  final String  MEMBERCARD_ID = "card_id";

    /** 登录时状态 1微信 2QQ 3新浪 **/
    public static  final int  LOGIN_WEIXIN = 1;
    public static  final int  LOGIN_QQ = 2;
    public static  final int  LOGIN_WEIBO = 3;

    /**  内存中保存历史记录**/
    public  static final String SEARCH_HISTORY = "search_history";
    public  static final String  INIT_DATA= "data";
    public  static final String  POSTION="position";
    public  static final String  VALUE="value";
    public  static final String  FILE_DATA= "file_data";
    public  static final String  FILE_DATAS= "file_datas";
    public  static final String  FILE_DATASS= "file_datass";
    public  static final String  FILE_BRAND= "brand";
    public  static final String  FILE_PRODUCT= "product";
    public  static final String  FILE_SEND= "send";
    public  static final String  FILE_BOOKING= "booking";
    public  static final String  FILE_FILTER= "filter";
    public  static final String  FILE_SORT= "sort";
    public  static final String  FILE_SIZE= "size";
    public  static final String  MESSAGE_ONE= "message_one";
    public  static final String  MESSAGE_TWO= "message_two";
    public  static final String  MESSAGE_THREE= "message_three";

/****************************************************************************************/
    /** 微博 **/
    public static  final String WEB_KEY="534531857";
    public static  final String WEB_SECRET="11535cb2f71d4849e6eaf4724dbb3d41";

    /** QQ **/
    public static  final String QQ_KEY="1105380677";
    public static  final String QQ_SECRET="EXSeLSDYIZPjcwxM";

    /** 微信 **/
    public static  final String WXKEY="wx5097f181a2ee5017";
    public static  final String WX_SECRET="29dadd75182a99f27e46decbb313ce23";

/***************************************************************************************/


    /** uri**/
    public static final String URL = "http://api.test.msparis.com/";
    /** 版本更新**/
    public static final String VERSION="common/version";
    /** 版本更新**/
    public static final String HOME="home";
    /** 基本配置**/
    public static final String CONFIGS="common/configs";
    /** 商品列表**/
    public static final String PRODUCT_LIAT="product/filter";
    /** 商品详情**/
    public static final String PRODUCT="product";
    /** 标签商品列表**/
    public static final String PRODUCT_TAGS="product/tags";
    /** 商品评论列表**/
    public static final String PRODUCT_COMMENTS="product/comments";
    /** 收藏／取消 商品**/
    public static final String WISH="user/wish";

    /** 获取商品可借周期 **/
    public static final String SCHEDULE="product/schedule";

    /** 获取购物袋可借周期 **/
    public static final String PACKAGE_SCHEDULE="packages/schedule";

    /** 获取购物袋商品剩余数量s **/
    public static final String PACKAGE_SIZE="packages/amount";

    /** 获取短信验证码 **/
    public static final String SMS ="common/sms";
    /** 获取语音验证码 **/
    public static final String VOICE ="common/voice";
    /** 登录 **/
    public static final String LOGIN ="user/login";
    /** 第三方登录 **/
    public static final String THIRDPARTY ="member/thirdParty";
    /** 绑定手机号 **/
    public static final String MOBILE ="Member/mobile";
    /** 获取用户基本信息 和修改用户信息   **/
    public static final String USER ="user/show";
    /** 所有特权   **/
    public static final String USER_PRIVILEGE ="user/member-privilege";
    /** 个人数据  **/
    public static final String GETUSER ="user";
    /** 会员中心数据  **/
    public static final String CENTRE ="user/member-centre";
    /** 用户地址 **/
    public static final String ADDRESS ="user/address";
    /**  我的钱包  **/
    public static final String WALLET ="user/wallet";
    /**  获取女神卡列表  **/
    public static final String CARD ="card";
    /**  获取女神卡详情  **/
    public static final String CARD_DETAIL ="card/detail";
    /**  获取我的押金列表  **/
    public static final String DEPOSIT ="user/order-deposit";
    /**  获取我的优惠券  **/
    public static final String COUPON ="user/coupon";
    /**  获取历史优惠券  **/
    public static final String COUPON_HISTORY ="user/coupon-history";
    /**  积分明细  **/
//    public static final String COUPON ="user/coupon";
    /**  我的女神卡  **/
    public static final String USER_CARD ="user/cards";
    /**  我的女神卡使用明细  **/
    public static final String CARDS ="user/cards";

    /**  激活女神卡  **/
    public static final String ACTIVATION="user/activate-card";
    /**  获取我的订单  **/
    public static final String ORDERS ="user/orders";
    /**  获取我的购物袋列表**/
    public static final String PACKAGE ="package";
    /**  加入购物袋**/
    public static final String PACKAGE_ADD ="/packages/product";

    /**  获取预约时间  **/
    public static final String BOOKING_TIME ="booking/time";
    /** 创建预约  取消预约 和  预约详情 **/
    public static final String BOOKING_INFO ="booking/info";
    /** 获取预约历史  **/
    public static final String BOOKING ="booking";

    /** 根据关键字搜索 **/
    public static final String KEYWORD ="product/keyword";
    /** 根据关键字搜索 **/
    public static final String KEY_AUTOCOMPLETE ="product/autocomplete";

    /** 我的订单 - 租赁订单列表 **/
    public static final String USER_ORDERS="user/orders";

    /** 我的订单 - 租赁订单详情 **/
    public static final String USER_ORDER_DEATIL="user/order-detail";

    /** 我的订单 - 女神卡列表 **/
    public static final String USER_CRAD_LIST="user/card-orders";

    /** 评价 **/
    public static final String ORDER_COMMENT="order/comment";

    /** 上传图片 **/
    public static final String PUSH_IMAGE ="common/upload";

    /** 消息中心 **/
    public static final String MESSAGE_CENTER ="common/message-center";

    /** 消息列表 **/
    public static final String MESSAGE_LIST ="common/message-list";

    /** 发现列表**/
    public static final String FIND="find";

}
