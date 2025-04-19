package com.example.common.enums;

public enum ProductStatusEnum {
    PUBLISHED("已发布"),
    BANED("被封禁"),
    UN_PUBLISHED("已下架"),
    NOT_PUBLISHED("未发布");

    private String value;

    public String getValue() {
        return value;
    }

    ProductStatusEnum(String value) {
        this.value = value;
    }
}
