package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/3/11.
 *  签到后 获取签到数据
 */

public class SignsRequestBean extends BaseBean{

    /**
     * data : {"bonus_points":10,"consecutive_day":1,"message":"连续签到5天后即可每天获得20分"}
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
         * bonus_points : 10
         * consecutive_day : 1
         * message : 连续签到5天后即可每天获得20分
         */

        private int bonus_points;
        private int consecutive_day;
        private String message;

        public int getBonus_points() {
            return bonus_points;
        }

        public void setBonus_points(int bonus_points) {
            this.bonus_points = bonus_points;
        }

        public int getConsecutive_day() {
            return consecutive_day;
        }

        public void setConsecutive_day(int consecutive_day) {
            this.consecutive_day = consecutive_day;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
