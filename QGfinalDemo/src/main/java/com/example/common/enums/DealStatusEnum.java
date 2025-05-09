package com.example.common.enums;

public enum DealStatusEnum {
    BUY("待发货"),
    PACK("在发货"),
    SEND("待收货"),
    RECEIVE("已收货"),
    CANCEL("已退货");

    private String value;

    public String getValue() {
        return value;
    }

    DealStatusEnum(String value) {
        this.value = value;
    }
}
