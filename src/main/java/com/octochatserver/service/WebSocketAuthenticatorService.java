package com.octochatserver.service;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WebSocketAuthenticatorService {

    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(final String username, final String password)
        throws AuthenticationException {

        if ((username == null) || username.trim().length() <= 0) {
            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
        }

        if ((password == null) || password.trim().length() <= 0) {
            throw new AuthenticationCredentialsNotFoundException("Password was null or empty.");
        }

//        if (fetchUserFromDb(username, password) == null) {
//            throw new BadCredentialsException("Bad credentials for user " + username);
//        }

        return new UsernamePasswordAuthenticationToken(
            username,
            null,
            Collections.singleton((GrantedAuthority) () -> "USER")
        );
    }
}