package com.example.entity;

public class Log {
    //索引
    private Integer id;
    //操作内容
    private String operateContent;
    //操作种类
    private String operateType;
    //操作者账号
    private String operateUsername;
    //操作者客机IP
    private String ip;
    //操作时间
    private String operateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUsername() {
        return operateUsername;
    }

    public void setOperateUsername(String operateUsername) {
        this.operateUsername = operateUsername;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", operateContent='" + operateContent + '\'' +
                ", operateType='" + operateType + '\'' +
                ", operateUsername='" + operateUsername + '\'' +
                ", ip='" + ip + '\'' +
                ", operateTime='" + operateTime + '\'' +
                '}';
    }
}
