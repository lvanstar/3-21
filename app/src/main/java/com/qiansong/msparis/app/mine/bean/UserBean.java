package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/24.
 *
 *  获取用户基本信息
 */

public class UserBean extends BaseBean {


    /**
     * data : {"nickname":"dsfsdfwfefe","head_portrait":"dsfsdfwfefe","fit_height":180,"dob":"1970-01-01","gender":"m","user_size":[{"id":1,"name":"XL","is_selected":1}]}
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
         * nickname : dsfsdfwfefe
         * head_portrait : dsfsdfwfefe
         * fit_height : 180
         * dob : 1970-01-01
         * gender : m
         * user_size : [{"id":1,"name":"XL","is_selected":1}]
         */

        private String nickname;
        private String head_portrait;
        private int fit_height;
        private String dob;
        private String gender;
        private List<UserSizeBean> user_size;

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

        public int getFit_height() {
            return fit_height;
        }

        public void setFit_height(int fit_height) {
            this.fit_height = fit_height;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public List<UserSizeBean> getUser_size() {
            return user_size;
        }

        public void setUser_size(List<UserSizeBean> user_size) {
            this.user_size = user_size;
        }

        public static class UserSizeBean {
            /**
             * id : 1
             * name : XL
             * is_selected : 1
             */

            private int id;
            private String name;
            private int is_selected;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIs_selected() {
                return is_selected;
            }

            public void setIs_selected(int is_selected) {
                this.is_selected = is_selected;
            }
        }
    }
}
