package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/23.
 *
 *  优惠券
 */

public class CouponBean extends BaseBean {


    /**
     * data : {"total":"1","rows":[{"name":"5元优惠券","to_date":"2017-05-30","type":"1","discount":"150","use_limit":"3000","description":"你猜"}]}
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
         * total : 1
         * rows : [{"name":"5元优惠券","to_date":"2017-05-30","type":"1","discount":"150","use_limit":"3000","description":"你猜"}]
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
             * name : 5元优惠券
             * to_date : 2017-05-30
             * type : 1
             * discount : 150
             * use_limit : 3000
             * description : 你猜
             */

            private String name;
            private String to_date;
            private String type;
            private String discount;
            private String use_limit;
            private String description;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTo_date() {
                return to_date;
            }

            public void setTo_date(String to_date) {
                this.to_date = to_date;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getUse_limit() {
                return use_limit;
            }

            public void setUse_limit(String use_limit) {
                this.use_limit = use_limit;
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
