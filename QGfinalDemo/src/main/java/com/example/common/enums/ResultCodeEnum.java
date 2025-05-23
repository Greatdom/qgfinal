package com.example.common.enums;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),

    PARAM_ERROR("400", "参数异常"),
    PARAM_LOST_ERROR("4001", "参数缺失"),

    SYSTEM_ERROR("500", "系统异常"),
    USER_EXIST_ERROR("5001", "用户名已存在"),
    PARAM_EXIST_ERROR("5007","相关数据已被其他用户使用"),
    USER_NOT_LOGIN("5002", "用户未登录"),
    USER_ACCOUNT_ERROR("5003", "账号或密码错误"),
    USER_NOT_EXIST_ERROR("5004", "用户似乎不存在"),
    PARAM_PASSWORD_ERROR("5005", "原密码输入错误"),
    PRODUCT_BUY_ERROR("5008","无法购买商品"),
    BAN_ERROR("5009","您已被封禁,请进行申诉以求解封账号！-->[web.example.com]"),
    REPORT_ERROR("5010","不能重复举报"),
    AVATAR_ERROR("5011","图片上传失败"),
    CODE_ERROR("5012","验证码错误");

    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
