package com.cdkj.hydz.module.model;

import java.util.List;

/**
 * Created by lei on 2017/8/22.
 */

public class NoticeModel {


    /**
     * pageNO : 1
     * start : 0
     * pageSize : 10
     * totalCount : 3
     * totalPage : 1
     * list : [{"code":"JL2017081710472820694","type":"1","commenter":"U201708171137236185567","receiver":"U201708171530061085458","content":"1111111125","commentDatetime":"Aug 22, 2017 12:49:09 PM","orderNo":5,"status":"0","commentName":"szz","commentMobile":"15738777150","receiveName":"宋真真","receiveMobile":"15738777150"},{"code":"JL2017081710472820692","type":"1","commenter":"U201708171137236185567","receiver":"U201708171530061085458","content":"1111111123","commentDatetime":"Aug 22, 2017 12:49:04 PM","orderNo":3,"status":"0","commentName":"szz","commentMobile":"15738777150","receiveName":"宋真真","receiveMobile":"15738777150"},{"code":"JL201708171047282069","type":"1","commenter":"U201708171137236185567","receiver":"U201708171530061085458","content":"11111","commentDatetime":"Aug 22, 2017 12:40:18 PM","orderNo":1,"status":"0","commentName":"szz","commentMobile":"15738777150","receiveName":"宋真真","receiveMobile":"15738777150"}]
     */

    private int pageNO;
    private int start;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<ListBean> list;

    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int pageNO) {
        this.pageNO = pageNO;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * code : JL2017081710472820694
         * type : 1
         * commenter : U201708171137236185567
         * receiver : U201708171530061085458
         * content : 1111111125
         * commentDatetime : Aug 22, 2017 12:49:09 PM
         * orderNo : 5
         * status : 0
         * commentName : szz
         * commentMobile : 15738777150
         * receiveName : 宋真真
         * receiveMobile : 15738777150
         */

        private String code;
        private String type;
        private String commenter;
        private String receiver;
        private String content;
        private String commentDatetime;
        private int orderNo;
        private String status;
        private String commentName;
        private String commentMobile;
        private String commentPhoto = "";
        private String receiveName;
        private String receiveMobile;
        private String receivePhoto;

        public String getCommentPhoto() {
            return commentPhoto;
        }

        public void setCommentPhoto(String commentPhoto) {
            this.commentPhoto = commentPhoto;
        }

        public String getReceivePhoto() {
            return receivePhoto;
        }

        public void setReceivePhoto(String receivePhoto) {
            this.receivePhoto = receivePhoto;
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

        public String getCommenter() {
            return commenter;
        }

        public void setCommenter(String commenter) {
            this.commenter = commenter;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCommentDatetime() {
            return commentDatetime;
        }

        public void setCommentDatetime(String commentDatetime) {
            this.commentDatetime = commentDatetime;
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

        public String getCommentName() {
            return commentName;
        }

        public void setCommentName(String commentName) {
            this.commentName = commentName;
        }

        public String getCommentMobile() {
            return commentMobile;
        }

        public void setCommentMobile(String commentMobile) {
            this.commentMobile = commentMobile;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getReceiveMobile() {
            return receiveMobile;
        }

        public void setReceiveMobile(String receiveMobile) {
            this.receiveMobile = receiveMobile;
        }
    }
}
