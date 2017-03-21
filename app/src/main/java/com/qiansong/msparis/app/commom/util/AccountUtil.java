package com.qiansong.msparis.app.commom.util;

import android.content.Context;
import android.content.Intent;

import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.mine.activity.LoginActivity;


/**
 *  常用
 */
public class AccountUtil {
    /**
     * 静态字段加在这里 新手引导判断
     */
    //总的key 他是 "0"才能往下判断
   public static String ALL_KEY="ALL_KEY";
    //首页
    public static String HOMEPAGE_KEY="ALL_KEY";
    /**
     *  单页面引导页创建总KEY
     *  0，没点过  1,点过不弹了
     */
    public static void creatAllKey(){
        TXShareFileUtil.getInstance().putString(ALL_KEY,"0");
    }
    /**
     *  总KEY关闭
     *  0，没点过  1,点过不弹了
     */
    public static void offAllKey(){
        TXShareFileUtil.getInstance().putString(ALL_KEY,"1");
    }
    /**
     *  当前页面创建key value
     *  0，没点过  1,点过不弹了
     */
    public static void creatOneKey(String key,String value){
        TXShareFileUtil.getInstance().putString(key,value);
    }
    /**
     *  关闭1个
     *  0，没点过  1,点过不弹了
     */
    public static void offOneKey(String key){
        TXShareFileUtil.getInstance().putString(key,"1");
    }
    /**
     *  当前页面通过key 拿value
     *  0，没点过  1,点过不弹了
     */
    public static String getOneKey(String key){
        if("1".equals(TXShareFileUtil.getInstance().getString(ALL_KEY,"0"))){
            return "1";
        }
        return TXShareFileUtil.getInstance().getString(key,"0");
    }

    /**
     *  判断未登录跳转至登录页
     */
    public static boolean showLoginView(Context context){
        if(!MyApplication.isLogin){
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        return !MyApplication.isLogin;
    }

}
