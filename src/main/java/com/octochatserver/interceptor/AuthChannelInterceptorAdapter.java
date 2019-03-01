package com.octochatserver.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static com.octochatserver.config.AppConfig.HEADER_STRING;
import static com.octochatserver.filter.JWTAuthorizationFilter.getAuthentication;

public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor != null && StompCommand.CONNECT == accessor.getCommand()) {
            String token = accessor.getFirstNativeHeader(HEADER_STRING);
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

            if (authentication != null) {
                accessor.setUser(authentication);
                accessor.setLeaveMutable(true);

                return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
            }
        }

        return message;
    }
}