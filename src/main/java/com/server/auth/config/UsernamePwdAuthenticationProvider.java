package com.server.auth.config;

import com.server.auth.domain.authority.Authority;
import com.server.auth.domain.role.Role;
import com.server.auth.domain.user.User;
import com.server.auth.domain.user.exceptions.UserNotFoundException;
import com.server.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        try {
            User user = userService.getUserByEmail(username);

            if(passwordEncoder.matches(pwd, user.getPassword())){
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(user));
            }else{
                throw new BadCredentialsException("Invalid password!");
            }
        } catch (UserNotFoundException exception){
            throw new BadCredentialsException("User not found!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority : user.getAuthorities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName().toString()));
        }
        for(Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
