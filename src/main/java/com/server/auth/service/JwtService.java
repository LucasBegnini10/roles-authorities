package com.server.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService {

    public String createJwt(Authentication auth){
        SecretKey key = Keys.hmacShaKeyFor("f102b8dd-ddae-415b-8abe-3f2243f410fd".getBytes(StandardCharsets.UTF_8));
        return Jwts
                .builder()
                .setIssuer("Lucas Begnini Auth")
                .setSubject("JWT Token")
                .claim("username", auth.getName())
                .claim("authorities", populateAuthorities(auth.getAuthorities()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.toString());
        }

        return String.join(",", authoritiesSet);
    }

    public void validateToken(String jwt){
        try {
            SecretKey key = Keys.hmacShaKeyFor("f102b8dd-ddae-415b-8abe-3f2243f410fd".getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            String username = String.valueOf(claims.get("username"));
            String authorities = (String) claims.get("authorities");

            Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Token received!");
        }
    }

}
