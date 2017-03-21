package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/2/23.
 * 登录
 */

public class LoginBean extends BaseBean {


    /**
     * data : {"access_token":"2aaac2951a766bf1865b781a12f6fcad","nickname":"小阿狸","head_portrait":"http://api.test.msparis.com/apidoc/img/male.png","mobile":"15000059840","new_user":"0"}
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
         * access_token : 2aaac2951a766bf1865b781a12f6fcad
         * nickname : 小阿狸
         * head_portrait : http://api.test.msparis.com/apidoc/img/male.png
         * mobile : 15000059840
         * new_user : 0
         */

        private String access_token;
        private String nickname;
        private String head_portrait;
        private String mobile;
        private String new_user;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getNew_user() {
            return new_user;
        }

        public void setNew_user(String new_user) {
            this.new_user = new_user;
        }
    }
}
