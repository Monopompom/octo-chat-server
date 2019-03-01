package com.octochatserver.service;

import com.octochatserver.entity.SpaceEntity;

public interface SpaceService {

    SpaceEntity get(int id);

    SpaceEntity getByName(String name);

    SpaceEntity save(SpaceEntity space);
}
