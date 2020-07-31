package com.myclass.service.impl;

import com.myclass.model.request.AuthRequest;
import com.myclass.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    TokenStore tokenStore;

    @Override
    public OAuth2AccessToken accessToken(AuthRequest authRequest) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(authRequest.getUsername());
        resourceDetails.setPassword(authRequest.getPassword());
        resourceDetails.setAccessTokenUri("http://localhost:6789/oauth/token");
        resourceDetails.setClientId(authRequest.getClientId());
        resourceDetails.setClientSecret(authRequest.getClientSerect());
        resourceDetails.setGrantType("password");
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        if (restTemplate != null && restTemplate.getAccessToken()!= null) {
            return restTemplate.getAccessToken();
        }
        return null;
    }

    @Override
    public OAuth2AccessToken refreshToken(AuthRequest authRequest) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setAccessTokenUri("http://localhost:6789/oauth/token");
        resourceDetails.setClientId(authRequest.getClientId());
        resourceDetails.setClientSecret(authRequest.getClientSerect());
        resourceDetails.setGrantType("refresh_token");
        ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
        OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(authRequest.getToken());
        OAuth2AccessToken accessToken = provider.refreshAccessToken(resourceDetails, refreshToken, new DefaultAccessTokenRequest());
        if (accessToken != null) {
            return accessToken;
        }
        return null;
    }

    @Override
    public void removeToken(String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);

        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
    }
}
