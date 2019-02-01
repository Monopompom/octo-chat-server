package com.octochatserver.service;

import com.octochatserver.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity get(int id);

    UserEntity getByEmail(String email);

    UserEntity save(UserEntity user);

    UserDetails loadUserByUsername(String email);
}
