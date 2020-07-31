package com.myclass.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    private String username;
    private String password;
    private String grantType;
    private String clientId;
    private String clientSerect;
    private String token;
}
