package com.stefkova.pantheonui.backend.controller;

import com.stefkova.pantheonui.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get online users without the current user.
     *
     * @param user current user
     *
     * @return collection of online users without the current user
     */
    @GetMapping("/users/{user}")
    public Set<String> getUsers(@PathVariable String user) {
        this.userService.updateUser(user); // update last action time of user
        return this.userService.getUsers(user); // user without current user
    }

    /**
     * Set a user as online. (add to list of online users).
     *
     * @param user user to be set as online
     *
     * @return ok when action succeeded
     */
    @PostMapping("/user/{user}")
    public Response addUser(@PathVariable String user) {
        this.userService.addUser(user);
        return Response.ok().build();
    }

    /**
     * Set a user as offline. (remove from list of online users).
     *
     * @param user user to be set as offline
     *
     * @return ok when action succeeded
     */
    @DeleteMapping("/user/{user}")
    public Response removeUser(@PathVariable String user) {
        this.userService.removeUser(user);
        return Response.ok().build();
    }
}
