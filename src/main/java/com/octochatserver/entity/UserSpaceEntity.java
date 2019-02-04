package com.octochatserver.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ochat_user_space")
public class UserSpaceEntity {

    private int id;
    private int userId;
    private int spaceId;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "space_id")
    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSpaceEntity that = (UserSpaceEntity) o;
        return id == that.id &&
            userId == that.userId &&
            spaceId == that.spaceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, spaceId);
    }
}
