package com.qiansong.msparis.app.commom.bean;

import java.io.Serializable;


/**
 * Created by lizhaozhao on 16/4/21.
 * <p/>
 * 请求错误类
 */
public class ErrorBean implements Serializable {
    /**
     * status : error
     * error : {"code":"1012","error":"WRONG_PASSWORD","message":"密码错误!"}
     */

    public String code;
    public String error;
    public String message;



    public void setCode(String code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {

        return code;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
//        ContentUtil.makeLog("lzz", "code:"+code);
//        if("1012".equals(code)){
//            MyApplication.isLogin = false;
//            MyApplication.sf.edit().putBoolean(GlobalConsts.ISLOGIN, false).commit();
//            ContentUtil.makeLog("lzz","1012");
//        }
        return message;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "code='" + code + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
