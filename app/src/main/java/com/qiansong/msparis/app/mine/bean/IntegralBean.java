package com.qiansong.msparis.app.mine.bean;


import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/7.
 *
 *  积分明细
 */

public class IntegralBean  extends BaseBean {


    /**
     * data : {"points":90,"rows":[{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":40,"created_at":"2017-03-07 00:00:00"},{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":50,"created_at":"2017-03-07 00:00:00"},{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":60,"created_at":"2017-03-07 00:00:00"},{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":70,"created_at":"2017-03-07 00:00:00"}],"total":6}
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
         * points : 90
         * rows : [{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":40,"created_at":"2017-03-07 00:00:00"},{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":50,"created_at":"2017-03-07 00:00:00"},{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":60,"created_at":"2017-03-07 00:00:00"},{"remark":"连续签到1天","type":1,"bonus_points":10,"remain_points":70,"created_at":"2017-03-07 00:00:00"}]
         * total : 6
         */

        private int points;
        private int total;
        private List<RowsBean> rows;

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * remark : 连续签到1天
             * type : 1
             * bonus_points : 10
             * remain_points : 40
             * created_at : 2017-03-07 00:00:00
             */

            private String remark;
            private int type;
            private int bonus_points;
            private int remain_points;
            private String created_at;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getBonus_points() {
                return bonus_points;
            }

            public void setBonus_points(int bonus_points) {
                this.bonus_points = bonus_points;
            }

            public int getRemain_points() {
                return remain_points;
            }

            public void setRemain_points(int remain_points) {
                this.remain_points = remain_points;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}
