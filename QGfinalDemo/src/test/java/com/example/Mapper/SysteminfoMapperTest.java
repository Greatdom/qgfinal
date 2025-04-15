package com.example.Mapper;

import com.example.entity.Systeminfo;
import com.example.mapper.SysteminfoMapper;
import org.junit.Test;

public class SysteminfoMapperTest {
    @Test
    public void initial(){
        SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
        Systeminfo systeminfo=systeminfoMapper.initial();
        System.out.println(systeminfo);
    }
    @Test
    public void update(){
        SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
        Systeminfo systeminfo=Systeminfo.getInstance();
        systeminfo.setAdminNum(23);
        int count = systeminfoMapper.update(systeminfo);
        System.out.println(count);
    }
    @Test
    public void initialStatic(){
        SysteminfoMapper systeminfoMapper = new SysteminfoMapper();
        System.out.println(Systeminfo.getInstance());
    }
    @Test
    public void test(){
        Systeminfo systeminfo=Systeminfo.getInstance();
        System.out.println(systeminfo);
        Systeminfo www=Systeminfo.getInstance();
        System.out.println(www);
    }
}
