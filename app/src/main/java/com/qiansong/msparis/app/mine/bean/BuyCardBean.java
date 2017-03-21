package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/16.
 *
 *  购买月卡
 */

public class BuyCardBean  extends BaseBean {


    /**
     * data : {"rows":[{"rows":[{"id":7,"cover_img":"","label":"","price":"0.00","market_price":"0.00"},{"id":12,"cover_img":"","label":"","price":"0.00","market_price":"0.00"},{"id":13,"cover_img":"","label":"","price":"100.00","market_price":"1000.00"}],"type":""}],"total":1}
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
         * rows : [{"rows":[{"id":7,"cover_img":"","label":"","price":"0.00","market_price":"0.00"},{"id":12,"cover_img":"","label":"","price":"0.00","market_price":"0.00"},{"id":13,"cover_img":"","label":"","price":"100.00","market_price":"1000.00"}],"type":""}]
         * total : 1
         */

        private int total;
        private List<RowsBeanX> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBeanX> getRows() {
            return rows;
        }

        public void setRows(List<RowsBeanX> rows) {
            this.rows = rows;
        }

        public static class RowsBeanX {
            /**
             * rows : [{"id":7,"cover_img":"","label":"","price":"0.00","market_price":"0.00"},{"id":12,"cover_img":"","label":"","price":"0.00","market_price":"0.00"},{"id":13,"cover_img":"","label":"","price":"100.00","market_price":"1000.00"}]
             * type :
             */

            private String type;
            private List<RowsBean> rows;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * id : 7
                 * cover_img :
                 * label :
                 * price : 0.00
                 * market_price : 0.00
                 */

                private int id;
                private String cover_img;
                private String label;
                private String price;
                private String market_price;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCover_img() {
                    return cover_img;
                }

                public void setCover_img(String cover_img) {
                    this.cover_img = cover_img;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }
            }
        }
    }
}
