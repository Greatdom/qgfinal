package com.example.entity;

public class Session {
    //索引
    private Integer id;
    //会话创建时间
    private String sessionTime;
    //先手ID
    private Integer headId;
    //后手ID
    private Integer hindId;
    //先手角色
    private String headRole;
    //后手角色
    private String hindRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public Integer getHindId() {
        return hindId;
    }

    public void setHindId(Integer hindId) {
        this.hindId = hindId;
    }

    public String getHeadRole() {
        return headRole;
    }

    public void setHeadRole(String headRole) {
        this.headRole = headRole;
    }

    public String getHindRole() {
        return hindRole;
    }

    public void setHindRole(String hindRole) {
        this.hindRole = hindRole;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionTime='" + sessionTime + '\'' +
                ", headId=" + headId +
                ", hindId=" + hindId +
                ", headRole='" + headRole + '\'' +
                ", hindRole='" + hindRole + '\'' +
                '}';
    }
}
