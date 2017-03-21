package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/27.
 *
 *  女神卡详情
 */

public class BuyCarDetailsBean extends BaseBean {

    /**
     * data : {"id":"1","cover_img":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","name":"1","card_role":[{"name":"尊贵VIP","image_src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}],"coupon_number":"2","notice":"无限期换","price":"50.00"}
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
         * id : 1
         * cover_img : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
         * name : 1
         * card_role : [{"name":"尊贵VIP","image_src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}]
         * coupon_number : 2
         * notice : 无限期换
         * price : 50.00
         */

        private String id;
        private String cover_img;
        private String name;
        private String coupon_number;
        private String notice;
        private String price;
        private List<CardRoleBean> card_role;

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

        public String getCoupon_number() {
            return coupon_number;
        }

        public void setCoupon_number(String coupon_number) {
            this.coupon_number = coupon_number;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<CardRoleBean> getCard_role() {
            return card_role;
        }

        public void setCard_role(List<CardRoleBean> card_role) {
            this.card_role = card_role;
        }

        public static class CardRoleBean {
            /**
             * name : 尊贵VIP
             * image_src : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
             */

            private String name;
            private String image_src;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage_src() {
                return image_src;
            }

            public void setImage_src(String image_src) {
                this.image_src = image_src;
            }
        }
    }
}
