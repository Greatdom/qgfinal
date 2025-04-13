package com.example.entity;

public class Report {
    private Integer id;
    private String reportTime;
    private String reportType;
    private String content;
    private String result;

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
                '}';
    }
}
