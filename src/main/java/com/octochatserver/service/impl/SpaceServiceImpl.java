package com.octochatserver.service.impl;

import com.octochatserver.dao.SpaceDAO;
import com.octochatserver.entity.SpaceEntity;
import com.octochatserver.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    SpaceDAO spaceDAO;

    @Override
    public SpaceEntity save(SpaceEntity space) {
        return spaceDAO.save(space);
    }
}
