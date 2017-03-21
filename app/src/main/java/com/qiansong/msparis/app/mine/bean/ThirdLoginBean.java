package com.qiansong.msparis.app.mine.bean;

import java.io.Serializable;

/**
 * Created by kevin on 2017/3/7.
 * 第三方登录返回的数据  用来绑定手机号的
 */

public class ThirdLoginBean implements Serializable{

    public  String  open_id ;
    public  String  type ;
    public  String  nickname;
    public  String  gender;
    public  String  head_portrait;

    @Override
    public String toString() {
        return "ThirdLoginBean{" +
                "open_id='" + open_id + '\'' +
                ", type='" + type + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", head_portrait='" + head_portrait + '\'' +
                '}';
    }
}
