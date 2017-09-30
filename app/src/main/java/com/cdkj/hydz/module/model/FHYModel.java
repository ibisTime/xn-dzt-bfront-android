package com.cdkj.hydz.module.model;

/**
 * Created by lei on 2017/9/29.
 */

public class FHYModel {


    /**
     * id : 1
     * type : 0
     * ckey : FHY
     * cvalue : 0.7
     * updater : admin
     * updateDatetime : Sep 29, 2017 9:55:30 AM
     * remark : H+产品：会员是非会员的几倍
     * companyCode : CD-CDZT000009
     * systemCode : CD-CDZT000009
     */

    private int id;
    private String type;
    private String ckey;
    private String cvalue;
    private String updater;
    private String updateDatetime;
    private String remark;
    private String companyCode;
    private String systemCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
