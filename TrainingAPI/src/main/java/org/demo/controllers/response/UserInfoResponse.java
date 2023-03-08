package org.demo.controllers.response;

import java.util.List;

public class UserInfoResponse {
    private int id;
    private String username;
    private String email;
    private List<String> roles;

    public UserInfoResponse(int id, String username, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
