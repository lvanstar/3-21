package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 * 更新租赁周期
 */

public class EditPakegeTimeBean {

    /**
     * status : ok
     * data : {"packge_items":[{"id":"1","specification":"1","name":"清雅紫丁香真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":false},{"id":"2","specification":"2","name":"高贵珍珠白真丝","brand":"AMSALE","image":"http://7xifi3.com2.z0.glb.qiniucdn.com/catalog/product/a/m/am7_2.jpeg?imageMogr2/thumbnail/!160x240r/crop/160x240","deposit":"20000","created_at":1487564580,"is_invalid":true}]}
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
        private List<PackgeItemsBean> packge_items;

        public List<PackgeItemsBean> getPackge_items() {
            return packge_items;
        }

        public void setPackge_items(List<PackgeItemsBean> packge_items) {
            this.packge_items = packge_items;
        }

        public static class PackgeItemsBean {
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
