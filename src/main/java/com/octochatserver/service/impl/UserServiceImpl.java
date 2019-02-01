package com.octochatserver.service.impl;

import com.octochatserver.dao.UserDAO;
import com.octochatserver.entity.UserEntity;
import com.octochatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.octochatserver.entity.UserEntity.WRONG_CREDENTIALS;
import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserEntity get(int id) {
        return userDAO.getById(id);
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userDAO.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = getByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(WRONG_CREDENTIALS);
        }

        return new User(user.getEmail(), user.getPassword(), emptyList());
    }
}
