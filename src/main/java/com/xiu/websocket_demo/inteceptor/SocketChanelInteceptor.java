package com.xiu.websocket_demo.inteceptor;

import com.xiu.websocket_demo.controller.v6.UserChatController;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 * 频道拦截器，类似管道，可以获取消息的一些原数据
 */
public class SocketChanelInteceptor implements ChannelInterceptor {


    /**
     * 在消息被实际发送到频道之前调用
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("SocketChannelInteceptor-->preSend");
        return null;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println("SocketChannelInteceptor-->postSend");
        //消息头访问器
        StompHeaderAccessor stompHeaderAccessor=StompHeaderAccessor.wrap(message);
        //避免非stomp消息类型，例如心跳检测
        if(stompHeaderAccessor.getCommand()==null) return;
        String sessionId=stompHeaderAccessor.getSessionAttributes().get("sessionId").toString();
        switch (stompHeaderAccessor.getCommand()){
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:
                break;
            case UNSUBSCRIBE:
                break;
        }
    }


    /**
     * 在完成发送之后调用，不管是否有异常发生，一般用于资源清理
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        System.out.println("SocketChannelInteceptor-->afterSendCompletion");
    }

    public void disconnect(String sessionId){
        System.out.println("DISCONNECT  sessionId"+sessionId);
        //下线操作
        UserChatController.onlineUser.remove(sessionId);
    }
    public void connect(String sessionId){
        System.out.println("CONNECT  sessionId"+sessionId);
    }
}
