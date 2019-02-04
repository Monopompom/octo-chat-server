package com.octochatserver.service.impl;

import com.octochatserver.dao.UserSpaceDAO;
import com.octochatserver.service.UserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSpaceServiceImpl implements UserSpaceService {

    @Autowired
    UserSpaceDAO userSpaceDAO;
}
