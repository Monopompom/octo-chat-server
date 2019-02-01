package com.octochatserver.service.impl;

import com.octochatserver.dao.ChannelDAO;
import com.octochatserver.entity.ChannelEntity;
import com.octochatserver.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelDAO channelDAO;

    @Override
    public ChannelEntity save(ChannelEntity channel) {
        return channelDAO.save(channel);
    }
}
