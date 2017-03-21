package com.qiansong.msparis.app.fulldress.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/1.
 *  预约时间
 */

public class LookingTimeBean extends BaseBean {

    /**
     * data : {"total":"5","rows":[{"time":"10:15","is_be_booked":1}]}
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
         * total : 5
         * rows : [{"time":"10:15","is_be_booked":1}]
         */

        private String total;
        private List<RowsBean> rows;

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
             * time : 10:15
             * is_be_booked : 1
             */

            private String time;
            private int is_be_booked;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getIs_be_booked() {
                return is_be_booked;
            }

            public void setIs_be_booked(int is_be_booked) {
                this.is_be_booked = is_be_booked;
            }
        }
    }
}
