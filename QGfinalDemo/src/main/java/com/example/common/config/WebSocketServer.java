package com.example.common.config;

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
            sessions.add(session);
            if (username != null) {
                // 将 username 存储到 WebSocket Session 的用户属性中
                session.getUserProperties().put("username", username);
                System.out.println("WebSocket connection opened for user: " + username);
                // 将 WebSocket Session 添加到集合中

                System.out.println("New connection: " + session.getId());
                for (Session s : sessions) {
                    if (s.isOpen()) {
                        try {
                            s.getBasicRemote().sendText(username+"进入聊天室");
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
        System.out.println("Received message: " + message);
        synchronized (sessions) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    try {
                        s.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
//        // 当客户端断开连接时，从集合中移除其会话
//        String username = (String) session.getUserProperties().get("username");
//        for (Session s : sessions) {
//            if (s.isOpen()&&s!=session) {
//                try {
//                    if(username!=null){
//                        session.getBasicRemote().sendText(username+"已离开");
//                    }else{
//                        session.getBasicRemote().sendText("某个不受欢迎的人被迫离开了");
//                    }
//                    s.getBasicRemote().sendText(username+"进入聊天室");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // 当发生错误时，记录错误信息
        System.out.println("Error occurred: " + throwable.getMessage());
    }
}