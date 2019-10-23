package com.xiu.websocket_demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectedEvent> {

    /**在事件触发的时候调用这个方法
     * StompHeaderAccessor   简单消息传递协议中处理消息头的基类
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(event.getMessage());
        //System.out.println("SessionConnectedEvent监听器事件："+stompHeaderAccessor.getCommand().getMessageType());
    }


}
