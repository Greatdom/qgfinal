package com.example.mapper;

import com.example.entity.Sentence;
import com.example.util.CRUDUtils;

import java.util.List;

public class SentenceMapper {
    public int insert(Sentence sentence) {
        Integer userId = sentence.getUserId();
        String userRole = sentence.getUserRole();
        String content = sentence.getContent();
        String sentenceTime = sentence.getSentenceTime();
        Integer sessionId = sentence.getSessionId();
        String insertSql="insert into sentence (user_id,user_role,content,sentence_time,session_id) values(?,?,?,?,?)";
        return CRUDUtils.insert(insertSql,userId,userRole,content,sentenceTime,sessionId);
    }
    public List<Sentence> selectAll() {
        String sql="select * from sentence";
        return CRUDUtils.queryForList(Sentence.class, sql);
    }
    public List<Sentence> selectBySessionId(Integer sessionId) {
        String sql="select * from sentence where session_id=?";
        return CRUDUtils.queryForList(Sentence.class, sql,sessionId);
    }

}
