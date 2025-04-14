package com.example.mapper;

import com.example.entity.Account;
import com.example.util.CRUDUtils;

import java.util.List;

public class AdminMapper {
    public int insert(String username, String password) {
        String insertSql = "INSERT INTO admin (username,password,status) VALUES (?, ? ,?)";
        return CRUDUtils.insert(insertSql, username, password,"正常");
    }
    public Account selectByUsername(String username) {
        String selectSql = "SELECT * FROM admin WHERE username = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, username);
    }
    public Account selectByEmail(String email) {
        String selectSql = "SELECT * FROM admin WHERE email = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, email);
    }
    public Account selectByPhone(String phone) {
        String selectSql = "SELECT * FROM admin WHERE phone = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, phone);
    }
    public List<Account> selectAll(){
        String selectSql = "SELECT * FROM admin";
        return CRUDUtils.queryForList(Account.class, selectSql);
    }
    public int update(String name,String password, String phone, String email, String avatar, String username) {
        String updateSql = "UPDATE admin SET name=?,password = ?,phone=?,email=?,avatar=? WHERE username = ?";
        return CRUDUtils.update(updateSql, name,password,phone,email,avatar,username);
    }
    public int cancelByUsername(String username) {
        String deleteSql = "UPDATE admin SER status = ? WHERE username = ?";
        return CRUDUtils.update(deleteSql, "已注销", username);
    }
    public int banByUsername(String username) {
        String deleteSql = "UPDATE admin SET status = ? WHERE username = ?";
        return CRUDUtils.update(deleteSql, "已封禁", username);
    }
}
