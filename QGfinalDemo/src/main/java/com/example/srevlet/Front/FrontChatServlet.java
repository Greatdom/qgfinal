package com.example.srevlet.Front;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.Sentence;
import com.example.entity.Session;
import com.example.service.SentenceService;
import com.example.service.SessionService;
import com.example.service.UserService;
import com.example.srevlet.BaseServlet;
import com.example.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Front/Chat")
public class FrontChatServlet extends BaseServlet {

    public void getSessionFormFrontProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer firstMan=Integer.valueOf(request.getParameter("firstMan"));
        Integer secondMan=Integer.valueOf(request.getParameter("secondMan"));
        String firstRole=request.getParameter("firstRole");
        String secondRole=request.getParameter("secondRole");
        Account acc1=new Account();
        Account acc2=new Account();
        acc1.setId(firstMan);
        acc2.setId(secondMan);
        acc1.setRole(firstRole);
        acc2.setRole(secondRole);
        Result result =null;
        SessionService sessionService=new SessionService();
        Session session=sessionService.selectSingle(acc1,acc2);
        if(session==null){
            session=new Session();
            session.setHeadId(firstMan);
            session.setHeadRole(firstRole);
            session.setHindId(secondMan);
            session.setHindRole(secondRole);
            session.setSessionTime(TimeUtil.getTime());
            sessionService.add(session);
        }
        session=sessionService.selectSingle(acc1,acc2);
        if(session!=null){
            result=Result.success(session);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);

    }
    public void LoadSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer chatMan=Integer.valueOf(request.getParameter("id"));
        String role=request.getParameter("role");
        SessionService sessionService=new SessionService();
        Account account=new Account();
        account.setId(chatMan);
        account.setRole(role);
        Result result=null;
        List<Session> sessions = sessionService.selectByAccount(account);
        if(sessions!=null){
            result=Result.success(sessions);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);

    }
    public void FocusOnSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer sessionId=Integer.valueOf(request.getParameter("sessionId"));
        SentenceService sentenceService=new SentenceService();
        Result result=null;
        List<Sentence> sentences=sentenceService.selectBySessionId(sessionId);
        if(sentences!=null){
            result=Result.success(sentences);
        }else{
            result=Result.error();
        }
        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }
    public void FocusOnCounterPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer userId=Integer.valueOf(request.getParameter("userId"));
        Result result=null;
        UserService userService=new UserService();
        Account account=new Account();
        account.setId(userId);
        account = userService.selectSingle(account);
        if(account!=null){
            result=Result.success(account);
        }else{
            result=Result.error();
        }

        String jsonStr= JSON.toJSONString(result);
        response.getWriter().write(jsonStr);
    }

}
