package com.cdkj.hydz.module.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/8/24.
 */

public class ProductModel {


    /**
     * code : MO201708211548546234
     * type : 1
     * name : 马甲
     * pic : 下载_1503301636446.jpg
     * advPic : 下载_1503301632322.jpg
     * description :
     * loss : 0.78
     * processFee : 200000
     * price : 0
     * location : 1
     * orderNo : 6
     * status : 1
     * createDatetime : Sep 11, 2017 11:12:28 AM
     * updater : admin
     * updateDatetime : Sep 18, 2017 12:46:09 PM
     * remark :
     * modelSpecsList : [{"code":"MOS201709181201222042","name":"马甲","pic":"2_1505274544105.jpg","updater":"admin","updateDatetime":"Sep 18, 2017 12:01:22 PM","remark":"","modelCode":"MO201708211548546234","res":{"clothList":[],"productCategoryList":[{"code":"138","type":"0","dkey":"MJLX","dvalue":"马甲领型","modelSpecsCode":"MOS201709181201222042","updater":"admin","updateDatetime":"Sep 12, 2017 9:43:33 AM","craftList":[]},{"code":"139","type":"0","dkey":"MJKS","dvalue":"马甲扣数","modelSpecsCode":"MOS201709181201222042","updater":"admin","updateDatetime":"Sep 12, 2017 9:43:33 AM","craftList":[]},{"code":"140","type":"0","dkey":"MJXKD","dvalue":"马甲胸口袋","modelSpecsCode":"MOS201709181201222042","updater":"admin","updateDatetime":"Sep 12, 2017 9:43:33 AM","craftList":[]},{"code":"141","type":"0","dkey":"MJXKD","dvalue":"马甲下口袋","modelSpecsCode":"MOS201709181201222042","updater":"admin","updateDatetime":"Sep 12, 2017 9:43:33 AM","craftList":[]},{"code":"142","type":"0","dkey":"MJXKX","dvalue":"马甲下口型","modelSpecsCode":"MOS201709181201222042","updater":"admin","updateDatetime":"Sep 12, 2017 9:43:33 AM","craftList":[]},{"code":"143","type":"0","dkey":"MJHBYD","dvalue":"马甲后背腰带","modelSpecsCode":"MOS201709181201222042","updater":"admin","updateDatetime":"Sep 12, 2017 9:43:33 AM","craftList":[]}]}}]
     */

    private String code;
    private String type;
    private String name;
    private String pic;
    private String advPic;
    private String description;
    private double loss;
    private int processFee;
    private int price;
    private String location;
    private int orderNo;
    private boolean select;
    private String status;
    private String createDatetime;
    private String updater;
    private String updateDatetime;
    private String remark;
    private List<ModelSpecsListBean> modelSpecsList;
    private String eventBusTag;

    public String getEventBusTag() {
        return eventBusTag;
    }

    public void setEventBusTag(String eventBusTag) {
        this.eventBusTag = eventBusTag;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public int getProcessFee() {
        return processFee;
    }

    public void setProcessFee(int processFee) {
        this.processFee = processFee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
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

    public List<ModelSpecsListBean> getModelSpecsList() {
        return modelSpecsList;
    }

    public void setModelSpecsList(List<ModelSpecsListBean> modelSpecsList) {
        this.modelSpecsList = modelSpecsList;
    }

    public static class ModelSpecsListBean {
        /**
         * code : MOS201709181201222042
         * name : 马甲
         * pic : 2_1505274544105.jpg
         * updater : admin
         * updateDatetime : Sep 18, 2017 12:01:22 PM
         * remark :
         * modelCode : MO201708211548546234
         */

        private String code;
        private String name;
        private String pic;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;

        private String clothCode;
        private Map<String,String> map = new HashMap<>();
        private List<String> codeList = new ArrayList<>();
        private BigDecimal craftPrice = new BigDecimal(0);
        private BigDecimal fabricPrice = new BigDecimal(0);

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public String getClothCode() {
            return clothCode;
        }

        public void setClothCode(String clothCode) {
            this.clothCode = clothCode;
        }

        public List<String> getCodeList() {
            return codeList;
        }

        public void setCodeList(List<String> codeList) {
            this.codeList = codeList;
        }

        public BigDecimal getFabricPrice() {
            return fabricPrice;
        }

        public void setFabricPrice(BigDecimal fabricPrice) {
            this.fabricPrice = fabricPrice;
        }

        public BigDecimal getCraftPrice() {
            return craftPrice;
        }

        public void setCraftPrice(BigDecimal craftPrice) {
            this.craftPrice = craftPrice;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public String getModelCode() {
            return modelCode;
        }

        public void setModelCode(String modelCode) {
            this.modelCode = modelCode;
        }

    }
}
