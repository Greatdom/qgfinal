package com.example.entity;

public class Report {
    //索引
    private Integer id;
    //举报时间
    private String reportTime;
    //举报种类
    private String reportType;
    //举报内容
    private String content;
    //举报结果
    private String result;
    //举报者
    private Integer userId;
    //举报对象
    private Integer pointerId;

    public Integer getPointerId() {
        return pointerId;
    }

    public void setPointerId(Integer pointerId) {
        this.pointerId = pointerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportTime='" + reportTime + '\'' +
                ", reportType='" + reportType + '\'' +
                ", content='" + content + '\'' +
                ", result='" + result + '\'' +
                ", userId=" + userId +
                ", pointerId=" + pointerId +
                '}';
    }
}
