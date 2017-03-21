package com.qiansong.msparis.app.homepage.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import com.google.gson.Gson;
import com.qiansong.msparis.app.commom.bean.BaseBean;
import com.qiansong.msparis.app.commom.bean.YajinOrderBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.homepage.view.sortlistview.CharacterParser;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.FollowBrandBean;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yechen on 2017/2/10.
 */

public class Eutil {
    /**
     * 我的专属log 内部用的大家都用的log...
     */
    public static void makeLog(String text){
        ContentUtil.makeLog("yc",text);
    }


    //变大再还原的动画
    public static void toBigThenSmallAnimation(View view){
        ObjectAnimator animatorbt = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f, 1f);
        ObjectAnimator animatorbts = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animatorbt).with(animatorbts);
        animSet.setDuration(350);
        animSet.start();

    }

    /**
     * 除法 保留2位小数
     */
    public static double division(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 购物车说明张开 动画
    public static Animation getScaleAnimation_see_all() {
        Animation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(200);
        return animation;
    }
    // 购物车说明收缩动画
    public static Animation getScaleAnimation_see_little() {
        Animation animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(200);
        return animation;
    }

    /**
     * 从小到大的动画
     */
    public static Animation getScaleAnimation_small_to_big() {
        Animation animation = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(150);
        return animation;
    }
    /**
     * 购物袋算总金额
     */
    public static int getMoney(int select_num){
        if (select_num<=3)
            return 0;
        return (select_num-3)*30;
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }
    // 将时间戳转为字符串
    public static String getStrTime2(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH时mm分ss秒");
        // 例如：
//        cc_time=1291778220 +"";
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime.split("  ")[0];
    }
    // 将时间戳转为字符串
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        // 例如：
//        cc_time=1291778220 +"";
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    //打印结果为： 2010年12月08日11时17分00秒
   //1291778220 2010年12月08日11时17分00秒  只精确到秒。

    private long timeStamp = System.currentTimeMillis();
    public String printTimeStamp(){
        return "TimeStamp: " + String.valueOf(timeStamp);
    }
    public String swapDateToStr(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return "Date: " + format.format(new Date(timeStamp));
    }
    /**
     * 名字转码ABC
     */
    public static String nameToAbc(String name) {
        CharacterParser characterParser = CharacterParser.getInstance();
        //汉字转换成拼音
        String pinyin = characterParser.getSelling(name);
        String sortString = pinyin.substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            return sortString.toUpperCase();
        }
        return "#";
    }

    /**
     * 高亮显示字符串中的部分字符 - 支持很多字符
     * todo textview直接设置返回值 textview.setText(getHighlight(...));
     * color eg:"#2e2e2e"
     */
    public static SpannableStringBuilder  getHighlight(String color,String content,String... key){
//        makeLog("content="+content);
        SpannableStringBuilder span = new SpannableStringBuilder(content);
        for (int m=0;m<key.length;m++) {
            int i = -1;
            if (key[m].length() > 0) while ((i = content.toUpperCase().indexOf(key[m], i + 1)) >= 0)
                span.setSpan(new ForegroundColorSpan(Color.parseColor(color)), i, i + key[m].length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            makeLog("key"+(i+1)+"="+key[m]);
        }
        return span;
    }



    /**
     * 更换运单号
     * @param order_return_id 还衣订单主键
     * @param express_no 新运单号
     */
    public static void editNumber(String order_return_id, String express_no, final Handler handler){
        Map<String,String> map=new Hashtable<>();
        map.put("access_token",MyApplication.token);
        map.put("order_return_id",order_return_id);
        map.put("express_no",express_no);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));

        HttpServiceClient.getInstance().order_returnbill(body).enqueue(new Callback<FollowBrandBean>() {
            @Override
            public void onResponse(Call<FollowBrandBean> call, Response<FollowBrandBean> response) {
                if (!response.isSuccessful()||!"ok".equals(response.body().getStatus()))return;
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(Call<FollowBrandBean> call, Throwable t) {

            }
        });
    }


    /**
     * 加入心愿单
     */
    public static void addWishList(String id){
        Map<String,String> map=new Hashtable<>();
        map.put("access_token",MyApplication.token);
        map.put("id",id);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
            HttpServiceClient.getInstance().to_wish(body).enqueue(new Callback<BaseBean>() {
                @Override
                public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                    if (response.isSuccessful() && "ok".equals(response.body().getStatus())) {
                        ContentUtil.makeToast(MyApplication.getApp(), "移入心愿单成功");
                    }
                }

                @Override
                public void onFailure(Call<BaseBean> call, Throwable t) {
                }
            });
    }

    /**
     * 创建押金订单
     */
    public static void creatYajinOrder(){
        Map<String,String> map=new Hashtable<>();
        map.put("access_token",MyApplication.token);
        map.put("channel","android");
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(map));
        HttpServiceClient.getInstance().order_deposit_certificate(body).enqueue(new Callback<YajinOrderBean>() {
            @Override
            public void onResponse(Call<YajinOrderBean> call, Response<YajinOrderBean> response) {
                if (response.isSuccessful()&&"ok".equals(response.body().getStatus())){
                    makeLog("订单id:"+response.body().getData().getId());
                }
            }

            @Override
            public void onFailure(Call<YajinOrderBean> call, Throwable t) {

            }
        });
    }

}

