package com.server.auth.controller;

import com.server.auth.domain.auth.AuthDTO;
import com.server.auth.domain.user.CreateUserDTO;
import com.server.auth.domain.user.User;
import com.server.auth.service.AuthService;
import com.server.auth.service.UserService;
import com.server.auth.util.RestResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody CreateUserDTO user){
        return authService.register(user);
    }

    @PostMapping
    public ResponseEntity<Object> authentication(@RequestBody AuthDTO authDTO){
        String token = authService.auth(authDTO);

        return RestResponseHandler.generateResponse("Authenticated", HttpStatus.OK, token);
    }
}
