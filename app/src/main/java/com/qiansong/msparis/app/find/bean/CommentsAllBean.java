package com.qiansong.msparis.app.find.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/3/15.
 *  所有评价列表
 */

public class CommentsAllBean extends BaseBean{

    /**
     * data : {"total":"1","rows":[{"id":"1","user":{"id":"1","nickname":"测试","head_portrait":"头像"},"content":"1","created_at":"0"}]}
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
         * total : 1
         * rows : [{"id":"1","user":{"id":"1","nickname":"测试","head_portrait":"头像"},"content":"1","created_at":"0"}]
         */

        private String total;
        private List<RowsBean> rows;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 1
             * user : {"id":"1","nickname":"测试","head_portrait":"头像"}
             * content : 1
             * created_at : 0
             */

            private String id;
            private UserBean user;
            private String content;
            private String created_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
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

            public static class UserBean {
                /**
                 * id : 1
                 * nickname : 测试
                 * head_portrait : 头像
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
    }
}
