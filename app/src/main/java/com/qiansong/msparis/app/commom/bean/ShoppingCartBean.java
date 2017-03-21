package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 * 购物车
 */

public class ShoppingCartBean {

    /**
     * status : ok
     * data : [{"starttime":"1487548800","endtime":"1487721600","region":"上海","items":[{"id":"1","specification":"1","name":"清雅紫丁香真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":false}]},{"starttime":"1487548800","endtime":"1487721600","region":"上海","items":[{"id":"1","specification":"1","name":"清雅紫丁香真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":false}]}]
     */

    private String status;
    public List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * starttime : 1487548800
         * endtime : 1487721600
         * region : 上海
         * items : [{"id":"1","specification":"1","name":"清雅紫丁香真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":false}]
         */

        private String starttime;
        private String endtime;
        private String region;
        public List<ItemsBean> items;

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
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
            public boolean isSelect=false;
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
