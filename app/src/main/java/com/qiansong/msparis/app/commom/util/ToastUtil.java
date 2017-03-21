package com.qiansong.msparis.app.commom.util;

import android.content.Context;
import android.widget.Toast;

import com.qiansong.msparis.app.application.MyApplication;


/**
 * 提示工具类，方便以后自定义
 *
 * @author chao.liang
 *
 * @date 2015年10月8日
 */
public class ToastUtil {

	private static Toast mToast;
	public static TPrompt tPrompt;
	protected static long lastClickTime;


	/**
	 * 提示内容来自资源文件
	 * 
	 * @param resId
	 */
	public static void showMessage(int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(MyApplication.getApp().getApplicationContext(), resId, Toast.LENGTH_LONG);
		} else {
			mToast.setText(resId);
			mToast.setDuration(Toast.LENGTH_LONG);
		}

		mToast.show();
	}

	/**
	 * 提示内容来自字符串
	 * 
	 * @param
	 */
	public static void showMessage(String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(MyApplication.getApp().getApplicationContext(), msg, Toast.LENGTH_LONG);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_LONG);
		}

		mToast.show();
	}
	
	public static void showMessage(String msg,int time) {
		if (mToast == null) {
			mToast = Toast.makeText(MyApplication.getApp().getApplicationContext(), msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}

		mToast.show();
	}

	public static void showAnimToast(String msg) {
		if (!isFastDoubleClick()) {
			if (tPrompt == null) {
				tPrompt = new TPrompt(MyApplication.getApp().getApplicationContext());
			}
			tPrompt.showToast(msg);
		}
	}

	protected static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 1000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

}
