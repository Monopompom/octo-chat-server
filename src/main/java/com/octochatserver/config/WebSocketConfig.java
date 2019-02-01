package com.octochatserver.config;

import com.octochatserver.interceptor.AuthChannelInterceptorAdapter;
import com.octochatserver.interceptor.HttpHandshakeInterceptor;
import com.octochatserver.service.WebSocketAuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@PropertySource("classpath:chat.properties")
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${octo.chat.broker}")
    private String broker;

    @Value("${octo.chat.app.prefix}")
    private String appPrefix;

    @Value("${octo.chat.user.prefix}")
    private String userPrefix;

    @Value("${octo.chat.endpoint}")
    private String chatEndpoint;

    @Autowired
    private HttpHandshakeInterceptor handshakeInterceptor;

    @Autowired
    private WebSocketAuthenticatorService webSocketAuthenticatorService;

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker(broker);
        config.setApplicationDestinationPrefixes(appPrefix);
        config.setUserDestinationPrefix(userPrefix);
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint(chatEndpoint)
            .addInterceptors(handshakeInterceptor);

        registry.addEndpoint(chatEndpoint)
            .addInterceptors(handshakeInterceptor)
            .setAllowedOrigins("*")
            .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(final ChannelRegistration registration) {
        registration.interceptors(new AuthChannelInterceptorAdapter(this.webSocketAuthenticatorService));
    }
}