package com.qiansong.msparis.app.wardrobe.selfview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qiansong.msparis.R;
import com.qiansong.msparis.app.commom.bean.RentalMonitor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lizhaozhao on 2017/2/15.
 *
 * 购物袋选择日期与地址
 */

public class ShoppingBagView2 extends PopupWindow{


    private Context context;
    private LayoutInflater inflater = null;
    private View myMenuView,line;
    private OnClickListener listener;



    public ShoppingBagView2(Context context, RentalMonitor.DataEntity entity, OnClickListener listener){
        this.context=context;
        this.listener=listener;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myMenuView = inflater.inflate(R.layout.pop_shopping_bag, null);
        setPopup();

        setListeners();
    }





    private void setPopup(){

        // 设置AccessoryPopup的view
        this.setContentView(myMenuView);
        // 设置AccessoryPopup弹出窗体的宽度
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置AccessoryPopup弹出窗体的高度
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置AccessoryPopup弹出窗体可点击
        this.setFocusable(true);
//        this.setAnimationStyle(R.style.AnimTopBottom);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0x00000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context,0.5f);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.update();
        line=myMenuView.findViewById(R.id.pop_shoppingBag_line);
        float pix = context.getResources().getDisplayMetrics().density;
        Animation a = new TranslateAnimation(0, 0, (int) (161 * pix + 0.5f), 0);
        a.setDuration(150);
        a.setInterpolator(new LinearInterpolator());
        line.startAnimation(a);
        final Animation b = new TranslateAnimation(0, 0, 0, (int) (161 * pix + 0.5f));
        b.setDuration(150);
        b.setInterpolator(new LinearInterpolator());

//        mcv.setShowOtherDates(MaterialCalendarView.SHOW_ALL);











        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);

            }
        });

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


    private void setListeners(){


    }

    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {

        showAtLocation(view, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }





    public interface OnClickListener{
       void onAddress();
        void onDateOk(Date date);
    }
}
