package com.qiansong.msparis.wxapi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.app.homepage.util.Eutil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 微信支付
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static String []datas;
    private WXPayEntryActivity INSTANCE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        INSTANCE=this;
        MyApplication.wxapi.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MyApplication.wxapi.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        Intent intent=new Intent();
        intent.setAction("com.qiansong.weixin_pay");
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                Eutil.makeLog("微信支付ok");
                intent.putExtra("status",true);
                sendBroadcast(intent);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                intent.putExtra("status",false);
                break;
            case BaseResp.ErrCode.ERR_COMM:
                intent.putExtra("status",false);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                intent.putExtra("status",false);
                finish();
                break;
            default:
                intent.putExtra("status",false);
                break;
        }
        sendBroadcast(intent);
    }


}
