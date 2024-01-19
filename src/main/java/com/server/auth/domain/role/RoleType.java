package com.server.auth.domain.role;

public enum RoleType {
    ROLE_USER("USER"),
    ROLE_MODERATOR("MODERATOR"),
    ROLE_ADMIN("ADMIN");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }
}
