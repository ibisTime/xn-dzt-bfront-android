package com.cdkj.hydz.module.model;

import java.math.BigDecimal;

/**
 * Created by lei on 2017/9/15.
 */

public class ProductFabricCommitModel {

    private String modelSpecsCode;
    private BigDecimal price;
    private String code;

    public String getModelSpecsCode() {
        return modelSpecsCode;
    }

    public void setModelSpecsCode(String modelSpecsCode) {
        this.modelSpecsCode = modelSpecsCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
