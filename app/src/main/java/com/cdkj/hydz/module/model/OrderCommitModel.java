package com.cdkj.hydz.module.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei on 2017/8/24.
 */

public class OrderCommitModel  {

    private String eventTag;
    private List<CommitBean> commitContent = new ArrayList<>();

    public String getEventTag() {
        return eventTag;
    }

    public void setEventTag(String eventTag) {
        this.eventTag = eventTag;
    }

    public List<CommitBean> getCommitContent() {
        return commitContent;
    }

    public void setCommitContent(List<CommitBean> commitContent) {
        this.commitContent = commitContent;
    }

    public static class CommitBean{

        private String key;
        private String value;
        private BigDecimal price;
        private String remark;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
