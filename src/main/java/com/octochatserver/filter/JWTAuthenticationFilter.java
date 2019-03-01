package com.octochatserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octochatserver.entity.UserEntity;
import com.octochatserver.service.UserService;
import com.octochatserver.util.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.octochatserver.config.AppConfig.EXPIRATION_TIME;
import static com.octochatserver.config.AppConfig.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) {

        try {
            UserEntity atempter = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    atempter.getEmail(),
                    atempter.getPassword(),
                    new ArrayList<>()
                )
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authentication
    ) {

        try {
            Response backResponse = new Response();
            String token = Jwts.builder()
                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
            UserEntity user = userService.getByEmail(((User) authentication.getPrincipal()).getUsername());

            user.setToken(token);

            backResponse.success();
            backResponse.data(user);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(backResponse.toJSONMiddle());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
