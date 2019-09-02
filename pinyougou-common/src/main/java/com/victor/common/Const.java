package com.victor.common;


public class Const {
    /**
     * 0：未审核   1：已审核   2：审核未通过   3：关闭
     */
    public enum ShopReviewEnum {
        UNREVIEW("0", "未审核"),
        REVIEWED("1", "已审核"),
        REVIEW_FAILED("2", "审核未通过"),
        CLOSED("3", "关闭");
        private String code;
        private String desc;

        ShopReviewEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }


    public enum GoodAuditStatu {
        APPLY("0", "未申请"),
        UNAPPLY("1", "已申请，待审核"),
        REVIEW_FAILED("2", "审核未通过"),
        APPLY_PASS("4", "审核通过"),
        CLOSED("3", "关闭");
        private String code;
        private String desc;

        GoodAuditStatu(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }


}
