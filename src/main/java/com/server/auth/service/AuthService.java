package com.server.auth.service;
import com.server.auth.domain.auth.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String auth(AuthDTO authDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        return jwtService.createJwt(auth);
    }

}
