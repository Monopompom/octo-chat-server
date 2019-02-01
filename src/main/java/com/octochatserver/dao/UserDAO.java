package com.octochatserver.dao;

import com.octochatserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<UserEntity, Integer> {

    UserEntity getById(int id);

    UserEntity getByEmail(String email);
}
