package com.server.auth.domain.authority;

public enum AuthorityType {
    VIEW_CONTENT("VIEW_CONTENT"),
    CREATE_CONTENT("CREATE_CONTENT"),
    UPDATE_CONTENT("UPDATE_CONTENT"),
    DELETE_CONTENT("DELETE_CONTENT");

    private final String authority;

    AuthorityType(String authority) {
        this.authority = authority;
    }
    public String getAuthority() {
        return this.authority;
    }
}


