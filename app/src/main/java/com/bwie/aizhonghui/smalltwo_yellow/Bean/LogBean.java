package com.bwie.aizhonghui.smalltwo_yellow.Bean;

/**
 * Created by DANGEROUS_HUI on 2017/11/16.
 */

public class LogBean {

    /**
     * h2y_app_id : string
     * pd : {"account":"string","openid":"string","password":"string","unionid":"string"}
     * token : string
     */

    private String h2y_app_id;
    private PdBean pd;
    private String token;

    public String getH2y_app_id() {
        return h2y_app_id;
    }

    public void setH2y_app_id(String h2y_app_id) {
        this.h2y_app_id = h2y_app_id;
    }

    public PdBean getPd() {
        return pd;
    }

    public void setPd(PdBean pd) {
        this.pd = pd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class PdBean {
        /**
         * account : string
         * openid : string
         * password : string
         * unionid : string
         */

        private String account;
        private String openid;
        private String password;
        private String unionid;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }
    }
}
