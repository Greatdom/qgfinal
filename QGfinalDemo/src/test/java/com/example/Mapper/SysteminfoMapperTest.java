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
}
