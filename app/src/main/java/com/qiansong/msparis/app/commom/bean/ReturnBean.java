package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 * 还衣确认
 */

public class ReturnBean {


    /**
     * status : ok
     * data : {"id":1,"shipping_name":"某某某","shipping_region_name":"上海上海市黄浦区","shipping_address":"新闸路831号","shipping_mobile":"185****8456","courier_company":["顺丰","中通"],"pickup_date":["2017-03-17","2017-03-18","2017-03-19"],"pickup_time":[["10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"],["08:00~09:00","09:00~10:00","10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"],["08:00~09:00","09:00~10:00","10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"]],"data":"2017-03-20","remaining_day":3,"bag_barcode":"0000000000","is_overdue":0,"is_comment":0,"product":[{"id":1,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS  S  M  L  XL  均码","market_price":0,"sale_price":0,"rental_price":10000,"max_rental_days":0,"product_mode":1,"status":1,"base_discount":0,"type_id":1,"enabled":1,"dots":3},{"id":2,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":0,"product_mode":1,"status":5,"base_discount":2.5,"type_id":2,"enabled":1,"dots":2},{"id":3,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":0,"product_mode":1,"status":2,"base_discount":0,"type_id":1,"enabled":1,"dots":3}]}
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
         * id : 1
         * shipping_name : 某某某
         * shipping_region_name : 上海上海市黄浦区
         * shipping_address : 新闸路831号
         * shipping_mobile : 185****8456
         * courier_company : ["顺丰","中通"]
         * pickup_date : ["2017-03-17","2017-03-18","2017-03-19"]
         * pickup_time : [["10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"],["08:00~09:00","09:00~10:00","10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"],["08:00~09:00","09:00~10:00","10:00~11:00","11:00~12:00","12:00~13:00","13:00~14:00","14:00~15:00","15:00~16:00","16:00~17:00"]]
         * data : 2017-03-20
         * remaining_day : 3
         * bag_barcode : 0000000000
         * is_overdue : 0
         * is_comment : 0
         * product : [{"id":1,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS  S  M  L  XL  均码","market_price":0,"sale_price":0,"rental_price":10000,"max_rental_days":0,"product_mode":1,"status":1,"base_discount":0,"type_id":1,"enabled":1,"dots":3},{"id":2,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":0,"product_mode":1,"status":5,"base_discount":2.5,"type_id":2,"enabled":1,"dots":2},{"id":3,"image_url":"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58","product_brand":"品牌名称","specification":"XS","market_price":12000,"sale_price":12000,"rental_price":10000,"max_rental_days":0,"product_mode":1,"status":2,"base_discount":0,"type_id":1,"enabled":1,"dots":3}]
         */

        private int id;
        private String shipping_name;
        private String shipping_region_name;
        private String shipping_address;
        private String shipping_mobile;
        private String data;
        private int remaining_day;
        private String bag_barcode;
        private int is_overdue;
        private int is_comment;
        private List<String> courier_company;
        private List<String> pickup_date;
        private List<List<String>> pickup_time;
        private List<ProductBean> product;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public String getShipping_region_name() {
            return shipping_region_name;
        }

        public void setShipping_region_name(String shipping_region_name) {
            this.shipping_region_name = shipping_region_name;
        }

        public String getShipping_address() {
            return shipping_address;
        }

        public void setShipping_address(String shipping_address) {
            this.shipping_address = shipping_address;
        }

        public String getShipping_mobile() {
            return shipping_mobile;
        }

        public void setShipping_mobile(String shipping_mobile) {
            this.shipping_mobile = shipping_mobile;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getRemaining_day() {
            return remaining_day;
        }

        public void setRemaining_day(int remaining_day) {
            this.remaining_day = remaining_day;
        }

        public String getBag_barcode() {
            return bag_barcode;
        }

        public void setBag_barcode(String bag_barcode) {
            this.bag_barcode = bag_barcode;
        }

        public int getIs_overdue() {
            return is_overdue;
        }

        public void setIs_overdue(int is_overdue) {
            this.is_overdue = is_overdue;
        }

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public List<String> getCourier_company() {
            return courier_company;
        }

        public void setCourier_company(List<String> courier_company) {
            this.courier_company = courier_company;
        }

        public List<String> getPickup_date() {
            return pickup_date;
        }

        public void setPickup_date(List<String> pickup_date) {
            this.pickup_date = pickup_date;
        }

        public List<List<String>> getPickup_time() {
            return pickup_time;
        }

        public void setPickup_time(List<List<String>> pickup_time) {
            this.pickup_time = pickup_time;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * id : 1
             * image_url : https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1854029884,2529185896&fm=58
             * product_brand : 品牌名称
             * specification : XS  S  M  L  XL  均码
             * market_price : 0
             * sale_price : 0
             * rental_price : 10000
             * max_rental_days : 0
             * product_mode : 1
             * status : 1
             * base_discount : 0
             * type_id : 1
             * enabled : 1
             * dots : 3
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
            private int status;
            private float base_discount;
            private int type_id;
            private int enabled;
            private int dots;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public float getBase_discount() {
                return base_discount;
            }

            public void setBase_discount(int base_discount) {
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
        }
    }
}
