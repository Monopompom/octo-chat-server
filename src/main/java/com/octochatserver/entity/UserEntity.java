package com.octochatserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.octochatserver.util.Views;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "ochat_users")
public class UserEntity {

    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String nickname;
    private String email;
    private String password;
    private String token;
    private Timestamp registeredDate;
    private Timestamp modifiedDate;
    //private UserPreferencesEntity userPreferences;
    private Set<UserChannelEntity> userChannels;
    //private Set<UserChatEntity> userChats;

    public static final String WRONG_CREDENTIALS = "Wrong credentials";

    public enum UserRole {
        MEMBER, ADMIN
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonView(Views.Manager.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    //    public UserPreferencesEntity getUserPreferences() {
//        return userPreferences;
//    }
//
//    public void setUserPreferences(UserPreferencesEntity userPreferences) {
//        this.userPreferences = userPreferences;
//    }

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "octo_user_channel", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "channel_id")})
    public Set<UserChannelEntity> getUserChannels() {
        return userChannels;
    }

    public void setUserChannels(Set<UserChannelEntity> userChannels) {
        this.userChannels = userChannels;
    }

//    public Set<UserChatEntity> getUserChats() {
//        return userChats;
//    }
//
//    public void setUserChats(Set<UserChatEntity> userChats) {
//        this.userChats = userChats;
//    }
}
