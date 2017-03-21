package com.qiansong.msparis.app.wardrobe.selfview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.qiansong.msparis.R;


/**
 * Created by lizhaozhao on 2017/2/10.
 *
 * 由中间从上往下弹出的视图
 */

public class TopMiddlePopup extends PopupWindow{

    private Context context;
    private LayoutInflater inflater = null;
    private View myMenuView;
    public static ImageView Riv,VIPiv,Titleiv;


    private onClickListener listener;

    public TopMiddlePopup(Context context,ImageView iv,onClickListener listener){

        this.context=context;
        this.listener=listener;
        this.Titleiv=iv;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myMenuView = inflater.inflate(R.layout.top_popup, null);

        setPopup();

        setListeners();

    }


    private void setListeners(){

        myMenuView.findViewById(R.id.wardrobe_R_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Riv.setVisibility(View.VISIBLE);
                    VIPiv.setVisibility(View.GONE);
                listener.onButtonR();
            }
        });

        myMenuView.findViewById(R.id.wardrobe_VIP_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Riv.setVisibility(View.GONE);
                VIPiv.setVisibility(View.VISIBLE);
                listener.onButtonVIP();
            }
        });
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
        this.setAnimationStyle(R.style.AnimTopMiddle);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((Activity) context,0.5f);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
                Titleiv.setImageResource(R.mipmap.wardrobe_up);

            }
        });
        Riv= (ImageView) myMenuView.findViewById(R.id.wardrobe_select_iv01);
        VIPiv= (ImageView) myMenuView.findViewById(R.id.wardrobe_select_iv02);

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


    /**
     * 显示弹窗界面
     *
     * @param view
     */
    public void show(View view) {

        showAsDropDown(view, 0, 0);
    }

    public interface onClickListener{

        void onButtonR();
        void onButtonVIP();
    }
}
