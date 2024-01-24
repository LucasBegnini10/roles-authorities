package com.server.auth.config;

import com.server.auth.domain.role.RoleType;
import com.server.auth.filter.TokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final TokenValidatorFilter tokenValidatorFilter;

    @Autowired
    public SecurityConfig(TokenValidatorFilter tokenValidatorFilter){
        this.tokenValidatorFilter = tokenValidatorFilter;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests)-> requests
                        .requestMatchers("/api/v1/auth/**").permitAll()
//                        .requestMatchers("/api/v1/users").hasAuthority(AuthorityType.VIEW_USERS.getAuthority())
                        .requestMatchers("/api/v1/users").hasRole(RoleType.ROLE_ADMIN.getRole())
                        .anyRequest().authenticated())
                .addFilterBefore(tokenValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
