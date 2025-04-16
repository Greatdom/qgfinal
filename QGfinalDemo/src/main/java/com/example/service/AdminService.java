package com.example.service;

import com.example.entity.Account;
import com.example.entity.Systeminfo;
import com.example.mapper.AdminMapper;
import com.example.mapper.SysteminfoMapper;

import java.util.List;

public class AdminService {
    AdminMapper adminMapper=new AdminMapper();

    public int add(Account account){
        Account dbAccount = adminMapper.selectSingle(account);
        if(dbAccount != null){
            return 0;
        }else{
            int count = adminMapper.insert(account.getUsername(),account.getPassword());
            if(count > 0){
                Systeminfo systeminfo=Systeminfo.getInstance();
                systeminfo.setAdminNum(systeminfo.getAdminNum()+1);
                SysteminfoMapper systeminfoMapper=new SysteminfoMapper();
                systeminfoMapper.update(systeminfo);
            }
            return count;
        }
    }

    public Account login(Account account){
        Account dbAccount =adminMapper.selectSingle(account);
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

    public int update(Account account) {return adminMapper.update(account);}

    public List<Account> selectAll(){return adminMapper.selectAll();}

    public Account selectSingle(Account account){return adminMapper.selectSingle(account);}
    

}
