package com.cdkj.hydz.module.model;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderMeasureModel {

    private boolean canInput;
    private String key = "";
    private String name = "";
    private String value = "";
    private String remark = "";

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanInput() {
        return canInput;
    }

    public void setCanInput(boolean canInput) {
        this.canInput = canInput;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
