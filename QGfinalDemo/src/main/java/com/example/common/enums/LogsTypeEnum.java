package com.example.common.enums;

public enum LogsTypeEnum {
    LOGIN("登录"),
    REGISTER("注册"),
    UPDATE_ACCOUNT("修改账户"),
    DELETE("删除"),
    PUBLISH_PRODUCT("发布商品"),
    UPDATE_PRODUCT("更新商品"),
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
