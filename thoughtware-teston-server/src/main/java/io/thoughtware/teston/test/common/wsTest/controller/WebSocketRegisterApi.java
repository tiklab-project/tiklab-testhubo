package io.thoughtware.teston.test.common.wsTest.controller;

import io.thoughtware.teston.test.common.wsTest.service.WebSocketServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketRegisterApi implements WebSocketConfigurer {
    /**
     * 注册handle
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketServiceImpl(), "/websocket")
                .addInterceptors()
                .setAllowedOrigins("*");
    }
}