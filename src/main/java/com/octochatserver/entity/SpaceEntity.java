package com.octochatserver.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ochat_spaces")
public class SpaceEntity {

    private int id;
    private String name;
    private int ownerId;
    private Timestamp registeredDate;
    private Timestamp modifiedDate;
    private Set<UserEntity> spaceUsers;

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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "userSpaces")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    public Set<UserEntity> getSpaceUsers() {
        return spaceUsers;
    }

    public void setSpaceUsers(Set<UserEntity> spaceUsers) {
        this.spaceUsers = spaceUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpaceEntity that = (SpaceEntity) o;
        return id == that.id &&
            ownerId == that.ownerId &&
            name.equals(that.name) &&
            registeredDate.equals(that.registeredDate) &&
            modifiedDate.equals(that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerId, registeredDate, modifiedDate);
    }
}
