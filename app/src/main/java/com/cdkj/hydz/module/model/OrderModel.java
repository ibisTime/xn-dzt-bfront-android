package com.cdkj.hydz.module.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lei on 2017/8/21.
 */

public class OrderModel {


    /**
     * pageNO : 1
     * start : 0
     * pageSize : 10
     * totalCount : 17
     * totalPage : 2
     * list : [{"code":"DD201708211745024179","type":"0","toUser":"U201708171137236185567","applyUser":"U201708171354515884944","applyName":"22","applyMobile":"15738777150","ltDatetime":"Aug 25, 2017 12:00:00 AM","ltProvince":"浙江省","ltCity":"杭州市","ltArea":"余杭区","ltAddress":"33","createDatetime":"Aug 21, 2017 5:45:02 PM","status":"3","ltUser":"U201708171606230741617","ltName":"小李","amount":499000,"payDatetime":"Aug 21, 2017 5:46:05 PM","payAmount":499000,"receiver":"22","reMobile":"15738777150","reAddress":"余杭区","updater":"admin","updateDatetime":"Aug 21, 2017 5:48:11 PM","remark":"3","ltUserDO":{"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}},{"code":"DD201708211731430234","toUser":"0","applyUser":"U201708071922132277071","applyName":"吴联请1","applyMobile":"18868824532","ltDatetime":"Aug 21, 2017 12:00:00 AM","ltProvince":"广西壮族自治区","ltCity":"百色市","ltArea":"德保县","ltAddress":"量体门牌","createDatetime":"Aug 21, 2017 5:31:43 PM","status":"11","receiver":"吴联请1","reMobile":"18868824532","updater":"U201708071922132277071","updateDatetime":"Aug 21, 2017 5:31:45 PM"},{"code":"DD201708211730280524","type":"0","toUser":"U201708171137236185567","applyUser":"U201708171354515884944","applyName":"22","applyMobile":"15738777150","ltDatetime":"Aug 25, 2017 12:00:00 AM","ltProvince":"浙江省","ltCity":"杭州市","ltArea":"余杭区","ltAddress":"333","createDatetime":"Aug 21, 2017 5:30:28 PM","status":"3","ltUser":"U201708171606230741617","ltName":"小李","amount":399000,"payDatetime":"Aug 21, 2017 5:44:13 PM","payAmount":399000,"receiver":"22","reMobile":"15738777150","updater":"admin","updateDatetime":"Aug 21, 2017 5:30:58 PM","remark":"","ltUserDO":{"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}},{"code":"DD201708211724357326","toUser":"0","applyUser":"U201708071922132277071","applyName":"吴联请1","applyMobile":"18868824532","ltDatetime":"Aug 21, 2017 12:00:00 AM","ltProvince":"广西壮族自治区","ltCity":"百色市","ltArea":"德保县","ltAddress":"量体门牌","createDatetime":"Aug 21, 2017 5:24:35 PM","status":"11","receiver":"吴联请1","reMobile":"18868824532","updater":"U201708071922132277071","updateDatetime":"Aug 21, 2017 5:24:37 PM"},{"code":"DD201708211710031941","type":"1","toUser":"U201708171137236185567","applyUser":"U201708171354515884944","applyName":"11","applyMobile":"15738777150","ltDatetime":"Aug 23, 2017 12:00:00 AM","ltProvince":"浙江省","ltCity":"杭州市","ltArea":"余杭区","ltAddress":"密码","createDatetime":"Aug 21, 2017 5:10:03 PM","status":"3","ltUser":"U201708171606230741617","ltName":"小李","amount":0,"payDatetime":"Aug 21, 2017 5:11:03 PM","payAmount":0,"receiver":"11","reMobile":"15738777150","updater":"admin","updateDatetime":"Aug 21, 2017 5:10:58 PM","remark":"","ltUserDO":{"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}},{"code":"DD201708211707451900","toUser":"0","applyUser":"U201708071922132277071","applyName":"吴联请1","applyMobile":"18868824532","ltDatetime":"Aug 21, 2017 12:00:00 AM","ltProvince":"广西壮族自治区","ltCity":"百色市","ltArea":"德保县","ltAddress":"量体门牌","createDatetime":"Aug 21, 2017 5:07:45 PM","status":"11","receiver":"吴联请1","reMobile":"18868824532","updater":"U201708071922132277071","updateDatetime":"Aug 21, 2017 5:23:20 PM"},{"code":"DD201708210947394289","type":"0","toUser":"U201708171137236185567","applyUser":"U201708071922132277071","applyName":"吴联请","applyMobile":"18868824532","ltDatetime":"Aug 21, 2017 12:00:00 AM","ltProvince":"浙江省","ltCity":"杭州市","ltArea":"余杭区","ltAddress":"反反复复","createDatetime":"Aug 21, 2017 9:47:39 AM","status":"3","ltUser":"U201708171606230741617","ltName":"小李","amount":399000,"payDatetime":"Aug 21, 2017 3:34:49 PM","payAmount":399000,"receiver":"吴联请","reMobile":"18868824532","reAddress":"333","updater":"admin","updateDatetime":"Aug 21, 2017 5:41:38 PM","ltUserDO":{"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}},{"code":"DD201708201700507500","toUser":"0","applyUser":"U201708071922132277071","applyName":"吴联请","applyMobile":"18888888888","ltDatetime":"Aug 20, 2017 12:00:00 AM","ltProvince":"山西省","ltCity":"长治市","ltArea":"长治县","ltAddress":"门牌号码","createDatetime":"Aug 20, 2017 5:00:50 PM","status":"11","receiver":"吴联请","reMobile":"18888888888","updater":"U201708071922132277071","updateDatetime":"Aug 20, 2017 6:26:09 PM"},{"code":"DD201708201359210487","toUser":"0","applyUser":"U201708071922132277071","applyName":"rrrr","applyMobile":"18888888888","ltDatetime":"Aug 20, 2017 12:00:00 AM","ltProvince":"安徽省","ltCity":"安庆市","ltArea":"枞阳县","ltAddress":"334reww","createDatetime":"Aug 20, 2017 1:59:21 PM","status":"1","ltUser":"U201708171606230741617","ltName":"小李","receiver":"rrrr","reMobile":"18888888888","updater":"admin","updateDatetime":"Aug 20, 2017 6:29:16 PM","ltUserDO":{"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}},{"code":"DD201708171522399489","type":"0","toUser":"U201708171137236185567","applyUser":"U201708171354515884944","applyName":"szz","applyMobile":"15738777150","ltDatetime":"Aug 19, 2017 12:00:00 AM","ltProvince":"浙江省","ltCity":"杭州市","ltArea":"余杭区","ltAddress":"ee","createDatetime":"Aug 17, 2017 3:22:39 PM","status":"9","ltUser":"U201708171606230741617","ltName":"小李","amount":499000,"payDatetime":"Aug 17, 2017 4:41:45 PM","payAmount":499000,"receiver":"szz","reMobile":"15738777150","updater":"admin","updateDatetime":"Aug 17, 2017 6:59:11 PM","remark":"33","ltUserDO":{"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}}]
     */

    private int pageNO;
    private int start;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<ListBean> list;

    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int pageNO) {
        this.pageNO = pageNO;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * code : DD201708211745024179
         * type : 0
         * toUser : U201708171137236185567
         * applyUser : U201708171354515884944
         * applyName : 22
         * applyMobile : 15738777150
         * ltDatetime : Aug 25, 2017 12:00:00 AM
         * ltProvince : 浙江省
         * ltCity : 杭州市
         * ltArea : 余杭区
         * ltAddress : 33
         * createDatetime : Aug 21, 2017 5:45:02 PM
         * status : 3
         * ltUser : U201708171606230741617
         * ltName : 小李
         * amount : 499000
         * payDatetime : Aug 21, 2017 5:46:05 PM
         * payAmount : 499000
         * receiver : 22
         * reMobile : 15738777150
         * reAddress : 余杭区
         * updater : admin
         * updateDatetime : Aug 21, 2017 5:48:11 PM
         * remark : 3
         * ltUserDO : {"userId":"U201708171606230741617","loginName":"15738777150","nickname":"30741617","mobile":"15738777150","status":"0","level":"1","kind":"B","realName":"小李","userReferee":"U201708171137236185567","idNo":"333302201612128529","divRate":"0.1","tradepwdFlag":"0","companyCode":"CD-CDZT000009","totalFollowNum":"null","totalFansNum":"null","systemCode":"CD-CDZT000009","identityFlag":"1","province":"浙江省","city":"杭州市","area":"余杭区"}
         */

        private String code;
        private String type;
        private String toUser;
        private String applyUser;
        private String applyName;
        private String applyMobile;
        private String ltDatetime;
        private String ltProvince;
        private String modelCode = "";
        private String modelName;
        private String ltCity;
        private String ltArea;
        private String ltAddress;
        private String createDatetime;
        private String status;
        private String ltUser;
        private String ltName;
        private BigDecimal amount;
        private String payDatetime;
        private BigDecimal payAmount;
        private String receiver;
        private String reMobile;
        private String reAddress;
        private String updater;
        private String updateDatetime;
        private String remark;
        private LtUserDOBean ltUserDO;

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getModelCode() {
            return modelCode;
        }

        public void setModelCode(String modelCode) {
            this.modelCode = modelCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public String getApplyUser() {
            return applyUser;
        }

        public void setApplyUser(String applyUser) {
            this.applyUser = applyUser;
        }

        public String getApplyName() {
            return applyName;
        }

        public void setApplyName(String applyName) {
            this.applyName = applyName;
        }

        public String getApplyMobile() {
            return applyMobile;
        }

        public void setApplyMobile(String applyMobile) {
            this.applyMobile = applyMobile;
        }

        public String getLtDatetime() {
            return ltDatetime;
        }

        public void setLtDatetime(String ltDatetime) {
            this.ltDatetime = ltDatetime;
        }

        public String getLtProvince() {
            return ltProvince;
        }

        public void setLtProvince(String ltProvince) {
            this.ltProvince = ltProvince;
        }

        public String getLtCity() {
            return ltCity;
        }

        public void setLtCity(String ltCity) {
            this.ltCity = ltCity;
        }

        public String getLtArea() {
            return ltArea;
        }

        public void setLtArea(String ltArea) {
            this.ltArea = ltArea;
        }

        public String getLtAddress() {
            return ltAddress;
        }

        public void setLtAddress(String ltAddress) {
            this.ltAddress = ltAddress;
        }

        public String getCreateDatetime() {
            return createDatetime;
        }

        public void setCreateDatetime(String createDatetime) {
            this.createDatetime = createDatetime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLtUser() {
            return ltUser;
        }

        public void setLtUser(String ltUser) {
            this.ltUser = ltUser;
        }

        public String getLtName() {
            return ltName;
        }

        public void setLtName(String ltName) {
            this.ltName = ltName;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getPayDatetime() {
            return payDatetime;
        }

        public void setPayDatetime(String payDatetime) {
            this.payDatetime = payDatetime;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getReMobile() {
            return reMobile;
        }

        public void setReMobile(String reMobile) {
            this.reMobile = reMobile;
        }

        public String getReAddress() {
            return reAddress;
        }

        public void setReAddress(String reAddress) {
            this.reAddress = reAddress;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public LtUserDOBean getLtUserDO() {
            return ltUserDO;
        }

        public void setLtUserDO(LtUserDOBean ltUserDO) {
            this.ltUserDO = ltUserDO;
        }

        public static class LtUserDOBean {
            /**
             * userId : U201708171606230741617
             * loginName : 15738777150
             * nickname : 30741617
             * mobile : 15738777150
             * status : 0
             * level : 1
             * kind : B
             * realName : 小李
             * userReferee : U201708171137236185567
             * idNo : 333302201612128529
             * divRate : 0.1
             * tradepwdFlag : 0
             * companyCode : CD-CDZT000009
             * totalFollowNum : null
             * totalFansNum : null
             * systemCode : CD-CDZT000009
             * identityFlag : 1
             * province : 浙江省
             * city : 杭州市
             * area : 余杭区
             */

            private String userId;
            private String loginName;
            private String nickname;
            private String mobile;
            private String status;
            private String level;
            private String kind;
            private String realName;
            private String userReferee;
            private String idNo;
            private String divRate;
            private String tradepwdFlag;
            private String companyCode;
            private String totalFollowNum;
            private String totalFansNum;
            private String systemCode;
            private String identityFlag;
            private String province;
            private String city;
            private String area;

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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
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

            public String getDivRate() {
                return divRate;
            }

            public void setDivRate(String divRate) {
                this.divRate = divRate;
            }

            public String getTradepwdFlag() {
                return tradepwdFlag;
            }

            public void setTradepwdFlag(String tradepwdFlag) {
                this.tradepwdFlag = tradepwdFlag;
            }

            public String getCompanyCode() {
                return companyCode;
            }

            public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
            }

            public String getTotalFollowNum() {
                return totalFollowNum;
            }

            public void setTotalFollowNum(String totalFollowNum) {
                this.totalFollowNum = totalFollowNum;
            }

            public String getTotalFansNum() {
                return totalFansNum;
            }

            public void setTotalFansNum(String totalFansNum) {
                this.totalFansNum = totalFansNum;
            }

            public String getSystemCode() {
                return systemCode;
            }

            public void setSystemCode(String systemCode) {
                this.systemCode = systemCode;
            }

            public String getIdentityFlag() {
                return identityFlag;
            }

            public void setIdentityFlag(String identityFlag) {
                this.identityFlag = identityFlag;
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
        }
    }
}
