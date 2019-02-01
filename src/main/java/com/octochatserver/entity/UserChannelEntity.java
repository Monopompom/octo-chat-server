package com.octochatserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "ochat_user_channel")
public class UserChannelEntity {

    private int id;
    private int userId;
    private int channelId;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
