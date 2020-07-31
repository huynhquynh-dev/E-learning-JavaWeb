package com.myclass.service;

import com.myclass.model.request.AuthRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface AuthService {

    public OAuth2AccessToken login(AuthRequest authRequest);

    public void logout(AuthRequest authRequest);
}
