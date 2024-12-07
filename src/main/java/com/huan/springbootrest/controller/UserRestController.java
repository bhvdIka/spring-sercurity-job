package com.huan.springbootrest.controller;

import com.huan.springbootrest.model.User;
import com.huan.springbootrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestController {

    @Autowired
    private UserService service;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> requestBody) {

        String username = requestBody.get("username");
        String password = requestBody.get("password");
        System.out.println(username);
        System.out.println(password);
        boolean isAuthenticated = service.login(username, password);
        if (isAuthenticated) {
            return "Login successful!" + username + password;
        } else {
            return "Invalid username or password!" + username + password;
        }
    }
}