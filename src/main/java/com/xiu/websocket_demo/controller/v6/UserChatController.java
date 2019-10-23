package com.xiu.websocket_demo.controller.v6;

import com.xiu.websocket_demo.model.InMessage;
import com.xiu.websocket_demo.service.WebSocketService;
import org.apache.logging.log4j.message.ReusableSimpleMessage;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserChatController {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 用户数据模拟
     */
    public static Map<String,String> map=new HashMap<>();
    static {
        map.put("zhangsan","111");
        map.put("lisi","111");
        map.put("wangwu","111");
        map.put("wade","111");
        map.put("james","111");
    }

    /**
     *在线用户
     */
    public static Map<String,User> onlineUser=new HashMap<>();
    static {
        onlineUser.put("123",new User("admin","111"));
    }


    @PostMapping("login")
    public String login(@RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "pwd",required = true) String pwd, HttpSession session){
        if(pwd.equals(map.get(username))){
            User user=new User(username,pwd);
            onlineUser.put(session.getId(),user);
            return "redirect:/v6/aaa" ;
        }else {
            return "redirect:/v6/error" ;
        }

    }

    @Scheduled(fixedRate = 2000)
    public void onlineUser() {
        webSocketService.sendOnlineUser(onlineUser);
    }


    @MessageMapping("/v6/chat")
    public void topicChat(InMessage message, SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        System.out.println(message.getContent());
        String sessionId=simpMessageHeaderAccessor.getSessionAttributes().get("sessionId").toString();
        System.out.println(sessionId);
        User user=onlineUser.get(sessionId);
        message.setFrom(user.getUsername());
        webSocketService.sendTopicChat(message);
    }
}
