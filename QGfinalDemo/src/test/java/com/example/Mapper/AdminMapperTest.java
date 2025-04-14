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
        int count = adminMapper.update("管理员","123456","13433384487","778005729@qq.com",null,"admin");
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
        Account phone= adminMapper.selectByPhone("13433384487");
        Account username= adminMapper.selectByUsername("admin");
        Account email= adminMapper.selectByEmail("778005729@qq.com");
        System.out.println(phone);
        System.out.println(username);
        System.out.println(email);
    }
}
