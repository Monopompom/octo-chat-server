package com.octochatserver.dao;

import com.octochatserver.entity.UserSpaceEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserSpaceDAO extends CrudRepository<UserSpaceEntity, Integer> {
}
