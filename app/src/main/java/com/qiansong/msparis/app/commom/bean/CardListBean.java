package com.qiansong.msparis.app.commom.bean;


import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/20.
 */

public class CardListBean extends BaseBean{
    /**
     * data : {"total":"1","rows":[{"id":"1","order_number":"212212131","status":"1","product_num":"1","total_sale":"5","created_at":"2017-02-22 17:28:00","order_detail":[{"id":"1","cover_img":"1.jpeg","name":"女神卡","price":"50"}]}]}
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * total : 1
         * rows : [{"id":"1","order_number":"212212131","status":"1","product_num":"1","total_sale":"5","created_at":"2017-02-22 17:28:00","order_detail":[{"id":"1","cover_img":"1.jpeg","name":"女神卡","price":"50"}]}]
         */

        private String total;
        private List<RowsEntity> rows;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public static class RowsEntity {
            /**
             * id : 1
             * order_number : 212212131
             * status : 1
             * product_num : 1
             * total_sale : 5
             * created_at : 2017-02-22 17:28:00
             * order_detail : [{"id":"1","cover_img":"1.jpeg","name":"女神卡","price":"50"}]
             */

            private String id;
            private String order_number;
            private String status;
            private String product_num;
            private String total_sale;
            private String created_at;
            private List<OrderDetailEntity> order_detail;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getProduct_num() {
                return product_num;
            }

            public void setProduct_num(String product_num) {
                this.product_num = product_num;
            }

            public String getTotal_sale() {
                return total_sale;
            }

            public void setTotal_sale(String total_sale) {
                this.total_sale = total_sale;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public List<OrderDetailEntity> getOrder_detail() {
                return order_detail;
            }

            public void setOrder_detail(List<OrderDetailEntity> order_detail) {
                this.order_detail = order_detail;
            }

            public static class OrderDetailEntity {
                /**
                 * id : 1
                 * cover_img : 1.jpeg
                 * name : 女神卡
                 * price : 50
                 */

                private String id;
                private String cover_img;
                private String name;
                private String price;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCover_img() {
                    return cover_img;
                }

                public void setCover_img(String cover_img) {
                    this.cover_img = cover_img;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}
