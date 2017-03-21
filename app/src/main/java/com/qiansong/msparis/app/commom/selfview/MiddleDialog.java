package com.qiansong.msparis.app.commom.selfview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiansong.msparis.R;


/**
 * Created by lizhaozhao on 2017/2/28.
 *
 *  中间弹出dialog
 */

public class MiddleDialog extends Dialog{

    private OnClickListener listener;

    public MiddleDialog(Context context,String value, final OnClickListener listener) {
        super(context, R.style.registDialog);
        View view = View.inflate(context, R.layout.dialog_middle, null);
        setContentView(view);
        setCancelable(false);
        this.listener=listener;
        ((TextView) view.findViewById(R.id.middle_tv)).setText(value);    //设置对话框内容
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUnClick();
                dismiss();
            }
        });
         view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 listener.onClick();
                 dismiss();
             }
         });
        this.show();

    }



    public interface OnClickListener{
        void onClick();
        void onUnClick();
    }
}
