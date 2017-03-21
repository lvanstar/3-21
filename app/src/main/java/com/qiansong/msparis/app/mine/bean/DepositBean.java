package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/26.
 */

public class DepositBean extends BaseBean {


    /**
     * data : {"total":"1","rows":[{"id":"1","pay_method":"wxpay","card_no":"221212121","order_no":"2212123131","pay_amount":"300.00","paid_at":"2017-02-22 15:30:00","status":"1","type":"order"}]}
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
         * rows : [{"id":"1","pay_method":"wxpay","card_no":"221212121","order_no":"2212123131","pay_amount":"300.00","paid_at":"2017-02-22 15:30:00","status":"1","type":"order"}]
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
             * id : 1
             * pay_method : wxpay
             * card_no : 221212121
             * order_no : 2212123131
             * pay_amount : 300.00
             * paid_at : 2017-02-22 15:30:00
             * status : 1
             * type : order
             */

            private String id;
            private String pay_method;
            private String card_no;
            private String order_no;
            private String pay_amount;
            private String paid_at;
            private String status;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPay_method() {
                return pay_method;
            }

            public void setPay_method(String pay_method) {
                this.pay_method = pay_method;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public String getPaid_at() {
                return paid_at;
            }

            public void setPaid_at(String paid_at) {
                this.paid_at = paid_at;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
