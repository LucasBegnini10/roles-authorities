package com.server.auth.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.server.auth.domain.authority.Authority;
import com.server.auth.domain.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Authority> authorities;
}
