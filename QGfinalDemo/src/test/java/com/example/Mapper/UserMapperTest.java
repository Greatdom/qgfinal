package com.example.Mapper;

import com.example.entity.Account;
import com.example.mapper.AdminMapper;
import com.example.mapper.UserMapper;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void Insert(){
        UserMapper userMapper = new UserMapper();
        int count=userMapper.insert("user","user");
        System.out.println(count);
    }
    @Test
    public void Update(){
        UserMapper userMapper = new UserMapper();
        Account account=new Account();
        account.setName("用户");
        account.setPassword("123456");
        account.setPhone("12345678");
        account.setEmail("user.gmail.com");
        account.setPayPassword("234456");
        account.setUsername("user");
        int count = userMapper.update(account);
        System.out.println(count);
    }
    @Test
    public void selectAll(){
        UserMapper userMapper = new UserMapper();
        List<Account> admin=userMapper.selectAll();
        System.out.println(admin);
    }
    @Test
    public void selectSingle(){
        UserMapper userMapper = new UserMapper();
        Account account=new Account();
        account.setUsername("user");
        Account account1=userMapper.selectSingle(account);
        System.out.println(account1);
    }

}
