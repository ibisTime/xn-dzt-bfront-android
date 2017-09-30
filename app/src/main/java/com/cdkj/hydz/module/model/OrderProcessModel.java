package com.cdkj.hydz.module.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lei on 2017/8/24.
 */

public class OrderProcessModel {

    @JSONField(name = "5-04")
    private List<_$504Bean> _$504;
    @JSONField(name = "1-05")
    private List<_$105Bean> _$105;
    @JSONField(name = "1-04")
    private List<_$104Bean> _$104;
    @JSONField(name = "1-07")
    private List<_$107Bean> _$107;
    @JSONField(name = "1-06")
    private List<_$106Bean> _$106;
    @JSONField(name = "1-08")
    private List<_$108Bean> _$108;
    @JSONField(name = "5-02")
    private List<_$502Bean> _$502;
    @JSONField(name = "5-03")
    private List<_$503Bean> _$503;
    @JSONField(name = "1-10")
    private List<_$1010Bean> _$1010;
    @JSONField(name = "1-01")
    private List<_$101Bean> _$101;
    @JSONField(name = "1-03")
    private List<_$103Bean> _$103;

    public List<_$504Bean> get_$504() {
        return _$504;
    }

    public void set_$504(List<_$504Bean> _$504) {
        this._$504 = _$504;
    }

    public List<_$105Bean> get_$105() {
        return _$105;
    }

    public void set_$105(List<_$105Bean> _$105) {
        this._$105 = _$105;
    }

    public List<_$104Bean> get_$104() {
        return _$104;
    }

    public void set_$104(List<_$104Bean> _$104) {
        this._$104 = _$104;
    }

    public List<_$107Bean> get_$107() {
        return _$107;
    }

    public void set_$107(List<_$107Bean> _$107) {
        this._$107 = _$107;
    }

    public List<_$106Bean> get_$106() {
        return _$106;
    }

    public void set_$106(List<_$106Bean> _$106) {
        this._$106 = _$106;
    }

    public List<_$108Bean> get_$108() {
        return _$108;
    }

    public void set_$108(List<_$108Bean> _$108) {
        this._$108 = _$108;
    }

    public List<_$502Bean> get_$502() {
        return _$502;
    }

    public void set_$502(List<_$502Bean> _$502) {
        this._$502 = _$502;
    }

    public List<_$503Bean> get_$503() {
        return _$503;
    }

    public void set_$503(List<_$503Bean> _$503) {
        this._$503 = _$503;
    }

    public List<_$1010Bean> get_$1010() {
        return _$1010;
    }

    public void set_$1010(List<_$1010Bean> _$1010) {
        this._$1010 = _$1010;
    }

    public List<_$101Bean> get_$101() {
        return _$101;
    }

    public void set_$101(List<_$101Bean> _$101) {
        this._$101 = _$101;
    }

    public List<_$103Bean> get_$103() {
        return _$103;
    }

    public void set_$103(List<_$103Bean> _$103) {
        this._$103 = _$103;
    }

    public static class _$504Bean {
        /**
         * code : GY201708181139192884
         * type : 5-4
         * name : 紫色
         * pic : param_25_a_1493950978966.jpg
         * price : 0
         * location : 1
         * orderNo : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 18, 2017 11:39:19 AM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private BigDecimal price;
        private String location;
        private int orderNo;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBean model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
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

        public ModelBean getModel() {
            return model;
        }

        public void setModel(ModelBean model) {
            this.model = model;
        }

        public static class ModelBean {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$105Bean {
        /**
         * code : GY00037
         * type : 1-5
         * name : 翻门襟
         * pic : 领型袖口矢量图方格图-未选中-03_1503461027508.png
         * advPic : 领型袖口矢量图方格图-选中-03_1503483297882.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 6:16:28 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         * location : 1
         * orderNo : 0
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private String advPic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanX model;
        private String location;
        private int orderNo;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanX getModel() {
            return model;
        }

        public void setModel(ModelBeanX model) {
            this.model = model;
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

        public static class ModelBeanX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$104Bean {
        /**
         * code : GY00035
         * type : 1-4
         * name : 六角单扣
         * pic : 领型袖口矢量图方格图-未选中-15_1503461724347.png
         * advPic : 领型袖口矢量图方格图-选中-15_1503482687042.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 6:06:16 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private String advPic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXX model) {
            this.model = model;
        }

        public static class ModelBeanXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$107Bean {
        /**
         * code : GY00042
         * type : 1-7
         * name : 通用款式
         * pic : 领型袖口矢量图方格图-未选中-32_1503464262540.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 12:59:12 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$106Bean {
        /**
         * code : GY00036
         * type : 1-6
         * name : 明门襟
         * pic : i14_1493949692856.png
         * advPic : 领型袖口矢量图方格图-选中-04_1503482718827.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 6:06:50 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private String advPic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$108Bean {
        /**
         * code : GY201708231415144228
         * type : 1-8
         * name : 非常修身
         * pic : 领型袖口矢量图方格图-无字-04_1503468824871.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 2:15:14 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXXXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$502Bean {
        /**
         * code : GY201708231424010161
         * type : 5-2
         * name : 左袖头外
         * pic : 领型袖口矢量图方格图-无字-15_1503469351807.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 2:24:01 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXXXXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXXXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXXXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXXXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$503Bean {
        /**
         * code : GY00066
         * type : 5-3
         * name : A1号
         * pic : param_24_a1_1493950892842.jpg
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 10:10:50 AM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXXXXXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXXXXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXXXXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXXXXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$1010Bean {
        /**
         * code : GY00135
         * type : 1-10
         * name : 短袖袖型
         * pic : xiu-short_1493949666799.jpg
         * status : 0
         * updater : admin
         * updateDatetime : Aug 16, 2017 8:06:10 PM
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         * price : 0
         * location : 1
         * orderNo : 0
         * remark :
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private String status;
        private String updater;
        private String updateDatetime;
        private String modelCode;
        private ModelBeanXXXXXXXXX model;
        private BigDecimal price;
        private String location;
        private int orderNo;
        private String remark;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getModelCode() {
            return modelCode;
        }

        public void setModelCode(String modelCode) {
            this.modelCode = modelCode;
        }

        public ModelBeanXXXXXXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXXXXXXX model) {
            this.model = model;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public static class ModelBeanXXXXXXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$101Bean {
        /**
         * code : GY201708231351269380
         * type : 1-1
         * name : 长袖
         * pic : 领型袖口矢量图方格图-未选中-01_1503467396404.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 1:51:26 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXXXXXXXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXXXXXXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXXXXXXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXXXXXXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class _$103Bean {
        /**
         * code : GY00144
         * type : 1-3
         * name : 标准领
         * pic : 领型袖口矢量图方格图-未选中-06_1503461154668.png
         * advPic : 领型袖口矢量图方格图-选中-06_1503482746193.png
         * price : 0
         * status : 0
         * updater : admin
         * updateDatetime : Aug 23, 2017 6:07:16 PM
         * remark :
         * modelCode : MO201708161448405541
         * model : {"name":"花花公子衬衫 短袖"}
         */

        private String code;
        private String type;
        private String name;
        private String pic;
        private String advPic;
        private BigDecimal price;
        private String status;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String modelCode;
        private ModelBeanXXXXXXXXXXX model;

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

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public ModelBeanXXXXXXXXXXX getModel() {
            return model;
        }

        public void setModel(ModelBeanXXXXXXXXXXX model) {
            this.model = model;
        }

        public static class ModelBeanXXXXXXXXXXX {
            /**
             * name : 花花公子衬衫 短袖
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
