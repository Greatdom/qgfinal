package com.example.service;

import com.example.entity.Account;
import com.example.entity.Sentence;
import com.example.entity.Session;
import com.example.entity.Systeminfo;
import com.example.mapper.SessionMapper;
import com.example.mapper.SysteminfoMapper;
import com.example.util.TimeUtil;

import java.util.List;

public class SessionService {
    SessionMapper sessionMapper=new SessionMapper();

    public int add(Session session){
        Account acc1=new Account();
        Account acc2=new Account();
        acc1.setId(session.getHeadId());
        acc1.setRole(session.getHeadRole());
        acc2.setId(session.getHindId());
        acc2.setRole(session.getHindRole());
        Session dbsession=sessionMapper.selectSingle(acc1,acc2);
        int count = 0;
        if(dbsession==null){
            count = sessionMapper.insert(session);
            if(count>0){
                dbsession=sessionMapper.selectSingle(acc1,acc2);
                SentenceService sentenceService=new SentenceService();
                Sentence sentence=new Sentence();
                sentence.setSessionId(dbsession.getId());
                sentence.setSentenceTime(TimeUtil.getTime());
                sentence.setUserId(dbsession.getHeadId());
                sentence.setUserRole(dbsession.getHeadRole());
                sentence.setContent("我向你发起了会话~");
                count = sentenceService.add(sentence);

            }
        }
        if(count>0){
            Systeminfo systeminfo=Systeminfo.getInstance();
            systeminfo.setSessionNum(systeminfo.getSessionNum()+1);
            SysteminfoMapper systeminfoMapper=new SysteminfoMapper();
            systeminfoMapper.update(systeminfo);
        }
        return count;
    }
    public List<Session> selectAll(){
        return sessionMapper.selectAll();
    }
    public Session selectById(int id){
        return sessionMapper.selectById(id);
    }
    public List<Session> selectByAccount(Account account){
        return sessionMapper.selectByAccount(account);
    }
    public Session selectSingle(Account acc1,Account acc2){
        return sessionMapper.selectSingle(acc1,acc2);
    }
}
