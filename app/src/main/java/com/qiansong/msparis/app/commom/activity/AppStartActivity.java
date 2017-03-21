package com.qiansong.msparis.app.commom.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.VersionBean;
import com.qiansong.msparis.app.commom.util.ContentUtil;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.application.MyApplication;
import com.qiansong.msparis.app.commom.selfview.MiddleDialog;
import com.qiansong.msparis.app.commom.util.AndroidUtil;
import com.qiansong.msparis.app.commom.util.ApkUpdateUtils;
import com.qiansong.msparis.app.commom.util.BarTintManger;
import com.qiansong.msparis.app.commom.util.HttpServiceClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lizhaozhao on 2017/2/6.
 *
 * 启动页
 */

public class AppStartActivity extends BaseActivity {

    public static AppStartActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_appstart);
        BarTintManger.getBar(this);

        INSTANCE=this;
        //检测版本
        updateApp();
//        gotoMain();
    }

    private void gotoMain(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MyApplication.isFirst){
                        Intent intent = new Intent(INSTANCE, MainActivity.class);
                        startActivity(intent);
                        AppStartActivity.this.finish();
                }else {
                    Intent intent = new Intent(INSTANCE, GuideActivity.class);
                    startActivity(intent);
                    AppStartActivity.this.finish();
                }
            }
        }, 1500);
    }


    /**
     * 版本更新检测
     */
    private void updateApp(){

        HttpServiceClient.getInstance().version("android").enqueue(new Callback<VersionBean>() {
            @Override
            public void onResponse(Call<VersionBean> call, Response<VersionBean> response) {

                if(response.isSuccessful()){
                    if("ok".equals(response.body().getStatus())){

                        final VersionBean.DataEntity bean=response.body().getData();
                        if(!AndroidUtil.getVersionName(getApplicationContext()).equals(equals(bean.getVersion()))){

                            // 不是必须更新
                            if(0==bean.getForced_update()){

                                new MiddleDialog(INSTANCE, "发现新版本:" + bean.getVersion()+"\n是否立即下载?", new MiddleDialog.OnClickListener() {
                                    @Override
                                    public void onClick() {

                                        if (!canDownloadState()) {
                                            ContentUtil.makeToast(getApplicationContext(),"下载服务不可用,请您启用");
                                            showDownloadSetting();
                                            return;
                                        }
                                        if(bean.getUpdate_url()!=null){
                                            ApkUpdateUtils.download(getApplicationContext(), bean.getUpdate_url(), getResources().getString(R.string.app_name));
                                        }

                                    }

                                    @Override
                                    public void onUnClick() {
                                        gotoMain();
                                    }
                                });
                            }else {
                                // 必须更新
                                ApkUpdateUtils.download(getApplicationContext(), bean.getUpdate_url(), getResources().getString(R.string.app_name));
                            }
                        }

                    }else {
                        ContentUtil.makeToast(getApplicationContext(),response.body().getError().getMessage());
                    }
                }else {
                    gotoMain();
                }
            }

            @Override
            public void onFailure(Call<VersionBean> call, Throwable t) {
                gotoMain();
                ContentUtil.makeToast(getApplicationContext(),"网络错误!");
            }
        });

    }

    private void showDownloadSetting() {
        String packageName = "com.android.providers.downloads";
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        if (intentAvailable(intent)) {
            startActivity(intent);
        }
    }

    private boolean intentAvailable(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    private boolean canDownloadState() {
        try {
            int state = this.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");

            if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                    || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
