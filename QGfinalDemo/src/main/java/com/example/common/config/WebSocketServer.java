package com.example.common.config;

import com.example.entity.Sentence;
import com.example.service.SentenceService;
import com.example.util.TimeUtil;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/websocket", configurator = CORSConfigurator.class)
public class WebSocketServer {
    // 用于存储所有连接的客户端会话
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 从 EndpointConfig 中获取 HttpSession
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        // 从 HttpSession 中获取 username 属性
        if (httpSession != null) {
            String username = (String) httpSession.getAttribute("username");
            String role = (String) httpSession.getAttribute("role");
            Integer id = (Integer) httpSession.getAttribute("id");
            String name = (String) httpSession.getAttribute("name");
            sessions.add(session);
            if (username != null) {
                // 将 username 存储到 WebSocket Session 的用户属性中
                session.getUserProperties().put("username", username);
                session.getUserProperties().put("role", role);
                session.getUserProperties().put("id", id);
                session.getUserProperties().put("name", name);
                System.out.println("WebSocket connection opened for user: " + username);
                // 将 WebSocket Session 添加到集合中

                System.out.println("New connection: " + session.getId());
                for (Session s : sessions) {
                    if (s.isOpen()) {
                        try {
                            s.getBasicRemote().sendText("PUBLIC:"+username+"进入聊天室");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                this.onClose(session);
            }
        }

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 当接收到客户端消息时，广播消息给所有连接的客户端
        String[] parts = message.split(":", 3);
        Integer type = Integer.valueOf(parts[0]);
        Integer sessionId = Integer.valueOf(parts[1]);
        String content = parts[2];
        System.out.println("CounterPart: " + type);
        System.out.println("Received message: " + content);

        //收消息的人的。。。
        int id;
        String role;

        if(type==0){
            id=1;
            role="SYSTEM";
        }else{
            id=type;
            role="USER";
        }

        synchronized (sessions) {

            Integer selfId = (Integer) session.getUserProperties().get("id");
            String selfRole = (String) session.getUserProperties().get("role");
            SentenceService sentenceService = new SentenceService();
            Sentence sentence = new Sentence();
            sentence.setSessionId(sessionId);
            sentence.setContent(content);
            sentence.setSentenceTime(TimeUtil.getTime());
            sentence.setUserId(selfId);
            sentence.setUserRole(selfRole);
            sentenceService.add(sentence);

            for (Session s : sessions) {
                if (s.isOpen()) {
                    //对方的身份
                    Integer counterpartId = (Integer) s.getUserProperties().get("id");
                    String counterpartRole = (String) s.getUserProperties().get("role");
                    //自己的身份
                    String selfUsername = (String) session.getUserProperties().get("username");
                    String selfName = (String) session.getUserProperties().get("name");
                    //如果是对方
                    if(counterpartId==id&&counterpartRole.equals(role)){
                        try {
                            if(selfName!=null&& !selfName.isEmpty())
                                s.getBasicRemote().sendText("USER:你收到["+selfName+"]的消息");
                            else
                                s.getBasicRemote().sendText("USER:你收到["+selfUsername+"]的消息");
                            s.getBasicRemote().sendText("PRIVATE:"+content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //如果是自己
                    if(s==session){
                        try {
                            s.getBasicRemote().sendText("PRIVATE:"+content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // 当发生错误时，记录错误信息
        System.out.println("Error occurred: " + throwable.getMessage());
    }
}