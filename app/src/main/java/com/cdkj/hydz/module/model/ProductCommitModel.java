package com.cdkj.hydz.module.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei on 2017/9/15.
 */

public class ProductCommitModel {

    private String modelSpecsCode;
    private List<ValueBean> valueList = new ArrayList<>();

    public String getModelSpecsCode() {
        return modelSpecsCode;
    }

    public void setModelSpecsCode(String modelSpecsCode) {
        this.modelSpecsCode = modelSpecsCode;
    }

    public List<ValueBean> getValueList() {
        return valueList;
    }

    public void setValueList(List<ValueBean> valueList) {
        this.valueList = valueList;
    }

    public static class ValueBean {
        private BigDecimal price;
        private String code;
        private String kind;

        private String key;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
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


}
