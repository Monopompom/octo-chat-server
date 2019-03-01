package com.octochatserver.controller;

import com.octochatserver.entity.MessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.message")
    public void message(@Payload MessageEntity message, Principal principal) {
        messagingTemplate.convertAndSend("/chatrooms/" + message.getSpace().getName(), message);
    }

    @MessageMapping("/chat.add")
    public void add(@Payload MessageEntity message, Principal principal) {
        message.getMessage();
        //messagingTemplate.convertAndSend("/chatrooms/" + message.getChatRoom() + "/general", message);
    }

    @MessageExceptionHandler
    public void handleException(Throwable exception) {
        //TODO Check below line
        messagingTemplate.convertAndSend("/chat.errors", exception.getMessage());
        LOGGER.error(exception.getMessage());
    }
}
