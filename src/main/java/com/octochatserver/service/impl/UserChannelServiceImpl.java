package com.octochatserver.service.impl;

import com.octochatserver.dao.ChannelDAO;
import com.octochatserver.dao.UserDAO;
import com.octochatserver.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChannelServiceImpl implements UserChannelService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    ChannelDAO channelDAO;
}
