package com.qiansong.msparis.app.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.drawee.backends.pipeline.Fresco;

import com.qiansong.msparis.app.commom.bean.ConfigsBean;
import com.qiansong.msparis.app.commom.util.AccountUtil;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.commom.util.FileHelper;
import com.qiansong.msparis.app.commom.util.GlobalConsts;
import com.qiansong.msparis.app.commom.util.JsonUtil;
import com.qiansong.msparis.app.commom.util.TXShareFileUtil;
import com.qiansong.msparis.app.commom.util.UILImageLoader;

import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import com.qiyukf.unicorn.api.SavePowerConfig;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/4.
 *
 */

public class MyApplication extends MultiDexApplication implements AMapLocationListener{

    protected static MyApplication mInstance;
    public static boolean isTest=true;
    //判断是否第一次打开app
    public static boolean isFirst;
    //判断是否已登录
    public static boolean isLogin;
    //侧边栏
    public static List<String> azList;
    public static ConfigsBean configsBean;
    public static String token;
    public static String region_code="310100";//初始城市代码 默认上海市
    public static String cityName="上海市";//初始城市代码 默认上海市
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    public static AMapLocation aMapLocation;
    public static boolean isMessAGEONE; //系统消息
    public static boolean isMessAGETWO; //物流消息
    public static boolean isMessAGETHREE; //还衣消息


    // 微信
    public static IWXAPI wxapi;
    @Override
    public void onCreate() {
        super.onCreate();
        wxapi = WXAPIFactory.createWXAPI(getApplicationContext(), "wx5097f181a2ee5017",
                true);
        wxapi.registerApp("wx5097f181a2ee5017");

        Fresco.initialize(this);
        TXShareFileUtil.getInstance().init(getApplicationContext());
        //单页面新手引导初始化
        AccountUtil.creatAllKey();

        token=TXShareFileUtil.getInstance().getString(GlobalConsts.ACCESS_TOKEN,"token");
        isFirst=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.IS_FIRST,false);
        isLogin=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.IS_LOGIN,false);
        isMessAGEONE=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.MESSAGE_ONE,false);
        isMessAGETWO=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.MESSAGE_TWO,false);
        isMessAGETHREE=TXShareFileUtil.getInstance().getBoolean(GlobalConsts.MESSAGE_THREE,false);
        initConfigs();

        //单页面新手引导初始化
        AccountUtil.creatAllKey();

        /** SharedSDK 初始化**/
        ShareSDK.initSDK(this);

        /** 极光推送 初始化**/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //启动七鱼客服
        Unicorn.init(this, "9b4be529879ea383df089778dca15d06", options(), new UILImageLoader());
        azList=new ArrayList<String>();


        /**
         * 定位信息
         */
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            mLocationOption.setOnceLocation(true);//设置一次定位
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }

        //图片加载显示配置 （默认）
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);


//        configsBean= JsonUtil.fromJson(FileHelper.getFromAssets(getApplicationContext(),"congifs.txt"),ConfigsBean.class);
//
//        setEntity(configsBean);

    }


    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.savePowerConfig = new SavePowerConfig();
        return options;

    }

    private void initConfigs(){


        //第一次启动读取本地打包数据 之后获取每次保存的数据
        if(!MyApplication.isFirst){
            ContentUtil.makeLog("lzz","111111");
            configsBean= JsonUtil.fromJson(FileHelper.getFromAssets(getApplicationContext(),"congifs.txt"),ConfigsBean.class);
        }else {
            configsBean=getConfigsBean();
        }

        HttpServiceClient.getInstance().congigs(configsBean.getData().getBrand().getUpdated_at(),configsBean.getData().getProduct_filter().getUpdated_at()
                ,configsBean.getData().getSend_cities().getUpdated_at(),configsBean.getData().getBooking_cities().getUpdated_at(),configsBean.getData().getFilter_panel().getUpdated_at(),
                configsBean.getData().getSort().getUpdated_at(),configsBean.getData().getUser_size().getUpdated_at()).enqueue(new Callback<ConfigsBean>() {
            @Override
            public void onResponse(Call<ConfigsBean> call, Response<ConfigsBean> response) {
                if(response.isSuccessful()){

                    if("ok".equals(response.body().getStatus())){
                        ConfigsBean bean=response.body();
                        setEntity(bean);
                    }else {
                        ContentUtil.makeToast(getApplicationContext(),response.body().getError().getMessage());
                    }

                }else {
                    ContentUtil.makeLog("lzz","---------");
                    ContentUtil.makeLog("lzz","code:"+response.code());
                    setEntity(configsBean);
                }
            }

            @Override
            public void onFailure(Call<ConfigsBean> call, Throwable t) {
                ContentUtil.makeLog("lzz","0.0"+t.toString());
                setEntity(configsBean);
            }
        });

    }


    private void setEntity(ConfigsBean bean){
        if(bean.getData()!=null){

            TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_DATA,JsonUtil.toJson(bean));

            if(bean.getData().getBrand()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_BRAND,JsonUtil.toJson(bean.getData().getBrand()));
//                                fileHelper.writeToTxt(JsonUtil.toJson(bean.getData().getBrand()),fileHelper.FILE_BRAND);
            } if(bean.getData().getProduct_filter()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_PRODUCT,JsonUtil.toJson(bean.getData().getProduct_filter()));
//                                fileHelper.writeToTxt(JsonUtil.toJson(bean.getData().getProduct_filter()),fileHelper.FILE_PRODUCT);
            } if(bean.getData().getSend_cities()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_SEND,JsonUtil.toJson(bean.getData().getSend_cities()));
//                                fileHelper.writeToTxt(JsonUtil.toJson(bean.getData().getSend_cities()),fileHelper.FILE_SEND);
            }if(bean.getData().getBooking_cities()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_BOOKING,JsonUtil.toJson(bean.getData().getBooking_cities()));
//                                fileHelper.writeToTxt(JsonUtil.toJson(bean.getData().getBooking_cities()),fileHelper.FILE_BOOKING);
            }if(bean.getData().getFilter_panel()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_FILTER,JsonUtil.toJson(bean.getData().getFilter_panel()));
            }if(bean.getData().getSort()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_SORT,JsonUtil.toJson(bean.getData().getSort()));
            }if(bean.getData().getUser_size()!=null){
                TXShareFileUtil.getInstance().putString(GlobalConsts.FILE_SIZE,JsonUtil.toJson(bean.getData().getUser_size()));
            }

        }
    }


    /**
     * 获取配置文件信息
     */

    public static ConfigsBean getConfigsBean(){
        return JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_DATA,""),ConfigsBean.class);
    }


    /**
     * 获取品牌列表数据
     * @return
     */
    public static ConfigsBean.DataEntity.BrandEntity getBrandEntity(){

//        return  JsonUtil.fromJson(fileHelper.readSDFile(fileHelper.FILE_BRAND),ConfigsBean.DataEntity.BrandEntity.class);
        return  JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_BRAND,""),ConfigsBean.DataEntity.BrandEntity.class);

    }

    /**
     * 获取衣柜筛选条件数据
     * @return
     */
    public static ConfigsBean.DataEntity.ProductFilterEntity getProductEntity(){

//        ContentUtil.makeLog("lzz","--------"+TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_PRODUCT,""));
//        return  JsonUtil.fromJson(fileHelper.readSDFile(fileHelper.FILE_PRODUCT),ConfigsBean.DataEntity.ProductFilterEntity.class);
        return  JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_PRODUCT,""),ConfigsBean.DataEntity.ProductFilterEntity.class);

    }

    /**
     * 获取送货城市数据
     * @return
     */
    public static ConfigsBean.DataEntity.SendCitiesEntityX getSendEntity(){

//        return  JsonUtil.fromJson(fileHelper.readSDFile(fileHelper.FILE_SEND),ConfigsBean.DataEntity.SendCitiesEntityX.class);
        return  JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_SEND,""),ConfigsBean.DataEntity.SendCitiesEntityX.class);

    }

    /**
     * 获取预约城市数据
     * @return
     */
    public static ConfigsBean.DataEntity.BookingCitiesEntityX getBookingEntity(){

//        return  JsonUtil.fromJson(fileHelper.readSDFile(fileHelper.FILE_BOOKING),ConfigsBean.DataEntity.BookingCitiesEntityX.class);
        return  JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_BOOKING,""),ConfigsBean.DataEntity.BookingCitiesEntityX.class);

    }

    /**
     * 获取排序集合
     */
    public static ConfigsBean.DataEntity.FilterPanelEntity getFilterEntity(){

        return JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_FILTER,""), ConfigsBean.DataEntity.FilterPanelEntity.class);
    }

    /**
     * 获取sort集合
     */
    public static ConfigsBean.DataEntity.SortEntity getSortEntity(){
        return  JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_SORT,""),ConfigsBean.DataEntity.SortEntity.class);
    }

    /**
     * 获取用户尺寸集合
     */
    public static ConfigsBean.DataEntity.UserSizeEntity getUserSizeEntity(){
        return JsonUtil.fromJson(TXShareFileUtil.getInstance().getString(GlobalConsts.FILE_SIZE,""),ConfigsBean.DataEntity.UserSizeEntity.class);
    }

    public MyApplication() {
        mInstance = this;
    }

    public static MyApplication getApp() {
        if (mInstance != null && mInstance instanceof MyApplication) {
            return mInstance;
        } else {
            mInstance = new MyApplication();
            mInstance.onCreate();
            return mInstance;
        }
    }

    /**
     * 定位回调
     * @param aMapLocation
     */

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

        if(aMapLocation!=null){
            if(aMapLocation.getErrorCode()==0){
                ContentUtil.makeLog("lzz","cityName:"+aMapLocation.getCity());
                if(aMapLocation.getCity()!=null){

                    if(getSendEntity().getSend_cities()!=null){
                        List<ConfigsBean.DataEntity.SendCitiesEntityX.SendCitiesEntity> entity=getSendEntity().getSend_cities();

                        for (int i=0;i<entity.size();i++){

                            for (int j=0;j<entity.get(i).getCities().size();j++){
                                if(aMapLocation.getCity().equals(entity.get(i).getCities().get(j).getName())){
                                    MyApplication.region_code=entity.get(i).getCities().get(j).getKey();
                                    MyApplication.cityName=entity.get(i).getCities().get(j).getName();
                                    ContentUtil.makeLog("lzz","code:"+entity.get(i).getCities().get(j).getKey());
                                }
                            }

                        }
                    }
                }
                // 定位成功返回
            }else {

                MyApplication.region_code="310100";
                MyApplication.cityName="上海市";
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

    }



    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
