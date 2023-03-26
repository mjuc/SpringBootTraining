package org.demo.controllers.request;

import lombok.Getter;

@Getter
public class UserCreationRequest {
    private String username;
    private String password;
    private String authority;
}
