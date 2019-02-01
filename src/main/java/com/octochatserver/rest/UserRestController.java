package com.octochatserver.rest;

import com.octochatserver.entity.UserEntity;
import com.octochatserver.service.UserService;
import com.octochatserver.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(method = POST, path = "/register")
    public @ResponseBody
    Map<String, Object> register(@RequestBody UserEntity user) {
        Response response = new Response();

        if (userService.getByEmail(user.getEmail()) != null) {
            response.failure().error("This email is already taken");
        } else {

            if (user.getNickname() == null) {
                user.setNickname(user.getEmail());
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            UserEntity registeredUser = userService.save(user);

            if (registeredUser != null) {
                response.success().data(registeredUser);
            } else {
                response.failure().error("Error while creating user");
            }
        }

        return response.toMap();
    }

    @RequestMapping(method = GET, path = "/{id}")
    public @ResponseBody
    Map<String, Object> getUser(@PathVariable int id) {
        Response response = new Response();

        UserEntity user = userService.get(id);

        if (user != null) {
            response.success();
            response.data(user);
        } else {
            response.failure();
            response.error("User can not be found");
        }

        return response.toMap();
    }
}

