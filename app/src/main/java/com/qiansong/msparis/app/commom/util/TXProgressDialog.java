package com.qiansong.msparis.app.commom.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.qiansong.msparis.R;


/**
 * 自已定义loading框
 * 
 * @author peng.zhang
 * @date 2015.3.31
 * 
 */
public class TXProgressDialog extends Dialog {

	private static TXProgressDialog customProgressDialog = null;

	public TXProgressDialog(Context context) {
		super(context);
	}

	public TXProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static TXProgressDialog createDialog(Context context) {
		customProgressDialog = new TXProgressDialog(context, R.style.TXCustomProgressDialog);
		customProgressDialog.setContentView(R.layout.loading_dialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (customProgressDialog == null) {
			return;
		}
		// ImageView imageView = (ImageView)
		// customProgressDialog.findViewById(R.id.loadingImageView);
		// AnimationDrawable animationDrawable = (AnimationDrawable)
		// imageView.getBackground();
		// animationDrawable.start();
	}

	/**
	 * 
	 * [Summary] setTitile 标题
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public TXProgressDialog setTitile(String strTitle) {
		return customProgressDialog;
	}

	/**
	 * 
	 * [Summary] setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public TXProgressDialog setMessage(String strMessage) {
		TextView txtMsg = (TextView) customProgressDialog.getWindow().findViewById(R.id.txtMsg);
		if (txtMsg != null) {
			txtMsg.setText(strMessage);
		}
		return customProgressDialog;
	}
}
