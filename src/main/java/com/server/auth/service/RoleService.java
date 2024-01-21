package com.server.auth.service;

import com.server.auth.domain.role.Role;
import com.server.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;

    public Set<Role> getRolesByName(Set<String> rolesName){
        return roleRepository.findByNameIn(rolesName);
    }

}
