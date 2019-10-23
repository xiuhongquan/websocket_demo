package com.xiu.websocket_demo.inteceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * spring和websocket的拦截器
 * 功能描述：握手拦截器，可以通过这个类的方法获取request和response
 *
 *
 */
public class HttpHandShakInteceptor implements HandshakeInterceptor {
    /**
     * 保存session到map中，之后消息处理类的每个方法都可以获取当前的session
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        if(serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)serverHttpRequest;
            HttpSession session =  servletRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("【握手拦截器】beforeHandshake sessionId="+sessionId);
            map.put("sessionId", sessionId);

        }
        return true;
    }



    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("【握手拦截器】afterHandshake");
        if(serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)serverHttpRequest;
            HttpSession session =  servletRequest.getServletRequest().getSession();
            String sessionId = session.getId();
            System.out.println("【握手拦截器】afterHandshake sessionId="+sessionId);
        }

    }
}
