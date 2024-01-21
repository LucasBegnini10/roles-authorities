package com.server.auth.service;

import com.server.auth.domain.authority.Authority;
import com.server.auth.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorityService {


    @Autowired
    private AuthorityRepository authorityRepository;

    public Set<Authority> getAuthoritiesByName(Set<String> authoritiesName){
        return authorityRepository.findByNameIn(authoritiesName);
    }
}
