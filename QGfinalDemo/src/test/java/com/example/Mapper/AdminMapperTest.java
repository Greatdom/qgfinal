package com.example.Mapper;
import com.example.entity.Account;
import com.example.mapper.AdminMapper;
import org.junit.Test;

import java.util.List;

public class AdminMapperTest {
    @Test
    public void Insert(){
        AdminMapper adminMapper = new AdminMapper();
        int count=adminMapper.insert("admin","admin");
         System.out.println(count);
    }
    @Test
    public void Update(){
        AdminMapper adminMapper = new AdminMapper();
        Account account=new Account();
        account.setName("管理员");
        account.setUsername("admin");
        account.setPassword("admin");
        account.setEmail("778005729@qq.com");
        account.setPhone("13433384487");

        int count = adminMapper.update(account);
        System.out.println(count);
    }
    @Test
    public void selectAll(){
        AdminMapper adminMapper = new AdminMapper();
        List<Account> admin=adminMapper.selectAll();
        System.out.println(admin);
    }
    @Test
    public void selectSingle(){
        AdminMapper adminMapper = new AdminMapper();
        Account account=new Account();
        account.setUsername("admin");
        Account account1=adminMapper.selectSingle(account);
        System.out.println(account1);

    }
}
