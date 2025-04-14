package com.example.mapper;

import com.example.entity.Account;
import com.example.util.CRUDUtils;

import java.util.List;

public class UserMapper {
    public int insert(String username, String password) {
        String insertSql = "INSERT INTO user (username,password,status) VALUES (?, ? ,?)";
        return CRUDUtils.insert(insertSql, username, password,"正常");
    }
public Account selectSingle(Account account) {
    String username = account.getUsername();
    String email = account.getEmail();
    String phone = account.getPhone();
    Integer id = account.getId();
    if(id!=null&&id>0){
        String selectSql ="SELECT * FROM user WHERE id = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, id);
    }else
    if(username==null&&email==null&&phone==null){
        return null;
    }else if(username==null&&email==null){
        String selectSql = "SELECT * FROM user WHERE phone = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, phone);
    }else if(username==null&&phone==null){
        String selectSql = "SELECT * FROM user WHERE email = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, email);
    }else if(phone==null&&email==null){
        String selectSql = "SELECT * FROM user WHERE username = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, username);
    }else if(username==null){
        String selectSql = "SELECT * FROM user WHERE phone = ? and email = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, phone, email);
    }else if(email==null){
        String selectSql = "SELECT * FROM user WHERE username = ? and phone = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, username, phone);
    }else if(phone==null){
        String selectSql = "SELECT * FROM user WHERE username = ? and email = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, username, email);
    }else{
        String selectSql = "SELECT * FROM user WHERE phone = ? and email = ? and username = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, phone, email, username);
    }
}
    public List<Account> selectAll(){
        String selectSql = "SELECT * FROM user";
        return CRUDUtils.queryForList(Account.class, selectSql);
    }
    public int update(Account account) {
        String name=account.getName();
        String password=account.getPassword();
        String phone=account.getPhone();
        String email=account.getEmail();
        String avatar=account.getAvatar();
        String payPassword=account.getPayPassword();
        String username=account.getUsername();
        String updateSql = "UPDATE user SET name=?,password = ?,phone=?,email=?,avatar=?,pay_password=? WHERE username = ?";
        return CRUDUtils.update(updateSql, name,password,phone,email,avatar,payPassword,username);
    }
    public int ChangeStatus(Account account) {
        if (account==null||account.getStatus()==null){
            return 0;
        }
        if(account.getUsername()!=null){
            String updateSql = "UPDATE user SET status=? WHERE username = ?";
            return CRUDUtils.update(updateSql, account.getStatus(),account.getUsername());
        }else if(account.getId()!=null){
            String updateSql = "UPDATE user SET status=? WHERE id = ?";
            return CRUDUtils.update(updateSql, account.getStatus(),account.getId());
        }else return 0;
    }
    public int increasePopularity(Account account) {
        Integer popularity=account.getPopularity();
        popularity = popularity == null ? 0 : popularity;
        popularity++;
        String username = account.getUsername();
        String updateSql="UPDATE user SET popularity = ? WHERE username = ?";
        return CRUDUtils.update(updateSql, popularity, username);
    }
    public int affectReputation(Account account,Integer score) {
        Integer reputation=account.getReputation();
        String username = account.getUsername();
        reputation = reputation == null ? 0 : reputation;
        score = score == null ? 0 : score;
        reputation+=score;
        String updateSql="UPDATE user SET reputation = ? WHERE username = ?";
        return CRUDUtils.update(updateSql, reputation, username);
    }
}
