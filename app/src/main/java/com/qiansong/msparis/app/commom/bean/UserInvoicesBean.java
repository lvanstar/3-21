package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 * 开票记录
 */

public class UserInvoicesBean {

    /**
     * status : ok
     * data : {"total":"1","rows":[{"invoice_amount":"1","status":"1","created_at":"2017-02-22 18::35:00"}]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 1
         * rows : [{"invoice_amount":"1","status":"1","created_at":"2017-02-22 18::35:00"}]
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
             * invoice_amount : 1
             * status : 1
             * created_at : 2017-02-22 18::35:00
             */

            private String invoice_amount;
            private String status;
            private String created_at;

            public String getInvoice_amount() {
                return invoice_amount;
            }

            public void setInvoice_amount(String invoice_amount) {
                this.invoice_amount = invoice_amount;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
