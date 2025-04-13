package com.example.entity;

public class Deal {
    //索引
    private int id;
    //支付方式
    private String payMethod;
    //订单状态
    private String dealStatus;
    //交易时间
    private String dealTime;
    //购买者
    private Integer userId;
    //交易商品
    private Integer productId;
    //购买数量
    private Integer productNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", payMethod='" + payMethod + '\'' +
                ", dealStatus='" + dealStatus + '\'' +
                ", dealTime='" + dealTime + '\'' +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                '}';
    }
}
