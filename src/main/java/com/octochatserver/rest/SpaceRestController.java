package com.octochatserver.rest;

import com.octochatserver.entity.SpaceEntity;
import com.octochatserver.entity.UserEntity;
import com.octochatserver.service.SpaceService;
import com.octochatserver.service.UserService;
import com.octochatserver.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/space")
public class SpaceRestController {

    @Autowired
    SpaceService spaceService;

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public @ResponseBody
    String register(@RequestBody SpaceEntity space) {
        Response response = new Response();

        if (spaceService.getByName(space.getName()) != null) {
            response.failure().error("This space name is already taken");
        } else {

            if (space.getOwnerId() <= 0) {
                response.failure().error("Error while creating space. No owner specified");
            } else {
                UserEntity user = userService.get(space.getOwnerId());

                space.getSpaceUsers().add(user);
                user.getUserSpaces().add(space);

                SpaceEntity registeredSpace = spaceService.save(space);

                if (registeredSpace != null) {
                    response.success().data(registeredSpace);
                } else {
                    response.failure().error("Error while creating space");
                }
            }
        }

        return response.toJSON();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    String getSpace(@PathVariable int id) {
        Response response = new Response();

        SpaceEntity space = spaceService.get(id);

        if (space != null) {
            response.success();

            response.data(space);
        } else {
            response.failure();
            response.error("Space can not be found");
        }

        return response.toJSON();
    }

    @GetMapping(path = "/{id}/users")
    public @ResponseBody
    Map<String, Object> getSpaceUsers(@PathVariable int id) {
        Response response = new Response();

        SpaceEntity space = spaceService.get(id);

        if (space != null) {
            response.success();

            response.data(space.getSpaceUsers());
        } else {
            response.failure();
            response.error("Space can not be found");
        }

        return response.toMap();
    }
}
