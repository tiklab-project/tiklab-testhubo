package io.thoughtware.teston.test.common.wsTest.controller;

import io.thoughtware.teston.test.common.wsTest.service.WebSocketServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketRegisterApi implements WebSocketConfigurer {

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(1024 * 256); // 设置文本消息缓冲区大小
        container.setMaxBinaryMessageBufferSize(1024 * 256); // 设置二进制消息缓冲区大小
        return container;
    }

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