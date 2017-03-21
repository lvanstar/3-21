package com.qiansong.msparis.app.commom.bean;


import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/10.
 *
 *  租赁订单列表
 */

public class OrderListBean extends BaseBean{
    /**
     * data : {"rows":[{"id":1,"order_no":"1234567890123456","status":"1","order_detail":[{"id":1,"image_url":"http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg","brand_name":"品牌名称品牌名称","price":"500.00","specification":"XS","product_mode":"日常装","name":"日常服装名称"}],"clothing_lattice_num":1,"clothing_lattice_price":50,"total_sale":50,"amount_shipping":50,"product_num":1,"lease_period":"2017/1/12-2017/1/14","pay_method":"wxpay"}],"total":1}
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
         * rows : [{"id":1,"order_no":"1234567890123456","status":"1","order_detail":[{"id":1,"image_url":"http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg","brand_name":"品牌名称品牌名称","price":"500.00","specification":"XS","product_mode":"日常装","name":"日常服装名称"}],"clothing_lattice_num":1,"clothing_lattice_price":50,"total_sale":50,"amount_shipping":50,"product_num":1,"lease_period":"2017/1/12-2017/1/14","pay_method":"wxpay"}]
         * total : 1
         */

        private int total;
        private List<RowsEntity> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
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
             * order_no : 1234567890123456
             * status : 1
             * order_detail : [{"id":1,"image_url":"http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg","brand_name":"品牌名称品牌名称","price":"500.00","specification":"XS","product_mode":"日常装","name":"日常服装名称"}]
             * clothing_lattice_num : 1
             * clothing_lattice_price : 50
             * total_sale : 50
             * amount_shipping : 50
             * product_num : 1
             * lease_period : 2017/1/12-2017/1/14
             * pay_method : wxpay
             */

            private int id;
            private String order_no;
            private String status;
            private int clothing_lattice_num;
            private int clothing_lattice_price;
            private int total_sale;
            private int amount_shipping;
            private int product_num;
            private String lease_period;
            private String pay_method;
            private List<OrderDetailEntity> order_detail;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getClothing_lattice_num() {
                return clothing_lattice_num;
            }

            public void setClothing_lattice_num(int clothing_lattice_num) {
                this.clothing_lattice_num = clothing_lattice_num;
            }

            public int getClothing_lattice_price() {
                return clothing_lattice_price;
            }

            public void setClothing_lattice_price(int clothing_lattice_price) {
                this.clothing_lattice_price = clothing_lattice_price;
            }

            public int getTotal_sale() {
                return total_sale;
            }

            public void setTotal_sale(int total_sale) {
                this.total_sale = total_sale;
            }

            public int getAmount_shipping() {
                return amount_shipping;
            }

            public void setAmount_shipping(int amount_shipping) {
                this.amount_shipping = amount_shipping;
            }

            public int getProduct_num() {
                return product_num;
            }

            public void setProduct_num(int product_num) {
                this.product_num = product_num;
            }

            public String getLease_period() {
                return lease_period;
            }

            public void setLease_period(String lease_period) {
                this.lease_period = lease_period;
            }

            public String getPay_method() {
                return pay_method;
            }

            public void setPay_method(String pay_method) {
                this.pay_method = pay_method;
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
                 * image_url : http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg
                 * brand_name : 品牌名称品牌名称
                 * price : 500.00
                 * specification : XS
                 * product_mode : 日常装
                 * name : 日常服装名称
                 */

                private int id;
                private String image_url;
                private String brand_name;
                private String price;
                private String specification;
                private String product_mode;
                private String name;

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

                public String getBrand_name() {
                    return brand_name;
                }

                public void setBrand_name(String brand_name) {
                    this.brand_name = brand_name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getSpecification() {
                    return specification;
                }

                public void setSpecification(String specification) {
                    this.specification = specification;
                }

                public String getProduct_mode() {
                    return product_mode;
                }

                public void setProduct_mode(String product_mode) {
                    this.product_mode = product_mode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
