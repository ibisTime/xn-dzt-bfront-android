package com.cdkj.hydz.module.model;

import java.math.BigDecimal;

/**
 * Created by lei on 2017/8/23.
 */

public class OrderCraftModel {

    private boolean isSelect;
    private String name;
    private String key;
    private String img;
    private String code;
    private BigDecimal price;
    private String eventBusTag;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEventBusTag() {
        return eventBusTag;
    }

    public void setEventBusTag(String eventBusTag) {
        this.eventBusTag = eventBusTag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}