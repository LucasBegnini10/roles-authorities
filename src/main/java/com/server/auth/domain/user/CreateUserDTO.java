package com.server.auth.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateUserDTO {

    private String username;
    private String email;
    String password;
    Set<String> roles;
    private Set<String> authorities;

}
