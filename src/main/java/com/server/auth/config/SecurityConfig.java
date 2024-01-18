package com.server.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {





    @Bean
    public PasswordEncoder passwordEncoder(){
//      Seta no Sprint Security nossa classe de encoder de senhas
        return new BCryptPasswordEncoder();
    }
}
