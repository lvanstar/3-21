/**
 *
 */
package com.qiansong.msparis.app.commom.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.qiansong.msparis.R;

import org.apache.http.protocol.HttpService;


/**
 * dialog视图
 */
public class DialogUtil {

    /**
     * 菊花转
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {
        final AnimationDrawable animationDrawable;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loaddialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        final ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.text);// 提示文字

        spaceshipImage.setImageResource(R.drawable.progress_round);
        animationDrawable= (AnimationDrawable) spaceshipImage.getDrawable();

        // 加载动画
        // 使用ImageView显示动画
        tipTextView.setText(msg);// 设置加载信息
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialogs);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局  
        loadingDialog.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    animationDrawable.stop();
//                    animationDrawable.stop();
                    //此处把dialog dismiss掉，然后把本身的activity finish掉
                    //   BarcodeActivity.this.finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        loadingDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                animationDrawable.start();
//                spaceshipImage.startAnimation(hyperspaceJumpAnimation);
            }
        });
        return loadingDialog;


    }


    /**
     * 中间提示框
     * @param context
     * @param msg
     * @param closeOnEnd
     * @return
     */
    public static Dialog show(final Context context, String msg, final boolean closeOnEnd) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View v = inflater.inflate(R.layout.dialog, null);

        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.dialog_rl);
        TextView tv = (TextView) v.findViewById(R.id.dialog_tv);
        tv.setText(msg);
        final Dialog dialog = new Dialog(context, R.style.loading_dialogs);
        dialog.setContentView(rl, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        Animation a = AnimationUtils.loadAnimation(context, R.anim.animation_fade);
        v.startAnimation(a);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                v.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                if (closeOnEnd) ((Activity) context).finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        return dialog;
    }



}
