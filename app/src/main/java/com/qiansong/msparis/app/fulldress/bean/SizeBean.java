package com.qiansong.msparis.app.fulldress.bean;

/**
 * Created by yechen on 2017/2/9.
 */

public class SizeBean {
    public String size;
    public boolean select=false;//默认未选中
    public boolean isOut; //表示是否缺少该尺码

    public SizeBean(String size,boolean isOut) {
        this.size = size;
        this.isOut=isOut;
    }

    public SizeBean(String size) {
        this.size = size;
    }
    public SizeBean() {
    }
}
