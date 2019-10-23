package com.xiu.websocket_demo.controller.v4;


import com.xiu.websocket_demo.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class SchedulePushInfoController {
    @Autowired
    private WebSocketService webSocketService;


    @MessageMapping("/v4/schedule/push")
    @Scheduled(fixedRate = 3000)//定时3秒
    public void sendServerInfo(){
        webSocketService.sendServerInfo();
    }
}
