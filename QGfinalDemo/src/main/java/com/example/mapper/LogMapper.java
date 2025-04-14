package com.example.mapper;

import com.example.entity.Log;
import com.example.util.CRUDUtils;

import java.util.List;

public class LogMapper {
    public int insert(Log log) {
        String operateContent = log.getOperateContent();
        String operateType = log.getOperateType();
        String operateUsername = log.getOperateUsername();
        String ip = log.getIp();
        String operateTime = log.getOperateTime();
        String insertSql = "insert into log (operate_content,operate_type,operate_username,ip,operate_time) values(?,?,?,?,?)";
        return CRUDUtils.insert(insertSql, operateContent, operateType, operateUsername, ip, operateTime);
    }
    public List<Log> selectAll() {
        String selectSql = "select * from log";
        return CRUDUtils.queryForList(Log.class, selectSql);
    }
    public List<Log> selectList(String searchWord) {
        if(searchWord == null || searchWord.isEmpty()) {
            return null;
        }
        searchWord = "%" + searchWord + "%";
        String selectSql = "select * from log"
                + " where operate_content like ?"
                + " or operate_type like ?"
                + " or operate_username like ?"
                + " or ip like ?";
        return CRUDUtils.queryForList(Log.class, selectSql, searchWord,searchWord,searchWord,searchWord);
    }
    public Log selectById(Integer id) {
        String selectSql = "select * from log where id = ?";
        return CRUDUtils.queryForObject(Log.class, selectSql, id);
    }
}
