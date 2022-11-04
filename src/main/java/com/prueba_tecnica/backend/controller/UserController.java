package com.prueba_tecnica.backend.controller;

import com.prueba_tecnica.backend.model.Request.UserRequest;
import com.prueba_tecnica.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Map<String,?>> getUsers() {
        return userService.getUsers();
    }



    @PostMapping
    public void registerNewUser(@RequestBody UserRequest model) {
        userService.addNewUser(model);
    }

    @PutMapping
    public void updateUser(@RequestBody UserRequest model) {
        userService.updateUser(model);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

}
