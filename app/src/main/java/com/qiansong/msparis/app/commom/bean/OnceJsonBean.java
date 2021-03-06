package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 * 曾经拥有
 */

public class OnceJsonBean {

    /**
     * status : ok
     * data : {"rows":[{"id":1,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS  S  M  L  XL  均码","market_price":0,"sale_price":12000,"rental_price":0,"max_rental_days":0,"product_mode":1,"base_discount":2.5,"type_id":1,"enabled":0,"dots":0},{"id":2,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":0,"product_mode":1,"base_discount":2.5,"type_id":2,"enabled":1,"dots":2},{"id":3,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS  S  M  L  XL  均码","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":3,"product_mode":2,"status":2,"base_discount":0,"type_id":1,"enabled":1,"dots":3}],"total":1}
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
         * rows : [{"id":1,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS  S  M  L  XL  均码","market_price":0,"sale_price":12000,"rental_price":0,"max_rental_days":0,"product_mode":1,"base_discount":2.5,"type_id":1,"enabled":0,"dots":0},{"id":2,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":0,"product_mode":1,"base_discount":2.5,"type_id":2,"enabled":1,"dots":2},{"id":3,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS  S  M  L  XL  均码","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":3,"product_mode":2,"status":2,"base_discount":0,"type_id":1,"enabled":1,"dots":3}]
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
             * id : 1
             * image_url : https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58
             * product_brand : 品牌名称
             * specification : XS  S  M  L  XL  均码
             * market_price : 0
             * sale_price : 12000
             * rental_price : 0
             * max_rental_days : 0
             * product_mode : 1
             * base_discount : 2.5
             * type_id : 1
             * enabled : 0
             * dots : 0
             * status : 2
             */

            private int id;
            private String image_url;
            private String product_brand;
            private String specification;
            private int market_price;
            private int sale_price;
            private int rental_price;
            private int max_rental_days;
            private int product_mode;
            private double base_discount;
            private int type_id;
            private int enabled;
            private int dots;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getProduct_brand() {
                return product_brand;
            }

            public void setProduct_brand(String product_brand) {
                this.product_brand = product_brand;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public int getMarket_price() {
                return market_price;
            }

            public void setMarket_price(int market_price) {
                this.market_price = market_price;
            }

            public int getSale_price() {
                return sale_price;
            }

            public void setSale_price(int sale_price) {
                this.sale_price = sale_price;
            }

            public int getRental_price() {
                return rental_price;
            }

            public void setRental_price(int rental_price) {
                this.rental_price = rental_price;
            }

            public int getMax_rental_days() {
                return max_rental_days;
            }

            public void setMax_rental_days(int max_rental_days) {
                this.max_rental_days = max_rental_days;
            }

            public int getProduct_mode() {
                return product_mode;
            }

            public void setProduct_mode(int product_mode) {
                this.product_mode = product_mode;
            }

            public double getBase_discount() {
                return base_discount;
            }

            public void setBase_discount(double base_discount) {
                this.base_discount = base_discount;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public int getEnabled() {
                return enabled;
            }

            public void setEnabled(int enabled) {
                this.enabled = enabled;
            }

            public int getDots() {
                return dots;
            }

            public void setDots(int dots) {
                this.dots = dots;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
