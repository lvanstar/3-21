package com.qiansong.msparis.app.mine.bean;


import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by kevin on 2017/3/7.
 * 第三方登录返回的数据
 */

public class ThirdBean extends BaseBean implements Serializable{
    /**
     * data : {"access_token":"0d6de0cf0dc234256e1e8789dbdb009d","level":1,"head_portrait":"http://api.test.msparis.com/apidoc/img/male.png","mobile":"15000059840"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * access_token : 0d6de0cf0dc234256e1e8789dbdb009d
         * level : 1
         * head_portrait : http://api.test.msparis.com/apidoc/img/male.png
         * mobile : 15000059840
         */

        private String access_token;
        private int level;
        private String head_portrait;
        private String mobile;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
