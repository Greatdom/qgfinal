package com.example.common;

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
    public void onOpen(Session session) {
        // 当有新客户端连接时，将其会话添加到集合中
        sessions.add(session);
        System.out.println("New connection: " + session.getId());
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
        // 当客户端断开连接时，从集合中移除其会话
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // 当发生错误时，记录错误信息
        System.out.println("Error occurred: " + throwable.getMessage());
    }
}