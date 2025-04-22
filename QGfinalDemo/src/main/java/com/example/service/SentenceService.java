package com.example.service;

import com.example.entity.Account;
import com.example.entity.Sentence;
import com.example.entity.Session;
import com.example.mapper.SentenceMapper;
import com.example.util.TimeUtil;

import java.util.List;

public class SentenceService {
    SentenceMapper sentenceMapper = new SentenceMapper();
    public int add(Sentence sentence) {
        return sentenceMapper.insert(sentence);
    }
    public List<Sentence> selectAll(){
        return sentenceMapper.selectAll();
    }
    public List<Sentence> selectBySessionId(Integer sessionId){
        return sentenceMapper.selectBySessionId(sessionId);
    }
    public int addUserToSystem(Integer userId, String content){
        Sentence sentence=new Sentence();
        sentence.setUserId(userId);
        sentence.setUserRole("USER");
        sentence.setSentenceTime(TimeUtil.getTime());
        sentence.setContent(content);

        Session session=null;
        {
            Account acc1=new Account();
            Account acc2=new Account();
            acc1.setId(userId);
            acc1.setRole("USER");
            acc2.setId(1);
            acc2.setRole("SYSTEM");
            SessionService sessionService=new SessionService();
            session = sessionService.selectSingle(acc1, acc2);
            sentence.setSessionId(session.getId());
        }
        return add(sentence);
    }
    public int addSystemToUser(Integer userId, String content){
        Sentence sentence=new Sentence();
        sentence.setUserId(1);
        sentence.setUserRole("SYSTEM");
        sentence.setSentenceTime(TimeUtil.getTime());
        sentence.setContent(content);

        Session session=null;
        {
            Account acc1=new Account();
            Account acc2=new Account();
            acc1.setId(userId);
            acc1.setRole("USER");
            acc2.setId(1);
            acc2.setRole("SYSTEM");
            SessionService sessionService=new SessionService();
            session = sessionService.selectSingle(acc1, acc2);
            sentence.setSessionId(session.getId());
        }
        return add(sentence);
    }
}
