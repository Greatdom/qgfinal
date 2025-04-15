package com.example.service;

import com.example.entity.Account;
import com.example.mapper.AdminMapper;
import com.example.mapper.UserMapper;

import java.util.List;

public class UserService {

    UserMapper userMapper = new UserMapper();

    public int add(Account account){
        Account dbAccount = userMapper.selectSingle(account);
        if(dbAccount != null){
            return 0;
        }else{
            return userMapper.insert(account.getUsername(),account.getPassword());
        }
    }

    public Account login(Account account){
        Account dbAccount =userMapper.selectSingle(account);
        if(dbAccount == null){
            return null;
        }else{
            if(dbAccount.getPassword().equals(account.getPassword())){
                return dbAccount;
            }else{
                return null;
            }
        }
    }
    public int register(Account account) {return add(account);}

    public int update(Account account) {return userMapper.update(account);}

    public List<Account> selectAll(){return userMapper.selectAll();}

    public Account selectSingle(Account account){return userMapper.selectSingle(account);}



}
