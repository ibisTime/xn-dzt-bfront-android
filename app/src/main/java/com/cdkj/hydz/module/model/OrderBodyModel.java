package com.cdkj.hydz.module.model;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderBodyModel {

    private boolean canSelect;
    private String key = "";
    private String name = "";
    private String value = "";
    private String valueKey = "";
    private String remark = "";

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanSelect() {
        return canSelect;
    }

    public void setCanSelect(boolean canSelect) {
        this.canSelect = canSelect;
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
