package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/2/24.
 *  绑定手机号码
 */

public class BoundPhoneBean extends BaseBean {


    /**
     * data : {"access_token":"dsfsdfwfefe","new_user":"0","head_portrait":"1.jpeg","level":"0"}
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
         * access_token : dsfsdfwfefe
         * new_user : 0
         * head_portrait : 1.jpeg
         * level : 0
         */

        private String access_token;
        private String new_user;
        private String head_portrait;
        private String level;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getNew_user() {
            return new_user;
        }

        public void setNew_user(String new_user) {
            this.new_user = new_user;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
