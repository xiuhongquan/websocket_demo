package com.xiu.websocket_demo.config;

import com.xiu.websocket_demo.inteceptor.HttpHandShakInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册端点，发布或者订阅的时候需要先连接端点。
     * setAllowedOrigins  *表示支持其他域连接
     * withSockJS表示支持sockJs
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpoint-websocket").addInterceptors(new HttpHandShakInteceptor())
                .setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置消息代理(类似于中介)
     * 在线用户就相当于客户，管理员发送消息给服务器的代理，代理发送给订阅他的客户。
     *      * @method enableSimpleBroker 服务端推送消息给客户端的地址前缀
     *         setApplicationDestinationPrefixes  客户端发送数据给服务端的前缀
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/chat");
        registry.setApplicationDestinationPrefixes("/app");
    }


   /* @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SocketChanelInteceptor());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SocketChanelInteceptor());
    }*/
}
