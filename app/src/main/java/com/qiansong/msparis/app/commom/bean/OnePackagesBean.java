package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 * 一个购物袋
 */

public class OnePackagesBean {

    /**
     * status : ok
     * data : {"package_id":"1","selected_icon":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","unselected_icon":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","user_card_paused":"active","delivery_region":"310100","express_time":"1","rental_expiry_date":1505836800,"delivery_date":1487520000,"send_back_date":1488124800,"backage_grid_limit":"6","init_grid_qty":"3","free_grid_qty":"3","grid_price":"3000","can_order_limit":"2","is_over_order_limit":false,"is_rush":false,"is_try_card":false,"can_buy_vip":true,"can_edit_date":true,"can_edit_address":true,"can_buy_longtime":true,"items_count":"2","package_items":[{"id":"1","specification":"1","name":"清雅紫丁香真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":false},{"id":"2","specification":"2","name":"高贵珍珠白真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":true}]}
     */

    private String status;
    public DataBean data;

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
         * package_id : 1
         * selected_icon : http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240
         * unselected_icon : http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240
         * user_card_paused : active
         * delivery_region : 310100
         * express_time : 1
         * rental_expiry_date : 1505836800
         * delivery_date : 1487520000
         * send_back_date : 1488124800
         * backage_grid_limit : 6
         * init_grid_qty : 3
         * free_grid_qty : 3
         * grid_price : 3000
         * can_order_limit : 2
         * is_over_order_limit : false
         * is_rush : false
         * is_try_card : false
         * can_buy_vip : true
         * can_edit_date : true
         * can_edit_address : true
         * can_buy_longtime : true
         * items_count : 2
         * package_items : [{"id":"1","specification":"1","name":"清雅紫丁香真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":false},{"id":"2","specification":"2","name":"高贵珍珠白真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":true}]
         */

        private String package_id;
        private String selected_icon;
        private String unselected_icon;
        private String user_card_paused;
        private String delivery_region;
        private String express_time;
        private int rental_expiry_date;
        private int delivery_date;
        private int send_back_date;
        private String backage_grid_limit;
        private String init_grid_qty;
        private String free_grid_qty;
        private String grid_price;
        private String can_order_limit;
        private boolean is_over_order_limit;
        private boolean is_rush;
        private boolean is_try_card;
        private boolean can_buy_vip;
        private boolean can_edit_date;
        private boolean can_edit_address;
        private boolean can_buy_longtime;
        private String items_count;
        public List<PackageItemsBean> package_items;

        public String getPackage_id() {
            return package_id;
        }

        public void setPackage_id(String package_id) {
            this.package_id = package_id;
        }

        public String getSelected_icon() {
            return selected_icon;
        }

        public void setSelected_icon(String selected_icon) {
            this.selected_icon = selected_icon;
        }

        public String getUnselected_icon() {
            return unselected_icon;
        }

        public void setUnselected_icon(String unselected_icon) {
            this.unselected_icon = unselected_icon;
        }

        public String getUser_card_paused() {
            return user_card_paused;
        }

        public void setUser_card_paused(String user_card_paused) {
            this.user_card_paused = user_card_paused;
        }

        public String getDelivery_region() {
            return delivery_region;
        }

        public void setDelivery_region(String delivery_region) {
            this.delivery_region = delivery_region;
        }

        public String getExpress_time() {
            return express_time;
        }

        public void setExpress_time(String express_time) {
            this.express_time = express_time;
        }

        public int getRental_expiry_date() {
            return rental_expiry_date;
        }

        public void setRental_expiry_date(int rental_expiry_date) {
            this.rental_expiry_date = rental_expiry_date;
        }

        public int getDelivery_date() {
            return delivery_date;
        }

        public void setDelivery_date(int delivery_date) {
            this.delivery_date = delivery_date;
        }

        public int getSend_back_date() {
            return send_back_date;
        }

        public void setSend_back_date(int send_back_date) {
            this.send_back_date = send_back_date;
        }

        public String getBackage_grid_limit() {
            return backage_grid_limit;
        }

        public void setBackage_grid_limit(String backage_grid_limit) {
            this.backage_grid_limit = backage_grid_limit;
        }

        public String getInit_grid_qty() {
            return init_grid_qty;
        }

        public void setInit_grid_qty(String init_grid_qty) {
            this.init_grid_qty = init_grid_qty;
        }

        public String getFree_grid_qty() {
            return free_grid_qty;
        }

        public void setFree_grid_qty(String free_grid_qty) {
            this.free_grid_qty = free_grid_qty;
        }

        public String getGrid_price() {
            return grid_price;
        }

        public void setGrid_price(String grid_price) {
            this.grid_price = grid_price;
        }

        public String getCan_order_limit() {
            return can_order_limit;
        }

        public void setCan_order_limit(String can_order_limit) {
            this.can_order_limit = can_order_limit;
        }

        public boolean isIs_over_order_limit() {
            return is_over_order_limit;
        }

        public void setIs_over_order_limit(boolean is_over_order_limit) {
            this.is_over_order_limit = is_over_order_limit;
        }

        public boolean isIs_rush() {
            return is_rush;
        }

        public void setIs_rush(boolean is_rush) {
            this.is_rush = is_rush;
        }

        public boolean isIs_try_card() {
            return is_try_card;
        }

        public void setIs_try_card(boolean is_try_card) {
            this.is_try_card = is_try_card;
        }

        public boolean isCan_buy_vip() {
            return can_buy_vip;
        }

        public void setCan_buy_vip(boolean can_buy_vip) {
            this.can_buy_vip = can_buy_vip;
        }

        public boolean isCan_edit_date() {
            return can_edit_date;
        }

        public void setCan_edit_date(boolean can_edit_date) {
            this.can_edit_date = can_edit_date;
        }

        public boolean isCan_edit_address() {
            return can_edit_address;
        }

        public void setCan_edit_address(boolean can_edit_address) {
            this.can_edit_address = can_edit_address;
        }

        public boolean isCan_buy_longtime() {
            return can_buy_longtime;
        }

        public void setCan_buy_longtime(boolean can_buy_longtime) {
            this.can_buy_longtime = can_buy_longtime;
        }

        public String getItems_count() {
            return items_count;
        }

        public void setItems_count(String items_count) {
            this.items_count = items_count;
        }

        public List<PackageItemsBean> getPackage_items() {
            return package_items;
        }

        public void setPackage_items(List<PackageItemsBean> package_items) {
            this.package_items = package_items;
        }

        public static class PackageItemsBean {
            /**
             * id : 1
             * specification : 1
             * name : 清雅紫丁香真丝
             * brand : AMSALE
             * image : http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240
             * deposit : 20000
             * created_at : 1487564580
             * is_invalid : false
             */

            private String id;
            private String specification;
            private String name;
            private String brand;
            private String image;
            private String deposit;
            private int created_at;
            private boolean is_invalid;
            public boolean isSelect=false;//选中
            public boolean isEmpty=false;//是否空格子 true,空格子 false,有东西
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public boolean isIs_invalid() {
                return is_invalid;
            }

            public void setIs_invalid(boolean is_invalid) {
                this.is_invalid = is_invalid;
            }
        }
    }
}
