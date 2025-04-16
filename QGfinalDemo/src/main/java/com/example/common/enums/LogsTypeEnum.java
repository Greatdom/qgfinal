package com.example.common.enums;

public enum LogsTypeEnum {
    LOGIN("登录"),
    REGISTER("注册"),
    UPDATE_PASSWORD("修改密码"),
    UPDATE("修改"),
    DELETE("删除"),
    PRODUCT("发布商品"),
    DEAL("购买商品"),
    COMMENT("评论操作");

    private String value;

    public String getValue() {
        return value;
    }

    LogsTypeEnum(String value) {
        this.value = value;
    }
}
