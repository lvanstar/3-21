package com.qiansong.msparis.app.wardrobe.selfview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qiansong.msparis.R;


/**
 * Created by yechen on 2015/12/10.
 */
public class MenuDialogYe extends Dialog {
   public ButtonClickListener listener_b;

    public MenuDialogYe(final Context context, int theme, int resource, final String phone, ButtonClickListener listener) {
        super(context, theme);
        View view = View.inflate(context, resource, null);
        this.listener_b=listener;
        setContentView(view);
        setCancelable(true);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
       // window.setWindowAnimations(R.anim.menu_dialog_in); //添加动画
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        if (!"".equals(phone)){
//         TextView view_text = (TextView) view.findViewById(R.id.number_Tv);
//         view_text.setText(phone);
        }
        if (view.findViewById(R.id.ye_1)!=null) {
            view.findViewById(R.id.ye_1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener_b.onButtonClick(0);
                    MenuDialogYe.this.dismiss();
                }
            });
        }
        if (view.findViewById(R.id.ye_2)!=null) {
            findViewById(R.id.ye_2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener_b.onButtonClick(1);
                    MenuDialogYe.this.dismiss();
                }
            });
        }
        if (view.findViewById(R.id.ye_3)!=null) {
            findViewById(R.id.ye_3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener_b.onButtonClick(2);
                    MenuDialogYe.this.dismiss();
                }
            });
        }
        if (view.findViewById(R.id.ye_4)!=null) {
            findViewById(R.id.ye_4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener_b.onButtonClick(3);
                    MenuDialogYe.this.dismiss();
                }
            });
        }
        if (view.findViewById(R.id.ye_5)!=null) {
            findViewById(R.id.ye_5).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener_b.onButtonClick(4);
                    MenuDialogYe.this.dismiss();
                }
            });
        }
        if (view.findViewById(R.id.ye_6)!=null) {
            findViewById(R.id.ye_6).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener_b.onButtonClick(5);
                    MenuDialogYe.this.dismiss();
                }
            });
        }

    }
//    public void setButtonClickListener(ButtonClickListener listener){
//        this.listener = listener;
//
//    }
    public interface ButtonClickListener {
        public void onButtonClick(int i);
    }
}
