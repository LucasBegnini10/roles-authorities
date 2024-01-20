package com.server.auth.service;

import com.server.auth.domain.authority.Authority;
import com.server.auth.domain.role.Role;
import com.server.auth.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService {

    public String createJwt(User user){
        SecretKey key = Keys.hmacShaKeyFor("f102b8dd-ddae-415b-8abe-3f2243f410fd".getBytes(StandardCharsets.UTF_8));

        return Jwts
                .builder()
                .setIssuer("Lucas Begnini Auth")
                .setSubject("JWT Token")
                .claim("username", user.getUsername())
                .claim("id", user.getId())
                .claim("authorities", populateAuthorities(user))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key)
                .compact();
    }

    private String populateAuthorities(User user) {
        Set<String> authoritiesSet = new HashSet<>();
        for (Authority authority : user.getAuthorities()) {
            authoritiesSet.add(authority.getName().toString());
        }

        for(Role role : user.getRoles()){
            authoritiesSet.add(role.getName().toString());
        }

        return String.join(",", authoritiesSet);
    }

}
