package com.server.auth.service;
import com.server.auth.domain.auth.AuthDTO;
import com.server.auth.domain.user.User;
import com.server.auth.domain.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String auth(AuthDTO authDto){
        try {
            String username = authDto.username();
            String password = authDto.password();

            User user = userService.getUserByEmail(username);

            boolean passwordsMatches = passwordEncoder.matches(password, user.getPassword());

            if(!passwordsMatches) throw new BadCredentialsException("Invalid password");

            return jwtService.createJwt(user);

        } catch (UserNotFoundException ex){
            throw new BadCredentialsException("User not found");
        }
    }

}
