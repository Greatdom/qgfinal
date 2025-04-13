package com.example.entity;

public class Comments {
    //索引
    private Integer id;
    //评分
    private String score;
    //评价时间
    private String commentTime;
    //评价内容
    private String content;
    //交易号
    private String dealId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", content='" + content + '\'' +
                ", dealId='" + dealId + '\'' +
                '}';
    }
}
