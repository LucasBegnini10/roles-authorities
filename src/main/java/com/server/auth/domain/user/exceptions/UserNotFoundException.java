package com.server.auth.domain.user.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User not found");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
