package com.xiu.websocket_demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {


    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("监听器事件："+stompHeaderAccessor.getCommand().getMessageType());
    }
}
