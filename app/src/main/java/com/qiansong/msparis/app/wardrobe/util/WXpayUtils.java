package com.qiansong.msparis.app.wardrobe.util;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.util.Log;

import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.bean.WeixinPreBean;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.tencent.mm.sdk.modelpay.PayReq;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;



/**
 * Created by Administrator on 2015/10/12.
 */
public class WXpayUtils {

    PayReq req;
    StringBuffer sb;
    public static WeixinPreBean bean;
    public static  int key;
    Context context;

public WXpayUtils(Context context, WeixinPreBean bean){
    this.bean=bean;
    this.context=context;
}


    public  void pay(){

        req = new PayReq();
        sb=new StringBuffer();


        genPayReq();
        sendPayReq();
    }


    private void sendPayReq() {

       boolean is= MyApplication.wxapi.sendReq(req);
        Eutil.makeLog("boolean:"+is);
    }




    private void genPayReq() {

        req.appId = bean.getData().getAppid();
        req.partnerId = bean.getData().getPartnerid();
        req.prepayId = bean.getData().getPrepayid();
        req.packageValue = bean.getData().getPackageX();
        req.nonceStr = bean.getData().getNoncestr();
        req.timeStamp = String.valueOf(bean.getData().getTimestamp());
        req.sign=bean.getData().getSign();

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
        signParams.add(new BasicNameValuePair("sign", req.sign));
//        req.sign = genAppSign(signParams);
        sb.append("sign\n"+req.sign+"\n\n");


        Log.e("orion", signParams.toString());

    }

}
