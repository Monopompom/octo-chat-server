package com.octochatserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "ochat_channels")
public class ChannelEntity {

    private int id;
    private String name;
    private int ownerId;
    private Timestamp registeredDate;
    private Timestamp modifiedDate;
    private Set<UserEntity> channelUsers;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @CreationTimestamp
    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    @UpdateTimestamp
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "userChannels")
    public Set<UserEntity> getChannelUsers() {
        return channelUsers;
    }

    public void setChannelUsers(Set<UserEntity> channelUsers) {
        this.channelUsers = channelUsers;
    }
}
