package com.octochatserver.dao;

import com.octochatserver.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChannelDAO extends CrudRepository<ChannelEntity, Integer> {
}
