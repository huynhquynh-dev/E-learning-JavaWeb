package com.myclass.controller;

import com.myclass.model.request.AuthRequest;
import com.myclass.model.response.RestResult;
import com.myclass.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Object login(@RequestBody AuthRequest authRequest) {
        RestResult<OAuth2AccessToken> result = new RestResult<>();
        try {
            OAuth2AccessToken oAuth2AccessToken = authService.login(authRequest);
            if (oAuth2AccessToken == null && oAuth2AccessToken.getValue() != null) {
                result.fail();
            }
            else {
                result.ok(oAuth2AccessToken);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            result.fail();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public Object logout(@RequestBody AuthRequest authRequest) {
        RestResult<OAuth2AccessToken> result = new RestResult<>();
        result.ok();
        authService.logout(authRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
