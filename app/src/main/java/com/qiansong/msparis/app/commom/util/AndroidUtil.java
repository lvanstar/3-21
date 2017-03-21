package com.qiansong.msparis.app.commom.util;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * android 工具类
 * 
 * @author chao.liang
 * @date 2015.4.8
 * 
 */
public class AndroidUtil {
	/**
	 * WindowManager设置背景
	 * @param bgAlpha
	 */
	public  static void backgroundAlpha(float bgAlpha, Activity activity) {  
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();  
        lp.alpha = bgAlpha; // 0.0~1.0  
        activity.getWindow().setAttributes(lp);  
    }

	/**
	 * 关闭软键盘
	 * 
	 * @param act
	 */
	public static void hideSoftInput(final Activity act) {

		((InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(act.getWindow().peekDecorView()
						.getWindowToken(), 0);
	}

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param et
	 */
	public static void showSoftInput(Context context, final EditText et) {
		if (context == null || et == null) {
			return;
		}
		InputMethodManager imm = (InputMethodManager) et.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
	}

	/**
	 * 更具包名判断是否已经安装该app
	 */
	public static boolean isApkExit(Context context, String packageName) {
		try {
			context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
		} catch (NameNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取软件版本号
	 *
	 * @param context
	 * @return 版本号,如：100
	 */
	public static String getVersionCode(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packInfo.versionCode+"";
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取版本名
	 *
	 * @param context
	 * @return 版本名,如：1.0.0
	 */
	public static String getVersionName(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取屏幕管理类
	 *
	 * @return DisplayMetrics 屏幕管理对象
	 */
	public static DisplayMetrics getDisplayMetrics(Activity context) {
		DisplayMetrics displayMetrics = null;
		if (displayMetrics == null) {
			displayMetrics = new DisplayMetrics();
		}
		context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics;
	}
	
}
