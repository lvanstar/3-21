package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/3/16.
 *
 *  发现列表
 */

public class FindListBean extends BaseBean{
    /**
     * data : {"rows":[{"id":4,"user":{"nickname":"nickname","head_portrait":"123","id":48177},"is_follow":2,"content":"3213123","city":"上海市","address":"静安区","comment_num":0,"like_num":0,"images":[{"w":719,"h":1280,"src":"http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb79785.jpg"},{"w":719,"h":1280,"src":"http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb73459.jpg"}],"created_at":"2017-03-09 15:49:59"}],"total":1}
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
         * rows : [{"id":4,"user":{"nickname":"nickname","head_portrait":"123","id":48177},"is_follow":2,"content":"3213123","city":"上海市","address":"静安区","comment_num":0,"like_num":0,"images":[{"w":719,"h":1280,"src":"http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb79785.jpg"},{"w":719,"h":1280,"src":"http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb73459.jpg"}],"created_at":"2017-03-09 15:49:59"}]
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
             * id : 4
             * user : {"nickname":"nickname","head_portrait":"123","id":48177}
             * is_follow : 2
             * content : 3213123
             * city : 上海市
             * address : 静安区
             * comment_num : 0
             * like_num : 0
             * images : [{"w":719,"h":1280,"src":"http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb79785.jpg"},{"w":719,"h":1280,"src":"http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb73459.jpg"}]
             * created_at : 2017-03-09 15:49:59
             */

            private int id;
            private UserEntity user;
            private int is_follow;
            private String content;
            private String city;
            private String address;
            private int comment_num;
            private int like_num;
            private String created_at;
            private List<ImagesEntity> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public UserEntity getUser() {
                return user;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public int getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(int is_follow) {
                this.is_follow = is_follow;
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

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public List<ImagesEntity> getImages() {
                return images;
            }

            public void setImages(List<ImagesEntity> images) {
                this.images = images;
            }

            public static class UserEntity {
                /**
                 * nickname : nickname
                 * head_portrait : 123
                 * id : 48177
                 */

                private String nickname;
                private String head_portrait;
                private int id;

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
            }

            public static class ImagesEntity {
                /**
                 * w : 719
                 * h : 1280
                 * src : http://api.test.msparis.com/uploads/20170309/1339962ddafe518aa02ca66dc88a7cb79785.jpg
                 */

                private int w;
                private int h;
                private String src;

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

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }
            }
        }
    }
}
