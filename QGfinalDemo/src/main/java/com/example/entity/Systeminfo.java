package com.example.entity;

import com.example.mapper.SysteminfoMapper;

import java.rmi.server.ExportException;

public class Systeminfo{
    private int id;
    private String role;
    private int adminNum;
    private int userNum;
    private int productNum;
    private double totalMoney;
    private int reportNum;
    private int sessionNum;
    private int dealNum;
    private int commentNum;
    private static boolean Initialized =false;
    private static volatile Systeminfo instance = null;
    private Systeminfo(){
        if(instance != null){
            throw new IllegalStateException("Singleton instance already exists!");
        }
    }
    public static Systeminfo getInstance(){
        if(!Initialized){
            SysteminfoMapper mapper = new SysteminfoMapper();
        }
        if(instance == null){
            synchronized (Systeminfo.class){
                if(instance == null){
                    System.out.println();
                    System.out.println();
                    instance = new Systeminfo();
                }
            }
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(int adminNum) {
        this.adminNum = adminNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public int getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(int sessionNum) {
        this.sessionNum = sessionNum;
    }

    public int getDealNum() {
        return dealNum;
    }

    public void setDealNum(int dealNum) {
        this.dealNum = dealNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    @Override
    public String toString() {
        return "Systeminfo{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", adminNum=" + adminNum +
                ", userNum=" + userNum +
                ", productNum=" + productNum +
                ", totalMoney=" + totalMoney +
                ", reportNum=" + reportNum +
                ", sessionNum=" + sessionNum +
                ", dealNum=" + dealNum +
                ", commentNum=" + commentNum +
                '}';
    }

    public static boolean isInitialized() {
        return Initialized;
    }

    public static void setInitialized(boolean initialized) {
        Initialized = initialized;
    }
}
