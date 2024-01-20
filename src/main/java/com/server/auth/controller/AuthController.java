package com.server.auth.controller;

import com.server.auth.domain.auth.AuthDTO;
import com.server.auth.domain.user.User;
import com.server.auth.service.AuthService;
import com.server.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping
    public String authentication(@RequestBody AuthDTO authDTO){
        return authService.auth(authDTO);
    }
}
