package com.qiansong.msparis.app.mine.bean;

import com.google.gson.annotations.SerializedName;
import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/6.
 *
 * 历史优惠券
 */

public class OldCouponBean extends BaseBean {


    /**
     * data : {"rows":[{"name":"测试","use_limit":5,"discount":1,"type":1,"to_date":"2017-02-27","status":1,"description":"你猜"}],"total":1}
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
         * rows : [{"name":"测试","use_limit":5,"discount":1,"type":1,"to_date":"2017-02-27","status":1,"description":"你猜"}]
         * total : 1
         */

        private int total;
        private List<RowsBean> rows;

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
             * name : 测试
             * use_limit : 5
             * discount : 1
             * type : 1
             * to_date : 2017-02-27
             * status : 1
             * description : 你猜
             */

            private String name;
            private int use_limit;
            private int discount;
            private int type;
            private String to_date;
            @SerializedName("status")
            private int statusX;
            private String description;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getUse_limit() {
                return use_limit;
            }

            public void setUse_limit(int use_limit) {
                this.use_limit = use_limit;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTo_date() {
                return to_date;
            }

            public void setTo_date(String to_date) {
                this.to_date = to_date;
            }

            public int getStatusX() {
                return statusX;
            }

            public void setStatusX(int statusX) {
                this.statusX = statusX;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
