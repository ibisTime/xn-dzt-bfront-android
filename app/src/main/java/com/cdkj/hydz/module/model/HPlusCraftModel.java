package com.cdkj.hydz.module.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/8/29.
 */

public class HPlusCraftModel implements Serializable {

    private BigDecimal totalPrice;
    private BigDecimal fabricPrice;
    private List<String> codeList;
    private Map<String,String> codeMap;
    private String eventBusTag;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getFabricPrice() {
        return fabricPrice;
    }

    public void setFabricPrice(BigDecimal fabricPrice) {
        this.fabricPrice = fabricPrice;
    }

    public String getEventBusTag() {
        return eventBusTag;
    }

    public void setEventBusTag(String eventBusTag) {
        this.eventBusTag = eventBusTag;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public Map<String, String> getCodeMap() {
        return codeMap;
    }

    public void setCodeMap(Map<String, String> codeMap) {
        this.codeMap = codeMap;
    }
}
