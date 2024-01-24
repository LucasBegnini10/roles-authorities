package com.server.auth.service;

import com.server.auth.domain.authority.Authority;
import com.server.auth.domain.role.Role;
import com.server.auth.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createJwt(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("authorities", populateAuthorities(user));

        return buildToken(claims);
    }

    private String populateAuthorities(User user) {

        Set<String> authoritiesSet = new HashSet<>();
        for (Authority authority : user.getAuthorities()) {
            authoritiesSet.add(authority.getName().toString());
        }

        for(Role role: user.getRoles()){
            authoritiesSet.add(role.getName().toString());
        }

        return String.join(",", authoritiesSet);
    }

    private String buildToken(Map<String, Object> claims) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(currentTimeMillis + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public void validateToken(String token){
        try {
            extractAllClaims(token);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Token received!");
        }
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserName(String token){
        Claims claims = extractAllClaims(token);
        return String.valueOf(claims.get("username"));
    }

    public String extractAuthorities(String token){
        Claims claims = extractAllClaims(token);
        return (String) claims.get("authorities");
    }
}
