package com.qiansong.msparis.app.wardrobe.bean;

/**
 * Created by lizhaozhao on 2017/2/16.
 *
 * 推荐对象
 */

public class RecommendBean {

    public String value;
    public boolean select=false;

    public RecommendBean(String value,boolean select){
        this.value=value;
        this.select=select;
    }
}
