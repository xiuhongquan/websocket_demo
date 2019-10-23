package com.xiu.websocket_demo.service;


import com.xiu.websocket_demo.controller.v6.User;
import com.xiu.websocket_demo.model.InMessage;
import com.xiu.websocket_demo.model.OutMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 消息推送模板
 */
@Slf4j
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;


    //广播发送
    public void sendTopicMessage(String dest,InMessage message){
        template.convertAndSend(dest,new OutMessage(message.getContent()));

    }


    //点对点聊天
    public void sendMessageToUser(InMessage message) {
        template.convertAndSend("/chat/single/"+message.getTo(),new OutMessage(message.getFrom()+"发送："+message.getContent()));
    }

    public void sendServerInfo() {
    }

    public void sendOnlineUser(Map<String, User> onlineUser) {
        String msg = "";
        for(Map.Entry<String, User> entry : onlineUser.entrySet()){
            msg = msg.concat(entry.getValue().getUsername()+" || ");
        }

        template.convertAndSend("/topic/onlineuser",new OutMessage(msg));
    }

    public void sendTopicChat(InMessage message) {
        template.convertAndSend("/topic/chat",new OutMessage(message.getFrom()+"  发送:  "+message.getContent()));
    }
}
