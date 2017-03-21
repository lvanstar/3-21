package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

/**
 * Created by kevin on 2017/2/24.
 */

public class GetUserBean  extends BaseBean {


    /**
     * data : {"nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","orders":{"pending_num":"1","to_be_delivered_num":"1","to_be_received_num":"1","to_be_commented_num":"1"},"level_name":"普通会员","level_icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","user_card":{"icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","name":"318元18天体验卡","expiry_date":"2017-03-24"},"wardrobe_num":"5","to_be_clothes":"1","user_card_num":"2","favorite_product_num":"1","favorite_brand_num":"1","authentication_type":"0","invoice_amount":"500"}
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
         * nickname : 测试
         * head_portrait : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
         * orders : {"pending_num":"1","to_be_delivered_num":"1","to_be_received_num":"1","to_be_commented_num":"1"}
         * level_name : 普通会员
         * level_icon : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
         * user_card : {"icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","name":"318元18天体验卡","expiry_date":"2017-03-24"}
         * wardrobe_num : 5
         * to_be_clothes : 1
         * user_card_num : 2
         * favorite_product_num : 1
         * favorite_brand_num : 1
         * authentication_type : 0
         * invoice_amount : 500
         */

        private String nickname;
        private String head_portrait;
        private OrdersBean orders;
        private String level_name;
        private String level_icon;
        private UserCardBean user_card;
        private String wardrobe_num;
        private String to_be_clothes;
        private String user_card_num;
        private String favorite_product_num;
        private String favorite_brand_num;
        private String authentication_type;
        private String invoice_amount;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public OrdersBean getOrders() {
            return orders;
        }

        public void setOrders(OrdersBean orders) {
            this.orders = orders;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getLevel_icon() {
            return level_icon;
        }

        public void setLevel_icon(String level_icon) {
            this.level_icon = level_icon;
        }

        public UserCardBean getUser_card() {
            return user_card;
        }

        public void setUser_card(UserCardBean user_card) {
            this.user_card = user_card;
        }

        public String getWardrobe_num() {
            return wardrobe_num;
        }

        public void setWardrobe_num(String wardrobe_num) {
            this.wardrobe_num = wardrobe_num;
        }

        public String getTo_be_clothes() {
            return to_be_clothes;
        }

        public void setTo_be_clothes(String to_be_clothes) {
            this.to_be_clothes = to_be_clothes;
        }

        public String getUser_card_num() {
            return user_card_num;
        }

        public void setUser_card_num(String user_card_num) {
            this.user_card_num = user_card_num;
        }

        public String getFavorite_product_num() {
            return favorite_product_num;
        }

        public void setFavorite_product_num(String favorite_product_num) {
            this.favorite_product_num = favorite_product_num;
        }

        public String getFavorite_brand_num() {
            return favorite_brand_num;
        }

        public void setFavorite_brand_num(String favorite_brand_num) {
            this.favorite_brand_num = favorite_brand_num;
        }

        public String getAuthentication_type() {
            return authentication_type;
        }

        public void setAuthentication_type(String authentication_type) {
            this.authentication_type = authentication_type;
        }

        public String getInvoice_amount() {
            return invoice_amount;
        }

        public void setInvoice_amount(String invoice_amount) {
            this.invoice_amount = invoice_amount;
        }

        public static class OrdersBean {
            /**
             * pending_num : 1
             * to_be_delivered_num : 1
             * to_be_received_num : 1
             * to_be_commented_num : 1
             */

            private String pending_num;
            private String to_be_delivered_num;
            private String to_be_received_num;
            private String to_be_commented_num;

            public String getPending_num() {
                return pending_num;
            }

            public void setPending_num(String pending_num) {
                this.pending_num = pending_num;
            }

            public String getTo_be_delivered_num() {
                return to_be_delivered_num;
            }

            public void setTo_be_delivered_num(String to_be_delivered_num) {
                this.to_be_delivered_num = to_be_delivered_num;
            }

            public String getTo_be_received_num() {
                return to_be_received_num;
            }

            public void setTo_be_received_num(String to_be_received_num) {
                this.to_be_received_num = to_be_received_num;
            }

            public String getTo_be_commented_num() {
                return to_be_commented_num;
            }

            public void setTo_be_commented_num(String to_be_commented_num) {
                this.to_be_commented_num = to_be_commented_num;
            }
        }

        public static class UserCardBean {
            /**
             * icon : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
             * name : 318元18天体验卡
             * expiry_date : 2017-03-24
             */

            private String icon;
            private String name;
            private String expiry_date;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getExpiry_date() {
                return expiry_date;
            }

            public void setExpiry_date(String expiry_date) {
                this.expiry_date = expiry_date;
            }
        }
    }
}
