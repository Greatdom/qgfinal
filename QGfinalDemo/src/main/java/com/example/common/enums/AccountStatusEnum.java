package com.example.common.enums;

public enum AccountStatusEnum {
    NORMAL("正常"),
    BAN("被封禁");

    private String value;

    public String getValue() {
        return value;
    }

    AccountStatusEnum(String value) {
        this.value = value;
    }
}
