package com.example.mapper;

import com.example.common.enums.AccountStatusEnum;
import com.example.entity.Account;
import com.example.util.CRUDUtils;

import java.util.List;

public class UserMapper {
    public int insert(String username, String password) {
        String insertSql = "INSERT INTO user (username,password,status) VALUES (?, ? ,?)";
        return CRUDUtils.insert(insertSql, username, password, AccountStatusEnum.NORMAL.getValue());
    }
public Account selectSingle(Account account) {
    String username = account.getUsername();
    String email = account.getEmail();
    String phone = account.getPhone();
    Integer id = account.getId();
    Account result = null;
    if(username!=null && username.isEmpty())username=null;
    if(email!=null && email.isEmpty())email=null;
    if(phone!=null && phone.isEmpty())phone=null;
    if(id!=null&&id>0){
        String selectSql ="SELECT * FROM user WHERE id = ?";
        return CRUDUtils.queryForObject(Account.class, selectSql, id);
    }else{
        if(username==null&&email==null&&phone==null){
            return null;
        }
        if(username==null&&email==null){
            String selectSql = "SELECT * FROM user WHERE phone = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, phone);
        if(result!=null)return result;
        }
        if(username==null&&phone==null){
            String selectSql = "SELECT * FROM user WHERE email = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, email);
        if(result!=null)return result;
        }
        else if(phone==null&&email==null){
            String selectSql = "SELECT * FROM user WHERE username = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, username);
            if(result!=null)return result;
        }
        if(username==null){
            String selectSql = "SELECT * FROM user WHERE phone = ? and email = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, phone, email);
            if(result!=null)return result;
        }
        if(email==null){
            String selectSql = "SELECT * FROM user WHERE username = ? and phone = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, username, phone);
            if(result!=null)return result;
        }
        if(phone==null){
            String selectSql = "SELECT * FROM user WHERE username = ? and email = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, username, email);
            if(result!=null)return result;
        }
        {
            String selectSql = "SELECT * FROM user WHERE phone = ? and email = ? and username = ?";
            result = CRUDUtils.queryForObject(Account.class, selectSql, phone, email, username);
            return result;
        }
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

}
