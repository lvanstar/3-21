package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/28.
 */

public class UseDetailsBean extends BaseBean {


    /**
     * data : {"rows":[{"remark":"会员续费","reward_day":"+15天","created_at":"2016-12-13 12:31"}],"name":"318元 18天体验卡","total":"1"}
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
         * rows : [{"remark":"会员续费","reward_day":"+15天","created_at":"2016-12-13 12:31"}]
         * name : 318元 18天体验卡
         * total : 1
         */

        private String name;
        private String total;
        private List<RowsBean> rows;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
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
             * remark : 会员续费
             * reward_day : +15天
             * created_at : 2016-12-13 12:31
             */

            private String remark;
            private String reward_day;
            private String created_at;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getReward_day() {
                return reward_day;
            }

            public void setReward_day(String reward_day) {
                this.reward_day = reward_day;
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
