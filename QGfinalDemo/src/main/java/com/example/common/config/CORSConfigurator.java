package com.example.common.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class CORSConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 添加CORS支持，允许所有域
        response.getHeaders().put("Access-Control-Allow-Origin", java.util.Collections.singletonList("*"));
        response.getHeaders().put("Access-Control-Allow-Methods", java.util.Collections.singletonList("GET, POST, OPTIONS"));
        response.getHeaders().put("Access-Control-Allow-Headers", java.util.Collections.singletonList("Content-Type, Authorization"));
    }
}