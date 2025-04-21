package com.example.common.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class CORSConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        super.modifyHandshake(sec, request, response);

        // 从 HandshakeRequest 中获取 HttpSession
        HttpSession httpSession = (HttpSession) request.getHttpSession();

        // 将 HttpSession 存储到 EndpointConfig 的用户属性中
        if (httpSession != null) {
            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }

        // 添加CORS支持，允许所有域
        response.getHeaders().put("Access-Control-Allow-Origin", java.util.Collections.singletonList("*"));
        response.getHeaders().put("Access-Control-Allow-Methods", java.util.Collections.singletonList("GET, POST, OPTIONS"));
        response.getHeaders().put("Access-Control-Allow-Headers", java.util.Collections.singletonList("Content-Type, Authorization"));
    }




}