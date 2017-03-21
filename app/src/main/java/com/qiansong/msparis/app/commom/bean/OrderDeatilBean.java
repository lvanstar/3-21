package com.qiansong.msparis.app.commom.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/10.
 *
 *  商品详情
 */

public class OrderDeatilBean extends BaseBean implements Serializable{

    /**
     * data : {"id":1,"order_no":"1234567890123456","status":"1","cancel_reason":"不想买了","shipping_name":"李益雄","shipping_address":"上海普陀区长征镇怒江北路598号4楼5-1号","shipping_mobile":"18516503389","rental_date":"2017/1/12-2017/1/14","order_detail":[{"id":1,"image_url":"http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg","brand_name":"品牌名称品牌名称","price":"500.00","specification":"XS","product_mode":"日常装","name":"日常服装名称","status":1}],"clothing_lattice_num":1,"clothing_lattice_price":50,"is_pay_longtime":1,"remark":1,"pay_method":"wxpay","amount_price":50,"longtime_price":50,"amount_deposit":50,"discount_price":50,"membership_discount_price":50,"total_sale":50,"amount_shipping":50,"created_at":"2017/1/12-2017/1/14","is_comment":0,"remaining_payment_time":0}
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        /**
         * id : 1
         * order_no : 1234567890123456
         * status : 1
         * cancel_reason : 不想买了
         * shipping_name : 李益雄
         * shipping_address : 上海普陀区长征镇怒江北路598号4楼5-1号
         * shipping_mobile : 18516503389
         * rental_date : 2017/1/12-2017/1/14
         * order_detail : [{"id":1,"image_url":"http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg","brand_name":"品牌名称品牌名称","price":"500.00","specification":"XS","product_mode":"日常装","name":"日常服装名称","status":1}]
         * clothing_lattice_num : 1
         * clothing_lattice_price : 50
         * is_pay_longtime : 1
         * remark : 1
         * pay_method : wxpay
         * amount_price : 50
         * longtime_price : 50
         * amount_deposit : 50
         * discount_price : 50
         * membership_discount_price : 50
         * total_sale : 50
         * amount_shipping : 50
         * created_at : 2017/1/12-2017/1/14
         * is_comment : 0
         * remaining_payment_time : 0
         */

        private int id;
        private String order_no;
        private String status;
        private String cancel_reason;
        private String shipping_name;
        private String shipping_address;
        private String shipping_mobile;
        private String rental_date;
        private int clothing_lattice_num;
        private int clothing_lattice_price;
        private int is_pay_longtime;
        private String remark;
        private String pay_method;
        private int amount_price;
        private int longtime_price;
        private int amount_deposit;
        private int discount_price;
        private int membership_discount_price;
        private int total_sale;
        private int amount_shipping;
        private String created_at;
        private int is_comment;
        private int remaining_payment_time;
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

        public String getCancel_reason() {
            return cancel_reason;
        }

        public void setCancel_reason(String cancel_reason) {
            this.cancel_reason = cancel_reason;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
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

        public String getRental_date() {
            return rental_date;
        }

        public void setRental_date(String rental_date) {
            this.rental_date = rental_date;
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

        public int getIs_pay_longtime() {
            return is_pay_longtime;
        }

        public void setIs_pay_longtime(int is_pay_longtime) {
            this.is_pay_longtime = is_pay_longtime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPay_method() {
            return pay_method;
        }

        public void setPay_method(String pay_method) {
            this.pay_method = pay_method;
        }

        public int getAmount_price() {
            return amount_price;
        }

        public void setAmount_price(int amount_price) {
            this.amount_price = amount_price;
        }

        public int getLongtime_price() {
            return longtime_price;
        }

        public void setLongtime_price(int longtime_price) {
            this.longtime_price = longtime_price;
        }

        public int getAmount_deposit() {
            return amount_deposit;
        }

        public void setAmount_deposit(int amount_deposit) {
            this.amount_deposit = amount_deposit;
        }

        public int getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(int discount_price) {
            this.discount_price = discount_price;
        }

        public int getMembership_discount_price() {
            return membership_discount_price;
        }

        public void setMembership_discount_price(int membership_discount_price) {
            this.membership_discount_price = membership_discount_price;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public int getRemaining_payment_time() {
            return remaining_payment_time;
        }

        public void setRemaining_payment_time(int remaining_payment_time) {
            this.remaining_payment_time = remaining_payment_time;
        }

        public List<OrderDetailEntity> getOrder_detail() {
            return order_detail;
        }

        public void setOrder_detail(List<OrderDetailEntity> order_detail) {
            this.order_detail = order_detail;
        }

        public static class OrderDetailEntity implements Serializable{
            /**
             * id : 1
             * image_url : http://n.sinaimg.cn/news/transform/20170310/fB-e-fychhuq3606919.jpg
             * brand_name : 品牌名称品牌名称
             * price : 500.00
             * specification : XS
             * product_mode : 日常装
             * name : 日常服装名称
             * status : 1
             */

            private int id;
            private String image_url;
            private String brand_name;
            private String price;
            private String specification;
            private String product_mode;
            private String name;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
