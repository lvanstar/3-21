package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by lizhaozhao on 2017/2/27.
 *
 * 评论列表
 */

public class CommentsBean extends BaseBean{


    /**
     * data : {"total":1,"rows":[{"id":1,"usr_pic":"http://www.msparis.com/","nickname":"abc","stars":5,"comment":"评论内容","created_at":1488161907}]}
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
         * total : 1
         * rows : [{"id":1,"usr_pic":"http://www.msparis.com/","nickname":"abc","stars":5,"comment":"评论内容","created_at":1488161907}]
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
             * id : 1
             * usr_pic : http://www.msparis.com/
             * nickname : abc
             * stars : 5
             * comment : 评论内容
             * created_at : 1488161907
             */

            private int id;
            private String usr_pic;
            private String nickname;
            private int stars;
            private String comment;
            private int created_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsr_pic() {
                return usr_pic;
            }

            public void setUsr_pic(String usr_pic) {
                this.usr_pic = usr_pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getStars() {
                return stars;
            }

            public void setStars(int stars) {
                this.stars = stars;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }
        }
    }
}
