package com.example.Mapper;

import com.example.entity.Account;
import com.example.entity.Session;
import com.example.mapper.SessionMapper;
import com.example.util.TimeUtil;
import org.junit.Test;

import java.util.List;

public class SessionMapperTest {
    @Test
    public void insert() {
        SessionMapper sessionMapper = new SessionMapper();
        Session session = new Session();
        session.setSessionTime(TimeUtil.getTime());
        session.setHeadId(1);
        session.setHindId(1);
        session.setHeadRole("ADMIN");
        session.setHindRole("USER");
        int count = sessionMapper.insert(session);
        System.out.println(count);
    }
    @Test
    public void selectByAccount(){
        SessionMapper sessionMapper = new SessionMapper();
        Account account = new Account();
        account.setId(1);
        account.setRole("ADMIN");
        List<Session> sessions = sessionMapper.selectByAccount(account);
        System.out.println(sessions);
    }
    @Test
    public void selectBySessionId(){
        SessionMapper sessionMapper = new SessionMapper();
        Session session = sessionMapper.selectById(1);
        System.out.println(session);
    }
}
