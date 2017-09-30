package com.cdkj.hydz.module.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lei on 2017/9/14.
 */

public class ProductCraftModel {

    private List<ProductCategoryListBean> productCategoryList;

    public List<ProductCategoryListBean> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategoryListBean> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public static class ProductCategoryListBean{
        /**
         * code : 145
         * kind : 0
         * type : 0
         * dkey : DYPTX
         * dvalue : 大衣驳头型
         * modelSpecsCode : MOS201709121927457932
         * updater : admin
         * updateDatetime : Sep 12, 2017 9:43:33 AM
         * craftList : [{"code":"GY201709181954075379","type":"DYPTX","name":"平驳头","pic":"default-32_1505302834313.png","selected":"default-32_1505302837383.png","isHit":"0","price":0,"status":"1","updater":"admin","updateDatetime":"Sep 18, 2017 7:54:07 PM","remark":"","modelSpecsCode":"MOS201709121927457932","modelCode":"MO201708291553303782","model":{"name":"大衣"}},{"code":"GY201709181955419906","type":"DYPTX","name":"半平驳头","pic":"default-32_1505302928396.png","selected":"default-32_1505302931366.png","isHit":"0","price":0,"status":"1","updater":"admin","updateDatetime":"Sep 18, 2017 7:55:41 PM","remark":"","modelSpecsCode":"MOS201709121927457932","modelCode":"MO201708291553303782","model":{"name":"大衣"}}]
         * colorCraftList : [{"code":"GY201709181956112351","type":"DYPTZS","name":"黄色","pic":"default-32_1505302957848.png","selected":"default-32_1505302961049.png","isHit":"1","price":0,"status":"1","updater":"admin","updateDatetime":"Sep 18, 2017 7:56:11 PM","remark":"","modelSpecsCode":"MOS201709121927457932","modelCode":"MO201708291553303782","model":{"name":"大衣"}},{"code":"GY201709181956409264","type":"DYPTZS","name":"浅灰色","pic":"default-32_1505302987810.png","selected":"default-32_1505302990778.png","isHit":"1","price":0,"status":"1","updater":"admin","updateDatetime":"Sep 18, 2017 7:56:40 PM","remark":"","modelSpecsCode":"MOS201709121927457932","modelCode":"MO201708291553303782","model":{"name":"大衣"}}]
         */

        private String code;
        private String kind;
        private String type;
        private String dkey;
        private String dvalue;
        private String modelSpecsCode;
        private String updater;
        private String updateDatetime;
        private List<CraftListBean> craftList;
        private List<ColorPcList> ColorPcList;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDkey() {
            return dkey;
        }

        public void setDkey(String dkey) {
            this.dkey = dkey;
        }

        public String getDvalue() {
            return dvalue;
        }

        public void setDvalue(String dvalue) {
            this.dvalue = dvalue;
        }

        public String getModelSpecsCode() {
            return modelSpecsCode;
        }

        public void setModelSpecsCode(String modelSpecsCode) {
            this.modelSpecsCode = modelSpecsCode;
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

        public List<CraftListBean> getCraftList() {
            return craftList;
        }

        public void setCraftList(List<CraftListBean> craftList) {
            this.craftList = craftList;
        }

        public List<ProductCategoryListBean.ColorPcList> getColorPcList() {
            return ColorPcList;
        }

        public void setColorPcList(List<ProductCategoryListBean.ColorPcList> colorPcList) {
            ColorPcList = colorPcList;
        }

        public static class CraftListBean {
            /**
             * code : GY201709181954075379
             * type : DYPTX
             * name : 平驳头
             * pic : default-32_1505302834313.png
             * selected : default-32_1505302837383.png
             * isHit : 0
             * price : 0
             * status : 1
             * updater : admin
             * updateDatetime : Sep 18, 2017 7:54:07 PM
             * remark :
             * modelSpecsCode : MOS201709121927457932
             * modelCode : MO201708291553303782
             * model : {"name":"大衣"}
             */

            private String code;
            private String type;
            private String name;
            private String pic;
            private String selected;
            private String isHit;
            private BigDecimal price;
            private String status;
            private String updater;
            private String updateDatetime;
            private String remark;
            private String modelSpecsCode;
            private String modelCode;
            private ModelBean model;
            private boolean isSelect = false;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
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

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }

            public String getIsHit() {
                return isHit;
            }

            public void setIsHit(String isHit) {
                this.isHit = isHit;
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

            public String getModelSpecsCode() {
                return modelSpecsCode;
            }

            public void setModelSpecsCode(String modelSpecsCode) {
                this.modelSpecsCode = modelSpecsCode;
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
                 * name : 大衣
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

        public static class ColorPcList{
            private String code;
            private String kind;
            private String type;
            private String parentKey;
            private String dkey;
            private String dvalue;
            private String modelSpecsCode;
            private String updater;
            private String updateDatetime;
            private List<ColorCraftListBean> colorCraftList;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getParentKey() {
                return parentKey;
            }

            public void setParentKey(String parentKey) {
                this.parentKey = parentKey;
            }

            public String getDkey() {
                return dkey;
            }

            public void setDkey(String dkey) {
                this.dkey = dkey;
            }

            public String getDvalue() {
                return dvalue;
            }

            public void setDvalue(String dvalue) {
                this.dvalue = dvalue;
            }

            public String getModelSpecsCode() {
                return modelSpecsCode;
            }

            public void setModelSpecsCode(String modelSpecsCode) {
                this.modelSpecsCode = modelSpecsCode;
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

            public List<ColorCraftListBean> getColorCraftList() {
                return colorCraftList;
            }

            public void setColorCraftList(List<ColorCraftListBean> colorCraftList) {
                this.colorCraftList = colorCraftList;
            }

            public static class ColorCraftListBean {
                /**
                 * code : GY201709181956112351
                 * type : DYPTZS
                 * name : 黄色
                 * pic : default-32_1505302957848.png
                 * selected : default-32_1505302961049.png
                 * isHit : 1
                 * price : 0
                 * status : 1
                 * updater : admin
                 * updateDatetime : Sep 18, 2017 7:56:11 PM
                 * remark :
                 * modelSpecsCode : MOS201709121927457932
                 * modelCode : MO201708291553303782
                 * model : {"name":"大衣"}
                 */

                private String code;
                private String type;
                private String name;
                private String pic;
                private String selected;
                private String isHit;
                private BigDecimal price;
                private String status;
                private String updater;
                private String updateDatetime;
                private String remark;
                private String modelSpecsCode;
                private String modelCode;
                private ModelBeanX model;
                private boolean isSelect;

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
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

                public String getSelected() {
                    return selected;
                }

                public void setSelected(String selected) {
                    this.selected = selected;
                }

                public String getIsHit() {
                    return isHit;
                }

                public void setIsHit(String isHit) {
                    this.isHit = isHit;
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

                public String getModelSpecsCode() {
                    return modelSpecsCode;
                }

                public void setModelSpecsCode(String modelSpecsCode) {
                    this.modelSpecsCode = modelSpecsCode;
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

                public static class ModelBeanX {
                    /**
                     * name : 大衣
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

    }
}
