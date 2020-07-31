package com.myclass.service.impl;

import com.myclass.model.request.AuthRequest;
import com.myclass.service.AuthService;
import com.myclass.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    OAuth2Service oAuth2Service;

    @Override
    public OAuth2AccessToken login(AuthRequest authRequest) {
        switch (authRequest.getGrantType()) {
            case "password":
                return oAuth2Service.accessToken(authRequest);
            case "refresh_token":
                return oAuth2Service.refreshToken(authRequest);
            default:
                return null;
        }
    }

    @Override
    public void logout(AuthRequest authRequest) {
        oAuth2Service.removeToken(authRequest.getToken());
    }
}
