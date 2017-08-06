package com.demo.task.chatup.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static com.demo.task.chatup.web.ChatController.APP_PREFIX;
import static com.demo.task.chatup.web.ChatController.MESSAAGE_BROKER_ENDPOINT;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registery) {
        registery.addEndpoint("/sock-bind").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(MESSAAGE_BROKER_ENDPOINT);
        config.setApplicationDestinationPrefixes(APP_PREFIX);
    }

}
