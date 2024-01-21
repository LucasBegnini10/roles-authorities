package com.server.auth.repository;

import com.server.auth.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByNameIn(Set<String> roles);
}
