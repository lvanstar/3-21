package com.qiansong.msparis.app.find.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/15.
 *  我的朋友圈
 */

public class MyCircleBean  extends BaseBean{

    /**
     * data : {"user":{"nickname":"昵称","head_portrait":"头像","id":1,"follow_num":1,"fans_num":1},"is_follow":0,"rows":[{"id":1,"content":"测试","city":"上海市","address":"南京路","comment_num":0,"like_num":0,"is_like":0,"images":[{"src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","w":123,"h":123}],"created_at":"2017-02-28 00:00:00"}],"total":1}
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
         * user : {"nickname":"昵称","head_portrait":"头像","id":1,"follow_num":1,"fans_num":1}
         * is_follow : 0
         * rows : [{"id":1,"content":"测试","city":"上海市","address":"南京路","comment_num":0,"like_num":0,"is_like":0,"images":[{"src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","w":123,"h":123}],"created_at":"2017-02-28 00:00:00"}]
         * total : 1
         */

        private UserBean user;
        private int is_follow;
        private int total;
        private List<RowsBean> rows;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

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

        public static class UserBean {
            /**
             * nickname : 昵称
             * head_portrait : 头像
             * id : 1
             * follow_num : 1
             * fans_num : 1
             */

            private String nickname;
            private String head_portrait;
            private int id;
            private int follow_num;
            private int fans_num;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFollow_num() {
                return follow_num;
            }

            public void setFollow_num(int follow_num) {
                this.follow_num = follow_num;
            }

            public int getFans_num() {
                return fans_num;
            }

            public void setFans_num(int fans_num) {
                this.fans_num = fans_num;
            }
        }

        public static class RowsBean {
            /**
             * id : 1
             * content : 测试
             * city : 上海市
             * address : 南京路
             * comment_num : 0
             * like_num : 0
             * is_like : 0
             * images : [{"src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","w":123,"h":123}]
             * created_at : 2017-02-28 00:00:00
             */

            private int id;
            private String content;
            private String city;
            private String address;
            private int comment_num;
            private int like_num;
            private int is_like;
            private String created_at;
            private List<ImagesBean> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getLike_num() {
                return like_num;
            }

            public void setLike_num(int like_num) {
                this.like_num = like_num;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public List<ImagesBean> getImages() {
                return images;
            }

            public void setImages(List<ImagesBean> images) {
                this.images = images;
            }

            public static class ImagesBean {
                /**
                 * src : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
                 * w : 123
                 * h : 123
                 */

                private String src;
                private int w;
                private int h;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }
            }
        }
    }
}
