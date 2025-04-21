package com.example.mapper;

import com.example.entity.Account;
import com.example.entity.Session;
import com.example.util.CRUDUtils;

import java.util.List;

public class SessionMapper {
    public int insert(Session session) {
        String sessionTime = session.getSessionTime();
        Integer headId = session.getHeadId();
        Integer hindId = session.getHindId();
        String headRole=session.getHeadRole();
        String hindRole=session.getHindRole();
        String insertSql=
                "INSERT INTO session (session_time,head_id,hind_id,head_role,hind_role)" +
                        " VALUES (?, ?, ?, ?, ?)";
        return CRUDUtils.insert(insertSql,sessionTime,headId,hindId,headRole,hindRole);

    }
    public List<Session> selectByAccount(Account account) {
        Integer id = account.getId();
        String role = account.getRole();
        String selectSql="select * from session where "
            + " ( head_id = ? and head_role = ? ) or ( hind_id = ? and hind_role = ? ) ";
        return CRUDUtils.queryForList(Session.class,selectSql,id,role,id,role);
    }
    public List<Session> selectAll(){
        String selectSql="select * from session";
        return CRUDUtils.queryForList(Session.class,selectSql);
    }
    public Session selectSingle(Account acc1,Account acc2) {
        Integer acc1Id = acc1.getId();
        Integer acc2Id = acc2.getId();
        String acc1Role = acc1.getRole();
        String acc2Role = acc2.getRole();
        String selectSql="select * from session where "
                + " ( head_id = ? and head_role = ? ) and ( hind_id = ? and hind_role = ? ) ";
        Session session=CRUDUtils.queryForObject(Session.class,selectSql,acc1Id,acc1Role,acc2Id,acc2Role);
        if(session==null){
            session=CRUDUtils.queryForObject(Session.class,selectSql,acc2Id,acc2Role,acc1Id,acc1Role);
        }
        return session;
    }
    public Session selectById(int id) {
        String selectSql="select * from session where id = ?";
        return CRUDUtils.queryForObject(Session.class,selectSql,id);
    }
}
