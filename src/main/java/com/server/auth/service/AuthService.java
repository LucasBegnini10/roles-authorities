package com.server.auth.service;
import com.server.auth.domain.auth.AuthDTO;
import com.server.auth.domain.user.CreateUserDTO;
import com.server.auth.domain.user.User;
import com.server.auth.domain.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthService(
            JwtService jwtService,
            UserService userService,
            PasswordEncoder passwordEncoder
    ){
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    public String auth(AuthDTO authDto){
        String username = authDto.username();
        String pwd = authDto.password();

        try {
            User user = userService.getUserByEmail(username);

            if(!passwordEncoder.matches(pwd, user.getPassword())) {
                throw new BadCredentialsException("Invalid password!");
            }
            return jwtService.createJwt(user);


        } catch (UserNotFoundException exception){
            throw new BadCredentialsException("User not found!");
        }
    }

    public User register(CreateUserDTO createUser){
        createUser.setPassword(passwordEncoder.encode(createUser.getPassword()));

        User user = userService.buildUser(createUser);

        return userService.save(user);
    }
}
