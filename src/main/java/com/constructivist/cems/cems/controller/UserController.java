package com.constructivist.cems.cems.controller;

import com.constructivist.cems.cems.model.UserId;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.constructivist.cems.cems.model.User;
import com.constructivist.cems.cems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/{foreignKeyId}")
    public User getUserById(@PathVariable Long id, @PathVariable Long foreignKeyId) {
        UserId userId = new UserId(id, foreignKeyId);
        return userService.getUserById(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}/{foreignKeyId}")
    public User updateUser(@PathVariable Long id, @PathVariable Long foreignKeyId, @RequestBody User user) {
        UserId userId = new UserId(id, foreignKeyId);
        user.setUserId(userId);
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}/{foreignKeyId}")
    public void deleteUser(@PathVariable Long id, @PathVariable Long foreignKeyId) {
        UserId userId = new UserId(id, foreignKeyId);
        userService.deleteUser(userId);
    }
}

