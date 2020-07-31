package com.myclass.service;

import com.myclass.model.request.AuthRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuth2Service {

    public OAuth2AccessToken accessToken(AuthRequest authRequest);

    public OAuth2AccessToken refreshToken(AuthRequest authRequest);

    public void removeToken(String token);
}
