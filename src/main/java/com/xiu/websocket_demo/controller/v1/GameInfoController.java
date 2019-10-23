package com.xiu.websocket_demo.controller.v1;

import com.xiu.websocket_demo.model.InMessage;
import com.xiu.websocket_demo.model.OutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameInfoController {

    @MessageMapping("/v1/chat")
    @SendTo("/topic/game_chat")
    public OutMessage gameInfo(InMessage message){
        return new OutMessage(message.getContent());
    }
}
