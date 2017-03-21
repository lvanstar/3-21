package com.qiansong.msparis.app.commom.bean;


import java.io.Serializable;

/**
 * Created by lizhaozhao on 2017/2/14.
 *
 *  地址集合
 */

public class CityBean implements Serializable{


        private String key;
        private String name;
        private String pingyin;

    public CityBean(String key,String name,String pingyin){

        this.key=key;
        this.name=name;
        this.pingyin=pingyin;
    }

        public String getKey() {
            return key;
        }

        public void setId(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPingyin() {
            return pingyin;
        }

        public void setPingyin(String pingyin) {
            this.pingyin = pingyin;
        }

}
