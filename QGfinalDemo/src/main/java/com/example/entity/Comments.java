package com.example.entity;

public class Comments {
    //索引
    private Integer id;
    //评分
    private Integer score;
    //评价时间
    private String commentTime;
    //评价内容
    private String content;
    //交易号
    private Integer dealId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", score=" + score +
                ", commentTime='" + commentTime + '\'' +
                ", content='" + content + '\'' +
                ", dealId=" + dealId +
                '}';
    }
}
