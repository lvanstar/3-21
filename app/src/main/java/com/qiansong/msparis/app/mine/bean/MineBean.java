package com.qiansong.msparis.app.mine.bean;

import com.qiansong.msparis.app.commom.bean.BaseBean;

import java.util.List;

/**
 * Created by kevin on 2017/2/24.
 *  会员中心首页数据
 */

public class MineBean extends BaseBean {


    /**
     * data : {"nickname":"测试","head_portrait":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","is_sign":"1","integral":"1000","upgrade_need_intergral":"500","level":"1","level_name":"普通会员","member_privilege":[{"icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","name":"特权名称","level":"1"}]}
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
         * is_sign : 1
         * integral : 1000
         * upgrade_need_intergral : 500
         * level : 1
         * level_name : 普通会员
         * member_privilege : [{"icon":"http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg","name":"特权名称","level":"1"}]
         */

        private String nickname;
        private String head_portrait;
        private String is_sign;
        private String integral;
        private String upgrade_need_intergral;
        private String level;
        private String level_name;
        private List<MemberPrivilegeBean> member_privilege;

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

        public String getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(String is_sign) {
            this.is_sign = is_sign;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getUpgrade_need_intergral() {
            return upgrade_need_intergral;
        }

        public void setUpgrade_need_intergral(String upgrade_need_intergral) {
            this.upgrade_need_intergral = upgrade_need_intergral;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public List<MemberPrivilegeBean> getMember_privilege() {
            return member_privilege;
        }

        public void setMember_privilege(List<MemberPrivilegeBean> member_privilege) {
            this.member_privilege = member_privilege;
        }

        public static class MemberPrivilegeBean {
            /**
             * icon : http://n.sinaimg.cn/news/transform/20170224/mgIu-fyavvth7470828.jpeg
             * name : 特权名称
             * level : 1
             */

            private String icon;
            private String name;
            private String level;

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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
