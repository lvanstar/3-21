package com.qiansong.msparis.app.find.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/15.
 *
 *  获取晒图详情
 */

public class FindDetailItemBean extends BaseBean{


    /**
     * data : {"id":"1","content":"一次快乐的租衣服务","comments":[{"id":"1","user":{"id":"1","nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"},"content":"这么高兴","created_at":"2017-02-28 17:50:34"}],"user":{"id":"1","nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"},"is_follow":"0","is_like":"0","city":"上海","clothes":[{"id":"1","cover_img":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}],"address":"大连路","images":[{"src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","w":123,"h":123}],"created_at":"2017-02-30 17:30:30","clothes_num":"1","comment_num":"1","concerns_num":"1"}
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
         * content : 一次快乐的租衣服务
         * comments : [{"id":"1","user":{"id":"1","nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"},"content":"这么高兴","created_at":"2017-02-28 17:50:34"}]
         * user : {"id":"1","nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}
         * is_follow : 0
         * is_like : 0
         * city : 上海
         * clothes : [{"id":"1","cover_img":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}]
         * address : 大连路
         * images : [{"src":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","w":123,"h":123}]
         * created_at : 2017-02-30 17:30:30
         * clothes_num : 1
         * comment_num : 1
         * concerns_num : 1
         */

        private String id;
        private String content;
        private UserBean user;
        private String is_follow;
        private String is_like;
        private String city;
        private String address;
        private String created_at;
        private String clothes_num;
        private String comment_num;
        private String concerns_num;
        private List<CommentsBean> comments;
        private List<ClothesBean> clothes;
        private List<ImagesBean> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getIs_like() {
            return is_like;
        }

        public void setIs_like(String is_like) {
            this.is_like = is_like;
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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getClothes_num() {
            return clothes_num;
        }

        public void setClothes_num(String clothes_num) {
            this.clothes_num = clothes_num;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getConcerns_num() {
            return concerns_num;
        }

        public void setConcerns_num(String concerns_num) {
            this.concerns_num = concerns_num;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public List<ClothesBean> getClothes() {
            return clothes;
        }

        public void setClothes(List<ClothesBean> clothes) {
            this.clothes = clothes;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class UserBean {
            /**
             * id : 1
             * nickname : 测试
             * head_portrait : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
             */

            private String id;
            private String nickname;
            private String head_portrait;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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
        }

        public static class CommentsBean {
            /**
             * id : 1
             * user : {"id":"1","nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg"}
             * content : 这么高兴
             * created_at : 2017-02-28 17:50:34
             */

            private String id;
            private UserBeanX user;
            private String content;
            private String created_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public static class UserBeanX {
                /**
                 * id : 1
                 * nickname : 测试
                 * head_portrait : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
                 */

                private String id;
                private String nickname;
                private String head_portrait;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

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
            }
        }

        public static class ClothesBean {
            /**
             * id : 1
             * cover_img : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
             */

            private String id;
            private String cover_img;

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
