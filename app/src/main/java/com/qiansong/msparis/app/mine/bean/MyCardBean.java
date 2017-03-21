package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/28.
 *  我的女神卡列表
 */

public class MyCardBean extends BaseBean {

    /**
     * data : {"rows":[{"id":"1","is_activate":"1","name":"大渡河路","expiry_date":"2017-03-24","notice":"测试使用须知","card_id":"1","card_role":[{"name":"女神特权","icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}]}],"images":["http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RowsBean> rows;
        private List<String> images;

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class RowsBean {
            /**
             * id : 1
             * is_activate : 1
             * name : 大渡河路
             * expiry_date : 2017-03-24
             * notice : 测试使用须知
             * card_id : 1
             * card_role : [{"name":"女神特权","icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}]
             */

            private String id;
            private String is_activate;
            private String name;
            private String expiry_date;
            private String notice;
            private String card_id;
            private List<CardRoleBean> card_role;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_activate() {
                return is_activate;
            }

            public void setIs_activate(String is_activate) {
                this.is_activate = is_activate;
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

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }

            public List<CardRoleBean> getCard_role() {
                return card_role;
            }

            public void setCard_role(List<CardRoleBean> card_role) {
                this.card_role = card_role;
            }

            public static class CardRoleBean {
                /**
                 * name : 女神特权
                 * icon : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
                 */

                private String name;
                private String icon;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }
    }
}
