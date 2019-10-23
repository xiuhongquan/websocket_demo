package com.xiu.websocket_demo.config;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.tomcat.websocket.server.DefaultServerEndpointConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
public class WSconfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
