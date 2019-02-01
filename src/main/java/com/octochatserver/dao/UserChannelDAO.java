package com.octochatserver.dao;

import com.octochatserver.entity.UserChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserChannelDAO extends CrudRepository<UserChannelEntity, Integer> {
}
