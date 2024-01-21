package com.server.auth.repository;

import com.server.auth.domain.authority.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Set<Authority> findByNameIn(Set<String> authorities);
}
