package com.qiansong.msparis.app.commom.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 * 邀请有奖
 */

public class InviteBean {

    /**
     * status : ok
     * data : {"invitation_code":"111111","invite_people":"111111","invitation_number":1,"reward_days":5,"reward_information":["1.jpeg"]}
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
        /**
         * invitation_code : 111111
         * invite_people : 111111
         * invitation_number : 1
         * reward_days : 5
         * reward_information : ["1.jpeg"]
         */

        private String invitation_code;
        private String invite_people;
        private int invitation_number;
        private int reward_days;
        private List<String> reward_information;

        public String getInvitation_code() {
            return invitation_code;
        }

        public void setInvitation_code(String invitation_code) {
            this.invitation_code = invitation_code;
        }

        public String getInvite_people() {
            return invite_people;
        }

        public void setInvite_people(String invite_people) {
            this.invite_people = invite_people;
        }

        public int getInvitation_number() {
            return invitation_number;
        }

        public void setInvitation_number(int invitation_number) {
            this.invitation_number = invitation_number;
        }

        public int getReward_days() {
            return reward_days;
        }

        public void setReward_days(int reward_days) {
            this.reward_days = reward_days;
        }

        public List<String> getReward_information() {
            return reward_information;
        }

        public void setReward_information(List<String> reward_information) {
            this.reward_information = reward_information;
        }
    }
}
