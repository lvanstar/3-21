package com.qiansong.msparis.app.commom.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.qiansong.msparis.R;

/**
 * Created by Administrator on 2015/8/12.
 */
public class BarTintManger {

    public static void getBar(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true,context);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager((Activity) context);
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(context.getResources().getColor(R.color.white));
    }



    @TargetApi(19)
    private static void setTranslucentStatus(boolean on,Context context) {
        Window win = ((Activity) context).getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
