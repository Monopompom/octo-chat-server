package com.octochatserver.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.octochatserver.util.Views;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
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
    private Set<SpaceEntity> userSpaces = new HashSet<>();
    //private Set<UserChatEntity> userChats;

    public static final String WRONG_CREDENTIALS = "Wrong credentials";
    public static final String UNAUTHORIZED = "Unauthorized";

    public enum UserRole {
        MEMBER, ADMIN
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @JsonView(Views.Height.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    @JsonView(Views.Middle.class)
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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "spaceUsers")
    public Set<SpaceEntity> getUserSpaces() {
        return userSpaces;
    }

    public void setUserSpaces(Set<SpaceEntity> userSpaces) {
        this.userSpaces = userSpaces;
    }

//    public Set<UserChatEntity> getUserChats() {
//        return userChats;
//    }
//
//    public void setUserChats(Set<UserChatEntity> userChats) {
//        this.userChats = userChats;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(secondName, that.secondName) &&
            Objects.equals(middleName, that.middleName) &&
            nickname.equals(that.nickname) &&
            email.equals(that.email) &&
            Objects.equals(password, that.password) &&
            registeredDate.equals(that.registeredDate) &&
            modifiedDate.equals(that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, middleName, nickname, email, password, registeredDate, modifiedDate);
    }
}
