package com.octochatserver.dao;

import com.octochatserver.entity.SpaceEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpaceDAO extends CrudRepository<SpaceEntity, Integer> {

    SpaceEntity getById(int id);

    SpaceEntity getByName(String name);
}
