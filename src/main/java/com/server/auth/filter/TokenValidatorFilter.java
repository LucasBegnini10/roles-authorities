package com.server.auth.filter;

import com.server.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
public class TokenValidatorFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        jwtService.validateToken(token);

        String username = jwtService.extractUserName(token);
        String authorities = jwtService.extractAuthorities(token);

        Authentication auth = buildAuthentication(
                username,
                null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

        setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private Boolean hasToken(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        return authHeader.substring(7);
    }

    private Authentication buildAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities
    ){
        return new UsernamePasswordAuthenticationToken(
                principal,
                credentials,
                authorities
        );
    }

    private void setAuthentication(Authentication auth){
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/api/v1/auth") || !hasToken(request);
    }
}
