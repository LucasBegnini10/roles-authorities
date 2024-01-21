package com.server.auth.domain.authority;

import lombok.Getter;

@Getter
public enum AuthorityType {
    VIEW_USERS("VIEW_USERS");
    private final String authority;

    AuthorityType(String authority) {
        this.authority = authority;
    }
}


