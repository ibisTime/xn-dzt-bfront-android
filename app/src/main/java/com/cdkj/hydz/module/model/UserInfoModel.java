package com.cdkj.hydz.module.model;

import android.os.Parcel;

/**
 * Created by 李先俊 on 2017/8/8.
 */

public class UserInfoModel {

    public UserInfoModel() {

        super();

    }


    /**
     * userId : U201708171530061085458
     * loginName : song
     * mobile : 15738777150
     * nickname : 61085458
     * loginPwdStrength : 1
     * kind : B
     * level : 1
     * userReferee : U201708171137236185567
     * idNo : 333302201612128529
     * realName : 宋真真
     * roleCode : DZTSR20170000000000lts
     * divRate : 0.1
     * status : 0
     * province : 浙江省
     * city : 杭州市
     * area : 余杭区
     * lastOrderDatetime : Aug 22, 2017 12:09:00 PM
     * createDatetime : Aug 17, 2017 3:30:06 PM
     * updater : 余杭合伙人
     * updateDatetime : Aug 17, 2017 3:32:11 PM
     * companyCode : CD-CDZT000009
     * systemCode : CD-CDZT000009
     * tradepwdFlag : false
     * totalFollowNum : 0
     * totalFansNum : 0
     * refereeUser : {"userId":"U201708171137236185567","loginName":"余杭合伙人","mobile":"15738777150","nickname":"36185567","loginPwdStrength":"1","kind":"PA","level":"1","idNo":"333302201612128529","realName":"szz","roleCode":"DZTSR20170000000000hhr","divRate":0.2,"status":"0","province":"浙江省","city":"杭州市","area":"余杭区","lastOrderDatetime":"Aug 22, 2017 12:08:58 PM","createDatetime":"Aug 17, 2017 11:37:23 AM","updater":"admin","updateDatetime":"Aug 17, 2017 11:37:23 AM","companyCode":"CD-CDZT000009","systemCode":"CD-CDZT000009","tradepwdFlag":false}
     */

    private String userId;
    private String loginName;
    private String mobile;
    private String nickname;
    private String loginPwdStrength;
    private String kind;
    private String level;
    private String userReferee;
    private String idNo;
    private String realName = "";
    private String photo = "";
    private String roleCode;
    private double divRate;
    private String status;
    private String province;
    private String city;
    private String area;
    private String lastOrderDatetime;
    private String createDatetime;
    private String updater;
    private String updateDatetime;
    private String companyCode;
    private String systemCode;
    private boolean tradepwdFlag;
    private int totalFollowNum;
    private int totalFansNum;
    private RefereeUserBean refereeUser;

    protected UserInfoModel(Parcel in) {
        userId = in.readString();
        loginName = in.readString();
        mobile = in.readString();
        nickname = in.readString();
        loginPwdStrength = in.readString();
        kind = in.readString();
        level = in.readString();
        userReferee = in.readString();
        idNo = in.readString();
        realName = in.readString();
        photo = in.readString();
        roleCode = in.readString();
        divRate = in.readDouble();
        status = in.readString();
        province = in.readString();
        city = in.readString();
        area = in.readString();
        lastOrderDatetime = in.readString();
        createDatetime = in.readString();
        updater = in.readString();
        updateDatetime = in.readString();
        companyCode = in.readString();
        systemCode = in.readString();
        tradepwdFlag = in.readByte() != 0;
        totalFollowNum = in.readInt();
        totalFansNum = in.readInt();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public double getDivRate() {
        return divRate;
    }

    public void setDivRate(double divRate) {
        this.divRate = divRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLastOrderDatetime() {
        return lastOrderDatetime;
    }

    public void setLastOrderDatetime(String lastOrderDatetime) {
        this.lastOrderDatetime = lastOrderDatetime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public boolean isTradepwdFlag() {
        return tradepwdFlag;
    }

    public void setTradepwdFlag(boolean tradepwdFlag) {
        this.tradepwdFlag = tradepwdFlag;
    }

    public int getTotalFollowNum() {
        return totalFollowNum;
    }

    public void setTotalFollowNum(int totalFollowNum) {
        this.totalFollowNum = totalFollowNum;
    }

    public int getTotalFansNum() {
        return totalFansNum;
    }

    public void setTotalFansNum(int totalFansNum) {
        this.totalFansNum = totalFansNum;
    }

    public RefereeUserBean getRefereeUser() {
        return refereeUser;
    }

    public void setRefereeUser(RefereeUserBean refereeUser) {
        this.refereeUser = refereeUser;
    }


    public static class RefereeUserBean {
        /**
         * userId : U201708171137236185567
         * loginName : 余杭合伙人
         * mobile : 15738777150
         * nickname : 36185567
         * loginPwdStrength : 1
         * kind : PA
         * level : 1
         * idNo : 333302201612128529
         * realName : szz
         * roleCode : DZTSR20170000000000hhr
         * divRate : 0.2
         * status : 0
         * province : 浙江省
         * city : 杭州市
         * area : 余杭区
         * lastOrderDatetime : Aug 22, 2017 12:08:58 PM
         * createDatetime : Aug 17, 2017 11:37:23 AM
         * updater : admin
         * updateDatetime : Aug 17, 2017 11:37:23 AM
         * companyCode : CD-CDZT000009
         * systemCode : CD-CDZT000009
         * tradepwdFlag : false
         */

        private String userId;
        private String loginName;
        private String mobile;
        private String nickname;
        private String loginPwdStrength;
        private String kind;
        private String level;
        private String idNo;
        private String realName;
        private String roleCode;
        private double divRate;
        private String status;
        private String province;
        private String city;
        private String area;
        private String lastOrderDatetime;
        private String createDatetime;
        private String updater;
        private String updateDatetime;
        private String companyCode;
        private String systemCode;
        private boolean tradepwdFlag;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getLoginPwdStrength() {
            return loginPwdStrength;
        }

        public void setLoginPwdStrength(String loginPwdStrength) {
            this.loginPwdStrength = loginPwdStrength;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

        public double getDivRate() {
            return divRate;
        }

        public void setDivRate(double divRate) {
            this.divRate = divRate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLastOrderDatetime() {
            return lastOrderDatetime;
        }

        public void setLastOrderDatetime(String lastOrderDatetime) {
            this.lastOrderDatetime = lastOrderDatetime;
        }

        public String getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(String createDatetime) {
            this.createDatetime = createDatetime;
        }

        public String getUpdater() {
            return updater;
        }

        public void setUpdater(String updater) {
            this.updater = updater;
        }

        public String getUpdateDatetime() {
            return updateDatetime;
        }

        public void setUpdateDatetime(String updateDatetime) {
            this.updateDatetime = updateDatetime;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getSystemCode() {
            return systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }

        public boolean isTradepwdFlag() {
            return tradepwdFlag;
        }

        public void setTradepwdFlag(boolean tradepwdFlag) {
            this.tradepwdFlag = tradepwdFlag;
        }
    }
}
