package com.xiu.websocket_demo.controller.v3;

import com.xiu.websocket_demo.model.InMessage;
import com.xiu.websocket_demo.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SingleChatController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/v3/single/chat")
    public void singleChat(InMessage message){
        webSocketService.sendMessageToUser(message);
    }
}
