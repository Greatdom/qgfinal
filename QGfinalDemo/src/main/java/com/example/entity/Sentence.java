package com.example.entity;

public class Sentence {
    //索引
    private Integer id;
    //聊天者
    private Integer userId;
    //信息时间
    private String sentenceTime;
    //消息内容
    private String content;
    //聊天者角色
    private String userRole;
    //隶属会话
    private Integer sessionId;

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSentenceTime() {
        return sentenceTime;
    }

    public void setSentenceTime(String sentenceTime) {
        this.sentenceTime = sentenceTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", userId=" + userId +
                ", sentenceTime='" + sentenceTime + '\'' +
                ", content='" + content + '\'' +
                ", userRole='" + userRole + '\'' +
                ", sessionId=" + sessionId +
                '}';
    }
}
