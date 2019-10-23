package com.xiu.websocket_demo.controller.v2;

import com.xiu.websocket_demo.model.InMessage;
import com.xiu.websocket_demo.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


/**
 * 使用simpleMessageTemolate推送
 */
@Controller
public class GameInfoController2 {

	@Autowired
	private WebSocketService webSocketService;


	@MessageMapping("/v2/chat")
	//@SendTo("/topic/game_chat")
	public void gameInfo(InMessage message){

		webSocketService.sendTopicMessage("/topic/game_rank",message);
	}


}



