package com.qiansong.msparis.app.commom.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.AliBean;
import com.qiansong.msparis.app.commom.bean.WeixinPreBean;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.qiansong.msparis.app.wardrobe.util.PayResult;
import com.qiansong.msparis.app.wardrobe.util.WXpayUtils;

import java.util.Hashtable;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yechen on 2017/3/17.
 * 支付帮助类
 * type 1.女神卡订单 2.押金认证订单 3.商品订单
 */

public class PayHelp {
    /**
     * 支付用来更改操作的handler
     * handler.sendEmptyMessage();  0,失败  1,成功
     */
     private static Handler handler;

    /**
     * 单例对象实例
     */
    private static PayHelp instance = null;

    public synchronized static PayHelp getInstance() {
        if (instance == null) {
            instance = new PayHelp();
        }
        return instance;
    }
    public void alipay(final Activity activity, String type, String id, final Handler handler){
        this.handler=handler;
        Map<String, Object> map = new Hashtable<>();
        map.put("access_token", MyApplication.token);
        map.put("type", type);
        map.put("id", id);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        HttpServiceClient.getInstance().alipay_sign(body).enqueue(new Callback<AliBean>() {
            @Override
            public void onResponse(Call<AliBean> call, final Response<AliBean> response) {
                if (response.isSuccessful() && "ok".equals(response.body().getStatus())) {
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(activity);
                            Map<String, String> result = alipay.payV2(response.body().getData().getOrder_spec(), true);
                            Log.i("yc", result.toString());
                            PayResult payResult = new PayResult(result);
                            String resultStatus = payResult.getResultStatus();
                            if (TextUtils.equals(resultStatus, "9000")) {
                                handler.sendEmptyMessage(1);
                            } else {
                                handler.sendEmptyMessage(0);
                            }
                        }
                    };

                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }
            }

            @Override
            public void onFailure(Call<AliBean> call, Throwable t) {

            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 微信支付
     * @param activity
     * @param type 1.女神卡订单 2.押金认证订单 3.商品订单
     * @param id  订单id
     * @param handler  提供外部操作
     */
    public void weixin_pay(final Activity activity, String type, String id, Handler handler){
        this.handler=handler;
        Map<String, Object> map = new Hashtable<>();
        map.put("access_token", MyApplication.token);
        map.put("type", type);
        map.put("id", id);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));

        HttpServiceClient.getInstance().weixin_prepay(body).enqueue(new Callback<WeixinPreBean>() {
            @Override
            public void onResponse(Call<WeixinPreBean> call, Response<WeixinPreBean> response) {
                if (response.isSuccessful() && "ok".equals(response.body().getStatus())) {
                    Eutil.makeLog("微信预支付我们接口调用成功");
                    new WXpayUtils(activity, response.body()).pay();
                }
            }

            @Override
            public void onFailure(Call<WeixinPreBean> call, Throwable t) {

            }
        });
    }

    /**
     * 微信支付成功会有广播过来
     * handler.sendEmptyMessage();  0,失败  1,成功
     */
    public static class BroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (handler!=null){
              if (true==intent.getBooleanExtra("status",false)) {
                  handler.sendEmptyMessage(1);
              }else if (false==intent.getBooleanExtra("status",false)){
                  handler.sendEmptyMessage(0);
              }
            }
        }
    }

}
