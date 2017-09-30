package com.cdkj.hydz.module.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lei on 2017/8/29.
 */

public class ManModel {


    /**
     * realName : 山
     * mobile : 13110992818
     * jfAmount : 0
     * sjAmount : 0
     * jyAmount : 0
     * days : 1
     * address : 1111111111111111111
     * sizeDataList : [{"id":"94","userId":"U000000000001","ckey":"2-01","cvalue":"领围","dkey":"10"},{"id":"95","userId":"U000000000001","ckey":"2-02","cvalue":"胸围","dkey":"10"},{"id":"96","userId":"U000000000001","ckey":"2-03","cvalue":"中腰围","dkey":"10"},{"id":"97","userId":"U000000000001","ckey":"2-04","cvalue":"裤腰围","dkey":"10"},{"id":"98","userId":"U000000000001","ckey":"2-05","cvalue":"臀围","dkey":"10"},{"id":"99","userId":"U000000000001","ckey":"2-06","cvalue":"大腿围","dkey":"10"},{"id":"100","userId":"U000000000001","ckey":"2-07","cvalue":"通档","dkey":"10"},{"id":"101","userId":"U000000000001","ckey":"2-08","cvalue":"臀围","dkey":"10"},{"id":"102","userId":"U000000000001","ckey":"2-09","cvalue":"总肩宽","dkey":"10"},{"id":"103","userId":"U000000000001","ckey":"2-10","cvalue":"袖长","dkey":"10"},{"id":"104","userId":"U000000000001","ckey":"2-11","cvalue":"前肩宽","dkey":"10"},{"id":"105","userId":"U000000000001","ckey":"2-12","cvalue":"后腰节长","dkey":"10"},{"id":"106","userId":"U000000000001","ckey":"2-13","cvalue":"后腰高","dkey":"10"},{"id":"107","userId":"U000000000001","ckey":"2-14","cvalue":"后衣高","dkey":"10"},{"id":"108","userId":"U000000000001","ckey":"2-15","cvalue":"前腰节长","dkey":"10"},{"id":"109","userId":"U000000000001","ckey":"2-16","cvalue":"前腰高","dkey":"1010"},{"id":"110","userId":"U000000000001","ckey":"2-17","cvalue":"裤长","dkey":"10"},{"id":"111","userId":"U000000000001","ckey":"2-18","cvalue":"小腿围","dkey":"10"},{"id":"112","userId":"U000000000001","ckey":"2-19","cvalue":"前胸宽","dkey":"10"},{"id":"113","userId":"U000000000001","ckey":"2-20","cvalue":"后背宽","dkey":"10"},{"id":"114","userId":"U000000000001","ckey":"2-21","cvalue":"腹围","dkey":"10"},{"id":"115","userId":"U000000000001","ckey":"2-22","cvalue":"小臂围","dkey":"10"},{"id":"116","userId":"U000000000001","ckey":"2-23","cvalue":"前衣长","dkey":"10"},{"id":"117","userId":"U000000000001","ckey":"2-24","cvalue":"腕围","dkey":"10"},{"id":"118","userId":"U000000000001","ckey":"4-01","cvalue":"体型","dkey":"A","dvalue":"正常体型"},{"id":"119","userId":"U000000000001","ckey":"4-02","cvalue":"背型","dkey":"B","dvalue":"驼背"},{"id":"120","userId":"U000000000001","ckey":"4-03","cvalue":"左肩","dkey":"B","dvalue":"偏高"},{"id":"121","userId":"U000000000001","ckey":"4-04","cvalue":"右肩","dkey":"A","dvalue":"正常"},{"id":"122","userId":"U000000000001","ckey":"4-05","cvalue":"脖子","dkey":"B","dvalue":"短脖"},{"id":"123","userId":"U000000000001","ckey":"4-06","cvalue":"肤色","dkey":"C","dvalue":"偏黑"},{"id":"124","userId":"U000000000001","ckey":"4-07","cvalue":"肚型","dkey":"A","dvalue":"正常"},{"id":"125","userId":"U000000000001","ckey":"4-08","cvalue":"色彩","dkey":"C","dvalue":"正常"},{"id":"126","userId":"U000000000001","ckey":"4-09","cvalue":"手臂","dkey":"B","dvalue":"偏长"},{"id":"127","userId":"U000000000001","ckey":"4-10","cvalue":"对比","dkey":"C","dvalue":"弱对比"},{"id":"128","userId":"U000000000001","ckey":"4-11","cvalue":"臀型","dkey":"B","dvalue":"偏胖"},{"id":"129","userId":"U000000000001","ckey":"4-12","cvalue":"量感","dkey":"C","dvalue":"少量感"},{"id":"130","userId":"U000000000001","ckey":"6-02","cvalue":"身高","dkey":"173"},{"id":"131","userId":"U000000000001","ckey":"6-03","cvalue":"身高","dkey":"73"}]
     */


    private String address;
    private String birthday;
    private String realName;
    private String nickName;
    private String mobile;
    private BigDecimal conAmount;
    private BigDecimal jfAmount;
    private BigDecimal sjAmount;
    private BigDecimal jyAmount;
    private int days;
    private String level;
    private SysDictMapBean sysDictMap;

    public SysDictMapBean getSysDictMap() {
        return sysDictMap;
    }

    public void setSysDictMap(SysDictMapBean sysDictMap) {
        this.sysDictMap = sysDictMap;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getConAmount() {
        return conAmount;
    }

    public void setConAmount(BigDecimal conAmount) {
        this.conAmount = conAmount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getJfAmount() {
        return jfAmount;
    }

    public void setJfAmount(BigDecimal jfAmount) {
        this.jfAmount = jfAmount;
    }

    public BigDecimal getSjAmount() {
        return sjAmount;
    }

    public void setSjAmount(BigDecimal sjAmount) {
        this.sjAmount = sjAmount;
    }

    public BigDecimal getJyAmount() {
        return jyAmount;
    }

    public void setJyAmount(BigDecimal jyAmount) {
        this.jyAmount = jyAmount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class SysDictMapBean {
        private List<OtherBean> other;
        private List<FigureBean> figure;
        private List<MeasureBean> measure;

        public List<OtherBean> getOther() {
            return other;
        }

        public void setOther(List<OtherBean> other) {
            this.other = other;
        }

        public List<FigureBean> getFigure() {
            return figure;
        }

        public void setFigure(List<FigureBean> figure) {
            this.figure = figure;
        }

        public List<MeasureBean> getMeasure() {
            return measure;
        }

        public void setMeasure(List<MeasureBean> measure) {
            this.measure = measure;
        }

        public static class OtherBean {
            /**
             * id : 95.0
             * type : 1
             * parentKey : other
             * dkey : 6-02
             * dvalue : 身高
             * updater : admin
             * updateDatetime : Sep 28, 2017 5:34:35 PM
             * remark : 1
             * systemCode : CD-CDZT000009
             */

            private double id;
            private String type;
            private String parentKey;
            private String dkey;
            private String dvalue;
            private String updater;
            private String updateDatetime;
            private String remark;
            private String systemCode;
            private OrderSizeDataBean orderSizeData;

            public double getId() {
                return id;
            }

            public void setId(double id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getParentKey() {
                return parentKey;
            }

            public void setParentKey(String parentKey) {
                this.parentKey = parentKey;
            }

            public String getDkey() {
                return dkey;
            }

            public void setDkey(String dkey) {
                this.dkey = dkey;
            }

            public String getDvalue() {
                return dvalue;
            }

            public void setDvalue(String dvalue) {
                this.dvalue = dvalue;
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

            public String getSystemCode() {
                return systemCode;
            }

            public void setSystemCode(String systemCode) {
                this.systemCode = systemCode;
            }

            public OrderSizeDataBean getOrderSizeData() {
                return orderSizeData;
            }

            public void setOrderSizeData(OrderSizeDataBean orderSizeData) {
                this.orderSizeData = orderSizeData;
            }

            public static class OrderSizeDataBean {
                /**
                 * id : 875
                 * orderCode : DD201709291139525348
                 * ckey : 6-02
                 * cvalue : 身高
                 * dkey : 180
                 */

                private String id;
                private String orderCode;
                private String ckey;
                private String cvalue;
                private String dkey;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getOrderCode() {
                    return orderCode;
                }

                public void setOrderCode(String orderCode) {
                    this.orderCode = orderCode;
                }

                public String getCkey() {
                    return ckey;
                }

                public void setCkey(String ckey) {
                    this.ckey = ckey;
                }

                public String getCvalue() {
                    return cvalue;
                }

                public void setCvalue(String cvalue) {
                    this.cvalue = cvalue;
                }

                public String getDkey() {
                    return dkey;
                }

                public void setDkey(String dkey) {
                    this.dkey = dkey;
                }
            }
        }

        public static class FigureBean {
            /**
             * id : 81.0
             * type : 1
             * parentKey : figure
             * dkey : 4-01
             * dvalue : 左肩
             * updater : admin
             * updateDatetime : Sep 11, 2017 11:12:26 AM
             * remark : 1
             * systemCode : CD-CDZT000009
             * sizeData : {"id":"737","userId":"U201709121404113798366","ckey":"4-01","cvalue":"左肩","dkey":"F","dvalue":"F"}
             */

            private double id;
            private String type;
            private String parentKey;
            private String dkey;
            private String dvalue;
            private String updater;
            private String updateDatetime;
            private String remark;
            private String systemCode;
            private SizeDataBean sizeData;

            public double getId() {
                return id;
            }

            public void setId(double id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getParentKey() {
                return parentKey;
            }

            public void setParentKey(String parentKey) {
                this.parentKey = parentKey;
            }

            public String getDkey() {
                return dkey;
            }

            public void setDkey(String dkey) {
                this.dkey = dkey;
            }

            public String getDvalue() {
                return dvalue;
            }

            public void setDvalue(String dvalue) {
                this.dvalue = dvalue;
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

            public String getSystemCode() {
                return systemCode;
            }

            public void setSystemCode(String systemCode) {
                this.systemCode = systemCode;
            }

            public SizeDataBean getSizeData() {
                return sizeData;
            }

            public void setSizeData(SizeDataBean sizeData) {
                this.sizeData = sizeData;
            }

            public static class SizeDataBean {
                /**
                 * id : 737
                 * userId : U201709121404113798366
                 * ckey : 4-01
                 * cvalue : 左肩
                 * dkey : F
                 * dvalue : F
                 */

                private String id;
                private String userId;
                private String ckey;
                private String cvalue;
                private String dkey;
                private String dvalue;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getCkey() {
                    return ckey;
                }

                public void setCkey(String ckey) {
                    this.ckey = ckey;
                }

                public String getCvalue() {
                    return cvalue;
                }

                public void setCvalue(String cvalue) {
                    this.cvalue = cvalue;
                }

                public String getDkey() {
                    return dkey;
                }

                public void setDkey(String dkey) {
                    this.dkey = dkey;
                }

                public String getDvalue() {
                    return dvalue;
                }

                public void setDvalue(String dvalue) {
                    this.dvalue = dvalue;
                }
            }
        }

        public static class MeasureBean {
            /**
             * id : 57.0
             * type : 1
             * parentKey : measure
             * dkey : 2-01
             * dvalue : 领围
             * updater : admin
             * updateDatetime : Sep 11, 2017 11:12:25 AM
             * remark : 1
             * systemCode : CD-CDZT000009
             * sizeData : {"id":"711","userId":"U201709121404113798366","ckey":"2-01","cvalue":"领围","dkey":"1"}
             */

            private double id;
            private String type;
            private String parentKey;
            private String dkey;
            private String dvalue;
            private String updater;
            private String updateDatetime;
            private String remark;
            private String systemCode;
            private SizeDataBeanX sizeData;

            public double getId() {
                return id;
            }

            public void setId(double id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getParentKey() {
                return parentKey;
            }

            public void setParentKey(String parentKey) {
                this.parentKey = parentKey;
            }

            public String getDkey() {
                return dkey;
            }

            public void setDkey(String dkey) {
                this.dkey = dkey;
            }

            public String getDvalue() {
                return dvalue;
            }

            public void setDvalue(String dvalue) {
                this.dvalue = dvalue;
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

            public String getSystemCode() {
                return systemCode;
            }

            public void setSystemCode(String systemCode) {
                this.systemCode = systemCode;
            }

            public SizeDataBeanX getSizeData() {
                return sizeData;
            }

            public void setSizeData(SizeDataBeanX sizeData) {
                this.sizeData = sizeData;
            }

            public static class SizeDataBeanX {
                /**
                 * id : 711
                 * userId : U201709121404113798366
                 * ckey : 2-01
                 * cvalue : 领围
                 * dkey : 1
                 */

                private String id;
                private String userId;
                private String ckey;
                private String cvalue;
                private String dkey;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getCkey() {
                    return ckey;
                }

                public void setCkey(String ckey) {
                    this.ckey = ckey;
                }

                public String getCvalue() {
                    return cvalue;
                }

                public void setCvalue(String cvalue) {
                    this.cvalue = cvalue;
                }

                public String getDkey() {
                    return dkey;
                }

                public void setDkey(String dkey) {
                    this.dkey = dkey;
                }
            }
        }
    }

}
