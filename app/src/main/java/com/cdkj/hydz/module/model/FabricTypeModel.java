package com.cdkj.hydz.module.model;

/**
 * Created by lei on 2017/9/18.
 */

public class FabricTypeModel {

    private String key;
    private String value;
    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
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
