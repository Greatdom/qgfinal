package com.example.service;

import com.example.common.enums.LogsTypeEnum;
import com.example.entity.Log;
import com.example.mapper.LogMapper;
import com.example.util.IpUtils;
import com.example.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.util.List;

public class LogService {
    LogMapper logMapper=new LogMapper();

    public void recordLog(String username,String type,String msg,HttpServletRequest request){

        Log log=new Log();
        log.setOperateUsername(username);
        log.setOperateType(type);
        log.setOperateContent(msg);
        log.setIp(IpUtils.getIpAddr(request));

        log.setOperateTime(TimeUtil.getTime());
        logMapper.insert(log);
    }


    public void add(Log log) {
        logMapper.insert(log);
    }

    public List<Log> selectAll() {
        return logMapper.selectAll();
    }

}
