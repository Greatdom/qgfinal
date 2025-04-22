package com.example.service;

import com.example.entity.Account;
import com.example.entity.Systeminfo;
import com.example.mapper.AdminMapper;
import com.example.mapper.SysteminfoMapper;
import com.example.mapper.UserMapper;

import java.util.List;

public class UserService {

    UserMapper userMapper = new UserMapper();

    public int add(Account account){
        Account dbAccount = userMapper.selectSingle(account);
        if(dbAccount != null){
            return 0;
        }else{
            int count = userMapper.insert(account.getUsername(),account.getPassword());
            if(count > 0){
                Systeminfo systeminfo = Systeminfo.getInstance();
                systeminfo.setUserNum(systeminfo.getUserNum()+1);
                SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
                systeminfoMapper.update(systeminfo);
            }
            return count;
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

    public int update(Account account) {
        Integer id=account.getId();
        Account username = new Account();
        username.setUsername(account.getUsername());
        Account email = new Account();
        email.setEmail(account.getEmail());
        Account phone = new Account();
        phone.setPhone(account.getPhone());
        username = userMapper.selectSingle(username);
        email = userMapper.selectSingle(email);
        phone = userMapper.selectSingle(phone);

        if(id==null||id<=0){return 0;}
        if((username!=null&& !id.equals(username.getId()))||(email!=null&& !id.equals(email.getId()))||(phone!=null&& !id.equals(phone.getId()))) {
            return 0;
        }else{
             return userMapper.update(account);
        }
    }

    public List<Account> selectAll(){return userMapper.selectAll();}

    public Account selectSingle(Account account){return userMapper.selectSingle(account);}

    public int ChangeStatus(Account account){
        return userMapper.ChangeStatus(account);
    }

}
