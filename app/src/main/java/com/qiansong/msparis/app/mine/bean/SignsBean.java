package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/11.
 *  获取签到数据
 */

public class SignsBean extends BaseBean{

    /**
     * data : {"points":30,"is_sign":"1","consecutive_day":1,"user_signs":["2017-03-07"]}
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
         * points : 30
         * is_sign : 1
         * consecutive_day : 1
         * user_signs : ["2017-03-07"]
         */

        private int points;
        private int is_sign;
        private int consecutive_day;
        private List<String> user_signs;

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getConsecutive_day() {
            return consecutive_day;
        }

        public void setConsecutive_day(int consecutive_day) {
            this.consecutive_day = consecutive_day;
        }

        public List<String> getUser_signs() {
            return user_signs;
        }

        public void setUser_signs(List<String> user_signs) {
            this.user_signs = user_signs;
        }
    }
}
