package com.qiansong.msparis.app.commom.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.qiansong.msparis.BaseActivity;
import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.util.GlobalConsts;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lizhaozhao on 2017/2/13.
 * <p>
 * 加载web view
 */

public class WebViewActivity extends BaseActivity {

    @InjectView(R.id.webview_title)
    TextView webviewTitle;
    @InjectView(R.id.back_btn)
    LinearLayout backBtn;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.webview_forward)
    TextView webviewForward;
    @InjectView(R.id.webview_goback)
    TextView webviewGoback;
    @InjectView(R.id.webview_reload)
    TextView webviewReload;

    private WebViewActivity INSTANCE;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);

        INSTANCE=this;

        url=getIntent().getStringExtra(GlobalConsts.INIT_DATA);
        setViews();
        setListeners();


    }


    private void setViews(){
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setBuiltInZoomControls(true);//设置支持两指缩放手势
        webSettings.setDisplayZoomControls(false);//隐藏缩放按钮
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        dialog.show();
        webview.loadUrl(url);
    }

    private void setListeners(){

        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    dialog.dismiss();
                }
                super.onProgressChanged(view, newProgress);
            }
        });


        //前进
        webviewForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.goForward();
            }
        });

        // 后退
        webviewGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.goBack();
            }
        });

        //刷新
        webviewReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
            }
        });
    }
}
