package com.octochatserver.entity;

public class MessageEntity {

    //private MessageType type;

//    private String chatRoom;
//    private String topic;
//
//    private Date date;
//    private String content;
//    private String sender;
//    private String destination;

    private SpaceEntity space;
    private UserEntity sender;
    private String message;

    public SpaceEntity getSpace() {
        return space;
    }

    public void setSpace(SpaceEntity space) {
        this.space = space;
    }

    public UserEntity getSenderr() {
        return sender;
    }

    public void setSender(UserEntity user) {
        this.sender = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public enum MessageType {
//        CREATE_ROOM, DELETE_ROOM, CREATE_TOPIC, DELETE_TOPIC, CHAT, JOIN, LEAVE
//    }

//    public MessageType getType() {
//        return type;
//    }
//
//    public void setType(MessageType type) {
//        this.type = type;
//    }
//
//    public String getChatRoom() {
//        return chatRoom;
//    }
//
//    public void setChatRoom(String chatRoom) {
//        this.chatRoom = chatRoom;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getSender() {
//        return sender;
//    }
//
//    public void setSender(String sender) {
//        this.sender = sender;
//    }
//
//    public String getDestination() {
//        return destination;
//    }
//
//    public void setDestination(String destination) {
//        this.destination = destination;
//    }
}