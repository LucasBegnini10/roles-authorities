package com.server.auth.domain.role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    private String description;

}
