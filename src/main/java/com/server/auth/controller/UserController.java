package com.server.auth.controller;

import com.server.auth.domain.user.User;
import com.server.auth.service.UserService;
import com.server.auth.util.RestResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<User> users = userService.getAll();

        return RestResponseHandler.generateResponse("List of users", HttpStatus.OK, users);
    }

}
