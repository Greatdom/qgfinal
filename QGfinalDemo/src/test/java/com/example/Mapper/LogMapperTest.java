package com.example.Mapper;

import com.example.entity.Log;
import com.example.mapper.LogMapper;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class LogMapperTest {
    @Test
    public void insert() {
        LogMapper logMapper = new LogMapper();
        Log log = new Log();
        log.setOperateContent("admin注册了");
        log.setOperateType("注册");
        log.setOperateUsername("admin");
        log.setIp("127.0.0.1");
        log.setOperateTime("未制作该模块");
        int count = logMapper.insert(log);
        System.out.println(count);
    }
    @Test
    public void select() {
        LogMapper logMapper = new LogMapper();
        Log log=logMapper.selectById(1);
        List<Log> logs = logMapper.selectList("注册");
        System.out.println("log:"+log);
        System.out.println("logs:"+logs);
    }
}
