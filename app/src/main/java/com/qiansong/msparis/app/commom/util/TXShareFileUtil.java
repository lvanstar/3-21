/**
 * 
 */
package com.qiansong.msparis.app.commom.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * SharedPreferences工具类，生命周期与application同在，免去每次修改数据后commit的麻烦
 * 
 * @author hui.wang
 * @date 2015.3.16
 * 
 */
public class TXShareFileUtil {
	private static TXShareFileUtil shareFileUtil;

	private SharedPreferences preferences;

	private TXShareFileUtil() {

	}

	public void init(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public static TXShareFileUtil getInstance() {
		if (shareFileUtil == null) {
			shareFileUtil = new TXShareFileUtil();
		}
		return shareFileUtil;
	}

	/**
	 * 从SharePreferences中获取字符串
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public  String getString(String key, String defValue) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		return preferences.getString(key, defValue);
	}

	/**
	 * 从SharePreferences中获取整数
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public int getInt(String key, int defValue) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		return preferences.getInt(key, defValue);
	}

	/**
	 * 从SharePreferences中获取长整数
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public long getLong(String key, long defValue) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		return preferences.getLong(key, defValue);
	}

	/**
	 * 从SharePreferences中获取符点数
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public float getFloat(String key, float defValue) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		return preferences.getFloat(key, defValue);
	}

	/**
	 * 从SharePreferences中获取布尔值
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public boolean getBoolean(String key, boolean defValue) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		return preferences.getBoolean(key, defValue);
	}

	/**
	 * 设置字符串到ShareProferences
	 * 
	 * @param key
	 * @param value
	 */
	public void putString(String key, String value) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		preferences.edit().putString(key, value).commit();
	}

	/**
	 * 设置整数到ShareProferences
	 * 
	 * @param key
	 * @param value
	 */
	public void putInt(String key, int value) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		preferences.edit().putInt(key, value).commit();
	}

	/**
	 * 设置长整数到ShareProferences
	 * 
	 * @param key
	 * @param value
	 */
	public void putLong(String key, long value) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		preferences.edit().putLong(key, value).commit();
	}

	/**
	 * 设置符点数到ShareProferences
	 * 
	 * @param key
	 * @param value
	 */
	public void putFloat(String key, float value) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		preferences.edit().putFloat(key, value).commit();
	}

	/**
	 * 设置布尔值到ShareProferences
	 * 
	 * @param key
	 * @param value
	 */
	public void putBoolean(String key, boolean value) {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		preferences.edit().putBoolean(key, value).commit();
	}

	/**
	 * 通过key删除数据
	 *
	 * @param key
	 */
	public void removeData(String  key,Context context){
		SharedPreferences.Editor sharedata = context.getSharedPreferences(FILENAME, 0).edit();
		sharedata.remove(key).commit();
	}

	/**
	 * 清除SharePreferences中的数据
	 */
	public void clear() {
		if (preferences == null) {
			throw new NullPointerException(
					"should call init() before any other method!!!");
		}
		preferences.edit().clear().commit();
	}

	/**
	 * desc:保存对象

	 * @param context
	 * @param key
	 * @param obj 要保存的对象，只能保存实现了serializable的对象
	 * modified:
	 */
	public static void saveObject(Context context,String key ,Object obj){
		try {
			// 保存对象
			SharedPreferences.Editor sharedata = context.getSharedPreferences(FILENAME, 0).edit();
			//先将序列化结果写到byte缓存中，其实就分配一个内存空间
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			ObjectOutputStream os=new ObjectOutputStream(bos);
			//将对象序列化写入byte缓存
			os.writeObject(obj);
			//将序列化的数据转为16进制保存
			String bytesToHexString = bytesToHexString(bos.toByteArray());
			//保存该16进制数组
			sharedata.putString(key, bytesToHexString);
			sharedata.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * desc:将数组转为16进制
	 * @param bArray
	 * @return
	 * modified:
	 */
	public static String bytesToHexString(byte[] bArray) {
		if(bArray == null){
			return null;
		}
		if(bArray.length == 0){
			return "";
		}
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	/**
	 * desc:获取保存的Object对象
	 * @param context
	 * @param key
	 * @return
	 * modified:
	 */
	public static Object readObject(Context context,String key ){
		try {
			SharedPreferences sharedata = context.getSharedPreferences(FILENAME, 0);
			if (sharedata.contains(key)) {
				String string = sharedata.getString(key, "");
				if(TextUtils.isEmpty(string)){
					return null;
				}else{
					//将16进制的数据转为数组，准备反序列化
					byte[] stringToBytes = StringToBytes(string);
					ByteArrayInputStream bis=new ByteArrayInputStream(stringToBytes);
					ObjectInputStream is=new ObjectInputStream(bis);
					//返回反序列化得到的对象
					Object readObject = is.readObject();
					return readObject;
				}
			}
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//所有异常返回null
		return null;

	}
	/**
	 * desc:将16进制的数据转为数组
	 * @param data
	 * @return
	 * modified:
	 */
	public static byte[] StringToBytes(String data){
		String hexString=data.toUpperCase().trim();
		if (hexString.length()%2!=0) {
			return null;
		}
		byte[] retData=new byte[hexString.length()/2];
		for(int i=0;i<hexString.length();i++)
		{
			int int_ch;  // 两位16进制数转化后的10进制数
			char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
			int int_ch1;
			if(hex_char1 >= '0' && hex_char1 <='9')
				int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
			else if(hex_char1 >= 'A' && hex_char1 <='F')
				int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
			else
				return null;
			i++;
			char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
			int int_ch2;
			if(hex_char2 >= '0' && hex_char2 <='9')
				int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
			else if(hex_char2 >= 'A' && hex_char2 <='F')
				int_ch2 = hex_char2-55; //// A 的Ascll - 65
			else
				return null;
			int_ch = int_ch1+int_ch2;
			retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
		}
		return retData;
	}

}
