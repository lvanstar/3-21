package com.qiansong.msparis.app.commom.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.qiansong.msparis.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by lizhaozhao on 2017/2/13.
 *
 * 分享util
 */

public class ShareUtil {

    public static PopupWindow popupWindow;

    public static View share(final Context context, View line){



        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.share, null);
        popupWindow = new PopupWindow(view, FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context,0.5f);
        popupWindow.update();
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(line, Gravity.BOTTOM, 0, 0);

        final View mainView = view.findViewById(R.id.share_view);
        final LinearLayout share_main= (LinearLayout) view.findViewById(R.id.share_main);
//        share_main.getBackground().setAlpha(230);
        final float pix = context.getResources().getDisplayMetrics().density;
        Animation a = new TranslateAnimation(0, 0, (int) (161 * pix + 0.5f), 0);
        a.setDuration(150);
        a.setInterpolator(new LinearInterpolator());
        mainView.startAnimation(a);
        final Animation b = new TranslateAnimation(0, 0, 0, (int) (161 * pix + 0.5f));
        b.setDuration(150);
        b.setInterpolator(new LinearInterpolator());
        b.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                popupWindow.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);

            }
        });

        // 取消
        view.findViewById(R.id.share_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        /**
         * 微信好友
         */
        view.findViewById(R.id.share_wxfriend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Wechat.ShareParams shareParams=new Wechat.ShareParams();
                shareParams.setTitle("测试信息");
                shareParams.setImageUrl("http://static.msparis.com/image/product/F/W/FW022W-1.jpg");
                shareParams.setUrl("http://www.baidu.com");
                Platform wxhy=ShareSDK.getPlatform(Wechat.NAME);

                wxhy.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                        ContentUtil.makeToast(context,"分享成功");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ContentUtil.makeToast(context,"分享失败");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ContentUtil.makeToast(context,"分享取消");
                    }
                });
                wxhy.share(shareParams);
            }
        });

        /**
         * 微信朋友圈
         */
        view.findViewById(R.id.share_pyq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Wechat.ShareParams shareParams=new Wechat.ShareParams();
                shareParams.setTitle("测试信息");
                shareParams.setImageUrl("http://static.msparis.com/image/product/F/W/FW022W-1.jpg");
                shareParams.setUrl("http://www.baidu.com");
                Platform wxpyq=ShareSDK.getPlatform(WechatMoments.NAME);

                wxpyq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                        ContentUtil.makeToast(context,"分享成功");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ContentUtil.makeToast(context,"分享失败");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ContentUtil.makeToast(context,"分享取消");
                    }
                });

                wxpyq.share(shareParams);
            }
        });

        /**
         * 微博
         */
        view.findViewById(R.id.share_sinaweibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SinaWeibo.ShareParams shareParams=new SinaWeibo.ShareParams();
                shareParams.setText("测试信息"+"http://www.baidu.com");
                shareParams.setImageUrl("http://static.msparis.com/image/product/F/W/FW022W-1.jpg");//图片url
                Platform weibo= ShareSDK.getPlatform(SinaWeibo.NAME);

                weibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                        ContentUtil.makeToast(context,"分享成功");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        ContentUtil.makeToast(context,"分享失败");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        ContentUtil.makeToast(context,"分享取消");
                    }
                });

                weibo.share(shareParams);

                popupWindow.dismiss();

            }
        });
        return view;
    }


    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
